package com.sinochem.crude.trade.shipping.domain;

import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;

public class LoadingProgress implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**主键_ID*/
	private Long shipLoadingProgressId;  
	
	/**UUID*/
	private String uuid;  
	
	/**船舶确认单ID*/
	private Long shipConfirmationSheetId;  
	
	/**船舶装港表ID*/
	private String shipLoadPortId;  
	
	/**船舶装港表*/
	private String shipLoadPort;  
	
	/**日期*/
	private java.util.Date datetime;  
	
	/**已装吨数*/
	private java.math.BigDecimal cargoLoaded;  
	
	/**未装吨数*/
	private java.math.BigDecimal theCargoToBeLoaded;  
	
	/**装速*/
	private java.math.BigDecimal loadingSpeed;  
	
	/**预计完货时间*/
	private java.util.Date etc;  
	
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
	
		/**主键_ID*/
	public void setShipLoadingProgressId(Long shipLoadingProgressId){
		this.shipLoadingProgressId=shipLoadingProgressId;
	}
	/**主键_ID*/
	public Long getShipLoadingProgressId(){
		return this.shipLoadingProgressId;
	}
	
	/**UUID*/
	public void setUuid(String uuid){
		this.uuid=uuid;
	}
	/**UUID*/
	public String getUuid(){
		return this.uuid;
	}
	
	/**船舶确认单ID*/
	public void setShipConfirmationSheetId(Long shipConfirmationSheetId){
		this.shipConfirmationSheetId=shipConfirmationSheetId;
	}
	/**船舶确认单ID*/
	public Long getShipConfirmationSheetId(){
		return this.shipConfirmationSheetId;
	}
	
	/**船舶装港表ID*/
	public void setShipLoadPortId(String shipLoadPortId){
		this.shipLoadPortId=shipLoadPortId;
	}
	/**船舶装港表ID*/
	public String getShipLoadPortId(){
		return this.shipLoadPortId;
	}
	
	/**船舶装港表*/
	public void setShipLoadPort(String shipLoadPort){
		this.shipLoadPort=shipLoadPort;
	}
	/**船舶装港表*/
	public String getShipLoadPort(){
		return this.shipLoadPort;
	}
	
	/**日期*/
	public void setDatetime(java.util.Date datetime){
		this.datetime=datetime;
	}
	/**日期*/
	public java.util.Date getDatetime(){
		return this.datetime;
	}
	
	/**已装吨数*/
	public void setCargoLoaded(java.math.BigDecimal cargoLoaded){
		this.cargoLoaded=cargoLoaded;
	}
	/**已装吨数*/
	public java.math.BigDecimal getCargoLoaded(){
		return this.cargoLoaded;
	}
	
	/**未装吨数*/
	public void setTheCargoToBeLoaded(java.math.BigDecimal theCargoToBeLoaded){
		this.theCargoToBeLoaded=theCargoToBeLoaded;
	}
	/**未装吨数*/
	public java.math.BigDecimal getTheCargoToBeLoaded(){
		return this.theCargoToBeLoaded;
	}
	
	/**装速*/
	public void setLoadingSpeed(java.math.BigDecimal loadingSpeed){
		this.loadingSpeed=loadingSpeed;
	}
	/**装速*/
	public java.math.BigDecimal getLoadingSpeed(){
		return this.loadingSpeed;
	}
	
	/**预计完货时间*/
	public void setEtc(java.util.Date etc){
		this.etc=etc;
	}
	/**预计完货时间*/
	public java.util.Date getEtc(){
		return this.etc;
	}
	
	/**预留字段1*/
	public void setExt1(String ext1){
		this.ext1=ext1;
	}
	/**预留字段1*/
	public String getExt1(){
		return this.ext1;
	}
	
	/**版本号*/
	public void setVersion(String version){
		this.version=version;
	}
	/**版本号*/
	public String getVersion(){
		return this.version;
	}
	
	/**当前有效状态*/
	public void setAliveFlag(String aliveFlag){
		this.aliveFlag=aliveFlag;
	}
	/**当前有效状态*/
	public String getAliveFlag(){
		return this.aliveFlag;
	}
	
	/**创建时间*/
	public void setCreateDate(java.util.Date createDate){
		this.createDate=createDate;
	}
	/**创建时间*/
	public java.util.Date getCreateDate(){
		return this.createDate;
	}
	
	/**创建者*/
	public void setCreateUser(Long createUser){
		this.createUser=createUser;
	}
	/**创建者*/
	public Long getCreateUser(){
		return this.createUser;
	}
	
	/**更新时间*/
	public void setUpdateDate(java.util.Date updateDate){
		this.updateDate=updateDate;
	}
	/**更新时间*/
	public java.util.Date getUpdateDate(){
		return this.updateDate;
	}
	
	/**更新者*/
	public void setUpdateUser(Long updateUser){
		this.updateUser=updateUser;
	}
	/**更新者*/
	public Long getUpdateUser(){
		return this.updateUser;
	}
	
		
	
	public Map<String, Object> toMap() {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("shipLoadingProgressId",this.shipLoadingProgressId);
	map.put("uuid",this.uuid);
	map.put("shipConfirmationSheetId",this.shipConfirmationSheetId);
	map.put("shipLoadPortId",this.shipLoadPortId);
	map.put("shipLoadPort",this.shipLoadPort);
	map.put("datetime",this.datetime);
	map.put("cargoLoaded",this.cargoLoaded);
	map.put("theCargoToBeLoaded",this.theCargoToBeLoaded);
	map.put("loadingSpeed",this.loadingSpeed);
	map.put("etc",this.etc);
	map.put("ext1",this.ext1);
	map.put("version",this.version);
	map.put("aliveFlag",this.aliveFlag);
	map.put("createDate",this.createDate);
	map.put("createUser",this.createUser);
	map.put("updateDate",this.updateDate);
	map.put("updateUser",this.updateUser);
			return map;
	}
	
}