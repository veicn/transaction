package com.sinochem.crude.trade.transaction.enums;

/**
 * 运输方式枚举
 * @author Yichen Zhao
 * date: 20180226
 */
public enum TransportModeEnum {

//    BY_SEA("1", "By Sea", "By Sea");

    BY_SEA("1", "海运", "By Sea");

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

    TransportModeEnum(String code, String zhName, String enName) {
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
