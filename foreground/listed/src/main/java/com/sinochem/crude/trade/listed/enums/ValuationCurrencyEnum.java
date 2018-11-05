package com.sinochem.crude.trade.listed.enums;

public enum ValuationCurrencyEnum {
	ValuationCurrency1(1, "USD",""),
	//ValuationCurrency2(2, "USC",""),
	//ValuationCurrency3(3, "RMB",""),
	ValuationCurrency4(4, "EURO",""),
	//ValuationCurrency5(5, "JPY",""),
	//ValuationCurrency6(6, "CNY",""),
	ValuationCurrency7(7, "GBP",""),
	ValuationCurrency8(8, "HKD","");
	//ValuationCurrency9(9, "AUD",""),
	//ValuationCurrency10(10, "TWD",""),
	//ValuationCurrency11(11, "SGD",""),
	//ValuationCurrency12(12, "CHF",""),
	//ValuationCurrency13(13, "KZT",""),
	//ValuationCurrency14(14, "AED","");

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
	
	private ValuationCurrencyEnum(Integer code, String zhName, String enName) {
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
