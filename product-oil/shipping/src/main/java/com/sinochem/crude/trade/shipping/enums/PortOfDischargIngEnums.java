package com.sinochem.crude.trade.shipping.enums;

/**
 * 租船代理状态
 * @author zhaoyulong
 *
 */
public enum PortOfDischargIngEnums {
	
	
	PORTOF_01Dis("10001","Australia", ""),
	PORTOF_02Dis("10002","Balongan,Indonesia", "Indonesia"),
	PORTOF_03Dis("10003","Bangladesh", ""),
	PORTOF_04Dis("10004","Hong Kong", ""),
	PORTOF_05Dis("10005","Indonesia", ""),
	PORTOF_06Dis("10006","Japan", ""),
	PORTOF_07Dis("10007","Jebel Ali", ""),
	PORTOF_08Dis("10008","Korea", ""),
	PORTOF_09Dis("100019","Malaysia", ""),
	PORTOF_10Dis("100010","Merak,Indonesia", "Indonesia"),
	PORTOF_11Dis("100011","Pengerang,Malaysia", "Malaysia"),
	PORTOF_12Dis("100012","Philippines", ""),
	PORTOF_13Dis("100013","Singapore", ""),
	PORTOF_14Dis("100014","South Korea", ""),
	PORTOF_15Dis("100015","Tanjung Langsat,Malaysia", "Malaysia"),
	PORTOF_16Dis("100016","Yhailand", ""),
	PORTOF_17Dis("100017","Uae", ""),
	PORTOF_18Dis("100018","Ulsan", "Korea"),
	PORTOF_19Dis("100019","Xia Men", "");
	
    /**
     * 代码
     */
    private String code;

    /**
     * 地址
     */
    private String port;

    /**
     * 国家
     */
    private String country;	
	
	private PortOfDischargIngEnums(String code, String port, String country) {
		this.code = code;
		this.port = port;
		this.country = country;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

}
