package com.sinochem.crude.trade.listed.enums;

public enum ProductOilClassifyEnum {
	ProductOilClassify1(1, "汽油",""),
	ProductOilClassify2(2, "柴油",""),
	ProductOilClassify3(3, "煤油","");

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
	
	private ProductOilClassifyEnum(Integer code, String zhName, String enName) {
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
