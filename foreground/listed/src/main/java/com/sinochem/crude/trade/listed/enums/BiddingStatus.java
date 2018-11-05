package com.sinochem.crude.trade.listed.enums;

/**
 * 报价单状态
 */
public enum BiddingStatus {

	DEMAND_STATUS_10(10,"已报价"),
	DEMAND_STATUS_20(20,"已中标"),
	DEMAND_STATUS_30(30,"已结束");

	/**
	 * 状态编号
	 */
	Integer code;

	/**
	 * 状态名称
	 */
	String name;

	private BiddingStatus(Integer code, String name) {
		this.code = code;
		this.name = name;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
