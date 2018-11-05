package com.sinochem.crude.trade.orderexecute.service.openapi.vo;

import java.io.Serializable;

/**
 * 付款条款
 * @author me
 *
 */
public class PaymentTerm implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7753984284944625542L;
	/**
	 * 付款方式
	 */
	private String payment_type;
	/**
	 * 说明
	 */
	private String details;
	
	public String getPayment_type() {
		return payment_type;
	}
	public void setPayment_type(String payment_type) {
		this.payment_type = payment_type;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
}
