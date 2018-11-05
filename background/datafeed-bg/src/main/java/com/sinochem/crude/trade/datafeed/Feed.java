package com.sinochem.crude.trade.datafeed;

import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.ObjectMapper;

public class Feed {

    private static final Log log = LogFactory.getLog(Feed.class);

    private static ObjectMapper objectMapper = new ObjectMapper();

    private static Map<String, String> fieldMap = Maps.newHashMap();
    /*static {
        fieldMap.put("1", "code"); // 代码
        fieldMap.put("2", "typeName"); // 品种名
        fieldMap.put("3", "lastPrice"); // 最近价、首类价、最新值
        fieldMap.put("4", "prevPrice"); // 前一价
        fieldMap.put("5", "upDown"); // 升跌
        fieldMap.put("6", "highPrice"); // 最高价
        fieldMap.put("7", "lowPrice"); // 最低价
        fieldMap.put("8", "trend"); // 走势
        fieldMap.put("9", "time"); // 时间
        fieldMap.put("10", "opening"); // 今开盘
        fieldMap.put("11", "closing"); // 昨收盘、昨叫买
        fieldMap.put("12", "buyPrice"); // 叫买
        fieldMap.put("13", "buyPrice2"); // 叫买２、条件1
        fieldMap.put("14", "buyPrice3"); // 叫买３、条件2
        fieldMap.put("15", "sellPrice"); // 叫卖
        fieldMap.put("16", "sellPrice2"); // 叫卖２、API、规格1
        fieldMap.put("17", "sellPrice3"); // 叫卖３、规格2 、硫含量
        fieldMap.put("18", "buyQuantity"); // 买量
        fieldMap.put("19", "sellQuantity"); // 卖量
        fieldMap.put("20", "delivery"); // 成交量、交割
        fieldMap.put("21", "date"); // 日期
        fieldMap.put("22", "openInterest"); // 空盘量、地点
        fieldMap.put("23", "settlement"); // 结算价、货币单位
        fieldMap.put("24", "turnover"); // 成交额
        fieldMap.put("25", "upDownPercent"); // 升跌%
        fieldMap.put("26", "subPrice"); // 次类价
        fieldMap.put("27", "buyQuantity2"); // 买量２、货币
        fieldMap.put("28", "buyQuantity3"); // 买量３、交易单位
        fieldMap.put("29", "bidder"); // 出价人、卖量２
        fieldMap.put("30", "logisticsType"); // 货运类别、卖量３
        fieldMap.put("31", "unknown"); // unknown
        fieldMap.put("32", "peRatios"); // 市盈率、昨叫卖
        fieldMap.put("33", "unknown2"); // unknown
        fieldMap.put("34", "name"); // Name
    }*/
    
    static {
    	fieldMap.put("1", "1"); // 代码
    	fieldMap.put("2", "2"); // 品种名
    	fieldMap.put("3", "3"); // 最近价、首类价、最新值
    	fieldMap.put("4", "4"); // 前一价
    	fieldMap.put("5", "5"); // 升跌
    	fieldMap.put("6", "6"); // 最高价
    	fieldMap.put("7", "7"); // 最低价
    	fieldMap.put("8", "8"); // 走势
    	fieldMap.put("9", "9"); // 时间
    	fieldMap.put("10", "10"); // 今开盘
    	fieldMap.put("11", "11"); // 昨收盘、昨叫买
    	fieldMap.put("12", "12"); // 叫买
    	fieldMap.put("13", "13"); // 叫买２、条件1
    	fieldMap.put("14", "14"); // 叫买３、条件2
    	fieldMap.put("15", "15"); // 叫卖
    	fieldMap.put("16", "16"); // 叫卖２、API、规格1
    	fieldMap.put("17", "17"); // 叫卖３、规格2 、硫含量
    	fieldMap.put("18", "18"); // 买量
    	fieldMap.put("19", "19"); // 卖量
    	fieldMap.put("20", "20"); // 成交量、交割
    	fieldMap.put("21", "21"); // 日期
    	fieldMap.put("22", "22"); // 空盘量、地点
    	fieldMap.put("23", "23"); // 结算价、货币单位
    	fieldMap.put("24", "24"); // 成交额
    	fieldMap.put("25", "25"); // 升跌%
    	fieldMap.put("26", "26"); // 次类价
    	fieldMap.put("27", "27"); // 买量２、货币
    	fieldMap.put("28", "28"); // 买量３、交易单位
    	fieldMap.put("29", "29"); // 出价人、卖量２
    	fieldMap.put("30", "30"); // 货运类别、卖量３
    	fieldMap.put("31", "31"); // unknown
    	fieldMap.put("32", "32"); // 市盈率、昨叫卖
    	fieldMap.put("33", "33"); // unknown
    	fieldMap.put("34", "34"); // Name
    }

    private boolean update = false;

    private String id;

    private Map<String, String> valueMap = Maps.newHashMap();

    /**
     * 按协议将原始数据解析为Feed对象属性
     * @param payload 原始数据
     */
    public void parseValue(String payload) {
        Iterator<String> it = Splitter.on(',').trimResults().split(payload).iterator();
        if (it.hasNext()) {
            update = "1".equals(it.next());
        } else {
            log.warn("No payload found when parse.");
        }
        if (it.hasNext()) {
            id = it.next();
        } else {
            log.warn("No id found when parse.");
        }
        if (it.hasNext()) {
            String bodySource = it.next();
            for (String mapSource : Splitter.on('\u001A').omitEmptyStrings().split(bodySource)) {
                if (!mapSource.contains("\u001B")) {
                    continue;
                }
                Iterator<String> mapItr = Splitter.on('\u001B').omitEmptyStrings().split(mapSource).iterator();
                String key = null;
                String value = null;
                if (mapItr.hasNext()) {
                    key = mapItr.next();
                }
                if (mapItr.hasNext()) {
                    value = mapItr.next();
                }
                if (!Strings.isNullOrEmpty(value) && fieldMap.containsKey(key)) {
                    valueMap.put(fieldMap.get(key), value);
                }
            }
        } else {
            log.warn("No data found when parse.");
            update = false;
            id = "-1";
        }
    }

    /**
     * 是否是数据更新包
     * @return true/false
     */
    public boolean isUpdate() {
        return update;
    }

    public String getId() {
        return id;
    }

    public Map<String, String> getValueMap() {
        return valueMap;
    }

    /**
     * 按照原始字段号（数字）获取字段值
     * @param num 字段号
     * @return 字段值
     */
    public String getValueByFieldNum(String num) {
        String field = fieldMap.get(num);
        if (Strings.isNullOrEmpty(field)) {
            return null;
        }
        return valueMap.get(field);
    }

    @Override
    public String toString() {
        try {
            return objectMapper.writeValueAsString(valueMap);
        } catch (IOException e) {
            log.error("Convert to json fail.", e);
            return "";
        }
    }
}
