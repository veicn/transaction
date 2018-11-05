package com.sinochem.crude.trade.orderexecute.domain;

import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;

public class OrderShipLoadinginfo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**业务唯一键*/
	private Long id;  
	
	/**UUID*/
	private String uuid;  
	
	/**订单编号*/
	private String orderNo;  
	
	/**运单uuid*/
	private String waybillUuid;  
	
	/**装港*/
	private String loadPort;  
	
	/**油种*/
	private String oil;  
	
	/**ETA*/
	private java.util.Date eta;  
	
	/**预计靠泊时间*/
	private java.util.Date exBerth;  
	
	/**NOR递交时间*/
	private java.util.Date norDate;  
	
	/**迎水上船时间*/
	private java.util.Date waterDate;  
	
	/**起锚时间*/
	private java.util.Date atripDate;  
	
	/**靠泊完成时间*/
	private java.util.Date berthDate;  
	
	/**实际装货开始时间*/
	private java.util.Date acStart;  
	
	/**实际装货完成时间*/
	private java.util.Date acFinish;  
	
	/**预计离港时间*/
	private java.util.Date exLeave;  
	
	/**实际离港时间*/
	private java.util.Date acLeave;  
	
	/**拆管时间*/
	private java.util.Date remTubeDate;  
	
	/**装港商检*/
	private String inspection;  
	
	/**商检公司ID*/
	private Long inspectionMemberid;  
	
	/**码头*/
	private String inspectionTerminal;  
	
	/**商检联系人*/
	private String inspectionContact;  
	
	/**商检指定联系人邮箱*/
	private String inspectionEmail;  
	
	/**商检指定联系人电话*/
	private String inspectionTel;  
	
	/**商检详情*/
	private String inspectionContent;  
	
	/**装港船代*/
	private String agency;  
	
	/**公司id*/
	private Long epMemberId;  
	
	/**提单日*/
	private java.util.Date blDate;  
	
	/**提单日期状态*/
	private String blStatus;  
	
	/**提单净桶*/
	private java.math.BigDecimal blNightstool;  
	
	/**提单毛桶*/
	private java.math.BigDecimal blHairBarrel;  
	
	/**提单净吨*/
	private java.math.BigDecimal blNetTonnage;  
	
	/**提单毛吨*/
	private java.math.BigDecimal blHairTonnage;  
	
	/**API*/
	private java.math.BigDecimal api;  
	
	/**水杂含量*/
	private java.math.BigDecimal waterImpCon;  
	
	/**装港明水数量*/
	private java.math.BigDecimal waterQuantity;  
	
	/**船量毛桶*/
	private java.math.BigDecimal shHairBarrel;  
	
	/**船量毛吨*/
	private java.math.BigDecimal shHairTonnage;  
	
	/**装船量（净桶）*/
	private java.math.BigDecimal shNetBarrel;  
	
	/**装船量（净吨）*/
	private java.math.BigDecimal shNetTonnage;  
	
	/**船量毛体积*/
	private java.math.BigDecimal shGrossCubicMeter;  
	
	/**船量净体积*/
	private java.math.BigDecimal shNetCubicMeter;  
	
	/**船量密度*/
	private java.math.BigDecimal shDensity;  
	
	/**短量比例（桶）*/
	private java.math.BigDecimal ratioBarrel;  
	
	/**短量比例（吨）*/
	private java.math.BigDecimal ratioTonnage;  
	
	/**装船数量*/
	private java.math.BigDecimal quantity;  
	
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
	
		/**业务唯一键*/
	public void setId(Long id){
		this.id=id;
	}
	/**业务唯一键*/
	public Long getId(){
		return this.id;
	}
	
	/**UUID*/
	public void setUuid(String uuid){
		this.uuid=uuid;
	}
	/**UUID*/
	public String getUuid(){
		return this.uuid;
	}
	
	/**订单编号*/
	public void setOrderNo(String orderNo){
		this.orderNo=orderNo;
	}
	/**订单编号*/
	public String getOrderNo(){
		return this.orderNo;
	}
	
	/**运单uuid*/
	public void setWaybillUuid(String waybillUuid){
		this.waybillUuid=waybillUuid;
	}
	/**运单uuid*/
	public String getWaybillUuid(){
		return this.waybillUuid;
	}
	
	/**装港*/
	public void setLoadPort(String loadPort){
		this.loadPort=loadPort;
	}
	/**装港*/
	public String getLoadPort(){
		return this.loadPort;
	}
	
	/**油种*/
	public void setOil(String oil){
		this.oil=oil;
	}
	/**油种*/
	public String getOil(){
		return this.oil;
	}
	
	/**ETA*/
	public void setEta(java.util.Date eta){
		this.eta=eta;
	}
	/**ETA*/
	public java.util.Date getEta(){
		return this.eta;
	}
	
	/**预计靠泊时间*/
	public void setExBerth(java.util.Date exBerth){
		this.exBerth=exBerth;
	}
	/**预计靠泊时间*/
	public java.util.Date getExBerth(){
		return this.exBerth;
	}
	
	/**NOR递交时间*/
	public void setNorDate(java.util.Date norDate){
		this.norDate=norDate;
	}
	/**NOR递交时间*/
	public java.util.Date getNorDate(){
		return this.norDate;
	}
	
	/**迎水上船时间*/
	public void setWaterDate(java.util.Date waterDate){
		this.waterDate=waterDate;
	}
	/**迎水上船时间*/
	public java.util.Date getWaterDate(){
		return this.waterDate;
	}
	
	/**起锚时间*/
	public void setAtripDate(java.util.Date atripDate){
		this.atripDate=atripDate;
	}
	/**起锚时间*/
	public java.util.Date getAtripDate(){
		return this.atripDate;
	}
	
	/**靠泊完成时间*/
	public void setBerthDate(java.util.Date berthDate){
		this.berthDate=berthDate;
	}
	/**靠泊完成时间*/
	public java.util.Date getBerthDate(){
		return this.berthDate;
	}
	
	/**实际装货开始时间*/
	public void setAcStart(java.util.Date acStart){
		this.acStart=acStart;
	}
	/**实际装货开始时间*/
	public java.util.Date getAcStart(){
		return this.acStart;
	}
	
	/**实际装货完成时间*/
	public void setAcFinish(java.util.Date acFinish){
		this.acFinish=acFinish;
	}
	/**实际装货完成时间*/
	public java.util.Date getAcFinish(){
		return this.acFinish;
	}
	
	/**预计离港时间*/
	public void setExLeave(java.util.Date exLeave){
		this.exLeave=exLeave;
	}
	/**预计离港时间*/
	public java.util.Date getExLeave(){
		return this.exLeave;
	}
	
	/**实际离港时间*/
	public void setAcLeave(java.util.Date acLeave){
		this.acLeave=acLeave;
	}
	/**实际离港时间*/
	public java.util.Date getAcLeave(){
		return this.acLeave;
	}
	
	/**拆管时间*/
	public void setRemTubeDate(java.util.Date remTubeDate){
		this.remTubeDate=remTubeDate;
	}
	/**拆管时间*/
	public java.util.Date getRemTubeDate(){
		return this.remTubeDate;
	}
	
	/**装港商检*/
	public void setInspection(String inspection){
		this.inspection=inspection;
	}
	/**装港商检*/
	public String getInspection(){
		return this.inspection;
	}
	
	/**商检公司ID*/
	public void setInspectionMemberid(Long inspectionMemberid){
		this.inspectionMemberid=inspectionMemberid;
	}
	/**商检公司ID*/
	public Long getInspectionMemberid(){
		return this.inspectionMemberid;
	}
	
	/**码头*/
	public void setInspectionTerminal(String inspectionTerminal){
		this.inspectionTerminal=inspectionTerminal;
	}
	/**码头*/
	public String getInspectionTerminal(){
		return this.inspectionTerminal;
	}
	
	/**商检联系人*/
	public void setInspectionContact(String inspectionContact){
		this.inspectionContact=inspectionContact;
	}
	/**商检联系人*/
	public String getInspectionContact(){
		return this.inspectionContact;
	}
	
	/**商检指定联系人邮箱*/
	public void setInspectionEmail(String inspectionEmail){
		this.inspectionEmail=inspectionEmail;
	}
	/**商检指定联系人邮箱*/
	public String getInspectionEmail(){
		return this.inspectionEmail;
	}
	
	/**商检指定联系人电话*/
	public void setInspectionTel(String inspectionTel){
		this.inspectionTel=inspectionTel;
	}
	/**商检指定联系人电话*/
	public String getInspectionTel(){
		return this.inspectionTel;
	}
	
	/**商检详情*/
	public void setInspectionContent(String inspectionContent){
		this.inspectionContent=inspectionContent;
	}
	/**商检详情*/
	public String getInspectionContent(){
		return this.inspectionContent;
	}
	
	/**装港船代*/
	public void setAgency(String agency){
		this.agency=agency;
	}
	/**装港船代*/
	public String getAgency(){
		return this.agency;
	}
	
	/**公司id*/
	public void setEpMemberId(Long epMemberId){
		this.epMemberId=epMemberId;
	}
	/**公司id*/
	public Long getEpMemberId(){
		return this.epMemberId;
	}
	
	/**提单日*/
	public void setBlDate(java.util.Date blDate){
		this.blDate=blDate;
	}
	/**提单日*/
	public java.util.Date getBlDate(){
		return this.blDate;
	}
	
	/**提单日期状态*/
	public void setBlStatus(String blStatus){
		this.blStatus=blStatus;
	}
	/**提单日期状态*/
	public String getBlStatus(){
		return this.blStatus;
	}
	
	/**提单净桶*/
	public void setBlNightstool(java.math.BigDecimal blNightstool){
		this.blNightstool=blNightstool;
	}
	/**提单净桶*/
	public java.math.BigDecimal getBlNightstool(){
		return this.blNightstool;
	}
	
	/**提单毛桶*/
	public void setBlHairBarrel(java.math.BigDecimal blHairBarrel){
		this.blHairBarrel=blHairBarrel;
	}
	/**提单毛桶*/
	public java.math.BigDecimal getBlHairBarrel(){
		return this.blHairBarrel;
	}
	
	/**提单净吨*/
	public void setBlNetTonnage(java.math.BigDecimal blNetTonnage){
		this.blNetTonnage=blNetTonnage;
	}
	/**提单净吨*/
	public java.math.BigDecimal getBlNetTonnage(){
		return this.blNetTonnage;
	}
	
	/**提单毛吨*/
	public void setBlHairTonnage(java.math.BigDecimal blHairTonnage){
		this.blHairTonnage=blHairTonnage;
	}
	/**提单毛吨*/
	public java.math.BigDecimal getBlHairTonnage(){
		return this.blHairTonnage;
	}
	
	/**API*/
	public void setApi(java.math.BigDecimal api){
		this.api=api;
	}
	/**API*/
	public java.math.BigDecimal getApi(){
		return this.api;
	}
	
	/**水杂含量*/
	public void setWaterImpCon(java.math.BigDecimal waterImpCon){
		this.waterImpCon=waterImpCon;
	}
	/**水杂含量*/
	public java.math.BigDecimal getWaterImpCon(){
		return this.waterImpCon;
	}
	
	/**装港明水数量*/
	public void setWaterQuantity(java.math.BigDecimal waterQuantity){
		this.waterQuantity=waterQuantity;
	}
	/**装港明水数量*/
	public java.math.BigDecimal getWaterQuantity(){
		return this.waterQuantity;
	}
	
	/**船量毛桶*/
	public void setShHairBarrel(java.math.BigDecimal shHairBarrel){
		this.shHairBarrel=shHairBarrel;
	}
	/**船量毛桶*/
	public java.math.BigDecimal getShHairBarrel(){
		return this.shHairBarrel;
	}
	
	/**船量毛吨*/
	public void setShHairTonnage(java.math.BigDecimal shHairTonnage){
		this.shHairTonnage=shHairTonnage;
	}
	/**船量毛吨*/
	public java.math.BigDecimal getShHairTonnage(){
		return this.shHairTonnage;
	}
	
	/**装船量（净桶）*/
	public void setShNetBarrel(java.math.BigDecimal shNetBarrel){
		this.shNetBarrel=shNetBarrel;
	}
	/**装船量（净桶）*/
	public java.math.BigDecimal getShNetBarrel(){
		return this.shNetBarrel;
	}
	
	/**装船量（净吨）*/
	public void setShNetTonnage(java.math.BigDecimal shNetTonnage){
		this.shNetTonnage=shNetTonnage;
	}
	/**装船量（净吨）*/
	public java.math.BigDecimal getShNetTonnage(){
		return this.shNetTonnage;
	}
	
	/**船量毛体积*/
	public void setShGrossCubicMeter(java.math.BigDecimal shGrossCubicMeter){
		this.shGrossCubicMeter=shGrossCubicMeter;
	}
	/**船量毛体积*/
	public java.math.BigDecimal getShGrossCubicMeter(){
		return this.shGrossCubicMeter;
	}
	
	/**船量净体积*/
	public void setShNetCubicMeter(java.math.BigDecimal shNetCubicMeter){
		this.shNetCubicMeter=shNetCubicMeter;
	}
	/**船量净体积*/
	public java.math.BigDecimal getShNetCubicMeter(){
		return this.shNetCubicMeter;
	}
	
	/**船量密度*/
	public void setShDensity(java.math.BigDecimal shDensity){
		this.shDensity=shDensity;
	}
	/**船量密度*/
	public java.math.BigDecimal getShDensity(){
		return this.shDensity;
	}
	
	/**短量比例（桶）*/
	public void setRatioBarrel(java.math.BigDecimal ratioBarrel){
		this.ratioBarrel=ratioBarrel;
	}
	/**短量比例（桶）*/
	public java.math.BigDecimal getRatioBarrel(){
		return this.ratioBarrel;
	}
	
	/**短量比例（吨）*/
	public void setRatioTonnage(java.math.BigDecimal ratioTonnage){
		this.ratioTonnage=ratioTonnage;
	}
	/**短量比例（吨）*/
	public java.math.BigDecimal getRatioTonnage(){
		return this.ratioTonnage;
	}
	
	/**装船数量*/
	public void setQuantity(java.math.BigDecimal quantity){
		this.quantity=quantity;
	}
	/**装船数量*/
	public java.math.BigDecimal getQuantity(){
		return this.quantity;
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
	
		
	
	public Map<String, Object> toMap() {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id",this.id);
	map.put("uuid",this.uuid);
	map.put("orderNo",this.orderNo);
	map.put("waybillUuid",this.waybillUuid);
	map.put("loadPort",this.loadPort);
	map.put("oil",this.oil);
	map.put("eta",this.eta);
	map.put("exBerth",this.exBerth);
	map.put("norDate",this.norDate);
	map.put("waterDate",this.waterDate);
	map.put("atripDate",this.atripDate);
	map.put("berthDate",this.berthDate);
	map.put("acStart",this.acStart);
	map.put("acFinish",this.acFinish);
	map.put("exLeave",this.exLeave);
	map.put("acLeave",this.acLeave);
	map.put("remTubeDate",this.remTubeDate);
	map.put("inspection",this.inspection);
	map.put("inspectionMemberid",this.inspectionMemberid);
	map.put("inspectionTerminal",this.inspectionTerminal);
	map.put("inspectionContact",this.inspectionContact);
	map.put("inspectionEmail",this.inspectionEmail);
	map.put("inspectionTel",this.inspectionTel);
	map.put("inspectionContent",this.inspectionContent);
	map.put("agency",this.agency);
	map.put("epMemberId",this.epMemberId);
	map.put("blDate",this.blDate);
	map.put("blStatus",this.blStatus);
	map.put("blNightstool",this.blNightstool);
	map.put("blHairBarrel",this.blHairBarrel);
	map.put("blNetTonnage",this.blNetTonnage);
	map.put("blHairTonnage",this.blHairTonnage);
	map.put("api",this.api);
	map.put("waterImpCon",this.waterImpCon);
	map.put("waterQuantity",this.waterQuantity);
	map.put("shHairBarrel",this.shHairBarrel);
	map.put("shHairTonnage",this.shHairTonnage);
	map.put("shNetBarrel",this.shNetBarrel);
	map.put("shNetTonnage",this.shNetTonnage);
	map.put("shGrossCubicMeter",this.shGrossCubicMeter);
	map.put("shNetCubicMeter",this.shNetCubicMeter);
	map.put("shDensity",this.shDensity);
	map.put("ratioBarrel",this.ratioBarrel);
	map.put("ratioTonnage",this.ratioTonnage);
	map.put("quantity",this.quantity);
	map.put("remark",this.remark);
	map.put("aliveFlag",this.aliveFlag);
	map.put("version",this.version);
	map.put("langVer",this.langVer);
	map.put("createDate",this.createDate);
	map.put("updateDate",this.updateDate);
	map.put("createUser",this.createUser);
	map.put("updateUser",this.updateUser);
			return map;
	}
}