package com.sinochem.crude.trade.shipping.enums;

/**
 * 按吨计费方式
 * @author TaoZhu
 *
 */
public enum RevenueTonEnums {
	REVENUE_TON_ONE("1", "实重", "true weight"), 
	REVENUE_TON_TWO("2", "体积重", "volume weight");

	/**
	 * 代码
	 */
	private String code;

	/**
	 * 中文名称
	 */
	private String zhName;

	/**
	 * 英文名称
	 */
	private String enName;

	private RevenueTonEnums(String code, String zhName, String enName) {
		this.code = code;
		this.zhName = zhName;
		this.enName = enName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getZhName() {
		return zhName;
	}

	public void setZhName(String zhName) {
		this.zhName = zhName;
	}

	public String getEnName() {
		return enName;
	}

	public void setEnName(String enName) {
		this.enName = enName;
	}

}
