package com.sinochem.crude.trade.orderexecute.domain;

import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;

public class OrderCustoms implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/***/
	private String id;  
	
	/***/
	private String uuid;  
	
	/**订单ID*/
	private Long orderId;  
	
	/**订单编号*/
	private String orderNo;  
	
	/**被委托方,受委托方*/
	private String consignee;  
	
	/**委托方式*/
	private String entrustMode;  
	
	/**委托书有效期*/
	private java.util.Date validPeriod;  
	
	/**委托日期*/
	private java.util.Date entrustDate;  
	
	/**委托方*/
	private String consignor;  
	
	/**货物名称*/
	private String goodsName;  
	
	/**HS编码*/
	private String hsCode;  
	
	/**货物总价（2位小数）*/
	private java.math.BigDecimal totalAmount;  
	
	/**进出口日期*/
	private java.util.Date importDate;  
	
	/**提单号*/
	private String billNo;  
	
	/**贸易方式*/
	private String tradeMode;  
	
	/**原产地／货源*/
	private String origin;  
	
	/**合同号*/
	private String contractNo;  
	
	/**数量净桶(两位小数)*/
	private java.math.BigDecimal nightstool;  
	
	/**数量净吨(两位小数)*/
	private java.math.BigDecimal netTon;  
	
	/**经办人签章*/
	private String operator;  
	
	/**联系电话*/
	private String contactPhone;  
	
	/**盖章日期*/
	private java.util.Date stampDate;  
	
	/**创建人*/
	private String createPerson;  
	
	/**创建日期*/
	private java.util.Date createDate;  
	
	/***/
	private String modifyPerson;  
	
	/**修改日期*/
	private java.util.Date modifyDate;  
	
	/**当前有效状态*/
	private String aliveFlag;  
	
	/**语言类型*/
	private String langVer;  
	
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
	
		/***/
	public void setId(String id){
		this.id=id;
	}
	/***/
	public String getId(){
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
	
	/**订单ID*/
	public void setOrderId(Long orderId){
		this.orderId=orderId;
	}
	/**订单ID*/
	public Long getOrderId(){
		return this.orderId;
	}
	
	/**订单编号*/
	public void setOrderNo(String orderNo){
		this.orderNo=orderNo;
	}
	/**订单编号*/
	public String getOrderNo(){
		return this.orderNo;
	}
	
	/**被委托方,受委托方*/
	public void setConsignee(String consignee){
		this.consignee=consignee;
	}
	/**被委托方,受委托方*/
	public String getConsignee(){
		return this.consignee;
	}
	
	/**委托方式*/
	public void setEntrustMode(String entrustMode){
		this.entrustMode=entrustMode;
	}
	/**委托方式*/
	public String getEntrustMode(){
		return this.entrustMode;
	}
	
	/**委托书有效期*/
	public void setValidPeriod(java.util.Date validPeriod){
		this.validPeriod=validPeriod;
	}
	/**委托书有效期*/
	public java.util.Date getValidPeriod(){
		return this.validPeriod;
	}
	
	/**委托日期*/
	public void setEntrustDate(java.util.Date entrustDate){
		this.entrustDate=entrustDate;
	}
	/**委托日期*/
	public java.util.Date getEntrustDate(){
		return this.entrustDate;
	}
	
	/**委托方*/
	public void setConsignor(String consignor){
		this.consignor=consignor;
	}
	/**委托方*/
	public String getConsignor(){
		return this.consignor;
	}
	
	/**货物名称*/
	public void setGoodsName(String goodsName){
		this.goodsName=goodsName;
	}
	/**货物名称*/
	public String getGoodsName(){
		return this.goodsName;
	}
	
	/**HS编码*/
	public void setHsCode(String hsCode){
		this.hsCode=hsCode;
	}
	/**HS编码*/
	public String getHsCode(){
		return this.hsCode;
	}
	
	/**货物总价（2位小数）*/
	public void setTotalAmount(java.math.BigDecimal totalAmount){
		this.totalAmount=totalAmount;
	}
	/**货物总价（2位小数）*/
	public java.math.BigDecimal getTotalAmount(){
		return this.totalAmount;
	}
	
	/**进出口日期*/
	public void setImportDate(java.util.Date importDate){
		this.importDate=importDate;
	}
	/**进出口日期*/
	public java.util.Date getImportDate(){
		return this.importDate;
	}
	
	/**提单号*/
	public void setBillNo(String billNo){
		this.billNo=billNo;
	}
	/**提单号*/
	public String getBillNo(){
		return this.billNo;
	}
	
	/**贸易方式*/
	public void setTradeMode(String tradeMode){
		this.tradeMode=tradeMode;
	}
	/**贸易方式*/
	public String getTradeMode(){
		return this.tradeMode;
	}
	
	/**原产地／货源*/
	public void setOrigin(String origin){
		this.origin=origin;
	}
	/**原产地／货源*/
	public String getOrigin(){
		return this.origin;
	}
	
	/**合同号*/
	public void setContractNo(String contractNo){
		this.contractNo=contractNo;
	}
	/**合同号*/
	public String getContractNo(){
		return this.contractNo;
	}
	
	/**数量净桶(两位小数)*/
	public void setNightstool(java.math.BigDecimal nightstool){
		this.nightstool=nightstool;
	}
	/**数量净桶(两位小数)*/
	public java.math.BigDecimal getNightstool(){
		return this.nightstool;
	}
	
	/**数量净吨(两位小数)*/
	public void setNetTon(java.math.BigDecimal netTon){
		this.netTon=netTon;
	}
	/**数量净吨(两位小数)*/
	public java.math.BigDecimal getNetTon(){
		return this.netTon;
	}
	
	/**经办人签章*/
	public void setOperator(String operator){
		this.operator=operator;
	}
	/**经办人签章*/
	public String getOperator(){
		return this.operator;
	}
	
	/**联系电话*/
	public void setContactPhone(String contactPhone){
		this.contactPhone=contactPhone;
	}
	/**联系电话*/
	public String getContactPhone(){
		return this.contactPhone;
	}
	
	/**盖章日期*/
	public void setStampDate(java.util.Date stampDate){
		this.stampDate=stampDate;
	}
	/**盖章日期*/
	public java.util.Date getStampDate(){
		return this.stampDate;
	}
	
	/**创建人*/
	public void setCreatePerson(String createPerson){
		this.createPerson=createPerson;
	}
	/**创建人*/
	public String getCreatePerson(){
		return this.createPerson;
	}
	
	/**创建日期*/
	public void setCreateDate(java.util.Date createDate){
		this.createDate=createDate;
	}
	/**创建日期*/
	public java.util.Date getCreateDate(){
		return this.createDate;
	}
	
	/***/
	public void setModifyPerson(String modifyPerson){
		this.modifyPerson=modifyPerson;
	}
	/***/
	public String getModifyPerson(){
		return this.modifyPerson;
	}
	
	/**修改日期*/
	public void setModifyDate(java.util.Date modifyDate){
		this.modifyDate=modifyDate;
	}
	/**修改日期*/
	public java.util.Date getModifyDate(){
		return this.modifyDate;
	}
	
	/**当前有效状态*/
	public void setAliveFlag(String aliveFlag){
		this.aliveFlag=aliveFlag;
	}
	/**当前有效状态*/
	public String getAliveFlag(){
		return this.aliveFlag;
	}
	
	/**语言类型*/
	public void setLangVer(String langVer){
		this.langVer=langVer;
	}
	/**语言类型*/
	public String getLangVer(){
		return this.langVer;
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
	map.put("orderId",this.orderId);
	map.put("orderNo",this.orderNo);
	map.put("consignee",this.consignee);
	map.put("entrustMode",this.entrustMode);
	map.put("validPeriod",this.validPeriod);
	map.put("entrustDate",this.entrustDate);
	map.put("consignor",this.consignor);
	map.put("goodsName",this.goodsName);
	map.put("hsCode",this.hsCode);
	map.put("totalAmount",this.totalAmount);
	map.put("importDate",this.importDate);
	map.put("billNo",this.billNo);
	map.put("tradeMode",this.tradeMode);
	map.put("origin",this.origin);
	map.put("contractNo",this.contractNo);
	map.put("nightstool",this.nightstool);
	map.put("netTon",this.netTon);
	map.put("operator",this.operator);
	map.put("contactPhone",this.contactPhone);
	map.put("stampDate",this.stampDate);
	map.put("createPerson",this.createPerson);
	map.put("createDate",this.createDate);
	map.put("modifyPerson",this.modifyPerson);
	map.put("modifyDate",this.modifyDate);
	map.put("aliveFlag",this.aliveFlag);
	map.put("langVer",this.langVer);
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