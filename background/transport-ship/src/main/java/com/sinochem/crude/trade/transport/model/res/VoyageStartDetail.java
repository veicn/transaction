package com.sinochem.crude.trade.transport.model.res;

import java.io.Serializable;

public class VoyageStartDetail implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**配载计划装量*/
	private java.math.BigDecimal quantity;  
	
	/**配载计划装量（桶）*/
	private java.math.BigDecimal quantityCask;  
	
	/**配载计划油种*/
	private String oilType;  
	/**配载计划油种Code*/
	private String oilTypeCode;  
	
	/**配载计划API*/
	private java.math.BigDecimal api;  
	
	/**配载计划装货温度*/
	private java.math.BigDecimal loadTemp;  
	
	/**配载计划装港吃水*/
	private java.math.BigDecimal loadDraft;  
	
	/**配载计划卸港吃水*/
	private java.math.BigDecimal unloadDraft;  
	
	
	public String getOilTypeCode() {
		return oilTypeCode;
	}
	public void setOilTypeCode(String oilTypeCode) {
		this.oilTypeCode = oilTypeCode;
	}
	public java.math.BigDecimal getQuantityCask() {
		return quantityCask;
	}
	public void setQuantityCask(java.math.BigDecimal quantityCask) {
		this.quantityCask = quantityCask;
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
	/**配载计划装量*/
	public void setQuantity(java.math.BigDecimal quantity){
		this.quantity=quantity;
	}
	/**配载计划装量*/
	public java.math.BigDecimal getQuantity(){
		return this.quantity;
	}
	
	/**配载计划油种*/
	public void setOilType(String oilType){
		this.oilType=oilType;
	}
	/**配载计划油种*/
	public String getOilType(){
		return this.oilType;
	}
	
	/**配载计划API*/
	public void setApi(java.math.BigDecimal api){
		this.api=api;
	}
	/**配载计划API*/
	public java.math.BigDecimal getApi(){
		return this.api;
	}
	
	/**配载计划装货温度*/
	public void setLoadTemp(java.math.BigDecimal loadTemp){
		this.loadTemp=loadTemp;
	}
	/**配载计划装货温度*/
	public java.math.BigDecimal getLoadTemp(){
		return this.loadTemp;
	}
}