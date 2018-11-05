package com.sinochem.crude.trade.orderexecute.model;

import com.sinochem.crude.trade.orderexecute.domain.OrderFeeDissent;

public class OrderFeeDissentVO extends OrderFeeDissent {

	private static final long serialVersionUID = 1L;	
	
	private int isPass;

	private Long orderStatementId;
	
	public int getIsPass() {
		return isPass;
	}

	public void setIsPass(int isPass) {
		this.isPass = isPass;
	}

	public Long getOrderStatementId() {
		return orderStatementId;
	}

	public void setOrderStatementId(Long orderStatementId) {
		this.orderStatementId = orderStatementId;
	}
}