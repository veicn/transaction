package com.sinochem.crude.trade.transport.query;

import java.util.HashMap;
import java.util.Map;

import com.sinochem.crude.trade.common.QueryBase;
import com.sinochem.crude.trade.common.SimplePageInfo;

public class OrderQuery extends QueryBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String name;
	String palletType = "1";
	

	@Override
	public Map<String, String> getParameters() {

		Map<String, String> param = new HashMap<String, String>();
		param.put("name", getName());
		param.put("palletType", getPalletType());
		return param;
	}

	public String getPalletType() {
		return palletType;
	}

	public void setPalletType(String palletType) {
		this.palletType = palletType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public SimplePageInfo getPageInfo(){
		SimplePageInfo pageInfo = new SimplePageInfo();
		pageInfo.setPageNum(super.getCurrentPage());
		pageInfo.setPageSize(super.getPageSize());
		return pageInfo;
	}
}
