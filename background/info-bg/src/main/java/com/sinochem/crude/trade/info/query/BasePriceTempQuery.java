package com.sinochem.crude.trade.info.query;

import java.util.HashMap;
import java.util.Map;

import com.sinochem.crude.trade.common.QueryBase;

/**
 * 基价指数模板查询
 * @author x
 *
 */
public class BasePriceTempQuery extends QueryBase{
	
	private String baseName;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Map<String, String> getParameters() {
		Map<String,String> param = new HashMap<>();
		param.put("baseName", baseName);
		return param;
	}

	public String getBaseName() {
		return baseName;
	}

	public void setBaseName(String baseName) {
		this.baseName = baseName;
	}
	
}
