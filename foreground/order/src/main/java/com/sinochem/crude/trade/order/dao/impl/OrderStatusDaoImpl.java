package com.sinochem.crude.trade.order.dao.impl;

import java.util.List;

import com.sinochem.crude.trade.order.dao.OrderStatusMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sinochem.it.b2b.order.status.dao.OrderStatusDao;
import com.sinochem.it.b2b.order.status.domain.OrderStatus;

@Component
public class OrderStatusDaoImpl implements OrderStatusDao {
	@Autowired
	OrderStatusMapper orderStatusMapper;

	@Override
	public void clear(Long orderId) {
		List<OrderStatus> orderStatusList = getAllByOrderId(orderId);
		for(OrderStatus orderStatus : orderStatusList){
			orderStatus.setClearFlag(true);
			update(orderStatus);
		}
	}

	@Override
	public List<OrderStatus> getAllByOrderId(Long orderId) {

		return orderStatusMapper.findAllByOrderId(orderId);
	}

	@Override
	public OrderStatus getByOrderId(Long orderId, String itemCode) {
		return orderStatusMapper.findAllByOrderIdAndItemCode(orderId,itemCode);
	}

	@Override
	public OrderStatus getLastestByOrderId(Long orderId) {

		return orderStatusMapper.getLastestByOrderId(orderId);
	}

	@Override
	public void insert(List<OrderStatus> orderStatusList) {
		for (OrderStatus orderStatus : orderStatusList){
			orderStatusMapper.insert(orderStatus);
		}
	}

	@Override
	public void update(OrderStatus orderStatus) {
		orderStatusMapper.updateByOrderIdAndItemCode(orderStatus);

	}

}
