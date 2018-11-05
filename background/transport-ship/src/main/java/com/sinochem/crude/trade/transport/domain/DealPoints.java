package com.sinochem.crude.trade.transport.domain;

import java.io.Serializable;

public class DealPoints implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**业务唯一键*/
	private Long dealPointsId;  
	
	/**UUID*/
	private String uuid;  
	
	/**地区*/
	private String region;  
	
	/**参考路线*/
	private String refRoute;  
	
	/**预估单桶运费*/
	private java.math.BigDecimal singleFare;  
	
	/**船型*/
	private String type;  
	
	/**价格*/
	private java.math.BigDecimal price;  
	
	/**名称*/
	private String name;  
	
	/**日期*/
	private java.util.Date date;  
	
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
	public void setDealPointsId(Long dealPointsId){
		this.dealPointsId=dealPointsId;
	}
	/**业务唯一键*/
	public Long getDealPointsId(){
		return this.dealPointsId;
	}
	
	/**UUID*/
	public void setUuid(String uuid){
		this.uuid=uuid;
	}
	/**UUID*/
	public String getUuid(){
		return this.uuid;
	}
	
	/**地区*/
	public void setRegion(String region){
		this.region=region;
	}
	/**地区*/
	public String getRegion(){
		return this.region;
	}
	
	/**参考路线*/
	public void setRefRoute(String refRoute){
		this.refRoute=refRoute;
	}
	/**参考路线*/
	public String getRefRoute(){
		return this.refRoute;
	}
	
	/**预估单桶运费*/
	public void setSingleFare(java.math.BigDecimal singleFare){
		this.singleFare=singleFare;
	}
	/**预估单桶运费*/
	public java.math.BigDecimal getSingleFare(){
		return this.singleFare;
	}
	
	/**船型*/
	public void setType(String type){
		this.type=type;
	}
	/**船型*/
	public String getType(){
		return this.type;
	}
	
	/**价格*/
	public void setPrice(java.math.BigDecimal price){
		this.price=price;
	}
	/**价格*/
	public java.math.BigDecimal getPrice(){
		return this.price;
	}
	
	/**名称*/
	public void setName(String name){
		this.name=name;
	}
	/**名称*/
	public String getName(){
		return this.name;
	}
	
	/**日期*/
	public void setDate(java.util.Date date){
		this.date=date;
	}
	/**日期*/
	public java.util.Date getDate(){
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
		map.put("dealPointsId",this.dealPointsId);
	map.put("uuid",this.uuid);
	map.put("region",this.region);
	map.put("refRoute",this.refRoute);
	map.put("singleFare",this.singleFare);
	map.put("type",this.type);
	map.put("price",this.price);
	map.put("name",this.name);
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