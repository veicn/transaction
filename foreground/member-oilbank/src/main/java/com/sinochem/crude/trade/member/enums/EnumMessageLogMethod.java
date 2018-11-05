package com.sinochem.crude.trade.member.enums;

public enum EnumMessageLogMethod {
    NOW("1", "立即发送"),

    DELAY("2", "定时发送"),

    PERIOD("3", "周期发送");

    private String code;

    private String desc;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    EnumMessageLogMethod(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
