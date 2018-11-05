package com.sinochem.crude.trade.common.enums;

/**
 * 付款日期的枚举
 * @author Yichen Zhao
 * date: 20180111
 */
public enum PayItemType {

    AFTER_B_OR_L_30_DAYS(1, "After B/L 30 Days, B/L=0"),
    AFTER_B_OR_L_60_DAYS(2, "After B/L 60 Days, B/L=0"),
    AFTER_NOR_15_DAYS(3, "After NOR 15 Days, B/L=0"),
    AFTER_NOR_30_DAYS(4, "After NOR 30 Days, NOR=0"),
    FROM_B_OR_L_30_DAYS(5, "From B/L 30 Days, B/L=1"),
    FROM_B_OR_L_60_DAYS(6, "From B/L 60 Days, B/L=1"),
    FROM_NOR_15_DAYS(7, "From NOR 15 Days, B/L=1"),
    FROM_NOR_30_DAYS(8, "From NOR 30 Days, B/L=1");

    Integer code;
    String name;

    PayItemType(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public static PayItemType getPayItemTypeByCode(Integer code) {
        for (PayItemType payItemType : PayItemType.values()) {
            if (payItemType.getCode().equals(code)) {
                return payItemType;
            }
        }

        return null;
    }

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
