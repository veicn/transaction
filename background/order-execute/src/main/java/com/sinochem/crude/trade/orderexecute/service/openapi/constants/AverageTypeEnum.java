package com.sinochem.crude.trade.orderexecute.service.openapi.constants;

public enum AverageTypeEnum {
	WEIGHTED_AVERAGE("Weighted Average"),
	SIMPLE_AVERAGE("Simple Average");
	
	AverageTypeEnum(String value){
		this.value = value;
	}
	
	private String value;
	
	public String getValue() {
		return value;
	}

}
