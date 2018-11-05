package com.sinochem.crude.trade.operation.vo;

import java.util.HashMap;
import java.util.Map;

import com.sinochem.crude.trade.common.QueryBase;

public class OilToHarborQuery  extends QueryBase{

	private static final long serialVersionUID = 1L;
	private String productionNumber;//品号
	
	
	@Override
	public Map<String, String> getParameters() {
		Map<String,String> map = new HashMap<>();
		map.put("produnctionNumber", this.getProductionNumber());
		return map;
	}


	public String getProductionNumber() {
		return productionNumber;
	}


	public void setProductionNumber(String productionNumber) {
		this.productionNumber = productionNumber;
	}





	
}