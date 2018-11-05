package com.sinochem.crude.trade.transaction.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 国家枚举
 * @author Yichen Zhao
 * date: 20180309
 */
public enum CountryEnum {

    CHINA("1", "中国", "China", "1"),
    SINGAPORE("2", "新加坡", "Singapore", "2"),
    MALAYSIA("3", "马来西亚", "Malaysia", "2"),
    INDONESIA("4", "印度尼西亚", "Indonesia", "2"),
    PHILIPPINES("5", "菲律宾", "Philippines", "2"),
    SOUTH_KOREA("6", "韩国", "South Korea", "2"),
    AUSTRALIA("7", "澳大利亚", "Australia", "2"),
    INDIA("8", "印度", "India", "2"),
    To_Be_Advised("9", "待定", "To be advised", "2");
    private String code;

    private String zhName;

    private String enName;

    /**
     * 港口类型代码（ShipPortTypeEnum）
     */
    private String shipPortTypeCode;

    CountryEnum(String code, String zhName, String enName, String shipPortTypeCode) {
        this.code = code;
        this.zhName = zhName;
        this.enName = enName;
        this.shipPortTypeCode = shipPortTypeCode;
    }

    /** getters */
    public String getCode() {
        return code;
    }

    public String getZhName() {
        return zhName;
    }

    public String getEnName() {
        return enName;
    }

    public String getShipPortTypeCode() {
        return shipPortTypeCode;
    }


    /**
     * 方便翻译
     * @return
     * @author wdh
     */
    public static Map<String,String> toMap(){
        Map<String,String> map = new HashMap<>();
        map.put("China","中国");
        map.put("Singapore","新加坡");
        map.put("Malaysia","马来西亚");
        map.put("Indonesia","印度尼西亚");
        map.put("Philippines","菲律宾");
        map.put("South Korea","韩国");
        map.put("Australia","澳大利亚");
        map.put("India","印度");
        map.put("To be advised","待定");
        return  map;
    }
}
