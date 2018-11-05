package com.sinochem.crude.trade.order.remote;

import java.io.Serializable;

public class DemandOrderReturnVO implements Serializable{
    private String uuid;
    private String orderNo;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
}
