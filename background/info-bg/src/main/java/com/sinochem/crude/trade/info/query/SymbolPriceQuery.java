package com.sinochem.crude.trade.info.query;

import java.util.HashMap;
import java.util.Map;

import com.sinochem.crude.trade.common.QueryBase;
/**
 * 指数查询
 * @author x
 */
public class SymbolPriceQuery extends QueryBase{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String symbolName; //合约名称
	private String tradeDate; //交易日期
	
	@Override
	public Map<String, String> getParameters() {
		Map<String,String> map = new HashMap<>();
		map.put("symbolName", symbolName);
		map.put("tradeDate", tradeDate);
		return map;
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
	

	

}
