package com.sinochem.crude.trade.transport.domain;

import java.io.Serializable;

public class Pallet implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**业务唯一键*/
	private Long palletId;  
	
	/**UUID*/
	private String uuid;  
	
	/**公司id*/
	private Long epMemberId;  
	
	/**会员公司名称*/
	private String epMemberName;  
	
	/**代理协议编号(船名+日期+委托方英文简称)*/
	private String agreementCode;  
	
	/**单位名称*/
	private String unitName;  
	
	/**油种*/
	private String oilName;  
	
	/**租船编号*/
	private String palletCode;  
	
	/**租船方式（1租船2拼船）*/
	private String palletType;  
	
	/**是否有订单租船（1有0没有）*/
	private String orderNo;  
	
	/**订单编号*/
	private String orderCode;  
	
	/**油品合同号*/
	private String orderPact;  
	
	/**状态（1待处理2处理中3已报盘4已确认5已关闭）*/
	private String status;  
	
	/**数量（桶）*/
	private java.math.BigDecimal quantity;  
	
	/**装期开始*/
	private java.util.Date laycanBeg;  
	
	/**装期结束*/
	private java.util.Date laycanEnd;  
	
	/**船型*/
	private String shipType;  
	
	/**船龄*/
	private String shipAge;  
	
	/**租船其它要求*/
	private String remark;  
	
	/**指定承运商名称*/
	private String traderName;  
	
	/**指定承运商id*/
	private Long traderId;  
	
	/**公司id*/
	private Long companyId;  
	
	/**公司名称*/
	private String companyName;  
	
	/**电话*/
	private String tel;  
	
	/**邮箱*/
	private String email;  
	
	/**联系人*/
	private String contacts;  
	
	/**是否有效(1有效0无效)*/
	private String aliveFlag;  
	
	/**版本号*/
	private String version;  
	
	/**语言版本*/
	private String langVer;  
	
	/**创建时间*/
	private java.util.Date createDate;  
	
	/**修改时间*/
	private java.util.Date updateDate;  
	
	/**创建人*/
	private Long createUser;  
	
	/**修改人*/
	private Long updateUser;  
	
	/**扩展字段1*/
	private String ext1;  
	
	/**第一装港*/
	private String loadPort;  
	
	/**船龄开始*/
	private Integer shipAgeBeg;  
	
	/**船龄结束*/
	private Integer shipAgeEnd;  
	
	/**是否先选择船盘（1是0否）*/
	private String plateSel;  
	
	/**是否指定租船代理（1是0否）*/
	private String traderSel;  
	
	/**船盘id*/
	private Long shipPlateId;  
	
	/**船盘uuid*/
	private String shipPlateUuid;  
	
	/**第一装港*/
	private String FLoadPort;  
	
	/**是否生成协议（1是0否）*/
	private String agreementFlag;  
	
	/**会员公司名称英文*/
	private String epMemberNameEn;  
	
	/**指定承运商名称英文*/
	private String traderNameEn;  
	
	/**公司英文*/
	private String companyNameEn;  
	
	
		public String getFLoadPort() {
		return FLoadPort;
	}
	public void setFLoadPort(String fLoadPort) {
		FLoadPort = fLoadPort;
	}
		/**业务唯一键*/
	public void setPalletId(Long palletId){
		this.palletId=palletId;
	}
	/**业务唯一键*/
	public Long getPalletId(){
		return this.palletId;
	}
	
	/**UUID*/
	public void setUuid(String uuid){
		this.uuid=uuid;
	}
	/**UUID*/
	public String getUuid(){
		return this.uuid;
	}
	
	/**公司id*/
	public void setEpMemberId(Long epMemberId){
		this.epMemberId=epMemberId;
	}
	/**公司id*/
	public Long getEpMemberId(){
		return this.epMemberId;
	}
	
	/**会员公司名称*/
	public void setEpMemberName(String epMemberName){
		this.epMemberName=epMemberName;
	}
	/**会员公司名称*/
	public String getEpMemberName(){
		return this.epMemberName;
	}
	
	/**代理协议编号(船名+日期+委托方英文简称)*/
	public void setAgreementCode(String agreementCode){
		this.agreementCode=agreementCode;
	}
	/**代理协议编号(船名+日期+委托方英文简称)*/
	public String getAgreementCode(){
		return this.agreementCode;
	}
	
	/**单位名称*/
	public void setUnitName(String unitName){
		this.unitName=unitName;
	}
	/**单位名称*/
	public String getUnitName(){
		return this.unitName;
	}
	
	/**油种*/
	public void setOilName(String oilName){
		this.oilName=oilName;
	}
	/**油种*/
	public String getOilName(){
		return this.oilName;
	}
	
	/**租船编号*/
	public void setPalletCode(String palletCode){
		this.palletCode=palletCode;
	}
	/**租船编号*/
	public String getPalletCode(){
		return this.palletCode;
	}
	
	/**租船方式（1租船2拼船）*/
	public void setPalletType(String palletType){
		this.palletType=palletType;
	}
	/**租船方式（1租船2拼船）*/
	public String getPalletType(){
		return this.palletType;
	}
	
	/**是否有订单租船（1有0没有）*/
	public void setOrderNo(String orderNo){
		this.orderNo=orderNo;
	}
	/**是否有订单租船（1有0没有）*/
	public String getOrderNo(){
		return this.orderNo;
	}
	
	/**订单编号*/
	public void setOrderCode(String orderCode){
		this.orderCode=orderCode;
	}
	/**订单编号*/
	public String getOrderCode(){
		return this.orderCode;
	}
	
	/**油品合同号*/
	public void setOrderPact(String orderPact){
		this.orderPact=orderPact;
	}
	/**油品合同号*/
	public String getOrderPact(){
		return this.orderPact;
	}
	
	/**状态（1待处理2处理中3已报盘4已确认5已关闭）*/
	public void setStatus(String status){
		this.status=status;
	}
	/**状态（1待处理2处理中3已报盘4已确认5已关闭）*/
	public String getStatus(){
		return this.status;
	}
	
	/**数量（桶）*/
	public void setQuantity(java.math.BigDecimal quantity){
		this.quantity=quantity;
	}
	/**数量（桶）*/
	public java.math.BigDecimal getQuantity(){
		return this.quantity;
	}
	
	/**装期开始*/
	public void setLaycanBeg(java.util.Date laycanBeg){
		this.laycanBeg=laycanBeg;
	}
	/**装期开始*/
	public java.util.Date getLaycanBeg(){
		return this.laycanBeg;
	}
	
	/**装期结束*/
	public void setLaycanEnd(java.util.Date laycanEnd){
		this.laycanEnd=laycanEnd;
	}
	/**装期结束*/
	public java.util.Date getLaycanEnd(){
		return this.laycanEnd;
	}
	
	/**船型*/
	public void setShipType(String shipType){
		this.shipType=shipType;
	}
	/**船型*/
	public String getShipType(){
		return this.shipType;
	}
	
	/**船龄*/
	public void setShipAge(String shipAge){
		this.shipAge=shipAge;
	}
	/**船龄*/
	public String getShipAge(){
		return this.shipAge;
	}
	
	/**租船其它要求*/
	public void setRemark(String remark){
		this.remark=remark;
	}
	/**租船其它要求*/
	public String getRemark(){
		return this.remark;
	}
	
	/**指定承运商名称*/
	public void setTraderName(String traderName){
		this.traderName=traderName;
	}
	/**指定承运商名称*/
	public String getTraderName(){
		return this.traderName;
	}
	
	/**指定承运商id*/
	public void setTraderId(Long traderId){
		this.traderId=traderId;
	}
	/**指定承运商id*/
	public Long getTraderId(){
		return this.traderId;
	}
	
	/**公司id*/
	public void setCompanyId(Long companyId){
		this.companyId=companyId;
	}
	/**公司id*/
	public Long getCompanyId(){
		return this.companyId;
	}
	
	/**公司名称*/
	public void setCompanyName(String companyName){
		this.companyName=companyName;
	}
	/**公司名称*/
	public String getCompanyName(){
		return this.companyName;
	}
	
	/**电话*/
	public void setTel(String tel){
		this.tel=tel;
	}
	/**电话*/
	public String getTel(){
		return this.tel;
	}
	
	/**邮箱*/
	public void setEmail(String email){
		this.email=email;
	}
	/**邮箱*/
	public String getEmail(){
		return this.email;
	}
	
	/**联系人*/
	public void setContacts(String contacts){
		this.contacts=contacts;
	}
	/**联系人*/
	public String getContacts(){
		return this.contacts;
	}
	
	/**是否有效(1有效0无效)*/
	public void setAliveFlag(String aliveFlag){
		this.aliveFlag=aliveFlag;
	}
	/**是否有效(1有效0无效)*/
	public String getAliveFlag(){
		return this.aliveFlag;
	}
	
	/**版本号*/
	public void setVersion(String version){
		this.version=version;
	}
	/**版本号*/
	public String getVersion(){
		return this.version;
	}
	
	/**语言版本*/
	public void setLangVer(String langVer){
		this.langVer=langVer;
	}
	/**语言版本*/
	public String getLangVer(){
		return this.langVer;
	}
	
	/**创建时间*/
	public void setCreateDate(java.util.Date createDate){
		this.createDate=createDate;
	}
	/**创建时间*/
	public java.util.Date getCreateDate(){
		return this.createDate;
	}
	
	/**修改时间*/
	public void setUpdateDate(java.util.Date updateDate){
		this.updateDate=updateDate;
	}
	/**修改时间*/
	public java.util.Date getUpdateDate(){
		return this.updateDate;
	}
	
	/**创建人*/
	public void setCreateUser(Long createUser){
		this.createUser=createUser;
	}
	/**创建人*/
	public Long getCreateUser(){
		return this.createUser;
	}
	
	/**修改人*/
	public void setUpdateUser(Long updateUser){
		this.updateUser=updateUser;
	}
	/**修改人*/
	public Long getUpdateUser(){
		return this.updateUser;
	}
	
	/**扩展字段1*/
	public void setExt1(String ext1){
		this.ext1=ext1;
	}
	/**扩展字段1*/
	public String getExt1(){
		return this.ext1;
	}
	
	/**第一装港*/
	public void setLoadPort(String loadPort){
		this.loadPort=loadPort;
	}
	/**第一装港*/
	public String getLoadPort(){
		return this.loadPort;
	}
	
	/**船龄开始*/
	public void setShipAgeBeg(Integer shipAgeBeg){
		this.shipAgeBeg=shipAgeBeg;
	}
	/**船龄开始*/
	public Integer getShipAgeBeg(){
		return this.shipAgeBeg;
	}
	
	/**船龄结束*/
	public void setShipAgeEnd(Integer shipAgeEnd){
		this.shipAgeEnd=shipAgeEnd;
	}
	/**船龄结束*/
	public Integer getShipAgeEnd(){
		return this.shipAgeEnd;
	}
	
	/**是否先选择船盘（1是0否）*/
	public void setPlateSel(String plateSel){
		this.plateSel=plateSel;
	}
	/**是否先选择船盘（1是0否）*/
	public String getPlateSel(){
		return this.plateSel;
	}
	
	/**是否指定租船代理（1是0否）*/
	public void setTraderSel(String traderSel){
		this.traderSel=traderSel;
	}
	/**是否指定租船代理（1是0否）*/
	public String getTraderSel(){
		return this.traderSel;
	}
	
	/**船盘id*/
	public void setShipPlateId(Long shipPlateId){
		this.shipPlateId=shipPlateId;
	}
	/**船盘id*/
	public Long getShipPlateId(){
		return this.shipPlateId;
	}
	
	/**船盘uuid*/
	public void setShipPlateUuid(String shipPlateUuid){
		this.shipPlateUuid=shipPlateUuid;
	}
	/**船盘uuid*/
	public String getShipPlateUuid(){
		return this.shipPlateUuid;
	}
	
	/**是否生成协议（1是0否）*/
	public void setAgreementFlag(String agreementFlag){
		this.agreementFlag=agreementFlag;
	}
	/**是否生成协议（1是0否）*/
	public String getAgreementFlag(){
		return this.agreementFlag;
	}
	
	/**会员公司名称英文*/
	public void setEpMemberNameEn(String epMemberNameEn){
		this.epMemberNameEn=epMemberNameEn;
	}
	/**会员公司名称英文*/
	public String getEpMemberNameEn(){
		return this.epMemberNameEn;
	}
	
	/**指定承运商名称英文*/
	public void setTraderNameEn(String traderNameEn){
		this.traderNameEn=traderNameEn;
	}
	/**指定承运商名称英文*/
	public String getTraderNameEn(){
		return this.traderNameEn;
	}
	
	/**公司英文*/
	public void setCompanyNameEn(String companyNameEn){
		this.companyNameEn=companyNameEn;
	}
	/**公司英文*/
	public String getCompanyNameEn(){
		return this.companyNameEn;
	}
	
		
	
	/*public Map<String, Object> toMap() {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("palletId",this.palletId);
	map.put("uuid",this.uuid);
	map.put("epMemberId",this.epMemberId);
	map.put("epMemberName",this.epMemberName);
	map.put("agreementCode",this.agreementCode);
	map.put("unitName",this.unitName);
	map.put("oilName",this.oilName);
	map.put("palletCode",this.palletCode);
	map.put("palletType",this.palletType);
	map.put("orderNo",this.orderNo);
	map.put("orderCode",this.orderCode);
	map.put("orderPact",this.orderPact);
	map.put("status",this.status);
	map.put("quantity",this.quantity);
	map.put("laycanBeg",this.laycanBeg);
	map.put("laycanEnd",this.laycanEnd);
	map.put("shipType",this.shipType);
	map.put("shipAge",this.shipAge);
	map.put("remark",this.remark);
	map.put("traderName",this.traderName);
	map.put("traderId",this.traderId);
	map.put("companyId",this.companyId);
	map.put("companyName",this.companyName);
	map.put("tel",this.tel);
	map.put("email",this.email);
	map.put("contacts",this.contacts);
	map.put("aliveFlag",this.aliveFlag);
	map.put("version",this.version);
	map.put("langVer",this.langVer);
	map.put("createDate",this.createDate);
	map.put("updateDate",this.updateDate);
	map.put("createUser",this.createUser);
	map.put("updateUser",this.updateUser);
	map.put("ext1",this.ext1);
	map.put("loadPort",this.loadPort);
	map.put("shipAgeBeg",this.shipAgeBeg);
	map.put("shipAgeEnd",this.shipAgeEnd);
	map.put("plateSel",this.plateSel);
	map.put("traderSel",this.traderSel);
	map.put("shipPlateId",this.shipPlateId);
	map.put("shipPlateUuid",this.shipPlateUuid);
	map.put("agreementFlag",this.agreementFlag);
	map.put("epMemberNameEn",this.epMemberNameEn);
	map.put("traderNameEn",this.traderNameEn);
	map.put("companyNameEn",this.companyNameEn);
			return map;
	}*/
}