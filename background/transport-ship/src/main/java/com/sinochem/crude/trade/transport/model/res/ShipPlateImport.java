package com.sinochem.crude.trade.transport.model.res;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class ShipPlateImport implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	/**会员公司名称*/
	private String epMemberName;  
	
	/**船名*/
	private String name;  
	
	/**船型*/
	private String type;  
	
	/**建成年份*/
	private String completeDate;
	
	/**船东*/
	private String shipOwner;  
	
	/**载重吨*/
	private java.math.BigDecimal loadQuantity;  
	
	/**船位*/
	private String position;  
	
	/**ETA*/
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private String eta;  
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private String etaCabinda; 
	
	/**经纪人名称*/
	private String brokerName;  
	
	/**备注*/
	private String remark;
	
	/**船东id*/
	private Long shipOwnerId;  
	
	/**卸货完成时间*/
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date open;
	
	/**船盘发布时间*/
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date publishTime;
	
	/**时效*/
	private Integer period;
	
	/**会员公司名称*/
	public String getEpMemberName() {
		return epMemberName;
	}
	/**会员公司名称*/
	public void setEpMemberName(String epMemberName) {
		this.epMemberName = epMemberName;
	}
	
	/**船名*/
	public void setName(String name){
		this.name=name;
	}
	/**船名*/
	public String getName(){
		return this.name;
	}
	
	/**船型*/
	public void setType(String type){
		this.type=type;
	}
	/**船型*/
	public String getType(){
		return this.type;
	}
	
	/**建成年份*/
	public void setCompleteDate(String completeDate){
		this.completeDate=completeDate;
	}
	/**建成年份*/
	public String getCompleteDate(){
		return this.completeDate;
	}
	
	/**船东*/
	public void setShipOwner(String shipOwner){
		this.shipOwner=shipOwner;
	}
	/**船东*/
	public String getShipOwner(){
		return this.shipOwner;
	}
	
	/**载重吨*/
	public void setLoadQuantity(java.math.BigDecimal loadQuantity){
		this.loadQuantity=loadQuantity;
	}
	/**载重吨*/
	public java.math.BigDecimal getLoadQuantity(){
		return this.loadQuantity;
	}
	
	/**船位*/
	public void setPosition(String position){
		this.position=position;
	}
	/**船位*/
	public String getPosition(){
		return this.position;
	}
	
	//**ETA*//*
	public void setEta(String eta){
		this.eta=eta;
	}
	//**ETA*//*
	public String getEta(){
		return this.eta;
	}
	
	public String getEtaCabinda() {
		return etaCabinda;
	}
	public void setEtaCabinda(String etaCabinda) {
		this.etaCabinda = etaCabinda;
	}
	/**经纪人名称*/
	public void setBrokerName(String brokerName){
		this.brokerName=brokerName;
	}
	/**经纪人名称*/
	public String getBrokerName(){
		return this.brokerName;
	}
	
	/**备注*/
	public void setRemark(String remark){
		this.remark=remark;
	}
	/**备注*/
	public String getRemark(){
		return this.remark;
	}

	/**船东id*/
	public void setShipOwnerId(Long shipOwnerId){
		this.shipOwnerId=shipOwnerId;
	}
	/**船东id*/
	public Long getShipOwnerId(){
		return this.shipOwnerId;
	}
	
	/**卸货完成时间*/
	public java.util.Date getOpen() {
		return open;
	}
	/**卸货完成时间*/
	public void setOpen(java.util.Date open) {
		this.open = open;
	}
	
	/**船盘发布时间*/
	public java.util.Date getPublishTime() {
		return publishTime;
	}
	/**船盘发布时间*/
	public void setPublishTime(java.util.Date publishTime) {
		this.publishTime = publishTime;
	}
	
	/**时效*/
	public Integer getPeriod() {
		return period;
	}
	/**时效*/
	public void setPeriod(Integer period) {
		this.period = period;
	}
	
	
	public ShipPlateImport(String name, String shipOwner, String position, String eta, String etaCabinda, Date open, Integer period) {
		super();
		this.name = name;
		this.shipOwner = shipOwner;
		this.position = position;
		this.eta = eta;
		this.etaCabinda = etaCabinda;
		this.open = open;
		this.period = period;
	}
	
}
