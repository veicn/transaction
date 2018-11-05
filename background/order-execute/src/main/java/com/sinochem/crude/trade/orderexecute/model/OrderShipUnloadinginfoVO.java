package com.sinochem.crude.trade.orderexecute.model;

import com.sinochem.crude.trade.orderexecute.domain.OrderShipUnloadinginfo;

public class OrderShipUnloadinginfoVO extends OrderShipUnloadinginfo {

	private static final long serialVersionUID = 1L;
	
	private String  norDateDesc;
	private String codDesc;
	private String acStartDesc;
	private String acFinishDesc;
	
	public String getNorDateDesc() {
		return norDateDesc;
	}
	public void setNorDateDesc(String norDateDesc) {
		this.norDateDesc = norDateDesc;
	}
	public String getCodDesc() {
		return codDesc;
	}
	public void setCodDesc(String codDesc) {
		this.codDesc = codDesc;
	}
	public String getAcStartDesc() {
		return acStartDesc;
	}
	public void setAcStartDesc(String acStartDesc) {
		this.acStartDesc = acStartDesc;
	}
	public String getAcFinishDesc() {
		return acFinishDesc;
	}
	public void setAcFinishDesc(String acFinishDesc) {
		this.acFinishDesc = acFinishDesc;
	}
	
	
	
}