package com.sinochem.crude.trade.orderexecute.domain;

import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;

public class TriggerTransfer implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**ID*/
	private Long id;  
	
	/**UUID*/
	private String uuid;  
	
	/**合约ID*/
	private Long contractId;  
	
	/**订单ID*/
	private Long orderId;  
	
	/**原合约月份*/
	private String oldMonth;  
	
	/**月份*/
	private String month;  
	
	/**合约名称*/
	private String name;  
	
	/**转月时间*/
	private java.util.Date transferTime;  
	
	/**转月数量*/
	private java.math.BigDecimal transferQuantity;  
	
	/**月差*/
	private java.math.BigDecimal differPrice;  
	
	/**月差额*/
	private java.math.BigDecimal differAmount;  
	
	/**已点数量*/
	private java.math.BigDecimal doneQuantity;  
	
	/**未点数量*/
	private java.math.BigDecimal surplusQuantity;  
	
	/**转月手续费*/
	private java.math.BigDecimal serviceFee;  
	
	/**手续费金额*/
	private java.math.BigDecimal serviceAmount;  
	
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
	
	/**合约ID*/
	public void setContractId(Long contractId){
		this.contractId=contractId;
	}
	/**合约ID*/
	public Long getContractId(){
		return this.contractId;
	}
	
	/**订单ID*/
	public void setOrderId(Long orderId){
		this.orderId=orderId;
	}
	/**订单ID*/
	public Long getOrderId(){
		return this.orderId;
	}
	
	/**原合约月份*/
	public void setOldMonth(String oldMonth){
		this.oldMonth=oldMonth;
	}
	/**原合约月份*/
	public String getOldMonth(){
		return this.oldMonth;
	}
	
	/**月份*/
	public void setMonth(String month){
		this.month=month;
	}
	/**月份*/
	public String getMonth(){
		return this.month;
	}
	
	/**合约名称*/
	public void setName(String name){
		this.name=name;
	}
	/**合约名称*/
	public String getName(){
		return this.name;
	}
	
	/**转月时间*/
	public void setTransferTime(java.util.Date transferTime){
		this.transferTime=transferTime;
	}
	/**转月时间*/
	public java.util.Date getTransferTime(){
		return this.transferTime;
	}
	
	/**转月数量*/
	public void setTransferQuantity(java.math.BigDecimal transferQuantity){
		this.transferQuantity=transferQuantity;
	}
	/**转月数量*/
	public java.math.BigDecimal getTransferQuantity(){
		return this.transferQuantity;
	}
	
	/**月差*/
	public void setDifferPrice(java.math.BigDecimal differPrice){
		this.differPrice=differPrice;
	}
	/**月差*/
	public java.math.BigDecimal getDifferPrice(){
		return this.differPrice;
	}
	
	/**月差额*/
	public void setDifferAmount(java.math.BigDecimal differAmount){
		this.differAmount=differAmount;
	}
	/**月差额*/
	public java.math.BigDecimal getDifferAmount(){
		return this.differAmount;
	}
	
	/**已点数量*/
	public void setDoneQuantity(java.math.BigDecimal doneQuantity){
		this.doneQuantity=doneQuantity;
	}
	/**已点数量*/
	public java.math.BigDecimal getDoneQuantity(){
		return this.doneQuantity;
	}
	
	/**未点数量*/
	public void setSurplusQuantity(java.math.BigDecimal surplusQuantity){
		this.surplusQuantity=surplusQuantity;
	}
	/**未点数量*/
	public java.math.BigDecimal getSurplusQuantity(){
		return this.surplusQuantity;
	}
	
	/**转月手续费*/
	public void setServiceFee(java.math.BigDecimal serviceFee){
		this.serviceFee=serviceFee;
	}
	/**转月手续费*/
	public java.math.BigDecimal getServiceFee(){
		return this.serviceFee;
	}
	
	/**手续费金额*/
	public void setServiceAmount(java.math.BigDecimal serviceAmount){
		this.serviceAmount=serviceAmount;
	}
	/**手续费金额*/
	public java.math.BigDecimal getServiceAmount(){
		return this.serviceAmount;
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
	map.put("contractId",this.contractId);
	map.put("orderId",this.orderId);
	map.put("oldMonth",this.oldMonth);
	map.put("month",this.month);
	map.put("name",this.name);
	map.put("transferTime",this.transferTime);
	map.put("transferQuantity",this.transferQuantity);
	map.put("differPrice",this.differPrice);
	map.put("differAmount",this.differAmount);
	map.put("doneQuantity",this.doneQuantity);
	map.put("surplusQuantity",this.surplusQuantity);
	map.put("serviceFee",this.serviceFee);
	map.put("serviceAmount",this.serviceAmount);
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