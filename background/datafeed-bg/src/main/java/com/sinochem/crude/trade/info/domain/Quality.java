package com.sinochem.crude.trade.info.domain;

import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;

public class Quality implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**自增主键*/
	private Long id;  
	
	/**uuid,唯一键*/
	private String uuid;  
	
	/**原油产品id*/
	private Long crudeId;  
	
	/**品质等级日期*/
	private java.util.Date simpleDate;  
	
	/**品质版本号*/
	private String qualityVersion;  
	
	/**API度*/
	private java.math.BigDecimal api;  
	
	/**硫含量,单位%wt，质量百分比*/
	private java.math.BigDecimal sulphur;  
	
	/**酸值,单位mgKOH/g*/
	private java.math.BigDecimal acidity;  
	
	/**凝点,单位℃*/
	private String freezingPoint;  
	
	/**闪点,单位℃*/
	private String flashPoint;  
	
	/**黏度（50℃）*/
	private String viscosity;  
	
	/**残碳,单位%wt，质量百分比*/
	private String carbonResidue;  
	
	/**镍含量,ppm wt*/
	private String nickel;  
	
	/**钒含量,ppm wt*/
	private String vanadium;  
	
	/**数据状态*/
	private String aliveFlag;  
	
	/**>350℃质量收率*/
	private String yield;  
	
	/**创建日期*/
	private java.util.Date createDate;  
	
	/**创建人*/
	private String createPerson;  
	
	/**修改日期*/
	private java.util.Date modifyDate;  
	
	/**修改人*/
	private String modifyPerson;  
	
	/**数据来源(1:转自接口，2:本系统页面维护)*/
	private String source;  
	
	/**中间表对应的id*/
	private Long interfaceId;  
	
	/**区域*/
	private String extend1;  
	
	/**产地*/
	private String extend2;
	
	/**原油类型*/
	private String extend3;  
	
	/**原油英文名*/
	private String extend4;  
	
	/**原油中文名*/
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
	
	/**uuid,唯一键*/
	public void setUuid(String uuid){
		this.uuid=uuid;
	}
	/**uuid,唯一键*/
	public String getUuid(){
		return this.uuid;
	}
	
	/**原油产品id*/
	public void setCrudeId(Long crudeId){
		this.crudeId=crudeId;
	}
	/**原油产品id*/
	public Long getCrudeId(){
		return this.crudeId;
	}
	
	/**品质等级日期*/
	public void setSimpleDate(java.util.Date simpleDate){
		this.simpleDate=simpleDate;
	}
	/**品质等级日期*/
	public java.util.Date getSimpleDate(){
		return this.simpleDate;
	}
	
	/**品质版本号*/
	public void setQualityVersion(String qualityVersion){
		this.qualityVersion=qualityVersion;
	}
	/**品质版本号*/
	public String getQualityVersion(){
		return this.qualityVersion;
	}
	
	/**API度*/
	public void setApi(java.math.BigDecimal api){
		this.api=api;
	}
	/**API度*/
	public java.math.BigDecimal getApi(){
		return this.api;
	}
	
	/**硫含量,单位%wt，质量百分比*/
	public void setSulphur(java.math.BigDecimal sulphur){
		this.sulphur=sulphur;
	}
	/**硫含量,单位%wt，质量百分比*/
	public java.math.BigDecimal getSulphur(){
		return this.sulphur;
	}
	
	/**酸值,单位mgKOH/g*/
	public void setAcidity(java.math.BigDecimal acidity){
		this.acidity=acidity;
	}
	/**酸值,单位mgKOH/g*/
	public java.math.BigDecimal getAcidity(){
		return this.acidity;
	}
	
	/**凝点,单位℃*/
	public void setFreezingPoint(String freezingPoint){
		this.freezingPoint=freezingPoint;
	}
	/**凝点,单位℃*/
	public String getFreezingPoint(){
		return this.freezingPoint;
	}
	
	/**闪点,单位℃*/
	public void setFlashPoint(String flashPoint){
		this.flashPoint=flashPoint;
	}
	/**闪点,单位℃*/
	public String getFlashPoint(){
		return this.flashPoint;
	}
	
	/**黏度（50℃）*/
	public void setViscosity(String viscosity){
		this.viscosity=viscosity;
	}
	/**黏度（50℃）*/
	public String getViscosity(){
		return this.viscosity;
	}
	
	/**残碳,单位%wt，质量百分比*/
	public void setCarbonResidue(String carbonResidue){
		this.carbonResidue=carbonResidue;
	}
	/**残碳,单位%wt，质量百分比*/
	public String getCarbonResidue(){
		return this.carbonResidue;
	}
	
	/**镍含量,ppm wt*/
	public void setNickel(String nickel){
		this.nickel=nickel;
	}
	/**镍含量,ppm wt*/
	public String getNickel(){
		return this.nickel;
	}
	
	/**钒含量,ppm wt*/
	public void setVanadium(String vanadium){
		this.vanadium=vanadium;
	}
	/**钒含量,ppm wt*/
	public String getVanadium(){
		return this.vanadium;
	}
	
	/**数据状态*/
	public void setAliveFlag(String aliveFlag){
		this.aliveFlag=aliveFlag;
	}
	/**数据状态*/
	public String getAliveFlag(){
		return this.aliveFlag;
	}
	
	/**>350℃质量收率*/
	public void setYield(String yield){
		this.yield=yield;
	}
	/**>350℃质量收率*/
	public String getYield(){
		return this.yield;
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
	
	/**区域*/
	public void setExtend1(String extend1){
		this.extend1=extend1;
	}
	/**区域*/
	public String getExtend1(){
		return this.extend1;
	}
	
	/**产地*/
	public void setExtend2(String extend2){
		this.extend2=extend2;
	}
	/**产地*/
	public String getExtend2(){
		return this.extend2;
	}
	
	/**原油类型*/
	public void setExtend3(String extend3){
		this.extend3=extend3;
	}
	/**原油类型*/
	public String getExtend3(){
		return this.extend3;
	}
	
	/**原油英文名*/
	public void setExtend4(String extend4){
		this.extend4=extend4;
	}
	/**原油英文名*/
	public String getExtend4(){
		return this.extend4;
	}
	
	/**原油中文名*/
	public void setExtend5(String extend5){
		this.extend5=extend5;
	}
	/**原油中文名*/
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
	map.put("crudeId",this.crudeId);
	map.put("simpleDate",this.simpleDate);
	map.put("qualityVersion",this.qualityVersion);
	map.put("api",this.api);
	map.put("sulphur",this.sulphur);
	map.put("acidity",this.acidity);
	map.put("freezingPoint",this.freezingPoint);
	map.put("flashPoint",this.flashPoint);
	map.put("viscosity",this.viscosity);
	map.put("carbonResidue",this.carbonResidue);
	map.put("nickel",this.nickel);
	map.put("vanadium",this.vanadium);
	map.put("aliveFlag",this.aliveFlag);
	map.put("yield",this.yield);
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