package com.sinochem.crude.trade.orderexecute.domain;

import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;

public class OrderParty implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**ID*/
	private Long partyId;  
	
	/**ORDER_ID*/
	private Long orderId;  
	
	/**交易方(1买方 2卖方 3代理商)*/
	private String partyType;  
	
	/**企业名称*/
	private String customerName;  
	
	/**企业ID*/
	private Long customerId;  
	
	/**企业地址*/
	private String address;  
	
	/**联系人*/
	private String contactName;  
	
	/**联系电话*/
	private String tel;  
	
	/**邮箱*/
	private String email;  
	
	/**传真*/
	private String fax;  
	
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
	public void setPartyId(Long partyId){
		this.partyId=partyId;
	}
	/**ID*/
	public Long getPartyId(){
		return this.partyId;
	}
	
	/**ORDER_ID*/
	public void setOrderId(Long orderId){
		this.orderId=orderId;
	}
	/**ORDER_ID*/
	public Long getOrderId(){
		return this.orderId;
	}
	
	/**交易方(1买方 2卖方 3代理商)*/
	public void setPartyType(String partyType){
		this.partyType=partyType;
	}
	/**交易方(1买方 2卖方 3代理商)*/
	public String getPartyType(){
		return this.partyType;
	}
	
	/**企业名称*/
	public void setCustomerName(String customerName){
		this.customerName=customerName;
	}
	/**企业名称*/
	public String getCustomerName(){
		return this.customerName;
	}
	
	/**企业ID*/
	public void setCustomerId(Long customerId){
		this.customerId=customerId;
	}
	/**企业ID*/
	public Long getCustomerId(){
		return this.customerId;
	}
	
	/**企业地址*/
	public void setAddress(String address){
		this.address=address;
	}
	/**企业地址*/
	public String getAddress(){
		return this.address;
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
	
	/**传真*/
	public void setFax(String fax){
		this.fax=fax;
	}
	/**传真*/
	public String getFax(){
		return this.fax;
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
		map.put("partyId",this.partyId);
	map.put("orderId",this.orderId);
	map.put("partyType",this.partyType);
	map.put("customerName",this.customerName);
	map.put("customerId",this.customerId);
	map.put("address",this.address);
	map.put("contactName",this.contactName);
	map.put("tel",this.tel);
	map.put("email",this.email);
	map.put("fax",this.fax);
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