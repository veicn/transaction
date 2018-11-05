package org.geojson.domain;

import java.io.Serializable;

public class Flag implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5088157383310359215L;
	
	private String displayCountryCn;
	private String country;
	private String displayCountryEn;
	
	public String getDisplayCountryCn() {
		return displayCountryCn;
	}
	public void setDisplayCountryCn(String displayCountryCn) {
		this.displayCountryCn = displayCountryCn;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getDisplayCountryEn() {
		return displayCountryEn;
	}
	public void setDisplayCountryEn(String displayCountryEn) {
		this.displayCountryEn = displayCountryEn;
	}
	
	

}
