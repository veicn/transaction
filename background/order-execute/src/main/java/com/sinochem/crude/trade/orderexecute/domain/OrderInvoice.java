package com.sinochem.crude.trade.orderexecute.domain;

import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;

public class OrderInvoice implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**开票ID*/
	private Long id;  
	
	/**订单ID*/
	private Long orderId;  
	
	/**合同ID*/
	private Long contractId;  
	
	/**结算单ID*/
	private Long settleId;  
	
	/**发票号*/
	private String invoiceNo;  
	
	/**发票类型*/
	private String invoiceType;  
	
	/**发票金额*/
	private java.math.BigDecimal invoiceMoney;  
	
	/**申请开票日期*/
	private java.util.Date applyDate;  
	
	/**申请开票项目*/
	private String applyItem;  
	
	/**申请人*/
	private String applyPerson;  
	
	/**开票日期*/
	private java.util.Date invoiceDate;  
	
	/**实开项目*/
	private String invoiceItem;  
	
	/**卖方会员编号*/
	private Long sCustId;  
	
	/**卖方会员名称*/
	private String sCustName;  
	
	/**买方会员编号*/
	private Long bCustId;  
	
	/**买方会员名称*/
	private String bCustName;  
	
	/**备注*/
	private String remarks;  
	
	/**开票状态*/
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
	
		/**开票ID*/
	public void setId(Long id){
		this.id=id;
	}
	/**开票ID*/
	public Long getId(){
		return this.id;
	}
	
	/**订单ID*/
	public void setOrderId(Long orderId){
		this.orderId=orderId;
	}
	/**订单ID*/
	public Long getOrderId(){
		return this.orderId;
	}
	
	/**合同ID*/
	public void setContractId(Long contractId){
		this.contractId=contractId;
	}
	/**合同ID*/
	public Long getContractId(){
		return this.contractId;
	}
	
	/**结算单ID*/
	public void setSettleId(Long settleId){
		this.settleId=settleId;
	}
	/**结算单ID*/
	public Long getSettleId(){
		return this.settleId;
	}
	
	/**发票号*/
	public void setInvoiceNo(String invoiceNo){
		this.invoiceNo=invoiceNo;
	}
	/**发票号*/
	public String getInvoiceNo(){
		return this.invoiceNo;
	}
	
	/**发票类型*/
	public void setInvoiceType(String invoiceType){
		this.invoiceType=invoiceType;
	}
	/**发票类型*/
	public String getInvoiceType(){
		return this.invoiceType;
	}
	
	/**发票金额*/
	public void setInvoiceMoney(java.math.BigDecimal invoiceMoney){
		this.invoiceMoney=invoiceMoney;
	}
	/**发票金额*/
	public java.math.BigDecimal getInvoiceMoney(){
		return this.invoiceMoney;
	}
	
	/**申请开票日期*/
	public void setApplyDate(java.util.Date applyDate){
		this.applyDate=applyDate;
	}
	/**申请开票日期*/
	public java.util.Date getApplyDate(){
		return this.applyDate;
	}
	
	/**申请开票项目*/
	public void setApplyItem(String applyItem){
		this.applyItem=applyItem;
	}
	/**申请开票项目*/
	public String getApplyItem(){
		return this.applyItem;
	}
	
	/**申请人*/
	public void setApplyPerson(String applyPerson){
		this.applyPerson=applyPerson;
	}
	/**申请人*/
	public String getApplyPerson(){
		return this.applyPerson;
	}
	
	/**开票日期*/
	public void setInvoiceDate(java.util.Date invoiceDate){
		this.invoiceDate=invoiceDate;
	}
	/**开票日期*/
	public java.util.Date getInvoiceDate(){
		return this.invoiceDate;
	}
	
	/**实开项目*/
	public void setInvoiceItem(String invoiceItem){
		this.invoiceItem=invoiceItem;
	}
	/**实开项目*/
	public String getInvoiceItem(){
		return this.invoiceItem;
	}
	
	/**卖方会员编号*/
	public void setSCustId(Long sCustId){
		this.sCustId=sCustId;
	}
	/**卖方会员编号*/
	public Long getSCustId(){
		return this.sCustId;
	}
	
	/**卖方会员名称*/
	public void setSCustName(String sCustName){
		this.sCustName=sCustName;
	}
	/**卖方会员名称*/
	public String getSCustName(){
		return this.sCustName;
	}
	
	/**买方会员编号*/
	public void setBCustId(Long bCustId){
		this.bCustId=bCustId;
	}
	/**买方会员编号*/
	public Long getBCustId(){
		return this.bCustId;
	}
	
	/**买方会员名称*/
	public void setBCustName(String bCustName){
		this.bCustName=bCustName;
	}
	/**买方会员名称*/
	public String getBCustName(){
		return this.bCustName;
	}
	
	/**备注*/
	public void setRemarks(String remarks){
		this.remarks=remarks;
	}
	/**备注*/
	public String getRemarks(){
		return this.remarks;
	}
	
	/**开票状态*/
	public void setStatus(String status){
		this.status=status;
	}
	/**开票状态*/
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
	map.put("orderId",this.orderId);
	map.put("contractId",this.contractId);
	map.put("settleId",this.settleId);
	map.put("invoiceNo",this.invoiceNo);
	map.put("invoiceType",this.invoiceType);
	map.put("invoiceMoney",this.invoiceMoney);
	map.put("applyDate",this.applyDate);
	map.put("applyItem",this.applyItem);
	map.put("applyPerson",this.applyPerson);
	map.put("invoiceDate",this.invoiceDate);
	map.put("invoiceItem",this.invoiceItem);
	map.put("sCustId",this.sCustId);
	map.put("sCustName",this.sCustName);
	map.put("bCustId",this.bCustId);
	map.put("bCustName",this.bCustName);
	map.put("remarks",this.remarks);
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