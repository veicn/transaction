package com.sinochem.crude.trade.info.domain;

import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;

public class InfoInterface implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**自增主键*/
	private Long id;  
	
	/**UUID*/
	private String uuid;  
	
	/**外部唯一键*/
	private String externalId;  
	
	/**资讯分类名称*/
	private String channelName;  
	
	/**标题*/
	private String infoTitle;  
	
	/**内容，可解析的html*/
	private String infoContent;  
	
	/**作者*/
	private String author;  
	
	/**来源*/
	private String infoSource;  
	
	/**来源链接*/
	private String infoSourceUrl;  
	
	/**标签,json数组的字符串*/
	private String infoTag;  
	
	/**外部最新编辑时间*/
	private java.util.Date editTime;  
	
	/**外部系统名称*/
	private String externalSystem;  
	
	/**接收日期*/
	private java.util.Date interfaceDate;  
	
	/**处理人*/
	private String processPerson;  
	
	/**处理时间*/
	private java.util.Date processDate;  
	
	/**状态(00：未处理，01：已使用，02：已弃用)*/
	private String status;  
	
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
	
	/**UUID*/
	public void setUuid(String uuid){
		this.uuid=uuid;
	}
	/**UUID*/
	public String getUuid(){
		return this.uuid;
	}
	
	/**外部唯一键*/
	public void setExternalId(String externalId){
		this.externalId=externalId;
	}
	/**外部唯一键*/
	public String getExternalId(){
		return this.externalId;
	}
	
	/**资讯分类名称*/
	public void setChannelName(String channelName){
		this.channelName=channelName;
	}
	/**资讯分类名称*/
	public String getChannelName(){
		return this.channelName;
	}
	
	/**标题*/
	public void setInfoTitle(String infoTitle){
		this.infoTitle=infoTitle;
	}
	/**标题*/
	public String getInfoTitle(){
		return this.infoTitle;
	}
	
	/**内容，可解析的html*/
	public void setInfoContent(String infoContent){
		this.infoContent=infoContent;
	}
	/**内容，可解析的html*/
	public String getInfoContent(){
		return this.infoContent;
	}
	
	/**作者*/
	public void setAuthor(String author){
		this.author=author;
	}
	/**作者*/
	public String getAuthor(){
		return this.author;
	}
	
	/**来源*/
	public void setInfoSource(String infoSource){
		this.infoSource=infoSource;
	}
	/**来源*/
	public String getInfoSource(){
		return this.infoSource;
	}
	
	/**来源链接*/
	public void setInfoSourceUrl(String infoSourceUrl){
		this.infoSourceUrl=infoSourceUrl;
	}
	/**来源链接*/
	public String getInfoSourceUrl(){
		return this.infoSourceUrl;
	}
	
	/**标签,json数组的字符串*/
	public void setInfoTag(String infoTag){
		this.infoTag=infoTag;
	}
	/**标签,json数组的字符串*/
	public String getInfoTag(){
		return this.infoTag;
	}
	
	/**外部最新编辑时间*/
	public void setEditTime(java.util.Date editTime){
		this.editTime=editTime;
	}
	/**外部最新编辑时间*/
	public java.util.Date getEditTime(){
		return this.editTime;
	}
	
	/**外部系统名称*/
	public void setExternalSystem(String externalSystem){
		this.externalSystem=externalSystem;
	}
	/**外部系统名称*/
	public String getExternalSystem(){
		return this.externalSystem;
	}
	
	/**接收日期*/
	public void setInterfaceDate(java.util.Date interfaceDate){
		this.interfaceDate=interfaceDate;
	}
	/**接收日期*/
	public java.util.Date getInterfaceDate(){
		return this.interfaceDate;
	}
	
	/**处理人*/
	public void setProcessPerson(String processPerson){
		this.processPerson=processPerson;
	}
	/**处理人*/
	public String getProcessPerson(){
		return this.processPerson;
	}
	
	/**处理时间*/
	public void setProcessDate(java.util.Date processDate){
		this.processDate=processDate;
	}
	/**处理时间*/
	public java.util.Date getProcessDate(){
		return this.processDate;
	}
	
	/**状态(00：未处理，01：已使用，02：已弃用)*/
	public void setStatus(String status){
		this.status=status;
	}
	/**状态(00：未处理，01：已使用，02：已弃用)*/
	public String getStatus(){
		return this.status;
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
	map.put("uuid",this.uuid);
	map.put("externalId",this.externalId);
	map.put("channelName",this.channelName);
	map.put("infoTitle",this.infoTitle);
	map.put("infoContent",this.infoContent);
	map.put("author",this.author);
	map.put("infoSource",this.infoSource);
	map.put("infoSourceUrl",this.infoSourceUrl);
	map.put("infoTag",this.infoTag);
	map.put("editTime",this.editTime);
	map.put("externalSystem",this.externalSystem);
	map.put("interfaceDate",this.interfaceDate);
	map.put("processPerson",this.processPerson);
	map.put("processDate",this.processDate);
	map.put("status",this.status);
	map.put("aliveFlag",this.aliveFlag);
			return map;
	}
	
}