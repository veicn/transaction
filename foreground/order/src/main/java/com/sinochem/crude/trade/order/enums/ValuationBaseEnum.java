package com.sinochem.crude.trade.order.enums;

public enum ValuationBaseEnum {

	ValuationBaseEnum1(1, "PLATTS DTD BRT",""),
	ValuationBaseEnum2(2, "PLATTS DUBAI FRONTLINE",""),
	ValuationBaseEnum3(3, "PLATTS OMAN FRONTLINE",""),
	ValuationBaseEnum4(4, "OSP",""),
	ValuationBaseEnum5(5, "FIXED PRICE",""),
	ValuationBaseEnum6(6, "ICE BRT FRONTLINE",""),
	ValuationBaseEnum7(7, "NYMEX WTI FRONTLINE",""),
	ValuationBaseEnum8(8, "ICE BRT CONTRACT TO BE TRIGGER","");

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
		
		private ValuationBaseEnum(Integer code, String zhName, String enName) {
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
