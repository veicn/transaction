package com.sinochem.crude.trade.transaction.enums;

/**
 * 计价期枚举
 * @author Yichen Zhao
 * date: 20180226
 */
public enum PricingPeriodEnum {

    CAL_B_L_MONTH("1", "Cal B/L Month", "Cal B/L Month"),
    OTHERS("2", "Others", "Others");

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

    PricingPeriodEnum(String code, String zhName, String enName) {
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
