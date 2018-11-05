package com.sinochem.crude.trade.portal.enums;

/**
 * 期现对比结果枚举
 * @author Yichen Zhao
 * date: 20180423
 */
public enum PriceCompareResultEnum {

    NO_PROFIT_OPPORTUNITY("1"),
    PHYSICAL_UNDERESTIMATED("3"),
    PHYSICAL_OVERVALUED("2");

    private String code;

    PriceCompareResultEnum(String code) {
        this.code = code;
    }

    /** getters */
    public String getCode() {
        return code;
    }
}
