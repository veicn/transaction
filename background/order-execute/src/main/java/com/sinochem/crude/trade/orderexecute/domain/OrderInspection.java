package com.sinochem.crude.trade.orderexecute.domain;

import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;

public class OrderInspection implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/***/
	private String id;  
	
	/***/
	private String uuid;  
	
	/**订单ID*/
	private Long orderId;  
	
	/**订单编号*/
	private String orderNo;  
	
	/**出入境检验检疫局*/
	private String quarantineBureau;  
	
	/**备案号/组织机构代码*/
	private String organizationCode;  
	
	/**编号*/
	private String code;  
	
	/**品名*/
	private String commodityName;  
	
	/**HS编码，默认2709000000*/
	private String hsCode;  
	
	/**数（重）量净桶，三位小数，如果提单有值则自动带入*/
	private java.math.BigDecimal nightstool;  
	
	/**数（重）量净吨，三位小数，如果提单有值则自动带入*/
	private java.math.BigDecimal netTon;  
	
	/**包装情况*/
	private String packaging;  
	
	/**信用证/合同号*/
	private String contractNo;  
	
	/**许可文件号*/
	private String licenseNumber;  
	
	/**进口货物收货单位及地址*/
	private String receivingCompany;  
	
	/**进口货物提/运单号*/
	private String transportNo;  
	
	/**其他特殊要求*/
	private String otherRequirements;  
	
	/**委托公司*/
	private String consignor;  
	
	/**代理报检注册登记号*/
	private String registrationNo;  
	
	/**检验检疫事宜,(1-5的多选，多个用逗号拼接)*/
	private String matters;  
	
	/**其他与报检有关的相关事宜*/
	private String otherMatters;  
	
	/**联系人*/
	private String contactPerson;  
	
	/**联系电话*/
	private String contactPhone;  
	
	/**本委托书有效期至,默认30天后日期*/
	private java.util.Date validPeriod;  
	
	/**委托人签字日期,默认当天*/
	private java.util.Date entrustDate;  
	
	/**受委托联 系 人*/
	private String entrustedContact;  
	
	/**受委托联系电话*/
	private String entrustedPhone;  
	
	/**受委托人签字日期,默认当天*/
	private java.util.Date entrustedDate;  
	
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
	
	/**出入境检验检疫局*/
	public void setQuarantineBureau(String quarantineBureau){
		this.quarantineBureau=quarantineBureau;
	}
	/**出入境检验检疫局*/
	public String getQuarantineBureau(){
		return this.quarantineBureau;
	}
	
	/**备案号/组织机构代码*/
	public void setOrganizationCode(String organizationCode){
		this.organizationCode=organizationCode;
	}
	/**备案号/组织机构代码*/
	public String getOrganizationCode(){
		return this.organizationCode;
	}
	
	/**编号*/
	public void setCode(String code){
		this.code=code;
	}
	/**编号*/
	public String getCode(){
		return this.code;
	}
	
	/**品名*/
	public void setCommodityName(String commodityName){
		this.commodityName=commodityName;
	}
	/**品名*/
	public String getCommodityName(){
		return this.commodityName;
	}
	
	/**HS编码，默认2709000000*/
	public void setHsCode(String hsCode){
		this.hsCode=hsCode;
	}
	/**HS编码，默认2709000000*/
	public String getHsCode(){
		return this.hsCode;
	}
	
	/**数（重）量净桶，三位小数，如果提单有值则自动带入*/
	public void setNightstool(java.math.BigDecimal nightstool){
		this.nightstool=nightstool;
	}
	/**数（重）量净桶，三位小数，如果提单有值则自动带入*/
	public java.math.BigDecimal getNightstool(){
		return this.nightstool;
	}
	
	/**数（重）量净吨，三位小数，如果提单有值则自动带入*/
	public void setNetTon(java.math.BigDecimal netTon){
		this.netTon=netTon;
	}
	/**数（重）量净吨，三位小数，如果提单有值则自动带入*/
	public java.math.BigDecimal getNetTon(){
		return this.netTon;
	}
	
	/**包装情况*/
	public void setPackaging(String packaging){
		this.packaging=packaging;
	}
	/**包装情况*/
	public String getPackaging(){
		return this.packaging;
	}
	
	/**信用证/合同号*/
	public void setContractNo(String contractNo){
		this.contractNo=contractNo;
	}
	/**信用证/合同号*/
	public String getContractNo(){
		return this.contractNo;
	}
	
	/**许可文件号*/
	public void setLicenseNumber(String licenseNumber){
		this.licenseNumber=licenseNumber;
	}
	/**许可文件号*/
	public String getLicenseNumber(){
		return this.licenseNumber;
	}
	
	/**进口货物收货单位及地址*/
	public void setReceivingCompany(String receivingCompany){
		this.receivingCompany=receivingCompany;
	}
	/**进口货物收货单位及地址*/
	public String getReceivingCompany(){
		return this.receivingCompany;
	}
	
	/**进口货物提/运单号*/
	public void setTransportNo(String transportNo){
		this.transportNo=transportNo;
	}
	/**进口货物提/运单号*/
	public String getTransportNo(){
		return this.transportNo;
	}
	
	/**其他特殊要求*/
	public void setOtherRequirements(String otherRequirements){
		this.otherRequirements=otherRequirements;
	}
	/**其他特殊要求*/
	public String getOtherRequirements(){
		return this.otherRequirements;
	}
	
	/**委托公司*/
	public void setConsignor(String consignor){
		this.consignor=consignor;
	}
	/**委托公司*/
	public String getConsignor(){
		return this.consignor;
	}
	
	/**代理报检注册登记号*/
	public void setRegistrationNo(String registrationNo){
		this.registrationNo=registrationNo;
	}
	/**代理报检注册登记号*/
	public String getRegistrationNo(){
		return this.registrationNo;
	}
	
	/**检验检疫事宜,(1-5的多选，多个用逗号拼接)*/
	public void setMatters(String matters){
		this.matters=matters;
	}
	/**检验检疫事宜,(1-5的多选，多个用逗号拼接)*/
	public String getMatters(){
		return this.matters;
	}
	
	/**其他与报检有关的相关事宜*/
	public void setOtherMatters(String otherMatters){
		this.otherMatters=otherMatters;
	}
	/**其他与报检有关的相关事宜*/
	public String getOtherMatters(){
		return this.otherMatters;
	}
	
	/**联系人*/
	public void setContactPerson(String contactPerson){
		this.contactPerson=contactPerson;
	}
	/**联系人*/
	public String getContactPerson(){
		return this.contactPerson;
	}
	
	/**联系电话*/
	public void setContactPhone(String contactPhone){
		this.contactPhone=contactPhone;
	}
	/**联系电话*/
	public String getContactPhone(){
		return this.contactPhone;
	}
	
	/**本委托书有效期至,默认30天后日期*/
	public void setValidPeriod(java.util.Date validPeriod){
		this.validPeriod=validPeriod;
	}
	/**本委托书有效期至,默认30天后日期*/
	public java.util.Date getValidPeriod(){
		return this.validPeriod;
	}
	
	/**委托人签字日期,默认当天*/
	public void setEntrustDate(java.util.Date entrustDate){
		this.entrustDate=entrustDate;
	}
	/**委托人签字日期,默认当天*/
	public java.util.Date getEntrustDate(){
		return this.entrustDate;
	}
	
	/**受委托联 系 人*/
	public void setEntrustedContact(String entrustedContact){
		this.entrustedContact=entrustedContact;
	}
	/**受委托联 系 人*/
	public String getEntrustedContact(){
		return this.entrustedContact;
	}
	
	/**受委托联系电话*/
	public void setEntrustedPhone(String entrustedPhone){
		this.entrustedPhone=entrustedPhone;
	}
	/**受委托联系电话*/
	public String getEntrustedPhone(){
		return this.entrustedPhone;
	}
	
	/**受委托人签字日期,默认当天*/
	public void setEntrustedDate(java.util.Date entrustedDate){
		this.entrustedDate=entrustedDate;
	}
	/**受委托人签字日期,默认当天*/
	public java.util.Date getEntrustedDate(){
		return this.entrustedDate;
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
	map.put("quarantineBureau",this.quarantineBureau);
	map.put("organizationCode",this.organizationCode);
	map.put("code",this.code);
	map.put("commodityName",this.commodityName);
	map.put("hsCode",this.hsCode);
	map.put("nightstool",this.nightstool);
	map.put("netTon",this.netTon);
	map.put("packaging",this.packaging);
	map.put("contractNo",this.contractNo);
	map.put("licenseNumber",this.licenseNumber);
	map.put("receivingCompany",this.receivingCompany);
	map.put("transportNo",this.transportNo);
	map.put("otherRequirements",this.otherRequirements);
	map.put("consignor",this.consignor);
	map.put("registrationNo",this.registrationNo);
	map.put("matters",this.matters);
	map.put("otherMatters",this.otherMatters);
	map.put("contactPerson",this.contactPerson);
	map.put("contactPhone",this.contactPhone);
	map.put("validPeriod",this.validPeriod);
	map.put("entrustDate",this.entrustDate);
	map.put("entrustedContact",this.entrustedContact);
	map.put("entrustedPhone",this.entrustedPhone);
	map.put("entrustedDate",this.entrustedDate);
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