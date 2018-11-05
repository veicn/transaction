package com.sinochem.crude.trade.listed.enums;

/**
 * 资讯价格来源的枚举
 * @author Yichen Zhao
 * date: 20180204
 */
public enum PriceSourceEnum {

    PLATTS("Platts"),
    EXCHANGE("EXCHANGE");

    String code;

    PriceSourceEnum(String code) {
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
