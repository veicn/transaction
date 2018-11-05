package com.sinochem.crude.trade.orderexecute.model;

import com.sinochem.crude.trade.orderexecute.domain.OrderStatement;

public class OrderStatementVO extends OrderStatement {

	private static final long serialVersionUID = 1L;	
	
	/**附件名称*/
	private String fileName;  
	
	/**附件地址*/
	private String fileUrl;  
	
	private String isEdit ;
	
	private Long orderFeeId;
	
	private String isAgain;
	
	private String billDateDesc;
	
	private String paymentDateDesc;
	
	private String checkTimeDesc;
	
	private String uuid;

	private Long orderStatementId;
	
	
	


	public Long getOrderStatementId() {
		return orderStatementId;
	}

	public void setOrderStatementId(Long orderStatementId) {
		this.orderStatementId = orderStatementId;
	}

	public String getIsEdit() {
		return isEdit;
	}

	public void setIsEdit(String isEdit) {
		this.isEdit = isEdit;
	}

	public Long getOrderFeeId() {
		return orderFeeId;
	}

	public void setOrderFeeId(Long orderFeeId) {
		this.orderFeeId = orderFeeId;
	}

	public String getIsAgain() {
		return isAgain;
	}

	public void setIsAgain(String isAgain) {
		this.isAgain = isAgain;
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

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	
}