package com.sinochem.crude.trade.shipping.enums;
/**
 * 港口信息
 * Created by WGP on 2018/3/21.
 */
public enum DatebuiltEnums {

    BUILT_ONE("1", "0-20years", "0-20years"),

    BUILT_TWO("2", "0-15years", "0-15years"),

    BUILT_THREE("3", "0-10years", "0-10years"),

    BUILT_FOUR("4", "non", "non"),

    BUILT_FIVE("5", "other", "other");

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

    private DatebuiltEnums(String code, String zhName, String enName) {
        this.code = code;
        this.zhName = zhName;
        this.enName = enName;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

    public String getZhName() {
        return zhName;
    }

    public String getEnName() {
        return enName;
    }
}
