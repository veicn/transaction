package com.sinochem.crude.trade.info.domain;

import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;

public class Symbol implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**自增的主键*/
	private Long id;  
	
	/**32位的唯一键*/
	private String uuid;  
	
	/**合约代码，按规则生成，唯一键*/
	private String symbol;  
	
	/**合约名称*/
	private String symbolName;  
	
	/**商品标的*/
	private String commodity;  
	
	/**价格来源(值集获取PRICE_SOURCE）*/
	private String priceSource;  
	
	/**市场（值集获取MARKET）*/
	private String market;  
	
	/**频率（daily, weekly, monthly，可配置成值集）*/
	private String frequency;  
	
	/**货币单位, 人民币，美元等*/
	private String currency;  
	
	/**合约类型(continously, monthly, weekly, spot, premium,可配置成值集)*/
	private String symbolType;  
	
	/**类型,Future（纸货），Spot（现货），SWAP（掉期），Forward（期货），SPREAD（价差）,可配置成值集*/
	private String type;  
	
	/**到期日*/
	private java.util.Date expireDate;  
	
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
	
	/**金凯讯合约代码*/
	private String extend1;  
	
	/**是否前台显示*/
	private String extend2;  
	
	/**jkx抓取id*/
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
	
	/**合约代码，按规则生成，唯一键*/
	public void setSymbol(String symbol){
		this.symbol=symbol;
	}
	/**合约代码，按规则生成，唯一键*/
	public String getSymbol(){
		return this.symbol;
	}
	
	/**合约名称*/
	public void setSymbolName(String symbolName){
		this.symbolName=symbolName;
	}
	/**合约名称*/
	public String getSymbolName(){
		return this.symbolName;
	}
	
	/**商品标的*/
	public void setCommodity(String commodity){
		this.commodity=commodity;
	}
	/**商品标的*/
	public String getCommodity(){
		return this.commodity;
	}
	
	/**价格来源(值集获取PRICE_SOURCE）*/
	public void setPriceSource(String priceSource){
		this.priceSource=priceSource;
	}
	/**价格来源(值集获取PRICE_SOURCE）*/
	public String getPriceSource(){
		return this.priceSource;
	}
	
	/**市场（值集获取MARKET）*/
	public void setMarket(String market){
		this.market=market;
	}
	/**市场（值集获取MARKET）*/
	public String getMarket(){
		return this.market;
	}
	
	/**频率（daily, weekly, monthly，可配置成值集）*/
	public void setFrequency(String frequency){
		this.frequency=frequency;
	}
	/**频率（daily, weekly, monthly，可配置成值集）*/
	public String getFrequency(){
		return this.frequency;
	}
	
	/**货币单位, 人民币，美元等*/
	public void setCurrency(String currency){
		this.currency=currency;
	}
	/**货币单位, 人民币，美元等*/
	public String getCurrency(){
		return this.currency;
	}
	
	/**合约类型(continously, monthly, weekly, spot, premium,可配置成值集)*/
	public void setSymbolType(String symbolType){
		this.symbolType=symbolType;
	}
	/**合约类型(continously, monthly, weekly, spot, premium,可配置成值集)*/
	public String getSymbolType(){
		return this.symbolType;
	}
	
	/**类型,Future（纸货），Spot（现货），SWAP（掉期），Forward（期货），SPREAD（价差）,可配置成值集*/
	public void setType(String type){
		this.type=type;
	}
	/**类型,Future（纸货），Spot（现货），SWAP（掉期），Forward（期货），SPREAD（价差）,可配置成值集*/
	public String getType(){
		return this.type;
	}
	
	/**到期日*/
	public void setExpireDate(java.util.Date expireDate){
		this.expireDate=expireDate;
	}
	/**到期日*/
	public java.util.Date getExpireDate(){
		return this.expireDate;
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
	
	/**是否前台显示*/
	public void setExtend2(String extend2){
		this.extend2=extend2;
	}
	/**是否前台显示*/
	public String getExtend2(){
		return this.extend2;
	}
	
	/**jkx抓取id*/
	public void setExtend3(String extend3){
		this.extend3=extend3;
	}
	/**jkx抓取id*/
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
	map.put("symbolName",this.symbolName);
	map.put("commodity",this.commodity);
	map.put("priceSource",this.priceSource);
	map.put("market",this.market);
	map.put("frequency",this.frequency);
	map.put("currency",this.currency);
	map.put("symbolType",this.symbolType);
	map.put("type",this.type);
	map.put("expireDate",this.expireDate);
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