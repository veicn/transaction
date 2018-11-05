package com.sinochem.crude.trade.info.query;

import java.util.HashMap;
import java.util.Map;

import com.sinochem.crude.trade.common.QueryBase;

/**
 * 合约模板查询
 * @author x
 */
public class SymbolIndexQuery extends QueryBase{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String symbolName = "";
	@Override
	public Map<String, String> getParameters() {
		Map<String,String> map = new HashMap<>();
		map.put("symbolName", symbolName);
		return map;
	}
	public String getSymbolName() {
		return symbolName;
	}
	public void setSymbolName(String symbolName) {
		this.symbolName = symbolName;
	}
	
	
	

}
