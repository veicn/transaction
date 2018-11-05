package com.sinochem.crude.trade.info.domain;

import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;

public class SymbolPrice implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**自增的主键*/
	private Long id;  
	
	/**32位的唯一键*/
	private String uuid;  
	
	/**合约编码，t_cms_symbol的symbol字段值*/
	private String symbol;  
	
	/**合约名称，t_cms_symbol的symbolName字段值*/
	private String symbolName;  
	
	/**交易日期*/
	private java.util.Date tradeDate;  
	
	/**暂时预留*/
	private String strip;  
	
	/**开盘价*/
	private java.math.BigDecimal openPrice;  
	
	/**最高价*/
	private java.math.BigDecimal highPrice;  
	
	/**最低价*/
	private java.math.BigDecimal lowPrice;  
	
	/**收盘价*/
	private java.math.BigDecimal closePrice;  
	
	/**平均价*/
	private java.math.BigDecimal avgPrice;  
	
	/**持仓量*/
	private java.math.BigDecimal openInterest;  
	
	/**成交量*/
	private java.math.BigDecimal volume;  
	
	/**结算价*/
	private java.math.BigDecimal settlementPrice;  
	
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
	
	/**金凯讯合约代码1*/
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
	
	/**合约编码，t_cms_symbol的symbol字段值*/
	public void setSymbol(String symbol){
		this.symbol=symbol;
	}
	/**合约编码，t_cms_symbol的symbol字段值*/
	public String getSymbol(){
		return this.symbol;
	}
	
	/**交易日期*/
	public void setTradeDate(java.util.Date tradeDate){
		this.tradeDate=tradeDate;
	}
	/**交易日期*/
	public java.util.Date getTradeDate(){
		return this.tradeDate;
	}
	
	/**暂时预留*/
	public void setStrip(String strip){
		this.strip=strip;
	}
	/**暂时预留*/
	public String getStrip(){
		return this.strip;
	}
	
	/**开盘价*/
	public void setOpenPrice(java.math.BigDecimal openPrice){
		this.openPrice=openPrice;
	}
	/**开盘价*/
	public java.math.BigDecimal getOpenPrice(){
		return this.openPrice;
	}
	
	/**最高价*/
	public void setHighPrice(java.math.BigDecimal highPrice){
		this.highPrice=highPrice;
	}
	/**最高价*/
	public java.math.BigDecimal getHighPrice(){
		return this.highPrice;
	}
	
	/**最低价*/
	public void setLowPrice(java.math.BigDecimal lowPrice){
		this.lowPrice=lowPrice;
	}
	/**最低价*/
	public java.math.BigDecimal getLowPrice(){
		return this.lowPrice;
	}
	
	/**收盘价*/
	public void setClosePrice(java.math.BigDecimal closePrice){
		this.closePrice=closePrice;
	}
	/**收盘价*/
	public java.math.BigDecimal getClosePrice(){
		return this.closePrice;
	}
	
	/**平均价*/
	public void setAvgPrice(java.math.BigDecimal avgPrice){
		this.avgPrice=avgPrice;
	}
	/**平均价*/
	public java.math.BigDecimal getAvgPrice(){
		return this.avgPrice;
	}
	
	/**持仓量*/
	public void setOpenInterest(java.math.BigDecimal openInterest){
		this.openInterest=openInterest;
	}
	/**持仓量*/
	public java.math.BigDecimal getOpenInterest(){
		return this.openInterest;
	}
	
	/**成交量*/
	public void setVolume(java.math.BigDecimal volume){
		this.volume=volume;
	}
	/**成交量*/
	public java.math.BigDecimal getVolume(){
		return this.volume;
	}
	
	/**结算价*/
	public void setSettlementPrice(java.math.BigDecimal settlementPrice){
		this.settlementPrice=settlementPrice;
	}
	/**结算价*/
	public java.math.BigDecimal getSettlementPrice(){
		return this.settlementPrice;
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
	
	/**金凯讯合约代码*/
	public void setExtend1(String extend1){
		this.extend1=extend1;
	}
	/**金凯讯合约代码*/
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
	map.put("symbol",this.symbol);
	map.put("tradeDate",this.tradeDate);
	map.put("strip",this.strip);
	map.put("openPrice",this.openPrice);
	map.put("highPrice",this.highPrice);
	map.put("lowPrice",this.lowPrice);
	map.put("closePrice",this.closePrice);
	map.put("avgPrice",this.avgPrice);
	map.put("openInterest",this.openInterest);
	map.put("volume",this.volume);
	map.put("settlementPrice",this.settlementPrice);
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
	public String getSymbolName() {
		return symbolName;
	}
	public void setSymbolName(String symbolName) {
		this.symbolName = symbolName;
	}
	
}