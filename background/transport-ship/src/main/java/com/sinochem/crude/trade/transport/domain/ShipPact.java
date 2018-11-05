package com.sinochem.crude.trade.transport.domain;

import java.io.Serializable;

public class ShipPact implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**业务唯一键*/
	private Long shipPactId;  
	
	/**UUID*/
	private String uuid;  
	
	/**主合同编号*/
	private String pactCode;  
	
	/**公司id*/
	private Long epMemberId;  
	
	/**状态（1已生成协议2确认执行3航次开始4已装港5航行中6已卸港7航次结束8已结算）*/
	private String status;  
	
	/**装港*/
	private String loadPort;  
	
	/**详细状态（1航次未开始2航次开始3已装港4在途5已卸港）*/
	private String detailStatus;  
	
	/**船盘id*/
	private Long shipPlateId;  
	
	/**船盘uuid*/
	private String shipPlateUuid;  
	
	/**租约日*/
	private java.util.Date signDate;  
	
	/**租船人id*/
	private Long carrierId;  
	
	/**租船人名称*/
	private String carrierName;  
	
	/**船东*/
	private String shipOwner;  
	
	/**船名*/
	private String shipName;  
	
	/**受载期开始*/
	private java.util.Date pactBeg;  
	
	/**受载期结束*/
	private java.util.Date pactEnd;  
	
	/**受载期文本*/
	private String pactText;  
	
	/**最小货量*/
	private java.math.BigDecimal minQuantity;  
	
	/**WS点*/
	private java.math.BigDecimal ws;  
	
	/**WS点参考*/
	private String wsRes;  
	
	/**合同规定航速*/
	private String pactSpeed;  
	
	/**滞期费率*/
	private java.math.BigDecimal demurrage;  
	
	/**经纪人id*/
	private Long brokerId;  
	
	/**经纪人名称*/
	private String brokerName;  
	
	/**允许装卸时间（小时）*/
	private java.math.BigDecimal dockTime;  
	
	/**合同上传*/
	private String accessory;  
	
	/**合同路径*/
	private String accessoryPath;  
	
	/**船舶代码MMSI*/
	private String mmsi;  
	
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
	
	/**扩展字段1*/
	private String ext1;  
	
	/**租船意向ID*/
	private Long shipIntentionId;  
	
	/**租船意向UUID*/
	private String shipIntentionUuid;  
	
	/**装货区域*/
	private String loadingArea;  
	
	/**卸货区域*/
	private String unloadingArea;  
	
	/**船东id*/
	private Long shipOwnerId;  
	
	/**会员公司名称英文*/
	private String epMemberNameEn;  
	
	/**船东名称英文*/
	private String shipOwnerEn;  
	
	/**租船人名称英文*/
	private String carrierNameEn;  
	
	/**经纪人名称英文*/
	private String brokerNameEn; 
	
	/**船舶uuid*/
	private String sysShipUuid;  
	
	public String getSysShipUuid() {
		return sysShipUuid;
	}
	public void setSysShipUuid(String sysShipUuid) {
		this.sysShipUuid = sysShipUuid;
	}
		/**业务唯一键*/
	public void setShipPactId(Long shipPactId){
		this.shipPactId=shipPactId;
	}
	/**业务唯一键*/
	public Long getShipPactId(){
		return this.shipPactId;
	}
	
	/**UUID*/
	public void setUuid(String uuid){
		this.uuid=uuid;
	}
	/**UUID*/
	public String getUuid(){
		return this.uuid;
	}
	
	/**主合同编号*/
	public void setPactCode(String pactCode){
		this.pactCode=pactCode;
	}
	/**主合同编号*/
	public String getPactCode(){
		return this.pactCode;
	}
	
	/**公司id*/
	public void setEpMemberId(Long epMemberId){
		this.epMemberId=epMemberId;
	}
	/**公司id*/
	public Long getEpMemberId(){
		return this.epMemberId;
	}
	
	/**状态（1已生成协议2确认执行3航次开始4已装港5航行中6已卸港7航次结束8已结算）*/
	public void setStatus(String status){
		this.status=status;
	}
	/**状态（1已生成协议2确认执行3航次开始4已装港5航行中6已卸港7航次结束8已结算）*/
	public String getStatus(){
		return this.status;
	}
	
	/**装港*/
	public void setLoadPort(String loadPort){
		this.loadPort=loadPort;
	}
	/**装港*/
	public String getLoadPort(){
		return this.loadPort;
	}
	
	/**详细状态（1航次未开始2航次开始3已装港4在途5已卸港）*/
	public void setDetailStatus(String detailStatus){
		this.detailStatus=detailStatus;
	}
	/**详细状态（1航次未开始2航次开始3已装港4在途5已卸港）*/
	public String getDetailStatus(){
		return this.detailStatus;
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
	
	/**租约日*/
	public void setSignDate(java.util.Date signDate){
		this.signDate=signDate;
	}
	/**租约日*/
	public java.util.Date getSignDate(){
		return this.signDate;
	}
	
	/**租船人id*/
	public void setCarrierId(Long carrierId){
		this.carrierId=carrierId;
	}
	/**租船人id*/
	public Long getCarrierId(){
		return this.carrierId;
	}
	
	/**租船人名称*/
	public void setCarrierName(String carrierName){
		this.carrierName=carrierName;
	}
	/**租船人名称*/
	public String getCarrierName(){
		return this.carrierName;
	}
	
	/**船东*/
	public void setShipOwner(String shipOwner){
		this.shipOwner=shipOwner;
	}
	/**船东*/
	public String getShipOwner(){
		return this.shipOwner;
	}
	
	/**船名*/
	public void setShipName(String shipName){
		this.shipName=shipName;
	}
	/**船名*/
	public String getShipName(){
		return this.shipName;
	}
	
	/**受载期开始*/
	public void setPactBeg(java.util.Date pactBeg){
		this.pactBeg=pactBeg;
	}
	/**受载期开始*/
	public java.util.Date getPactBeg(){
		return this.pactBeg;
	}
	
	/**受载期结束*/
	public void setPactEnd(java.util.Date pactEnd){
		this.pactEnd=pactEnd;
	}
	/**受载期结束*/
	public java.util.Date getPactEnd(){
		return this.pactEnd;
	}
	
	/**受载期文本*/
	public void setPactText(String pactText){
		this.pactText=pactText;
	}
	/**受载期文本*/
	public String getPactText(){
		return this.pactText;
	}
	
	/**最小货量*/
	public void setMinQuantity(java.math.BigDecimal minQuantity){
		this.minQuantity=minQuantity;
	}
	/**最小货量*/
	public java.math.BigDecimal getMinQuantity(){
		return this.minQuantity;
	}
	
	/**WS点*/
	public void setWs(java.math.BigDecimal ws){
		this.ws=ws;
	}
	/**WS点*/
	public java.math.BigDecimal getWs(){
		return this.ws;
	}
	
	/**WS点参考*/
	public void setWsRes(String wsRes){
		this.wsRes=wsRes;
	}
	/**WS点参考*/
	public String getWsRes(){
		return this.wsRes;
	}
	
	/**合同规定航速*/
	public void setPactSpeed(String pactSpeed){
		this.pactSpeed=pactSpeed;
	}
	/**合同规定航速*/
	public String getPactSpeed(){
		return this.pactSpeed;
	}
	
	/**滞期费率*/
	public void setDemurrage(java.math.BigDecimal demurrage){
		this.demurrage=demurrage;
	}
	/**滞期费率*/
	public java.math.BigDecimal getDemurrage(){
		return this.demurrage;
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
	
	/**允许装卸时间（小时）*/
	public void setDockTime(java.math.BigDecimal dockTime){
		this.dockTime=dockTime;
	}
	/**允许装卸时间（小时）*/
	public java.math.BigDecimal getDockTime(){
		return this.dockTime;
	}
	
	/**合同上传*/
	public void setAccessory(String accessory){
		this.accessory=accessory;
	}
	/**合同上传*/
	public String getAccessory(){
		return this.accessory;
	}
	
	/**合同路径*/
	public void setAccessoryPath(String accessoryPath){
		this.accessoryPath=accessoryPath;
	}
	/**合同路径*/
	public String getAccessoryPath(){
		return this.accessoryPath;
	}
	
	/**船舶代码MMSI*/
	public void setMmsi(String mmsi){
		this.mmsi=mmsi;
	}
	/**船舶代码MMSI*/
	public String getMmsi(){
		return this.mmsi;
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
	
	/**扩展字段1*/
	public void setExt1(String ext1){
		this.ext1=ext1;
	}
	/**扩展字段1*/
	public String getExt1(){
		return this.ext1;
	}
	
	/**租船意向ID*/
	public void setShipIntentionId(Long shipIntentionId){
		this.shipIntentionId=shipIntentionId;
	}
	/**租船意向ID*/
	public Long getShipIntentionId(){
		return this.shipIntentionId;
	}
	
	/**租船意向UUID*/
	public void setShipIntentionUuid(String shipIntentionUuid){
		this.shipIntentionUuid=shipIntentionUuid;
	}
	/**租船意向UUID*/
	public String getShipIntentionUuid(){
		return this.shipIntentionUuid;
	}
	
	/**装货区域*/
	public void setLoadingArea(String loadingArea){
		this.loadingArea=loadingArea;
	}
	/**装货区域*/
	public String getLoadingArea(){
		return this.loadingArea;
	}
	
	/**卸货区域*/
	public void setUnloadingArea(String unloadingArea){
		this.unloadingArea=unloadingArea;
	}
	/**卸货区域*/
	public String getUnloadingArea(){
		return this.unloadingArea;
	}
	
	/**船东id*/
	public void setShipOwnerId(Long shipOwnerId){
		this.shipOwnerId=shipOwnerId;
	}
	/**船东id*/
	public Long getShipOwnerId(){
		return this.shipOwnerId;
	}
	
	/**会员公司名称英文*/
	public void setEpMemberNameEn(String epMemberNameEn){
		this.epMemberNameEn=epMemberNameEn;
	}
	/**会员公司名称英文*/
	public String getEpMemberNameEn(){
		return this.epMemberNameEn;
	}
	
	/**船东名称英文*/
	public void setShipOwnerEn(String shipOwnerEn){
		this.shipOwnerEn=shipOwnerEn;
	}
	/**船东名称英文*/
	public String getShipOwnerEn(){
		return this.shipOwnerEn;
	}
	
	/**租船人名称英文*/
	public void setCarrierNameEn(String carrierNameEn){
		this.carrierNameEn=carrierNameEn;
	}
	/**租船人名称英文*/
	public String getCarrierNameEn(){
		return this.carrierNameEn;
	}
	
	/**经纪人名称英文*/
	public void setBrokerNameEn(String brokerNameEn){
		this.brokerNameEn=brokerNameEn;
	}
	/**经纪人名称英文*/
	public String getBrokerNameEn(){
		return this.brokerNameEn;
	}
	
		
	
	/*public Map<String, Object> toMap() {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("shipPactId",this.shipPactId);
	map.put("uuid",this.uuid);
	map.put("pactCode",this.pactCode);
	map.put("epMemberId",this.epMemberId);
	map.put("status",this.status);
	map.put("loadPort",this.loadPort);
	map.put("detailStatus",this.detailStatus);
	map.put("shipPlateId",this.shipPlateId);
	map.put("shipPlateUuid",this.shipPlateUuid);
	map.put("signDate",this.signDate);
	map.put("carrierId",this.carrierId);
	map.put("carrierName",this.carrierName);
	map.put("shipOwner",this.shipOwner);
	map.put("shipName",this.shipName);
	map.put("pactBeg",this.pactBeg);
	map.put("pactEnd",this.pactEnd);
	map.put("pactText",this.pactText);
	map.put("minQuantity",this.minQuantity);
	map.put("ws",this.ws);
	map.put("wsRes",this.wsRes);
	map.put("pactSpeed",this.pactSpeed);
	map.put("demurrage",this.demurrage);
	map.put("brokerId",this.brokerId);
	map.put("brokerName",this.brokerName);
	map.put("dockTime",this.dockTime);
	map.put("accessory",this.accessory);
	map.put("accessoryPath",this.accessoryPath);
	map.put("mmsi",this.mmsi);
	map.put("remark",this.remark);
	map.put("aliveFlag",this.aliveFlag);
	map.put("version",this.version);
	map.put("langVer",this.langVer);
	map.put("createDate",this.createDate);
	map.put("updateDate",this.updateDate);
	map.put("createUser",this.createUser);
	map.put("updateUser",this.updateUser);
	map.put("ext1",this.ext1);
	map.put("shipIntentionId",this.shipIntentionId);
	map.put("shipIntentionUuid",this.shipIntentionUuid);
	map.put("loadingArea",this.loadingArea);
	map.put("unloadingArea",this.unloadingArea);
	map.put("shipOwnerId",this.shipOwnerId);
	map.put("epMemberNameEn",this.epMemberNameEn);
	map.put("shipOwnerEn",this.shipOwnerEn);
	map.put("carrierNameEn",this.carrierNameEn);
	map.put("brokerNameEn",this.brokerNameEn);
			return map;
	}*/
}