package com.sinochem.crude.trade.transport.remote;

import java.io.Serializable;

public class LoadGoodsVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**装港*/
	private String loadPort;  
	
	/**油种*/
	private String oilType;  
	
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
	
	/**短量比例（桶）*/
	private java.math.BigDecimal ratioBarrel;  
	
	/**短量比例（吨）*/
	private java.math.BigDecimal ratioTonnage;  
	
	/**装船数量*/
	private java.math.BigDecimal quantity;  
	
	/**备注*/
	private String remark;  
	
	/**船量毛体积*/
	private java.math.BigDecimal shGrossCubicMeter;  
	
	/**船量净体积*/
	private java.math.BigDecimal shNetCubicMeter;  
	
	/**船量密度*/
	private java.math.BigDecimal shDensity;  
	
	/**装港*/
	public void setLoadPort(String loadPort){
		this.loadPort=loadPort;
	}
	/**装港*/
	public String getLoadPort(){
		return this.loadPort;
	}
	
	/**油种*/
	public void setOilType(String oilType){
		this.oilType=oilType;
	}
	/**油种*/
	public String getOilType(){
		return this.oilType;
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
	
}