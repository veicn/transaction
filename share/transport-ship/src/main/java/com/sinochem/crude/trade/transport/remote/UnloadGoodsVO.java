package com.sinochem.crude.trade.transport.remote;

import java.io.Serializable;

public class UnloadGoodsVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**卸港*/
	private String unloadPort;  
	
	/**油种*/
	private String oilType;  
	
	/**卸港船毛桶*/
	private java.math.BigDecimal shipHairBar;  
	
	/**卸港船毛吨*/
	private java.math.BigDecimal shipHairTon;  
	
	/**卸港商检毛桶*/
	private java.math.BigDecimal commHairBar;  
	
	/**卸港商检毛吨*/
	private java.math.BigDecimal commHairTon;  
	
	/**卸港商检净桶*/
	private java.math.BigDecimal commCleanBar;  
	
	/**卸港商检净吨*/
	private java.math.BigDecimal commCleanTon;  
	
	/**卸港罐毛桶*/
	private java.math.BigDecimal potHairBar;  
	
	/**卸港罐毛吨*/
	private java.math.BigDecimal potHairTon;  
	
	/**提单短量（%毛桶）*/
	private java.math.BigDecimal blHairBarRate;  
	
	/**提单短量（%毛吨）*/
	private java.math.BigDecimal blHairTonRate;  
	
	/**ROB桶（Reain on   board）*/
	private java.math.BigDecimal robQuanatity;  
	
	/**备注*/
	private String remark;  
	/**COD*/
	private java.util.Date cod;  
	
	/**卸港船毛桶_VEF*/
	private java.math.BigDecimal shipHairBarVef;  
	
	/**卸港船毛吨_VEF*/
	private java.math.BigDecimal shipHairTonVef;  
	
	/**API*/
	private java.math.BigDecimal api;  
	
	/**水杂含量*/
	private java.math.BigDecimal waterImpCon;  
	
	/**装港明水数量*/
	private java.math.BigDecimal waterQuantity;  
	
	/**船量毛体积*/
	private java.math.BigDecimal shGrossCubicMeter;  
	
	/**船量净体积*/
	private java.math.BigDecimal shNetCubicMeter;  
	
	/**船量密度*/
	private java.math.BigDecimal shDensity;  
	/**卸港*/
	public void setUnloadPort(String unloadPort){
		this.unloadPort=unloadPort;
	}
	/**卸港*/
	public String getUnloadPort(){
		return this.unloadPort;
	}
	
	/**油种*/
	public void setOilType(String oilType){
		this.oilType=oilType;
	}
	/**油种*/
	public String getOilType(){
		return this.oilType;
	}
	
	/**卸港船毛桶*/
	public void setShipHairBar(java.math.BigDecimal shipHairBar){
		this.shipHairBar=shipHairBar;
	}
	/**卸港船毛桶*/
	public java.math.BigDecimal getShipHairBar(){
		return this.shipHairBar;
	}
	
	/**卸港船毛吨*/
	public void setShipHairTon(java.math.BigDecimal shipHairTon){
		this.shipHairTon=shipHairTon;
	}
	/**卸港船毛吨*/
	public java.math.BigDecimal getShipHairTon(){
		return this.shipHairTon;
	}
	
	/**卸港商检毛桶*/
	public void setCommHairBar(java.math.BigDecimal commHairBar){
		this.commHairBar=commHairBar;
	}
	/**卸港商检毛桶*/
	public java.math.BigDecimal getCommHairBar(){
		return this.commHairBar;
	}
	
	/**卸港商检毛吨*/
	public void setCommHairTon(java.math.BigDecimal commHairTon){
		this.commHairTon=commHairTon;
	}
	/**卸港商检毛吨*/
	public java.math.BigDecimal getCommHairTon(){
		return this.commHairTon;
	}
	
	/**卸港商检净桶*/
	public void setCommCleanBar(java.math.BigDecimal commCleanBar){
		this.commCleanBar=commCleanBar;
	}
	/**卸港商检净桶*/
	public java.math.BigDecimal getCommCleanBar(){
		return this.commCleanBar;
	}
	
	/**卸港商检净吨*/
	public void setCommCleanTon(java.math.BigDecimal commCleanTon){
		this.commCleanTon=commCleanTon;
	}
	/**卸港商检净吨*/
	public java.math.BigDecimal getCommCleanTon(){
		return this.commCleanTon;
	}
	
	/**卸港罐毛桶*/
	public void setPotHairBar(java.math.BigDecimal potHairBar){
		this.potHairBar=potHairBar;
	}
	/**卸港罐毛桶*/
	public java.math.BigDecimal getPotHairBar(){
		return this.potHairBar;
	}
	
	/**卸港罐毛吨*/
	public void setPotHairTon(java.math.BigDecimal potHairTon){
		this.potHairTon=potHairTon;
	}
	/**卸港罐毛吨*/
	public java.math.BigDecimal getPotHairTon(){
		return this.potHairTon;
	}
	
	/**提单短量（%毛桶）*/
	public void setBlHairBarRate(java.math.BigDecimal blHairBarRate){
		this.blHairBarRate=blHairBarRate;
	}
	/**提单短量（%毛桶）*/
	public java.math.BigDecimal getBlHairBarRate(){
		return this.blHairBarRate;
	}
	
	/**提单短量（%毛吨）*/
	public void setBlHairTonRate(java.math.BigDecimal blHairTonRate){
		this.blHairTonRate=blHairTonRate;
	}
	/**提单短量（%毛吨）*/
	public java.math.BigDecimal getBlHairTonRate(){
		return this.blHairTonRate;
	}
	
	/**ROB桶（Reain on   board）*/
	public void setRobQuanatity(java.math.BigDecimal robQuanatity){
		this.robQuanatity=robQuanatity;
	}
	/**ROB桶（Reain on   board）*/
	public java.math.BigDecimal getRobQuanatity(){
		return this.robQuanatity;
	}
	
	/**备注*/
	public void setRemark(String remark){
		this.remark=remark;
	}
	/**备注*/
	public String getRemark(){
		return this.remark;
	}
	/**COD*/
	public void setCod(java.util.Date cod){
		this.cod=cod;
	}
	/**COD*/
	public java.util.Date getCod(){
		return this.cod;
	}
	
	/**卸港船毛桶_VEF*/
	public void setShipHairBarVef(java.math.BigDecimal shipHairBarVef){
		this.shipHairBarVef=shipHairBarVef;
	}
	/**卸港船毛桶_VEF*/
	public java.math.BigDecimal getShipHairBarVef(){
		return this.shipHairBarVef;
	}
	
	/**卸港船毛吨_VEF*/
	public void setShipHairTonVef(java.math.BigDecimal shipHairTonVef){
		this.shipHairTonVef=shipHairTonVef;
	}
	/**卸港船毛吨_VEF*/
	public java.math.BigDecimal getShipHairTonVef(){
		return this.shipHairTonVef;
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