package com.sinochem.crude.trade.transport.model.res;

import java.io.Serializable;

public class ShipPactRes implements Serializable {

	private static final long serialVersionUID = 1L;
	/**详细状态（1航次未开始2航次开始3已装港4在途5已卸港）*/
	private String detailStatus;  
	
	/**第一装港*/
	private String loadPort;  
	
	/**租约日*/
	private String signDate;  
	
	/**租船人名称*/
	private String carrierName;  
	
	/**船东*/
	private String shipOwner;  
	
	/**船名*/
	private String shipName;  
	
	/**laycan*/
	private String laycan;  
	
	/**油品合同号*/
	private String pactCode;  
	
	/**代理协议编号*/
	private String agreementCode;
	
	/**船合同编号*/
	private String shipPactCode;
	
	/**mmsi号*/
	private String mmsi;
	
	/**船合同uuid*/
	private String uuid;
	
	/**imo号*/
	private String imo;
	
	private String timeBeg;
	
	private String timeEnd;
	
	/**1轨迹2定位*/
	private String type;
	
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTimeBeg() {
		return timeBeg;
	}
	public void setTimeBeg(String timeBeg) {
		this.timeBeg = timeBeg;
	}
	public String getTimeEnd() {
		return timeEnd;
	}
	public void setTimeEnd(String timeEnd) {
		this.timeEnd = timeEnd;
	}
	/**imo号*/
	public String getImo() {
		return imo;
	}
	/**imo号*/
	public void setImo(String imo) {
		this.imo = imo;
	}
	/**船合同uuid*/
	public String getUuid() {
		return uuid;
	}
	/**船合同uuid*/
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getMmsi() {
		return mmsi;
	}
	public void setMmsi(String mmsi) {
		this.mmsi = mmsi;
	}
	public String getDetailStatus() {
		return detailStatus;
	}
	public void setDetailStatus(String detailStatus) {
		this.detailStatus = detailStatus;
	}
	public String getLaycan() {
		return laycan;
	}
	public void setLaycan(String laycan) {
		this.laycan = laycan;
	}
	public String getPactCode() {
		return pactCode;
	}
	public void setPactCode(String pactCode) {
		this.pactCode = pactCode;
	}
	public String getAgreementCode() {
		return agreementCode;
	}
	public void setAgreementCode(String agreementCode) {
		this.agreementCode = agreementCode;
	}
	public String getShipPactCode() {
		return shipPactCode;
	}
	public void setShipPactCode(String shipPactCode) {
		this.shipPactCode = shipPactCode;
	}
	/**第一装港*/
	public void setLoadPort(String loadPort){
		this.loadPort=loadPort;
	}
	/**第一装港*/
	public String getLoadPort(){
		return this.loadPort;
	}
	
	public String getSignDate() {
		return signDate;
	}
	public void setSignDate(String signDate) {
		this.signDate = signDate;
	}
	/**租船人名称*/
	public void setCarrierName(String carrierName){
		this.carrierName=carrierName;
	}
	/**租船人名称*/
	public String getCarrierName(){
		return this.carrierName;
	}
	
	/**船东*/
	public void setShipOwner(String shipOwner){
		this.shipOwner=shipOwner;
	}
	/**船东*/
	public String getShipOwner(){
		return this.shipOwner;
	}
	
	/**船名*/
	public void setShipName(String shipName){
		this.shipName=shipName;
	}
	/**船名*/
	public String getShipName(){
		return this.shipName;
	}
	
	
}