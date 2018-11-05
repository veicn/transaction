package com.sinochem.crude.trade.orderexecute.domain;

import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;

public class InterfaceList implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**ID*/
	private Long interfaceId;  
	
	/**外部系统别名*/
	private String sysName;  
	
	/**外部系统用户角色*/
	private String memRole;  
	
	/**接口类型(WEBSERVICE RESTFUL)*/
	private String interfaceType;  
	
	/**接口业务类型*/
	private String businessType;  
	
	/**接口唯一编码*/
	private String interfaceCode;  
	
	/**接口地址*/
	private String interfaceUrl;  
	
	/**接口说明*/
	private String interfaceDesc;  
	
	/**入参说明*/
	private String inputDesc;  
	
	/**出参说明*/
	private String outputDesc;  
	
	/**语言类型*/
	private String langVer;  
	
	/**当前有效状态*/
	private String aliveFlag;  
	
	/**创建人*/
	private Long createUser;  
	
	/**创建时间*/
	private java.util.Date createDate;  
	
	/**修改人*/
	private Long updateUser;  
	
	/**修改时间*/
	private java.util.Date updateDate;  
	
	/**版本号*/
	private Integer version;  
	
		/**ID*/
	public void setInterfaceId(Long interfaceId){
		this.interfaceId=interfaceId;
	}
	/**ID*/
	public Long getInterfaceId(){
		return this.interfaceId;
	}
	
	/**外部系统别名*/
	public void setSysName(String sysName){
		this.sysName=sysName;
	}
	/**外部系统别名*/
	public String getSysName(){
		return this.sysName;
	}
	
	/**外部系统用户角色*/
	public void setMemRole(String memRole){
		this.memRole=memRole;
	}
	/**外部系统用户角色*/
	public String getMemRole(){
		return this.memRole;
	}
	
	/**接口类型(WEBSERVICE RESTFUL)*/
	public void setInterfaceType(String interfaceType){
		this.interfaceType=interfaceType;
	}
	/**接口类型(WEBSERVICE RESTFUL)*/
	public String getInterfaceType(){
		return this.interfaceType;
	}
	
	/**接口业务类型*/
	public void setBusinessType(String businessType){
		this.businessType=businessType;
	}
	/**接口业务类型*/
	public String getBusinessType(){
		return this.businessType;
	}
	
	/**接口唯一编码*/
	public void setInterfaceCode(String interfaceCode){
		this.interfaceCode=interfaceCode;
	}
	/**接口唯一编码*/
	public String getInterfaceCode(){
		return this.interfaceCode;
	}
	
	/**接口地址*/
	public void setInterfaceUrl(String interfaceUrl){
		this.interfaceUrl=interfaceUrl;
	}
	/**接口地址*/
	public String getInterfaceUrl(){
		return this.interfaceUrl;
	}
	
	/**接口说明*/
	public void setInterfaceDesc(String interfaceDesc){
		this.interfaceDesc=interfaceDesc;
	}
	/**接口说明*/
	public String getInterfaceDesc(){
		return this.interfaceDesc;
	}
	
	/**入参说明*/
	public void setInputDesc(String inputDesc){
		this.inputDesc=inputDesc;
	}
	/**入参说明*/
	public String getInputDesc(){
		return this.inputDesc;
	}
	
	/**出参说明*/
	public void setOutputDesc(String outputDesc){
		this.outputDesc=outputDesc;
	}
	/**出参说明*/
	public String getOutputDesc(){
		return this.outputDesc;
	}
	
	/**语言类型*/
	public void setLangVer(String langVer){
		this.langVer=langVer;
	}
	/**语言类型*/
	public String getLangVer(){
		return this.langVer;
	}
	
	/**当前有效状态*/
	public void setAliveFlag(String aliveFlag){
		this.aliveFlag=aliveFlag;
	}
	/**当前有效状态*/
	public String getAliveFlag(){
		return this.aliveFlag;
	}
	
	/**创建人*/
	public void setCreateUser(Long createUser){
		this.createUser=createUser;
	}
	/**创建人*/
	public Long getCreateUser(){
		return this.createUser;
	}
	
	/**创建时间*/
	public void setCreateDate(java.util.Date createDate){
		this.createDate=createDate;
	}
	/**创建时间*/
	public java.util.Date getCreateDate(){
		return this.createDate;
	}
	
	/**修改人*/
	public void setUpdateUser(Long updateUser){
		this.updateUser=updateUser;
	}
	/**修改人*/
	public Long getUpdateUser(){
		return this.updateUser;
	}
	
	/**修改时间*/
	public void setUpdateDate(java.util.Date updateDate){
		this.updateDate=updateDate;
	}
	/**修改时间*/
	public java.util.Date getUpdateDate(){
		return this.updateDate;
	}
	
	/**版本号*/
	public void setVersion(Integer version){
		this.version=version;
	}
	/**版本号*/
	public Integer getVersion(){
		return this.version;
	}
	
		
	
	public Map<String, Object> toMap() {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("interfaceId",this.interfaceId);
	map.put("sysName",this.sysName);
	map.put("memRole",this.memRole);
	map.put("interfaceType",this.interfaceType);
	map.put("businessType",this.businessType);
	map.put("interfaceCode",this.interfaceCode);
	map.put("interfaceUrl",this.interfaceUrl);
	map.put("interfaceDesc",this.interfaceDesc);
	map.put("inputDesc",this.inputDesc);
	map.put("outputDesc",this.outputDesc);
	map.put("langVer",this.langVer);
	map.put("aliveFlag",this.aliveFlag);
	map.put("createUser",this.createUser);
	map.put("createDate",this.createDate);
	map.put("updateUser",this.updateUser);
	map.put("updateDate",this.updateDate);
	map.put("version",this.version);
			return map;
	}
}