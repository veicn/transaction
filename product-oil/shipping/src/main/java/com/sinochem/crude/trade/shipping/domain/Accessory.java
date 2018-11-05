package com.sinochem.crude.trade.shipping.domain;

import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;

public class Accessory implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**主键_ID*/
	private Long accessoryId;  
	
	/**UUID*/
	private String uuid;  
	
	/**平台船舶ID*/
	private Long sysShipId;  
	
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
	public void setAccessoryId(Long accessoryId){
		this.accessoryId=accessoryId;
	}
	/**主键_ID*/
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
	
	/**平台船舶ID*/
	public void setSysShipId(Long sysShipId){
		this.sysShipId=sysShipId;
	}
	/**平台船舶ID*/
	public Long getSysShipId(){
		return this.sysShipId;
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
		map.put("accessoryId",this.accessoryId);
	map.put("uuid",this.uuid);
	map.put("sysShipId",this.sysShipId);
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