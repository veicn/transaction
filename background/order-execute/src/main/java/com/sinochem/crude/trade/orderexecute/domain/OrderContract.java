package com.sinochem.crude.trade.orderexecute.domain;

import java.util.HashMap;
import java.util.Map;

import com.sinochem.crude.trade.common.utils.ValueSetUtils;
import com.sinochem.crude.trade.common.values.CodeValue;
import com.sinochem.crude.trade.orderexecute.commons.constants.ValueSetGroupConstant;
import com.sinochem.crude.trade.orderexecute.commons.utils.ValueSetUtil;

import java.io.Serializable;

public class OrderContract implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**合同ID*/
	private Long contractId;  
	
	/**UUID*/
	private String contractUuid;  
	
	/**订单ID*/
	private Long orderId;  
	
	/**订单编号*/
	private String orderNo;  
	
	/**合同编号*/
	private String contractNo;  
	
	/**合同名称*/
	private String contractName;  
	
	/**合同类别*/
	private String contractType;  
	
	/**买方*/
	private String buyerCustomer;  
	
	/**买方签订人*/
	private String buyerPerson;  
	
	/**买方联系方式*/
	private String buyerTel;  
	
	/**卖方*/
	private String sellerCustomer;  
	
	/**卖方签订人*/
	private String sellerPerson;  
	
	/**卖方联系方式*/
	private String sellerTel;  
	
	/**签订日期*/
	private java.util.Date signDate;  
	
	/**签约地点*/
	private String signAddress;  
	
	/**有效期从*/
	private java.util.Date startDate;  
	
	/**有效期到*/
	private java.util.Date endDate;  
	
	/**合同标的物*/
	private String goodsName;  
	
	/**标的物数量*/
	private java.math.BigDecimal quantity;  
	
	/**合同状态*/
	private String contractStatus;  
	
	/**录入人员*/
	private Long inputPerson;  
	
	/**履行方式*/
	private String performType;  
	
	/**装期LAYTIME*/
	private String layTime;  
	
	/**VSL（船名）*/
	private String shipName;  
	
	/**价格公式*/
	private String priceFormula;  
	
	/**计价期*/
	private String pricingDesc;  
	
	/**PRICE ROUNDING保留小数*/
	private String priceRounding;  
	
	/**付款条款*/
	private String paymentTerm;  
	
	/**滞期费(描述)*/
	private String demurrage;  
	
	/**INSPECTION*/
	private String inspection;  
	
	/**贸易条款*/
	private String tradeTerm;  
	
	/**GTC*/
	private String gtc;  
	
	/**卖家公司ID*/
	private Long sellerCustomerId;  
	
	/**买家公司ID*/
	private Long buyerCustomerId;  
	
	/**备注*/
	private String remark;  
	
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
	
		/**合同ID*/
	public void setContractId(Long contractId){
		this.contractId=contractId;
	}
	/**合同ID*/
	public Long getContractId(){
		return this.contractId;
	}
	
	/**UUID*/
	public void setContractUuid(String contractUuid){
		this.contractUuid=contractUuid;
	}
	/**UUID*/
	public String getContractUuid(){
		return this.contractUuid;
	}
	
	/**订单ID*/
	public void setOrderId(Long orderId){
		this.orderId=orderId;
	}
	/**订单ID*/
	public Long getOrderId(){
		return this.orderId;
	}
	
	/**订单编号*/
	public void setOrderNo(String orderNo){
		this.orderNo=orderNo;
	}
	/**订单编号*/
	public String getOrderNo(){
		return this.orderNo;
	}
	
	/**合同编号*/
	public void setContractNo(String contractNo){
		this.contractNo=contractNo;
	}
	/**合同编号*/
	public String getContractNo(){
		return this.contractNo;
	}
	
	/**合同名称*/
	public void setContractName(String contractName){
		this.contractName=contractName;
	}
	/**合同名称*/
	public String getContractName(){
		return this.contractName;
	}
	
	/**合同类别*/
	public void setContractType(String contractType){
		this.contractType=contractType;
	}
	/**合同类别*/
	public String getContractType(){
		return this.contractType;
	}
	
	/**买方*/
	public void setBuyerCustomer(String buyerCustomer){
		this.buyerCustomer=buyerCustomer;
	}
	/**买方*/
	public String getBuyerCustomer(){
		return this.buyerCustomer;
	}
	
	/**买方签订人*/
	public void setBuyerPerson(String buyerPerson){
		this.buyerPerson=buyerPerson;
	}
	/**买方签订人*/
	public String getBuyerPerson(){
		return this.buyerPerson;
	}
	
	/**买方联系方式*/
	public void setBuyerTel(String buyerTel){
		this.buyerTel=buyerTel;
	}
	/**买方联系方式*/
	public String getBuyerTel(){
		return this.buyerTel;
	}
	
	/**卖方*/
	public void setSellerCustomer(String sellerCustomer){
		this.sellerCustomer=sellerCustomer;
	}
	/**卖方*/
	public String getSellerCustomer(){
		return this.sellerCustomer;
	}
	
	/**卖方签订人*/
	public void setSellerPerson(String sellerPerson){
		this.sellerPerson=sellerPerson;
	}
	/**卖方签订人*/
	public String getSellerPerson(){
		return this.sellerPerson;
	}
	
	/**卖方联系方式*/
	public void setSellerTel(String sellerTel){
		this.sellerTel=sellerTel;
	}
	/**卖方联系方式*/
	public String getSellerTel(){
		return this.sellerTel;
	}
	
	/**签订日期*/
	public void setSignDate(java.util.Date signDate){
		this.signDate=signDate;
	}
	/**签订日期*/
	public java.util.Date getSignDate(){
		return this.signDate;
	}
	
	/**签约地点*/
	public void setSignAddress(String signAddress){
		this.signAddress=signAddress;
	}
	/**签约地点*/
	public String getSignAddress(){
		return this.signAddress;
	}
	
	/**有效期从*/
	public void setStartDate(java.util.Date startDate){
		this.startDate=startDate;
	}
	/**有效期从*/
	public java.util.Date getStartDate(){
		return this.startDate;
	}
	
	/**有效期到*/
	public void setEndDate(java.util.Date endDate){
		this.endDate=endDate;
	}
	/**有效期到*/
	public java.util.Date getEndDate(){
		return this.endDate;
	}
	
	/**合同标的物*/
	public void setGoodsName(String goodsName){
		this.goodsName=goodsName;
	}
	/**合同标的物*/
	public String getGoodsName(){
		return this.goodsName;
	}
	
	/**标的物数量*/
	public void setQuantity(java.math.BigDecimal quantity){
		this.quantity=quantity;
	}
	/**标的物数量*/
	public java.math.BigDecimal getQuantity(){
		return this.quantity;
	}
	
	/**合同状态*/
	public void setContractStatus(String contractStatus){
		this.contractStatus=contractStatus;
	}
	/**合同状态*/
	public String getContractStatus(){
		return this.contractStatus;
	}
	
	/**录入人员*/
	public void setInputPerson(Long inputPerson){
		this.inputPerson=inputPerson;
	}
	/**录入人员*/
	public Long getInputPerson(){
		return this.inputPerson;
	}
	
	/**履行方式*/
	public void setPerformType(String performType){
		this.performType=performType;
	}
	/**履行方式*/
	public String getPerformType(){
		return this.performType;
	}
	
	/**装期LAYTIME*/
	public void setLayTime(String layTime){
		this.layTime=layTime;
	}
	/**装期LAYTIME*/
	public String getLayTime(){
		return this.layTime;
	}
	
	/**VSL（船名）*/
	public void setShipName(String shipName){
		this.shipName=shipName;
	}
	/**VSL（船名）*/
	public String getShipName(){
		return this.shipName;
	}
	
	/**价格公式*/
	public void setPriceFormula(String priceFormula){
		this.priceFormula=priceFormula;
	}
	/**价格公式*/
	public String getPriceFormula(){
		return this.priceFormula;
	}
	
	/**计价期*/
	public void setPricingDesc(String pricingDesc){
		this.pricingDesc=pricingDesc;
	}
	/**计价期*/
	public String getPricingDesc(){
		return this.pricingDesc;
	}
	
	/**PRICE ROUNDING保留小数*/
	public void setPriceRounding(String priceRounding){
		this.priceRounding=priceRounding;
	}
	/**PRICE ROUNDING保留小数*/
	public String getPriceRounding(){
		return this.priceRounding;
	}
	
	/**付款条款*/
	public void setPaymentTerm(String paymentTerm){
		this.paymentTerm=paymentTerm;
	}
	/**付款条款*/
	public String getPaymentTerm(){
		return this.paymentTerm;
	}
	
	/**滞期费(描述)*/
	public void setDemurrage(String demurrage){
		this.demurrage=demurrage;
	}
	/**滞期费(描述)*/
	public String getDemurrage(){
		return this.demurrage;
	}
	
	/**INSPECTION*/
	public void setInspection(String inspection){
		this.inspection=inspection;
	}
	/**INSPECTION*/
	public String getInspection(){
		return this.inspection;
	}
	
	/**贸易条款*/
	public void setTradeTerm(String tradeTerm){
		this.tradeTerm=tradeTerm;
	}
	/**贸易条款*/
	public String getTradeTerm(){
		return this.tradeTerm;
	}
	
	/**GTC*/
	public void setGtc(String gtc){
		this.gtc=gtc;
	}
	/**GTC*/
	public String getGtc(){
		return this.gtc;
	}
	
	/**卖家公司ID*/
	public void setSellerCustomerId(Long sellerCustomerId){
		this.sellerCustomerId=sellerCustomerId;
	}
	/**卖家公司ID*/
	public Long getSellerCustomerId(){
		return this.sellerCustomerId;
	}
	
	/**买家公司ID*/
	public void setBuyerCustomerId(Long buyerCustomerId){
		this.buyerCustomerId=buyerCustomerId;
	}
	/**买家公司ID*/
	public Long getBuyerCustomerId(){
		return this.buyerCustomerId;
	}
	
	/**备注*/
	public void setRemark(String remark){
		this.remark=remark;
	}
	/**备注*/
	public String getRemark(){
		return this.remark;
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
		map.put("contractId",this.contractId);
	map.put("contractUuid",this.contractUuid);
	map.put("orderId",this.orderId);
	map.put("orderNo",this.orderNo);
	map.put("contractNo",this.contractNo);
	map.put("contractName",this.contractName);
	map.put("contractType",this.contractType);
	map.put("buyerCustomer",this.buyerCustomer);
	map.put("buyerPerson",this.buyerPerson);
	map.put("buyerTel",this.buyerTel);
	map.put("sellerCustomer",this.sellerCustomer);
	map.put("sellerPerson",this.sellerPerson);
	map.put("sellerTel",this.sellerTel);
	map.put("signDate",this.signDate);
	map.put("signAddress",this.signAddress);
	map.put("startDate",this.startDate);
	map.put("endDate",this.endDate);
	map.put("goodsName",this.goodsName);
	map.put("quantity",this.quantity);
	map.put("contractStatus",this.contractStatus);
	map.put("inputPerson",this.inputPerson);
	map.put("performType",this.performType);
	map.put("layTime",this.layTime);
	map.put("shipName",this.shipName);
	map.put("priceFormula",this.priceFormula);
	map.put("pricingDesc",this.pricingDesc);
	map.put("priceRounding",this.priceRounding);
	map.put("paymentTerm",this.paymentTerm);
	map.put("demurrage",this.demurrage);
	map.put("inspection",this.inspection);
	map.put("tradeTerm",this.tradeTerm);
	map.put("gtc",this.gtc);
	map.put("sellerCustomerId",this.sellerCustomerId);
	map.put("buyerCustomerId",this.buyerCustomerId);
	map.put("remark",this.remark);
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