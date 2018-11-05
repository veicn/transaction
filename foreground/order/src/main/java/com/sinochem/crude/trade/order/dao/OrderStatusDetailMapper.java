package com.sinochem.crude.trade.order.dao;

import com.sinochem.it.b2b.order.status.domain.OrderStatusDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderStatusDetailMapper {
    /**
     * 按主键插入
     * @param id
     */
    void deleteByPrimaryKey(Long id);

    /**
     * 插入
     * @param record
     * @return
     */
    Long insert(OrderStatusDetail record);

    /**
     * 插入
     * @param record
     * @return
     */
    Long insertSelective(OrderStatusDetail record);

    /**
     * 按主键查询
     * @param id
     * @return
     */
    OrderStatusDetail selectByPrimaryKey(Long id);

    /**
     * 按主键选择性更新
     * @param record
     */
    void updateByPrimaryKeySelective(OrderStatusDetail record);

    /**
     * 按主键全更新
     * @param record
     */
    void updateByPrimaryKey(OrderStatusDetail record);

    /**
     * 通过orderId查询所有
     * @param orderId 订单编号
     * @return
     */
    List<OrderStatusDetail> findAllByOrderId(@Param("orderId")Long orderId);

    /**
     * 通过itemcode查询所有
     * @param orderId
     * @param itemCode
     * @return
     */
    List<OrderStatusDetail> findAllByOrderIdAndItemCode(@Param("orderId")Long orderId, @Param("itemCode") String itemCode);
}