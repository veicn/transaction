package com.sinochem.crude.trade.common.enums;

/**
 * 单据交易类型
 * @author Yichen Zhao
 * date: 20180111
 */
public enum DealType {

    BUY("B"),
    SELL("S");

    String code;

    DealType(String code) {
        this.code = code;
    }

    public static DealType getDealTypeByCode(String code) {
        for (DealType dealType : DealType.values()) {
            if (dealType.getCode().equals(code)) {
                return dealType;
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
