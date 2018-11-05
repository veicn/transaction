package com.sinochem.crude.trade.orderexecute.controller.openapi.vo;


/**
 * 
 * @author Down
 *
 */
public class ReceivablesVO extends InputVO {

	private static final long serialVersionUID = 1L;

	/**
	 * 收款方
	 */
	private String payee;
	
	/**
	 * 收款金额
	 */
	private String receiveAmount;
	
	/**
	 * 收款时间
	 */
	private String receiveTime;
	
	/**
	 * 订单ID
	 */
	private String uuid;

	public String getPayee() {
		return payee;
	}

	public void setPayee(String payee) {
		this.payee = payee;
	}

	public String getReceiveAmount() {
		return receiveAmount;
	}

	public void setReceiveAmount(String receiveAmount) {
		this.receiveAmount = receiveAmount;
	}

	public String getReceiveTime() {
		return receiveTime;
	}

	public void setReceiveTime(String receiveTime) {
		this.receiveTime = receiveTime;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
}