package com.sinochem.crude.trade.order.enums;

public enum HLCTypeEnum {
	OldPayItemEnum1(1, "OPEN",""),
	OldPayItemEnum2(2, "HIGH",""),
	OldPayItemEnum3(3, "MID",""),
	OldPayItemEnum4(4, "LOW",""),
	OldPayItemEnum5(5, "CLOSE",""),
	OldPayItemEnum6(6, "TRIGGER","");

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
	
	private HLCTypeEnum(Integer code, String zhName, String enName) {
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
