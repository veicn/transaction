package com.sinochem.crude.trade.shipping.enums;

/**
 * 异常信息枚举类
 */
public enum ProductStatusEnums {

	PRODUCT_ONE("1", "商品1", "ONE"),

	PRODUCT_TWO("2", "商品2", "TWO"),

	PRODUCT_THREE("3", "商品3", "THREE");

	/**
     * 代码
     */
    private String code;

    /**
     * 中文名称
     */
    private String zhName;

    /**
     * 英文名称
     */
    private String enName;	
	
	private ProductStatusEnums(String code, String zhName, String enName) {
		this.code = code;
        this.zhName = zhName;
        this.enName = enName;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
    public String getZhName() {
        return zhName;
    }

    public String getEnName() {
        return enName;
    }
	
}
