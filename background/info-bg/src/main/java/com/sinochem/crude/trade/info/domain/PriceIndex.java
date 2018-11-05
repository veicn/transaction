package com.sinochem.crude.trade.info.domain;

import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;

public class PriceIndex implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**自增的主键*/
	private Long id;  
	
	/**32位的唯一键*/
	private String uuid;  
	
	/**指数模板ID*/
	private Long indexTemplateId;  
	
	/**指数日期*/
	private java.util.Date indexDate;  
	
	/**指数值*/
	private java.math.BigDecimal smeiValue;  
	
	/**涨跌*/
	private java.math.BigDecimal riseFall;  
	
	/**涨跌幅*/
	private java.math.BigDecimal riseFallRate;  
	
	/**开盘*/
	private java.math.BigDecimal open;  
	
	/**最高*/
	private java.math.BigDecimal high;  
	
	/**最低*/
	private java.math.BigDecimal low;  
	
	/**最新报价*/
	private java.math.BigDecimal lastPrice;  
	
	/**昨收*/
	private java.math.BigDecimal preClose;  
	
	/**昨收涨跌*/
	private java.math.BigDecimal preRiseFall;  
	
	/**昨收涨跌率*/
	private java.math.BigDecimal preRiseFallRate;  
	
	/**昨结*/
	private java.math.BigDecimal preSettle;  
	
	/**是否最新(1是0否)*/
	private String latestFlag;  
	
	/**备注*/
	private String remark;  
	
	/**创建者*/
	private String createUser;  
	
	/**创建时间*/
	private java.util.Date createDate;  
	
	/**修改者*/
	private String updateUser;  
	
	/**修改时间*/
	private java.util.Date updateDate;  
	
	/**数据状态*/
	private String aliveFlag;  
	
	/**扩展字段1*/
	private String extend1;  
	
	/**扩展字段2*/
	private String extend2;  
	
	/**扩展字段3*/
	private String extend3;  
	
	/**扩展字段4*/
	private String extend4;  
	
	/**扩展字段5*/
	private String extend5;  
	
	/**扩展字段6*/
	private String extend6;  
	
	/**扩展字段7*/
	private String extend7;  
	
	/**扩展字段8*/
	private String extend8;  
	
	/**扩展字段9*/
	private String extend9;  
	
	/**扩展字段10*/
	private String extend10;  
	
		/**自增的主键*/
	public void setId(Long id){
		this.id=id;
	}
	/**自增的主键*/
	public Long getId(){
		return this.id;
	}
	
	/**32位的唯一键*/
	public void setUuid(String uuid){
		this.uuid=uuid;
	}
	/**32位的唯一键*/
	public String getUuid(){
		return this.uuid;
	}
	
	/**指数模板ID*/
	public void setIndexTemplateId(Long indexTemplateId){
		this.indexTemplateId=indexTemplateId;
	}
	/**指数模板ID*/
	public Long getIndexTemplateId(){
		return this.indexTemplateId;
	}
	
	/**指数日期*/
	public void setIndexDate(java.util.Date indexDate){
		this.indexDate=indexDate;
	}
	/**指数日期*/
	public java.util.Date getIndexDate(){
		return this.indexDate;
	}
	
	/**指数值*/
	public void setSmeiValue(java.math.BigDecimal smeiValue){
		this.smeiValue=smeiValue;
	}
	/**指数值*/
	public java.math.BigDecimal getSmeiValue(){
		return this.smeiValue;
	}
	
	/**涨跌*/
	public void setRiseFall(java.math.BigDecimal riseFall){
		this.riseFall=riseFall;
	}
	/**涨跌*/
	public java.math.BigDecimal getRiseFall(){
		return this.riseFall;
	}
	
	/**涨跌幅*/
	public void setRiseFallRate(java.math.BigDecimal riseFallRate){
		this.riseFallRate=riseFallRate;
	}
	/**涨跌幅*/
	public java.math.BigDecimal getRiseFallRate(){
		return this.riseFallRate;
	}
	
	/**开盘*/
	public void setOpen(java.math.BigDecimal open){
		this.open=open;
	}
	/**开盘*/
	public java.math.BigDecimal getOpen(){
		return this.open;
	}
	
	/**最高*/
	public void setHigh(java.math.BigDecimal high){
		this.high=high;
	}
	/**最高*/
	public java.math.BigDecimal getHigh(){
		return this.high;
	}
	
	/**最低*/
	public void setLow(java.math.BigDecimal low){
		this.low=low;
	}
	/**最低*/
	public java.math.BigDecimal getLow(){
		return this.low;
	}
	
	/**最新报价*/
	public void setLastPrice(java.math.BigDecimal lastPrice){
		this.lastPrice=lastPrice;
	}
	/**最新报价*/
	public java.math.BigDecimal getLastPrice(){
		return this.lastPrice;
	}
	
	/**昨收*/
	public void setPreClose(java.math.BigDecimal preClose){
		this.preClose=preClose;
	}
	/**昨收*/
	public java.math.BigDecimal getPreClose(){
		return this.preClose;
	}
	
	/**昨收涨跌*/
	public void setPreRiseFall(java.math.BigDecimal preRiseFall){
		this.preRiseFall=preRiseFall;
	}
	/**昨收涨跌*/
	public java.math.BigDecimal getPreRiseFall(){
		return this.preRiseFall;
	}
	
	/**昨收涨跌率*/
	public void setPreRiseFallRate(java.math.BigDecimal preRiseFallRate){
		this.preRiseFallRate=preRiseFallRate;
	}
	/**昨收涨跌率*/
	public java.math.BigDecimal getPreRiseFallRate(){
		return this.preRiseFallRate;
	}
	
	/**昨结*/
	public void setPreSettle(java.math.BigDecimal preSettle){
		this.preSettle=preSettle;
	}
	/**昨结*/
	public java.math.BigDecimal getPreSettle(){
		return this.preSettle;
	}
	
	/**是否最新(1是0否)*/
	public void setLatestFlag(String latestFlag){
		this.latestFlag=latestFlag;
	}
	/**是否最新(1是0否)*/
	public String getLatestFlag(){
		return this.latestFlag;
	}
	
	/**备注*/
	public void setRemark(String remark){
		this.remark=remark;
	}
	/**备注*/
	public String getRemark(){
		return this.remark;
	}
	
	/**创建者*/
	public void setCreateUser(String createUser){
		this.createUser=createUser;
	}
	/**创建者*/
	public String getCreateUser(){
		return this.createUser;
	}
	
	/**创建时间*/
	public void setCreateDate(java.util.Date createDate){
		this.createDate=createDate;
	}
	/**创建时间*/
	public java.util.Date getCreateDate(){
		return this.createDate;
	}
	
	/**修改者*/
	public void setUpdateUser(String updateUser){
		this.updateUser=updateUser;
	}
	/**修改者*/
	public String getUpdateUser(){
		return this.updateUser;
	}
	
	/**修改时间*/
	public void setUpdateDate(java.util.Date updateDate){
		this.updateDate=updateDate;
	}
	/**修改时间*/
	public java.util.Date getUpdateDate(){
		return this.updateDate;
	}
	
	/**数据状态*/
	public void setAliveFlag(String aliveFlag){
		this.aliveFlag=aliveFlag;
	}
	/**数据状态*/
	public String getAliveFlag(){
		return this.aliveFlag;
	}
	
	/**扩展字段1*/
	public void setExtend1(String extend1){
		this.extend1=extend1;
	}
	/**扩展字段1*/
	public String getExtend1(){
		return this.extend1;
	}
	
	/**扩展字段2*/
	public void setExtend2(String extend2){
		this.extend2=extend2;
	}
	/**扩展字段2*/
	public String getExtend2(){
		return this.extend2;
	}
	
	/**扩展字段3*/
	public void setExtend3(String extend3){
		this.extend3=extend3;
	}
	/**扩展字段3*/
	public String getExtend3(){
		return this.extend3;
	}
	
	/**扩展字段4*/
	public void setExtend4(String extend4){
		this.extend4=extend4;
	}
	/**扩展字段4*/
	public String getExtend4(){
		return this.extend4;
	}
	
	/**扩展字段5*/
	public void setExtend5(String extend5){
		this.extend5=extend5;
	}
	/**扩展字段5*/
	public String getExtend5(){
		return this.extend5;
	}
	
	/**扩展字段6*/
	public void setExtend6(String extend6){
		this.extend6=extend6;
	}
	/**扩展字段6*/
	public String getExtend6(){
		return this.extend6;
	}
	
	/**扩展字段7*/
	public void setExtend7(String extend7){
		this.extend7=extend7;
	}
	/**扩展字段7*/
	public String getExtend7(){
		return this.extend7;
	}
	
	/**扩展字段8*/
	public void setExtend8(String extend8){
		this.extend8=extend8;
	}
	/**扩展字段8*/
	public String getExtend8(){
		return this.extend8;
	}
	
	/**扩展字段9*/
	public void setExtend9(String extend9){
		this.extend9=extend9;
	}
	/**扩展字段9*/
	public String getExtend9(){
		return this.extend9;
	}
	
	/**扩展字段10*/
	public void setExtend10(String extend10){
		this.extend10=extend10;
	}
	/**扩展字段10*/
	public String getExtend10(){
		return this.extend10;
	}
	
		
	
	public Map<String, Object> toMap() {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id",this.id);
	map.put("uuid",this.uuid);
	map.put("indexTemplateId",this.indexTemplateId);
	map.put("indexDate",this.indexDate);
	map.put("smeiValue",this.smeiValue);
	map.put("riseFall",this.riseFall);
	map.put("riseFallRate",this.riseFallRate);
	map.put("open",this.open);
	map.put("high",this.high);
	map.put("low",this.low);
	map.put("lastPrice",this.lastPrice);
	map.put("preClose",this.preClose);
	map.put("preRiseFall",this.preRiseFall);
	map.put("preRiseFallRate",this.preRiseFallRate);
	map.put("preSettle",this.preSettle);
	map.put("latestFlag",this.latestFlag);
	map.put("remark",this.remark);
	map.put("createUser",this.createUser);
	map.put("createDate",this.createDate);
	map.put("updateUser",this.updateUser);
	map.put("updateDate",this.updateDate);
	map.put("aliveFlag",this.aliveFlag);
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