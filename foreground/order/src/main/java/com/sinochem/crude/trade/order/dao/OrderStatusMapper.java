package com.sinochem.crude.trade.order.dao;


import com.sinochem.it.b2b.order.status.domain.OrderStatus;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderStatusMapper {



    Long insert(OrderStatus record);


    Long insertSelective(OrderStatus record);


    /**
     * 通过订单号和
     * @param record
     * @return
     */
    void updateByOrderIdAndItemCode(OrderStatus record);

    /**
     * 通过订单编号查询所有的订单状态
     * @param orderId
     * @return
     */
    List<OrderStatus> findAllByOrderId(@Param("orderId")Long orderId);

    /**
     * 通过订单号和节点编号查询订单状态
     * @param orderId
     * @param itemCode
     * @return
     */
    OrderStatus findAllByOrderIdAndItemCode(@Param("orderId")Long orderId, @Param("itemCode") String itemCode);

    /**
     * 通过订单号查询到最后一个改变的订单状态
     * @param orderId
     * @return
     */
    OrderStatus getLastestByOrderId(@Param("orderId")Long orderId);
}