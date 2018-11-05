package com.sinochem.crude.trade.portal.model;

public enum OilModelTypeEnum {
    DUBAI("DUBAI"),
    WTI("WTI"),
    DTD_BRENT("DTD BRENT"),
    ICE_BRENT("ICE BRENT");

    String code;

    OilModelTypeEnum(String code) {
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
