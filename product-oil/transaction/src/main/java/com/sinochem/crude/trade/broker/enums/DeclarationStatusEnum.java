package com.sinochem.crude.trade.broker.enums;

public enum DeclarationStatusEnum {
    SAVE("1","保存");

    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    private String value;
    DeclarationStatusEnum(String code,String value){
        this.code=code;
        this.value=value;
    }

}
