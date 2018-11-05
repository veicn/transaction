package com.sinochem.crude.trade.listed.enums;

/**
 * 资讯合约市场的枚举
 * @author Yichen Zhao
 * date: 20180204
 */
public enum MarketEnum {

    DATED_NORTH_SEA("DATED NORTH SEA"),
    FOB_ARAB_GULF("FOB ARAB GULF"),
    ICE_DUBAI_SWAP("ICE-DUBAI SWAP"),
    DTD_BRENT_DUBAI("DTD Brent-Dubai"),
    ICE("ICE"),
    NYMEX("NYMEX");

    String code;

    MarketEnum(String code) {
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
