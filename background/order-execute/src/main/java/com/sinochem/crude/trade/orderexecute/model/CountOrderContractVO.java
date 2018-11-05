package com.sinochem.crude.trade.orderexecute.model;

import java.io.Serializable;

public class CountOrderContractVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6036103829294111784L;
	
	/**订单ID*/
	private Long orderId;  
	
	/**订单编号*/
	private String orderNo;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

}
