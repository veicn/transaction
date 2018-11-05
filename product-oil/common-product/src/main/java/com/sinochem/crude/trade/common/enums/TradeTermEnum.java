package com.sinochem.crude.trade.common.enums;

/**
 * 贸易条款枚举
 * @author Yichen Zhao
 * date: 20180225
 */
public enum TradeTermEnum {

    FOB("1", "FOB", "FOB"),
    CFR("2", "CFR", "CFR"),
    CIF("3", "CIF", "CIF");

    /**
     * 代码
     */
    private String code;

    /**
     * 中文名称，目前和英文名称一样
     */
    private String zhName;

    /**
     * 英文名称
     */
    private String enName;

    TradeTermEnum(String code, String zhName, String enName) {
        this.code = code;
        this.zhName = zhName;
        this.enName = enName;
    }

    /**
     * 根据代码获取贸易条款
     */
    public static TradeTermEnum getByCode(String code) {
        if (code == null) {
            return null;
        }

        for (TradeTermEnum tradeTermEnum : TradeTermEnum.values()) {
            if (tradeTermEnum.getCode().equals(code)) {
                return tradeTermEnum;
            }
        }

        return null;
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
