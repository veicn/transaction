package com.sinochem.crude.trade.listed.enums;

/**
 * 单据（Demand）类型
 * @author Yichen Zhao
 * date: 20180111
 */
public enum DemandType {

    /**
     * 需求单
     */
    REQUIRE("D"),

    /**
     * 报价单
     */
    BIDDING("B"),

    /**
     * 贸易链单
     */
    TRADINGCHAIN("T");

    String code;

    DemandType(String code) {
        this.code = code;
    }

    public static DemandType getDemandTypeByCode(String code) {
        for (DemandType demandType : DemandType.values()) {
            if (demandType.getCode().equals(code)) {
                return demandType;
            }
        }

        return null;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
