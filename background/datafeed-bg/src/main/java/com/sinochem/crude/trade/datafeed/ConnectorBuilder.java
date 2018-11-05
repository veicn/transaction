package com.sinochem.crude.trade.datafeed;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 用来构建socket客户端连接对象
 *
 * @author HuangWj
 */
public class ConnectorBuilder {

    private static final Log log = LogFactory.getLog(ConnectorBuilder.class);

    private SocketChannel socketChannel;
    private Selector selector;
    private AtomicBoolean closeFlag = new AtomicBoolean(false);
    
    private Manager manager;
    private AtomicBoolean connstate = new AtomicBoolean(false);

    private Callback<byte[]> callback;
    
	public class Manager extends Thread {
		public String host;
		public int port;

		public Manager(String host, int port) {
			this.host = host;
			this.port = port;
		}
		
		public void setEstablished() {
			connstate.getAndSet(true);
		}

		public void setDisconnect() {
			connstate.getAndSet(false);
		}

		public boolean getConnstate() {
			return connstate.get();
		}

		public void run() {
			while (true) {
				try {
					ConnectorBuilder.this.connect0(host, port);
					Connector connector = ConnectorBuilder.this.new Connector();
					connector.startConnect();
					log.error("connect to server sucessful ");
				} catch (Exception ex) {
					log.error("connect to server fail ");
					continue;
				}

				while (getConnstate()) {
					try {
						synchronized (this) {
							this.wait();
							log.debug("got a news about disconnection and reconnect to server");
						}
					} catch (InterruptedException e) {
						log.debug(e.getMessage());
					}
				}
			}
		}
	}

    public class Connector extends Thread {

        private int idCounter = 0;
        private Map<Integer, PeriodicTask> taskList = Maps.newHashMap();
        
        /**
         * 添加一个大致定时执行的任务，执行时间会有误差
         * @param time 执行预期时间，单位毫秒
         * @param task 运行的任务
         * @return 任务id
         */
        public int addPeriodic(long time, Callback<Integer> task) {
            PeriodicTask taskContainer = new PeriodicTask();
            int id = ++idCounter;
            taskContainer.setId(id);
            taskContainer.setTime(time);
            taskContainer.setTask(task);
            taskList.put(id, taskContainer);
            return id;
        }
        
        /**
         * 开始连接服务器
         */
        public void startConnect() {
            this.start();
            this.write("sinochem-jcache-data-feed".getBytes());
            this.addPeriodic(20000, new Callback<Integer>() {
                @Override
                public void handle(Integer data) {
                	Connector.this.write("sinochem-jcache-data-feed".getBytes());
                }
            });
        }

        /**
         * 结束一个定时任务
         * @param id 任务id
         */
        public void endPeriodic(int id) {
            taskList.remove(id);
        }

        @Override
        public void run() {
            try {
                // EventLoop start
                while (!closeFlag.get()) {
                    // handle periodic task
                    for (Entry<Integer, PeriodicTask> e : taskList.entrySet()) {
                        e.getValue().runIfTimeOut(System.currentTimeMillis());
                    }
                    int select = selector.select(300); // add 300ms timeout to run periodic task
                    if (select <= 0) {
                        Thread.sleep(50);
                        continue;
                    }
                    Set<SelectionKey> keys = selector.selectedKeys();
                    Iterator<SelectionKey> iter = keys.iterator();
                    while (iter.hasNext()) {
                        SelectionKey sk = iter.next();
                        iter.remove();
                        if (sk.isReadable()) {
                            SocketChannel channel = (SocketChannel)sk.channel();
                            ByteBuffer buffer = ByteBuffer.allocate(1024);
                            while (channel.read(buffer) > 0) {
                                buffer.flip();
                                callback.handle(buffer.array());
                                buffer.clear();
                            }
                        }
                    }
                }
            } catch (Exception e) {
            	log.error("Write fail.", e);
                
                try {
                	log.debug("disconnection event happened");
                	log.info("shutdown socket connect Start....");
                	this.close();
                	log.info("shutdown socket connect end....");
    				synchronized (manager) {
    					closeFlag.getAndSet(true);
    					manager.setDisconnect();
    					manager.notifyAll();
    				}
    			} catch (Exception e1) {
    				log.error(e1.getMessage());
    			}
            } finally {
            	log.info("thie process ending");
                //ConnectorBuilder.this.close();
            }
        }

        /**
         * 向socket通道中写入数据（发送到服务方）
         * @param data 写入的数据
         */
        public void write(byte[] data) {
            try {
                ByteBuffer buffer = ByteBuffer.wrap(data);
                while (buffer.hasRemaining()) {
                    socketChannel.write(buffer);
                }
            } catch (IOException e) {
                log.error("Write fail.", e);
                
                try {
                	log.debug("disconnection event happened");
                	log.info("shutdown socket connect Start....");
                	this.close();
                	log.info("shutdown socket connect end....");
    				synchronized (manager) {
    					closeFlag.getAndSet(true);
    					manager.setDisconnect();
    					manager.notifyAll();
    				}
    			} catch (Exception e1) {
    				log.error(e1.getMessage());
    			}
    			/*sk.cancel();*/
            }
        }

        public void close() {
            ConnectorBuilder.this.close();
        }
    }

    /**
     * 获取连接对象
     * @param host 主机
     * @param port 端口
     * @param callback 接收数据后执行的回调
     * @return 连接对象
     */
    public static Manager connect(String host, int port, Callback<byte[]> callback) {
        ConnectorBuilder builder = new ConnectorBuilder();
        builder.callback = callback;
        builder.manager = builder.new Manager(host, port);
        return builder.manager;
    }

    // 实际的处理方法
    private void connect0(String host, int port) throws Exception {

        InetSocketAddress addr;

        // Define address info
        if (Strings.isNullOrEmpty(host)) {
            addr = new InetSocketAddress(port);
        } else {
            addr = new InetSocketAddress(host, port);
        }

        // Init nio socket channel and register to selector
        try {
            socketChannel = SocketChannel.open();
            selector = Selector.open();
            socketChannel.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_READ);
            socketChannel.connect(addr);
        } catch (IOException e) {
            log.error("Connect fail.", e);
            throw e;
        }

        // Check connect
        log.info("socket connecting to " + addr);
        try {
            int dots = 0;
            while (!socketChannel.finishConnect()) {
                dots++;
                System.out.print(".");
                if (dots > 30) {
                    dots = 0;
                    System.out.println();
                }
                Thread.sleep(10);
            }
            System.out.println();
            log.info("socket connected");
        } catch (IOException | InterruptedException e) {
            log.error("Connect fail.", e);
            throw e;
        }
        closeFlag.getAndSet(false);
        manager.setEstablished();

        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                close();
            }
        }));

    }

    private void close() {
        try {
            if (selector != null && selector.isOpen()) {
                selector.close();
            }
            if (socketChannel != null && socketChannel.isOpen()) {
                socketChannel.close();
            }
            closeFlag.getAndSet(true);
            manager.setDisconnect();
        } catch (IOException e) {
            log.error("Close socket client fail.", e);
        }
    }
}
