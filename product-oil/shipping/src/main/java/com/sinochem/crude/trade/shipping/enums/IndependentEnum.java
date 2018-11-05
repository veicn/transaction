package com.sinochem.crude.trade.shipping.enums;

/**
 * 租船代理状态
 * @author zhaoyulong
 *
 */
public enum IndependentEnum {

    Independent_ONE("1001","","SGS"),
    Independent_TWO("1002","","Inspectorate"),
    Independent_TRHEE("1003","","CCIC");


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

	private IndependentEnum(String code, String zhName, String enName) {
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
