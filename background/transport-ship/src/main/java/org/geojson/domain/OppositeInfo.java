package org.geojson.domain;

import java.io.Serializable;

public class OppositeInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 784307002165265150L;
	
	private String en;
	private String cn;
	public String getEn() {
		return en;
	}
	public void setEn(String en) {
		this.en = en;
	}
	public String getCn() {
		return cn;
	}
	public void setCn(String cn) {
		this.cn = cn;
	}
	
	
}
