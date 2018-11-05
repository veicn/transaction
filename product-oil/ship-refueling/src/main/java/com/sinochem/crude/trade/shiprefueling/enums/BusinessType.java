package com.sinochem.crude.trade.shiprefueling.enums;

public enum BusinessType {
    PURCHASE("1", "采购"),
    SALE("2", "销售"),
    IGNITION("3", "船燃订单"),
    SUPPLY("4", "船供订单"),
    CRUDE_OIL("9" ,"原油订单");

    private String code;
    private String name;

    BusinessType(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
