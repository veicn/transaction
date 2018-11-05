package com.sinochem.crude.trade.listed.enums;

public enum ProductOilKindEnum {

	ProductOilKind1(1, "新加坡汽油",""),
	ProductOilKind11(11, "菲律宾柴油",""),
	ProductOilKind12(12, "新加坡柴油","");
	

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
	
	private ProductOilKindEnum(Integer code, String zhName, String enName) {
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