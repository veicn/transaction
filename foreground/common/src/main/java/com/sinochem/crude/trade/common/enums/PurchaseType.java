package com.sinochem.crude.trade.common.enums;

/**
 * 采购方式（招标，询价）枚举
 * @author Yichen Zhao
 * date: 20180110
 */
public enum PurchaseType {

    /** 招标 */
    BIDDING(1, "招标"),
    ENQUIRY(2, "询价");

    Integer code;
    String name;

    PurchaseType(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public static PurchaseType getPurchaseTypeByCode(Integer code) {
        for (PurchaseType purchaseType : PurchaseType.values()) {
            if (purchaseType.getCode().equals(code)) {
                return purchaseType;
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
