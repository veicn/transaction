package com.sinochem.crude.trade.listed.enums;

public enum GasolineProductOilKindEnum {

	GasolineProductOilKind1(1, "新加坡汽油","");
		

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
		
		private GasolineProductOilKindEnum(Integer code, String zhName, String enName) {
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
