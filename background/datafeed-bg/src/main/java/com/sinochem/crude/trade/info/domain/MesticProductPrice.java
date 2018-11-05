package com.sinochem.crude.trade.info.domain;

import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;

public class MesticProductPrice implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**自增主键*/
	private Long id;  
	
	/**32位唯一码*/
	private String uuid;  
	
	/**产品编号*/
	private String productCode;  
	
	/**产品名称*/
	private String productName;  
	
	/**价格(平均价)*/
	private java.math.BigDecimal price;  
	
	/**最高报价*/
	private java.math.BigDecimal highPrice;  
	
	/**最低报价*/
	private java.math.BigDecimal lowPrice;  
	
	/**价格单位*/
	private String priceUnit;  
	
	/**价格日期*/
	private java.util.Date priceDate;  
	
	/**区域代码*/
	private String areaCode;  
	
	/**区域名称*/
	private String areaName;  
	
	/**价格来源(如：中石油)*/
	private String priceSource;  
	
	/**价格趋势(0:持平，1:上涨，-1:下跌)*/
	private String priceTrend;  
	
	/**价格变动(较上一交易日)*/
	private java.math.BigDecimal priceChange;  
	
	/**创建者*/
	private String createUser;  
	
	/**创建时间*/
	private java.util.Date createDate;  
	
	/**修改者*/
	private String updateUser;  
	
	/**修改时间*/
	private java.util.Date updateDate;  
	
	/**数据状态*/
	private String aliveFlag;  
	
	/**牌号*/
	private String extend1;  
	
	/**行情ID*/
	private String extend2;  
	
	/**港口*/
	private String extend3;  
	
	/**交易方式*/
	private String extend4;  
	
	/**扩展字段5*/
	private String extend5;  
	
	/**扩展字段6*/
	private String extend6;  
	
	/**扩展字段7*/
	private String extend7;  
	
	/**扩展字段8*/
	private String extend8;  
	
	/**扩展字段9*/
	private String extend9;  
	
	/**扩展字段10*/
	private String extend10;  
	
	/**最高价格变动*/
	private java.math.BigDecimal highPriceChange;  
	
	/**最低价格变动*/
	private java.math.BigDecimal lowPriceChange;
	
	/**昨日最高报价*/
	private java.math.BigDecimal preHighPrice; 
	
	/**昨日最低报价*/
	private java.math.BigDecimal preLowPrice;  
	
		/**自增主键*/
	public void setId(Long id){
		this.id=id;
	}
	/**自增主键*/
	public Long getId(){
		return this.id;
	}
	
	/**32位唯一码*/
	public void setUuid(String uuid){
		this.uuid=uuid;
	}
	/**32位唯一码*/
	public String getUuid(){
		return this.uuid;
	}
	
	/**产品编号*/
	public void setProductCode(String productCode){
		this.productCode=productCode;
	}
	/**产品编号*/
	public String getProductCode(){
		return this.productCode;
	}
	
	/**产品名称*/
	public void setProductName(String productName){
		this.productName=productName;
	}
	/**产品名称*/
	public String getProductName(){
		return this.productName;
	}
	
	/**价格(平均价)*/
	public void setPrice(java.math.BigDecimal price){
		this.price=price;
	}
	/**价格(平均价)*/
	public java.math.BigDecimal getPrice(){
		return this.price;
	}
	
	/**最高报价*/
	public void setHighPrice(java.math.BigDecimal highPrice){
		this.highPrice=highPrice;
	}
	/**最高报价*/
	public java.math.BigDecimal getHighPrice(){
		return this.highPrice;
	}
	
	/**最低报价*/
	public void setLowPrice(java.math.BigDecimal lowPrice){
		this.lowPrice=lowPrice;
	}
	/**最低报价*/
	public java.math.BigDecimal getLowPrice(){
		return this.lowPrice;
	}
	
	/**价格单位*/
	public void setPriceUnit(String priceUnit){
		this.priceUnit=priceUnit;
	}
	/**价格单位*/
	public String getPriceUnit(){
		return this.priceUnit;
	}
	
	/**价格日期*/
	public void setPriceDate(java.util.Date priceDate){
		this.priceDate=priceDate;
	}
	/**价格日期*/
	public java.util.Date getPriceDate(){
		return this.priceDate;
	}
	
	/**区域代码*/
	public void setAreaCode(String areaCode){
		this.areaCode=areaCode;
	}
	/**区域代码*/
	public String getAreaCode(){
		return this.areaCode;
	}
	
	/**区域名称*/
	public void setAreaName(String areaName){
		this.areaName=areaName;
	}
	/**区域名称*/
	public String getAreaName(){
		return this.areaName;
	}
	
	/**价格来源(如：中石油)*/
	public void setPriceSource(String priceSource){
		this.priceSource=priceSource;
	}
	/**价格来源(如：中石油)*/
	public String getPriceSource(){
		return this.priceSource;
	}
	
	/**价格趋势(0:持平，1:上涨，-1:下跌)*/
	public void setPriceTrend(String priceTrend){
		this.priceTrend=priceTrend;
	}
	/**价格趋势(0:持平，1:上涨，-1:下跌)*/
	public String getPriceTrend(){
		return this.priceTrend;
	}
	
	/**价格变动(较上一交易日)*/
	public void setPriceChange(java.math.BigDecimal priceChange){
		this.priceChange=priceChange;
	}
	/**价格变动(较上一交易日)*/
	public java.math.BigDecimal getPriceChange(){
		return this.priceChange;
	}
	
	/**创建者*/
	public void setCreateUser(String createUser){
		this.createUser=createUser;
	}
	/**创建者*/
	public String getCreateUser(){
		return this.createUser;
	}
	
	/**创建时间*/
	public void setCreateDate(java.util.Date createDate){
		this.createDate=createDate;
	}
	/**创建时间*/
	public java.util.Date getCreateDate(){
		return this.createDate;
	}
	
	/**修改者*/
	public void setUpdateUser(String updateUser){
		this.updateUser=updateUser;
	}
	/**修改者*/
	public String getUpdateUser(){
		return this.updateUser;
	}
	
	/**修改时间*/
	public void setUpdateDate(java.util.Date updateDate){
		this.updateDate=updateDate;
	}
	/**修改时间*/
	public java.util.Date getUpdateDate(){
		return this.updateDate;
	}
	
	/**数据状态*/
	public void setAliveFlag(String aliveFlag){
		this.aliveFlag=aliveFlag;
	}
	/**数据状态*/
	public String getAliveFlag(){
		return this.aliveFlag;
	}
	
	/**牌号*/
	public void setExtend1(String extend1){
		this.extend1=extend1;
	}
	/**牌号*/
	public String getExtend1(){
		return this.extend1;
	}
	
	/**行情ID*/
	public void setExtend2(String extend2){
		this.extend2=extend2;
	}
	/**行情ID*/
	public String getExtend2(){
		return this.extend2;
	}
	
	/**港口*/
	public void setExtend3(String extend3){
		this.extend3=extend3;
	}
	/**港口*/
	public String getExtend3(){
		return this.extend3;
	}
	
	/**交易方式*/
	public void setExtend4(String extend4){
		this.extend4=extend4;
	}
	/**交易方式*/
	public String getExtend4(){
		return this.extend4;
	}
	
	/**扩展字段5*/
	public void setExtend5(String extend5){
		this.extend5=extend5;
	}
	/**扩展字段5*/
	public String getExtend5(){
		return this.extend5;
	}
	
	/**扩展字段6*/
	public void setExtend6(String extend6){
		this.extend6=extend6;
	}
	/**扩展字段6*/
	public String getExtend6(){
		return this.extend6;
	}
	
	/**扩展字段7*/
	public void setExtend7(String extend7){
		this.extend7=extend7;
	}
	/**扩展字段7*/
	public String getExtend7(){
		return this.extend7;
	}
	
	/**扩展字段8*/
	public void setExtend8(String extend8){
		this.extend8=extend8;
	}
	/**扩展字段8*/
	public String getExtend8(){
		return this.extend8;
	}
	
	/**扩展字段9*/
	public void setExtend9(String extend9){
		this.extend9=extend9;
	}
	/**扩展字段9*/
	public String getExtend9(){
		return this.extend9;
	}
	
	/**扩展字段10*/
	public void setExtend10(String extend10){
		this.extend10=extend10;
	}
	/**扩展字段10*/
	public String getExtend10(){
		return this.extend10;
	}
	
	/**最高价格变动*/
	public void setHighPriceChange(java.math.BigDecimal highPriceChange){
		this.highPriceChange=highPriceChange;
	}
	/**最高价格变动*/
	public java.math.BigDecimal getHighPriceChange(){
		return this.highPriceChange;
	}
	
	/**最低价格变动*/
	public void setLowPriceChange(java.math.BigDecimal lowPriceChange){
		this.lowPriceChange=lowPriceChange;
	}
	/**最低价格变动*/
	public java.math.BigDecimal getLowPriceChange(){
		return this.lowPriceChange;
	}
	
		
	
	public Map<String, Object> toMap() {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id",this.id);
	map.put("uuid",this.uuid);
	map.put("productCode",this.productCode);
	map.put("productName",this.productName);
	map.put("price",this.price);
	map.put("highPrice",this.highPrice);
	map.put("lowPrice",this.lowPrice);
	map.put("priceUnit",this.priceUnit);
	map.put("priceDate",this.priceDate);
	map.put("areaCode",this.areaCode);
	map.put("areaName",this.areaName);
	map.put("priceSource",this.priceSource);
	map.put("priceTrend",this.priceTrend);
	map.put("priceChange",this.priceChange);
	map.put("createUser",this.createUser);
	map.put("createDate",this.createDate);
	map.put("updateUser",this.updateUser);
	map.put("updateDate",this.updateDate);
	map.put("aliveFlag",this.aliveFlag);
	map.put("extend1",this.extend1);
	map.put("extend2",this.extend2);
	map.put("extend3",this.extend3);
	map.put("extend4",this.extend4);
	map.put("extend5",this.extend5);
	map.put("extend6",this.extend6);
	map.put("extend7",this.extend7);
	map.put("extend8",this.extend8);
	map.put("extend9",this.extend9);
	map.put("extend10",this.extend10);
	map.put("highPriceChange",this.highPriceChange);
	map.put("lowPriceChange",this.lowPriceChange);
	map.put("preHighPrice",this.preHighPrice);
	map.put("preLowPrice",this.preLowPrice);
			return map;
	}
	/**昨日最高报价*/
	public java.math.BigDecimal getPreHighPrice() {
		return preHighPrice;
	}
	/**昨日最高报价*/
	public void setPreHighPrice(java.math.BigDecimal preHighPrice) {
		this.preHighPrice = preHighPrice;
	}
	/**昨日最低报价*/
	public java.math.BigDecimal getPreLowPrice() {
		return preLowPrice;
	}
	/**昨日最低报价*/
	public void setPreLowPrice(java.math.BigDecimal preLowPrice) {
		this.preLowPrice = preLowPrice;
	}
	
}