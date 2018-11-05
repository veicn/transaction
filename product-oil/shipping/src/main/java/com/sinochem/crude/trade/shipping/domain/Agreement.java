package com.sinochem.crude.trade.shipping.domain;

import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;

public class Agreement implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**主键_ID*/
	private Long agreementId;  
	
	/**UUID*/
	private String uuid;  
	
	/**买家ID*/
	private Long buyerId;  
	
	/**卖家ID*/
	private Long sellerId;  
	
	/**租船需求表ID*/
	private Long demandsId;  
	
	/**租船代理协议编码*/
	private String agreementCd;  
	
	/**租船代理名*/
	private String charteringAgentNm;  
	
	/**订单ID*/
	private Long orderId;  
	
	/**签约方日期*/
	private java.util.Date charterPartyDate;  
	
	/**托运人*/
	private String consignor;  
	
	/**租船契约*/
	private String charterParty;  
	
	/**船名*/
	private String vesselName;  
	
	/**产品*/
	private String productNm;  
	
	/**数量*/
	private String quantity;  
	
	/**误差范围*/
	private String rangeOfError;  
	
	/**装货港*/
	private String portOfLoading;  
	
	/**卸货港*/
	private String portOfDischarge;  
	
	/**受载期开始*/
	private java.util.Date laycanStrat;  
	
	/**受载期结束*/
	private java.util.Date laycanEnd;  
	
	/**标识*/
	private String imo;  
	
	/**需求单创建者ID*/
	private Long epMemberId;  
	
	/**附件地址*/
	private String uploadQ88;  
	
	/**Q88文件名称*/
	private String uploadQ88FileNm;  
	
	/**附件地址*/
	private String uploadCp;  
	
	/**合同文件名称*/
	private String uploadCpFileNm;  
	
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
	public void setAgreementId(Long agreementId){
		this.agreementId=agreementId;
	}

	/**主键_ID*/
	public Long getAgreementId(){
		return this.agreementId;
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
	
	/**租船需求表ID*/
	public void setDemandsId(Long demandsId){
		this.demandsId=demandsId;
	}
	/**租船需求表ID*/
	public Long getDemandsId(){
		return this.demandsId;
	}
	
	/**租船代理协议编码*/
	public void setAgreementCd(String agreementCd){
		this.agreementCd=agreementCd;
	}
	/**租船代理协议编码*/
	public String getAgreementCd(){
		return this.agreementCd;
	}
	
	/**租船代理名*/
	public void setCharteringAgentNm(String charteringAgentNm){
		this.charteringAgentNm=charteringAgentNm;
	}
	/**租船代理名*/
	public String getCharteringAgentNm(){
		return this.charteringAgentNm;
	}
	
	/**订单ID*/
	public void setOrderId(Long orderId){
		this.orderId=orderId;
	}
	/**订单ID*/
	public Long getOrderId(){
		return this.orderId;
	}
	
	/**签约方日期*/
	public void setCharterPartyDate(java.util.Date charterPartyDate){
		this.charterPartyDate=charterPartyDate;
	}
	/**签约方日期*/
	public java.util.Date getCharterPartyDate(){
		return this.charterPartyDate;
	}
	
	/**托运人*/
	public void setConsignor(String consignor){
		this.consignor=consignor;
	}
	/**托运人*/
	public String getConsignor(){
		return this.consignor;
	}
	
	/**租船契约*/
	public void setCharterParty(String charterParty){
		this.charterParty=charterParty;
	}
	/**租船契约*/
	public String getCharterParty(){
		return this.charterParty;
	}
	
	/**船名*/
	public void setVesselName(String vesselName){
		this.vesselName=vesselName;
	}
	/**船名*/
	public String getVesselName(){
		return this.vesselName;
	}
	
	/**产品*/
	public void setProductNm(String productNm){
		this.productNm=productNm;
	}
	/**产品*/
	public String getProductNm(){
		return this.productNm;
	}
	
	/**数量*/
	public void setQuantity(String quantity){
		this.quantity=quantity;
	}
	/**数量*/
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
	
	/**装货港*/
	public void setPortOfLoading(String portOfLoading){
		this.portOfLoading=portOfLoading;
	}
	/**装货港*/
	public String getPortOfLoading(){
		return this.portOfLoading;
	}
	
	/**卸货港*/
	public void setPortOfDischarge(String portOfDischarge){
		this.portOfDischarge=portOfDischarge;
	}
	/**卸货港*/
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
	
	/**标识*/
	public void setImo(String imo){
		this.imo=imo;
	}
	/**标识*/
	public String getImo(){
		return this.imo;
	}
	
	/**需求单创建者ID*/
	public void setEpMemberId(Long epMemberId){
		this.epMemberId=epMemberId;
	}
	/**需求单创建者ID*/
	public Long getEpMemberId(){
		return this.epMemberId;
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
		map.put("agreementId",this.agreementId);
		map.put("uuid",this.uuid);
		map.put("buyerId",this.buyerId);
		map.put("sellerId",this.sellerId);
		map.put("demandsId",this.demandsId);
		map.put("agreementCd",this.agreementCd);
		map.put("charteringAgentNm",this.charteringAgentNm);
		map.put("orderId",this.orderId);
		map.put("charterPartyDate",this.charterPartyDate);
		map.put("consignor",this.consignor);
		map.put("charterParty",this.charterParty);
		map.put("vesselName",this.vesselName);
		map.put("productNm",this.productNm);
		map.put("quantity",this.quantity);
		map.put("rangeOfError",this.rangeOfError);
		map.put("portOfLoading",this.portOfLoading);
		map.put("portOfDischarge",this.portOfDischarge);
		map.put("laycanStrat",this.laycanStrat);
		map.put("laycanEnd",this.laycanEnd);
		map.put("imo",this.imo);
		map.put("epMemberId",this.epMemberId);
		map.put("uploadQ88",this.uploadQ88);
		map.put("uploadQ88FileNm",this.uploadQ88FileNm);
		map.put("uploadCp",this.uploadCp);
		map.put("uploadCpFileNm",this.uploadCpFileNm);
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