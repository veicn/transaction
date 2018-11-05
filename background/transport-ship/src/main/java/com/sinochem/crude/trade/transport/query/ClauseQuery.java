package com.sinochem.crude.trade.transport.query;

import java.util.HashMap;
import java.util.Map;

import com.sinochem.crude.trade.common.QueryBase;
import com.sinochem.crude.trade.common.SimplePageInfo;

public class ClauseQuery extends QueryBase{
	
	private static final long serialVersionUID = 1L;
	/** 报盘单编号 */
	String clauseCode;
	
	/** 船名 */
	String shipName;
	
	/** 船名 */
	String shipPlateUuid;
	
	/**状态*/
	String status;
	public String getClauseCode() {
		return clauseCode;
	}
	public void setClauseCode(String clauseCode) {
		this.clauseCode = clauseCode;
	}
	public String getShipName() {
		return shipName;
	}
	public void setShipName(String shipName) {
		this.shipName = shipName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getShipPlateUuid() {
		return shipPlateUuid;
	}
	public void setShipPlateUuid(String shipPlateUuid) {
		this.shipPlateUuid = shipPlateUuid;
	}
	/**
	 * 报盘查询条件
	 */
	@Override
	public Map<String, String> getParameters() {
		Map<String, String> param = new HashMap<String, String>();
		param.put("clauseCode", getClauseCode());
		param.put("shipName", getShipName());
		param.put("status", getStatus());
		return param;
	}
	public SimplePageInfo getPageInfo() {
		SimplePageInfo pageInfo = new SimplePageInfo();
		pageInfo.setPageNum(super.getCurrentPage());
		pageInfo.setPageSize(super.getPageSize());
		return pageInfo;
	}
}
