package com.sinochem.crude.trade.listed.enums;

public enum SerialNumberBizType {

	CrudeOilDemand("01","原油需求订单"),
	CrudeOilQuote("02","原油报价"),
	CrudeOilOrder("03","原油订单"),
	ProductOilDemand("04","成品油需求订单"),
	ProductOilQuote("05","成品油报价"),
	ProductOilOrder("06","成品油订单"),
	TradingChain("07","贸易链");
	
	/**
	 * 业务类型编号
	 */
	String bizType;
	
	/**
	 * 业务类型名称
	 */
	String bizName;

	private SerialNumberBizType(String bizType, String bizName) {
		this.bizType = bizType;
		this.bizName = bizName;
	}

	public String getBizType() {
		return bizType;
	}

	public void setBizType(String bizType) {
		this.bizType = bizType;
	}

	public String getBizName() {
		return bizName;
	}

	public void setBizName(String bizName) {
		this.bizName = bizName;
	}
	
	
	
	
}
