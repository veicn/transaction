package com.sinochem.crude.trade.listed.enums;

public enum TradeItemEnum {

	TradeItemEnum1(1, "FOB",""),
	TradeItemEnum2(2, "CIF",""),
	TradeItemEnum3(3, "CFR",""),
	TradeItemEnum4(4, "FCA",""),
	TradeItemEnum5(5, "DES",""),
	TradeItemEnum6(6, "DDU",""),
	TradeItemEnum7(7, "ITT","");
			

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
	
	private TradeItemEnum(Integer code, String zhName, String enName) {
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
