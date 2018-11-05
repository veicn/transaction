package com.sinochem.crude.trade.orderexecute.model;

import com.sinochem.crude.trade.orderexecute.domain.OrderCustoms;

public class OrderCustomsVO extends OrderCustoms {

	private static final long serialVersionUID = 1L;	
	
	private String validPeriodStr;
	private String entrustDateStr;
	private String importDateStr;
	private String stampDateStr;
	
	public String getValidPeriodStr() {
		return validPeriodStr;
	}
	public void setValidPeriodStr(String validPeriodStr) {
		this.validPeriodStr = validPeriodStr;
	}
	public String getEntrustDateStr() {
		return entrustDateStr;
	}
	public void setEntrustDateStr(String entrustDateStr) {
		this.entrustDateStr = entrustDateStr;
	}
	public String getImportDateStr() {
		return importDateStr;
	}
	public void setImportDateStr(String importDateStr) {
		this.importDateStr = importDateStr;
	}
	public String getStampDateStr() {
		return stampDateStr;
	}
	public void setStampDateStr(String stampDateStr) {
		this.stampDateStr = stampDateStr;
	}
}