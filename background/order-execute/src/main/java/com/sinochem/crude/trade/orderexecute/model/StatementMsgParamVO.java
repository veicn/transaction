package com.sinochem.crude.trade.orderexecute.model;

import java.util.HashMap;
import java.util.Map;

public class StatementMsgParamVO {
	private Long orderId;
	private Long receiverId;
	private Long customerId;
	
	private String epMemberName;
	private String statementNo;
	private String statementDetailLink;
	private String loginLink;
	
	
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public Long getReceiverId() {
		return receiverId;
	}
	public void setReceiverId(Long receiverId) {
		this.receiverId = receiverId;
	}
	public String getEpMemberName() {
		return epMemberName;
	}
	public void setEpMemberName(String epMemberName) {
		this.epMemberName = epMemberName;
	}
	public String getStatementNo() {
		return statementNo;
	}
	public void setStatementNo(String statementNo) {
		this.statementNo = statementNo;
	}
	public String getStatementDetailLink() {
		return statementDetailLink;
	}
	public void setStatementDetailLink(String statementDetailLink) {
		this.statementDetailLink = statementDetailLink;
	}
	public String getLoginLink() {
		return loginLink;
	}
	public void setLoginLink(String loginLink) {
		this.loginLink = loginLink;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	public Map<String, Object> toMap(){
		Map<String, Object> map = new HashMap<>();
		map.put("orderId", this.orderId);
		map.put("receiverId", this.receiverId);
		map.put("customerId", this.customerId);
		map.put("epMemberName", this.epMemberName);
		map.put("statementNo", this.statementNo);
		map.put("statementDetailLink", this.statementDetailLink);
		map.put("loginLink", this.loginLink);
		
		return map;
	}

}
