package com.sinochem.crude.trade.shiprefueling.enums;

/**
 * 销售/采购状态常量Purchase
 * @author songhaiqiang
 */
public enum InquiryQuotationStatusEnums {


	NOT_CONNECTED("1" , "未联系" , ""),
	CONNECTED("2","已联系",""),
	DONE("3","已成交",""),
	EFFECTIVED("4","有效",""),
	INVALIDED("5","无效" ,"");

	private InquiryQuotationStatusEnums(String code, String zhName, String enName) {
		this.code = code;
		this.zhName = zhName;
		this.enName = enName;
	}

	@Override
	public String toString() {
		return this.code + "," + this.zhName;
	}

	public static InquiryQuotationStatusEnums get(String str) {
		for (InquiryQuotationStatusEnums e : values()) {
			if(e.toString().split(",")[0].equals(str)) {
				return e;
			}
		}
		return null;
	}

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
