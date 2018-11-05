package com.sinochem.crude.trade.portal.enums;

/**
 * 地域枚举
 * @author Yichen Zhao
 * date: 20180414
 */
public enum RegionEnum {

    MIDDLE_EAST("MIDDLE_EAST", "中东", "Middle East"),
    NORTH_AFRICA("North_Africa", "北非", "North Africa");

    private String code;
    private String zhName;
    private String enName;

    RegionEnum(String code, String zhName, String enName) {
        this.code = code;
        this.zhName = zhName;
        this.enName = enName;
    }

    /** getters */
    public String getCode() {
        return code;
    }

    public String getZhName() {
        return zhName;
    }

    public String getEnName() {
        return enName;
    }
}
