package com.sinochem.crude.trade.info.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class CrudeBasePrice implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**自增主键*/
	private Long id;  
	
	/**UUID*/
	private String uuid;  
	
	/**原油ID*/
	private Long crudeId;  
	
	/**价格*/
	private java.math.BigDecimal pricing;  
	
	/**价格日期*/
	private java.util.Date pricingDate;  
	
	/**备注*/
	private String remark;  
	
	/**备注*/
	private String note;  
	
	/**是否最新(1是0否)*/
	private String latestFlag;  
	
	/**数据状态*/
	private String aliveFlag;  
	
	/**创建时间*/
	private java.util.Date createDate;  
	
	/***/
	private String createPerson;  
	
	/***/
	private java.util.Date modifyDate;  
	
	/***/
	private String modifyPerson;  
	
	/**数据来源(1:转自接口，2:本系统页面维护)*/
	private String source;  
	
	/**中间表对应的id*/
	private Long interfaceId;  
	
	/***/
	private String extend1;  
	
	/***/
	private String extend2;  
	
	/***/
	private String extend3;  
	
	/***/
	private String extend4;  
	
	/***/
	private String extend5;  
	
	/***/
	private String extend6;  
	
	/***/
	private String extend7;  
	
	/***/
	private String extend8;  
	
	/***/
	private String extend9;  
	
	/***/
	private String extend10;  
	
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
	
	/**原油ID*/
	public void setCrudeId(Long crudeId){
		this.crudeId=crudeId;
	}
	/**原油ID*/
	public Long getCrudeId(){
		return this.crudeId;
	}
	
	/**价格*/
	public void setPricing(java.math.BigDecimal pricing){
		this.pricing=pricing;
	}
	/**价格*/
	public java.math.BigDecimal getPricing(){
		return this.pricing;
	}
	
	/**价格日期*/
	public void setPricingDate(java.util.Date pricingDate){
		this.pricingDate=pricingDate;
	}
	/**价格日期*/
	public java.util.Date getPricingDate(){
		return this.pricingDate;
	}
	
	/**备注*/
	public void setRemark(String remark){
		this.remark=remark;
	}
	/**备注*/
	public String getRemark(){
		return this.remark;
	}
	
	/**备注*/
	public void setNote(String note){
		this.note=note;
	}
	/**备注*/
	public String getNote(){
		return this.note;
	}
	
	/**是否最新(1是0否)*/
	public void setLatestFlag(String latestFlag){
		this.latestFlag=latestFlag;
	}
	/**是否最新(1是0否)*/
	public String getLatestFlag(){
		return this.latestFlag;
	}
	
	/**数据状态*/
	public void setAliveFlag(String aliveFlag){
		this.aliveFlag=aliveFlag;
	}
	/**数据状态*/
	public String getAliveFlag(){
		return this.aliveFlag;
	}
	
	/**创建时间*/
	public void setCreateDate(java.util.Date createDate){
		this.createDate=createDate;
	}
	/**创建时间*/
	public java.util.Date getCreateDate(){
		return this.createDate;
	}
	
	/***/
	public void setCreatePerson(String createPerson){
		this.createPerson=createPerson;
	}
	/***/
	public String getCreatePerson(){
		return this.createPerson;
	}
	
	/***/
	public void setModifyDate(java.util.Date modifyDate){
		this.modifyDate=modifyDate;
	}
	/***/
	public java.util.Date getModifyDate(){
		return this.modifyDate;
	}
	
	/***/
	public void setModifyPerson(String modifyPerson){
		this.modifyPerson=modifyPerson;
	}
	/***/
	public String getModifyPerson(){
		return this.modifyPerson;
	}
	
	/**数据来源(1:转自接口，2:本系统页面维护)*/
	public void setSource(String source){
		this.source=source;
	}
	/**数据来源(1:转自接口，2:本系统页面维护)*/
	public String getSource(){
		return this.source;
	}
	
	/**中间表对应的id*/
	public void setInterfaceId(Long interfaceId){
		this.interfaceId=interfaceId;
	}
	/**中间表对应的id*/
	public Long getInterfaceId(){
		return this.interfaceId;
	}
	
	/***/
	public void setExtend1(String extend1){
		this.extend1=extend1;
	}
	/***/
	public String getExtend1(){
		return this.extend1;
	}
	
	/***/
	public void setExtend2(String extend2){
		this.extend2=extend2;
	}
	/***/
	public String getExtend2(){
		return this.extend2;
	}
	
	/***/
	public void setExtend3(String extend3){
		this.extend3=extend3;
	}
	/***/
	public String getExtend3(){
		return this.extend3;
	}
	
	/***/
	public void setExtend4(String extend4){
		this.extend4=extend4;
	}
	/***/
	public String getExtend4(){
		return this.extend4;
	}
	
	/***/
	public void setExtend5(String extend5){
		this.extend5=extend5;
	}
	/***/
	public String getExtend5(){
		return this.extend5;
	}
	
	/***/
	public void setExtend6(String extend6){
		this.extend6=extend6;
	}
	/***/
	public String getExtend6(){
		return this.extend6;
	}
	
	/***/
	public void setExtend7(String extend7){
		this.extend7=extend7;
	}
	/***/
	public String getExtend7(){
		return this.extend7;
	}
	
	/***/
	public void setExtend8(String extend8){
		this.extend8=extend8;
	}
	/***/
	public String getExtend8(){
		return this.extend8;
	}
	
	/***/
	public void setExtend9(String extend9){
		this.extend9=extend9;
	}
	/***/
	public String getExtend9(){
		return this.extend9;
	}
	
	/***/
	public void setExtend10(String extend10){
		this.extend10=extend10;
	}
	/***/
	public String getExtend10(){
		return this.extend10;
	}
	
		
	
	public Map<String, Object> toMap() {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id",this.id);
	map.put("uuid",this.uuid);
	map.put("crudeId",this.crudeId);
	map.put("pricing",this.pricing);
	map.put("pricingDate",this.pricingDate);
	map.put("remark",this.remark);
	map.put("note",this.note);
	map.put("latestFlag",this.latestFlag);
	map.put("aliveFlag",this.aliveFlag);
	map.put("createDate",this.createDate);
	map.put("createPerson",this.createPerson);
	map.put("modifyDate",this.modifyDate);
	map.put("modifyPerson",this.modifyPerson);
	map.put("source",this.source);
	map.put("interfaceId",this.interfaceId);
	map.put("extend1",this.extend1);
	map.put("extend2",this.extend2);
	map.put("extend3",this.extend3);
	map.put("extend4",this.extend4);
	map.put("extend5",this.extend5);
	map.put("extend6",this.extend6);
	map.put("extend7",this.extend7);
	map.put("extend8",this.extend8);
	map.put("extend9",this.extend9);
	map.put("extend10",this.extend10);
			return map;
	}
	
}