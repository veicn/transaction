package com.sinochem.crude.trade.order.enums;

public enum CreditItemEnum {
	CreditItem1(1, "LC (Documentary L/C)",""),
	CreditItem2(2, "SBLC (Standby L/C)",""),
	CreditItem3(3, "Open (Open Credit)",""),
	CreditItem4(4, "Open with PCG (Parent Company Guarantee)",""),
	CreditItem5(5, "Open with PU (Payment Undertaking)",""),
	CreditItem6(6, "Prepay","");

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
	
	private CreditItemEnum(Integer code, String zhName, String enName) {
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
