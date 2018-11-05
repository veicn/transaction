package com.sinochem.crude.trade.order.service;


import com.sinochem.it.b2b.order.status.domain.OrderStatus;

public interface OrderMessageService {

    String sendOrderChangeMessage(Long orderId, String message);

    public String sendOrderChangeMessage(OrderStatus orderStatus);
}
