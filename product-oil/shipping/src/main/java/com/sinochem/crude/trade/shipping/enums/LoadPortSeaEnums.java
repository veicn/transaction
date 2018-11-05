package com.sinochem.crude.trade.shipping.enums;

/**
 * 海况
 * @author zhaoyulong
 *
 */
public enum LoadPortSeaEnums {

	LEVEL0("0", "LEVEL0", "CALM-GLASSY　　 0 FT (0 METERS)"),
	LEVEL1("1", "LEVEL1", "CALM-RIPPLED　 0-1/3 FT (0-.1METERS)"),
	LEVEL2("2", "LEVEL2", "SMOOTH-WAVELET　 1/3-1 2/3 FT (.1-.5 METERS)"),
	LEVEL3("3", "LEVEL3", "SLIGHT　 1 2/3 - 4 FT(.5-1.25 METERS)"),
	LEVEL4("4", "LEVEL4", "MODERATE　 4-8 FT(1.25-2.50 METERS)"),
	LEVEL5("5", "LEVEL5", " ROUGH　 8-13 FT(2.50-4.0 METERS)"),
	LEVEL6("6", "LEVEL6", "VERY ROUGH　 13-20 FT(4-6 METERS)"),
	LEVEL7("7", "LEVEL7", "HIGH　　　 20-30 FT(6-9 METERS)"),
	LEVEL8("8", "LEVEL8", "VERY HIGH　 30-45 FT(9-14 METERS)"),
	LEVEL9("9", "LEVEL9", "PHENOMENAL　 >45 FT (>14 METERS)");

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
	
	private LoadPortSeaEnums(String code, String zhName, String enName) {
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
