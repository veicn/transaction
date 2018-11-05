package com.sinochem.crude.trade.orderexecute.domain;

import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;

public class InterfaceContrast implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**主键*/
	private Long contrastId;  
	
	/**系统别名*/
	private String sysName;  
	
	/**参数类型*/
	private String paraType;  
	
	/**参数编码*/
	private String paraCode;  
	
	/**参数说明*/
	private String paraDesc;  
	
	/**对端参数编码*/
	private String otherCode;  
	
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
	public void setContrastId(Long contrastId){
		this.contrastId=contrastId;
	}
	/**主键*/
	public Long getContrastId(){
		return this.contrastId;
	}
	
	/**系统别名*/
	public void setSysName(String sysName){
		this.sysName=sysName;
	}
	/**系统别名*/
	public String getSysName(){
		return this.sysName;
	}
	
	/**参数类型*/
	public void setParaType(String paraType){
		this.paraType=paraType;
	}
	/**参数类型*/
	public String getParaType(){
		return this.paraType;
	}
	
	/**参数编码*/
	public void setParaCode(String paraCode){
		this.paraCode=paraCode;
	}
	/**参数编码*/
	public String getParaCode(){
		return this.paraCode;
	}
	
	/**参数说明*/
	public void setParaDesc(String paraDesc){
		this.paraDesc=paraDesc;
	}
	/**参数说明*/
	public String getParaDesc(){
		return this.paraDesc;
	}
	
	/**对端参数编码*/
	public void setOtherCode(String otherCode){
		this.otherCode=otherCode;
	}
	/**对端参数编码*/
	public String getOtherCode(){
		return this.otherCode;
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
		map.put("contrastId",this.contrastId);
	map.put("sysName",this.sysName);
	map.put("paraType",this.paraType);
	map.put("paraCode",this.paraCode);
	map.put("paraDesc",this.paraDesc);
	map.put("otherCode",this.otherCode);
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