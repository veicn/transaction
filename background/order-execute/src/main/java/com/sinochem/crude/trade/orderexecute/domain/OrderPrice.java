package com.sinochem.crude.trade.orderexecute.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class OrderPrice implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**ID*/
	private Long orderPriceId;  
	
	/**UUID*/
	private String orderPriceUuid;  
	
	/**ORDER_ID*/
	private Long orderId;  
	
	/**ORDER_GOODS_ID*/
	private Long orderGoodsId;  
	
	/**贸易条款*/
	private String tradeTerm;  
	
	/**价格方式(Fixed AVG)*/
	private String priceType;  
	
	/**计价基准（DTD WTI  OPEC）*/
	private String basePrice;  
	
	/**计价单位*/
	private String priceUnit;  
	
	/**币种*/
	private String currency;  
	
	/**单价*/
	private java.math.BigDecimal price;  
	
	/**计价期*/
	private String pricingDesc;  
	
	/**价格公式描述*/
	private String priceFormula;  
	
	/**价格公式JSON*/
	private String priceFormulaJson;  
	
	/**价格说明*/
	private String priceDesc;  
	
	/**付款条款*/
	private String paymentTerm;  
	
	/**付款条款Json*/
	private String paymentTermJson;  
	
	/**升贴水*/
	private java.math.BigDecimal agio;  
	
	/**
	 * 付款日期
	 */
	private java.util.Date payDate;  
	
	/**点价最小量*/
	private java.math.BigDecimal lockQuantityMin;  
	
	/**转月限制次数*/
	private Integer transferCount;  
	
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
	public void setOrderPriceId(Long orderPriceId){
		this.orderPriceId=orderPriceId;
	}
	/**ID*/
	public Long getOrderPriceId(){
		return this.orderPriceId;
	}
	
	/**UUID*/
	public void setOrderPriceUuid(String orderPriceUuid){
		this.orderPriceUuid=orderPriceUuid;
	}
	/**UUID*/
	public String getOrderPriceUuid(){
		return this.orderPriceUuid;
	}
	
	/**ORDER_ID*/
	public void setOrderId(Long orderId){
		this.orderId=orderId;
	}
	/**ORDER_ID*/
	public Long getOrderId(){
		return this.orderId;
	}
	
	/**ORDER_GOODS_ID*/
	public void setOrderGoodsId(Long orderGoodsId){
		this.orderGoodsId=orderGoodsId;
	}
	/**ORDER_GOODS_ID*/
	public Long getOrderGoodsId(){
		return this.orderGoodsId;
	}
	
	/**贸易条款*/
	public void setTradeTerm(String tradeTerm){
		this.tradeTerm=tradeTerm;
	}
	/**贸易条款*/
	public String getTradeTerm(){
		return this.tradeTerm;
	}
	
	/**价格方式(Fixed AVG)*/
	public void setPriceType(String priceType){
		this.priceType=priceType;
	}
	/**价格方式(Fixed AVG)*/
	public String getPriceType(){
		return this.priceType;
	}
	
	/**计价基准（DTD WTI  OPEC）*/
	public void setBasePrice(String basePrice){
		this.basePrice=basePrice;
	}
	/**计价基准（DTD WTI  OPEC）*/
	public String getBasePrice(){
		return this.basePrice;
	}
	
	/**计价单位*/
	public void setPriceUnit(String priceUnit){
		this.priceUnit=priceUnit;
	}
	/**计价单位*/
	public String getPriceUnit(){
		return this.priceUnit;
	}
	
	/**币种*/
	public void setCurrency(String currency){
		this.currency=currency;
	}
	/**币种*/
	public String getCurrency(){
		return this.currency;
	}
	
	/**单价*/
	public void setPrice(java.math.BigDecimal price){
		this.price=price;
	}
	/**单价*/
	public java.math.BigDecimal getPrice(){
		return this.price;
	}
	
	/**计价期*/
	public void setPricingDesc(String pricingDesc){
		this.pricingDesc=pricingDesc;
	}
	/**计价期*/
	public String getPricingDesc(){
		return this.pricingDesc;
	}
	
	/**价格公式描述*/
	public void setPriceFormula(String priceFormula){
		this.priceFormula=priceFormula;
	}
	/**价格公式描述*/
	public String getPriceFormula(){
		return this.priceFormula;
	}
	
	/**价格公式JSON*/
	public void setPriceFormulaJson(String priceFormulaJson){
		this.priceFormulaJson=priceFormulaJson;
	}
	/**价格公式JSON*/
	public String getPriceFormulaJson(){
		return this.priceFormulaJson;
	}
	
	/**价格说明*/
	public void setPriceDesc(String priceDesc){
		this.priceDesc=priceDesc;
	}
	/**价格说明*/
	public String getPriceDesc(){
		return this.priceDesc;
	}
	
	/**付款条款*/
	public void setPaymentTerm(String paymentTerm){
		this.paymentTerm=paymentTerm;
	}
	/**付款条款*/
	public String getPaymentTerm(){
		return this.paymentTerm;
	}
	
	/**付款条款Json*/
	public void setPaymentTermJson(String paymentTermJson){
		this.paymentTermJson=paymentTermJson;
	}
	/**付款条款Json*/
	public String getPaymentTermJson(){
		return this.paymentTermJson;
	}
	
	/**升贴水*/
	public void setAgio(java.math.BigDecimal agio){
		this.agio=agio;
	}
	/**升贴水*/
	public java.math.BigDecimal getAgio(){
		return this.agio;
	}
	
	/**
	 * 付款日期
	 */
	public void setPayDate(java.util.Date payDate){
		this.payDate=payDate;
	}
	/**
	 * 付款日期
	 */
	public java.util.Date getPayDate(){
		return this.payDate;
	}
	
	/**点价最小量*/
	public void setLockQuantityMin(java.math.BigDecimal lockQuantityMin){
		this.lockQuantityMin=lockQuantityMin;
	}
	/**点价最小量*/
	public java.math.BigDecimal getLockQuantityMin(){
		return this.lockQuantityMin;
	}
	
	/**转月限制次数*/
	public void setTransferCount(Integer transferCount){
		this.transferCount=transferCount;
	}
	/**转月限制次数*/
	public Integer getTransferCount(){
		return this.transferCount;
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
		map.put("orderPriceId",this.orderPriceId);
	map.put("orderPriceUuid",this.orderPriceUuid);
	map.put("orderId",this.orderId);
	map.put("orderGoodsId",this.orderGoodsId);
	map.put("tradeTerm",this.tradeTerm);
	map.put("priceType",this.priceType);
	map.put("basePrice",this.basePrice);
	map.put("priceUnit",this.priceUnit);
	map.put("currency",this.currency);
	map.put("price",this.price);
	map.put("pricingDesc",this.pricingDesc);
	map.put("priceFormula",this.priceFormula);
	map.put("priceFormulaJson",this.priceFormulaJson);
	map.put("priceDesc",this.priceDesc);
	map.put("paymentTerm",this.paymentTerm);
	map.put("paymentTermJson",this.paymentTermJson);
	map.put("agio",this.agio);
	map.put("payDate",this.payDate);
	map.put("lockQuantityMin",this.lockQuantityMin);
	map.put("transferCount",this.transferCount);
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