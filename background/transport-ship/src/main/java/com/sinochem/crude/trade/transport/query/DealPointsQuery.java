package com.sinochem.crude.trade.transport.query;

import java.util.HashMap;
import java.util.Map;

import com.sinochem.crude.trade.common.QueryBase;
import com.sinochem.crude.trade.common.SimplePageInfo;

public class DealPointsQuery extends QueryBase{

	private static final long serialVersionUID = 1L;
	/** 地区*/
	String region;
	
	@Override
	public Map<String, String> getParameters() {
		Map<String, String> param = new HashMap<String, String>();
		param.put("region", getRegion());
		return param;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}
	public SimplePageInfo getPageInfo(){
		SimplePageInfo pageInfo = new SimplePageInfo();
		pageInfo.setPageNum(super.getCurrentPage());
		pageInfo.setPageSize(super.getPageSize());
		return pageInfo;
	}
}
