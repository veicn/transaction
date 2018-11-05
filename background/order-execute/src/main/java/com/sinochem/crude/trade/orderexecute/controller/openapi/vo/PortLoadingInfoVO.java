package com.sinochem.crude.trade.orderexecute.controller.openapi.vo;


/**
 * 
 * @author Down
 *
 */
public class PortLoadingInfoVO extends InputVO {
	private static final long serialVersionUID = 1L;

	/**
	 * 实际装货完成时间
	 */
	private String acFinish;
	
	/**
	 * 实际装货开始时间
	 */
	private String acStart;
	
	/**
	 * 装港船代
	 */
	private String agent;
	
	/**
	 * 提单号
	 */
	private String blCode;
	
	/**
	 * 提单日
	 */
	private String blDate;
	
	/**
	 * 提单毛桶
	 */
	private String blHairBarrel;
	
	/**
	 * 提单毛吨
	 */
	private String blHairTonnage;
	
	/**
	 * 提单净吨
	 */
	private String blNetTonnage;
	
	/**
	 * 提单净桶
	 */
	private String blNightstool;

	/**
	 * ETA
	 */
	private String etaDesc;
	
	/**
	 * 装船港
	 */
	private String loadPort;
	
	/**
	 * NOR递交时间
	 */
	private String norDate;
	
	/**
	 * 装船量 （净桶）
	 */
	private String shNetBarrel;
	
	/**
	 * 装船量 （净吨）
	 */
	private String shNetTonnage;
	
	/**
	 * 订单ID
	 */
	private String uuid;

	public String getAcFinish() {
		return acFinish;
	}

	public void setAcFinish(String acFinish) {
		this.acFinish = acFinish;
	}

	public String getAcStart() {
		return acStart;
	}

	public void setAcStart(String acStart) {
		this.acStart = acStart;
	}

	public String getAgent() {
		return agent;
	}

	public void setAgent(String agent) {
		this.agent = agent;
	}

	public String getBlCode() {
		return blCode;
	}

	public void setBlCode(String blCode) {
		this.blCode = blCode;
	}

	public String getBlDate() {
		return blDate;
	}

	public void setBlDate(String blDate) {
		this.blDate = blDate;
	}

	public String getBlHairBarrel() {
		return blHairBarrel;
	}

	public void setBlHairBarrel(String blHairBarrel) {
		this.blHairBarrel = blHairBarrel;
	}

	public String getBlHairTonnage() {
		return blHairTonnage;
	}

	public void setBlHairTonnage(String blHairTonnage) {
		this.blHairTonnage = blHairTonnage;
	}

	public String getBlNetTonnage() {
		return blNetTonnage;
	}

	public void setBlNetTonnage(String blNetTonnage) {
		this.blNetTonnage = blNetTonnage;
	}

	public String getBlNightstool() {
		return blNightstool;
	}

	public void setBlNightstool(String blNightstool) {
		this.blNightstool = blNightstool;
	}

	public String getEtaDesc() {
		return etaDesc;
	}

	public void setEtaDesc(String etaDesc) {
		this.etaDesc = etaDesc;
	}

	public String getLoadPort() {
		return loadPort;
	}

	public void setLoadPort(String loadPort) {
		this.loadPort = loadPort;
	}

	public String getNorDate() {
		return norDate;
	}

	public void setNorDate(String norDate) {
		this.norDate = norDate;
	}

	public String getShNetBarrel() {
		return shNetBarrel;
	}

	public void setShNetBarrel(String shNetBarrel) {
		this.shNetBarrel = shNetBarrel;
	}

	public String getShNetTonnage() {
		return shNetTonnage;
	}

	public void setShNetTonnage(String shNetTonnage) {
		this.shNetTonnage = shNetTonnage;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
}