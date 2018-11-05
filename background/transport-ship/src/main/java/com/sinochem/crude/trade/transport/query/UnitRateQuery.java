package com.sinochem.crude.trade.transport.query;

import java.util.HashMap;
import java.util.Map;

import com.sinochem.crude.trade.common.QueryBase;
import com.sinochem.crude.trade.common.SimplePageInfo;

public class UnitRateQuery extends QueryBase{
	
	private static final long serialVersionUID = 1L;
	String oneName;
	String twoName;
	

	@Override
	public Map<String, String> getParameters() {
		Map<String, String> param = new HashMap<String, String>();
		param.put("oneName", getOneName());
		param.put("twoName", getTwoName());
		return param;
	}


	public String getOneName() {
		return oneName;
	}


	public void setOneName(String oneName) {
		this.oneName = oneName;
	}


	public String getTwoName() {
		return twoName;
	}


	public void setTwoName(String twoName) {
		this.twoName = twoName;
	}

	public SimplePageInfo getPageInfo(){
		SimplePageInfo pageInfo = new SimplePageInfo();
		pageInfo.setPageNum(super.getCurrentPage());
		pageInfo.setPageSize(super.getPageSize());
		return pageInfo;
	}
	
}
