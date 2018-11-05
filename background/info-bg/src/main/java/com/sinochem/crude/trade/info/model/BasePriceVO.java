package com.sinochem.crude.trade.info.model;

import com.sinochem.crude.trade.info.domain.BasePrice;

public class BasePriceVO extends BasePrice {

	private static final long serialVersionUID = 1L;
	
	private String basePriceTempUuuid;

	public String getBasePriceTempUuuid() {
		return basePriceTempUuuid;
	}

	public void setBasePriceTempUuuid(String basePriceTempUuuid) {
		this.basePriceTempUuuid = basePriceTempUuuid;
	}
	
	
	
}