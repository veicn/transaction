package com.sinochem.crude.trade.shipping.enums;

/**
 * 租船代理状态
 * @author zhaoyulong
 *
 */
public enum AgreementEnums {
	
	
	AGREEMENT_ONE("10001","DAY HARVEST", "DAY HARVEST");
//	AGREEMENT_TWO("20","租船协议待确认", "to be confirmed"),
	
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
	
	private AgreementEnums(String code, String zhName, String enName) {
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
