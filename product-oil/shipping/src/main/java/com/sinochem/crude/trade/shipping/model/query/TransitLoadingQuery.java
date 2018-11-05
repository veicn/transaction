package com.sinochem.crude.trade.shipping.model.query;

public class TransitLoadingQuery {

	/**UUID*/
	private String uuid;  
	
	/**船舶确认单ID*/
	private Long shipConfirmationSheetId;  
	
	/**日期*/
	private java.util.Date date;  
	
	/**到达港口*/
	private String loadingPort;  
	
	/**预计到港时间*/
	private java.util.Date eta;  
	
	/**预计离港时间*/
	private java.util.Date etb;  
	
	/**坐标（经纬度）*/
	private String posn;  
	
	/**海况CD*/
	private String seaId;  
	
	/**海况*/
	private String sea;  
	
	/**航速（24小时）*/
	private java.math.BigDecimal aveSpd24h;  
	
	/**预留字段1*/
	private String ext1;  
	
	/**版本号*/
	private String version;  
	
	/**当前有效状态*/
	private String aliveFlag;  
	
	/**创建时间*/
	private java.util.Date createDate;  
	
	/**创建者*/
	private Long createUser;  
	
	/**更新时间*/
	private java.util.Date updateDate;  
	
	/**更新者*/
	private Long updateUser;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Long getShipConfirmationSheetId() {
		return shipConfirmationSheetId;
	}

	public void setShipConfirmationSheetId(Long shipConfirmationSheetId) {
		this.shipConfirmationSheetId = shipConfirmationSheetId;
	}

	public java.util.Date getDate() {
		return date;
	}

	public void setDate(java.util.Date date) {
		this.date = date;
	}

	public String getLoadingPort() {
		return loadingPort;
	}

	public void setLoadingPort(String loadingPort) {
		this.loadingPort = loadingPort;
	}

	public java.util.Date getEta() {
		return eta;
	}

	public void setEta(java.util.Date eta) {
		this.eta = eta;
	}

	public java.util.Date getEtb() {
		return etb;
	}

	public void setEtb(java.util.Date etb) {
		this.etb = etb;
	}

	public String getPosn() {
		return posn;
	}

	public void setPosn(String posn) {
		this.posn = posn;
	}

	public String getSeaId() {
		return seaId;
	}

	public void setSeaId(String seaId) {
		this.seaId = seaId;
	}

	public String getSea() {
		return sea;
	}

	public void setSea(String sea) {
		this.sea = sea;
	}

	public java.math.BigDecimal getAveSpd24h() {
		return aveSpd24h;
	}

	public void setAveSpd24h(java.math.BigDecimal aveSpd24h) {
		this.aveSpd24h = aveSpd24h;
	}

	public String getExt1() {
		return ext1;
	}

	public void setExt1(String ext1) {
		this.ext1 = ext1;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getAliveFlag() {
		return aliveFlag;
	}

	public void setAliveFlag(String aliveFlag) {
		this.aliveFlag = aliveFlag;
	}

	public java.util.Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}

	public Long getCreateUser() {
		return createUser;
	}

	public void setCreateUser(Long createUser) {
		this.createUser = createUser;
	}

	public java.util.Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(java.util.Date updateDate) {
		this.updateDate = updateDate;
	}

	public Long getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(Long updateUser) {
		this.updateUser = updateUser;
	}
	
	
}
