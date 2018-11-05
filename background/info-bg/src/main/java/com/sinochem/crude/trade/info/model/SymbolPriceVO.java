package com.sinochem.crude.trade.info.model;

import java.io.Serializable;
import java.util.List;

import com.sinochem.crude.trade.info.domain.SymbolPrice;


public class SymbolPriceVO implements Serializable{

	private static final long serialVersionUID = 1L;	
	
	private List<SymbolPrice> vos;
	private String tradeDate;//价格日期
	private String symbol;//合约代码
	private String symbolName;//合约名称
	private String tradeDateStart;//价格日期开始
	private String tradeDateEnd;//价格日期结束
	
	public List<SymbolPrice> getVos() {
		return vos;
	}
	public void setVos(List<SymbolPrice> vos) {
		this.vos = vos;
	}
	public String getTradeDate() {
		return tradeDate;
	}
	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
	}
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
	public String getTradeDateStart() {
		return tradeDateStart;
	}
	public void setTradeDateStart(String tradeDateStart) {
		this.tradeDateStart = tradeDateStart;
	}
	public String getTradeDateEnd() {
		return tradeDateEnd;
	}
	public void setTradeDateEnd(String tradeDateEnd) {
		this.tradeDateEnd = tradeDateEnd;
	}
	
	
}