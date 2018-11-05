package com.sinochem.crude.trade.transaction.enums;

/**
 * 市场枚举
 * @author Yichen Zhao
 * date: 20180301
 */
public enum PriceRegionEnum {

    FOB_SINGAPORE("1", "FOB Singapore", "FOB Singapore"),
    FOB_ARAB_GULF("2", "FOB Arab Gulf", "FOB Arab Gulf"),
    MIDEAST_GULF("3", "Mideast Gulf", "Mideast Gulf"),
    FOB_JAPAN("4", "FOB Japan", "FOB Japan");


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

    PriceRegionEnum(String code, String zhName, String enName) {
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
