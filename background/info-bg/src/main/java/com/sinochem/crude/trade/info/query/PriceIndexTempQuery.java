package com.sinochem.crude.trade.info.query;

import java.util.HashMap;
import java.util.Map;

import com.sinochem.crude.trade.common.QueryBase;

/**
 * 指数模板查询
 * @author x
 */
public class PriceIndexTempQuery extends QueryBase{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String indexName = "";
	@Override
	public Map<String, String> getParameters() {
		Map<String,String> map = new HashMap<>();
		map.put("indexName", indexName);
		return map;
	}
	public String getIndexName() {
		return indexName;
	}
	public void setIndexName(String indexName) {
		this.indexName = indexName;
	}
	
	
	

}
