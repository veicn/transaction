package com.sinochem.crude.trade.orderexecute.model;

import com.sinochem.crude.trade.orderexecute.domain.OrderInspection;

public class OrderInspectionVO extends OrderInspection {

	private static final long serialVersionUID = 1L;	
	
	private String validPeriodStr;
	private String entrustDateStr;
	private String entrustedDateStr;
	
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
	public String getEntrustedDateStr() {
		return entrustedDateStr;
	}
	public void setEntrustedDateStr(String entrustedDateStr) {
		this.entrustedDateStr = entrustedDateStr;
	}
	
}