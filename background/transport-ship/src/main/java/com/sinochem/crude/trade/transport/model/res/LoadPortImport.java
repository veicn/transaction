package com.sinochem.crude.trade.transport.model.res;

import java.io.Serializable;
import java.util.Date;

public class LoadPortImport implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**代理协议编号*/
	private String agreementCode;  
	
	/**代理协议uuid*/
	private String agreementUuid;
	
	/**装港*/
	private String loadPort;  
	
	/**油种*/
	private String oilType;  
	
	/**ETA*/
	private java.util.Date eta;  
	
	/**预计靠泊时间*/
	private java.util.Date exBerth;  
	
	/**NOR递交时间*/
	private java.util.Date norDate;  
	
	/**引水上船时间*/
	private java.util.Date waterDate;  
	
	/**起锚时间*/
	private java.util.Date atripDate;  
	
	/**靠泊完成时间*/
	private java.util.Date berthDate;  
	
	/**实际装货开始时间*/
	private java.util.Date acStart;  
	
	/**实际装货完成时间*/
	private java.util.Date acFinish;  
	
	/**拆管时间*/
	private java.util.Date remTubeDate;  
	
	/**预计离港时间*/
	private java.util.Date exLeave;  
	
	/**实际离港时间*/
	private java.util.Date acLeave;  
	
	/**装港商检*/
	private String inspection;  
	
	/**装港船代*/
	private String agency;

	public String getAgreementCode() {
		return agreementCode;
	}

	public void setAgreementCode(String agreementCode) {
		this.agreementCode = agreementCode;
	}

	public String getLoadPort() {
		return loadPort;
	}

	public void setLoadPort(String loadPort) {
		this.loadPort = loadPort;
	}

	public String getOilType() {
		return oilType;
	}

	public void setOilType(String oilType) {
		this.oilType = oilType;
	}

	public java.util.Date getEta() {
		return eta;
	}

	public void setEta(java.util.Date eta) {
		this.eta = eta;
	}

	public java.util.Date getExBerth() {
		return exBerth;
	}

	public void setExBerth(java.util.Date exBerth) {
		this.exBerth = exBerth;
	}

	public java.util.Date getNorDate() {
		return norDate;
	}

	public void setNorDate(java.util.Date norDate) {
		this.norDate = norDate;
	}

	public java.util.Date getWaterDate() {
		return waterDate;
	}

	public void setWaterDate(java.util.Date waterDate) {
		this.waterDate = waterDate;
	}

	public java.util.Date getAtripDate() {
		return atripDate;
	}

	public void setAtripDate(java.util.Date atripDate) {
		this.atripDate = atripDate;
	}

	public java.util.Date getBerthDate() {
		return berthDate;
	}

	public void setBerthDate(java.util.Date berthDate) {
		this.berthDate = berthDate;
	}

	public java.util.Date getAcStart() {
		return acStart;
	}

	public void setAcStart(java.util.Date acStart) {
		this.acStart = acStart;
	}

	public java.util.Date getAcFinish() {
		return acFinish;
	}

	public void setAcFinish(java.util.Date acFinish) {
		this.acFinish = acFinish;
	}

	public java.util.Date getRemTubeDate() {
		return remTubeDate;
	}

	public void setRemTubeDate(java.util.Date remTubeDate) {
		this.remTubeDate = remTubeDate;
	}

	public java.util.Date getExLeave() {
		return exLeave;
	}

	public void setExLeave(java.util.Date exLeave) {
		this.exLeave = exLeave;
	}

	public java.util.Date getAcLeave() {
		return acLeave;
	}

	public void setAcLeave(java.util.Date acLeave) {
		this.acLeave = acLeave;
	}

	public String getInspection() {
		return inspection;
	}

	public void setInspection(String inspection) {
		this.inspection = inspection;
	}

	public String getAgency() {
		return agency;
	}

	public void setAgency(String agency) {
		this.agency = agency;
	}
	
	
	public String getAgreementUuid() {
		return agreementUuid;
	}

	public void setAgreementUuid(String agreementUuid) {
		this.agreementUuid = agreementUuid;
	}

	public LoadPortImport() {
		super();
	}

	public LoadPortImport( Date eta, Date exBerth,
			Date norDate, Date waterDate, Date atripDate, Date berthDate,
			Date acStart, Date acFinish, Date remTubeDate, Date exLeave,
			Date acLeave) {
		super();
		this.eta = eta;
		this.exBerth = exBerth;
		this.norDate = norDate;
		this.waterDate = waterDate;
		this.atripDate = atripDate;
		this.berthDate = berthDate;
		this.acStart = acStart;
		this.acFinish = acFinish;
		this.remTubeDate = remTubeDate;
		this.exLeave = exLeave;
		this.acLeave = acLeave;
	}
}
