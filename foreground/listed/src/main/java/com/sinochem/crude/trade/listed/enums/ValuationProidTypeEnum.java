package com.sinochem.crude.trade.listed.enums;

public enum ValuationProidTypeEnum {
	ValuationProidType1(1, "5 days after B/L，B/L=0",""),
	ValuationProidType2(2, "Cal B/L Month",""),
	ValuationProidType3(3, "Cal Nomination Month",""),
	ValuationProidType4(4, "Period",""),
	ValuationProidType5(5, "As per Futures Contract","");

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
	
	private ValuationProidTypeEnum(Integer code, String zhName, String enName) {
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
