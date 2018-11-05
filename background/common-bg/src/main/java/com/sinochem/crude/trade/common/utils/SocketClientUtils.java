package com.sinochem.crude.trade.common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @deprecated not used
 * @see com.sinochem.crude.trade.common.datafeed.DataFeedUtils
 */
public class SocketClientUtils {

	private static Log log = LogFactory.getLog(SocketClientUtils.class);

	private static final ThreadLocal<Socket> threadConnect = new ThreadLocal<Socket>();

	private static final String HOST = "211.157.2.99";

	private static final int PORT = 2920;

	private static Socket client;

	private static OutputStream outStr = null;

	private static InputStream inStr = null;

	private static Thread tRecv = new Thread(new RecvThread());
	private static Thread tKeep = new Thread(new KeepThread());

	public static void connect() throws UnknownHostException, IOException {
		client = threadConnect.get();
		if (client == null) {
			client = new Socket(HOST, PORT);
			threadConnect.set(client);
			tKeep.start();
			System.out.println("========链接开始！========");
		}
		outStr = client.getOutputStream();
		inStr = client.getInputStream();
	}

	public static void disconnect() {
		try {
			outStr.close();
			inStr.close();
			client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static class KeepThread implements Runnable {
		public void run() {
			try {
				System.out.println("=====================开始发送心跳包==============");
				while (true) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("发送心跳数据包");
					outStr.write("sinochem-jcache-data-feed".getBytes());
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	private static class RecvThread implements Runnable {
		public void run() {
			try {
				System.out.println("==============开始接收数据===============");
				while (true) {
					/*BufferedReader br = new BufferedReader(new InputStreamReader(inStr));
					String info = null;
					System.out.println(br.readLine());
					while ((info = br.readLine()) != null) {
						System.out.println("我是客户端，服务器说：" + new String(info.getBytes("ISO8859-1"), "UTF-8"));
					}*/
					
					byte[] b = new byte[1024];
					int r = inStr.read(b);
//					System.out.println(b);
					if (r > -1) {
						String str = new String(b,"GBK");
						System.out.println(str);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	public static void main(String[] args) {
		/*int i = 1;
		boolean flag = true;
		while (flag) {
			if (i == 1000) {
				flag = false;
			}

			try {
				// 创建一个客户端socket
				Socket socket = new Socket("211.157.2.99", 2920);
				// 向服务器端传递信息
				OutputStream ots = socket.getOutputStream();
				PrintWriter pw = new PrintWriter(ots);
				pw.write("sinochem-jcache-data-feed");
				pw.flush();
				// 关闭输出流
				socket.shutdownOutput();
				// 获取服务器端传递的数据
				InputStream is = socket.getInputStream();
				InputStreamReader isr = new InputStreamReader(is);
				BufferedReader br = new BufferedReader(isr);
				String info = null;
				while ((info = br.readLine()) != null) {
					System.out.println("我是客户端，服务器说："
							+ new String(info.getBytes("ISO8859-1"), "UTF-8"));
				}
				// 关闭资源
				br.close();
				isr.close();
				is.close();
				pw.close();
				ots.close();
				socket.close();
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println(i);
			i++;
		}*/
		
		try {
			SocketClientUtils.connect();
			tRecv.start();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	}
}
