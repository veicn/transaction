package com.sinochem.crude.trade.info.domain;

import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;

public class EFractionMsg implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**自增主键*/
	private Long id;  
	
	/**UUID*/
	private String uuid;  
	
	/**原油ID*/
	private Long crudeId;  
	
	/**原油名称(英文)*/
	private String crudeNameE;  
	
	/**原油名称(中文)*/
	private String crudeNameC;  
	
	/**轻质石脑油*/
	private java.math.BigDecimal lightNaptha;  
	
	/**中质石脑油*/
	private java.math.BigDecimal mediumNaptha;  
	
	/**航煤*/
	private java.math.BigDecimal aviationFuel;  
	
	/**瓦斯油*/
	private java.math.BigDecimal gasOil;  
	
	/**减压瓦斯油*/
	private java.math.BigDecimal vacuumGasOil;  
	
	/**重质减压油*/
	private java.math.BigDecimal heavyFeliefOil;  
	
	/**减压渣油*/
	private java.math.BigDecimal vacuumResidue;  
	
	/**创建人*/
	private String createPerson;  
	
	/**创建时间*/
	private java.util.Date createDate;  
	
	/**硫含量*/
	private java.math.BigDecimal sulphurContent;  
	
	/**有效标志*/
	private String aliveFlag;  
	
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
	
	/**原油ID*/
	public void setCrudeId(Long crudeId){
		this.crudeId=crudeId;
	}
	/**原油ID*/
	public Long getCrudeId(){
		return this.crudeId;
	}
	
	/**原油名称(英文)*/
	public void setCrudeNameE(String crudeNameE){
		this.crudeNameE=crudeNameE;
	}
	/**原油名称(英文)*/
	public String getCrudeNameE(){
		return this.crudeNameE;
	}
	
	/**原油名称(中文)*/
	public void setCrudeNameC(String crudeNameC){
		this.crudeNameC=crudeNameC;
	}
	/**原油名称(中文)*/
	public String getCrudeNameC(){
		return this.crudeNameC;
	}
	
	/**轻质石脑油*/
	public void setLightNaptha(java.math.BigDecimal lightNaptha){
		this.lightNaptha=lightNaptha;
	}
	/**轻质石脑油*/
	public java.math.BigDecimal getLightNaptha(){
		return this.lightNaptha;
	}
	
	/**中质石脑油*/
	public void setMediumNaptha(java.math.BigDecimal mediumNaptha){
		this.mediumNaptha=mediumNaptha;
	}
	/**中质石脑油*/
	public java.math.BigDecimal getMediumNaptha(){
		return this.mediumNaptha;
	}
	
	/**航煤*/
	public void setAviationFuel(java.math.BigDecimal aviationFuel){
		this.aviationFuel=aviationFuel;
	}
	/**航煤*/
	public java.math.BigDecimal getAviationFuel(){
		return this.aviationFuel;
	}
	
	/**瓦斯油*/
	public void setGasOil(java.math.BigDecimal gasOil){
		this.gasOil=gasOil;
	}
	/**瓦斯油*/
	public java.math.BigDecimal getGasOil(){
		return this.gasOil;
	}
	
	/**减压瓦斯油*/
	public void setVacuumGasOil(java.math.BigDecimal vacuumGasOil){
		this.vacuumGasOil=vacuumGasOil;
	}
	/**减压瓦斯油*/
	public java.math.BigDecimal getVacuumGasOil(){
		return this.vacuumGasOil;
	}
	
	/**重质减压油*/
	public void setHeavyFeliefOil(java.math.BigDecimal heavyFeliefOil){
		this.heavyFeliefOil=heavyFeliefOil;
	}
	/**重质减压油*/
	public java.math.BigDecimal getHeavyFeliefOil(){
		return this.heavyFeliefOil;
	}
	
	/**减压渣油*/
	public void setVacuumResidue(java.math.BigDecimal vacuumResidue){
		this.vacuumResidue=vacuumResidue;
	}
	/**减压渣油*/
	public java.math.BigDecimal getVacuumResidue(){
		return this.vacuumResidue;
	}
	
	/**创建人*/
	public void setCreatePerson(String createPerson){
		this.createPerson=createPerson;
	}
	/**创建人*/
	public String getCreatePerson(){
		return this.createPerson;
	}
	
	/**创建时间*/
	public void setCreateDate(java.util.Date createDate){
		this.createDate=createDate;
	}
	/**创建时间*/
	public java.util.Date getCreateDate(){
		return this.createDate;
	}
	
	/**硫含量*/
	public void setSulphurContent(java.math.BigDecimal sulphurContent){
		this.sulphurContent=sulphurContent;
	}
	/**硫含量*/
	public java.math.BigDecimal getSulphurContent(){
		return this.sulphurContent;
	}
	
	/**有效标志*/
	public void setAliveFlag(String aliveFlag){
		this.aliveFlag=aliveFlag;
	}
	/**有效标志*/
	public String getAliveFlag(){
		return this.aliveFlag;
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
		map.put("crudeId",this.crudeId);
		map.put("crudeNameE",this.crudeNameE);
		map.put("crudeNameC",this.crudeNameC);
		map.put("lightNaptha",this.lightNaptha);
		map.put("mediumNaptha",this.mediumNaptha);
		map.put("aviationFuel",this.aviationFuel);
		map.put("gasOil",this.gasOil);
		map.put("vacuumGasOil",this.vacuumGasOil);
		map.put("heavyFeliefOil",this.heavyFeliefOil);
		map.put("vacuumResidue",this.vacuumResidue);
		map.put("createPerson",this.createPerson);
		map.put("createDate",this.createDate);
		map.put("sulphurContent",this.sulphurContent);
		map.put("aliveFlag",this.aliveFlag);
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