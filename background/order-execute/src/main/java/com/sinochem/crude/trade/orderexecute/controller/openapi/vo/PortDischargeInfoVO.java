package com.sinochem.crude.trade.orderexecute.controller.openapi.vo;


/**
 * 
 * @author Down
 *
 */
public class PortDischargeInfoVO extends InputVO {
	private static final long serialVersionUID = 1L;

	/**
	 * 实际卸货完成时间
	 */
	private String acFinish;
	
	/**
	 * 实际卸货开始时间
	 */
	private String acStart;
	
	/**
	 * NOR递交时间
	 */
	private String norDate;
	
	/**
	 * 卸港商检净桶
	 */
	private String commCleanBar;
	
	/**
	 * 	卸港商检净吨
	 */
	private String commCleanTon;
	
	/**
	 * 	卸港国检净桶
	 */
	private String counCleanBar;
	
	/**
	 * 	卸港国检净吨
	 */
	private String counCleanTon;
	
	/**
	 * 	来源系统代码
	 */
	private String sysName;
	
	/**
	 * 	卸货港
	 */
	private String unloadPort;
	
	/**
	
	/**
	 * 订单ID
	 */
	private String uuid;

	public String getAcFinish() {
		return acFinish;
	}

	public String getAcStart() {
		return acStart;
	}

	public String getNorDate() {
		return norDate;
	}

	public String getCommCleanBar() {
		return commCleanBar;
	}

	public String getCommCleanTon() {
		return commCleanTon;
	}

	public String getCounCleanBar() {
		return counCleanBar;
	}

	public String getCounCleanTon() {
		return counCleanTon;
	}

	public String getSysName() {
		return sysName;
	}

	public String getUnloadPort() {
		return unloadPort;
	}

	public String getUuid() {
		return uuid;
	}

	public void setAcFinish(String acFinish) {
		this.acFinish = acFinish;
	}

	public void setAcStart(String acStart) {
		this.acStart = acStart;
	}

	public void setNorDate(String norDate) {
		this.norDate = norDate;
	}

	public void setCommCleanBar(String commCleanBar) {
		this.commCleanBar = commCleanBar;
	}

	public void setCommCleanTon(String commCleanTon) {
		this.commCleanTon = commCleanTon;
	}

	public void setCounCleanBar(String counCleanBar) {
		this.counCleanBar = counCleanBar;
	}

	public void setCounCleanTon(String counCleanTon) {
		this.counCleanTon = counCleanTon;
	}

	public void setSysName(String sysName) {
		this.sysName = sysName;
	}

	public void setUnloadPort(String unloadPort) {
		this.unloadPort = unloadPort;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
}