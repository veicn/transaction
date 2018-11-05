package com.sinochem.crude.trade.datafeed;

import java.util.Map;
import java.util.Set;

import com.sinochem.crude.trade.datafeed.ConnectorBuilder.Manager;

/**
 * socket接口数据工具类
 */
public final class DataFeedInit {

    private DataFeedInit() { }

    private static final BytePumper pumper = new BytePumper();

    private static Manager manager;

    private static Handler handler;
    
    private static String dataFeedHost;

	private static Integer dataFeedPort;

	
	public static void init(String host, int port) {
		dataFeedHost = host;
		dataFeedPort = port;
		init();
	}
	
    public static void init() {
        manager = ConnectorBuilder.connect(dataFeedHost, dataFeedPort, new Callback<byte[]>() {
            @Override
            public void handle(byte[] data) {
                pumper.push(data);
            }
        });
    }

    /**
     * 注册数据项处理器
     * @param handler 处理器
     */
    public static void registerHandler(final Handler handler) {
        DataFeedInit.handler = handler;
        pumper.setCallback(handler);
    }

    /**
     * 开始连接服务器
     */
    public static void start() {
        manager.start();
        /*manager.write("sinochem-jcache-data-feed".getBytes());
        manager.addPeriodic(20000, new Callback<Integer>() {
            @Override
            public void handle(Integer data) {
                manager.write("sinochem-jcache-data-feed".getBytes());
            }
        });*/
    }

    /**
     * 列出数据id列表
     * @return id列表
     */
    public static Set<String> listId() {
        return handler.listId();
    }

    /**
     * 获取一条数据
     * @param id 数据id
     * @return 数据项map
     */
    public static Map<String, String> getItem(String id) {
        return handler.getItem(id);
    }
}
