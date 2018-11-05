package com.sinochem.crude.trade.orderexecute.domain;

import java.util.HashMap;
import java.util.Map;

import com.sinochem.crude.trade.common.SimplePageInfo;

import java.io.Serializable;

public class InterfaceInput implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**主键*/
	private Long inputId;  
	
	/**源系统*/
	private String sysName;  
	
	/**接口唯一编码*/
	private String interfaceCode;  
	
	/**接收地址*/
	private String inputUrl;  
	
	/**调用方式POST GET*/
	private String methodType;  
	
	/**处理状态*/
	private String status;  
	
	/**接收时间*/
	private java.util.Date inputTime;  
	
	/**接收参数*/
	private String inputPara;  
	
	/**输出参数*/
	private String outputPara;  
	
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
	public void setInputId(Long inputId){
		this.inputId=inputId;
	}
	/**主键*/
	public Long getInputId(){
		return this.inputId;
	}
	
	/**源系统*/
	public void setSysName(String sysName){
		this.sysName=sysName;
	}
	/**源系统*/
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
	
	/**接收地址*/
	public void setInputUrl(String inputUrl){
		this.inputUrl=inputUrl;
	}
	/**接收地址*/
	public String getInputUrl(){
		return this.inputUrl;
	}
	
	/**调用方式POST GET*/
	public void setMethodType(String methodType){
		this.methodType=methodType;
	}
	/**调用方式POST GET*/
	public String getMethodType(){
		return this.methodType;
	}
	
	/**处理状态*/
	public void setStatus(String status){
		this.status=status;
	}
	/**处理状态*/
	public String getStatus(){
		return this.status;
	}
	
	/**接收时间*/
	public void setInputTime(java.util.Date inputTime){
		this.inputTime=inputTime;
	}
	/**接收时间*/
	public java.util.Date getInputTime(){
		return this.inputTime;
	}
	
	/**接收参数*/
	public void setInputPara(String inputPara){
		this.inputPara=inputPara;
	}
	/**接收参数*/
	public String getInputPara(){
		return this.inputPara;
	}
	
	/**输出参数*/
	public void setOutputPara(String outputPara){
		this.outputPara=outputPara;
	}
	/**输出参数*/
	public String getOutputPara(){
		return this.outputPara;
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
		map.put("inputId",this.inputId);
	map.put("sysName",this.sysName);
	map.put("interfaceCode",this.interfaceCode);
	map.put("inputUrl",this.inputUrl);
	map.put("methodType",this.methodType);
	map.put("status",this.status);
	map.put("inputTime",this.inputTime);
	map.put("inputPara",this.inputPara);
	map.put("outputPara",this.outputPara);
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