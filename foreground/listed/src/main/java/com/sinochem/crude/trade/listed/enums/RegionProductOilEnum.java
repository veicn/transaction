package com.sinochem.crude.trade.listed.enums;

public enum RegionProductOilEnum {
	RegionProductOil1(1, "泉州",""),
	RegionProductOil2(2, "东营",""),
	RegionProductOil3(3, "北京",""),
	RegionProductOil4(4, "上海","");

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
	
	private RegionProductOilEnum(Integer code, String zhName, String enName) {
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
