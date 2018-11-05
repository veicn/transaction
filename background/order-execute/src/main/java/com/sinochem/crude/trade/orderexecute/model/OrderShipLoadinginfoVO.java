package com.sinochem.crude.trade.orderexecute.model;

import com.sinochem.crude.trade.orderexecute.domain.OrderShipLoadinginfo;

public class OrderShipLoadinginfoVO extends OrderShipLoadinginfo {

	private static final long serialVersionUID = 1L;
	
	private String blDateS;
	private String acStartS;
	private String acFinishS;

	public String getAcStartS() {
		return acStartS;
	}
	public void setAcStartS(String acStartS) {
		this.acStartS = acStartS;
	}
	public String getAcFinishS() {
		return acFinishS;
	}
	public void setAcFinishS(String acFinishS) {
		this.acFinishS = acFinishS;
	}
	public String getBlDateS() {
		return blDateS;
	}
	public void setBlDateS(String blDateS) {
		this.blDateS = blDateS;
	}
	
	
}