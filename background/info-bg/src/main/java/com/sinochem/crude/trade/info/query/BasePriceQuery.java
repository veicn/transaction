package com.sinochem.crude.trade.info.query;

import java.util.HashMap;
import java.util.Map;

import com.sinochem.crude.trade.common.QueryBase;

public class BasePriceQuery extends QueryBase{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String baseArea;
	private String baseDate;

	@Override
	public Map<String, String> getParameters() {
		Map<String,String> map = new HashMap<String,String>();
		map.put("baseArea", baseArea);
		map.put("baseDate", baseDate);
		return map;
	}


	public String getBaseArea() {
		return baseArea;
	}


	public void setBaseArea(String baseArea) {
		this.baseArea = baseArea;
	}


	public String getBaseDate() {
		return baseDate;
	}

	public void setBaseDate(String baseDate) {
		this.baseDate = baseDate;
	}
	
	

}
