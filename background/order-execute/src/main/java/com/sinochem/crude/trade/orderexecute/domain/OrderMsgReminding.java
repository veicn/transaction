package com.sinochem.crude.trade.orderexecute.domain;

import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;

public class OrderMsgReminding implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**订单ID*/
	private Long orderId;  
	
	/**消息代码*/
	private String msgCode;  
	
	/**消息类型（sms:短信,mail:邮件,msg:站内信,app:APP推送）*/
	private String msgType;  
	
	/**消息标题*/
	private String msgTitle;  
	
	/**消息模板*/
	private String msgTemplate;  
	
	/**消息参数*/
	private String msgParams;  
	
	/**接收用户ID*/
	private Long receiveUserId;  
	
	/**最后一次执行时间*/
	private java.util.Date lastExecTime;  
	
	/**执行次数*/
	private Integer execCount;  
	
	/**状态（0：未执行，1：已执行，-1：失效）*/
	private String status;  
	
	/**计划执行时间*/
	private java.util.Date schedule;  
	
	/**创建时间*/
	private java.util.Date createDate;  
	
		/**订单ID*/
	public void setOrderId(Long orderId){
		this.orderId=orderId;
	}
	/**订单ID*/
	public Long getOrderId(){
		return this.orderId;
	}
	
	/**消息代码*/
	public void setMsgCode(String msgCode){
		this.msgCode=msgCode;
	}
	/**消息代码*/
	public String getMsgCode(){
		return this.msgCode;
	}
	
	/**消息类型（sms:短信,mail:邮件,msg:站内信,app:APP推送）*/
	public void setMsgType(String msgType){
		this.msgType=msgType;
	}
	/**消息类型（sms:短信,mail:邮件,msg:站内信,app:APP推送）*/
	public String getMsgType(){
		return this.msgType;
	}
	
	/**消息标题*/
	public void setMsgTitle(String msgTitle){
		this.msgTitle=msgTitle;
	}
	/**消息标题*/
	public String getMsgTitle(){
		return this.msgTitle;
	}
	
	/**消息模板*/
	public void setMsgTemplate(String msgTemplate){
		this.msgTemplate=msgTemplate;
	}
	/**消息模板*/
	public String getMsgTemplate(){
		return this.msgTemplate;
	}
	
	/**消息参数*/
	public void setMsgParams(String msgParams){
		this.msgParams=msgParams;
	}
	/**消息参数*/
	public String getMsgParams(){
		return this.msgParams;
	}
	
	/**接收用户ID*/
	public void setReceiveUserId(Long receiveUserId){
		this.receiveUserId=receiveUserId;
	}
	/**接收用户ID*/
	public Long getReceiveUserId(){
		return this.receiveUserId;
	}
	
	/**最后一次执行时间*/
	public void setLastExecTime(java.util.Date lastExecTime){
		this.lastExecTime=lastExecTime;
	}
	/**最后一次执行时间*/
	public java.util.Date getLastExecTime(){
		return this.lastExecTime;
	}
	
	/**执行次数*/
	public void setExecCount(Integer execCount){
		this.execCount=execCount;
	}
	/**执行次数*/
	public Integer getExecCount(){
		return this.execCount;
	}
	
	/**状态（0：未执行，1：已执行，-1：失效）*/
	public void setStatus(String status){
		this.status=status;
	}
	/**状态（0：未执行，1：已执行，-1：失效）*/
	public String getStatus(){
		return this.status;
	}
	
	/**计划执行时间*/
	public void setSchedule(java.util.Date schedule){
		this.schedule=schedule;
	}
	/**计划执行时间*/
	public java.util.Date getSchedule(){
		return this.schedule;
	}
	
	/**创建时间*/
	public void setCreateDate(java.util.Date createDate){
		this.createDate=createDate;
	}
	/**创建时间*/
	public java.util.Date getCreateDate(){
		return this.createDate;
	}
	
		
	
	public Map<String, Object> toMap() {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("orderId",this.orderId);
	map.put("msgCode",this.msgCode);
	map.put("msgType",this.msgType);
	map.put("msgTitle",this.msgTitle);
	map.put("msgTemplate",this.msgTemplate);
	map.put("msgParams",this.msgParams);
	map.put("receiveUserId",this.receiveUserId);
	map.put("lastExecTime",this.lastExecTime);
	map.put("execCount",this.execCount);
	map.put("status",this.status);
	map.put("schedule",this.schedule);
	map.put("createDate",this.createDate);
			return map;
	}
}