package com.sinochem.crude.trade.transaction.enums;

/**
 * 港口标识枚举
 * @author Yichen Zhao
 * date: 20180226
 */
public enum ShipPortTypeEnum {

    LOADING_PORT("1", "装港", "Loading Port"), //装港
    DISCHARGE_PORT("2", "卸港", "Discharge Port"); //卸港

    /**
     * 代码
     */
    private String code;

    /**
     * 装港
     */
    private String zhName;

    /**
     * 卸港
     */
    private String enName;

    ShipPortTypeEnum(String code, String zhName, String enName) {
        this.code = code;
        this.zhName = zhName;
        this.enName = enName;
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
}
