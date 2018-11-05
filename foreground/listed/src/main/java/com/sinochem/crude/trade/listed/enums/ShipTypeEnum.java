package com.sinochem.crude.trade.listed.enums;

public enum ShipTypeEnum {
	ShipType1(1, "VLCCs",""),
	ShipType2(2, "PANAMAX",""),
	ShipType3(3, "AFRAMAX",""),
	ShipType4(4, "MR",""),
	ShipType5(5, "ULCC",""),
	ShipType6(6, "SUEZMAX",""),
	ShipType7(7, "HANDYSIZE","");

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
	
	private ShipTypeEnum(Integer code, String zhName, String enName) {
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
