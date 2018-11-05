package com.sinochem.crude.trade.listed.enums;

/**
 * DataQuery类的排序属性
 * @author Yichen Zhao
 * date: 20180109
 */
public enum CrudeOilDemandOrderProptertyType {
    NONE("1", ""),
    NUM("2", "d.num"),
    PUB_DATE("3", "d.pub_date"),
    DISCHARGE_START_TIME("5", "ds.discharge_start_time"),
    STOP_BID_TIME("6", "d.stop_bid_time"),;

    String code;
    String name;

    CrudeOilDemandOrderProptertyType(String code, String name) {
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
