package com.sinochem.crude.trade.orderexecute.domain;

import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;

public class TriggerContract implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**ID*/
	private Long id;  
	
	/**UUID*/
	private String uuid;  
	
	/**订单ID*/
	private Long orderId;  
	
	/**合约名称*/
	private String name;  
	
	/**月份*/
	private String month;  
	
	/**品类*/
	private String commodity;  
	
	/**市场*/
	private String market;  
	
	/**价格源*/
	private String priceSource;  
	
	/**取价方式*/
	private String priceType;  
	
	/**合约数量*/
	private java.math.BigDecimal quantity;  
	
	/**数量状态（0临时 1最终）*/
	private String quantityStatus;  
	
	/**点价开始时间*/
	private java.util.Date beginTime;  
	
	/**点价结束时间*/
	private java.util.Date endTime;  
	
	/**已点数量*/
	private java.math.BigDecimal doneQuantity;  
	
	/**剩余数量*/
	private java.math.BigDecimal surplusQuantity;  
	
	/**转月数量*/
	private java.math.BigDecimal transferQuantity;  
	
	/**状态*/
	private String status;  
	
	/**语言类型*/
	private String langVer;  
	
	/**当前有效状态*/
	private String aliveFlag;  
	
	/**创建人*/
	private Long createUser;  
	
	/**创建时间*/
	private java.util.Date createDate;  
	
	/**修改人*/
	private Long updateUser;  
	
	/**修改时间*/
	private java.util.Date updateDate;  
	
	/**版本号*/
	private Integer version;  
	
		/**ID*/
	public void setId(Long id){
		this.id=id;
	}
	/**ID*/
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
	
	/**订单ID*/
	public void setOrderId(Long orderId){
		this.orderId=orderId;
	}
	/**订单ID*/
	public Long getOrderId(){
		return this.orderId;
	}
	
	/**合约名称*/
	public void setName(String name){
		this.name=name;
	}
	/**合约名称*/
	public String getName(){
		return this.name;
	}
	
	/**月份*/
	public void setMonth(String month){
		this.month=month;
	}
	/**月份*/
	public String getMonth(){
		return this.month;
	}
	
	/**品类*/
	public void setCommodity(String commodity){
		this.commodity=commodity;
	}
	/**品类*/
	public String getCommodity(){
		return this.commodity;
	}
	
	/**市场*/
	public void setMarket(String market){
		this.market=market;
	}
	/**市场*/
	public String getMarket(){
		return this.market;
	}
	
	/**价格源*/
	public void setPriceSource(String priceSource){
		this.priceSource=priceSource;
	}
	/**价格源*/
	public String getPriceSource(){
		return this.priceSource;
	}
	
	/**取价方式*/
	public void setPriceType(String priceType){
		this.priceType=priceType;
	}
	/**取价方式*/
	public String getPriceType(){
		return this.priceType;
	}
	
	/**合约数量*/
	public void setQuantity(java.math.BigDecimal quantity){
		this.quantity=quantity;
	}
	/**合约数量*/
	public java.math.BigDecimal getQuantity(){
		return this.quantity;
	}
	
	/**数量状态（0临时 1最终）*/
	public void setQuantityStatus(String quantityStatus){
		this.quantityStatus=quantityStatus;
	}
	/**数量状态（0临时 1最终）*/
	public String getQuantityStatus(){
		return this.quantityStatus;
	}
	
	/**点价开始时间*/
	public void setBeginTime(java.util.Date beginTime){
		this.beginTime=beginTime;
	}
	/**点价开始时间*/
	public java.util.Date getBeginTime(){
		return this.beginTime;
	}
	
	/**点价结束时间*/
	public void setEndTime(java.util.Date endTime){
		this.endTime=endTime;
	}
	/**点价结束时间*/
	public java.util.Date getEndTime(){
		return this.endTime;
	}
	
	/**已点数量*/
	public void setDoneQuantity(java.math.BigDecimal doneQuantity){
		this.doneQuantity=doneQuantity;
	}
	/**已点数量*/
	public java.math.BigDecimal getDoneQuantity(){
		return this.doneQuantity;
	}
	
	/**剩余数量*/
	public void setSurplusQuantity(java.math.BigDecimal surplusQuantity){
		this.surplusQuantity=surplusQuantity;
	}
	/**剩余数量*/
	public java.math.BigDecimal getSurplusQuantity(){
		return this.surplusQuantity;
	}
	
	/**转月数量*/
	public void setTransferQuantity(java.math.BigDecimal transferQuantity){
		this.transferQuantity=transferQuantity;
	}
	/**转月数量*/
	public java.math.BigDecimal getTransferQuantity(){
		return this.transferQuantity;
	}
	
	/**状态*/
	public void setStatus(String status){
		this.status=status;
	}
	/**状态*/
	public String getStatus(){
		return this.status;
	}
	
	/**语言类型*/
	public void setLangVer(String langVer){
		this.langVer=langVer;
	}
	/**语言类型*/
	public String getLangVer(){
		return this.langVer;
	}
	
	/**当前有效状态*/
	public void setAliveFlag(String aliveFlag){
		this.aliveFlag=aliveFlag;
	}
	/**当前有效状态*/
	public String getAliveFlag(){
		return this.aliveFlag;
	}
	
	/**创建人*/
	public void setCreateUser(Long createUser){
		this.createUser=createUser;
	}
	/**创建人*/
	public Long getCreateUser(){
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
	
	/**修改人*/
	public void setUpdateUser(Long updateUser){
		this.updateUser=updateUser;
	}
	/**修改人*/
	public Long getUpdateUser(){
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
	
	/**版本号*/
	public void setVersion(Integer version){
		this.version=version;
	}
	/**版本号*/
	public Integer getVersion(){
		return this.version;
	}
	
		
	
	public Map<String, Object> toMap() {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id",this.id);
	map.put("uuid",this.uuid);
	map.put("orderId",this.orderId);
	map.put("name",this.name);
	map.put("month",this.month);
	map.put("commodity",this.commodity);
	map.put("market",this.market);
	map.put("priceSource",this.priceSource);
	map.put("priceType",this.priceType);
	map.put("quantity",this.quantity);
	map.put("quantityStatus",this.quantityStatus);
	map.put("beginTime",this.beginTime);
	map.put("endTime",this.endTime);
	map.put("doneQuantity",this.doneQuantity);
	map.put("surplusQuantity",this.surplusQuantity);
	map.put("transferQuantity",this.transferQuantity);
	map.put("status",this.status);
	map.put("langVer",this.langVer);
	map.put("aliveFlag",this.aliveFlag);
	map.put("createUser",this.createUser);
	map.put("createDate",this.createDate);
	map.put("updateUser",this.updateUser);
	map.put("updateDate",this.updateDate);
	map.put("version",this.version);
			return map;
	}
}