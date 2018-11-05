package com.sinochem.crude.trade.common.enums;

/**
 * 商检费分摊类型
 * @author Yichen Zhao
 * date: 20180129
 */
public enum InspectionFeeSharingType {

    BUYER_TO_PAY_ALL(1, "seller’s account"),
    SELLER_TO_PAY_ALL(2, "buyer’s account"),
    EACH_HALF(3, "50/50 between seller&buyer"),
    ALL_STAKEHOLDERS_SHARE(4, "shared between parties");

    Integer code;
    String name;

    InspectionFeeSharingType(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public static InspectionFeeSharingType getInspectionFeeSharingTypeByCode(Integer code) {
        for(InspectionFeeSharingType type : InspectionFeeSharingType.values()) {
            if (type.getCode().equals(code)) {
                return type;
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
