package com.sinochem.crude.trade.listed.enums;

/**
 * 原油品类枚举
 * @author Yichen Zhao
 * date: 20180204
 */
public enum CommodityEnum {

    DTD_BRENT_SPREAD("DTD Brent spread"),
    DUBAI_SPREAD("Dubai spread"),
    EFS("EFS"),
    DTD_BRENT_DUBAI("DTD Brent/Dubai"),
    BRENT("Brent"),
    WTI("WTI");

    /**
     * 合约名称
     */
    String code;

    CommodityEnum(String code) {
        this.code = code;
    }

    /** getters and setters */
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
