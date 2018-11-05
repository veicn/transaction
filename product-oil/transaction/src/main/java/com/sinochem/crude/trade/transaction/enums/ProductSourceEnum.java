package com.sinochem.crude.trade.transaction.enums;

/**
 * 商品来源枚举，仅当卖家为中化新时使用
 * @author Yichen Zhao
 * date: 20180307
 */
public enum ProductSourceEnum {

    AGENT("1", "代理泉化成品油", "Agent of Sinochem Quanzhou Petrolchemical petroleum products"),
    BUYOUT("2", "买断泉化成品油", "Buyout of Sinochem Quanzhou Petrolchemical petroleum products"),
    OTHER_CHANNELS("3", "其他渠道货源", "Other channels' petroleum products");

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

    ProductSourceEnum(String code, String zhName, String enName) {
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
