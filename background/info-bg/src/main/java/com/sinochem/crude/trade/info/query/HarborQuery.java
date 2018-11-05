package com.sinochem.crude.trade.info.query;

import com.sinochem.crude.trade.common.SimplePageInfo;
/**
 * 
 * @author x
 */
public class HarborQuery extends SimplePageInfo{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//品号
	private String productionNumber;

	public String getProductionNumber() {
		return productionNumber;
	}

	public void setProductionNumber(String productionNumber) {
		this.productionNumber = productionNumber;
	}
	
	

	

	

}
