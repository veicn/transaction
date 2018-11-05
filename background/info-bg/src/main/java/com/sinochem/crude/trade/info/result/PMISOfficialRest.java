package com.sinochem.crude.trade.info.result;

import java.io.Serializable;
import java.math.BigDecimal;

public class PMISOfficialRest implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String crudeNameC;
	
	private String crudeNameE;
	
	/**价格*/
	private BigDecimal pricing;
	
	private String pricingMonth;
	
	private String priceMode;

	public String getCrudeNameC() {
		return crudeNameC;
	}

	public void setCrudeNameC(String crudeNameC) {
		this.crudeNameC = crudeNameC;
	}

	public String getCrudeNameE() {
		return crudeNameE;
	}

	public void setCrudeNameE(String crudeNameE) {
		this.crudeNameE = crudeNameE;
	}

	public BigDecimal getPricing() {
		return pricing;
	}

	public void setPricing(BigDecimal pricing) {
		this.pricing = pricing;
	}

	public String getPricingMonth() {
		return pricingMonth;
	}

	public void setPricingMonth(String pricingMonth) {
		this.pricingMonth = pricingMonth;
	}

	public String getPriceMode() {
		return priceMode;
	}

	public void setPriceMode(String priceMode) {
		this.priceMode = priceMode;
	}
	
	
}
