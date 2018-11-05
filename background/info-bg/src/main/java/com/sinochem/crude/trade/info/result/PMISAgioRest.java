package com.sinochem.crude.trade.info.result;

import java.io.Serializable;
import java.math.BigDecimal;

public class PMISAgioRest implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String crudeNameC;
	
	private String crudeNameE;
	
	/**价格*/
	private BigDecimal pricing;
	
	/**价格日期*/
	private String pricingDate;
	
	/**装货月份*/
	private String loadingMonth;
	
	private String priceMode;
	
	/**运输条款*/
	private String transportClause;

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

	public String getPricingDate() {
		return pricingDate;
	}

	public void setPricingDate(String pricingDate) {
		this.pricingDate = pricingDate;
	}

	public String getLoadingMonth() {
		return loadingMonth;
	}

	public void setLoadingMonth(String loadingMonth) {
		this.loadingMonth = loadingMonth;
	}

	public String getPriceMode() {
		return priceMode;
	}

	public void setPriceMode(String priceMode) {
		this.priceMode = priceMode;
	}

	public String getTransportClause() {
		return transportClause;
	}

	public void setTransportClause(String transportClause) {
		this.transportClause = transportClause;
	}

	
}
