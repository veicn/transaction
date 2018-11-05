package com.sinochem.crude.trade.listed.enums;

/**
 * 报价类型，1-意向报价，2-正式报价
 * @author Yichen Zhao
 * date: 20180117
 */
public enum DemandBiddingType {

    /**
     * 意向报价
     */
    INTENTION_BIDDING(1, "意向报价"),

    /**
     * 正式报价
     */
    ACTUAL_BIDDING(2, "正式报价");

    Integer code;
    String name;

    DemandBiddingType(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public static DemandBiddingType getDemandBiddingTypeByCode(Integer code) {
        for (DemandBiddingType demandBiddingType : DemandBiddingType.values()) {
            if (demandBiddingType.getCode().equals(code)) {
                return demandBiddingType;
            }
        }

        return null;
    }

    /** getters and setters */
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
