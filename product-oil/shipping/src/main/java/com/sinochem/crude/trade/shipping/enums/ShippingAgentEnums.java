package com.sinochem.crude.trade.shipping.enums;

/**
 * 
 * 船代枚举
 * @author TaoZhu
 *
 */
public enum ShippingAgentEnums {
	
	SHIPPING_AGENT_ONE("101","泉州外轮代理有限公司","Quanzhou ocean shipping agency co. LTD."),
	SHIPPING_AGENT_TWO("201","泉州兴通国际船务代理有限公司","Quanzhou xingtong international shipping agency co. LTD."),
	SHIPPING_AGENT_THREE("301","泉州市捷润国际船舶代理有限公司","Quanzhou jiemun international shipping agency co. LTD."),
	SHIPPING_AGENT_FOUR("401","福建中外运船务代理有限公司","Fujian sinotrans shipping agency co. LTD."),
	SHIPPING_AGENT_FIVE("501","福建外轮代理有限公司泉州分公司","Fujian foreign shipping agency co. LTD. Quanzhou branch."),
	SHIPPING_AGENT_SIX("601","厦门中远海运船务代理有限公司","Xiamen cosco shipping agency co. LTD.");
	

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

	private ShippingAgentEnums(String code, String zhName, String enName) {
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
