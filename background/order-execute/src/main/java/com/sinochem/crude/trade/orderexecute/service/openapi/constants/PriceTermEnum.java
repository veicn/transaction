package com.sinochem.crude.trade.orderexecute.service.openapi.constants;

public enum PriceTermEnum {
	BASIS("", "Basis"),
	FLAT_PRICE("", "Flat Price");
	
	PriceTermEnum(String code, String value){
		this.code = code;
		this.value = value;
	}
	
	private String code;
	private String value;
	
	public String getCode() {
		return code;
	}

	public String getValue() {
		return value;
	}
	
	public static PriceTermEnum getByCode(String code){
		for(PriceTermEnum priceTermEnum : PriceTermEnum.values()){
			if(priceTermEnum.getCode().equals(code)){
				return priceTermEnum;
			}
		}
		
		return null;
	}
}
