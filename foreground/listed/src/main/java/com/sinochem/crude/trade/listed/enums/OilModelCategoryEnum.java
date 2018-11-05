package com.sinochem.crude.trade.listed.enums;

public enum OilModelCategoryEnum {

    FIXED(0),
    VARIATIONAL(1);

    Integer code;

    OilModelCategoryEnum(Integer code) {
        this.code = code;
    }

    /** getters and setters */
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
