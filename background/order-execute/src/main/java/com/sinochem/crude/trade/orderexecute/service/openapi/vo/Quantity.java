package com.sinochem.crude.trade.orderexecute.service.openapi.vo;

import java.math.BigDecimal;

import com.sinochem.crude.trade.orderexecute.service.openapi.constants.MoreOrLessSymbolEnum;

/**
 * 数量公式
 * 
 */
public class Quantity {
	/**
	 * 数量
	 */
	private BigDecimal quantity;
	/**
	 * 数量单位
	 */
	private String quantityUnit;
	/**
	 * 溢短装符号
	 */
	private MoreOrLessSymbolEnum moreOrLessSymbol;
	/**
	 * 溢短装数值
	 */
	private BigDecimal moreOrLess;
	
	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public String getQuantityUnit() {
		return quantityUnit;
	}

	public void setQuantityUnit(String quantityUnit) {
		this.quantityUnit = quantityUnit;
	}

	public MoreOrLessSymbolEnum getMoreOrLessSymbol() {
		return moreOrLessSymbol;
	}

	public void setMoreOrLessSymbol(MoreOrLessSymbolEnum moreOrLessSymbol) {
		this.moreOrLessSymbol = moreOrLessSymbol;
	}

	public BigDecimal getMoreOrLess() {
		return moreOrLess;
	}

	public void setMoreOrLess(BigDecimal moreOrLess) {
		this.moreOrLess = moreOrLess;
	}

	public String[] toQuantityArray(){
		
		return new String[]{
				quantity.toString(), 
				quantityUnit, 
				moreOrLessSymbol.getSymbol(), 
				moreOrLess==null?"":moreOrLess.toString(), 
				"%", 
				"", 
				""
		};
	}
}
