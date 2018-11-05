package com.sinochem.crude.trade.info.domain;

import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;

public class Oil implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**自增主键*/
	private Long id;  
	
	/**UUID*/
	private String uuid;  
	
	/**原油英文名*/
	private String crudeNameE;  
	
	/**英文全称*/
	private String crudeNameC;  
	
	/**原油中文名*/
	private String fullNameE;  
	
	/**产地区域id*/
	private Long originAreaId;  
	
	/**产地id*/
	private Long originId;  
	
	/**种类id*/
	private Long catagoryId;  
	
	/**交易模式(1-现货/2-期货)*/
	private String transactionMode;  
	
	/**是否基准价 1/0，真/假*/
	private String priceBaseFlag;  
	
	/**价格模式(osp/osp_dis/base_dis，1/2/3，只有现货原油品种有这个属性)*/
	private String priceMode;  
	
	/**排序*/
	private Integer priority;  
	
	/**桶吨比*/
	private java.math.BigDecimal tonBucket;  
	
	/**有效标志*/
	private String aliveFlag;  
	
	/**创建日期*/
	private java.util.Date createDate;  
	
	/**创建人*/
	private String createPerson;  
	
	/**修改日期*/
	private java.util.Date modifyDate;  
	
	/**修改人*/
	private String modifyPerson;  
	
	/**备用字段1*/
	private String extend1;  
	
	/**备用字段2*/
	private String extend2;  
	
	/**备用字段3*/
	private String extend3;  
	
	/**备用字段4*/
	private String extend4;  
	
	/**备用字段5*/
	private String extend5;  
	
	/**备用字段6*/
	private String extend6;  
	
	/**备用字段7*/
	private String extend7;  
	
	/**备用字段8*/
	private String extend8;  
	
	/**备用字段9*/
	private String extend9;  
	
	/**备用字段10*/
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
	
	/**原油英文名*/
	public void setCrudeNameE(String crudeNameE){
		this.crudeNameE=crudeNameE;
	}
	/**原油英文名*/
	public String getCrudeNameE(){
		return this.crudeNameE;
	}
	
	/**英文全称*/
	public void setCrudeNameC(String crudeNameC){
		this.crudeNameC=crudeNameC;
	}
	/**英文全称*/
	public String getCrudeNameC(){
		return this.crudeNameC;
	}
	
	/**原油中文名*/
	public void setFullNameE(String fullNameE){
		this.fullNameE=fullNameE;
	}
	/**原油中文名*/
	public String getFullNameE(){
		return this.fullNameE;
	}
	
	/**产地区域id*/
	public void setOriginAreaId(Long originAreaId){
		this.originAreaId=originAreaId;
	}
	/**产地区域id*/
	public Long getOriginAreaId(){
		return this.originAreaId;
	}
	
	/**产地id*/
	public void setOriginId(Long originId){
		this.originId=originId;
	}
	/**产地id*/
	public Long getOriginId(){
		return this.originId;
	}
	
	/**种类id*/
	public void setCatagoryId(Long catagoryId){
		this.catagoryId=catagoryId;
	}
	/**种类id*/
	public Long getCatagoryId(){
		return this.catagoryId;
	}
	
	/**交易模式(1-现货/2-期货)*/
	public void setTransactionMode(String transactionMode){
		this.transactionMode=transactionMode;
	}
	/**交易模式(1-现货/2-期货)*/
	public String getTransactionMode(){
		return this.transactionMode;
	}
	
	/**是否基准价 1/0，真/假*/
	public void setPriceBaseFlag(String priceBaseFlag){
		this.priceBaseFlag=priceBaseFlag;
	}
	/**是否基准价 1/0，真/假*/
	public String getPriceBaseFlag(){
		return this.priceBaseFlag;
	}
	
	/**价格模式(osp/osp_dis/base_dis，1/2/3，只有现货原油品种有这个属性)*/
	public void setPriceMode(String priceMode){
		this.priceMode=priceMode;
	}
	/**价格模式(osp/osp_dis/base_dis，1/2/3，只有现货原油品种有这个属性)*/
	public String getPriceMode(){
		return this.priceMode;
	}
	
	/**排序*/
	public void setPriority(Integer priority){
		this.priority=priority;
	}
	/**排序*/
	public Integer getPriority(){
		return this.priority;
	}
	
	/**桶吨比*/
	public void setTonBucket(java.math.BigDecimal tonBucket){
		this.tonBucket=tonBucket;
	}
	/**桶吨比*/
	public java.math.BigDecimal getTonBucket(){
		return this.tonBucket;
	}
	
	/**有效标志*/
	public void setAliveFlag(String aliveFlag){
		this.aliveFlag=aliveFlag;
	}
	/**有效标志*/
	public String getAliveFlag(){
		return this.aliveFlag;
	}
	
	/**创建日期*/
	public void setCreateDate(java.util.Date createDate){
		this.createDate=createDate;
	}
	/**创建日期*/
	public java.util.Date getCreateDate(){
		return this.createDate;
	}
	
	/**创建人*/
	public void setCreatePerson(String createPerson){
		this.createPerson=createPerson;
	}
	/**创建人*/
	public String getCreatePerson(){
		return this.createPerson;
	}
	
	/**修改日期*/
	public void setModifyDate(java.util.Date modifyDate){
		this.modifyDate=modifyDate;
	}
	/**修改日期*/
	public java.util.Date getModifyDate(){
		return this.modifyDate;
	}
	
	/**修改人*/
	public void setModifyPerson(String modifyPerson){
		this.modifyPerson=modifyPerson;
	}
	/**修改人*/
	public String getModifyPerson(){
		return this.modifyPerson;
	}
	
	/**备用字段1*/
	public void setExtend1(String extend1){
		this.extend1=extend1;
	}
	/**备用字段1*/
	public String getExtend1(){
		return this.extend1;
	}
	
	/**备用字段2*/
	public void setExtend2(String extend2){
		this.extend2=extend2;
	}
	/**备用字段2*/
	public String getExtend2(){
		return this.extend2;
	}
	
	/**备用字段3*/
	public void setExtend3(String extend3){
		this.extend3=extend3;
	}
	/**备用字段3*/
	public String getExtend3(){
		return this.extend3;
	}
	
	/**备用字段4*/
	public void setExtend4(String extend4){
		this.extend4=extend4;
	}
	/**备用字段4*/
	public String getExtend4(){
		return this.extend4;
	}
	
	/**备用字段5*/
	public void setExtend5(String extend5){
		this.extend5=extend5;
	}
	/**备用字段5*/
	public String getExtend5(){
		return this.extend5;
	}
	
	/**备用字段6*/
	public void setExtend6(String extend6){
		this.extend6=extend6;
	}
	/**备用字段6*/
	public String getExtend6(){
		return this.extend6;
	}
	
	/**备用字段7*/
	public void setExtend7(String extend7){
		this.extend7=extend7;
	}
	/**备用字段7*/
	public String getExtend7(){
		return this.extend7;
	}
	
	/**备用字段8*/
	public void setExtend8(String extend8){
		this.extend8=extend8;
	}
	/**备用字段8*/
	public String getExtend8(){
		return this.extend8;
	}
	
	/**备用字段9*/
	public void setExtend9(String extend9){
		this.extend9=extend9;
	}
	/**备用字段9*/
	public String getExtend9(){
		return this.extend9;
	}
	
	/**备用字段10*/
	public void setExtend10(String extend10){
		this.extend10=extend10;
	}
	/**备用字段10*/
	public String getExtend10(){
		return this.extend10;
	}
	
		
	
	public Map<String, Object> toMap() {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id",this.id);
	map.put("uuid",this.uuid);
	map.put("crudeNameE",this.crudeNameE);
	map.put("crudeNameC",this.crudeNameC);
	map.put("fullNameE",this.fullNameE);
	map.put("originAreaId",this.originAreaId);
	map.put("originId",this.originId);
	map.put("catagoryId",this.catagoryId);
	map.put("transactionMode",this.transactionMode);
	map.put("priceBaseFlag",this.priceBaseFlag);
	map.put("priceMode",this.priceMode);
	map.put("priority",this.priority);
	map.put("tonBucket",this.tonBucket);
	map.put("aliveFlag",this.aliveFlag);
	map.put("createDate",this.createDate);
	map.put("createPerson",this.createPerson);
	map.put("modifyDate",this.modifyDate);
	map.put("modifyPerson",this.modifyPerson);
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