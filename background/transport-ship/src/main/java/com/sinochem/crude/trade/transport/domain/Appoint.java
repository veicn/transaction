package com.sinochem.crude.trade.transport.domain;

import java.io.Serializable;

public class Appoint implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**业务唯一键*/
	private Long appointId;  
	
	/**UUID*/
	private String uuid;  
	
	/**船合同id*/
	private Long shipPactId;  
	
	/**船合同uuid*/
	private String shipPactUuid;  
	
	/**代理协议uuid*/
	private String agreementUuid;  
	
	/**代理协议编号*/
	private String agreementCode;  
	
	/**公司id*/
	private Long epMemberId;  
	
	/**卸港表id*/
	private Long unloadPortId;  
	
	/**卸港表uuid*/
	private String unloadPortUuid;  
	
	/**卸港*/
	private String unloadPort;  
	
	/**卸港商检指定*/
	private String inspection;  
	
	/**卸港商检指定联系方式*/
	private String inspectionTel;  
	
	/**卸港监卸*/
	private String monitor;  
	
	/**卸港监卸联系方式*/
	private String monitorTel;  
	
	/**卸港船代*/
	private String agency;  
	
	/**联系方卸港船代联系方式*/
	private String agencyTel;  
	
	/**是否有效(1有效0无效)*/
	private String aliveFlag;  
	
	/**版本号*/
	private String version;  
	
	/**语言版本*/
	private String langVer;  
	
	/**创建时间*/
	private java.util.Date createDate;  
	
	/**修改时间*/
	private java.util.Date updateDate;  
	
	/**创建人*/
	private Long createUser;  
	
	/**修改人*/
	private Long updateUser;  
	
	/**扩展字段1*/
	private String ext1;  
	
		/**业务唯一键*/
	public void setAppointId(Long appointId){
		this.appointId=appointId;
	}
	/**业务唯一键*/
	public Long getAppointId(){
		return this.appointId;
	}
	
	/**UUID*/
	public void setUuid(String uuid){
		this.uuid=uuid;
	}
	/**UUID*/
	public String getUuid(){
		return this.uuid;
	}
	
	/**船合同id*/
	public void setShipPactId(Long shipPactId){
		this.shipPactId=shipPactId;
	}
	/**船合同id*/
	public Long getShipPactId(){
		return this.shipPactId;
	}
	
	/**船合同uuid*/
	public void setShipPactUuid(String shipPactUuid){
		this.shipPactUuid=shipPactUuid;
	}
	/**船合同uuid*/
	public String getShipPactUuid(){
		return this.shipPactUuid;
	}
	
	/**代理协议uuid*/
	public void setAgreementUuid(String agreementUuid){
		this.agreementUuid=agreementUuid;
	}
	/**代理协议uuid*/
	public String getAgreementUuid(){
		return this.agreementUuid;
	}
	
	/**代理协议编号*/
	public void setAgreementCode(String agreementCode){
		this.agreementCode=agreementCode;
	}
	/**代理协议编号*/
	public String getAgreementCode(){
		return this.agreementCode;
	}
	
	/**公司id*/
	public void setEpMemberId(Long epMemberId){
		this.epMemberId=epMemberId;
	}
	/**公司id*/
	public Long getEpMemberId(){
		return this.epMemberId;
	}
	
	/**卸港表id*/
	public void setUnloadPortId(Long unloadPortId){
		this.unloadPortId=unloadPortId;
	}
	/**卸港表id*/
	public Long getUnloadPortId(){
		return this.unloadPortId;
	}
	
	/**卸港表uuid*/
	public void setUnloadPortUuid(String unloadPortUuid){
		this.unloadPortUuid=unloadPortUuid;
	}
	/**卸港表uuid*/
	public String getUnloadPortUuid(){
		return this.unloadPortUuid;
	}
	
	/**卸港*/
	public void setUnloadPort(String unloadPort){
		this.unloadPort=unloadPort;
	}
	/**卸港*/
	public String getUnloadPort(){
		return this.unloadPort;
	}
	
	/**卸港商检指定*/
	public void setInspection(String inspection){
		this.inspection=inspection;
	}
	/**卸港商检指定*/
	public String getInspection(){
		return this.inspection;
	}
	
	/**卸港商检指定联系方式*/
	public void setInspectionTel(String inspectionTel){
		this.inspectionTel=inspectionTel;
	}
	/**卸港商检指定联系方式*/
	public String getInspectionTel(){
		return this.inspectionTel;
	}
	
	/**卸港监卸*/
	public void setMonitor(String monitor){
		this.monitor=monitor;
	}
	/**卸港监卸*/
	public String getMonitor(){
		return this.monitor;
	}
	
	/**卸港监卸联系方式*/
	public void setMonitorTel(String monitorTel){
		this.monitorTel=monitorTel;
	}
	/**卸港监卸联系方式*/
	public String getMonitorTel(){
		return this.monitorTel;
	}
	
	/**卸港船代*/
	public void setAgency(String agency){
		this.agency=agency;
	}
	/**卸港船代*/
	public String getAgency(){
		return this.agency;
	}
	
	/**联系方卸港船代联系方式*/
	public void setAgencyTel(String agencyTel){
		this.agencyTel=agencyTel;
	}
	/**联系方卸港船代联系方式*/
	public String getAgencyTel(){
		return this.agencyTel;
	}
	
	/**是否有效(1有效0无效)*/
	public void setAliveFlag(String aliveFlag){
		this.aliveFlag=aliveFlag;
	}
	/**是否有效(1有效0无效)*/
	public String getAliveFlag(){
		return this.aliveFlag;
	}
	
	/**版本号*/
	public void setVersion(String version){
		this.version=version;
	}
	/**版本号*/
	public String getVersion(){
		return this.version;
	}
	
	/**语言版本*/
	public void setLangVer(String langVer){
		this.langVer=langVer;
	}
	/**语言版本*/
	public String getLangVer(){
		return this.langVer;
	}
	
	/**创建时间*/
	public void setCreateDate(java.util.Date createDate){
		this.createDate=createDate;
	}
	/**创建时间*/
	public java.util.Date getCreateDate(){
		return this.createDate;
	}
	
	/**修改时间*/
	public void setUpdateDate(java.util.Date updateDate){
		this.updateDate=updateDate;
	}
	/**修改时间*/
	public java.util.Date getUpdateDate(){
		return this.updateDate;
	}
	
	/**创建人*/
	public void setCreateUser(Long createUser){
		this.createUser=createUser;
	}
	/**创建人*/
	public Long getCreateUser(){
		return this.createUser;
	}
	
	/**修改人*/
	public void setUpdateUser(Long updateUser){
		this.updateUser=updateUser;
	}
	/**修改人*/
	public Long getUpdateUser(){
		return this.updateUser;
	}
	
	/**扩展字段1*/
	public void setExt1(String ext1){
		this.ext1=ext1;
	}
	/**扩展字段1*/
	public String getExt1(){
		return this.ext1;
	}
	
		
	
	/*public Map<String, Object> toMap() {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("appointId",this.appointId);
	map.put("uuid",this.uuid);
	map.put("shipPactId",this.shipPactId);
	map.put("shipPactUuid",this.shipPactUuid);
	map.put("agreementUuid",this.agreementUuid);
	map.put("agreementCode",this.agreementCode);
	map.put("epMemberId",this.epMemberId);
	map.put("unloadPortId",this.unloadPortId);
	map.put("unloadPortUuid",this.unloadPortUuid);
	map.put("unloadPort",this.unloadPort);
	map.put("inspection",this.inspection);
	map.put("inspectionTel",this.inspectionTel);
	map.put("monitor",this.monitor);
	map.put("monitorTel",this.monitorTel);
	map.put("agency",this.agency);
	map.put("agencyTel",this.agencyTel);
	map.put("aliveFlag",this.aliveFlag);
	map.put("version",this.version);
	map.put("langVer",this.langVer);
	map.put("createDate",this.createDate);
	map.put("updateDate",this.updateDate);
	map.put("createUser",this.createUser);
	map.put("updateUser",this.updateUser);
	map.put("ext1",this.ext1);
			return map;
	}*/
}