package com.sinochem.crude.trade.order.dao.impl;

import com.sinochem.crude.trade.order.dao.OrderBizTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sinochem.it.b2b.order.status.dao.OrderBizTypeDao;
import com.sinochem.it.b2b.order.status.domain.OrderBizType;

@Component
public class OrderBizTypeDaoImpl implements OrderBizTypeDao {

	@Autowired
	OrderBizTypeMapper orderBizTypeMapper;

	@Override
	public OrderBizType getByOrder(Long orderId) {
		return orderBizTypeMapper.selectByOrderId(orderId);
	}
	public OrderBizType insert(OrderBizType orderBizType){
		orderBizTypeMapper.insert(orderBizType);
		return orderBizType;
	}

}
