package com.sinochem.crude.trade.order.model.vo;

import com.sinochem.it.b2b.order.status.domain.OrderStatus;

public class OrderStatusVO extends OrderStatus{

    /**
     * 描述
     */
    String itemDesc;

    /**
     * 状态描述
     */
    String valueDesc;

    public OrderStatusVO() {
    }

    public OrderStatusVO(OrderStatus orderStatus, String itemDesc , String valueDesc) {
        this.itemDesc = itemDesc;
        this.valueDesc = valueDesc;
        this.setClearFlag(orderStatus.isClearFlag());
        this.setItemCode(orderStatus.getItemCode());
        this.setOrderId(orderStatus.getOrderId());
        this.setValue(orderStatus.getValue());
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public String getValueDesc() {
        return valueDesc;
    }

    public void setValueDesc(String valueDesc) {
        this.valueDesc = valueDesc;
    }
}
