package com.sinochem.crude.trade.listed.enums;

public enum PricingModeEnum {

	PricingModeEnum1(1, "Average",""),
	PricingModeEnum2(2, "Complex",""),
	PricingModeEnum3(3, "Event",""),
	PricingModeEnum4(4, "Fixed",""),
	PricingModeEnum5(5, "Trigger","");

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
		
		private PricingModeEnum(Integer code, String zhName, String enName) {
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
