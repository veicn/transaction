package com.sinochem.crude.trade.info.model;

import java.io.Serializable;

public class PIMSVo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String crudeNameC;
	
	private String crudeNameE;
	
	private String dateStart;
	
	private String dateEnd;

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

	public String getDateStart() {
		return dateStart;
	}

	public void setDateStart(String dateStart) {
		this.dateStart = dateStart;
	}

	public String getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(String dateEnd) {
		this.dateEnd = dateEnd;
	}
}
