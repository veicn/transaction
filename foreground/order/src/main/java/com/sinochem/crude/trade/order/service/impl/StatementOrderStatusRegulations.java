package com.sinochem.crude.trade.order.service.impl;

import com.eyeieye.melody.util.StringUtil;
import com.sinochem.crude.trade.order.dao.ContractMapper;
import com.sinochem.crude.trade.order.domain.Contract;
import com.sinochem.crude.trade.order.service.OrderMessageService;
import com.sinochem.it.b2b.order.status.domain.OrderStatus;
import com.sinochem.it.b2b.order.status.service.OrderStatusRegulations;
import com.sinochem.it.b2b.order.status.service.OrderStatusRegulationsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 结算状态机接口
 */
@Component
public class StatementOrderStatusRegulations implements OrderStatusRegulations {

	@Autowired
	private ContractMapper contractMapper;
	@Autowired
	OrderMessageService orderMessageService;

	/**
	 * 该状态是否可以执行,实现后，默认是可以
	 * 
	 */
//	@Override
	public boolean can(List<OrderStatus> sourceOrderStatus,
			OrderStatus targetOrderStatus)
			throws OrderStatusRegulationsException {
		return item().equals(targetOrderStatus.getItemCode());
	}

	/**
	 * 执行通知
	 */
	@Override
	public void before(OrderStatus orderStatus) {

	}

	/**
	 * 执行完回调
	 */
	@Override
	public void after(OrderStatus orderStatus) {
		//Contract.orderStatus = 'XXX${orderStatus.value}'
		Contract contract = contractMapper.selectByPrimaryKey(orderStatus.getOrderId());
		if (contract == null) {
			return;
		}
		String contractOrderStatus = contract.getOrderStatus();
		if(StringUtil.isEmpty(contractOrderStatus)){
			contractOrderStatus = "0000";
		}
		contractOrderStatus = contractOrderStatus.substring(0,3) + orderStatus.getValue();
		contractMapper.updateOrderStatusById(contractOrderStatus, contract.getId());
		orderMessageService.sendOrderChangeMessage(orderStatus);

	}

	/**
	 * 执行完回调下一个状态
	 */
	@Override
	public void next(OrderStatus orderStatus) {
	}

	/**
	 * 规则名称
	 * 
	 */
	@Override
	public String bizType() {
		return Contract.ORDER_STATUS_CONFIG;
	}

	/**
	 * 对应的状态项，可以为空，如果为空 就是全局
	 * 
	 * @return
	 */
	public String item() {
		return "statement";
	}

	@Override
	public int sort() {
		return 0;
	}
}
