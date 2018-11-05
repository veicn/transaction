package com.sinochem.crude.trade.shipping.enums;

/**
 * 港口信息
 * Created by WGP on 2018/3/21.
 */
public enum PortEnums {

    PRODUCT_ONE("1", "香港港口", "Hong Kong"),

    PRODUCT_TWO("2", "新加坡港口", "Singapore"),

    PRODUCT_THREE("3", "上海港口", "Shanghai");

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

    private PortEnums(String code, String zhName, String enName) {
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
