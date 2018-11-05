package com.sinochem.crude.trade.transport.model.result;

import java.io.Serializable;
import java.util.List;

import org.codehaus.jackson.annotate.JsonUnwrapped;

import com.sinochem.crude.trade.common.SimplePageInfo;


public class WaybillRes implements Serializable {

	private static final long serialVersionUID = 1L;	
	
	/**船盘uuid*/
	private String shipPlateUuid;

	/**货盘uuid*/
	private List<String> palletUuids;
	
	/**业务唯一键*/
	private Long waybillId;  
	
	/**UUID*/
	private String uuid;  
	
	/**运单号*/
	private String waybillCode;  
	
	/**主合同编号*/
	private String pactCode;  
	
	/**货盘ids*/
	private String palletIds;  
	
	/**船盘id*/
	private Long shipPlateId;  
	
	/**货物订单号*/
	private String orderCode;  
	
	/**状态（1待船东确认2已确认3执行中4已完成5已作废）*/
	private String status;  
	
	/**合同时间*/
	private java.util.Date pactTime;  
	
	/**承运人id*/
	private Long carrierId;  
	
	/**承运人名称*/
	private String carrierName;  
	
	/**船东名称*/
	private String shipOwnerName;  
	
	/**船东id*/
	private Long shipOwnerId;  
	
	/**经纪人id*/
	private Long brokerId;  
	
	/**经纪人名称*/
	private String brokerName;  
	
	/**船名*/
	private String shipName;  
	
	/**租约日*/
	private java.util.Date signDate;  
	
	/**受载期*/
	private String laycan;  
	
	/**合同规定航速*/
	private String pactSpeed;  
	
	/**装港时间*/
	private java.util.Date loadDate;  
	
	/**卸港时间*/
	private java.util.Date unloadDate;  
	
	/**运杂费收款方*/
	private String blendFeePer;  
	
	/**约定最低配载*/
	private java.math.BigDecimal minLoad;  
	
	/**WS点*/
	private String ws;  
	
	/**规定滞期费*/
	private java.math.BigDecimal demurrage;  
	
	/**备注*/
	private String remark;  
	
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
		/**业务唯一键*/
	public void setWaybillId(Long waybillId){
		this.waybillId=waybillId;
	}
	/**业务唯一键*/
	public Long getWaybillId(){
		return this.waybillId;
	}
	
	/**UUID*/
	public void setUuid(String uuid){
		this.uuid=uuid;
	}
	/**UUID*/
	public String getUuid(){
		return this.uuid;
	}
	
	/**运单号*/
	public void setWaybillCode(String waybillCode){
		this.waybillCode=waybillCode;
	}
	/**运单号*/
	public String getWaybillCode(){
		return this.waybillCode;
	}
	
	/**主合同编号*/
	public void setPactCode(String pactCode){
		this.pactCode=pactCode;
	}
	/**主合同编号*/
	public String getPactCode(){
		return this.pactCode;
	}
	
	/**货盘ids*/
	public void setPalletIds(String palletIds){
		this.palletIds=palletIds;
	}
	/**货盘ids*/
	public String getPalletIds(){
		return this.palletIds;
	}
	
	/**船盘id*/
	public void setShipPlateId(Long shipPlateId){
		this.shipPlateId=shipPlateId;
	}
	/**船盘id*/
	public Long getShipPlateId(){
		return this.shipPlateId;
	}
	
	/**货物订单号*/
	public void setOrderCode(String orderCode){
		this.orderCode=orderCode;
	}
	/**货物订单号*/
	public String getOrderCode(){
		return this.orderCode;
	}
	
	/**状态（1待船东确认2已确认3执行中4已完成5已作废）*/
	public void setStatus(String status){
		this.status=status;
	}
	/**状态（1待船东确认2已确认3执行中4已完成5已作废）*/
	public String getStatus(){
		return this.status;
	}
	
	/**合同时间*/
	public void setPactTime(java.util.Date pactTime){
		this.pactTime=pactTime;
	}
	/**合同时间*/
	public java.util.Date getPactTime(){
		return this.pactTime;
	}
	
	/**承运人id*/
	public void setCarrierId(Long carrierId){
		this.carrierId=carrierId;
	}
	/**承运人id*/
	public Long getCarrierId(){
		return this.carrierId;
	}
	
	/**承运人名称*/
	public void setCarrierName(String carrierName){
		this.carrierName=carrierName;
	}
	/**承运人名称*/
	public String getCarrierName(){
		return this.carrierName;
	}
	
	/**船东名称*/
	public void setShipOwnerName(String shipOwnerName){
		this.shipOwnerName=shipOwnerName;
	}
	/**船东名称*/
	public String getShipOwnerName(){
		return this.shipOwnerName;
	}
	
	/**船东id*/
	public void setShipOwnerId(Long shipOwnerId){
		this.shipOwnerId=shipOwnerId;
	}
	/**船东id*/
	public Long getShipOwnerId(){
		return this.shipOwnerId;
	}
	
	/**经纪人id*/
	public void setBrokerId(Long brokerId){
		this.brokerId=brokerId;
	}
	/**经纪人id*/
	public Long getBrokerId(){
		return this.brokerId;
	}
	
	/**经纪人名称*/
	public void setBrokerName(String brokerName){
		this.brokerName=brokerName;
	}
	/**经纪人名称*/
	public String getBrokerName(){
		return this.brokerName;
	}
	
	/**船名*/
	public void setShipName(String shipName){
		this.shipName=shipName;
	}
	/**船名*/
	public String getShipName(){
		return this.shipName;
	}
	
	/**租约日*/
	public void setSignDate(java.util.Date signDate){
		this.signDate=signDate;
	}
	/**租约日*/
	public java.util.Date getSignDate(){
		return this.signDate;
	}
	
	/**受载期*/
	public void setLaycan(String laycan){
		this.laycan=laycan;
	}
	/**受载期*/
	public String getLaycan(){
		return this.laycan;
	}
	
	/**合同规定航速*/
	public void setPactSpeed(String pactSpeed){
		this.pactSpeed=pactSpeed;
	}
	/**合同规定航速*/
	public String getPactSpeed(){
		return this.pactSpeed;
	}
	
	/**装港时间*/
	public void setLoadDate(java.util.Date loadDate){
		this.loadDate=loadDate;
	}
	/**装港时间*/
	public java.util.Date getLoadDate(){
		return this.loadDate;
	}
	
	/**卸港时间*/
	public void setUnloadDate(java.util.Date unloadDate){
		this.unloadDate=unloadDate;
	}
	/**卸港时间*/
	public java.util.Date getUnloadDate(){
		return this.unloadDate;
	}
	
	/**运杂费收款方*/
	public void setBlendFeePer(String blendFeePer){
		this.blendFeePer=blendFeePer;
	}
	/**运杂费收款方*/
	public String getBlendFeePer(){
		return this.blendFeePer;
	}
	
	/**约定最低配载*/
	public void setMinLoad(java.math.BigDecimal minLoad){
		this.minLoad=minLoad;
	}
	/**约定最低配载*/
	public java.math.BigDecimal getMinLoad(){
		return this.minLoad;
	}
	
	/**WS点*/
	public void setWs(String ws){
		this.ws=ws;
	}
	/**WS点*/
	public String getWs(){
		return this.ws;
	}
	
	/**规定滞期费*/
	public void setDemurrage(java.math.BigDecimal demurrage){
		this.demurrage=demurrage;
	}
	/**规定滞期费*/
	public java.math.BigDecimal getDemurrage(){
		return this.demurrage;
	}
	
	/**备注*/
	public void setRemark(String remark){
		this.remark=remark;
	}
	/**备注*/
	public String getRemark(){
		return this.remark;
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
	
	public List<String> getPalletUuids() {
		return palletUuids;
	}

	public void setPalletUuids(List<String> palletUuids) {
		this.palletUuids = palletUuids;
	}

	public String getShipPlateUuid() {
		return shipPlateUuid;
	}

	public void setShipPlateUuid(String shipPlateUuid) {
		this.shipPlateUuid = shipPlateUuid;
	}

	
	
	
}