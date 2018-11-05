package com.sinochem.crude.trade.transaction.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 港口信息枚举
 * @author Yichen Zhao
 * date: 20180228
 */
public enum ShipPortEnum {

    //中国
    QUANZHOU("1", "泉州港", "Quanzhou", "1", "1"),
    XIAMEN("2", "厦门港", "Xiamen", "1", "1"),
    DALIAN("3", "大连港", "Dalian", "1", "1"),
    LONGKOU("4", "龙口港", "Longkou", "1", "1"),
    QINGDAO("5", "青岛港", "Qingdao", "1", "1"),
    DONGGUAN("29", "东莞", "Dongguan", "1", "1"),
    HUIZHOU("30", "惠州", "Huizhou", "1", "1"),

    //新加坡
    TANKSTORE("6", "Tankstore", "Tankstore", "2", "2"),
    UNIVERSAL("7", "Universal", "Universal", "2", "2"),
    VOPAK("8", "Vopak", "Vopak", "2", "2"),
    HORIZON("9", "Horizon", "Horizon", "2", "2"),
    SHELL("10", "Shell", "Shell", "2", "2"),
    EXXONMOBIL("11", "Exxonmobil", "Exxonmobil", "2", "2"),
    CHEVRON("12", "Chevron", "Chevron", "2", "2"),
    OILTNKING("13", "Oiltanking", "Oiltanking", "2", "2"),

    //马来西亚
    TANJUNG_BIN("14", "Tanjung Bin", "Tanjung Bin", "2", "3"),
    PENGRANG("15", "Pengrang", "Pengrang", "2", "3"),
    KARIMUN("16", "KARIMUN", "Karimun", "2", "3"),
    TANJUNG_LANGSAT("17", "Tanjung Langsat", "Tanjung Langsat", "2", "3"),

    //印度尼西亚
    BALONGAN("18", "Balongan", "Balongan", "2", "4"),
    MERAK("19", "Merak", "Merak", "2", "4"),

    //菲律宾
    SUBLIC_BAY("20", "Sublic Bay", "Sublic Bay", "2", "5"),
    BATAAN("21", "Bataan", "Bataan", "2", "5"),

    //韩国
    YOSU("22", "Yosu", "Yosu", "2", "6"),
    ULSAN("23", "Ulsan", "Ulsan", "2", "6"),

    //澳大利亚
    ADELAIDE("24", "Adelaide", "Adelaide", "2", "7"),
    MELBOURNE("25", "Melbourne", "Melbourne", "2", "7"),
    PORT_HEDLAND("26", "Port Hedland", "Port Hedland", "2", "7"),
    DARWIN("27", "Darwin", "Darwin", "2", "7"),

    //印度
    HALDIA("31", "Haldia", "Haldia", "2", "8"),

    //待定
    To_Be_Advised("28", "To be advised", "To be advised", "2","9");



    /**
     * 代码
     */
    private String code;

    /**
     * 中文名称
     */
    private String zhName;

    /**
     * 英文名称
     */
    private String enName;

    /**
     * 港口类型（ShipPortTypeEnum）
     */
    private String shipPortTypeCode;

    /**
     * 国家代码（CountryEnum）
     */
    private String countryCode;

    ShipPortEnum(String code, String zhName, String enName, String shipPortTypeCode, String countryCode) {
        this.code = code;
        this.zhName = zhName;
        this.enName = enName;
        this.shipPortTypeCode = shipPortTypeCode;
        this.countryCode = countryCode;
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

    public String getCountryCode() {
        return countryCode;
    }

    /**
     * 方便翻译
     * @return
     * @author wdh
     */
    public static Map<String,String> toMap(){
        Map<String,String> map = new HashMap<>();
        map.put("Quanzhou","泉州港");
        map.put("Xiamen","厦门港");
        map.put("Dalian","大连港");
        map.put("Longkou","龙口港");
        map.put("Qingdao","青岛港");
        map.put("Dongguan","东莞");
        map.put("Huizhou","惠州");
        map.put("Tankstore","Tankstore");
        map.put("Universal","Universal");
        map.put("Vopak","Vopak");
        map.put("Horizon","Horizon");
        map.put("Shell","Shell");
        map.put("Exxonmobil","Exxonmobil");
        map.put("Chevron","Chevron");
        map.put("Oiltanking","Oiltanking");
        map.put("Tanjung","Tanjung");
        map.put("Pengrang","Pengrang");
        map.put("Karimun","Karimun");
        map.put("Tanjung Langsat","Tanjung Langsat");
        map.put("Balongan","Balongan");
        map.put("Merak","Merak");
        map.put("Sublic Bay","Sublic Bay");
        map.put("Bataan","Bataan");
        map.put("Yosu","Yosu");
        map.put("Ulsan","Ulsan");
        map.put("Adelaide","Adelaide");
        map.put("Melbourne","Melbourne");
        map.put("Port Hedland","Port Hedland");
        map.put("Darwin","Darwin");
        map.put("Haldia","Haldia");
        map.put("To be advised","To be advised");
        return  map;
    }
}
