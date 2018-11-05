package com.sinochem.crude.trade.transport.model.res;

import java.io.Serializable;

public class PalletPortList implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**单位*/
	private String unitName;  
	
	/**卸港*/
	private String loadPort;  
	
	/**装港*/
	private String unloadPort;  
	
	/**装货区域*/
	private String loadRegion;  
	
	/**卸货区域*/
	private String unloadRegion;  
	
	/**数量（桶）*/
	private java.math.BigDecimal quantity;
	
	/**油种*/
	private String oilType;
	
	/**装港英文*/
	private String loadPortEn;  
	
	/**装港code*/
	private String loadPortCode;  
	
	/**卸港英文*/
	private String unloadPortEn;  
	
	/**卸港code*/
	private String unloadPortCode;  
	
	/**油种英文*/
	private String oilTypeEn;  
	
	/**油种名称code*/
	private String oilTypeCode; 
	

	public String getUnloadPort() {
		return unloadPort;
	}

	public void setUnloadPort(String unloadPort) {
		this.unloadPort = unloadPort;
	}

	public String getUnloadRegion() {
		return unloadRegion;
	}

	public void setUnloadRegion(String unloadRegion) {
		this.unloadRegion = unloadRegion;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getLoadPort() {
		return loadPort;
	}

	public void setLoadPort(String loadPort) {
		this.loadPort = loadPort;
	}


	public String getLoadRegion() {
		return loadRegion;
	}

	public void setLoadRegion(String loadRegion) {
		this.loadRegion = loadRegion;
	}

//	public String getUnloadRegion() {
//		return unloadRegion;
//	}
//
//	public void setUnloadRegion(String unloadRegion) {
//		this.unloadRegion = unloadRegion;
//	}

	public java.math.BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(java.math.BigDecimal quantity) {
		this.quantity = quantity;
	}

	public String getOilType() {
		return oilType;
	}

	public void setOilType(String oilType) {
		this.oilType = oilType;
	}

	public String getOilTypeCode() {
		return oilTypeCode;
	}

	public void setOilTypeCode(String oilTypeCode) {
		this.oilTypeCode = oilTypeCode;
	}

	public String getLoadPortEn() {
		return loadPortEn;
	}

	public void setLoadPortEn(String loadPortEn) {
		this.loadPortEn = loadPortEn;
	}

	public String getLoadPortCode() {
		return loadPortCode;
	}

	public void setLoadPortCode(String loadPortCode) {
		this.loadPortCode = loadPortCode;
	}

	public String getUnloadPortEn() {
		return unloadPortEn;
	}

	public void setUnloadPortEn(String unloadPortEn) {
		this.unloadPortEn = unloadPortEn;
	}

	public String getUnloadPortCode() {
		return unloadPortCode;
	}

	public void setUnloadPortCode(String unloadPortCode) {
		this.unloadPortCode = unloadPortCode;
	}

	public String getOilTypeEn() {
		return oilTypeEn;
	}

	public void setOilTypeEn(String oilTypeEn) {
		this.oilTypeEn = oilTypeEn;
	}  
	
}
