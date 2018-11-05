package com.sinochem.crude.trade.info.result;

import java.io.Serializable;

public class CrudePriceDetailRest implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 价格 */
	private Double pricing;

	/** 价格日期 */
	private String pricingDate;
	
	public Double getPricing() {
		return pricing;
	}

	public void setPricing(Double pricing) {
		this.pricing = pricing;
	}

	public String getPricingDate() {
		return pricingDate;
	}

	public void setPricingDate(String pricingDate) {
		this.pricingDate = pricingDate;
	}

}
