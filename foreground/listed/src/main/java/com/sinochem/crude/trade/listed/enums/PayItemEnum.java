package com.sinochem.crude.trade.listed.enums;

public enum PayItemEnum {

	PayItemEnum1(1, "B/L",""),
	PayItemEnum2(2, "NOR",""),
	PayItemEnum3(3, "COD",""),
	PayItemEnum4(4, "ITT",""),
	PayItemEnum5(5, "LDR",""),
	PayItemEnum6(6, "DATE","");
	
	/**
	 * 编号
	 */
	private Integer code;
	
	/**
	 * 中文
	 */
	private String zhName;
	
	/**
	 * 英文
	 */
	private String enName;	
	
	private PayItemEnum(Integer code, String zhName, String enName) {
		this.code = code;
	    this.zhName = zhName;
	    this.enName = enName;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	
	public String getZhName() {
	    return zhName;
	}
	
	public String getEnName() {
	    return enName;
	}
}
