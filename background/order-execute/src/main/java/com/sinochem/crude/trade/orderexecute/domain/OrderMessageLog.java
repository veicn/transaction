package com.sinochem.crude.trade.orderexecute.domain;

import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;

public class OrderMessageLog implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**ID*/
	private Long messageId;  
	
	/**ORDER_ID*/
	private Long orderId;  
	
	/**标题*/
	private String messageTile;  
	
	/**内容*/
	private String content;  
	
	/**类型（1业务提醒 2文件提醒 ）*/
	private String messageType;  
	
	/**发送方*/
	private Long fromPerson;  
	
	/**接收方*/
	private Long toPerson;  
	
	/**发送状态*/
	private String status;  
	
	/**发送时间*/
	private java.util.Date sendTime;  
	
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
	public void setMessageId(Long messageId){
		this.messageId=messageId;
	}
	/**ID*/
	public Long getMessageId(){
		return this.messageId;
	}
	
	/**ORDER_ID*/
	public void setOrderId(Long orderId){
		this.orderId=orderId;
	}
	/**ORDER_ID*/
	public Long getOrderId(){
		return this.orderId;
	}
	
	/**标题*/
	public void setMessageTile(String messageTile){
		this.messageTile=messageTile;
	}
	/**标题*/
	public String getMessageTile(){
		return this.messageTile;
	}
	
	/**内容*/
	public void setContent(String content){
		this.content=content;
	}
	/**内容*/
	public String getContent(){
		return this.content;
	}
	
	/**类型（1业务提醒 2文件提醒 ）*/
	public void setMessageType(String messageType){
		this.messageType=messageType;
	}
	/**类型（1业务提醒 2文件提醒 ）*/
	public String getMessageType(){
		return this.messageType;
	}
	
	/**发送方*/
	public void setFromPerson(Long fromPerson){
		this.fromPerson=fromPerson;
	}
	/**发送方*/
	public Long getFromPerson(){
		return this.fromPerson;
	}
	
	/**接收方*/
	public void setToPerson(Long toPerson){
		this.toPerson=toPerson;
	}
	/**接收方*/
	public Long getToPerson(){
		return this.toPerson;
	}
	
	/**发送状态*/
	public void setStatus(String status){
		this.status=status;
	}
	/**发送状态*/
	public String getStatus(){
		return this.status;
	}
	
	/**发送时间*/
	public void setSendTime(java.util.Date sendTime){
		this.sendTime=sendTime;
	}
	/**发送时间*/
	public java.util.Date getSendTime(){
		return this.sendTime;
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
		map.put("messageId",this.messageId);
	map.put("orderId",this.orderId);
	map.put("messageTile",this.messageTile);
	map.put("content",this.content);
	map.put("messageType",this.messageType);
	map.put("fromPerson",this.fromPerson);
	map.put("toPerson",this.toPerson);
	map.put("status",this.status);
	map.put("sendTime",this.sendTime);
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