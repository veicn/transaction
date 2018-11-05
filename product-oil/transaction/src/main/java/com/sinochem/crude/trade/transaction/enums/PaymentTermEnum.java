package com.sinochem.crude.trade.transaction.enums;

/**
 * 付款条件枚举
 * @author Yichen Zhao
 * date: 20180226
 */
public enum PaymentTermEnum {

    T_T_30_DAYS_AFTER_B_L_B_L_0("1", "T/T 30 days after B/L,B/L=0", "T/T 30 days after B/L,B/L=0"),
    L_C_30_DAYS("2", "L/C 30 days", "L/C 30 days"),
    T_T_30_DAYS_FROM_B_L_B_L_1("3", "T/T 30 days from B/L, B/L=1", "T/T 30 days from B/L, B/L=1");

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

    PaymentTermEnum(String code, String zhName, String enName) {
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
