package com.sinochem.crude.trade.info.query;

import java.util.HashMap;
import java.util.Map;

import com.sinochem.crude.trade.common.QueryBase;

public class NoticeQuery extends QueryBase{

	private static final long serialVersionUID = 1L;
	
	private String validEnd;
	private String validBegin;
	private String status;
	private String uuid;
	@Override
	public Map<String, String> getParameters() {
		Map<String,String> map = new HashMap<String,String>();
		map.put("validEnd", getValidEnd());
		map.put("validBegin",getValidBegin());
		map.put("status", getStatus());
		return map;
	}



	public String getValidEnd() {
		return validEnd;
	}



	public void setValidEnd(String validEnd) {
		this.validEnd = validEnd;
	}



	public String getValidBegin() {
		return validBegin;
	}



	public void setValidBegin(String validBegin) {
		this.validBegin = validBegin;
	}



	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}



	public String getUuid() {
		return uuid;
	}



	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	
}
