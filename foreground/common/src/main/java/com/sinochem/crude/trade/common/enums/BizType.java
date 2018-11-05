package com.sinochem.crude.trade.common.enums;

/**
 * 交易对象（成品油，原油）枚举
 * @author Yichen Zhao
 * date: 20180111
 */
public enum BizType {
    CRUDE_OIL("CrudeOil"),
    PRODUCT_OIL("ProductOil");

    String code;

    BizType(String code) {
        this.code = code;
    }

    public static BizType getBizTypeByCode(String code) {
        for (BizType bizType : BizType.values()) {
            if (bizType.getCode().equals(code)) {
                return bizType;
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
