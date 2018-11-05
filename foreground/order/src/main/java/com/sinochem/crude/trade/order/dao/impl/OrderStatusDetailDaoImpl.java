package com.sinochem.crude.trade.order.dao.impl;

import java.util.List;

import com.sinochem.crude.trade.order.dao.OrderStatusDetailMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sinochem.it.b2b.order.status.dao.OrderStatusDetailDao;
import com.sinochem.it.b2b.order.status.domain.OrderStatusDetail;

@Component
public class OrderStatusDetailDaoImpl implements OrderStatusDetailDao {

	@Autowired
	OrderStatusDetailMapper orderStatusDetailMapper;

	@Override
	public List<OrderStatusDetail> findByOrderId(Long orderId) {

		return orderStatusDetailMapper.findAllByOrderId(orderId);
	}

	@Override
	public List<OrderStatusDetail> findByOrderId(Long orderId, String itemCode) {

		return orderStatusDetailMapper.findAllByOrderIdAndItemCode(orderId,itemCode);
	}

	@Override
	public void insert(OrderStatusDetail orderStatusDetail) {
		orderStatusDetailMapper.insert(orderStatusDetail);

	}

}
