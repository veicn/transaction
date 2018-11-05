package com.sinochem.crude.trade.transaction.enums;

/**
 * 数量单位枚举
 * @author gyf
 * date: 20180517
 */
public enum InspetionEnum {

    SELLE_ACCOUNT("1", "Seller's Account", "Seller's Account"),
    BUYER_ACCOUNT("2", "Buyer's Account", "Buyer's Account"),
    BETWEEN_SELLER_50_BUYER_50("3", "50/50 Between Seller & Buyer", "50/50 Between Seller & Buyer");

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

    InspetionEnum(String code, String zhName, String enName) {
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
