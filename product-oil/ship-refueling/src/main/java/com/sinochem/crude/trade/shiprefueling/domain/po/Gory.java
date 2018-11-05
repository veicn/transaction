package com.sinochem.crude.trade.shiprefueling.domain.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Gory implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**业务唯一键*/
	private Long palletId;  
	
	/**UUID*/
	private String uuid;  
	
	/**品种*/
	private String oilVarieties;

    /**币别*/
	private String currency;

	/**单位*/
	private String unit;  
	
	/**数量*/
	private java.math.BigDecimal number;  
	
	/**单价*/
	private java.math.BigDecimal unitPrice;  
	
	/**数量（桶）*/
	private java.math.BigDecimal quantity;  
	
	/**公司id*/
	private Long companyId;  
	
	/**公司名称*/
	private String companyName;  
	
	/**订单类型(1:船燃订单2:船供订单)*/
	private String orderType;  
	
	/**订单ID*/
	private Long orderId;  
	
	/**订单UUID*/
	private String orderUuid;  
	
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
	/*类型*/
	private String oilClassification;
	/*分类价格*/
	private java.math.BigDecimal classPrice;
		/**业务唯一键*/
	public void setPalletId(Long palletId){
		this.palletId=palletId;
	}
	/**业务唯一键*/
	public Long getPalletId(){
		return this.palletId;
	}
	
	/**UUID*/
	public void setUuid(String uuid){
		this.uuid=uuid;
	}
	/**UUID*/
	public String getUuid(){
		return this.uuid;
	}
	
	/**品种*/
	public void setOilVarieties(String oilVarieties){
		this.oilVarieties=oilVarieties;
	}
	/**品种*/
	public String getOilVarieties(){
		return this.oilVarieties;
	}
	
	/**单位*/
	public void setUnit(String unit){
		this.unit=unit;
	}
	/**单位*/
	public String getUnit(){
		return this.unit;
	}
	
	/**数量*/
	public void setNumber(java.math.BigDecimal number){
		this.number=number;
	}
	/**数量*/
	public java.math.BigDecimal getNumber(){
		return this.number;
	}
	
	/**单价*/
	public void setUnitPrice(java.math.BigDecimal unitPrice){
		this.unitPrice=unitPrice;
	}
	/**单价*/
	public java.math.BigDecimal getUnitPrice(){
		return this.unitPrice;
	}
	
	/**数量（桶）*/
	public void setQuantity(java.math.BigDecimal quantity){
		this.quantity=quantity;
	}
	/**数量（桶）*/
	public java.math.BigDecimal getQuantity(){
		return this.quantity;
	}
	
	/**公司id*/
	public void setCompanyId(Long companyId){
		this.companyId=companyId;
	}
	/**公司id*/
	public Long getCompanyId(){
		return this.companyId;
	}
	
	/**公司名称*/
	public void setCompanyName(String companyName){
		this.companyName=companyName;
	}
	/**公司名称*/
	public String getCompanyName(){
		return this.companyName;
	}
	
	/**订单类型(1:船燃订单2:船供订单)*/
	public void setOrderType(String orderType){
		this.orderType=orderType;
	}
	/**订单类型(1:船燃订单2:船供订单)*/
	public String getOrderType(){
		return this.orderType;
	}
	
	/**订单ID*/
	public void setOrderId(Long orderId){
		this.orderId=orderId;
	}
	/**订单ID*/
	public Long getOrderId(){
		return this.orderId;
	}
	
	/**订单UUID*/
	public void setOrderUuid(String orderUuid){
		this.orderUuid=orderUuid;
	}
	/**订单UUID*/
	public String getOrderUuid(){
		return this.orderUuid;
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

	public String getOilClassification() {
		return oilClassification;
	}

	public void setOilClassification(String oilClassification) {
		this.oilClassification = oilClassification;
	}

	public BigDecimal getClassPrice() {
		return classPrice;
	}

	public void setClassPrice(BigDecimal classPrice) {
		this.classPrice = classPrice;
	}

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

	public Map<String, Object> toMap() {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("palletId",this.palletId);
	map.put("uuid",this.uuid);
	map.put("oilVarieties",this.oilVarieties);
	map.put("unit",this.unit);
	map.put("number",this.number);
	map.put("unitPrice",this.unitPrice);
	map.put("quantity",this.quantity);
	map.put("companyId",this.companyId);
	map.put("companyName",this.companyName);
	map.put("orderType",this.orderType);
	map.put("orderId",this.orderId);
	map.put("orderUuid",this.orderUuid);
	map.put("aliveFlag",this.aliveFlag);
	map.put("version",this.version);
	map.put("langVer",this.langVer);
	map.put("createDate",this.createDate);
	map.put("updateDate",this.updateDate);
	map.put("createUser",this.createUser);
	map.put("updateUser",this.updateUser);
    map.put("currency",this.currency);
	map.put("ext1",this.ext1);
			return map;
	}

}