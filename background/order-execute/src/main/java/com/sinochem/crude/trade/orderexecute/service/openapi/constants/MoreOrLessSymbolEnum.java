package com.sinochem.crude.trade.orderexecute.service.openapi.constants;

/**
 * 溢短装符号
 * @author me
 *
 */
public enum MoreOrLessSymbolEnum {
	PLUS("plus", "+"),
	MINUS("minus", "-"),
	PLUS_MINUS("plusMinus", "±");
	
	private String name;
	private String symbol;
	
	MoreOrLessSymbolEnum(String name, String symbol){
		this.name = name;
		this.symbol = symbol;
	}

	public String getName() {
		return name;
	}

	public String getSymbol() {
		return symbol;
	}
}
