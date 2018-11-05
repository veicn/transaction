package com.sinochem.crude.trade.listed.enums;

public enum DemandNumEnum {
	DemandNum1(1, "50万以下",""),
	DemandNum2(2, "50万-100万",""),
	DemandNum3(3, "100万-200万",""),
	DemandNum4(4, "200万以上","");

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
	
	private DemandNumEnum(Integer code, String zhName, String enName) {
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
