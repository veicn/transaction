package com.sinochem.crude.trade.info.domain;

import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;

public class ExternalInteractive implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**自增主键*/
	private Long id;  
	
	/**对方系统代码*/
	private String sysCode;  
	
	/**业务接口名称*/
	private String method;  
	
	/**交互的数据格式(仅支持json)*/
	private String format;  
	
	/**交互的字符编码*/
	private String charset;  
	
	/**发送请求的时间*/
	private java.util.Date timeStamp;  
	
	/**业务参数*/
	private String bizContent;  
	
	/**接收到请求的时间*/
	private java.util.Date receiveDate;  
	
	/**网关处理结果*/
	private String returnMsg;  
	
	/**业务处理结果*/
	private String returnSubMsg;  
	
	/**业务返回参数*/
	private String returnBizContent;  
	
	/**返回处理结果的时间*/
	private java.util.Date returnDate;  
	
	/**数据状态*/
	private String aliveFlag;  
	
		/**自增主键*/
	public void setId(Long id){
		this.id=id;
	}
	/**自增主键*/
	public Long getId(){
		return this.id;
	}
	
	/**对方系统代码*/
	public void setSysCode(String sysCode){
		this.sysCode=sysCode;
	}
	/**对方系统代码*/
	public String getSysCode(){
		return this.sysCode;
	}
	
	/**业务接口名称*/
	public void setMethod(String method){
		this.method=method;
	}
	/**业务接口名称*/
	public String getMethod(){
		return this.method;
	}
	
	/**交互的数据格式(仅支持json)*/
	public void setFormat(String format){
		this.format=format;
	}
	/**交互的数据格式(仅支持json)*/
	public String getFormat(){
		return this.format;
	}
	
	/**交互的字符编码*/
	public void setCharset(String charset){
		this.charset=charset;
	}
	/**交互的字符编码*/
	public String getCharset(){
		return this.charset;
	}
	
	/**发送请求的时间*/
	public void setTimeStamp(java.util.Date timeStamp){
		this.timeStamp=timeStamp;
	}
	/**发送请求的时间*/
	public java.util.Date getTimeStamp(){
		return this.timeStamp;
	}
	
	/**业务参数*/
	public void setBizContent(String bizContent){
		this.bizContent=bizContent;
	}
	/**业务参数*/
	public String getBizContent(){
		return this.bizContent;
	}
	
	/**接收到请求的时间*/
	public void setReceiveDate(java.util.Date receiveDate){
		this.receiveDate=receiveDate;
	}
	/**接收到请求的时间*/
	public java.util.Date getReceiveDate(){
		return this.receiveDate;
	}
	
	/**网关处理结果*/
	public void setReturnMsg(String returnMsg){
		this.returnMsg=returnMsg;
	}
	/**网关处理结果*/
	public String getReturnMsg(){
		return this.returnMsg;
	}
	
	/**业务处理结果*/
	public void setReturnSubMsg(String returnSubMsg){
		this.returnSubMsg=returnSubMsg;
	}
	/**业务处理结果*/
	public String getReturnSubMsg(){
		return this.returnSubMsg;
	}
	
	/**业务返回参数*/
	public void setReturnBizContent(String returnBizContent){
		this.returnBizContent=returnBizContent;
	}
	/**业务返回参数*/
	public String getReturnBizContent(){
		return this.returnBizContent;
	}
	
	/**返回处理结果的时间*/
	public void setReturnDate(java.util.Date returnDate){
		this.returnDate=returnDate;
	}
	/**返回处理结果的时间*/
	public java.util.Date getReturnDate(){
		return this.returnDate;
	}
	
	/**数据状态*/
	public void setAliveFlag(String aliveFlag){
		this.aliveFlag=aliveFlag;
	}
	/**数据状态*/
	public String getAliveFlag(){
		return this.aliveFlag;
	}
	
		
	
	public Map<String, Object> toMap() {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id",this.id);
	map.put("sysCode",this.sysCode);
	map.put("method",this.method);
	map.put("format",this.format);
	map.put("charset",this.charset);
	map.put("timeStamp",this.timeStamp);
	map.put("bizContent",this.bizContent);
	map.put("receiveDate",this.receiveDate);
	map.put("returnMsg",this.returnMsg);
	map.put("returnSubMsg",this.returnSubMsg);
	map.put("returnBizContent",this.returnBizContent);
	map.put("returnDate",this.returnDate);
	map.put("aliveFlag",this.aliveFlag);
			return map;
	}
	
}