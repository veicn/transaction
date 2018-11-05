package com.sinochem.crude.trade.order.enums;

public enum ProductOilValuationBaseEnum {

	ProductOilValuationBase1(1, "Mops Gasoil",""),
	ProductOilValuationBase2(2, "Mops Gasoline 92ron","");

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
		
		private ProductOilValuationBaseEnum(Integer code, String zhName, String enName) {
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
