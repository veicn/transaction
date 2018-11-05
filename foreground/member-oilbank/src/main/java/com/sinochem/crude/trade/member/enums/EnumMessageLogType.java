package com.sinochem.crude.trade.member.enums;

public enum EnumMessageLogType {
    MAIL("1", "邮件"),

    SMS("2", "短信"),

    PUSH("3", "推送");

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

    EnumMessageLogType(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
