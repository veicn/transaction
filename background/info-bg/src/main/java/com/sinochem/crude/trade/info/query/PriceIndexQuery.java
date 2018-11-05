package com.sinochem.crude.trade.info.query;

import java.util.HashMap;
import java.util.Map;

import com.sinochem.crude.trade.common.QueryBase;
/**
 * 指数查询
 * @author x
 */
public class PriceIndexQuery extends QueryBase{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String indexName; //指数名称
	private String indexDate; //指数日期
	
	@Override
	public Map<String, String> getParameters() {
		Map<String,String> map = new HashMap<>();
		map.put("indexName", indexName);
		map.put("indexDate", indexDate);
		return map;
	}
	

	public String getIndexDate() {
		return indexDate;
	}

	public void setIndexDate(String indexDate) {
		this.indexDate = indexDate;
	}

	public String getIndexName() {
		return indexName;
	}

	public void setIndexName(String indexName) {
		this.indexName = indexName;
	}

}
