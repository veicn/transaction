package com.sinochem.crude.trade.orderexecute.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.sinochem.crude.trade.common.QueryBase;

public class SellerListPreVO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	
    private	List<Map<String, Object>> datas;

	private QueryBase query;

	public List<Map<String, Object>> getDatas() {
		return datas;
	}

	public void setDatas(List<Map<String, Object>> datas) {
		this.datas = datas;
	}

	public QueryBase getQuery() {
		return query;
	}

	public void setQuery(QueryBase query) {
		this.query = query;
	}
	
	
}
