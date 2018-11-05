package com.sinochem.crude.trade.listed.enums;

/**
 * 需求单状态
 */
public enum DemandStatus {
	DEMAND_STATUS_0(0,"已删除"),
	DEMAND_STATUS_1(1,"已保存"),
	DEMAND_STATUS_2(2,"已发布"),
	DEMAND_STATUS_3(3,"已结束")
	;

	/**
	 * 状态编号
	 */
	Integer code;

	/**
	 * 状态名称
	 */
	String name;

	private DemandStatus(Integer code, String name) {
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
