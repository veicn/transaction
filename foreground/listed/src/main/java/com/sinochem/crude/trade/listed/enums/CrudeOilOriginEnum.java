package com.sinochem.crude.trade.listed.enums;

public enum CrudeOilOriginEnum {
	CrudeOilOrigin1(1, "东南亚",""),
	CrudeOilOrigin2(2, "中东",""),
	CrudeOilOrigin3(3, "中亚",""),
	CrudeOilOrigin4(4, "非洲",""),
	CrudeOilOrigin5(5, "欧洲",""),
	CrudeOilOrigin6(6, "美洲",""),
	CrudeOilOrigin7(7, "大洋洲","");

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
	
	private CrudeOilOriginEnum(Integer code, String zhName, String enName) {
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
