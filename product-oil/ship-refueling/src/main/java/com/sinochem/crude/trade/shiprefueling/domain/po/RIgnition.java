package com.sinochem.crude.trade.shiprefueling.domain.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;

public class RIgnition implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**业务唯一键*/
	private Long orderId;  
	
	/**UUID*/
	private String uuid;  
	
	/**会员公司id*/
	private Long sellCompanyId;
	
	/**会员公司名称*/
	private String sellCompanyName;  
	
	/**公司id*/
	private Long buyCompanyId;  
	
	/**公司名称*/
	private String buyCompanyName;  
	
	/**订单日期*/
	private java.util.Date orderTime;  
	
	/**订单编号*/
	private String orderCode;  
	
	/**总价*/
	private java.math.BigDecimal totalPrice;  
	
	/**提货日期*/
	private java.util.Date pickUpTime;  
	
	/**交货方式*/
	private String deliveryWay;  
	
	/**运输方式*/
	private String transportWay;  
	
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
	
	/**状态(1:洽谈中 2:已完成)*/
	private String status;


    /**币别*/
    private String currency;

    /**业务唯一键*/
	public void setOrderId(Long orderId){
		this.orderId=orderId;
	}
	/**业务唯一键*/
	public Long getOrderId(){
		return this.orderId;
	}
	
	/**UUID*/
	public void setUuid(String uuid){
		this.uuid=uuid;
	}
	/**UUID*/
	public String getUuid(){
		return this.uuid;
	}
	
	/**会员公司id*/
	public void setSellCompanyId(Long sellCompanyId){
		this.sellCompanyId=sellCompanyId;
	}
	/**会员公司id*/
	public Long getSellCompanyId(){
		return this.sellCompanyId;
	}
	
	/**会员公司名称*/
	public void setSellCompanyName(String sellCompanyName){
		this.sellCompanyName=sellCompanyName;
	}
	/**会员公司名称*/
	public String getSellCompanyName(){
		return this.sellCompanyName;
	}
	
	/**公司id*/
	public void setBuyCompanyId(Long buyCompanyId){
		this.buyCompanyId=buyCompanyId;
	}
	/**公司id*/
	public Long getBuyCompanyId(){
		return this.buyCompanyId;
	}
	
	/**公司名称*/
	public void setBuyCompanyName(String buyCompanyName){
		this.buyCompanyName=buyCompanyName;
	}
	/**公司名称*/
	public String getBuyCompanyName(){
		return this.buyCompanyName;
	}
	
	/**订单日期*/
	public void setOrderTime(java.util.Date orderTime){
		this.orderTime=orderTime;
	}
	/**订单日期*/
	public java.util.Date getOrderTime(){
		return this.orderTime;
	}
	
	/**订单编号*/
	public void setOrderCode(String orderCode){
		this.orderCode=orderCode;
	}
	/**订单编号*/
	public String getOrderCode(){
		return this.orderCode;
	}
	
	/**总价*/
	public void setTotalPrice(java.math.BigDecimal totalPrice){
		this.totalPrice=totalPrice;
	}
	/**总价*/
	public java.math.BigDecimal getTotalPrice(){
		return this.totalPrice;
	}
	
	/**提货日期*/
	public void setPickUpTime(java.util.Date pickUpTime){
		this.pickUpTime=pickUpTime;
	}
	/**提货日期*/
	public java.util.Date getPickUpTime(){
		return this.pickUpTime;
	}
	
	/**交货方式*/
	public void setDeliveryWay(String deliveryWay){
		this.deliveryWay=deliveryWay;
	}
	/**交货方式*/
	public String getDeliveryWay(){
		return this.deliveryWay;
	}
	
	/**运输方式*/
	public void setTransportWay(String transportWay){
		this.transportWay=transportWay;
	}
	/**运输方式*/
	public String getTransportWay(){
		return this.transportWay;
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
	
	/**状态(1:洽谈中 2:已完成)*/
	public void setStatus(String status){
		this.status=status;
	}
	/**状态(1:洽谈中 2:已完成)*/
	public String getStatus(){
		return this.status;
	}

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }


    public Map<String, Object> toMap() {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("orderId",this.orderId);
	map.put("uuid",this.uuid);
	map.put("sellCompanyId",this.sellCompanyId);
	map.put("sellCompanyName",this.sellCompanyName);
	map.put("buyCompanyId",this.buyCompanyId);
	map.put("buyCompanyName",this.buyCompanyName);
	map.put("orderTime",this.orderTime);
	map.put("orderCode",this.orderCode);
	map.put("totalPrice",this.totalPrice);
	map.put("pickUpTime",this.pickUpTime);
	map.put("deliveryWay",this.deliveryWay);
	map.put("transportWay",this.transportWay);
	map.put("aliveFlag",this.aliveFlag);
	map.put("version",this.version);
	map.put("langVer",this.langVer);
	map.put("createDate",this.createDate);
	map.put("updateDate",this.updateDate);
	map.put("createUser",this.createUser);
	map.put("updateUser",this.updateUser);
	map.put("ext1",this.ext1);
    map.put("currency",this.currency);
	map.put("status",this.status);
			return map;
	}

}