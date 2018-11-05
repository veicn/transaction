package com.sinochem.crude.trade.orderexecute.domain;

import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;

public class OrderDocument implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**ID*/
	private Long orderDocumentId;  
	
	/**UUID*/
	private String orderDocumentUuid;  
	
	/**ORDER_ID*/
	private Long orderId;  
	
	/**订单编号*/
	private String orderNo;  
	
	/**合同号*/
	private String contractNo;  
	
	/**船名*/
	private String shipName;  
	
	/**单证类型*/
	private String documentType;  
	
	/**提单日*/
	private java.util.Date billDate;  
	
	/**卸港*/
	private java.util.Date unportDate;  
	
	/**油种*/
	private String oil;  
	
	/**商检公司名称*/
	private String survey;  
	
	/**许可证编号*/
	private String licence;  
	
	/**许可证编号类型*/
	private String licenceType;  
	
	/**贸易方式*/
	private String tradeTerm;  
	
	/**进口商*/
	private String importCustomer;  
	
	/**进口用户*/
	private String importUser;  
	
	/**许可证数量*/
	private Integer licenceCount;  
	
	/**报关口岸*/
	private String declaratPort;  
	
	/**原产地国*/
	private String countryOrigin;  
	
	/**报关单号*/
	private String declaratNo;  
	
	/**进口口岸*/
	private String importPort;  
	
	/**出口口岸*/
	private String extendPort;  
	
	/**放行日期*/
	private java.util.Date clearanceDate;  
	
	/**项目名称*/
	private String projectName;  
	
	/**金额*/
	private java.math.BigDecimal amount;  
	
	/**付款单位*/
	private String paymentCompany;  
	
	/**发票号*/
	private String invoiceNo;  
	
	/**报关发票类型*/
	private String declaratInvoiceType;  
	
	/**结算发票类型*/
	private String settlementInvoiceType;  
	
	/**手册号*/
	private String manualNo;  
	
	/**加工贸易类型*/
	private String machiningType;  
	
	/**主管海关*/
	private String ciq;  
	
	/**外商公司*/
	private String foreignName;  
	
	/**出具方*/
	private String issue;  
	
	/**备注*/
	private String remark;  
	
	/**版本号*/
	private Integer version;  
	
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
	
		/**ID*/
	public void setOrderDocumentId(Long orderDocumentId){
		this.orderDocumentId=orderDocumentId;
	}
	/**ID*/
	public Long getOrderDocumentId(){
		return this.orderDocumentId;
	}
	
	/**UUID*/
	public void setOrderDocumentUuid(String orderDocumentUuid){
		this.orderDocumentUuid=orderDocumentUuid;
	}
	/**UUID*/
	public String getOrderDocumentUuid(){
		return this.orderDocumentUuid;
	}
	
	/**ORDER_ID*/
	public void setOrderId(Long orderId){
		this.orderId=orderId;
	}
	/**ORDER_ID*/
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
	
	/**合同号*/
	public void setContractNo(String contractNo){
		this.contractNo=contractNo;
	}
	/**合同号*/
	public String getContractNo(){
		return this.contractNo;
	}
	
	/**船名*/
	public void setShipName(String shipName){
		this.shipName=shipName;
	}
	/**船名*/
	public String getShipName(){
		return this.shipName;
	}
	
	/**单证类型*/
	public void setDocumentType(String documentType){
		this.documentType=documentType;
	}
	/**单证类型*/
	public String getDocumentType(){
		return this.documentType;
	}
	
	/**提单日*/
	public void setBillDate(java.util.Date billDate){
		this.billDate=billDate;
	}
	/**提单日*/
	public java.util.Date getBillDate(){
		return this.billDate;
	}
	
	/**卸港*/
	public void setUnportDate(java.util.Date unportDate){
		this.unportDate=unportDate;
	}
	/**卸港*/
	public java.util.Date getUnportDate(){
		return this.unportDate;
	}
	
	/**油种*/
	public void setOil(String oil){
		this.oil=oil;
	}
	/**油种*/
	public String getOil(){
		return this.oil;
	}
	
	/**商检公司名称*/
	public void setSurvey(String survey){
		this.survey=survey;
	}
	/**商检公司名称*/
	public String getSurvey(){
		return this.survey;
	}
	
	/**许可证编号*/
	public void setLicence(String licence){
		this.licence=licence;
	}
	/**许可证编号*/
	public String getLicence(){
		return this.licence;
	}
	
	/**许可证编号类型*/
	public void setLicenceType(String licenceType){
		this.licenceType=licenceType;
	}
	/**许可证编号类型*/
	public String getLicenceType(){
		return this.licenceType;
	}
	
	/**贸易方式*/
	public void setTradeTerm(String tradeTerm){
		this.tradeTerm=tradeTerm;
	}
	/**贸易方式*/
	public String getTradeTerm(){
		return this.tradeTerm;
	}
	
	/**进口商*/
	public void setImportCustomer(String importCustomer){
		this.importCustomer=importCustomer;
	}
	/**进口商*/
	public String getImportCustomer(){
		return this.importCustomer;
	}
	
	/**进口用户*/
	public void setImportUser(String importUser){
		this.importUser=importUser;
	}
	/**进口用户*/
	public String getImportUser(){
		return this.importUser;
	}
	
	/**许可证数量*/
	public void setLicenceCount(Integer licenceCount){
		this.licenceCount=licenceCount;
	}
	/**许可证数量*/
	public Integer getLicenceCount(){
		return this.licenceCount;
	}
	
	/**报关口岸*/
	public void setDeclaratPort(String declaratPort){
		this.declaratPort=declaratPort;
	}
	/**报关口岸*/
	public String getDeclaratPort(){
		return this.declaratPort;
	}
	
	/**原产地国*/
	public void setCountryOrigin(String countryOrigin){
		this.countryOrigin=countryOrigin;
	}
	/**原产地国*/
	public String getCountryOrigin(){
		return this.countryOrigin;
	}
	
	/**报关单号*/
	public void setDeclaratNo(String declaratNo){
		this.declaratNo=declaratNo;
	}
	/**报关单号*/
	public String getDeclaratNo(){
		return this.declaratNo;
	}
	
	/**进口口岸*/
	public void setImportPort(String importPort){
		this.importPort=importPort;
	}
	/**进口口岸*/
	public String getImportPort(){
		return this.importPort;
	}
	
	/**出口口岸*/
	public void setExtendPort(String extendPort){
		this.extendPort=extendPort;
	}
	/**出口口岸*/
	public String getExtendPort(){
		return this.extendPort;
	}
	
	/**放行日期*/
	public void setClearanceDate(java.util.Date clearanceDate){
		this.clearanceDate=clearanceDate;
	}
	/**放行日期*/
	public java.util.Date getClearanceDate(){
		return this.clearanceDate;
	}
	
	/**项目名称*/
	public void setProjectName(String projectName){
		this.projectName=projectName;
	}
	/**项目名称*/
	public String getProjectName(){
		return this.projectName;
	}
	
	/**金额*/
	public void setAmount(java.math.BigDecimal amount){
		this.amount=amount;
	}
	/**金额*/
	public java.math.BigDecimal getAmount(){
		return this.amount;
	}
	
	/**付款单位*/
	public void setPaymentCompany(String paymentCompany){
		this.paymentCompany=paymentCompany;
	}
	/**付款单位*/
	public String getPaymentCompany(){
		return this.paymentCompany;
	}
	
	/**发票号*/
	public void setInvoiceNo(String invoiceNo){
		this.invoiceNo=invoiceNo;
	}
	/**发票号*/
	public String getInvoiceNo(){
		return this.invoiceNo;
	}
	
	/**报关发票类型*/
	public void setDeclaratInvoiceType(String declaratInvoiceType){
		this.declaratInvoiceType=declaratInvoiceType;
	}
	/**报关发票类型*/
	public String getDeclaratInvoiceType(){
		return this.declaratInvoiceType;
	}
	
	/**结算发票类型*/
	public void setSettlementInvoiceType(String settlementInvoiceType){
		this.settlementInvoiceType=settlementInvoiceType;
	}
	/**结算发票类型*/
	public String getSettlementInvoiceType(){
		return this.settlementInvoiceType;
	}
	
	/**手册号*/
	public void setManualNo(String manualNo){
		this.manualNo=manualNo;
	}
	/**手册号*/
	public String getManualNo(){
		return this.manualNo;
	}
	
	/**加工贸易类型*/
	public void setMachiningType(String machiningType){
		this.machiningType=machiningType;
	}
	/**加工贸易类型*/
	public String getMachiningType(){
		return this.machiningType;
	}
	
	/**主管海关*/
	public void setCiq(String ciq){
		this.ciq=ciq;
	}
	/**主管海关*/
	public String getCiq(){
		return this.ciq;
	}
	
	/**外商公司*/
	public void setForeignName(String foreignName){
		this.foreignName=foreignName;
	}
	/**外商公司*/
	public String getForeignName(){
		return this.foreignName;
	}
	
	/**出具方*/
	public void setIssue(String issue){
		this.issue=issue;
	}
	/**出具方*/
	public String getIssue(){
		return this.issue;
	}
	
	/**备注*/
	public void setRemark(String remark){
		this.remark=remark;
	}
	/**备注*/
	public String getRemark(){
		return this.remark;
	}
	
	/**版本号*/
	public void setVersion(Integer version){
		this.version=version;
	}
	/**版本号*/
	public Integer getVersion(){
		return this.version;
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
	
		
	
	public Map<String, Object> toMap() {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("orderDocumentId",this.orderDocumentId);
	map.put("orderDocumentUuid",this.orderDocumentUuid);
	map.put("orderId",this.orderId);
	map.put("orderNo",this.orderNo);
	map.put("contractNo",this.contractNo);
	map.put("shipName",this.shipName);
	map.put("documentType",this.documentType);
	map.put("billDate",this.billDate);
	map.put("unportDate",this.unportDate);
	map.put("oil",this.oil);
	map.put("survey",this.survey);
	map.put("licence",this.licence);
	map.put("licenceType",this.licenceType);
	map.put("tradeTerm",this.tradeTerm);
	map.put("importCustomer",this.importCustomer);
	map.put("importUser",this.importUser);
	map.put("licenceCount",this.licenceCount);
	map.put("declaratPort",this.declaratPort);
	map.put("countryOrigin",this.countryOrigin);
	map.put("declaratNo",this.declaratNo);
	map.put("importPort",this.importPort);
	map.put("extendPort",this.extendPort);
	map.put("clearanceDate",this.clearanceDate);
	map.put("projectName",this.projectName);
	map.put("amount",this.amount);
	map.put("paymentCompany",this.paymentCompany);
	map.put("invoiceNo",this.invoiceNo);
	map.put("declaratInvoiceType",this.declaratInvoiceType);
	map.put("settlementInvoiceType",this.settlementInvoiceType);
	map.put("manualNo",this.manualNo);
	map.put("machiningType",this.machiningType);
	map.put("ciq",this.ciq);
	map.put("foreignName",this.foreignName);
	map.put("issue",this.issue);
	map.put("remark",this.remark);
	map.put("version",this.version);
	map.put("langVer",this.langVer);
	map.put("aliveFlag",this.aliveFlag);
	map.put("createUser",this.createUser);
	map.put("createDate",this.createDate);
	map.put("updateUser",this.updateUser);
	map.put("updateDate",this.updateDate);
			return map;
	}
}