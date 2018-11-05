package com.sinochem.crude.trade.transaction.enums;

/**
 * 计价源枚举
 * @author Yichen Zhao
 * date: 20180301
 */
public enum PriceSourceEnum {

    PLATTS("1", "Platts", "Platts"),
    ARGUS("2", "Argus", "Argus");

    /**
     * 代码
     */
    private String code;

    /**
     * 中文名
     */
    private String zhName;

    /**
     * 英文名
     */
    private String enName;

    PriceSourceEnum(String code, String zhName, String enName) {
        this.code = code;
        this.zhName = zhName;
        this.enName = enName;
    }

    /** getters */
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getZhName() {
        return zhName;
    }

    public void setZhName(String zhName) {
        this.zhName = zhName;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }
}
