package com.sinochem.crude.trade.info.model;

import java.io.Serializable;
import java.util.List;


public class CrudeAgioVo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String crudeNameE;
	
	private String pricingDate;
	
	private List<String> crudeNameArray;

	public String getCrudeNameE() {
		return crudeNameE;
	}

	public void setCrudeNameE(String crudeNameE) {
		this.crudeNameE = crudeNameE;
	}

	public String getPricingDate() {
		return pricingDate;
	}

	public void setPricingDate(String pricingDate) {
		this.pricingDate = pricingDate;
	}

	public List<String> getCrudeNameArray() {
		return crudeNameArray;
	}

	public void setCrudeNameArray(List<String> crudeNameArray) {
		this.crudeNameArray = crudeNameArray;
	}
	
	
	
}