package com.sinochem.crude.trade.transport.domain;

import java.io.Serializable;

public class BasicTariff implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**业务唯一键*/
	private Long basicTariffId;  
	
	/**UUID*/
	private String uuid;  
	
	/**第一装港*/
	private String loadPort1;  
	
	/**第二装港*/
	private String loadPort2;  
	
	/**第三装港*/
	private String loadPort3;  
	
	/**第一卸港*/
	private String unloadPort1;  
	
	/**第二卸港*/
	private String unloadPort2;  
	
	/**第三卸港*/
	private String unloadPort3;  
	
	/**年份*/
	private Integer year;  
	
	/**费率*/
	private java.math.BigDecimal price;  
	
	/**里程数*/
	private java.math.BigDecimal mileage;  
	
	/**注意事项*/
	private String remark;  
	
	/**路线选择*/
	private String remark2;  
	
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
	
	/**扩展字段1*/
	private String ext1;  
	
		/**业务唯一键*/
	public void setBasicTariffId(Long basicTariffId){
		this.basicTariffId=basicTariffId;
	}
	/**业务唯一键*/
	public Long getBasicTariffId(){
		return this.basicTariffId;
	}
	
	/**UUID*/
	public void setUuid(String uuid){
		this.uuid=uuid;
	}
	/**UUID*/
	public String getUuid(){
		return this.uuid;
	}
	
	/**第一装港*/
	public void setLoadPort1(String loadPort1){
		this.loadPort1=loadPort1;
	}
	/**第一装港*/
	public String getLoadPort1(){
		return this.loadPort1;
	}
	
	/**第二装港*/
	public void setLoadPort2(String loadPort2){
		this.loadPort2=loadPort2;
	}
	/**第二装港*/
	public String getLoadPort2(){
		return this.loadPort2;
	}
	
	/**第三装港*/
	public void setLoadPort3(String loadPort3){
		this.loadPort3=loadPort3;
	}
	/**第三装港*/
	public String getLoadPort3(){
		return this.loadPort3;
	}
	
	/**第一卸港*/
	public void setUnloadPort1(String unloadPort1){
		this.unloadPort1=unloadPort1;
	}
	/**第一卸港*/
	public String getUnloadPort1(){
		return this.unloadPort1;
	}
	
	/**第二卸港*/
	public void setUnloadPort2(String unloadPort2){
		this.unloadPort2=unloadPort2;
	}
	/**第二卸港*/
	public String getUnloadPort2(){
		return this.unloadPort2;
	}
	
	/**第三卸港*/
	public void setUnloadPort3(String unloadPort3){
		this.unloadPort3=unloadPort3;
	}
	/**第三卸港*/
	public String getUnloadPort3(){
		return this.unloadPort3;
	}
	
	/**年份*/
	public void setYear(Integer year){
		this.year=year;
	}
	/**年份*/
	public Integer getYear(){
		return this.year;
	}
	
	/**费率*/
	public void setPrice(java.math.BigDecimal price){
		this.price=price;
	}
	/**费率*/
	public java.math.BigDecimal getPrice(){
		return this.price;
	}
	
	/**里程数*/
	public void setMileage(java.math.BigDecimal mileage){
		this.mileage=mileage;
	}
	/**里程数*/
	public java.math.BigDecimal getMileage(){
		return this.mileage;
	}
	
	/**注意事项*/
	public void setRemark(String remark){
		this.remark=remark;
	}
	/**注意事项*/
	public String getRemark(){
		return this.remark;
	}
	
	/**路线选择*/
	public void setRemark2(String remark2){
		this.remark2=remark2;
	}
	/**路线选择*/
	public String getRemark2(){
		return this.remark2;
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
	
	/**地区*/
	public String getRegion() {
		return region;
	}
	/**地区*/
	public void setRegion(String region) {
		this.region = region;
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
		map.put("basicTariffId",this.basicTariffId);
	map.put("uuid",this.uuid);
	map.put("loadPort1",this.loadPort1);
	map.put("loadPort2",this.loadPort2);
	map.put("loadPort3",this.loadPort3);
	map.put("unloadPort1",this.unloadPort1);
	map.put("unloadPort2",this.unloadPort2);
	map.put("unloadPort3",this.unloadPort3);
	map.put("year",this.year);
	map.put("price",this.price);
	map.put("mileage",this.mileage);
	map.put("remark",this.remark);
	map.put("remark2",this.remark2);
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