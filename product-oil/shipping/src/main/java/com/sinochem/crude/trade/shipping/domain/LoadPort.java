package com.sinochem.crude.trade.shipping.domain;

import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;

public class LoadPort implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**主键_ID*/
	private Long shipLoadPortId;  
	
	/**UUID*/
	private String uuid;  
	
	/**船舶确认单ID*/
	private Long shipConfirmationSheetId;  
	
	/**船名*/
	private String vesselName;  
	
	/**装货港*/
	private String portOfLoading;  
	
	/**船舶标识符*/
	private String imo;  
	
	/**商品*/
	private String product;  
	
	/**损耗值*/
	private String quantityTons;  
	
	/**量*/
	private java.math.BigDecimal quantity;  
	
	/**预记装载时间开始*/
	private java.util.Date laycanStart;  
	
	/**预记装载时间结束*/
	private java.util.Date laycanEnd;  
	
	/**船舶准备就绪日期*/
	private java.util.Date norTendered;  
	
	/**下锚日期*/
	private java.util.Date anchorAweigh;  
	
	/**引水员登船时间*/
	private java.util.Date pobDatetimeOne;  
	
	/**停船位*/
	private java.math.BigDecimal pobBerth;  
	
	/**第一根缆绳上岸时间*/
	private java.util.Date firstLineDatetime;  
	
	/**预计靠泊时间*/
	private java.util.Date firstLineEtb;  
	
	/**停船位*/
	private Long firstLineBerth;  
	
	/**全速*/
	private java.util.Date allFast;  
	
	/**储罐检测预约时间*/
	private java.util.Date tanksInspectionDatetime;  
	
	/**储罐检测备注*/
	private String tanksInspectionRemarks;  
	
	/**商检*/
	private String independentInspection;  
	
	/**商检日期*/
	private java.util.Date independentInspectionDatetime;  
	
	/**商检说明*/
	private String independentInspectionRemarks;  
	
	/**船载数量*/
	private java.math.BigDecimal independentInspectionObq;  
	
	/**接臂*/
	private java.util.Date shoreArmsConnecting;  
	
	/**开始装载时间*/
	private java.util.Date commencedLoadingDatetime;  
	
	/**预计装完时间*/
	private java.util.Date etc;  
	
	/**实际装载完成时间*/
	private java.util.Date completedLoadingDatetime;  
	
	/**拆臂扫管线日期*/
	private java.util.Date shoreArmDisconnecting;  
	
	/**商检上船*/
	private java.util.Date cargoSurvey;  
	
	/**提单公吨*/
	private java.math.BigDecimal blMetricTons;  
	
	/**提单长吨*/
	private java.math.BigDecimal blLongTons;  
	
	/***/
	private java.math.BigDecimal ltr;  
	
	/***/
	private java.math.BigDecimal bbls;  
	
	/**出港时间*/
	private java.util.Date leaveDatetime;  
	
	/**下个港口ID*/
	private String nextPortId;  
	
	/**下个港口-目的地*/
	private String nextPort;  
	
	/**步骤 到第几步*/
	private Long step;  
	
	/**预留字段1*/
	private String ext1;  
	
	/**版本号*/
	private String version;  
	
	/**当前有效状态*/
	private String aliveFlag;  
	
	/**创建时间*/
	private java.util.Date createDate;  
	
	/**创建者*/
	private Long createUser;  
	
	/**更新时间*/
	private java.util.Date updateDate;  
	
	/**更新者*/
	private Long updateUser;  
	/**
	 * 船舶确认单uuid
	 */
	private String comUuid;
	
		public String getComUuid() {
		return comUuid;
	}
	public void setComUuid(String comUuid) {
		this.comUuid = comUuid;
	}
		/**主键_ID*/
	public void setShipLoadPortId(Long shipLoadPortId){
		this.shipLoadPortId=shipLoadPortId;
	}
	/**主键_ID*/
	public Long getShipLoadPortId(){
		return this.shipLoadPortId;
	}
	
	/**UUID*/
	public void setUuid(String uuid){
		this.uuid=uuid;
	}
	/**UUID*/
	public String getUuid(){
		return this.uuid;
	}
	
	/**船舶确认单ID*/
	public void setShipConfirmationSheetId(Long shipConfirmationSheetId){
		this.shipConfirmationSheetId=shipConfirmationSheetId;
	}
	/**船舶确认单ID*/
	public Long getShipConfirmationSheetId(){
		return this.shipConfirmationSheetId;
	}
	
	/***/
	public void setVesselName(String vesselName){
		this.vesselName=vesselName;
	}
	/***/
	public String getVesselName(){
		return this.vesselName;
	}
	
	/***/
	public void setPortOfLoading(String portOfLoading){
		this.portOfLoading=portOfLoading;
	}
	/***/
	public String getPortOfLoading(){
		return this.portOfLoading;
	}
	
	/***/
	public void setImo(String imo){
		this.imo=imo;
	}
	/***/
	public String getImo(){
		return this.imo;
	}
	
	/***/
	public void setProduct(String product){
		this.product=product;
	}
	/***/
	public String getProduct(){
		return this.product;
	}
	
	/***/
	public void setQuantityTons(String quantityTons){
		this.quantityTons=quantityTons;
	}
	/***/
	public String getQuantityTons(){
		return this.quantityTons;
	}
	
	public java.math.BigDecimal getQuantity() {
		return quantity;
	}
	public void setQuantity(java.math.BigDecimal quantity) {
		this.quantity = quantity;
	}
	/**预记装载时间开始*/
	public void setLaycanStart(java.util.Date laycanStart){
		this.laycanStart=laycanStart;
	}
	/**预记装载时间开始*/
	public java.util.Date getLaycanStart(){
		return this.laycanStart;
	}
	
	/**预记装载时间结束*/
	public void setLaycanEnd(java.util.Date laycanEnd){
		this.laycanEnd=laycanEnd;
	}
	/**预记装载时间结束*/
	public java.util.Date getLaycanEnd(){
		return this.laycanEnd;
	}
	
	/**船舶准备就绪日期*/
	public void setNorTendered(java.util.Date norTendered){
		this.norTendered=norTendered;
	}
	/**船舶准备就绪日期*/
	public java.util.Date getNorTendered(){
		return this.norTendered;
	}
	
	/**下锚日期*/
	public void setAnchorAweigh(java.util.Date anchorAweigh){
		this.anchorAweigh=anchorAweigh;
	}
	/**下锚日期*/
	public java.util.Date getAnchorAweigh(){
		return this.anchorAweigh;
	}
	
	public java.util.Date getPobDatetimeOne() {
		return pobDatetimeOne;
	}
	public void setPobDatetimeOne(java.util.Date pobDatetimeOne) {
		this.pobDatetimeOne = pobDatetimeOne;
	}
	
	
	public java.math.BigDecimal getPobBerth() {
		return pobBerth;
	}
	public void setPobBerth(java.math.BigDecimal pobBerth) {
		this.pobBerth = pobBerth;
	}
	/**第一根缆绳上岸时间*/
	public void setFirstLineDatetime(java.util.Date firstLineDatetime){
		this.firstLineDatetime=firstLineDatetime;
	}
	/**第一根缆绳上岸时间*/
	public java.util.Date getFirstLineDatetime(){
		return this.firstLineDatetime;
	}
	
	/**预计靠泊时间*/
	public void setFirstLineEtb(java.util.Date firstLineEtb){
		this.firstLineEtb=firstLineEtb;
	}
	/**预计靠泊时间*/
	public java.util.Date getFirstLineEtb(){
		return this.firstLineEtb;
	}
	
	/**停船位*/
	public void setFirstLineBerth(Long firstLineBerth){
		this.firstLineBerth=firstLineBerth;
	}
	/**停船位*/
	public Long getFirstLineBerth(){
		return this.firstLineBerth;
	}
	
	/**全速*/
	public void setAllFast(java.util.Date allFast){
		this.allFast=allFast;
	}
	/**全速*/
	public java.util.Date getAllFast(){
		return this.allFast;
	}
	
	/**储罐检测预约时间*/
	public void setTanksInspectionDatetime(java.util.Date tanksInspectionDatetime){
		this.tanksInspectionDatetime=tanksInspectionDatetime;
	}
	/**储罐检测预约时间*/
	public java.util.Date getTanksInspectionDatetime(){
		return this.tanksInspectionDatetime;
	}
	
	/**储罐检测备注*/
	public void setTanksInspectionRemarks(String tanksInspectionRemarks){
		this.tanksInspectionRemarks=tanksInspectionRemarks;
	}
	/**储罐检测备注*/
	public String getTanksInspectionRemarks(){
		return this.tanksInspectionRemarks;
	}
	
	/**商检*/
	public void setIndependentInspection(String independentInspection){
		this.independentInspection=independentInspection;
	}
	/**商检*/
	public String getIndependentInspection(){
		return this.independentInspection;
	}
	
	/**商检日期*/
	public void setIndependentInspectionDatetime(java.util.Date independentInspectionDatetime){
		this.independentInspectionDatetime=independentInspectionDatetime;
	}
	/**商检日期*/
	public java.util.Date getIndependentInspectionDatetime(){
		return this.independentInspectionDatetime;
	}
	
	/**商检说明*/
	public void setIndependentInspectionRemarks(String independentInspectionRemarks){
		this.independentInspectionRemarks=independentInspectionRemarks;
	}
	/**商检说明*/
	public String getIndependentInspectionRemarks(){
		return this.independentInspectionRemarks;
	}
	
	/**船载数量*/
	public void setIndependentInspectionObq(java.math.BigDecimal independentInspectionObq){
		this.independentInspectionObq=independentInspectionObq;
	}
	/**船载数量*/
	public java.math.BigDecimal getIndependentInspectionObq(){
		return this.independentInspectionObq;
	}
	
	/**接臂*/
	public void setShoreArmsConnecting(java.util.Date shoreArmsConnecting){
		this.shoreArmsConnecting=shoreArmsConnecting;
	}
	/**接臂*/
	public java.util.Date getShoreArmsConnecting(){
		return this.shoreArmsConnecting;
	}
	
	/**开始装载时间*/
	public void setCommencedLoadingDatetime(java.util.Date commencedLoadingDatetime){
		this.commencedLoadingDatetime=commencedLoadingDatetime;
	}
	/**开始装载时间*/
	public java.util.Date getCommencedLoadingDatetime(){
		return this.commencedLoadingDatetime;
	}
	
	/**预计装完时间*/
	public void setEtc(java.util.Date etc){
		this.etc=etc;
	}
	/**预计装完时间*/
	public java.util.Date getEtc(){
		return this.etc;
	}
	
	/**实际装载完成时间*/
	public void setCompletedLoadingDatetime(java.util.Date completedLoadingDatetime){
		this.completedLoadingDatetime=completedLoadingDatetime;
	}
	/**实际装载完成时间*/
	public java.util.Date getCompletedLoadingDatetime(){
		return this.completedLoadingDatetime;
	}
	
	/**拆臂扫管线日期*/
	public void setShoreArmDisconnecting(java.util.Date shoreArmDisconnecting){
		this.shoreArmDisconnecting=shoreArmDisconnecting;
	}
	/**拆臂扫管线日期*/
	public java.util.Date getShoreArmDisconnecting(){
		return this.shoreArmDisconnecting;
	}
	
	/**商检上船*/
	public void setCargoSurvey(java.util.Date cargoSurvey){
		this.cargoSurvey=cargoSurvey;
	}
	/**商检上船*/
	public java.util.Date getCargoSurvey(){
		return this.cargoSurvey;
	}
	
	/**提单公吨*/
	public void setBlMetricTons(java.math.BigDecimal blMetricTons){
		this.blMetricTons=blMetricTons;
	}
	/**提单公吨*/
	public java.math.BigDecimal getBlMetricTons(){
		return this.blMetricTons;
	}
	
	/**提单长吨*/
	public void setBlLongTons(java.math.BigDecimal blLongTons){
		this.blLongTons=blLongTons;
	}
	/**提单长吨*/
	public java.math.BigDecimal getBlLongTons(){
		return this.blLongTons;
	}
	
	/***/
	public void setLtr(java.math.BigDecimal ltr){
		this.ltr=ltr;
	}
	/***/
	public java.math.BigDecimal getLtr(){
		return this.ltr;
	}
	
	/***/
	public void setBbls(java.math.BigDecimal bbls){
		this.bbls=bbls;
	}
	/***/
	public java.math.BigDecimal getBbls(){
		return this.bbls;
	}
	
	/**出港时间*/
	public void setLeaveDatetime(java.util.Date leaveDatetime){
		this.leaveDatetime=leaveDatetime;
	}
	/**出港时间*/
	public java.util.Date getLeaveDatetime(){
		return this.leaveDatetime;
	}
	
	/**下个港口ID*/
	public void setNextPortId(String nextPortId){
		this.nextPortId=nextPortId;
	}
	/**下个港口ID*/
	public String getNextPortId(){
		return this.nextPortId;
	}
	
	/**下个港口-目的地*/
	public void setNextPort(String nextPort){
		this.nextPort=nextPort;
	}
	/**下个港口-目的地*/
	public String getNextPort(){
		return this.nextPort;
	}
	
	/**步骤 到第几步*/
	public void setStep(Long step){
		this.step=step;
	}
	/**步骤 到第几步*/
	public Long getStep(){
		return this.step;
	}
	
	/**预留字段1*/
	public void setExt1(String ext1){
		this.ext1=ext1;
	}
	/**预留字段1*/
	public String getExt1(){
		return this.ext1;
	}
	
	/**版本号*/
	public void setVersion(String version){
		this.version=version;
	}
	/**版本号*/
	public String getVersion(){
		return this.version;
	}
	
	/**当前有效状态*/
	public void setAliveFlag(String aliveFlag){
		this.aliveFlag=aliveFlag;
	}
	/**当前有效状态*/
	public String getAliveFlag(){
		return this.aliveFlag;
	}
	
	/**创建时间*/
	public void setCreateDate(java.util.Date createDate){
		this.createDate=createDate;
	}
	/**创建时间*/
	public java.util.Date getCreateDate(){
		return this.createDate;
	}
	
	/**创建者*/
	public void setCreateUser(Long createUser){
		this.createUser=createUser;
	}
	/**创建者*/
	public Long getCreateUser(){
		return this.createUser;
	}
	
	/**更新时间*/
	public void setUpdateDate(java.util.Date updateDate){
		this.updateDate=updateDate;
	}
	/**更新时间*/
	public java.util.Date getUpdateDate(){
		return this.updateDate;
	}
	
	/**更新者*/
	public void setUpdateUser(Long updateUser){
		this.updateUser=updateUser;
	}
	/**更新者*/
	public Long getUpdateUser(){
		return this.updateUser;
	}
	
		
	
	public Map<String, Object> toMap() {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("shipLoadPortId",this.shipLoadPortId);
	map.put("uuid",this.uuid);
	map.put("shipConfirmationSheetId",this.shipConfirmationSheetId);
	map.put("vesselName",this.vesselName);
	map.put("portOfLoading",this.portOfLoading);
	map.put("imo",this.imo);
	map.put("product",this.product);
	map.put("quantityTons",this.quantityTons);
	map.put("quantity",this.quantity);
	map.put("laycanStart",this.laycanStart);
	map.put("laycanEnd",this.laycanEnd);
	map.put("norTendered",this.norTendered);
	map.put("anchorAweigh",this.anchorAweigh);
	map.put("pobDatetimeOne",this.pobDatetimeOne);
	map.put("pobBerth",this.pobBerth);
	map.put("firstLineDatetime",this.firstLineDatetime);
	map.put("firstLineEtb",this.firstLineEtb);
	map.put("firstLineBerth",this.firstLineBerth);
	map.put("allFast",this.allFast);
	map.put("tanksInspectionDatetime",this.tanksInspectionDatetime);
	map.put("tanksInspectionRemarks",this.tanksInspectionRemarks);
	map.put("independentInspection",this.independentInspection);
	map.put("independentInspectionDatetime",this.independentInspectionDatetime);
	map.put("independentInspectionRemarks",this.independentInspectionRemarks);
	map.put("independentInspectionObq",this.independentInspectionObq);
	map.put("shoreArmsConnecting",this.shoreArmsConnecting);
	map.put("commencedLoadingDatetime",this.commencedLoadingDatetime);
	map.put("etc",this.etc);
	map.put("completedLoadingDatetime",this.completedLoadingDatetime);
	map.put("shoreArmDisconnecting",this.shoreArmDisconnecting);
	map.put("cargoSurvey",this.cargoSurvey);
	map.put("blMetricTons",this.blMetricTons);
	map.put("blLongTons",this.blLongTons);
	map.put("ltr",this.ltr);
	map.put("bbls",this.bbls);
	map.put("leaveDatetime",this.leaveDatetime);
	map.put("nextPortId",this.nextPortId);
	map.put("nextPort",this.nextPort);
	map.put("step",this.step);
	map.put("ext1",this.ext1);
	map.put("version",this.version);
	map.put("aliveFlag",this.aliveFlag);
	map.put("createDate",this.createDate);
	map.put("createUser",this.createUser);
	map.put("updateDate",this.updateDate);
	map.put("updateUser",this.updateUser);
			return map;
	}
	
}