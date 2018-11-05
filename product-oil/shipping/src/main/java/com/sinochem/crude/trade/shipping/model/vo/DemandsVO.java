package com.sinochem.crude.trade.shipping.model.vo;

import java.util.List;

import com.eyeieye.melody.util.StringUtil;
import com.sinochem.crude.trade.common.base.BaseDomainVO;
import com.sinochem.crude.trade.common.utils.DateUtil;
import com.sinochem.crude.trade.shipping.domain.Demands;

public class DemandsVO extends BaseDomainVO<Demands> {

	/**主键_ID*/
	private Long demandsId;  
	
	/**UUID*/
	private String uuid;  
	
	/**租船需求编码*/
	private String demandsCd;  
	
	/**订单Uuid*/
	private String orderUuid;  
	
	/**订单ID*/
	private Long orderId;  
	
	/**买家ID*/
	private Long buyerId;  
	
	/**卖家ID*/
	private Long sellerId;  
	
	/**订单编码*/
	private String orderNumber;  
	
	/**订单种类*/
	private String tradeTerms;  
	
	/**产品名称*/
	private String prodoctNm;  
	
	/**数量（吨）*/
	private String quantity;  
	
	/**误差范围*/
	private String rangeOfError;  
	
	/**装港*/
	private String portOfLoading;  
	
	/**卸港*/
	private String portOfDischarge;  
	
	/**受载期开始*/
	private String laycanStart;  
	
	/**受载期结束*/
	private String laycanEnd;  
	
	/**船龄*/
	private String builtDate;  
	
	/**备考*/
	private String other;
	
	/**租船代理ID*/
	private Long charteringAgentId;
	
	/**租船代理名*/
	private String charteringAgentNm;
	
	/**公司ID*/
	private Long corporationId;  
	
	/**公司名*/
	private String corporationNm;  
	
	/**联系人*/
	private String linkNm;  
	
	/**邮件*/
	private String eMial;  
	
	/**电话*/
	private String phoneNum;  
	
	/**需求单状态*/
	private String status;  
	
	/**需求单创建者所属企业ID*/
	private Long epMemberId;  
	
	/**预留字段1*/
	private String ext1;  
	
	/**版本号*/
	private String version;  
	
	/**当前有效状态*/
	private String aliveFlag;  
	
	/**创建时间*/
	private String createDate;  
	
	/**创建者*/
	private Long createUser;  
	
	/**更新时间*/
	private String updateDate;  
	
	/**更新者*/
	private Long updateUser;  
	/**更新者*/
	private List agentList;  
	
	private String createStart;
	
	private String createEnd;

	/** 产品来源 */
	private String productSourceCode;

	/**
	 * 泉炼表示Flag
	 */
	private String quanzhouFlag;

	public String getQuanzhouFlag() {
		return quanzhouFlag;
	}

	public void setQuanzhouFlag(String quanzhouFlag) {
		this.quanzhouFlag = quanzhouFlag;
	}

	public String getProductSourceCode() {
		return productSourceCode;
	}

	public void setProductSourceCode(String productSourceCode) {
		this.productSourceCode = productSourceCode;
	}

	public String geteMial() {
		return eMial;
	}
	public void seteMial(String eMial) {
		this.eMial = eMial;
	}
	public String getCreateStart() {
		return createStart;
	}
	public void setCreateStart(String createStart) {
		this.createStart = createStart;
	}
	public String getCreateEnd() {
		return createEnd;
	}
	public void setCreateEnd(String createEnd) {
		this.createEnd = createEnd;
	}
	/**other BuiltDate*/
	private String remark;  
	
	/**主键_ID*/
	public void setDemandsId(Long demandsId){
		this.demandsId=demandsId;
	}
	/**主键_ID*/
	public Long getDemandsId(){
		return this.demandsId;
	}
	
	/**UUID*/
	public void setUuid(String uuid){
		this.uuid=uuid;
	}
	/**UUID*/
	public String getUuid(){
		return this.uuid;
	}
	
	/**租船需求编码*/
	public void setDemandsCd(String demandsCd){
		this.demandsCd=demandsCd;
	}
	/**租船需求编码*/
	public String getDemandsCd(){
		return this.demandsCd;
	}
	
	/**订单ID*/
	public void setOrderId(Long orderId){
		this.orderId=orderId;
	}
	/**订单ID*/
	public Long getOrderId(){
		return this.orderId;
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
	
	/**产品名称*/
	public void setProdoctNm(String prodoctNm){
		this.prodoctNm=prodoctNm;
	}
	/**产品名称*/
	public String getProdoctNm(){
		return this.prodoctNm;
	}
	
	/**数量（吨）*/
	public void setQuantity(String quantity){
		this.quantity=quantity;
	}
	/**数量（吨）*/
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
	
	/**装港*/
	public void setPortOfLoading(String portOfLoading){
		this.portOfLoading=portOfLoading;
	}
	/**装港*/
	public String getPortOfLoading(){
		return this.portOfLoading;
	}
	
	/**卸港*/
	public void setPortOfDischarge(String portOfDischarge){
		this.portOfDischarge=portOfDischarge;
	}
	/**卸港*/
	public String getPortOfDischarge(){
		return this.portOfDischarge;
	}
	
	/**受载期开始*/
	public void setLaycanStart(String laycanStart){
		this.laycanStart=laycanStart;
	}
	/**受载期开始*/
	public String getLaycanStart(){
		return this.laycanStart;
	}
	
	/**受载期结束*/
	public void setLaycanEnd(String laycanEnd){
		this.laycanEnd=laycanEnd;
	}
	/**受载期结束*/
	public String getLaycanEnd(){
		return this.laycanEnd;
	}
	
	/**船龄*/
	public void setBuiltDate(String builtDate){
		this.builtDate=builtDate;
	}
	/**船龄*/
	public String getBuiltDate(){
		return this.builtDate;
	}
	
	/**备考*/
	public void setOther(String other){
		this.other=other;
	}
	/**备考*/
	public String getOther(){
		return this.other;
	}
	
	/**租船代理ID*/
	public void setCharteringAgentId(Long charteringAgentId){
		this.charteringAgentId=charteringAgentId;
	}
	/**租船代理ID*/
	public Long getCharteringAgentId(){
		return this.charteringAgentId;
	}
	
	/**租船代理名*/
	public void setCharteringAgentNm(String charteringAgentNm){
		this.charteringAgentNm=charteringAgentNm;
	}
	/**租船代理名*/
	public String getCharteringAgentNm(){
		return this.charteringAgentNm;
	}
	
	/**公司ID*/
	public void setCorporationId(Long corporationId){
		this.corporationId=corporationId;
	}
	/**公司ID*/
	public Long getCorporationId(){
		return this.corporationId;
	}
	
	/**公司名*/
	public void setCorporationNm(String corporationNm){
		this.corporationNm=corporationNm;
	}
	/**公司名*/
	public String getCorporationNm(){
		return this.corporationNm;
	}
	
	/**联系人*/
	public void setLinkNm(String linkNm){
		this.linkNm=linkNm;
	}
	/**联系人*/
	public String getLinkNm(){
		return this.linkNm;
	}
	
	/**邮件*/
	public void setEMial(String eMial){
		this.eMial=eMial;
	}
	/**邮件*/
	public String getEMial(){
		return this.eMial;
	}
	
	/**电话*/
	public void setPhoneNum(String phoneNum){
		this.phoneNum=phoneNum;
	}
	/**电话*/
	public String getPhoneNum(){
		return this.phoneNum;
	}
	
	/**需求单状态*/
	public void setStatus(String status){
		this.status=status;
	}
	/**需求单状态*/
	public String getStatus(){
		return this.status;
	}
	
	/**需求单创建者所属企业ID*/
	public void setEpMemberId(Long epMemberId){
		this.epMemberId=epMemberId;
	}
	/**需求单创建者所属企业ID*/
	public Long getEpMemberId(){
		return this.epMemberId;
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
	public void setCreateDate(String createDate){
		this.createDate=createDate;
	}
	/**创建时间*/
	public String getCreateDate(){
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
	public void setUpdateDate(String updateDate){
		this.updateDate=updateDate;
	}
	/**更新时间*/
	public String getUpdateDate(){
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
	
	/**
	 * @return the agentList
	 */
	public List getAgentList() {
		return agentList;
	}
	/**
	 * @param agentList the agentList to set
	 */
	public void setAgentList(List agentList) {
		this.agentList = agentList;
	}
	
	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getOrderUuid() {
		return orderUuid;
	}
	public void setOrderUuid(String orderUuid) {
		this.orderUuid = orderUuid;
	}
	
	@Override
	protected void convertToVO(Demands domain) {
		this.demandsId = domain.getDemandsId();
		this.uuid = domain.getUuid();
		this.demandsCd = domain.getDemandsCd();
		this.orderId = domain.getOrderId();
		this.buyerId = domain.getBuyerId();
		this.sellerId = domain.getSellerId();
		this.orderNumber = domain.getOrderNumber();
		this.prodoctNm = domain.getProdoctNm();
		this.quantity = domain.getQuantity();
		this.setRangeOfError(domain.getRangeOfError());
		this.portOfLoading = domain.getPortOfLoading();
		this.portOfDischarge = domain.getPortOfDischarge();
		this.laycanStart = DateUtil.formatDate(domain.getLaycanStart());
		this.laycanEnd = DateUtil.formatDate(domain.getLaycanEnd());
		this.builtDate = domain.getBuiltDate();
		this.other = domain.getOther();
		this.charteringAgentId = domain.getCharteringAgentId();
		this.charteringAgentNm = domain.getCharteringAgentNm();
		this.corporationId = domain.getCorporationId();
		this.corporationNm = domain.getCorporationNm();
		this.linkNm = domain.getLinkNm();
		this.eMial = domain.getEMial();
		this.phoneNum = domain.getPhoneNum();
		this.status = domain.getStatus();
		this.epMemberId = domain.getEpMemberId();
		this.ext1 = domain.getExt1();
		this.version = domain.getVersion();
		this.aliveFlag = domain.getAliveFlag();
		this.createDate = DateUtil.formatDate(domain.getCreateDate());
		this.createUser = domain.getCreateUser();
		this.updateDate = DateUtil.formatDate(domain.getUpdateDate());
		this.updateUser = domain.getUpdateUser();
		this.tradeTerms = domain.getTradeTerms();
		this.productSourceCode = domain.getProductSourceCode();
	}

	@Override
	protected Demands convertToDomain() {
		Demands demands = new Demands();
		demands.setDemandsId(this.demandsId);
		demands.setUuid(this.uuid);
		demands.setDemandsCd(this.demandsCd);
		demands.setOrderId(this.orderId);
		demands.setBuyerId(this.buyerId);
		demands.setSellerId(this.sellerId);
		demands.setOrderNumber(this.orderNumber);
		demands.setProdoctNm(this.prodoctNm);
		demands.setQuantity(this.quantity);
		demands.setRangeOfError(this.rangeOfError);
		demands.setPortOfLoading(this.portOfLoading);
		demands.setPortOfDischarge(this.portOfDischarge);
		if (StringUtil.isNotBlank(this.laycanStart)) {
			demands.setLaycanStart(DateUtil.getDate(this.laycanStart));
		}
		if (StringUtil.isNotBlank(this.laycanEnd)) {
			demands.setLaycanEnd(DateUtil.getDate(this.laycanEnd));
		}
		demands.setBuiltDate(this.builtDate);
		demands.setOther(this.other);
		demands.setCharteringAgentId(this.charteringAgentId);
		demands.setCharteringAgentNm(this.charteringAgentNm);
		demands.setCorporationId(this.corporationId);
		demands.setCorporationNm(this.corporationNm);
		demands.setLinkNm(this.linkNm);
		demands.setEMial(this.eMial);
		demands.setPhoneNum(this.phoneNum);
		demands.setStatus(this.status);
		demands.setEpMemberId(this.epMemberId);
		demands.setExt1(this.ext1);
		demands.setVersion(this.version);
		demands.setAliveFlag(this.aliveFlag);
		demands.setCreateUser(this.createUser);
		demands.setUpdateUser(this.updateUser);
		demands.setTradeTerms(this.tradeTerms);
		demands.setProductSourceCode(this.productSourceCode);
		return demands;
	}  
	
    /**
     * 使用构造函数获得VO
     */
    public DemandsVO(Demands domain) {
        super(domain);
    }
    /**
     * 使用构造函数获得VO
     */
    public DemandsVO() {
        super();
    }
	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}
}