package com.sinochem.crude.trade.orderexecute.query;

import java.util.HashMap;
import java.util.Map;

import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.orderexecute.commons.QueryBaseExt;

public class InspectionStaffQuery extends QueryBaseExt{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2121245178342234234L;
	/**
	 * 联系人
	 */
	private String contactName;
	/**
	 * 港口
	 */
	private String port;

	public Map<String, Object> getQueryParameter() {
		Map<String, Object> params = new HashMap<>();
		params.put("contactName", this.contactName);
		params.put("port", this.port);
		return params;
	}
	public SimplePageInfo getPageInfo(){
		SimplePageInfo pageInfo = new SimplePageInfo();
		pageInfo.setPageNum(super.getCurrentPage());
		pageInfo.setPageSize(super.getPageSize());
		return pageInfo;
	}
	
	@Override
	public Map<String, String> getParameters() {
		Map<String, String> params = new HashMap<>();
		params.put("contactName", this.contactName);
		params.put("port", this.port);
		return params;
	}
	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

}
