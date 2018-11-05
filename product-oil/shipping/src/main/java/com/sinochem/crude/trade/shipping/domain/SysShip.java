package com.sinochem.crude.trade.shipping.domain;

import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;

public class SysShip implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**主键_ID*/
	private Long sysShipId;  
	
	/**UUID*/
	private String uuid;  
	
	/***/
	private String vesselName;  
	
	/***/
	private String imo;  
	
	/***/
	private String built;  
	
	/***/
	private String vesselType;  
	
	/***/
	private java.math.BigDecimal vesselSize;  
	
	/***/
	private java.math.BigDecimal cubic;  
	
	/***/
	private java.math.BigDecimal sdwt;  
	
	/***/
	private java.math.BigDecimal loa;  
	
	/***/
	private java.math.BigDecimal beam;  
	
	/***/
	private java.math.BigDecimal draft;  
	
	/***/
	private String hullType;  
	
	/**文件名称*/
	private String accessoryFileNm;  
	
	/**附件地址*/
	private String accessory;  
	
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
	public void setSysShipId(Long sysShipId){
		this.sysShipId=sysShipId;
	}
	/**主键_ID*/
	public Long getSysShipId(){
		return this.sysShipId;
	}
	
	/**UUID*/
	public void setUuid(String uuid){
		this.uuid=uuid;
	}
	/**UUID*/
	public String getUuid(){
		return this.uuid;
	}
	
	/***/
	public void setVesselName(String vesselName){
		this.vesselName=vesselName;
	}
	/***/
	public String getVesselName(){
		return this.vesselName;
	}
	
	/***/
	public void setImo(String imo){
		this.imo=imo;
	}
	/***/
	public String getImo(){
		return this.imo;
	}
	
	/***/
	public void setBuilt(String built){
		this.built=built;
	}
	/***/
	public String getBuilt(){
		return this.built;
	}
	
	/***/
	public void setVesselType(String vesselType){
		this.vesselType=vesselType;
	}
	/***/
	public String getVesselType(){
		return this.vesselType;
	}
	
	/***/
	public void setVesselSize(java.math.BigDecimal vesselSize){
		this.vesselSize=vesselSize;
	}
	/***/
	public java.math.BigDecimal getVesselSize(){
		return this.vesselSize;
	}
	
	/***/
	public void setCubic(java.math.BigDecimal cubic){
		this.cubic=cubic;
	}
	/***/
	public java.math.BigDecimal getCubic(){
		return this.cubic;
	}
	
	/***/
	public void setSdwt(java.math.BigDecimal sdwt){
		this.sdwt=sdwt;
	}
	/***/
	public java.math.BigDecimal getSdwt(){
		return this.sdwt;
	}
	
	/***/
	public void setLoa(java.math.BigDecimal loa){
		this.loa=loa;
	}
	/***/
	public java.math.BigDecimal getLoa(){
		return this.loa;
	}
	
	/***/
	public void setBeam(java.math.BigDecimal beam){
		this.beam=beam;
	}
	/***/
	public java.math.BigDecimal getBeam(){
		return this.beam;
	}
	
	/***/
	public void setDraft(java.math.BigDecimal draft){
		this.draft=draft;
	}
	/***/
	public java.math.BigDecimal getDraft(){
		return this.draft;
	}
	
	/***/
	public void setHullType(String hullType){
		this.hullType=hullType;
	}
	/***/
	public String getHullType(){
		return this.hullType;
	}
	
	/**文件名称*/
	public void setAccessoryFileNm(String accessoryFileNm){
		this.accessoryFileNm=accessoryFileNm;
	}
	/**文件名称*/
	public String getAccessoryFileNm(){
		return this.accessoryFileNm;
	}
	
	/**附件地址*/
	public void setAccessory(String accessory){
		this.accessory=accessory;
	}
	/**附件地址*/
	public String getAccessory(){
		return this.accessory;
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
		map.put("sysShipId",this.sysShipId);
	map.put("uuid",this.uuid);
	map.put("vesselName",this.vesselName);
	map.put("imo",this.imo);
	map.put("built",this.built);
	map.put("vesselType",this.vesselType);
	map.put("vesselSize",this.vesselSize);
	map.put("cubic",this.cubic);
	map.put("sdwt",this.sdwt);
	map.put("loa",this.loa);
	map.put("beam",this.beam);
	map.put("draft",this.draft);
	map.put("hullType",this.hullType);
	map.put("accessoryFileNm",this.accessoryFileNm);
	map.put("accessory",this.accessory);
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