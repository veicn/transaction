package com.sinochem.crude.trade.orderexecute.remote;

import java.io.Serializable;

/**
 * 装港信息
 * @author wangxinran
 *
 */
public class TransportLoadVO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5070754668512439034L;

	/**订单编号*/
	private String orderNo; 
	
	/**装港*/
	private String loadPort;  
	
	/**ETA*/
	private java.util.Date eta;  
	
	/**油种*/
	private String oilType;
	
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
	
	/**装港船代*/
	private String agency;
	
	/**提单日*/
	private java.util.Date blDate;  
	
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
	
	/**短量比例（桶）*/
	private java.math.BigDecimal ratioBarrel;  
	
	/**短量比例（吨）*/
	private java.math.BigDecimal ratioTonnage;  
	
	/**装船数量*/
	private java.math.BigDecimal quantity;  
	
	/**备注*/
	private String remark;  
	
	/** 代理协议UUID */
	private String argeementUuid;

	/** 船舶类型 */
	private String type;

	/** 船舶名称 */
	private String name;
	
	/**订单编号*/
	public void setOrderNo(String orderNo){
		this.orderNo=orderNo;
	}
	/**订单编号*/
	public String getOrderNo(){
		return this.orderNo;
	}
	
	/**装港*/
	public void setLoadPort(String loadPort){
		this.loadPort=loadPort;
	}
	/**装港*/
	public String getLoadPort(){
		return this.loadPort;
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
	/**油种*/
	public String getOilType() {
		return oilType;
	}
	/**油种*/
	public void setOilType(String oilType) {
		this.oilType = oilType;
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
	
	/**装港船代*/
	public void setAgency(String agency){
		this.agency=agency;
	}
	/**装港船代*/
	public String getAgency(){
		return this.agency;
	}
	
	/**提单日*/
	public void setBlDate(java.util.Date blDate){
		this.blDate=blDate;
	}
	/**提单日*/
	public java.util.Date getBlDate(){
		return this.blDate;
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
	
	/** 代理协议UUID */
	public void setArgeementUuid(String argeementUuid) {
		this.argeementUuid = argeementUuid;
	}

	/** 代理协议UUID */
	public String getArgeementUuid() {
		return this.argeementUuid;
	}

	/** 船舶类型 */
	public void setType(String type) {
		this.type = type;
	}

	/** 船舶类型 */
	public String getType() {
		return this.type;
	}

	/** 船舶名称 */
	public void setName(String name) {
		this.name = name;
	}

	/** 船舶名称 */
	public String getName() {
		return this.name;
	}
}
