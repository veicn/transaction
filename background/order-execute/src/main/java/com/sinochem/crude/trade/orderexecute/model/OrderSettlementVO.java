package com.sinochem.crude.trade.orderexecute.model;

import com.sinochem.crude.trade.orderexecute.domain.OrderSettlement;

public class OrderSettlementVO extends OrderSettlement {

	private static final long serialVersionUID = 1L;
	
	private String billDateDesc;
	
	private String paymentDateDesc;
	
	private String checkTimeDesc;
	
	private String uuid;
	
	private String orderStatementUuid;
	
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getOrderStatementUuid() {
		return orderStatementUuid;
	}

	public void setOrderStatementUuid(String orderStatementUuid) {
		this.orderStatementUuid = orderStatementUuid;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getBillDateDesc() {
		return billDateDesc;
	}

	public void setBillDateDesc(String billDateDesc) {
		this.billDateDesc = billDateDesc;
	}

	public String getPaymentDateDesc() {
		return paymentDateDesc;
	}

	public void setPaymentDateDesc(String paymentDateDesc) {
		this.paymentDateDesc = paymentDateDesc;
	}

	public String getCheckTimeDesc() {
		return checkTimeDesc;
	}

	public void setCheckTimeDesc(String checkTimeDesc) {
		this.checkTimeDesc = checkTimeDesc;
	}

	public Long getOrderFeeId() {
		return orderFeeId;
	}

	public void setOrderFeeId(Long orderFeeId) {
		this.orderFeeId = orderFeeId;
	}

	private Long orderFeeId;
	
	
}