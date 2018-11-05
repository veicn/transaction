package com.sinochem.crude.trade.transport.model.res;

import java.io.Serializable;

public class DealPointsRes implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
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
	
}