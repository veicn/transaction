package com.sinochem.crude.trade.transport.model.res;

import java.io.Serializable;
import java.math.BigDecimal;

public class VoyageStartImport implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public VoyageStartImport() {
		super();
	}

	public VoyageStartImport(String oilType, BigDecimal quantityCask,
			BigDecimal quantity, BigDecimal api, BigDecimal loadTemp,
			BigDecimal loadDraft, BigDecimal unloadDraft) {
		super();
		this.oilType = oilType;
		this.quantityCask = quantityCask;
		this.quantity = quantity;
		this.api = api;
		this.loadTemp = loadTemp;
		this.loadDraft = loadDraft;
		this.unloadDraft = unloadDraft;
	}

	/**配载计划油种*/
	private String oilType;
	
	/**配载计划装量（桶）*/
	private java.math.BigDecimal quantityCask;  
	
	/**配载计划装量（吨）*/
	private java.math.BigDecimal quantity;  

	/**配载计划API*/
	private java.math.BigDecimal api;  
	
	/**配载计划装货温度*/
	private java.math.BigDecimal loadTemp;  
	
	/**配载计划装港吃水*/
	private java.math.BigDecimal loadDraft;  
	
	/**配载计划卸港吃水*/
	private java.math.BigDecimal unloadDraft;

	public String getOilType() {
		return oilType;
	}

	public void setOilType(String oilType) {
		this.oilType = oilType;
	}

	public java.math.BigDecimal getQuantityCask() {
		return quantityCask;
	}

	public void setQuantityCask(java.math.BigDecimal quantityCask) {
		this.quantityCask = quantityCask;
	}

	public java.math.BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(java.math.BigDecimal quantity) {
		this.quantity = quantity;
	}

	public java.math.BigDecimal getApi() {
		return api;
	}

	public void setApi(java.math.BigDecimal api) {
		this.api = api;
	}

	public java.math.BigDecimal getLoadTemp() {
		return loadTemp;
	}

	public void setLoadTemp(java.math.BigDecimal loadTemp) {
		this.loadTemp = loadTemp;
	}

	public java.math.BigDecimal getLoadDraft() {
		return loadDraft;
	}

	public void setLoadDraft(java.math.BigDecimal loadDraft) {
		this.loadDraft = loadDraft;
	}

	public java.math.BigDecimal getUnloadDraft() {
		return unloadDraft;
	}

	public void setUnloadDraft(java.math.BigDecimal unloadDraft) {
		this.unloadDraft = unloadDraft;
	}  
	
	
}
