package com.sinochem.crude.trade.info.domain;

import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;

public class AgioInterface implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**自增主键*/
	private Long id;  
	
	/***/
	private String uuid;  
	
	/**原油产品英文名*/
	private String crudeNameE;  
	
	/**原油产品中文名*/
	private String crudeNameC;  
	
	/**价格*/
	private java.math.BigDecimal pricing;  
	
	/**价格日期*/
	private java.util.Date pricingDate;  
	
	/**装货月份*/
	private String loadingMonth;  
	
	/**运输条款*/
	private String transportClause;  
	
	/**变化*/
	private String change1;  
	
	/**外部系统名称*/
	private String externalSystem;  
	
	/**接收日期*/
	private java.util.Date interfaceDate;  
	
	/**处理人*/
	private String processPerson;  
	
	/**处理时间*/
	private java.util.Date processDate;  
	
	/**状态(00：未处理，01：已启用，02：已弃用)*/
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
	
	/***/
	public void setUuid(String uuid){
		this.uuid=uuid;
	}
	/***/
	public String getUuid(){
		return this.uuid;
	}
	
	/**原油产品英文名*/
	public void setCrudeNameE(String crudeNameE){
		this.crudeNameE=crudeNameE;
	}
	/**原油产品英文名*/
	public String getCrudeNameE(){
		return this.crudeNameE;
	}
	
	/**原油产品中文名*/
	public void setCrudeNameC(String crudeNameC){
		this.crudeNameC=crudeNameC;
	}
	/**原油产品中文名*/
	public String getCrudeNameC(){
		return this.crudeNameC;
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
	
	/**装货月份*/
	public void setLoadingMonth(String loadingMonth){
		this.loadingMonth=loadingMonth;
	}
	/**装货月份*/
	public String getLoadingMonth(){
		return this.loadingMonth;
	}
	
	/**运输条款*/
	public void setTransportClause(String transportClause){
		this.transportClause=transportClause;
	}
	/**运输条款*/
	public String getTransportClause(){
		return this.transportClause;
	}
	
	/**变化*/
	public void setChange1(String change1){
		this.change1=change1;
	}
	/**变化*/
	public String getChange1(){
		return this.change1;
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
	
	/**状态(00：未处理，01：已启用，02：已弃用)*/
	public void setStatus(String status){
		this.status=status;
	}
	/**状态(00：未处理，01：已启用，02：已弃用)*/
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
	map.put("crudeNameE",this.crudeNameE);
	map.put("crudeNameC",this.crudeNameC);
	map.put("pricing",this.pricing);
	map.put("pricingDate",this.pricingDate);
	map.put("loadingMonth",this.loadingMonth);
	map.put("transportClause",this.transportClause);
	map.put("change1",this.change1);
	map.put("externalSystem",this.externalSystem);
	map.put("interfaceDate",this.interfaceDate);
	map.put("processPerson",this.processPerson);
	map.put("processDate",this.processDate);
	map.put("status",this.status);
	map.put("aliveFlag",this.aliveFlag);
			return map;
	}
	
}