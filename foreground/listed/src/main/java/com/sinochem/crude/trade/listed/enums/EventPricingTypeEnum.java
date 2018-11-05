package com.sinochem.crude.trade.listed.enums;

public enum EventPricingTypeEnum {
	OldPayItemEnum1(1, "B/L",""),
	OldPayItemEnum2(2, "NOR",""),
	OldPayItemEnum3(3, "COD",""),
	OldPayItemEnum4(4, "ITT",""),
	OldPayItemEnum5(5, "LDR",""),
	OldPayItemEnum6(6, "SPD",""),
	OldPayItemEnum7(7, "CPD","");

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
	
	private EventPricingTypeEnum(Integer code, String zhName, String enName) {
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
