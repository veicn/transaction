package com.sinochem.crude.trade.shiprefueling.enums;

/**
 * 租船代理状态
 * @author zhaoyulong
 *
 */
public enum DieselOilEnums {


    DIESELOIL_ONE("2001","柴油", "");

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

	private DieselOilEnums(String code, String zhName, String enName) {
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
