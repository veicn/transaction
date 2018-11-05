package com.sinochem.crude.trade.transaction.enums;

/**
 * 数量单位枚举
 * @author gyf
 * date: 20180517
 */
public enum UnitEnum {

    MTS("1", "MT", "MT"),
    BBLS("2", "BBL", "BBL");

    /**
     * 代码
     */
    private String code;

    /**
     * 英文名称
     */
    private String zhName;

    /**
     * 英文名称
     */
    private String enName;

    UnitEnum(String code, String zhName, String enName) {
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
