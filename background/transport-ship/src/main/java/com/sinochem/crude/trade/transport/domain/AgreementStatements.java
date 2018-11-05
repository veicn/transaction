package com.sinochem.crude.trade.transport.domain;

import java.io.Serializable;

public class AgreementStatements implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**业务唯一键*/
	private Long agreementStatementsId;  
	
	/**UUID*/
	private String uuid;  
	
	/**发票号码*/
	private String receiptCode;  
	
	/**协议id*/
	private Long shipAgreementId;  
	
	/**协议uuid*/
	private String shipAgreementUuid;  
	
	/**代理协议编号(船名+日期+委托方英文简称)*/
	private String agreementCode;  
	
	/**收款方*/
	private String payee;  
	
	/**付款方*/
	private String payer;  
	
	/**Freight Quantily*/
	private java.math.BigDecimal freightQuantily;  
	
	/**Freight Flatrate*/
	private java.math.BigDecimal freightFlatrate;  
	
	/**Freight Rate*/
	private java.math.BigDecimal freightRate;  
	
	/**Freight overage Quantily*/
	private java.math.BigDecimal freightOverageQuantily;  
	
	/**Freight overage Flatrate*/
	private java.math.BigDecimal freightOverageFlatrate;  
	
	/**Freight overage Rate*/
	private java.math.BigDecimal freightOverageRate;  
	
	/**Freight overage Pct*/
	private java.math.BigDecimal freightOveragePct;  
	
	/**Address Commission Quantily*/
	private java.math.BigDecimal addressCommissionQuantily;  
	
	/**Address Commission Pct*/
	private java.math.BigDecimal addressCommissionPct;  
	
	/**Freight Total*/
	private java.math.BigDecimal freightTotal;  
	
	/**Freight overage Total*/
	private java.math.BigDecimal freightOverageTotal;  
	
	/**Address Commission Total*/
	private java.math.BigDecimal addressCommissionTotal;  
	
	/**Freight Amount*/
	private java.math.BigDecimal freightAmount;  
	
	/**Freight overage Amount*/
	private java.math.BigDecimal freightOverageAmount;  
	
	/**Address Commission Amount*/
	private java.math.BigDecimal addressCommissionAmount;  
	
	/**Balance in USD*/
	private java.math.BigDecimal balance;  
	
	/**货主公司id*/
	private Long epMemberId;  
	
	/**代理公司ID*/
	private Long agentMemberId;  
	
	/**发票附件*/
	private String receiptAccessory;  
	
	/**发票附件路径*/
	private String receiptAccessoryPath;  
	
	/**船合同id*/
	private Long shipPactId;  
	
	/**船合同uuid*/
	private String shipPactUuid;  
	
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
	
	/**是否有效(1有效0无效)*/
	private String aliveFlag;  
	
		/**业务唯一键*/
	public void setAgreementStatementsId(Long agreementStatementsId){
		this.agreementStatementsId=agreementStatementsId;
	}
	/**业务唯一键*/
	public Long getAgreementStatementsId(){
		return this.agreementStatementsId;
	}
	
	/**UUID*/
	public void setUuid(String uuid){
		this.uuid=uuid;
	}
	/**UUID*/
	public String getUuid(){
		return this.uuid;
	}
	
	/**发票号码*/
	public void setReceiptCode(String receiptCode){
		this.receiptCode=receiptCode;
	}
	/**发票号码*/
	public String getReceiptCode(){
		return this.receiptCode;
	}
	
	/**协议id*/
	public void setShipAgreementId(Long shipAgreementId){
		this.shipAgreementId=shipAgreementId;
	}
	/**协议id*/
	public Long getShipAgreementId(){
		return this.shipAgreementId;
	}
	
	/**协议uuid*/
	public void setShipAgreementUuid(String shipAgreementUuid){
		this.shipAgreementUuid=shipAgreementUuid;
	}
	/**协议uuid*/
	public String getShipAgreementUuid(){
		return this.shipAgreementUuid;
	}
	
	/**代理协议编号(船名+日期+委托方英文简称)*/
	public void setAgreementCode(String agreementCode){
		this.agreementCode=agreementCode;
	}
	/**代理协议编号(船名+日期+委托方英文简称)*/
	public String getAgreementCode(){
		return this.agreementCode;
	}
	
	/**收款方*/
	public void setPayee(String payee){
		this.payee=payee;
	}
	/**收款方*/
	public String getPayee(){
		return this.payee;
	}
	
	/**付款方*/
	public void setPayer(String payer){
		this.payer=payer;
	}
	/**付款方*/
	public String getPayer(){
		return this.payer;
	}
	
	/**Freight Quantily*/
	public void setFreightQuantily(java.math.BigDecimal freightQuantily){
		this.freightQuantily=freightQuantily;
	}
	/**Freight Quantily*/
	public java.math.BigDecimal getFreightQuantily(){
		return this.freightQuantily;
	}
	
	/**Freight Flatrate*/
	public void setFreightFlatrate(java.math.BigDecimal freightFlatrate){
		this.freightFlatrate=freightFlatrate;
	}
	/**Freight Flatrate*/
	public java.math.BigDecimal getFreightFlatrate(){
		return this.freightFlatrate;
	}
	
	/**Freight Rate*/
	public void setFreightRate(java.math.BigDecimal freightRate){
		this.freightRate=freightRate;
	}
	/**Freight Rate*/
	public java.math.BigDecimal getFreightRate(){
		return this.freightRate;
	}
	
	/**Freight overage Quantily*/
	public void setFreightOverageQuantily(java.math.BigDecimal freightOverageQuantily){
		this.freightOverageQuantily=freightOverageQuantily;
	}
	/**Freight overage Quantily*/
	public java.math.BigDecimal getFreightOverageQuantily(){
		return this.freightOverageQuantily;
	}
	
	/**Freight overage Flatrate*/
	public void setFreightOverageFlatrate(java.math.BigDecimal freightOverageFlatrate){
		this.freightOverageFlatrate=freightOverageFlatrate;
	}
	/**Freight overage Flatrate*/
	public java.math.BigDecimal getFreightOverageFlatrate(){
		return this.freightOverageFlatrate;
	}
	
	/**Freight overage Rate*/
	public void setFreightOverageRate(java.math.BigDecimal freightOverageRate){
		this.freightOverageRate=freightOverageRate;
	}
	/**Freight overage Rate*/
	public java.math.BigDecimal getFreightOverageRate(){
		return this.freightOverageRate;
	}
	
	/**Freight overage Pct*/
	public void setFreightOveragePct(java.math.BigDecimal freightOveragePct){
		this.freightOveragePct=freightOveragePct;
	}
	/**Freight overage Pct*/
	public java.math.BigDecimal getFreightOveragePct(){
		return this.freightOveragePct;
	}
	
	/**Address Commission Quantily*/
	public void setAddressCommissionQuantily(java.math.BigDecimal addressCommissionQuantily){
		this.addressCommissionQuantily=addressCommissionQuantily;
	}
	/**Address Commission Quantily*/
	public java.math.BigDecimal getAddressCommissionQuantily(){
		return this.addressCommissionQuantily;
	}
	
	/**Address Commission Pct*/
	public void setAddressCommissionPct(java.math.BigDecimal addressCommissionPct){
		this.addressCommissionPct=addressCommissionPct;
	}
	/**Address Commission Pct*/
	public java.math.BigDecimal getAddressCommissionPct(){
		return this.addressCommissionPct;
	}
	
	/**Freight Total*/
	public void setFreightTotal(java.math.BigDecimal freightTotal){
		this.freightTotal=freightTotal;
	}
	/**Freight Total*/
	public java.math.BigDecimal getFreightTotal(){
		return this.freightTotal;
	}
	
	/**Freight overage Total*/
	public void setFreightOverageTotal(java.math.BigDecimal freightOverageTotal){
		this.freightOverageTotal=freightOverageTotal;
	}
	/**Freight overage Total*/
	public java.math.BigDecimal getFreightOverageTotal(){
		return this.freightOverageTotal;
	}
	
	/**Address Commission Total*/
	public void setAddressCommissionTotal(java.math.BigDecimal addressCommissionTotal){
		this.addressCommissionTotal=addressCommissionTotal;
	}
	/**Address Commission Total*/
	public java.math.BigDecimal getAddressCommissionTotal(){
		return this.addressCommissionTotal;
	}
	
	/**Freight Amount*/
	public void setFreightAmount(java.math.BigDecimal freightAmount){
		this.freightAmount=freightAmount;
	}
	/**Freight Amount*/
	public java.math.BigDecimal getFreightAmount(){
		return this.freightAmount;
	}
	
	/**Freight overage Amount*/
	public void setFreightOverageAmount(java.math.BigDecimal freightOverageAmount){
		this.freightOverageAmount=freightOverageAmount;
	}
	/**Freight overage Amount*/
	public java.math.BigDecimal getFreightOverageAmount(){
		return this.freightOverageAmount;
	}
	
	/**Address Commission Amount*/
	public void setAddressCommissionAmount(java.math.BigDecimal addressCommissionAmount){
		this.addressCommissionAmount=addressCommissionAmount;
	}
	/**Address Commission Amount*/
	public java.math.BigDecimal getAddressCommissionAmount(){
		return this.addressCommissionAmount;
	}
	
	/**Balance in USD*/
	public void setBalance(java.math.BigDecimal balance){
		this.balance=balance;
	}
	/**Balance in USD*/
	public java.math.BigDecimal getBalance(){
		return this.balance;
	}
	
	/**货主公司id*/
	public void setEpMemberId(Long epMemberId){
		this.epMemberId=epMemberId;
	}
	/**货主公司id*/
	public Long getEpMemberId(){
		return this.epMemberId;
	}
	
	/**代理公司ID*/
	public void setAgentMemberId(Long agentMemberId){
		this.agentMemberId=agentMemberId;
	}
	/**代理公司ID*/
	public Long getAgentMemberId(){
		return this.agentMemberId;
	}
	
	/**发票附件*/
	public void setReceiptAccessory(String receiptAccessory){
		this.receiptAccessory=receiptAccessory;
	}
	/**发票附件*/
	public String getReceiptAccessory(){
		return this.receiptAccessory;
	}
	
	/**发票附件路径*/
	public void setReceiptAccessoryPath(String receiptAccessoryPath){
		this.receiptAccessoryPath=receiptAccessoryPath;
	}
	/**发票附件路径*/
	public String getReceiptAccessoryPath(){
		return this.receiptAccessoryPath;
	}
	
	/**船合同id*/
	public void setShipPactId(Long shipPactId){
		this.shipPactId=shipPactId;
	}
	/**船合同id*/
	public Long getShipPactId(){
		return this.shipPactId;
	}
	
	/**船合同uuid*/
	public void setShipPactUuid(String shipPactUuid){
		this.shipPactUuid=shipPactUuid;
	}
	/**船合同uuid*/
	public String getShipPactUuid(){
		return this.shipPactUuid;
	}
	
	/**创建时间*/
	public void setCreateDate(java.util.Date createDate){
		this.createDate=createDate;
	}
	/**创建时间*/
	public java.util.Date getCreateDate(){
		return this.createDate;
	}
	
	/**修改时间*/
	public void setUpdateDate(java.util.Date updateDate){
		this.updateDate=updateDate;
	}
	/**修改时间*/
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
	
	/**是否有效(1有效0无效)*/
	public void setAliveFlag(String aliveFlag){
		this.aliveFlag=aliveFlag;
	}
	/**是否有效(1有效0无效)*/
	public String getAliveFlag(){
		return this.aliveFlag;
	}
	
		
	
	/*public Map<String, Object> toMap() {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("agreementStatementsId",this.agreementStatementsId);
	map.put("uuid",this.uuid);
	map.put("receiptCode",this.receiptCode);
	map.put("shipAgreementId",this.shipAgreementId);
	map.put("shipAgreementUuid",this.shipAgreementUuid);
	map.put("agreementCode",this.agreementCode);
	map.put("payee",this.payee);
	map.put("payer",this.payer);
	map.put("freightQuantily",this.freightQuantily);
	map.put("freightFlatrate",this.freightFlatrate);
	map.put("freightRate",this.freightRate);
	map.put("freightOverageQuantily",this.freightOverageQuantily);
	map.put("freightOverageFlatrate",this.freightOverageFlatrate);
	map.put("freightOverageRate",this.freightOverageRate);
	map.put("freightOveragePct",this.freightOveragePct);
	map.put("addressCommissionQuantily",this.addressCommissionQuantily);
	map.put("addressCommissionPct",this.addressCommissionPct);
	map.put("freightTotal",this.freightTotal);
	map.put("freightOverageTotal",this.freightOverageTotal);
	map.put("addressCommissionTotal2",this.addressCommissionTotal2);
	map.put("freightAmount",this.freightAmount);
	map.put("freightOverageAmount",this.freightOverageAmount);
	map.put("addressCommissionAmount",this.addressCommissionAmount);
	map.put("balance",this.balance);
	map.put("epMemberId",this.epMemberId);
	map.put("agentMemberId",this.agentMemberId);
	map.put("receiptAccessory",this.receiptAccessory);
	map.put("receiptAccessoryPath",this.receiptAccessoryPath);
	map.put("shipPactId",this.shipPactId);
	map.put("shipPactUuid",this.shipPactUuid);
	map.put("createDate",this.createDate);
	map.put("updateDate",this.updateDate);
	map.put("createUser",this.createUser);
	map.put("updateUser",this.updateUser);
	map.put("ext1",this.ext1);
	map.put("aliveFlag",this.aliveFlag);
			return map;
	}*/
}