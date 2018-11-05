package com.sinochem.crude.trade.order.dao;

import com.sinochem.it.b2b.order.status.domain.OrderBizType;

public interface OrderBizTypeMapper {

    void deleteByPrimaryKey(Long id);


    Long insert(OrderBizType record);


    Long insertSelective(OrderBizType record);


    OrderBizType selectByPrimaryKey(Long id);


    void updateByPrimaryKeySelective(OrderBizType record);


    void updateByPrimaryKey(OrderBizType record);

    /**
     * 通过订单编号查询相应的业务类型
     * @param orderId
     * @return
     */
    OrderBizType selectByOrderId(Long orderId);
}