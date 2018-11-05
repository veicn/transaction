package com.sinochem.crude.trade.orderexecute.domain;

import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;

public class InterfaceOutput implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**主键*/
	private Long outputId;  
	
	/**系统别名*/
	private String sysName;  
	
	/**接口唯一编码*/
	private String interfaceCode;  
	
	/**调用地址*/
	private String url;  
	
	/**调用状态*/
	private String status;  
	
	/**调用开始时间*/
	private java.util.Date startTime;  
	
	/**调用结束时间*/
	private java.util.Date endTime;  
	
	/**入参*/
	private String inputInfo;  
	
	/**出参*/
	private String outputInfo;  
	
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
	
		/**主键*/
	public void setOutputId(Long outputId){
		this.outputId=outputId;
	}
	/**主键*/
	public Long getOutputId(){
		return this.outputId;
	}
	
	/**系统别名*/
	public void setSysName(String sysName){
		this.sysName=sysName;
	}
	/**系统别名*/
	public String getSysName(){
		return this.sysName;
	}
	
	/**接口唯一编码*/
	public void setInterfaceCode(String interfaceCode){
		this.interfaceCode=interfaceCode;
	}
	/**接口唯一编码*/
	public String getInterfaceCode(){
		return this.interfaceCode;
	}
	
	/**调用地址*/
	public void setUrl(String url){
		this.url=url;
	}
	/**调用地址*/
	public String getUrl(){
		return this.url;
	}
	
	/**调用状态*/
	public void setStatus(String status){
		this.status=status;
	}
	/**调用状态*/
	public String getStatus(){
		return this.status;
	}
	
	/**调用开始时间*/
	public void setStartTime(java.util.Date startTime){
		this.startTime=startTime;
	}
	/**调用开始时间*/
	public java.util.Date getStartTime(){
		return this.startTime;
	}
	
	/**调用结束时间*/
	public void setEndTime(java.util.Date endTime){
		this.endTime=endTime;
	}
	/**调用结束时间*/
	public java.util.Date getEndTime(){
		return this.endTime;
	}
	
	/**入参*/
	public void setInputInfo(String inputInfo){
		this.inputInfo=inputInfo;
	}
	/**入参*/
	public String getInputInfo(){
		return this.inputInfo;
	}
	
	/**出参*/
	public void setOutputInfo(String outputInfo){
		this.outputInfo=outputInfo;
	}
	/**出参*/
	public String getOutputInfo(){
		return this.outputInfo;
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
		map.put("outputId",this.outputId);
	map.put("sysName",this.sysName);
	map.put("interfaceCode",this.interfaceCode);
	map.put("url",this.url);
	map.put("status",this.status);
	map.put("startTime",this.startTime);
	map.put("endTime",this.endTime);
	map.put("inputInfo",this.inputInfo);
	map.put("outputInfo",this.outputInfo);
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