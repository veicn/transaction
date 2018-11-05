package com.sinochem.crude.trade.info.model;

import java.io.Serializable;

public class NewSymbolPriceVO  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**合约编码，t_cms_symbol的symbol字段值*/
	private String symbol;  
	
	/**合约名称，t_cms_symbol的symbolName字段值*/
	private String symbolName;  
	
	/**交易日期*/
	private String tradeDate;
	
	/**开盘价*/
	private java.math.BigDecimal openPrice;  
	
	/**最高价*/
	private java.math.BigDecimal highPrice;  
	
	/**最低价*/
	private java.math.BigDecimal lowPrice;  
	
	/**收盘价*/
	private java.math.BigDecimal closePrice;  
	
	/**平均价*/
	private java.math.BigDecimal avgPrice;  
	
	/**持仓量*/
	private java.math.BigDecimal openInterest;  
	
	/**成交量*/
	private java.math.BigDecimal volume;  
	
	/**结算价*/
	private java.math.BigDecimal settlementPrice;

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getSymbolName() {
		return symbolName;
	}

	public void setSymbolName(String symbolName) {
		this.symbolName = symbolName;
	}

	public String getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
	}

	public java.math.BigDecimal getOpenPrice() {
		return openPrice;
	}

	public void setOpenPrice(java.math.BigDecimal openPrice) {
		this.openPrice = openPrice;
	}

	public java.math.BigDecimal getHighPrice() {
		return highPrice;
	}

	public void setHighPrice(java.math.BigDecimal highPrice) {
		this.highPrice = highPrice;
	}

	public java.math.BigDecimal getLowPrice() {
		return lowPrice;
	}

	public void setLowPrice(java.math.BigDecimal lowPrice) {
		this.lowPrice = lowPrice;
	}

	public java.math.BigDecimal getClosePrice() {
		return closePrice;
	}

	public void setClosePrice(java.math.BigDecimal closePrice) {
		this.closePrice = closePrice;
	}

	public java.math.BigDecimal getAvgPrice() {
		return avgPrice;
	}

	public void setAvgPrice(java.math.BigDecimal avgPrice) {
		this.avgPrice = avgPrice;
	}

	public java.math.BigDecimal getOpenInterest() {
		return openInterest;
	}

	public void setOpenInterest(java.math.BigDecimal openInterest) {
		this.openInterest = openInterest;
	}

	public java.math.BigDecimal getVolume() {
		return volume;
	}

	public void setVolume(java.math.BigDecimal volume) {
		this.volume = volume;
	}

	public java.math.BigDecimal getSettlementPrice() {
		return settlementPrice;
	}

	public void setSettlementPrice(java.math.BigDecimal settlementPrice) {
		this.settlementPrice = settlementPrice;
	}  
	
	
}
