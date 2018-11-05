package com.sinochem.crude.trade.shiprefueling.enums;

/**
 * @author songhaiqiang
 */
public enum PurchaseTypeEnums {



	PURCHASE_INFOTYPE_ONE("1","船油采购", ""),
	PURCHASE_INFOTYPE_NINE("9","原料油采购", "");
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

	private PurchaseTypeEnums(String code, String zhName, String enName) {
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

	public String getEnName() {
		return enName;
	}
}
