package com.sinochem.crude.trade.transport.domain;

import java.io.Serializable;

public class UnitRate implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**业务唯一键*/
	private Long unitRateId;  
	
	/**UUID*/
	private String uuid;  
	
	/**上级单位名称*/
	private String oneName;  
	
	/**下级单位名称*/
	private String twoName;  
	
	/**类型*/
	private String type;  
	
	/**底数*/
	private java.math.BigDecimal baseName;  
	
	/**幂次方*/
	private java.math.BigDecimal power;  
	
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
	public void setUnitRateId(Long unitRateId){
		this.unitRateId=unitRateId;
	}
	/**业务唯一键*/
	public Long getUnitRateId(){
		return this.unitRateId;
	}
	
	/**UUID*/
	public void setUuid(String uuid){
		this.uuid=uuid;
	}
	/**UUID*/
	public String getUuid(){
		return this.uuid;
	}
	
	/**上级单位名称*/
	public void setOneName(String oneName){
		this.oneName=oneName;
	}
	/**上级单位名称*/
	public String getOneName(){
		return this.oneName;
	}
	
	/**下级单位名称*/
	public void setTwoName(String twoName){
		this.twoName=twoName;
	}
	/**下级单位名称*/
	public String getTwoName(){
		return this.twoName;
	}
	
	/**类型*/
	public void setType(String type){
		this.type=type;
	}
	/**类型*/
	public String getType(){
		return this.type;
	}
	
	/**底数*/
	public void setBaseName(java.math.BigDecimal baseName){
		this.baseName=baseName;
	}
	/**底数*/
	public java.math.BigDecimal getBaseName(){
		return this.baseName;
	}
	
	/**幂次方*/
	public void setPower(java.math.BigDecimal power){
		this.power=power;
	}
	/**幂次方*/
	public java.math.BigDecimal getPower(){
		return this.power;
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
		map.put("unitRateId",this.unitRateId);
	map.put("uuid",this.uuid);
	map.put("oneName",this.oneName);
	map.put("twoName",this.twoName);
	map.put("type",this.type);
	map.put("baseName",this.baseName);
	map.put("power",this.power);
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