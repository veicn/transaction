package com.sinochem.crude.trade.orderexecute.domain;

import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;

public class OrderFeeDissent implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**ID*/
	private Long feeDissentId;  
	
	/**ORDER_ID*/
	private Long orderId;  
	
	/**异议类型（1对账单 2结算单）*/
	private String dissentType;  
	
	/**单据ID*/
	private Long billId;  
	
	/**是否回复*/
	private String isReply;  
	
	/**驳回原因*/
	private String dissentContent;  
	
	/**回复内容*/
	private String replyContent;  
	
	/**异议时间*/
	private java.util.Date dissentTime;  
	
	/**版本号*/
	private Integer version;  
	
	/**异议人*/
	private Long dissentPerson;  
	
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
	public void setFeeDissentId(Long feeDissentId){
		this.feeDissentId=feeDissentId;
	}
	/**ID*/
	public Long getFeeDissentId(){
		return this.feeDissentId;
	}
	
	/**ORDER_ID*/
	public void setOrderId(Long orderId){
		this.orderId=orderId;
	}
	/**ORDER_ID*/
	public Long getOrderId(){
		return this.orderId;
	}
	
	/**异议类型（1对账单 2结算单）*/
	public void setDissentType(String dissentType){
		this.dissentType=dissentType;
	}
	/**异议类型（1对账单 2结算单）*/
	public String getDissentType(){
		return this.dissentType;
	}
	
	/**单据ID*/
	public void setBillId(Long billId){
		this.billId=billId;
	}
	/**单据ID*/
	public Long getBillId(){
		return this.billId;
	}
	
	/**是否回复*/
	public void setIsReply(String isReply){
		this.isReply=isReply;
	}
	/**是否回复*/
	public String getIsReply(){
		return this.isReply;
	}
	
	/**驳回原因*/
	public void setDissentContent(String dissentContent){
		this.dissentContent=dissentContent;
	}
	/**驳回原因*/
	public String getDissentContent(){
		return this.dissentContent;
	}
	
	/**回复内容*/
	public void setReplyContent(String replyContent){
		this.replyContent=replyContent;
	}
	/**回复内容*/
	public String getReplyContent(){
		return this.replyContent;
	}
	
	/**异议时间*/
	public void setDissentTime(java.util.Date dissentTime){
		this.dissentTime=dissentTime;
	}
	/**异议时间*/
	public java.util.Date getDissentTime(){
		return this.dissentTime;
	}
	
	/**版本号*/
	public void setVersion(Integer version){
		this.version=version;
	}
	/**版本号*/
	public Integer getVersion(){
		return this.version;
	}
	
	/**异议人*/
	public void setDissentPerson(Long dissentPerson){
		this.dissentPerson=dissentPerson;
	}
	/**异议人*/
	public Long getDissentPerson(){
		return this.dissentPerson;
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
		map.put("feeDissentId",this.feeDissentId);
	map.put("orderId",this.orderId);
	map.put("dissentType",this.dissentType);
	map.put("billId",this.billId);
	map.put("isReply",this.isReply);
	map.put("dissentContent",this.dissentContent);
	map.put("replyContent",this.replyContent);
	map.put("dissentTime",this.dissentTime);
	map.put("version",this.version);
	map.put("dissentPerson",this.dissentPerson);
	map.put("langVer",this.langVer);
	map.put("aliveFlag",this.aliveFlag);
	map.put("createUser",this.createUser);
	map.put("createDate",this.createDate);
	map.put("updateUser",this.updateUser);
	map.put("updateDate",this.updateDate);
			return map;
	}
}