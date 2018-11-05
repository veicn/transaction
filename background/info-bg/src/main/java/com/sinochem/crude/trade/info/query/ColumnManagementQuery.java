package com.sinochem.crude.trade.info.query;

import java.util.HashMap;
import java.util.Map;

import com.sinochem.crude.trade.common.QueryBase;

public class ColumnManagementQuery extends QueryBase{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String status; // 状态 00:取消申请 01:审核不通过 09待提交(草稿) 10:申请中 20:审核通过
	
	private String columnClassifyName; // 专栏分类名

	public String getColumnClassifyName() {
		return columnClassifyName;
	}

	public void setColumnClassifyName(String columnClassifyName) {
		this.columnClassifyName = columnClassifyName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public Map<String, String> getParameters() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("status", this.getStatus());
		params.put("columnClassifyName", this.getColumnClassifyName());
		return params;
	}


}
