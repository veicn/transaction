package com.sinochem.crude.trade.shipping.domain;

import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;

public class ConfirmationSheet implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**主键_ID*/
	private Long confirmationSheetId;  
	
	/**UUID*/
	private String uuid;  
	
	/**买家ID*/
	private Long buyerId;  
	
	/**卖家ID*/
	private Long sellerId;  
	
	/**船舶确认单编码*/
	private String confirmationSheetCd;  
	
	/**订单ID*/
	private Long orderId;  
	
	/**订单编码*/
	private String orderNumber;  
	
	/**订单种类*/
	private String tradeTerms;  
	
	/**租船代理协议ID*/
	private Long shipAgreementId;  
	
	/**租船合同编号*/
	private String charterPartyNumber;  
	
	/**租船合同日期*/
	private String charterPartyDate;  
	
	/**发货人*/
	private String consignor;  
	
	/**租船人*/
	private String charterParty;  
	
	/**船舶名称*/
	private String vesselName;  
	
	/**产品*/
	private String product;  
	
	/**数量(吨）*/
	private String quantity;  
	
	/**误差范围*/
	private String rangeOfError;  
	
	/**装货港口*/
	private String portOfLoading;  
	
	/**卸货港口*/
	private String portOfDischarge;  
	
	/**受载期开始*/
	private java.util.Date laycanStrat;  
	
	/**受载期结束*/
	private java.util.Date laycanEnd;  
	
	/**附件地址*/
	private String uploadQ88;  
	
	/**Q88文件名称*/
	private String uploadQ88FileNm;  
	
	/**附件地址*/
	private String uploadCp;  
	
	/**合同文件名称*/
	private String uploadCpFileNm;  
	
	/**卖家是否在线确认标识*/
	private String confirmOnline;  
	
	/**到货期*/
	private java.util.Date ddr;  
	
	/**定价方式CD*/
	private String pricingCd;  
	
	/**定价方式*/
	private String pricingMethod;  
	
	/**计费种类CD*/
	private String revenueTonCd;  
	
	/**计费种类*/
	private String revenueTon;  
	
	/**基本运费率*/
	private java.math.BigDecimal basicFreightRate;  
	
	/**装卸滞纳金*/
	private java.math.BigDecimal demurrageRates;  
	
	/***/
	private Integer laytimeHours;  
	
	/**许可证日期*/
	private java.util.Date charterDate;  
	
	/**备考*/
	private String remark;  
	
	/**船代ID*/
	private Long shippingAgentId;  
	
	/**船代*/
	private String shippingAgent;  
	
	/**联系人*/
	private String linkman;  
	
	/**电话*/
	private String phoneNumber;  
	
	/**数据创建者ID*/
	private Long epMemberId;  
	
	/**标识*/
	private String imo;  
	
	/**状态*/
	private String status;  
	
	/**预留字段1*/
	private String ext1;  
	
	/**版本号*/
	private String version;  
	
	/**当前有效状态*/
	private String aliveFlag;  
	
	/**创建时间*/
	private java.util.Date createDate;  
	
	/**创建者*/
	private Long createUser;  
	
	/**更新时间*/
	private java.util.Date updateDate;  
	
	/**更新者*/
	private Long updateUser;

	/** 产品来源 */
	private String productSourceCode;

		/**主键_ID*/
	public void setConfirmationSheetId(Long confirmationSheetId){
		this.confirmationSheetId=confirmationSheetId;
	}
	/**主键_ID*/
	public Long getConfirmationSheetId(){
		return this.confirmationSheetId;
	}
	
	/**UUID*/
	public void setUuid(String uuid){
		this.uuid=uuid;
	}
	/**UUID*/
	public String getUuid(){
		return this.uuid;
	}
	
	/**买家ID*/
	public void setBuyerId(Long buyerId){
		this.buyerId=buyerId;
	}
	/**买家ID*/
	public Long getBuyerId(){
		return this.buyerId;
	}
	
	/**卖家ID*/
	public void setSellerId(Long sellerId){
		this.sellerId=sellerId;
	}
	/**卖家ID*/
	public Long getSellerId(){
		return this.sellerId;
	}
	
	/**船舶确认单编码*/
	public void setConfirmationSheetCd(String confirmationSheetCd){
		this.confirmationSheetCd=confirmationSheetCd;
	}
	/**船舶确认单编码*/
	public String getConfirmationSheetCd(){
		return this.confirmationSheetCd;
	}
	
	/**订单ID*/
	public void setOrderId(Long orderId){
		this.orderId=orderId;
	}
	/**订单ID*/
	public Long getOrderId(){
		return this.orderId;
	}
	
	/**订单编码*/
	public void setOrderNumber(String orderNumber){
		this.orderNumber=orderNumber;
	}
	/**订单编码*/
	public String getOrderNumber(){
		return this.orderNumber;
	}
	
	/**订单种类*/
	public void setTradeTerms(String tradeTerms){
		this.tradeTerms=tradeTerms;
	}
	/**订单种类*/
	public String getTradeTerms(){
		return this.tradeTerms;
	}
	
	/**租船代理协议ID*/
	public void setShipAgreementId(Long shipAgreementId){
		this.shipAgreementId=shipAgreementId;
	}
	/**租船代理协议ID*/
	public Long getShipAgreementId(){
		return this.shipAgreementId;
	}
	
	/**租船合同编号*/
	public void setCharterPartyNumber(String charterPartyNumber){
		this.charterPartyNumber=charterPartyNumber;
	}
	/**租船合同编号*/
	public String getCharterPartyNumber(){
		return this.charterPartyNumber;
	}
	
	/**租船合同日期*/
	public void setCharterPartyDate(String charterPartyDate){
		this.charterPartyDate=charterPartyDate;
	}
	/**租船合同日期*/
	public String getCharterPartyDate(){
		return this.charterPartyDate;
	}
	
	/**发货人*/
	public void setConsignor(String consignor){
		this.consignor=consignor;
	}
	/**发货人*/
	public String getConsignor(){
		return this.consignor;
	}
	
	/**租船人*/
	public void setCharterParty(String charterParty){
		this.charterParty=charterParty;
	}
	/**租船人*/
	public String getCharterParty(){
		return this.charterParty;
	}
	
	/**船舶名称*/
	public void setVesselName(String vesselName){
		this.vesselName=vesselName;
	}
	/**船舶名称*/
	public String getVesselName(){
		return this.vesselName;
	}
	
	/**产品*/
	public void setProduct(String product){
		this.product=product;
	}
	/**产品*/
	public String getProduct(){
		return this.product;
	}
	
	/**数量(吨）*/
	public void setQuantity(String quantity){
		this.quantity=quantity;
	}
	/**数量(吨）*/
	public String getQuantity(){
		return this.quantity;
	}
	
	/**误差范围*/
	public void setRangeOfError(String rangeOfError){
		this.rangeOfError=rangeOfError;
	}
	/**误差范围*/
	public String getRangeOfError(){
		return this.rangeOfError;
	}
	
	/**装货港口*/
	public void setPortOfLoading(String portOfLoading){
		this.portOfLoading=portOfLoading;
	}
	/**装货港口*/
	public String getPortOfLoading(){
		return this.portOfLoading;
	}
	
	/**卸货港口*/
	public void setPortOfDischarge(String portOfDischarge){
		this.portOfDischarge=portOfDischarge;
	}
	/**卸货港口*/
	public String getPortOfDischarge(){
		return this.portOfDischarge;
	}
	
	/**受载期开始*/
	public void setLaycanStrat(java.util.Date laycanStrat){
		this.laycanStrat=laycanStrat;
	}
	/**受载期开始*/
	public java.util.Date getLaycanStrat(){
		return this.laycanStrat;
	}
	
	/**受载期结束*/
	public void setLaycanEnd(java.util.Date laycanEnd){
		this.laycanEnd=laycanEnd;
	}
	/**受载期结束*/
	public java.util.Date getLaycanEnd(){
		return this.laycanEnd;
	}
	
	/**附件地址*/
	public void setUploadQ88(String uploadQ88){
		this.uploadQ88=uploadQ88;
	}
	/**附件地址*/
	public String getUploadQ88(){
		return this.uploadQ88;
	}
	
	/**Q88文件名称*/
	public void setUploadQ88FileNm(String uploadQ88FileNm){
		this.uploadQ88FileNm=uploadQ88FileNm;
	}
	/**Q88文件名称*/
	public String getUploadQ88FileNm(){
		return this.uploadQ88FileNm;
	}
	
	/**附件地址*/
	public void setUploadCp(String uploadCp){
		this.uploadCp=uploadCp;
	}
	/**附件地址*/
	public String getUploadCp(){
		return this.uploadCp;
	}
	
	/**合同文件名称*/
	public void setUploadCpFileNm(String uploadCpFileNm){
		this.uploadCpFileNm=uploadCpFileNm;
	}
	/**合同文件名称*/
	public String getUploadCpFileNm(){
		return this.uploadCpFileNm;
	}
	
	/**卖家是否在线确认标识*/
	public void setConfirmOnline(String confirmOnline){
		this.confirmOnline=confirmOnline;
	}
	/**卖家是否在线确认标识*/
	public String getConfirmOnline(){
		return this.confirmOnline;
	}
	
	/**到货期*/
	public void setDdr(java.util.Date ddr){
		this.ddr=ddr;
	}
	/**到货期*/
	public java.util.Date getDdr(){
		return this.ddr;
	}
	
	/**定价方式CD*/
	public void setPricingCd(String pricingCd){
		this.pricingCd=pricingCd;
	}
	/**定价方式CD*/
	public String getPricingCd(){
		return this.pricingCd;
	}
	
	/**定价方式*/
	public void setPricingMethod(String pricingMethod){
		this.pricingMethod=pricingMethod;
	}
	/**定价方式*/
	public String getPricingMethod(){
		return this.pricingMethod;
	}
	
	/**计费种类CD*/
	public void setRevenueTonCd(String revenueTonCd){
		this.revenueTonCd=revenueTonCd;
	}
	/**计费种类CD*/
	public String getRevenueTonCd(){
		return this.revenueTonCd;
	}
	
	/**计费种类*/
	public void setRevenueTon(String revenueTon){
		this.revenueTon=revenueTon;
	}
	/**计费种类*/
	public String getRevenueTon(){
		return this.revenueTon;
	}
	
	/**基本运费率*/
	public void setBasicFreightRate(java.math.BigDecimal basicFreightRate){
		this.basicFreightRate=basicFreightRate;
	}
	/**基本运费率*/
	public java.math.BigDecimal getBasicFreightRate(){
		return this.basicFreightRate;
	}
	
	/**装卸滞纳金*/
	public void setDemurrageRates(java.math.BigDecimal demurrageRates){
		this.demurrageRates=demurrageRates;
	}
	/**装卸滞纳金*/
	public java.math.BigDecimal getDemurrageRates(){
		return this.demurrageRates;
	}
	
	/***/
	public void setLaytimeHours(Integer laytimeHours){
		this.laytimeHours=laytimeHours;
	}
	/***/
	public Integer getLaytimeHours(){
		return this.laytimeHours;
	}
	
	/**许可证日期*/
	public void setCharterDate(java.util.Date charterDate){
		this.charterDate=charterDate;
	}
	/**许可证日期*/
	public java.util.Date getCharterDate(){
		return this.charterDate;
	}
	
	/**备考*/
	public void setRemark(String remark){
		this.remark=remark;
	}
	/**备考*/
	public String getRemark(){
		return this.remark;
	}
	
	/**船代ID*/
	public void setShippingAgentId(Long shippingAgentId){
		this.shippingAgentId=shippingAgentId;
	}
	/**船代ID*/
	public Long getShippingAgentId(){
		return this.shippingAgentId;
	}
	
	/**船代*/
	public void setShippingAgent(String shippingAgent){
		this.shippingAgent=shippingAgent;
	}
	/**船代*/
	public String getShippingAgent(){
		return this.shippingAgent;
	}
	
	/**联系人*/
	public void setLinkman(String linkman){
		this.linkman=linkman;
	}
	/**联系人*/
	public String getLinkman(){
		return this.linkman;
	}
	
	/**电话*/
	public void setPhoneNumber(String phoneNumber){
		this.phoneNumber=phoneNumber;
	}
	/**电话*/
	public String getPhoneNumber(){
		return this.phoneNumber;
	}
	
	/**数据创建者ID*/
	public void setEpMemberId(Long epMemberId){
		this.epMemberId=epMemberId;
	}
	/**数据创建者ID*/
	public Long getEpMemberId(){
		return this.epMemberId;
	}
	
	/**标识*/
	public void setImo(String imo){
		this.imo=imo;
	}
	/**标识*/
	public String getImo(){
		return this.imo;
	}
	
	/**状态*/
	public void setStatus(String status){
		this.status=status;
	}
	/**状态*/
	public String getStatus(){
		return this.status;
	}
	
	/**预留字段1*/
	public void setExt1(String ext1){
		this.ext1=ext1;
	}
	/**预留字段1*/
	public String getExt1(){
		return this.ext1;
	}
	
	/**版本号*/
	public void setVersion(String version){
		this.version=version;
	}
	/**版本号*/
	public String getVersion(){
		return this.version;
	}
	
	/**当前有效状态*/
	public void setAliveFlag(String aliveFlag){
		this.aliveFlag=aliveFlag;
	}
	/**当前有效状态*/
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
	
	/**创建者*/
	public void setCreateUser(Long createUser){
		this.createUser=createUser;
	}
	/**创建者*/
	public Long getCreateUser(){
		return this.createUser;
	}
	
	/**更新时间*/
	public void setUpdateDate(java.util.Date updateDate){
		this.updateDate=updateDate;
	}
	/**更新时间*/
	public java.util.Date getUpdateDate(){
		return this.updateDate;
	}
	
	/**更新者*/
	public void setUpdateUser(Long updateUser){
		this.updateUser=updateUser;
	}
	/**更新者*/
	public Long getUpdateUser(){
		return this.updateUser;
	}

	public String getProductSourceCode() {
		return productSourceCode;
	}

	public void setProductSourceCode(String productSourceCode) {
		this.productSourceCode = productSourceCode;
	}
	
	public Map<String, Object> toMap() {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("confirmationSheetId",this.confirmationSheetId);
		map.put("uuid",this.uuid);
		map.put("buyerId",this.buyerId);
		map.put("sellerId",this.sellerId);
		map.put("confirmationSheetCd",this.confirmationSheetCd);
		map.put("orderId",this.orderId);
		map.put("orderNumber",this.orderNumber);
		map.put("tradeTerms",this.tradeTerms);
		map.put("shipAgreementId",this.shipAgreementId);
		map.put("charterPartyNumber",this.charterPartyNumber);
		map.put("charterPartyDate",this.charterPartyDate);
		map.put("consignor",this.consignor);
		map.put("charterParty",this.charterParty);
		map.put("vesselName",this.vesselName);
		map.put("product",this.product);
		map.put("quantity",this.quantity);
		map.put("rangeOfError",this.rangeOfError);
		map.put("portOfLoading",this.portOfLoading);
		map.put("portOfDischarge",this.portOfDischarge);
		map.put("laycanStrat",this.laycanStrat);
		map.put("laycanEnd",this.laycanEnd);
		map.put("uploadQ88",this.uploadQ88);
		map.put("uploadQ88FileNm",this.uploadQ88FileNm);
		map.put("uploadCp",this.uploadCp);
		map.put("uploadCpFileNm",this.uploadCpFileNm);
		map.put("confirmOnline",this.confirmOnline);
		map.put("ddr",this.ddr);
		map.put("pricingCd",this.pricingCd);
		map.put("pricingMethod",this.pricingMethod);
		map.put("revenueTonCd",this.revenueTonCd);
		map.put("revenueTon",this.revenueTon);
		map.put("basicFreightRate",this.basicFreightRate);
		map.put("demurrageRates",this.demurrageRates);
		map.put("laytimeHours",this.laytimeHours);
		map.put("charterDate",this.charterDate);
		map.put("remark",this.remark);
		map.put("shippingAgentId",this.shippingAgentId);
		map.put("shippingAgent",this.shippingAgent);
		map.put("linkman",this.linkman);
		map.put("phoneNumber",this.phoneNumber);
		map.put("epMemberId",this.epMemberId);
		map.put("imo",this.imo);
		map.put("status",this.status);
		map.put("ext1",this.ext1);
		map.put("version",this.version);
		map.put("aliveFlag",this.aliveFlag);
		map.put("createDate",this.createDate);
		map.put("createUser",this.createUser);
		map.put("updateDate",this.updateDate);
		map.put("updateUser",this.updateUser);
		map.put("productSourceCode",this.productSourceCode);
		return map;
	}
	
}