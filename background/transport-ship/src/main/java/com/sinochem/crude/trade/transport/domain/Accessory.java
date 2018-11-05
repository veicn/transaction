package com.sinochem.crude.trade.transport.domain;

import java.io.Serializable;

public class Accessory implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**业务唯一键*/
	private Long accessoryId;  
	
	/**UUID*/
	private String uuid;  
	
	/**业务类型*/
	private String type;  
	
	/**业务主键*/
	private Long id;  
	
	/**附件名称*/
	private String name;  
	
	/**附件路径*/
	private String path;  
	
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
	public void setAccessoryId(Long accessoryId){
		this.accessoryId=accessoryId;
	}
	/**业务唯一键*/
	public Long getAccessoryId(){
		return this.accessoryId;
	}
	
	/**UUID*/
	public void setUuid(String uuid){
		this.uuid=uuid;
	}
	/**UUID*/
	public String getUuid(){
		return this.uuid;
	}
	
	/**业务类型*/
	public void setType(String type){
		this.type=type;
	}
	/**业务类型*/
	public String getType(){
		return this.type;
	}
	
	/**业务主键*/
	public void setId(Long id){
		this.id=id;
	}
	/**业务主键*/
	public Long getId(){
		return this.id;
	}
	
	/**附件名称*/
	public void setName(String name){
		this.name=name;
	}
	/**附件名称*/
	public String getName(){
		return this.name;
	}
	
	/**附件路径*/
	public void setPath(String path){
		this.path=path;
	}
	/**附件路径*/
	public String getPath(){
		return this.path;
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
		map.put("accessoryId",this.accessoryId);
	map.put("uuid",this.uuid);
	map.put("type",this.type);
	map.put("id",this.id);
	map.put("name",this.name);
	map.put("path",this.path);
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