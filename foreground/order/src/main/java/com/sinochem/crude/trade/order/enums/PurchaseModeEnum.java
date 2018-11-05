package com.sinochem.crude.trade.order.enums;

public enum PurchaseModeEnum {
	//PurchaseMode1(1, "长协采购",""),
	PurchaseMode2(2, "现货采购",""),
	PurchaseMode3(3, "临时补货","");

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
	
	private PurchaseModeEnum(Integer code, String zhName, String enName) {
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
