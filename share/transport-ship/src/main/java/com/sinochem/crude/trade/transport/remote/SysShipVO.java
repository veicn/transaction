package com.sinochem.crude.trade.transport.remote;

import java.io.Serializable;

public class SysShipVO  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**业务唯一键*/
	private Long sysShipId;  
	
	/**UUID*/
	private String uuid;  
	
	/**状态（1待审核2有效3驳回）*/
	private String status;  
	
	/**船舶代码IMO*/
	private String imo;  
	
	/**船舶MMSI*/
	private String mmsi;
	
	/**船舶用途类型*/
	private String useType;  
	
	/**船名*/
	private String name;  
	
	/**船旗*/
	private String pennant;  
	
	/**船型*/
	private String type;  
	
	/**建造商*/
	private String builder;  
	
	/**船级社*/
	private String classSociety;  
	
	/**建成年份*/
	private String completeDate;  
	
	/**船体*/
	private String hull;  
	
	/**船长（米）*/
	private java.math.BigDecimal length;  
	
	/**船宽（米）*/
	private java.math.BigDecimal wide;  
	
	/**吃水（米）*/
	private java.math.BigDecimal draft;  
	
	/**载重吨*/
	private java.math.BigDecimal loadQuantity;  
	
	/**总吨*/
	private java.math.BigDecimal allQuantity;  
	
	/**载重容积*/
	private java.math.BigDecimal capacity;  
	
	/**附件*/
	private String accessory;  
	
	
	/**发布方类型（1船东/经纪人2二船东3平台）*/
	private String publisherType; 
	
		/**业务唯一键*/
	public void setSysShipId(Long sysShipId){
		this.sysShipId=sysShipId;
	}
	/**业务唯一键*/
	public Long getSysShipId(){
		return this.sysShipId;
	}
	
	/**UUID*/
	public void setUuid(String uuid){
		this.uuid=uuid;
	}
	/**UUID*/
	public String getUuid(){
		return this.uuid;
	}
	
	/**状态（1待审核2未启用3已启用4已作废  目前创建成功默认3已启用）*/
	public void setStatus(String status){
		this.status=status;
	}
	/**状态（1待审核2未启用3已启用4已作废  目前创建成功默认3已启用）*/
	public String getStatus(){
		return this.status;
	}
	
	/**船舶代码IMO*/
	public void setImo(String imo){
		this.imo=imo;
	}
	/**船舶代码IMO*/
	public String getImo(){
		return this.imo;
	}
	
	/**船舶MMSI*/
	public String getMmsi() {
		return mmsi;
	}
	
	/**船舶MMSI*/
	public void setMmsi(String mmsi) {
		this.mmsi = mmsi;
	}
	
	/**船舶用途类型*/
	public void setUseType(String useType){
		this.useType=useType;
	}
	/**船舶用途类型*/
	public String getUseType(){
		return this.useType;
	}
	
	/**船名*/
	public void setName(String name){
		this.name=name;
	}
	/**船名*/
	public String getName(){
		return this.name;
	}
	
	/**船旗*/
	public void setPennant(String pennant){
		this.pennant=pennant;
	}
	/**船旗*/
	public String getPennant(){
		return this.pennant;
	}
	
	/**船型*/
	public void setType(String type){
		this.type=type;
	}
	/**船型*/
	public String getType(){
		return this.type;
	}
	
	/**建造商*/
	public void setBuilder(String builder){
		this.builder=builder;
	}
	/**建造商*/
	public String getBuilder(){
		return this.builder;
	}
	
	/**船级社*/
	public void setClassSociety(String classSociety){
		this.classSociety=classSociety;
	}
	/**船级社*/
	public String getClassSociety(){
		return this.classSociety;
	}
	
	/**建成年份*/
	public void setCompleteDate(String completeDate){
		this.completeDate=completeDate;
	}
	/**建成年份*/
	public String getCompleteDate(){
		return this.completeDate;
	}
	
	/**船体*/
	public void setHull(String hull){
		this.hull=hull;
	}
	/**船体*/
	public String getHull(){
		return this.hull;
	}
	
	/**船长（米）*/
	public void setLength(java.math.BigDecimal length){
		this.length=length;
	}
	/**船长（米）*/
	public java.math.BigDecimal getLength(){
		return this.length;
	}
	
	/**船宽（米）*/
	public void setWide(java.math.BigDecimal wide){
		this.wide=wide;
	}
	/**船宽（米）*/
	public java.math.BigDecimal getWide(){
		return this.wide;
	}
	
	/**吃水（米）*/
	public void setDraft(java.math.BigDecimal draft){
		this.draft=draft;
	}
	/**吃水（米）*/
	public java.math.BigDecimal getDraft(){
		return this.draft;
	}
	
	/**载重吨*/
	public void setLoadQuantity(java.math.BigDecimal loadQuantity){
		this.loadQuantity=loadQuantity;
	}
	/**载重吨*/
	public java.math.BigDecimal getLoadQuantity(){
		return this.loadQuantity;
	}
	
	/**总吨*/
	public void setAllQuantity(java.math.BigDecimal allQuantity){
		this.allQuantity=allQuantity;
	}
	/**总吨*/
	public java.math.BigDecimal getAllQuantity(){
		return this.allQuantity;
	}
	
	/**载重容积*/
	public void setCapacity(java.math.BigDecimal capacity){
		this.capacity=capacity;
	}
	/**载重容积*/
	public java.math.BigDecimal getCapacity(){
		return this.capacity;
	}
	
	/**附件*/
	public void setAccessory(String accessory){
		this.accessory=accessory;
	}
	/**附件*/
	public String getAccessory(){
		return this.accessory;
	}
	
	/**发布方类型（1船东/经纪人2二船东3平台）*/
	public void setPublisherType(String publisherType){
		this.publisherType=publisherType;
	}
	/**发布方类型（1船东/经纪人2二船东3平台）*/
	public String getPublisherType(){
		return this.publisherType;
	}
	
	
}