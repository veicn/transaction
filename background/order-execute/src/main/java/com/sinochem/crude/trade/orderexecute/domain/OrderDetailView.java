package com.sinochem.crude.trade.orderexecute.domain;

import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;

public class OrderDetailView implements Serializable {

	private static final long serialVersionUID = 1L;
	/****************************订单主表 start**************************/
	/**订单ID*/
	private Long orderId;  
	
	/**订单UUID*/
	private String uuid;  
	
	/**订单号*/
	private String orderNo;  
	
	/**成交单UUID*/
	private String tradeUuid;  
	
	/**合同ID*/
	private Long contractId;  
	
	/**合同编号*/
	private String contractNo;  
	
	/**买卖方向(买1卖2)*/
	private String buysell;  
	
	/**买方公司ID*/
	private Long buyerCustomerId;  
	
	/**买方公司名称*/
	private String buyerCustomerName;  
	
	/**买方交易员ID*/
	private Long buyerPersonId;  
	
	/**买方交易员名称*/
	private String buyerPersonName;  
	
	/**卖方公司ID*/
	private Long sellerCustomerId;  
	
	/**卖方公司名称*/
	private String sellerCustomerName;  
	
	/**卖方交易员ID*/
	private Long sellerPersonId;  
	
	/**卖方交易员名称*/
	private String sellerPersonName;  
	
	/**代理商编号*/
	private Long agentId;  
	
	/**代理商名称*/
	private String agentName;  
	
	/**代理方式（1卖方代理 2买方代理）*/
	private String agentType;  
	
	/**提单日*/
	private java.util.Date billDate;  
	
	/**成交方式（长协、现货、临时）*/
	private String tradeMode;  
	
	/**成交方式描述（长协、现货、临时）*/
	private String tradeModeDesc;  
	
	/**成交时间*/
	private java.util.Date tradeTime;  
	
	/**备注信息*/
	private String remark;  
	
	/**订单状态*/
	private String status;  
	
	/**交易类型（1招标 2询价 3议价）*/
	private String tradeType;  
	
	/**交易类型描述（1招标 2询价 3议价）*/
	private String tradeTypeDesc;  
	
	/**交易大类（1原油；2成品油；3化工品）*/
	private String tradeCategory;  
	
	/**交易大类（1原油；2成品油；3化工品）*/
	private String tradeCategoryDesc;  
	
	/**取消、关闭原因*/
	private String closeDesc;  
	
	/**关闭状态（0否 其它 是）*/
	private String closeStatus;  
	
	/**其它条款*/
	private String otherTerm;  
	
	/**信用条款*/
	private String creditTerm;  
	
	/**商检机构*/
	private String survey;  
	
	/**授信模式（企业 信用证 保证金）*/
	private String creditModel;  
	
	/**授信模式描述（企业 信用证 保证金）*/
	private String creditModelDesc;  
	
	/**进出口配额文件*/
	private String quatoFile;  
	
	/**收款时间*/
	private java.util.Date receiveTime;  
	
	/**收款金额*/
	private java.math.BigDecimal receiveAmount;  
	
	/**收款确认人*/
	private Long receivePerson;  
	
	/**收款人*/
	private String payee;  
	
	/**收款银行*/
	private String receiveBank;  
	
	/**收款账户*/
	private String receiveAcount;  
	
	/**创建人*/
	private Long createUser;  
	
	/**创建时间*/
	private java.util.Date createDate;  
	
	/**修改人*/
	private Long updateUser;  
	
	/**修改时间*/
	private java.util.Date updateDate;  
	
	/**当前有效状态*/
	private String aliveFlag;  
	/****************************订单主表 end**************************/
	
	/****************************订单运输表 start**************************/
	/**ID*/
	private Long orderTransportId;  
	
	/**UUID*/
	private String orderTransportUuid;  
	
	/**船型*/
	private String shipModel;  
	
	/**装货港*/
	private String loadingPort;  
	
	/**卸货港*/
	private String uploadingPort;  
	
	/**装期开始*/
	private java.util.Date deliveryDateStart;  
	
	/**装期结束*/
	private java.util.Date deliveryDateEnd;  
	
	/**到货开始*/
	private java.util.Date dischargeDateStart;  
	
	/**到货结束*/
	private java.util.Date dischargeDateEnd;  
	/****************************订单运输表 end**************************/
	
	/****************************订单价格信息 start**************************/
	/**ID*/
	private Long orderPriceId;  
	
	/**UUID*/
	private String orderPriceUuid;  
	
	/**贸易条款*/
	private String tradeTerm;  
	
	/**价格方式(FIXED AVG)*/
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
	
	/**价格公式*/
	private String priceFormula;  
	
	/**价格说明*/
	private String priceDesc;  
	
	/**付款条款*/
	private String paymentTerm;  
	
	/**升贴水*/
	private java.math.BigDecimal agio;  
	
	/**付款日期*/
	private java.util.Date payDate;  
	/****************************订单价格信息 end**************************/
	/****************************订单商品信息start**************************/
	/**ID*/
	private Long orderGoodsId;  
	
	/**UUID*/
	private String orderGoodsUuid;  
	
	/**资源ID*/
	private Long resourceId;  
	
	/**中文名称*/
	private String zhName;  
	
	/**英文名称*/
	private String enName;  
	
	/**区域*/
	private String region;  
	
	/**产地*/
	private String countryOrigin;  
	
	/**数量*/
	private java.math.BigDecimal quantity;  
	
	/**数量单位*/
	private String quantityUnit;  
	
	/**规格（成品油）*/
	private String spec;  
	
	/**API度*/
	private java.math.BigDecimal api;  
	
	/**溢短装*/
	private java.math.BigDecimal moreLess;  
	/****************************订单商品信息 end**************************/
	
	/**UUID*/
	public void setUuid(String uuid){
		this.uuid=uuid;
	}
	/**UUID*/
	public String getUuid(){
		return this.uuid;
	}
	
	/**订单号*/
	public void setOrderNo(String orderNo){
		this.orderNo=orderNo;
	}
	/**订单号*/
	public String getOrderNo(){
		return this.orderNo;
	}
	
	/**成交单UUID*/
	public void setTradeUuid(String tradeUuid){
		this.tradeUuid=tradeUuid;
	}
	/**成交单UUID*/
	public String getTradeUuid(){
		return this.tradeUuid;
	}
	
	/**合同ID*/
	public void setContractId(Long contractId){
		this.contractId=contractId;
	}
	/**合同ID*/
	public Long getContractId(){
		return this.contractId;
	}
	
	/**合同编号*/
	public void setContractNo(String contractNo){
		this.contractNo=contractNo;
	}
	/**合同编号*/
	public String getContractNo(){
		return this.contractNo;
	}
	
	/**买卖方向(买1卖2)*/
	public void setBuysell(String buysell){
		this.buysell=buysell;
	}
	/**买卖方向(买1卖2)*/
	public String getBuysell(){
		return this.buysell;
	}
	
	/**买方公司ID*/
	public void setBuyerCustomerId(Long buyerCustomerId){
		this.buyerCustomerId=buyerCustomerId;
	}
	/**买方公司ID*/
	public Long getBuyerCustomerId(){
		return this.buyerCustomerId;
	}
	
	/**买方公司名称*/
	public void setBuyerCustomerName(String buyerCustomerName){
		this.buyerCustomerName=buyerCustomerName;
	}
	/**买方公司名称*/
	public String getBuyerCustomerName(){
		return this.buyerCustomerName;
	}
	
	/**买方交易员ID*/
	public void setBuyerPersonId(Long buyerPersonId){
		this.buyerPersonId=buyerPersonId;
	}
	/**买方交易员ID*/
	public Long getBuyerPersonId(){
		return this.buyerPersonId;
	}
	
	/**买方交易员名称*/
	public void setBuyerPersonName(String buyerPersonName){
		this.buyerPersonName=buyerPersonName;
	}
	/**买方交易员名称*/
	public String getBuyerPersonName(){
		return this.buyerPersonName;
	}
	
	/**卖方公司ID*/
	public void setSellerCustomerId(Long sellerCustomerId){
		this.sellerCustomerId=sellerCustomerId;
	}
	/**卖方公司ID*/
	public Long getSellerCustomerId(){
		return this.sellerCustomerId;
	}
	
	/**卖方公司名称*/
	public void setSellerCustomerName(String sellerCustomerName){
		this.sellerCustomerName=sellerCustomerName;
	}
	/**卖方公司名称*/
	public String getSellerCustomerName(){
		return this.sellerCustomerName;
	}
	
	/**卖方交易员ID*/
	public void setSellerPersonId(Long sellerPersonId){
		this.sellerPersonId=sellerPersonId;
	}
	/**卖方交易员ID*/
	public Long getSellerPersonId(){
		return this.sellerPersonId;
	}
	
	/**卖方交易员名称*/
	public void setSellerPersonName(String sellerPersonName){
		this.sellerPersonName=sellerPersonName;
	}
	/**卖方交易员名称*/
	public String getSellerPersonName(){
		return this.sellerPersonName;
	}
	
	/**代理商编号*/
	public void setAgentId(Long agentId){
		this.agentId=agentId;
	}
	/**代理商编号*/
	public Long getAgentId(){
		return this.agentId;
	}
	
	/**代理商名称*/
	public void setAgentName(String agentName){
		this.agentName=agentName;
	}
	/**代理商名称*/
	public String getAgentName(){
		return this.agentName;
	}
	
	/**代理方式（1卖方代理 2买方代理）*/
	public void setAgentType(String agentType){
		this.agentType=agentType;
	}
	/**代理方式（1卖方代理 2买方代理）*/
	public String getAgentType(){
		return this.agentType;
	}
	
	/**提单日*/
	public void setBillDate(java.util.Date billDate){
		this.billDate=billDate;
	}
	/**提单日*/
	public java.util.Date getBillDate(){
		return this.billDate;
	}
	
	/**成交方式（长协、现货、临时）*/
	public void setTradeMode(String tradeMode){
		this.tradeMode=tradeMode;
	}
	/**成交方式（长协、现货、临时）*/
	public String getTradeMode(){
		return this.tradeMode;
	}
	
	/**成交时间*/
	public void setTradeTime(java.util.Date tradeTime){
		this.tradeTime=tradeTime;
	}
	/**成交时间*/
	public java.util.Date getTradeTime(){
		return this.tradeTime;
	}
	
	/**备注信息*/
	public void setRemark(String remark){
		this.remark=remark;
	}
	/**备注信息*/
	public String getRemark(){
		return this.remark;
	}
	
	/**订单状态*/
	public void setStatus(String status){
		this.status=status;
	}
	/**订单状态*/
	public String getStatus(){
		return this.status;
	}
	
	/**交易类型（1招标 2询价 3议价）*/
	public void setTradeType(String tradeType){
		this.tradeType=tradeType;
	}
	/**交易类型（1招标 2询价 3议价）*/
	public String getTradeType(){
		return this.tradeType;
	}
	
	/**交易大类（1原油；2成品油；3化工品）*/
	public void setTradeCategory(String tradeCategory){
		this.tradeCategory=tradeCategory;
	}
	/**交易大类（1原油；2成品油；3化工品）*/
	public String getTradeCategory(){
		return this.tradeCategory;
	}
	
	/**取消、关闭原因*/
	public void setCloseDesc(String closeDesc){
		this.closeDesc=closeDesc;
	}
	/**取消、关闭原因*/
	public String getCloseDesc(){
		return this.closeDesc;
	}
	
	/**关闭状态（0否 其它 是）*/
	public void setCloseStatus(String closeStatus){
		this.closeStatus=closeStatus;
	}
	/**关闭状态（0否 其它 是）*/
	public String getCloseStatus(){
		return this.closeStatus;
	}
	
	/**其它条款*/
	public void setOtherTerm(String otherTerm){
		this.otherTerm=otherTerm;
	}
	/**其它条款*/
	public String getOtherTerm(){
		return this.otherTerm;
	}
	
	/**信用条款*/
	public void setCreditTerm(String creditTerm){
		this.creditTerm=creditTerm;
	}
	/**信用条款*/
	public String getCreditTerm(){
		return this.creditTerm;
	}
	
	/**商检机构*/
	public void setSurvey(String survey){
		this.survey=survey;
	}
	/**商检机构*/
	public String getSurvey(){
		return this.survey;
	}
	
	/**授信模式（企业 信用证 保证金）*/
	public void setCreditModel(String creditModel){
		this.creditModel=creditModel;
	}
	/**授信模式（企业 信用证 保证金）*/
	public String getCreditModel(){
		return this.creditModel;
	}
	
	/**进出口配额文件*/
	public void setQuatoFile(String quatoFile){
		this.quatoFile=quatoFile;
	}
	/**进出口配额文件*/
	public String getQuatoFile(){
		return this.quatoFile;
	}
	
	/**收款时间*/
	public void setReceiveTime(java.util.Date receiveTime){
		this.receiveTime=receiveTime;
	}
	/**收款时间*/
	public java.util.Date getReceiveTime(){
		return this.receiveTime;
	}
	
	/**收款金额*/
	public void setReceiveAmount(java.math.BigDecimal receiveAmount){
		this.receiveAmount=receiveAmount;
	}
	/**收款金额*/
	public java.math.BigDecimal getReceiveAmount(){
		return this.receiveAmount;
	}
	
	/**收款确认人*/
	public void setReceivePerson(Long receivePerson){
		this.receivePerson=receivePerson;
	}
	/**收款确认人*/
	public Long getReceivePerson(){
		return this.receivePerson;
	}
	
	/**收款人*/
	public void setPayee(String payee){
		this.payee=payee;
	}
	/**收款人*/
	public String getPayee(){
		return this.payee;
	}
	
	/**收款银行*/
	public void setReceiveBank(String receiveBank){
		this.receiveBank=receiveBank;
	}
	/**收款银行*/
	public String getReceiveBank(){
		return this.receiveBank;
	}
	
	/**收款账户*/
	public void setReceiveAcount(String receiveAcount){
		this.receiveAcount=receiveAcount;
	}
	/**收款账户*/
	public String getReceiveAcount(){
		return this.receiveAcount;
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

	
		
	
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public String getTradeModeDesc() {
		return tradeModeDesc;
	}
	public void setTradeModeDesc(String tradeModeDesc) {
		this.tradeModeDesc = tradeModeDesc;
	}
	public String getTradeTypeDesc() {
		return tradeTypeDesc;
	}
	public void setTradeTypeDesc(String tradeTypeDesc) {
		this.tradeTypeDesc = tradeTypeDesc;
	}
	public String getCreditModelDesc() {
		return creditModelDesc;
	}
	public void setCreditModelDesc(String creditModelDesc) {
		this.creditModelDesc = creditModelDesc;
	}
	public Long getOrderTransportId() {
		return orderTransportId;
	}
	public void setOrderTransportId(Long orderTransportId) {
		this.orderTransportId = orderTransportId;
	}
	public String getOrderTransportUuid() {
		return orderTransportUuid;
	}
	public void setOrderTransportUuid(String orderTransportUuid) {
		this.orderTransportUuid = orderTransportUuid;
	}
	public String getShipModel() {
		return shipModel;
	}
	public void setShipModel(String shipModel) {
		this.shipModel = shipModel;
	}
	public String getLoadingPort() {
		return loadingPort;
	}
	public void setLoadingPort(String loadingPort) {
		this.loadingPort = loadingPort;
	}
	public String getUploadingPort() {
		return uploadingPort;
	}
	public void setUploadingPort(String uploadingPort) {
		this.uploadingPort = uploadingPort;
	}
	public java.util.Date getDeliveryDateStart() {
		return deliveryDateStart;
	}
	public void setDeliveryDateStart(java.util.Date deliveryDateStart) {
		this.deliveryDateStart = deliveryDateStart;
	}
	public java.util.Date getDeliveryDateEnd() {
		return deliveryDateEnd;
	}
	public void setDeliveryDateEnd(java.util.Date deliveryDateEnd) {
		this.deliveryDateEnd = deliveryDateEnd;
	}
	public java.util.Date getDischargeDateStart() {
		return dischargeDateStart;
	}
	public void setDischargeDateStart(java.util.Date dischargeDateStart) {
		this.dischargeDateStart = dischargeDateStart;
	}
	public java.util.Date getDischargeDateEnd() {
		return dischargeDateEnd;
	}
	public void setDischargeDateEnd(java.util.Date dischargeDateEnd) {
		this.dischargeDateEnd = dischargeDateEnd;
	}
	public Long getOrderPriceId() {
		return orderPriceId;
	}
	public void setOrderPriceId(Long orderPriceId) {
		this.orderPriceId = orderPriceId;
	}
	public String getOrderPriceUuid() {
		return orderPriceUuid;
	}
	public void setOrderPriceUuid(String orderPriceUuid) {
		this.orderPriceUuid = orderPriceUuid;
	}
	public String getTradeTerm() {
		return tradeTerm;
	}
	public void setTradeTerm(String tradeTerm) {
		this.tradeTerm = tradeTerm;
	}
	public String getPriceType() {
		return priceType;
	}
	public void setPriceType(String priceType) {
		this.priceType = priceType;
	}
	public String getBasePrice() {
		return basePrice;
	}
	public void setBasePrice(String basePrice) {
		this.basePrice = basePrice;
	}
	public String getPriceUnit() {
		return priceUnit;
	}
	public void setPriceUnit(String priceUnit) {
		this.priceUnit = priceUnit;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public java.math.BigDecimal getPrice() {
		return price;
	}
	public void setPrice(java.math.BigDecimal price) {
		this.price = price;
	}
	public String getPricingDesc() {
		return pricingDesc;
	}
	public void setPricingDesc(String pricingDesc) {
		this.pricingDesc = pricingDesc;
	}
	public String getPriceFormula() {
		return priceFormula;
	}
	public void setPriceFormula(String priceFormula) {
		this.priceFormula = priceFormula;
	}
	public String getPriceDesc() {
		return priceDesc;
	}
	public void setPriceDesc(String priceDesc) {
		this.priceDesc = priceDesc;
	}
	public String getPaymentTerm() {
		return paymentTerm;
	}
	public void setPaymentTerm(String paymentTerm) {
		this.paymentTerm = paymentTerm;
	}
	public java.math.BigDecimal getAgio() {
		return agio;
	}
	public void setAgio(java.math.BigDecimal agio) {
		this.agio = agio;
	}
	public java.util.Date getPayDate() {
		return payDate;
	}
	public void setPayDate(java.util.Date payDate) {
		this.payDate = payDate;
	}
	public Long getOrderGoodsId() {
		return orderGoodsId;
	}
	public void setOrderGoodsId(Long orderGoodsId) {
		this.orderGoodsId = orderGoodsId;
	}
	public String getOrderGoodsUuid() {
		return orderGoodsUuid;
	}
	public void setOrderGoodsUuid(String orderGoodsUuid) {
		this.orderGoodsUuid = orderGoodsUuid;
	}
	public Long getResourceId() {
		return resourceId;
	}
	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}
	public String getZhName() {
		return zhName;
	}
	public void setZhName(String zhName) {
		this.zhName = zhName;
	}
	public String getEnName() {
		return enName;
	}
	public void setEnName(String enName) {
		this.enName = enName;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getCountryOrigin() {
		return countryOrigin;
	}
	public void setCountryOrigin(String countryOrigin) {
		this.countryOrigin = countryOrigin;
	}
	public java.math.BigDecimal getQuantity() {
		return quantity;
	}
	public void setQuantity(java.math.BigDecimal quantity) {
		this.quantity = quantity;
	}
	public String getQuantityUnit() {
		return quantityUnit;
	}
	public void setQuantityUnit(String quantityUnit) {
		this.quantityUnit = quantityUnit;
	}
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
	public java.math.BigDecimal getApi() {
		return api;
	}
	public void setApi(java.math.BigDecimal api) {
		this.api = api;
	}
	public java.math.BigDecimal getMoreLess() {
		return moreLess;
	}
	public void setMoreLess(java.math.BigDecimal moreLess) {
		this.moreLess = moreLess;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public String getAliveFlag() {
		return aliveFlag;
	}
	public void setAliveFlag(String aliveFlag) {
		this.aliveFlag = aliveFlag;
	}
	
	public String getTradeCategoryDesc() {
		return tradeCategoryDesc;
	}
	public void setTradeCategoryDesc(String tradeCategoryDesc) {
		this.tradeCategoryDesc = tradeCategoryDesc;
	}
	public Map<String, Object> toMap() {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("orderId",this.orderId);
		map.put("uuid",this.uuid);
		map.put("orderNo",this.orderNo);
		map.put("tradeUuid",this.tradeUuid);
		map.put("contractId",this.contractId);
		map.put("contractNo",this.contractNo);
		map.put("buysell",this.buysell);
		map.put("buyerCustomerId",this.buyerCustomerId);
		map.put("buyerCustomerName",this.buyerCustomerName);
		map.put("buyerPersonId",this.buyerPersonId);
		map.put("buyerPersonName",this.buyerPersonName);
		map.put("sellerCustomerId",this.sellerCustomerId);
		map.put("sellerCustomerName",this.sellerCustomerName);
		map.put("sellerPersonId",this.sellerPersonId);
		map.put("sellerPersonName",this.sellerPersonName);
		map.put("agentId",this.agentId);
		map.put("agentName",this.agentName);
		map.put("agentType",this.agentType);
		map.put("billDate",this.billDate);
		map.put("tradeMode",this.tradeMode);
		map.put("tradeTime",this.tradeTime);
		map.put("remark",this.remark);
		map.put("status",this.status);
		map.put("tradeType",this.tradeType);
		map.put("tradeCategory",this.tradeCategory);
		map.put("tradeCategoryDesc",this.tradeCategoryDesc);
		map.put("closeDesc",this.closeDesc);
		map.put("closeStatus",this.closeStatus);
		map.put("otherTerm",this.otherTerm);
		map.put("creditTerm",this.creditTerm);
		map.put("survey",this.survey);
		map.put("creditModel",this.creditModel);
		map.put("quatoFile",this.quatoFile);
		map.put("receiveTime",this.receiveTime);
		map.put("receiveAmount",this.receiveAmount);
		map.put("receivePerson",this.receivePerson);
		map.put("payee",this.payee);
		map.put("receiveBank",this.receiveBank);
		map.put("receiveAcount",this.receiveAcount);
		map.put("createUser",this.createUser);
		map.put("createDate",this.createDate);
		map.put("updateUser",this.updateUser);
		map.put("updateDate",this.updateDate);
		map.put("aliveFlag",this.aliveFlag);
		
		map.put("orderTransportId",this.orderTransportId);
		map.put("orderTransportUuid",this.orderTransportUuid);
		map.put("shipModel",this.shipModel);
		map.put("loadingPort",this.loadingPort);
		map.put("uploadingPort",this.uploadingPort);
		map.put("deliveryDateStart",this.deliveryDateStart);
		map.put("deliveryDateEnd",this.deliveryDateEnd);
		map.put("dischargeDateStart",this.dischargeDateStart);
		map.put("dischargeDateEnd",this.dischargeDateEnd);
		
		map.put("orderPriceId",this.orderPriceId);
		map.put("orderPriceUuid",this.orderPriceUuid);
		map.put("tradeTerm",this.tradeTerm);
		map.put("priceType",this.priceType);
		map.put("basePrice",this.basePrice);
		map.put("priceUnit",this.priceUnit);
		map.put("currency",this.currency);
		map.put("price",this.price);
		map.put("pricingDesc",this.pricingDesc);
		map.put("priceFormula",this.priceFormula);
		map.put("priceDesc",this.priceDesc);
		map.put("paymentTerm",this.paymentTerm);
		map.put("agio",this.agio);
		map.put("payDate",this.payDate);
		
		map.put("orderGoodsId",this.orderGoodsId);
		map.put("orderGoodsUuid",this.orderGoodsUuid);
		map.put("resourceId",this.resourceId);
		map.put("zhName",this.zhName);
		map.put("enName",this.enName);
		map.put("region",this.region);
		map.put("countryOrigin",this.countryOrigin);
		map.put("quantity",this.quantity);
		map.put("quantityUnit",this.quantityUnit);
		map.put("spec",this.spec);
		map.put("api",this.api);
		map.put("moreLess",this.moreLess);
			return map;
	}
}