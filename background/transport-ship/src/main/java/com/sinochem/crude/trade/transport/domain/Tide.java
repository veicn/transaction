package com.sinochem.crude.trade.transport.domain;

import java.io.Serializable;

public class Tide implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**业务唯一键*/
	private Long tideId;  
	
	/**UUID*/
	private String uuid;  
	
	/**港口code*/
	private String portCode;  
	
	/**港口名称*/
	private String portName;  
	
	/**小时*/
	private String hour;  
	
	/**分钟*/
	private String minute;  
	
	/**潮高（cm）*/
	private java.math.BigDecimal height;  
	
	/**日期*/
	private String date;  
	
	/**备注*/
	private String remark;  
	
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
	public void setTideId(Long tideId){
		this.tideId=tideId;
	}
	/**业务唯一键*/
	public Long getTideId(){
		return this.tideId;
	}
	
	/**UUID*/
	public void setUuid(String uuid){
		this.uuid=uuid;
	}
	/**UUID*/
	public String getUuid(){
		return this.uuid;
	}
	
	/**港口code*/
	public void setPortCode(String portCode){
		this.portCode=portCode;
	}
	/**港口code*/
	public String getPortCode(){
		return this.portCode;
	}
	
	/**港口名称*/
	public void setPortName(String portName){
		this.portName=portName;
	}
	/**港口名称*/
	public String getPortName(){
		return this.portName;
	}
	
	/**小时*/
	public void setHour(String hour){
		this.hour=hour;
	}
	/**小时*/
	public String getHour(){
		return this.hour;
	}
	
	/**分钟*/
	public void setMinute(String minute){
		this.minute=minute;
	}
	/**分钟*/
	public String getMinute(){
		return this.minute;
	}
	
	/**潮高（cm）*/
	public void setHeight(java.math.BigDecimal height){
		this.height=height;
	}
	/**潮高（cm）*/
	public java.math.BigDecimal getHeight(){
		return this.height;
	}
	
	/**日期*/
	public void setDate(String date){
		this.date=date;
	}
	/**日期*/
	public String getDate(){
		return this.date;
	}
	
	/**备注*/
	public void setRemark(String remark){
		this.remark=remark;
	}
	/**备注*/
	public String getRemark(){
		return this.remark;
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
		map.put("tideId",this.tideId);
	map.put("uuid",this.uuid);
	map.put("portCode",this.portCode);
	map.put("portName",this.portName);
	map.put("hour",this.hour);
	map.put("minute",this.minute);
	map.put("height",this.height);
	map.put("date",this.date);
	map.put("remark",this.remark);
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