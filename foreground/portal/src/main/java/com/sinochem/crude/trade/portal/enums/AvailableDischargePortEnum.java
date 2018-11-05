package com.sinochem.crude.trade.portal.enums;

public enum AvailableDischargePortEnum {

    DALIAN("01"),
    QINGDAO("02"),
    RIZHAO("03"),
    NINGBO("06"),
    ZHOUSHAN("07"),
    QUANZHOU("08"),
    ZHANJIANG("10");

    private String code;

    AvailableDischargePortEnum(String code) {
        this.code = code;
    }

    public static boolean containsCode(String code) {
        for (AvailableDischargePortEnum availableDischargePortEnum : AvailableDischargePortEnum.values()) {
            if (availableDischargePortEnum.getCode().equals(code)) {
                return true;
            }
        }

        return false;
    }

    /** getters */
    public String getCode() {
        return code;
    }
}
