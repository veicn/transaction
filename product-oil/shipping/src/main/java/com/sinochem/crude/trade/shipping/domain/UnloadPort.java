package com.sinochem.crude.trade.shipping.domain;

import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;

public class UnloadPort implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**主键_ID*/
	private Long unloadPortId;  
	
	/**UUID*/
	private String uuid;  
	
	/**船舶确认单ID*/
	private Long shipConfirmationSheetId;  
	
	/**卸货港口ID*/
	private String dischargingPortId;  
	
	/**卸货港口*/
	private String dischargingPort;  
	
	/**起锚时间*/
	private java.util.Date anchorAweigh;  
	
	/**引水上船时间*/
	private java.util.Date pob;  
	
	/**靠泊时间*/
	private java.util.Date allFast;  
	
	/**拆臂扫管线时间*/
	private java.util.Date shoreArmDisconnecting;  
	
	/**完成卸货日期时间*/
	private java.util.Date completedDischarged;  
	
	/**离泊*/
	private java.util.Date allLinesCastOffAndVesselSailed;  
	
	/**公吨*/
	private String metricTons;  
	
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
	public void setUnloadPortId(Long unloadPortId){
		this.unloadPortId=unloadPortId;
	}
	/**主键_ID*/
	public Long getUnloadPortId(){
		return this.unloadPortId;
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
	
	/**卸货港口ID*/
	public void setDischargingPortId(String dischargingPortId){
		this.dischargingPortId=dischargingPortId;
	}
	/**卸货港口ID*/
	public String getDischargingPortId(){
		return this.dischargingPortId;
	}
	
	/**卸货港口*/
	public void setDischargingPort(String dischargingPort){
		this.dischargingPort=dischargingPort;
	}
	/**卸货港口*/
	public String getDischargingPort(){
		return this.dischargingPort;
	}
	
	/**起锚时间*/
	public void setAnchorAweigh(java.util.Date anchorAweigh){
		this.anchorAweigh=anchorAweigh;
	}
	/**起锚时间*/
	public java.util.Date getAnchorAweigh(){
		return this.anchorAweigh;
	}
	
	/**引水上船时间*/
	public void setPob(java.util.Date pob){
		this.pob=pob;
	}
	/**引水上船时间*/
	public java.util.Date getPob(){
		return this.pob;
	}
	
	/**靠泊时间*/
	public void setAllFast(java.util.Date allFast){
		this.allFast=allFast;
	}
	/**靠泊时间*/
	public java.util.Date getAllFast(){
		return this.allFast;
	}
	
	/**拆臂扫管线时间*/
	public void setShoreArmDisconnecting(java.util.Date shoreArmDisconnecting){
		this.shoreArmDisconnecting=shoreArmDisconnecting;
	}
	/**拆臂扫管线时间*/
	public java.util.Date getShoreArmDisconnecting(){
		return this.shoreArmDisconnecting;
	}
	
	/**完成卸货日期时间*/
	public void setCompletedDischarged(java.util.Date completedDischarged){
		this.completedDischarged=completedDischarged;
	}
	/**完成卸货日期时间*/
	public java.util.Date getCompletedDischarged(){
		return this.completedDischarged;
	}
	
	/**离泊*/
	public void setAllLinesCastOffAndVesselSailed(java.util.Date allLinesCastOffAndVesselSailed){
		this.allLinesCastOffAndVesselSailed=allLinesCastOffAndVesselSailed;
	}
	/**离泊*/
	public java.util.Date getAllLinesCastOffAndVesselSailed(){
		return this.allLinesCastOffAndVesselSailed;
	}
	
	/**公吨*/
	public void setMetricTons(String metricTons){
		this.metricTons=metricTons;
	}
	/**公吨*/
	public String getMetricTons(){
		return this.metricTons;
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
		map.put("unloadPortId",this.unloadPortId);
	map.put("uuid",this.uuid);
	map.put("shipConfirmationSheetId",this.shipConfirmationSheetId);
	map.put("dischargingPortId",this.dischargingPortId);
	map.put("dischargingPort",this.dischargingPort);
	map.put("anchorAweigh",this.anchorAweigh);
	map.put("pob",this.pob);
	map.put("allFast",this.allFast);
	map.put("shoreArmDisconnecting",this.shoreArmDisconnecting);
	map.put("completedDischarged",this.completedDischarged);
	map.put("allLinesCastOffAndVesselSailed",this.allLinesCastOffAndVesselSailed);
	map.put("metricTons",this.metricTons);
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