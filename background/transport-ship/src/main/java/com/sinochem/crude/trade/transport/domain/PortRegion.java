package com.sinochem.crude.trade.transport.domain;

import java.io.Serializable;

public class PortRegion implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**业务唯一键*/
	private Long portRegionId;  
	
	/**UUID*/
	private String uuid;  
	
	/**类型（1装港2卸港）*/
	private String type;  
	
	/**港口名称*/
	private String portName;  
	
	/**地区*/
	private String region;  
	
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
	
	/**扩展字段3*/
	private String ext3;  
	
	/**扩展字段2*/
	private String ext2;  
	
	/**扩展字段1*/
	private String ext1;  
	
	/**扩展字段4*/
	private String ext4;  
	
		/**业务唯一键*/
	public void setPortRegionId(Long portRegionId){
		this.portRegionId=portRegionId;
	}
	/**业务唯一键*/
	public Long getPortRegionId(){
		return this.portRegionId;
	}
	
	/**UUID*/
	public void setUuid(String uuid){
		this.uuid=uuid;
	}
	/**UUID*/
	public String getUuid(){
		return this.uuid;
	}
	
	/**类型（1装港2卸港）*/
	public void setType(String type){
		this.type=type;
	}
	/**类型（1装港2卸港）*/
	public String getType(){
		return this.type;
	}
	
	/**港口名称*/
	public void setPortName(String portName){
		this.portName=portName;
	}
	/**港口名称*/
	public String getPortName(){
		return this.portName;
	}
	
	/**地区*/
	public void setRegion(String region){
		this.region=region;
	}
	/**地区*/
	public String getRegion(){
		return this.region;
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
	
	/**扩展字段3*/
	public void setExt3(String ext3){
		this.ext3=ext3;
	}
	/**扩展字段3*/
	public String getExt3(){
		return this.ext3;
	}
	
	/**扩展字段2*/
	public void setExt2(String ext2){
		this.ext2=ext2;
	}
	/**扩展字段2*/
	public String getExt2(){
		return this.ext2;
	}
	
	/**扩展字段1*/
	public void setExt1(String ext1){
		this.ext1=ext1;
	}
	/**扩展字段1*/
	public String getExt1(){
		return this.ext1;
	}
	
	/**扩展字段4*/
	public void setExt4(String ext4){
		this.ext4=ext4;
	}
	/**扩展字段4*/
	public String getExt4(){
		return this.ext4;
	}
	
		
	
	/*public Map<String, Object> toMap() {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("portRegionId",this.portRegionId);
	map.put("uuid",this.uuid);
	map.put("type",this.type);
	map.put("portName",this.portName);
	map.put("region",this.region);
	map.put("aliveFlag",this.aliveFlag);
	map.put("version",this.version);
	map.put("langVer",this.langVer);
	map.put("createDate",this.createDate);
	map.put("updateDate",this.updateDate);
	map.put("createUser",this.createUser);
	map.put("updateUser",this.updateUser);
	map.put("ext3",this.ext3);
	map.put("ext2",this.ext2);
	map.put("ext1",this.ext1);
	map.put("ext4",this.ext4);
			return map;
	}*/
}