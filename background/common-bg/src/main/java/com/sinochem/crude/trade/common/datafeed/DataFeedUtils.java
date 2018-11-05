package com.sinochem.crude.trade.common.datafeed;

import java.util.Map;
import java.util.Set;

import org.redisson.api.RMap;

import com.sinochem.crude.trade.common.utils.JedisUtils;

/**
 * socket接口数据工具类
 */
public final class DataFeedUtils {
	public static String FEED_KEY_PREFIX = "_feed_id_";

	public static RMap<String, Map<String, String>> map = JedisUtils.getRedisson().getMap(FEED_KEY_PREFIX + "map");
	
    public static Set<String> listId() {
        return map.keySet();
    }

    public static Map<String, String> getItem(String id) {
        return map.get(id);
    }
}
