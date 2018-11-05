package com.sinochem.crude.trade.shipping.enums;

/**
 * 异常信息枚举类
 */
public enum ExceptionEnum {

    NULL_PARAMETER(1, "参数为空", "Input parameters are null"),

    NO_DATA(2, "没有数据", "No data"),

    NO_AUTHORIZATION(3, "没有权限", "No authorization");

    /**
     * 错误代码
     */
    private int code;

    /**
     * 中文信息
     */
    private String zhMessage;

    /**
     * 英文信息
     */
    private String enMessage;

    ExceptionEnum(int code, String zhMessage, String enMessage) {
        this.code = code;
        this.zhMessage = zhMessage;
        this.enMessage = enMessage;
    }

    /** getters */
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getZhMessage() {
        return zhMessage;
    }

    public void setZhMessage(String zhMessage) {
        this.zhMessage = zhMessage;
    }

    public String getEnMessage() {
        return enMessage;
    }

    public void setEnMessage(String enMessage) {
        this.enMessage = enMessage;
    }
}
