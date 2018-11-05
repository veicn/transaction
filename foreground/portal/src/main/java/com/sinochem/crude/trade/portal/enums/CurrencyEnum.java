package com.sinochem.crude.trade.portal.enums;

/**
 * 计价货币枚举
 * @author Yichen Zhao
 * date: 20180226
 */
public enum CurrencyEnum {

    USD("1", "USD", "USD"),
    CNY("2", "CNY", "CNY");

    /**
     * 代码
     */
    private String code;

    /**
     * 中文名称，目前和英文名相同
     */
    private String zhName;

    /**
     * 英文名称
     */
    private String enName;

    CurrencyEnum(String code, String zhName, String enName) {
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
