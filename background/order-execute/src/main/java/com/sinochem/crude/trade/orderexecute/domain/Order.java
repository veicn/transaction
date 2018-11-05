package com.sinochem.crude.trade.orderexecute.domain;

import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;

public class Order implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**ID*/
	private Long id;  
	
	/**UUID*/
	private String uuid;  
	
	/**订单号*/
	private String orderNo;  
	
	/**成交单UUID*/
	private Long tradeId;  
	
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
	
	/**成交时间*/
	private java.util.Date tradeTime;  
	
	/**备注信息*/
	private String remark;  
	
	/**订单状态*/
	private String status;  
	
	/**交易类型（1招标 2询价 3议价）*/
	private String tradeType;  
	
	/**交易大类（1原油；2成品油；3化工品）*/
	private String tradeCategory;  
	
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
	
	/**买家外部接口状态*/
	private String buyerInterfaceStatus;
	
	/**卖家外部接口状态*/
	private String sellerInterfaceStatus;
	
	/**代理商外部接口状态*/
	private String agentInterfaceStatus;
	
	/**结算量标准*/
	private String settlStandard;
	
	/**装卸货允许时间*/
	private String loadAndDischargeTime;
	
	/**商检费用分摊方式*/
	private String inspectionFeeSharingType;
	
	/**法律*/
	private String law;
	
	/**GTC*/
	private String gtc;
	
	/**语言类型*/
	private String langVer;  
	
	/**当前有效状态*/
	private String aliveFlag;  
	
	/**扩展字段1*/
	private String extend1;  
	
	/**扩展字段2*/
	private String extend2;  
	
	/**扩展字段3*/
	private String extend3;  
	
	/**扩展字段4*/
	private String extend4;  
	
	/**扩展字段5*/
	private String extend5;  
	
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
	
	/** 卖方合同号（线下数据录入）*/
	private String sellerContractNo;
	
	/** 买方合同号（线下数据录入） */
	private String buyerContractNo;
	
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
	
	/**订单号*/
	public void setOrderNo(String orderNo){
		this.orderNo=orderNo;
	}
	/**订单号*/
	public String getOrderNo(){
		return this.orderNo;
	}
	
	/**成交单UUID*/
	public void setTradeId(Long tradeId){
		this.tradeId=tradeId;
	}
	/**成交单UUID*/
	public Long getTradeId(){
		return this.tradeId;
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
	
	/**买家外部接口状态*/
	public void setBuyerInterfaceStatus(String buyerInterfaceStatus){
		this.buyerInterfaceStatus=buyerInterfaceStatus;
	}
	/**买家外部接口状态*/
	public String getBuyerInterfaceStatus(){
		return this.buyerInterfaceStatus;
	}
	
	/**卖家外部接口状态*/
	public void setSellerInterfaceStatus(String sellerInterfaceStatus){
		this.sellerInterfaceStatus=sellerInterfaceStatus;
	}
	/**卖家外部接口状态*/
	public String getSellerInterfaceStatus(){
		return this.sellerInterfaceStatus;
	}
	
	/**代理商外部接口状态*/
	public void setAgentInterfaceStatus(String agentInterfaceStatus){
		this.agentInterfaceStatus=agentInterfaceStatus;
	}
	/**代理商外部接口状态*/
	public String getAgentInterfaceStatus(){
		return this.agentInterfaceStatus;
	}
	
	
	public String getSettlStandard() {
		return settlStandard;
	}
	public void setSettlStandard(String settlStandard) {
		this.settlStandard = settlStandard;
	}
	public String getLoadAndDischargeTime() {
		return loadAndDischargeTime;
	}
	
	public void setLoadAndDischargeTime(String loadAndDischargeTime) {
		this.loadAndDischargeTime = loadAndDischargeTime;
	}
	
	
	public String getInspectionFeeSharingType() {
		return inspectionFeeSharingType;
	}
	public void setInspectionFeeSharingType(String inspectionFeeSharingType) {
		this.inspectionFeeSharingType = inspectionFeeSharingType;
	}
	
	
	public String getLaw() {
		return law;
	}
	public void setLaw(String law) {
		this.law = law;
	}
	public String getGtc() {
		return gtc;
	}
	public void setGtc(String gtc) {
		this.gtc = gtc;
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
	
	/**扩展字段1*/
	public void setExtend1(String extend1){
		this.extend1=extend1;
	}
	/**扩展字段1*/
	public String getExtend1(){
		return this.extend1;
	}
	
	/**扩展字段2*/
	public void setExtend2(String extend2){
		this.extend2=extend2;
	}
	/**扩展字段2*/
	public String getExtend2(){
		return this.extend2;
	}
	
	/**扩展字段3*/
	public void setExtend3(String extend3){
		this.extend3=extend3;
	}
	/**扩展字段3*/
	public String getExtend3(){
		return this.extend3;
	}
	
	/**扩展字段4*/
	public void setExtend4(String extend4){
		this.extend4=extend4;
	}
	/**扩展字段4*/
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
	
		
	
	public String getSellerContractNo() {
		return sellerContractNo;
	}
	public void setSellerContractNo(String sellerContractNo) {
		this.sellerContractNo = sellerContractNo;
	}
	public String getBuyerContractNo() {
		return buyerContractNo;
	}
	public void setBuyerContractNo(String buyerContractNo) {
		this.buyerContractNo = buyerContractNo;
	}
	public Map<String, Object> toMap() {
		Map<String,Object> map = new HashMap<>();
		map.put("id",this.id);
	map.put("uuid",this.uuid);
	map.put("orderNo",this.orderNo);
	map.put("tradeId",this.tradeId);
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
	map.put("settlStandard",this.settlStandard);
	map.put("loadAndDischargeTime",this.loadAndDischargeTime);
	map.put("langVer",this.langVer);
	map.put("aliveFlag",this.aliveFlag);
	map.put("extend1",this.extend1);
	map.put("extend2",this.extend2);
	map.put("extend3",this.extend3);
	map.put("extend4",this.extend4);
	map.put("extend5",this.extend5);
	map.put("createUser",this.createUser);
	map.put("createDate",this.createDate);
	map.put("updateUser",this.updateUser);
	map.put("updateDate",this.updateDate);
	map.put("version",this.version);
	map.put("sellerContractNo",this.sellerContractNo);
	map.put("buyerContractNo",this.buyerContractNo);
	map.put("inspectionFeeSharingType",this.inspectionFeeSharingType);
			return map;
	}
	
}