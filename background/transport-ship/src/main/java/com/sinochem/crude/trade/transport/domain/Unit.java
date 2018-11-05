package com.sinochem.crude.trade.transport.domain;

import java.io.Serializable;

public class Unit implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	
	/**业务唯一键*/
	private Long unitId;  
	
	/**UUID*/
	private String uuid;  
	
	/**中文名称*/
	private String chName;  
	
	/**英文名称*/
	private String usName;  
	
	/**类型*/
	private String type;  
	
	/**是否参考单位*/
	private String isDefaultUnit;  
	
	/**显示排序用*/
	private Long rank;  
	
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
	public void setUnitId(Long unitId){
		this.unitId=unitId;
	}
	/**业务唯一键*/
	public Long getUnitId(){
		return this.unitId;
	}
	
	/**UUID*/
	public void setUuid(String uuid){
		this.uuid=uuid;
	}
	/**UUID*/
	public String getUuid(){
		return this.uuid;
	}
	
	/**中文名称*/
	public void setChName(String chName){
		this.chName=chName;
	}
	/**中文名称*/
	public String getChName(){
		return this.chName;
	}
	
	/**英文名称*/
	public void setUsName(String usName){
		this.usName=usName;
	}
	/**英文名称*/
	public String getUsName(){
		return this.usName;
	}
	
	/**类型*/
	public void setType(String type){
		this.type=type;
	}
	/**类型*/
	public String getType(){
		return this.type;
	}
	
	/**是否参考单位*/
	public void setIsDefaultUnit(String isDefaultUnit){
		this.isDefaultUnit=isDefaultUnit;
	}
	/**是否参考单位*/
	public String getIsDefaultUnit(){
		return this.isDefaultUnit;
	}
	
	/**显示排序用*/
	public void setRank(Long rank){
		this.rank=rank;
	}
	/**显示排序用*/
	public Long getRank(){
		return this.rank;
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
		map.put("unitId",this.unitId);
	map.put("uuid",this.uuid);
	map.put("chName",this.chName);
	map.put("usName",this.usName);
	map.put("type",this.type);
	map.put("isDefaultUnit",this.isDefaultUnit);
	map.put("rank",this.rank);
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