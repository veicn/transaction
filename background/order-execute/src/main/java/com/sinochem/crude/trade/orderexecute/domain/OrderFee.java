package com.sinochem.crude.trade.orderexecute.domain;

import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;

public class OrderFee implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**ID*/
	private Long orderFeeId;  
	
	/**UUID*/
	private String orderFeeUuid;  
	
	/**ORDER_ID*/
	private Long orderId;  
	
	/**订单编号*/
	private String orderNo;  
	
	/**费用分类(0订单 1预对账 2预结算 3终对账4终结算)*/
	private String feeType;  
	
	/**外贸合同号*/
	private String contractNo;  
	
	/**代理协议号*/
	private String agentNo;  
	
	/**船名*/
	private String shipName;  
	
	/**买方公司名称*/
	private String buyerCustomerName;  
	
	/**油品分类*/
	private String oil;  
	
	/**数量*/
	private java.math.BigDecimal quantity;  
	
	/**品种*/
	private String breed;  
	
	/**规格*/
	private String spec;  
	
	/**费用合计*/
	private java.math.BigDecimal totalFee;  
	
	/**版本号*/
	private Integer version;  
	
	/**修改时间*/
	private java.util.Date updateDate;  
	
	/**修改人*/
	private Long updateUser;  
	
	/**创建时间*/
	private java.util.Date createDate;  
	
	/**创建人*/
	private Long createUser;  
	
	/**当前有效状态*/
	private String aliveFlag;  
	
	/**语言类型*/
	private String langVer;  
	
		/**ID*/
	public void setOrderFeeId(Long orderFeeId){
		this.orderFeeId=orderFeeId;
	}
	/**ID*/
	public Long getOrderFeeId(){
		return this.orderFeeId;
	}
	
	/**UUID*/
	public void setOrderFeeUuid(String orderFeeUuid){
		this.orderFeeUuid=orderFeeUuid;
	}
	/**UUID*/
	public String getOrderFeeUuid(){
		return this.orderFeeUuid;
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
	
	/**费用分类(0订单 1预对账 2预结算 3终对账4终结算)*/
	public void setFeeType(String feeType){
		this.feeType=feeType;
	}
	/**费用分类(0订单 1预对账 2预结算 3终对账4终结算)*/
	public String getFeeType(){
		return this.feeType;
	}
	
	/**外贸合同号*/
	public void setContractNo(String contractNo){
		this.contractNo=contractNo;
	}
	/**外贸合同号*/
	public String getContractNo(){
		return this.contractNo;
	}
	
	/**代理协议号*/
	public void setAgentNo(String agentNo){
		this.agentNo=agentNo;
	}
	/**代理协议号*/
	public String getAgentNo(){
		return this.agentNo;
	}
	
	/**船名*/
	public void setShipName(String shipName){
		this.shipName=shipName;
	}
	/**船名*/
	public String getShipName(){
		return this.shipName;
	}
	
	/**买方公司名称*/
	public void setBuyerCustomerName(String buyerCustomerName){
		this.buyerCustomerName=buyerCustomerName;
	}
	/**买方公司名称*/
	public String getBuyerCustomerName(){
		return this.buyerCustomerName;
	}
	
	/**油品分类*/
	public void setOil(String oil){
		this.oil=oil;
	}
	/**油品分类*/
	public String getOil(){
		return this.oil;
	}
	
	/**数量*/
	public void setQuantity(java.math.BigDecimal quantity){
		this.quantity=quantity;
	}
	/**数量*/
	public java.math.BigDecimal getQuantity(){
		return this.quantity;
	}
	
	/**品种*/
	public void setBreed(String breed){
		this.breed=breed;
	}
	/**品种*/
	public String getBreed(){
		return this.breed;
	}
	
	/**规格*/
	public void setSpec(String spec){
		this.spec=spec;
	}
	/**规格*/
	public String getSpec(){
		return this.spec;
	}
	
	/**费用合计*/
	public void setTotalFee(java.math.BigDecimal totalFee){
		this.totalFee=totalFee;
	}
	/**费用合计*/
	public java.math.BigDecimal getTotalFee(){
		return this.totalFee;
	}
	
	/**版本号*/
	public void setVersion(Integer version){
		this.version=version;
	}
	/**版本号*/
	public Integer getVersion(){
		return this.version;
	}
	
	/**修改时间*/
	public void setUpdateDate(java.util.Date updateDate){
		this.updateDate=updateDate;
	}
	/**修改时间*/
	public java.util.Date getUpdateDate(){
		return this.updateDate;
	}
	
	/**修改人*/
	public void setUpdateUser(Long updateUser){
		this.updateUser=updateUser;
	}
	/**修改人*/
	public Long getUpdateUser(){
		return this.updateUser;
	}
	
	/**创建时间*/
	public void setCreateDate(java.util.Date createDate){
		this.createDate=createDate;
	}
	/**创建时间*/
	public java.util.Date getCreateDate(){
		return this.createDate;
	}
	
	/**创建人*/
	public void setCreateUser(Long createUser){
		this.createUser=createUser;
	}
	/**创建人*/
	public Long getCreateUser(){
		return this.createUser;
	}
	
	/**当前有效状态*/
	public void setAliveFlag(String aliveFlag){
		this.aliveFlag=aliveFlag;
	}
	/**当前有效状态*/
	public String getAliveFlag(){
		return this.aliveFlag;
	}
	
	/**语言类型*/
	public void setLangVer(String langVer){
		this.langVer=langVer;
	}
	/**语言类型*/
	public String getLangVer(){
		return this.langVer;
	}
	
		
	
	public Map<String, Object> toMap() {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("orderFeeId",this.orderFeeId);
	map.put("orderFeeUuid",this.orderFeeUuid);
	map.put("orderId",this.orderId);
	map.put("orderNo",this.orderNo);
	map.put("feeType",this.feeType);
	map.put("contractNo",this.contractNo);
	map.put("agentNo",this.agentNo);
	map.put("shipName",this.shipName);
	map.put("buyerCustomerName",this.buyerCustomerName);
	map.put("oil",this.oil);
	map.put("quantity",this.quantity);
	map.put("breed",this.breed);
	map.put("spec",this.spec);
	map.put("totalFee",this.totalFee);
	map.put("version",this.version);
	map.put("updateDate",this.updateDate);
	map.put("updateUser",this.updateUser);
	map.put("createDate",this.createDate);
	map.put("createUser",this.createUser);
	map.put("aliveFlag",this.aliveFlag);
	map.put("langVer",this.langVer);
			return map;
	}
}