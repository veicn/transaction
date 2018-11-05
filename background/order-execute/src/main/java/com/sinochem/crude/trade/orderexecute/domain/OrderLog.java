package com.sinochem.crude.trade.orderexecute.domain;

import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;

public class OrderLog implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**ID*/
	private Long logId;  
	
	/**ORDER_ID*/
	private Long orderId;  
	
	/**订单编号*/
	private String orderNo;  
	
	/**操作类型*/
	private String type;  
	
	/**操作描述*/
	private String content;  
	
	/**操作人*/
	private Long person;  
	
	/**操作时间*/
	private java.util.Date time;  
	
	/**类名*/
	private String className;  
	
	/**方法名*/
	private String methodName;  
	
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
	public void setLogId(Long logId){
		this.logId=logId;
	}
	/**ID*/
	public Long getLogId(){
		return this.logId;
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
	
	/**操作类型*/
	public void setType(String type){
		this.type=type;
	}
	/**操作类型*/
	public String getType(){
		return this.type;
	}
	
	/**操作描述*/
	public void setContent(String content){
		this.content=content;
	}
	/**操作描述*/
	public String getContent(){
		return this.content;
	}
	
	/**操作人*/
	public void setPerson(Long person){
		this.person=person;
	}
	/**操作人*/
	public Long getPerson(){
		return this.person;
	}
	
	/**操作时间*/
	public void setTime(java.util.Date time){
		this.time=time;
	}
	/**操作时间*/
	public java.util.Date getTime(){
		return this.time;
	}
	
	/**类名*/
	public void setClassName(String className){
		this.className=className;
	}
	/**类名*/
	public String getClassName(){
		return this.className;
	}
	
	/**方法名*/
	public void setMethodName(String methodName){
		this.methodName=methodName;
	}
	/**方法名*/
	public String getMethodName(){
		return this.methodName;
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
		map.put("logId",this.logId);
	map.put("orderId",this.orderId);
	map.put("orderNo",this.orderNo);
	map.put("type",this.type);
	map.put("content",this.content);
	map.put("person",this.person);
	map.put("time",this.time);
	map.put("className",this.className);
	map.put("methodName",this.methodName);
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