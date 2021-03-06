package com.sinochem.crude.trade.shiprefueling.domain.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;

public class HaseInfo implements Serializable {

	private static final long serialVersionUID = 1L;


	/** 已发布*/
	public static final String STATUS_PUBLISHED = "1";
	/** 已确认*/
	public static final String STATUS_CONFIRMED = "2";
	/** 已撤销*/
	public static final String STATUS_DELETED = "3";

	/**业务唯一键*/
	private Long infoId;  
	
	/**UUID*/
	private String uuid;  
	
	/**信息类型(1:船燃采购 2:船油采购)*/
	private String infoType;  
	
	/**信息标题*/
	private String infoTitle;  
	
	/**油品分类*/
	private String oilClassification;  
	
	/**品种*/
	private String oilVarieties;

	/**发布日期*/
	private Date releasedDate;
	
	/**交货方式(1:送供 2:自提)*/
	private String deliveryWay;

	/**所在地区省*/
	private String locationProvince;

	/**所在地区市*/
	private String locationCity;

	/**单位*/
	private String unit;

	/**单价*/
	private java.math.BigDecimal unitPrice;

	/**数量*/
	private java.math.BigDecimal number;  
	
	/**详细说明*/
	private String detailedDescription;  
	
	/**港口*/
	private String port;  
	
	/**运输方式*/
	private String transportWay;  
	
	/**会员公司id*/
	private Long epMemberId;  
	
	/**会员公司名称*/
	private String epMemberName;  
	
	/**是否有效(1有效0无效)*/
	private String aliveFlag;  
	
	/**版本号*/
	private String version;  
	
	/**语言版本*/
	private String langVer;  
	
	/**创建时间*/
	private Date createDate;
	
	/**修改时间*/
	private Date updateDate;
	
	/**创建人*/
	private Long createUser;  
	
	/**修改人*/
	private Long updateUser;  
	
	/**扩展字段1*/
	private String ext1;  
	
	/**状态*/
	private String status;


	//ADD_油品名称,规格名称_wdh_20180802_Start
	private String ypname;

	private String ggname;

	public String getYpname() {
		return ypname;
	}

	public void setYpname(String ypname) {
		this.ypname = ypname;
	}

	public String getGgname() {
		return ggname;
	}

	public void setGgname(String ggname) {
		this.ggname = ggname;
	}
	//ADD_油品名称,规格名称_wdh_20180802_End

	/**业务唯一键*/
	public void setInfoId(Long infoId){
		this.infoId=infoId;
	}
	/**业务唯一键*/
	public Long getInfoId(){
		return this.infoId;
	}
	
	/**UUID*/
	public void setUuid(String uuid){
		this.uuid=uuid;
	}
	/**UUID*/
	public String getUuid(){
		return this.uuid;
	}
	
	/**信息类型(1:船燃采购 2:船油采购)*/
	public void setInfoType(String infoType){
		this.infoType=infoType;
	}
	/**信息类型(1:船燃采购 2:船油采购)*/
	public String getInfoType(){
		return this.infoType;
	}
	
	/**信息标题*/
	public void setInfoTitle(String infoTitle){
		this.infoTitle=infoTitle;
	}
	/**信息标题*/
	public String getInfoTitle(){
		return this.infoTitle;
	}
	
	/**油品分类*/
	public void setOilClassification(String oilClassification){
		this.oilClassification=oilClassification;
	}
	/**油品分类*/
	public String getOilClassification(){
		return this.oilClassification;
	}
	
	/**品种*/
	public void setOilVarieties(String oilVarieties){
		this.oilVarieties=oilVarieties;
	}
	/**品种*/
	public String getOilVarieties(){
		return this.oilVarieties;
	}

	/**发布日期*/
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
	public Date getReleasedDate() {
		return releasedDate;
	}
	/**发布日期*/
	public void setReleasedDate(Date releasedDate) {
		this.releasedDate = releasedDate;
	}

	/**交货方式(1:送供 2:自提)*/
	public void setDeliveryWay(String deliveryWay){
		this.deliveryWay=deliveryWay;
	}
	/**交货方式(1:送供 2:自提)*/
	public String getDeliveryWay(){
		return this.deliveryWay;
	}

	public String getLocationProvince() {
		return locationProvince;
	}

	public void setLocationProvince(String locationProvince) {
		this.locationProvince = locationProvince;
	}

	public String getLocationCity() {
		return locationCity;
	}

	public void setLocationCity(String locationCity) {
		this.locationCity = locationCity;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	/**数量*/
	public void setNumber(java.math.BigDecimal number){
		this.number=number;
	}
	/**数量*/
	public java.math.BigDecimal getNumber(){
		return this.number;
	}
	
	/**详细说明*/
	public void setDetailedDescription(String detailedDescription){
		this.detailedDescription=detailedDescription;
	}
	/**详细说明*/
	public String getDetailedDescription(){
		return this.detailedDescription;
	}
	
	/**港口*/
	public void setPort(String port){
		this.port=port;
	}
	/**港口*/
	public String getPort(){
		return this.port;
	}
	
	/**运输方式*/
	public void setTransportWay(String transportWay){
		this.transportWay=transportWay;
	}
	/**运输方式*/
	public String getTransportWay(){
		return this.transportWay;
	}
	
	/**会员公司id*/
	public void setEpMemberId(Long epMemberId){
		this.epMemberId=epMemberId;
	}
	/**会员公司id*/
	public Long getEpMemberId(){
		return this.epMemberId;
	}
	
	/**会员公司名称*/
	public void setEpMemberName(String epMemberName){
		this.epMemberName=epMemberName;
	}
	/**会员公司名称*/
	public String getEpMemberName(){
		return this.epMemberName;
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
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
	public java.util.Date getCreateDate(){
		return this.createDate;
	}
	
	/**修改时间*/
	public void setUpdateDate(java.util.Date updateDate){
		this.updateDate=updateDate;
	}
	/**修改时间*/
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
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
	
	/**状态*/
	public void setStatus(String status){
		this.status=status;
	}
	/**状态*/
	public String getStatus(){
		return this.status;
	}
	
		
	
	public Map<String, Object> toMap() {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("infoId",this.infoId);
		map.put("uuid",this.uuid);
		map.put("infoType",this.infoType);
		map.put("infoTitle",this.infoTitle);
		map.put("oilClassification",this.oilClassification);
		map.put("oilVarieties",this.oilVarieties);
		map.put("releasedDate", this.releasedDate);
		map.put("deliveryWay",this.deliveryWay);
		map.put("locationProvince",this.locationProvince);
		map.put("locationCity",this.locationCity);
		map.put("unit",this.unit);
		map.put("unitPrice",this.unitPrice);
		map.put("number",this.number);
		map.put("detailedDescription",this.detailedDescription);
		map.put("port",this.port);
		map.put("transportWay",this.transportWay);
		map.put("epMemberId",this.epMemberId);
		map.put("epMemberName",this.epMemberName);
		map.put("aliveFlag",this.aliveFlag);
		map.put("version",this.version);
		map.put("langVer",this.langVer);
		map.put("createDate",this.createDate);
		map.put("updateDate",this.updateDate);
		map.put("createUser",this.createUser);
		map.put("updateUser",this.updateUser);
		map.put("ext1",this.ext1);
		map.put("status",this.status);
		map.put("ypname",this.ypname);
		map.put("ggname",this.ggname);
		return map;
	}

}