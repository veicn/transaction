package com.sinochem.crude.trade.shipping.enums;

/**
 * 计价方式
 * 
 * @author TaoZhu
 *
 */
public enum PricingMethodEnums {

	PRICING_METHOD_ONE("1", "一次付款", "LUMPSUM"),

	PRICING_METHOD_TWO("2", "按吨付费", "WS");

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

	private PricingMethodEnums(String code, String zhName, String enName) {
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
