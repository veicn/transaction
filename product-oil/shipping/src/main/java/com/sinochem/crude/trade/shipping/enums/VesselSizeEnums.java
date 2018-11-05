package com.sinochem.crude.trade.shipping.enums;
/**
 *	VesselSize
 */
public enum VesselSizeEnums {
	ULCC("1", "ULCC", "ULCC"),
	VLCC("2", "VLCC", "VLCC"),
	SUEZMAX("3", "SUEZMAX", "SUEZMAX"),
	AFRAMEAX("4", "AFRAMEAX", "AFRAMEAX"),
	PANAMAX("5", "PANAMAX", "PANAMAX"),
	HANDYSIZE("6", "HANDYSIZE", "HANDYSIZE");
	
	
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

	private VesselSizeEnums(String code, String zhName, String enName) {
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
