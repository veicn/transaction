package com.sinochem.crude.trade.info.query;

import java.util.HashMap;
import java.util.Map;

import com.sinochem.crude.trade.common.QueryBase;

public class PriceExcelQuery extends QueryBase{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String startTime;
	private String endTime;

	@Override
	public Map<String, String> getParameters() {
		Map<String,String> map = new HashMap<>();
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		return map;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	
}
