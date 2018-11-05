package com.sinochem.crude.trade.portal.enums;

/**
 * 允许的距离当前时间的到港时间月差
 * @author Yichen Zhao
 * date: 20180416
 */
public enum AvailableArrivalMonthDifferenceEnum {

    THREE_MONTHS(3),
    FOUR_MONTHS(4);

    private Integer monthDifference;

    AvailableArrivalMonthDifferenceEnum(Integer monthDifference) {
        this.monthDifference = monthDifference;
    }

    /** getters */
    public Integer getMonthDifference() {
        return monthDifference;
    }
}
