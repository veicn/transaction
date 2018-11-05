package com.sinochem.crude.trade.orderexecute.domain;

import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;

public class OrderStatement implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**ID*/
	private Long orderStatementId;  
	
	/**UUID*/
	private String orderStatementUuid;  
	
	/**ORDER_ID*/
	private Long orderId;  
	
	/**对账类型(1预估 2正式)*/
	private String statementType;  
	
	/**对账单编号*/
	private String orderStatementNo;  
	
	/**订单编号*/
	private String orderNo;  
	
	/**提单量（净桶）*/
	private java.math.BigDecimal billWeightBbl;  
	
	/**提单量（净吨）*/
	private java.math.BigDecimal billWeightT;  
	
	/**费用合计*/
	private java.math.BigDecimal totalFee;  
	
	/**单价合计*/
	private java.math.BigDecimal price;  
	
	/**结算合计*/
	private java.math.BigDecimal totalAmount;  
	
	/**付款日*/
	private java.util.Date paymentDate;  
	
	/**提单日*/
	private java.util.Date billDate;  
	
	/**收款人*/
	private String payee;  
	
	/**账号*/
	private String acount;  
	
	/**开户行*/
	private String bankName;  
	
	/**状态（01初始 05待确认 10已确认 20已驳回 30已结算）*/
	private String status;  
	
	/**提交人*/
	private Long submitPerson;  
	
	/**提交人名称*/
	private String submitPersonName;  
	
	/**确认人*/
	private Long confirmPerson;  
	
	/**确认人名称*/
	private String confirmPersonName;  
	
	/**对账方名称*/
	private String customerName;  
	
	/**联系人*/
	private String contactName;  
	
	/**联系电话*/
	private String tel;  
	
	/**邮箱*/
	private String email;  
	
	/**对账时间*/
	private java.util.Date checkTime;  
	
	/**外贸合同*/
	private String contractNo;  
	
	/**代理协议*/
	private String agentContract;  
	
	/**油品*/
	private String oil;  
	
	/**价格公式*/
	private String priceFormula;  
	
	/**船名*/
	private String shipName;  
	
	/**基准价格*/
	private java.math.BigDecimal basePrice;  
	
	/**升贴水*/
	private java.math.BigDecimal agio;  
	
	/**合同量*/
	private java.math.BigDecimal contractQuantity;  
	
	/**贸易条款*/
	private String tradeTerm;  
	
	/**结算价格*/
	private java.math.BigDecimal settlementPrice;  
	
	/**货款金额*/
	private java.math.BigDecimal goodsAmount;  
	
	/**收款日*/
	private java.util.Date payDate;  
	
	/**结算量（桶）*/
	private java.math.BigDecimal settlementQuantityBbl;  
	
	/**已结算金额*/
	private java.math.BigDecimal settledAmount;  
	
	/**卖方ID*/
	private Long sellerCustomerId;  
	
	/**买方ID*/
	private Long buyerCustomerId;  
	
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
	
	/***/
	private String dataJson;  
	
	/**附件名称*/
	private String fileName;  
	
	/**附件地址*/
	private String fileUrl;  
	
	/**结算标准*/
	private String settleStandard;
	
		/**ID*/
	public void setOrderStatementId(Long orderStatementId){
		this.orderStatementId=orderStatementId;
	}
	/**ID*/
	public Long getOrderStatementId(){
		return this.orderStatementId;
	}
	
	/**UUID*/
	public void setOrderStatementUuid(String orderStatementUuid){
		this.orderStatementUuid=orderStatementUuid;
	}
	/**UUID*/
	public String getOrderStatementUuid(){
		return this.orderStatementUuid;
	}
	
	/**ORDER_ID*/
	public void setOrderId(Long orderId){
		this.orderId=orderId;
	}
	/**ORDER_ID*/
	public Long getOrderId(){
		return this.orderId;
	}
	
	/**对账类型(1预估 2正式)*/
	public void setStatementType(String statementType){
		this.statementType=statementType;
	}
	/**对账类型(1预估 2正式)*/
	public String getStatementType(){
		return this.statementType;
	}
	
	/**对账单编号*/
	public void setOrderStatementNo(String orderStatementNo){
		this.orderStatementNo=orderStatementNo;
	}
	/**对账单编号*/
	public String getOrderStatementNo(){
		return this.orderStatementNo;
	}
	
	/**订单编号*/
	public void setOrderNo(String orderNo){
		this.orderNo=orderNo;
	}
	/**订单编号*/
	public String getOrderNo(){
		return this.orderNo;
	}
	
	/**提单量（净桶）*/
	public void setBillWeightBbl(java.math.BigDecimal billWeightBbl){
		this.billWeightBbl=billWeightBbl;
	}
	/**提单量（净桶）*/
	public java.math.BigDecimal getBillWeightBbl(){
		return this.billWeightBbl;
	}
	
	/**提单量（净吨）*/
	public void setBillWeightT(java.math.BigDecimal billWeightT){
		this.billWeightT=billWeightT;
	}
	/**提单量（净吨）*/
	public java.math.BigDecimal getBillWeightT(){
		return this.billWeightT;
	}
	
	/**费用合计*/
	public void setTotalFee(java.math.BigDecimal totalFee){
		this.totalFee=totalFee;
	}
	/**费用合计*/
	public java.math.BigDecimal getTotalFee(){
		return this.totalFee;
	}
	
	/**单价合计*/
	public void setPrice(java.math.BigDecimal price){
		this.price=price;
	}
	/**单价合计*/
	public java.math.BigDecimal getPrice(){
		return this.price;
	}
	
	/**结算合计*/
	public void setTotalAmount(java.math.BigDecimal totalAmount){
		this.totalAmount=totalAmount;
	}
	/**结算合计*/
	public java.math.BigDecimal getTotalAmount(){
		return this.totalAmount;
	}
	
	/**付款日*/
	public void setPaymentDate(java.util.Date paymentDate){
		this.paymentDate=paymentDate;
	}
	/**付款日*/
	public java.util.Date getPaymentDate(){
		return this.paymentDate;
	}
	
	/**提单日*/
	public void setBillDate(java.util.Date billDate){
		this.billDate=billDate;
	}
	/**提单日*/
	public java.util.Date getBillDate(){
		return this.billDate;
	}
	
	/**收款人*/
	public void setPayee(String payee){
		this.payee=payee;
	}
	/**收款人*/
	public String getPayee(){
		return this.payee;
	}
	
	/**账号*/
	public void setAcount(String acount){
		this.acount=acount;
	}
	/**账号*/
	public String getAcount(){
		return this.acount;
	}
	
	/**开户行*/
	public void setBankName(String bankName){
		this.bankName=bankName;
	}
	/**开户行*/
	public String getBankName(){
		return this.bankName;
	}
	
	/**状态（01初始 05待确认 10已确认 20已驳回 30已结算）*/
	public void setStatus(String status){
		this.status=status;
	}
	/**状态（01初始 05待确认 10已确认 20已驳回 30已结算）*/
	public String getStatus(){
		return this.status;
	}
	
	/**提交人*/
	public void setSubmitPerson(Long submitPerson){
		this.submitPerson=submitPerson;
	}
	/**提交人*/
	public Long getSubmitPerson(){
		return this.submitPerson;
	}
	
	/**提交人名称*/
	public void setSubmitPersonName(String submitPersonName){
		this.submitPersonName=submitPersonName;
	}
	/**提交人名称*/
	public String getSubmitPersonName(){
		return this.submitPersonName;
	}
	
	/**确认人*/
	public void setConfirmPerson(Long confirmPerson){
		this.confirmPerson=confirmPerson;
	}
	/**确认人*/
	public Long getConfirmPerson(){
		return this.confirmPerson;
	}
	
	/**确认人名称*/
	public void setConfirmPersonName(String confirmPersonName){
		this.confirmPersonName=confirmPersonName;
	}
	/**确认人名称*/
	public String getConfirmPersonName(){
		return this.confirmPersonName;
	}
	
	/**对账方名称*/
	public void setCustomerName(String customerName){
		this.customerName=customerName;
	}
	/**对账方名称*/
	public String getCustomerName(){
		return this.customerName;
	}
	
	/**联系人*/
	public void setContactName(String contactName){
		this.contactName=contactName;
	}
	/**联系人*/
	public String getContactName(){
		return this.contactName;
	}
	
	/**联系电话*/
	public void setTel(String tel){
		this.tel=tel;
	}
	/**联系电话*/
	public String getTel(){
		return this.tel;
	}
	
	/**邮箱*/
	public void setEmail(String email){
		this.email=email;
	}
	/**邮箱*/
	public String getEmail(){
		return this.email;
	}
	
	/**对账时间*/
	public void setCheckTime(java.util.Date checkTime){
		this.checkTime=checkTime;
	}
	/**对账时间*/
	public java.util.Date getCheckTime(){
		return this.checkTime;
	}
	
	/**外贸合同*/
	public void setContractNo(String contractNo){
		this.contractNo=contractNo;
	}
	/**外贸合同*/
	public String getContractNo(){
		return this.contractNo;
	}
	
	/**代理协议*/
	public void setAgentContract(String agentContract){
		this.agentContract=agentContract;
	}
	/**代理协议*/
	public String getAgentContract(){
		return this.agentContract;
	}
	
	/**油品*/
	public void setOil(String oil){
		this.oil=oil;
	}
	/**油品*/
	public String getOil(){
		return this.oil;
	}
	
	/**价格公式*/
	public void setPriceFormula(String priceFormula){
		this.priceFormula=priceFormula;
	}
	/**价格公式*/
	public String getPriceFormula(){
		return this.priceFormula;
	}
	
	/**船名*/
	public void setShipName(String shipName){
		this.shipName=shipName;
	}
	/**船名*/
	public String getShipName(){
		return this.shipName;
	}
	
	/**基准价格*/
	public void setBasePrice(java.math.BigDecimal basePrice){
		this.basePrice=basePrice;
	}
	/**基准价格*/
	public java.math.BigDecimal getBasePrice(){
		return this.basePrice;
	}
	
	/**升贴水*/
	public void setAgio(java.math.BigDecimal agio){
		this.agio=agio;
	}
	/**升贴水*/
	public java.math.BigDecimal getAgio(){
		return this.agio;
	}
	
	/**合同量*/
	public void setContractQuantity(java.math.BigDecimal contractQuantity){
		this.contractQuantity=contractQuantity;
	}
	/**合同量*/
	public java.math.BigDecimal getContractQuantity(){
		return this.contractQuantity;
	}
	
	/**贸易条款*/
	public void setTradeTerm(String tradeTerm){
		this.tradeTerm=tradeTerm;
	}
	/**贸易条款*/
	public String getTradeTerm(){
		return this.tradeTerm;
	}
	
	/**结算价格*/
	public void setSettlementPrice(java.math.BigDecimal settlementPrice){
		this.settlementPrice=settlementPrice;
	}
	/**结算价格*/
	public java.math.BigDecimal getSettlementPrice(){
		return this.settlementPrice;
	}
	
	/**货款金额*/
	public void setGoodsAmount(java.math.BigDecimal goodsAmount){
		this.goodsAmount=goodsAmount;
	}
	/**货款金额*/
	public java.math.BigDecimal getGoodsAmount(){
		return this.goodsAmount;
	}
	
	/**收款日*/
	public void setPayDate(java.util.Date payDate){
		this.payDate=payDate;
	}
	/**收款日*/
	public java.util.Date getPayDate(){
		return this.payDate;
	}
	
	/**结算量（桶）*/
	public void setSettlementQuantityBbl(java.math.BigDecimal settlementQuantityBbl){
		this.settlementQuantityBbl=settlementQuantityBbl;
	}
	/**结算量（桶）*/
	public java.math.BigDecimal getSettlementQuantityBbl(){
		return this.settlementQuantityBbl;
	}
	
	/**已结算金额*/
	public void setSettledAmount(java.math.BigDecimal settledAmount){
		this.settledAmount=settledAmount;
	}
	/**已结算金额*/
	public java.math.BigDecimal getSettledAmount(){
		return this.settledAmount;
	}
	
	/**卖方ID*/
	public void setSellerCustomerId(Long sellerCustomerId){
		this.sellerCustomerId=sellerCustomerId;
	}
	/**卖方ID*/
	public Long getSellerCustomerId(){
		return this.sellerCustomerId;
	}
	
	/**买方ID*/
	public void setBuyerCustomerId(Long buyerCustomerId){
		this.buyerCustomerId=buyerCustomerId;
	}
	/**买方ID*/
	public Long getBuyerCustomerId(){
		return this.buyerCustomerId;
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
	
	/***/
	public void setDataJson(String dataJson){
		this.dataJson=dataJson;
	}
	/***/
	public String getDataJson(){
		return this.dataJson;
	}
	
	/**附件名称*/
	public void setFileName(String fileName){
		this.fileName=fileName;
	}
	/**附件名称*/
	public String getFileName(){
		return this.fileName;
	}
	
	/**附件地址*/
	public void setFileUrl(String fileUrl){
		this.fileUrl=fileUrl;
	}
	/**附件地址*/
	public String getFileUrl(){
		return this.fileUrl;
	}
	
		
	
	public String getSettleStandard() {
		return settleStandard;
	}
	public void setSettleStandard(String settleStandard) {
		this.settleStandard = settleStandard;
	}
	public Map<String, Object> toMap() {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("orderStatementId",this.orderStatementId);
	map.put("orderStatementUuid",this.orderStatementUuid);
	map.put("orderId",this.orderId);
	map.put("statementType",this.statementType);
	map.put("orderStatementNo",this.orderStatementNo);
	map.put("orderNo",this.orderNo);
	map.put("billWeightBbl",this.billWeightBbl);
	map.put("billWeightT",this.billWeightT);
	map.put("totalFee",this.totalFee);
	map.put("price",this.price);
	map.put("totalAmount",this.totalAmount);
	map.put("paymentDate",this.paymentDate);
	map.put("billDate",this.billDate);
	map.put("payee",this.payee);
	map.put("acount",this.acount);
	map.put("bankName",this.bankName);
	map.put("status",this.status);
	map.put("submitPerson",this.submitPerson);
	map.put("submitPersonName",this.submitPersonName);
	map.put("confirmPerson",this.confirmPerson);
	map.put("confirmPersonName",this.confirmPersonName);
	map.put("customerName",this.customerName);
	map.put("contactName",this.contactName);
	map.put("tel",this.tel);
	map.put("email",this.email);
	map.put("checkTime",this.checkTime);
	map.put("contractNo",this.contractNo);
	map.put("agentContract",this.agentContract);
	map.put("oil",this.oil);
	map.put("priceFormula",this.priceFormula);
	map.put("shipName",this.shipName);
	map.put("basePrice",this.basePrice);
	map.put("agio",this.agio);
	map.put("contractQuantity",this.contractQuantity);
	map.put("tradeTerm",this.tradeTerm);
	map.put("settlementPrice",this.settlementPrice);
	map.put("goodsAmount",this.goodsAmount);
	map.put("payDate",this.payDate);
	map.put("settlementQuantityBbl",this.settlementQuantityBbl);
	map.put("settledAmount",this.settledAmount);
	map.put("sellerCustomerId",this.sellerCustomerId);
	map.put("buyerCustomerId",this.buyerCustomerId);
	map.put("langVer",this.langVer);
	map.put("aliveFlag",this.aliveFlag);
	map.put("createUser",this.createUser);
	map.put("createDate",this.createDate);
	map.put("updateUser",this.updateUser);
	map.put("updateDate",this.updateDate);
	map.put("version",this.version);
	map.put("dataJson",this.dataJson);
	map.put("fileName",this.fileName);
	map.put("fileUrl",this.fileUrl);
	map.put("settleStandard",this.settleStandard);	
			return map;
	}
}