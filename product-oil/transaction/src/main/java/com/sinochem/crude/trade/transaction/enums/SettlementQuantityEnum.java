package com.sinochem.crude.trade.transaction.enums;

/**
 * 结算量标准枚举
 * @author Yichen Zhao
 * date: 20180226
 */
public enum SettlementQuantityEnum {

    B_L_QUANTITY("1", "B/L Quantity", "B/L Quantity");

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

    SettlementQuantityEnum(String code, String zhName, String enName) {
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
