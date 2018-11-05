package com.sinochem.crude.trade.shiprefueling.enums;

/**
 * 租船代理状态
 * @author zhaoyulong
 *
 */
public enum EngineOilEnums {


    ENGINEOIL_TWO("3001","机油", "");

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

	private EngineOilEnums(String code, String zhName, String enName) {
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
