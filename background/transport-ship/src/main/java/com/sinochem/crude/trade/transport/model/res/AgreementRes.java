package com.sinochem.crude.trade.transport.model.res;

import java.io.Serializable;
import java.util.Map;

public class AgreementRes implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 业务唯一键 */
	private Long agreementId;

	/** UUID */
	private String uuid;

	/** 代理协议编号(船名+日期+委托方英文简称) */
	private String agreementCode;

	/** 公司id */
	private Long epMemberId;

	/** 订单编号 */
	private String orderCode;

	/** 是否有订单租船（1有0没有） */
	private String orderNo;

	/** 租约日 */
	private java.util.Date signDate;

	/** 货盘id */
	private Long palletId;

	/** 货盘uuid */
	private String palletUuid;

	/** 甲方 */
	private String firParty;

	/** 乙方 */
	private String secParty;

	/** 船名 */
	private String shipName;

	/** 船舶库uuid */
	private String sysShipUuid;

	/** 船舶库id */
	private Long sysShipId;

	/** 状态（1未匹配2已匹配3执行中4航次结束5已取消） */
	private String status;

	/** 总数量 */
	private java.math.BigDecimal allQuantity;

	/** 数量 */
	private String quantity;

	/** 油种 */
	private String oilType;

	/** 装港 */
	private String loadPort;

	/** 卸港 */
	private String unloadPort;

	/** 装期 */
	private String laycan;

	/** 协议上传 */
	private String accessory;

	/** 是否有效(1有效0无效) */
	private String aliveFlag;

	/** 版本号 */
	private String version;

	/** 语言版本 */
	private String langVer;

	/** 创建时间 */
	private java.util.Date createDate;

	/** 修改时间 */
	private java.util.Date updateDate;

	/** 创建人 */
	private Long createUser;

	/** 修改人 */
	private Long updateUser;

	/** 扩展字段1 */
	private String ext1;

	/** 协议路径 */
	private String accessoryPath;

	/** 装卸港 */
	private Map<String, Object> map;

	/** 租船人id */
	private Long carrierId;

	/** 租船人名称 */
	private String carrierName;

	/** 船东 */
	private String shipOwner;

	/** 受载期开始 */
	private java.util.Date pactBeg;

	/** 受载期结束 */
	private java.util.Date pactEnd;

	/** 受载期文本 */
	private String pactText;

	/** 最小货量 */
	private java.math.BigDecimal minQuantity;

	/** WS点 */
	private java.math.BigDecimal ws;

	/** WS点参考 */
	private String wsRes;

	/** 合同规定航速 */
	private String pactSpeed;

	/** 滞期费率 */
	private java.math.BigDecimal demurrage;

	/** 经纪人id */
	private Long brokerId;

	/** 经纪人名称 */
	private String brokerName;

	/** 允许装卸时间（小时） */
	private java.math.BigDecimal dockTime;

	/** 船合同id */
	private Long shipPactId;

	/** 船合同uuid */
	private String shipPactUuid;

	/** 备注 */
	private String remark;

	/** 会员公司名称 */
	private String epMemberName;

	/** 会员公司名称英文 */
	private String epMemberNameEn;

	/** 船东名称英文 */
	private String shipOwnerEn;

	/** 租船人名称英文 */
	private String carrierNameEn;

	/** 经纪人名称英文 */
	private String brokerNameEn;

	public String getShipOwnerEn() {
		return shipOwnerEn;
	}

	public void setShipOwnerEn(String shipOwnerEn) {
		this.shipOwnerEn = shipOwnerEn;
	}

	public String getCarrierNameEn() {
		return carrierNameEn;
	}

	public void setCarrierNameEn(String carrierNameEn) {
		this.carrierNameEn = carrierNameEn;
	}

	public String getBrokerNameEn() {
		return brokerNameEn;
	}

	public void setBrokerNameEn(String brokerNameEn) {
		this.brokerNameEn = brokerNameEn;
	}

	public String getEpMemberName() {
		return epMemberName;
	}

	public void setEpMemberName(String epMemberName) {
		this.epMemberName = epMemberName;
	}

	public String getEpMemberNameEn() {
		return epMemberNameEn;
	}

	public void setEpMemberNameEn(String epMemberNameEn) {
		this.epMemberNameEn = epMemberNameEn;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	/** 业务唯一键 */
	public void setAgreementId(Long agreementId) {
		this.agreementId = agreementId;
	}

	/** 业务唯一键 */
	public Long getAgreementId() {
		return this.agreementId;
	}

	/** UUID */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	/** UUID */
	public String getUuid() {
		return this.uuid;
	}

	/** 代理协议编号(船名+日期+委托方英文简称) */
	public void setAgreementCode(String agreementCode) {
		this.agreementCode = agreementCode;
	}

	/** 代理协议编号(船名+日期+委托方英文简称) */
	public String getAgreementCode() {
		return this.agreementCode;
	}

	/** 公司id */
	public void setEpMemberId(Long epMemberId) {
		this.epMemberId = epMemberId;
	}

	/** 公司id */
	public Long getEpMemberId() {
		return this.epMemberId;
	}

	/** 订单编号 */
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	/** 订单编号 */
	public String getOrderCode() {
		return this.orderCode;
	}

	/** 是否有订单租船（1有0没有） */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	/** 是否有订单租船（1有0没有） */
	public String getOrderNo() {
		return this.orderNo;
	}

	/** 租约日 */
	public void setSignDate(java.util.Date signDate) {
		this.signDate = signDate;
	}

	/** 租约日 */
	public java.util.Date getSignDate() {
		return this.signDate;
	}

	/** 货盘id */
	public void setPalletId(Long palletId) {
		this.palletId = palletId;
	}

	/** 货盘id */
	public Long getPalletId() {
		return this.palletId;
	}

	/** 货盘uuid */
	public void setPalletUuid(String palletUuid) {
		this.palletUuid = palletUuid;
	}

	/** 货盘uuid */
	public String getPalletUuid() {
		return this.palletUuid;
	}

	/** 甲方 */
	public void setFirParty(String firParty) {
		this.firParty = firParty;
	}

	/** 甲方 */
	public String getFirParty() {
		return this.firParty;
	}

	/** 乙方 */
	public void setSecParty(String secParty) {
		this.secParty = secParty;
	}

	/** 乙方 */
	public String getSecParty() {
		return this.secParty;
	}

	/** 船名 */
	public void setShipName(String shipName) {
		this.shipName = shipName;
	}

	/** 船名 */
	public String getShipName() {
		return this.shipName;
	}

	/** 船舶库uuid */
	public void setSysShipUuid(String sysShipUuid) {
		this.sysShipUuid = sysShipUuid;
	}

	/** 船舶库uuid */
	public String getSysShipUuid() {
		return this.sysShipUuid;
	}

	/** 船舶库id */
	public void setSysShipId(Long sysShipId) {
		this.sysShipId = sysShipId;
	}

	/** 船舶库id */
	public Long getSysShipId() {
		return this.sysShipId;
	}

	/** 状态（1未匹配2已匹配3执行中4航次结束5已取消） */
	public void setStatus(String status) {
		this.status = status;
	}

	/** 状态（1未匹配2已匹配3执行中4航次结束5已取消） */
	public String getStatus() {
		return this.status;
	}

	/** 总数量 */
	public void setAllQuantity(java.math.BigDecimal allQuantity) {
		this.allQuantity = allQuantity;
	}

	/** 总数量 */
	public java.math.BigDecimal getAllQuantity() {
		return this.allQuantity;
	}

	/** 数量 */
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	/** 数量 */
	public String getQuantity() {
		return this.quantity;
	}

	/** 油种 */
	public void setOilType(String oilType) {
		this.oilType = oilType;
	}

	/** 油种 */
	public String getOilType() {
		return this.oilType;
	}

	/** 装港 */
	public void setLoadPort(String loadPort) {
		this.loadPort = loadPort;
	}

	/** 装港 */
	public String getLoadPort() {
		return this.loadPort;
	}

	/** 卸港 */
	public void setUnloadPort(String unloadPort) {
		this.unloadPort = unloadPort;
	}

	/** 卸港 */
	public String getUnloadPort() {
		return this.unloadPort;
	}

	/** 装期 */
	public void setLaycan(String laycan) {
		this.laycan = laycan;
	}

	/** 装期 */
	public String getLaycan() {
		return this.laycan;
	}

	/** 协议上传 */
	public void setAccessory(String accessory) {
		this.accessory = accessory;
	}

	/** 协议上传 */
	public String getAccessory() {
		return this.accessory;
	}

	/** 是否有效(1有效0无效) */
	public void setAliveFlag(String aliveFlag) {
		this.aliveFlag = aliveFlag;
	}

	/** 是否有效(1有效0无效) */
	public String getAliveFlag() {
		return this.aliveFlag;
	}

	/** 版本号 */
	public void setVersion(String version) {
		this.version = version;
	}

	/** 版本号 */
	public String getVersion() {
		return this.version;
	}

	/** 语言版本 */
	public void setLangVer(String langVer) {
		this.langVer = langVer;
	}

	/** 语言版本 */
	public String getLangVer() {
		return this.langVer;
	}

	/** 创建时间 */
	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}

	/** 创建时间 */
	public java.util.Date getCreateDate() {
		return this.createDate;
	}

	/** 修改时间 */
	public void setUpdateDate(java.util.Date updateDate) {
		this.updateDate = updateDate;
	}

	/** 修改时间 */
	public java.util.Date getUpdateDate() {
		return this.updateDate;
	}

	/** 创建人 */
	public void setCreateUser(Long createUser) {
		this.createUser = createUser;
	}

	/** 创建人 */
	public Long getCreateUser() {
		return this.createUser;
	}

	/** 修改人 */
	public void setUpdateUser(Long updateUser) {
		this.updateUser = updateUser;
	}

	/** 修改人 */
	public Long getUpdateUser() {
		return this.updateUser;
	}

	/** 扩展字段1 */
	public void setExt1(String ext1) {
		this.ext1 = ext1;
	}

	/** 扩展字段1 */
	public String getExt1() {
		return this.ext1;
	}

	/** 协议路径 */
	public void setAccessoryPath(String accessoryPath) {
		this.accessoryPath = accessoryPath;
	}

	/** 协议路径 */
	public String getAccessoryPath() {
		return this.accessoryPath;
	}

	public Long getCarrierId() {
		return carrierId;
	}

	public void setCarrierId(Long carrierId) {
		this.carrierId = carrierId;
	}

	public String getCarrierName() {
		return carrierName;
	}

	public void setCarrierName(String carrierName) {
		this.carrierName = carrierName;
	}

	public String getShipOwner() {
		return shipOwner;
	}

	public void setShipOwner(String shipOwner) {
		this.shipOwner = shipOwner;
	}

	public java.util.Date getPactBeg() {
		return pactBeg;
	}

	public void setPactBeg(java.util.Date pactBeg) {
		this.pactBeg = pactBeg;
	}

	public java.util.Date getPactEnd() {
		return pactEnd;
	}

	public void setPactEnd(java.util.Date pactEnd) {
		this.pactEnd = pactEnd;
	}

	public String getPactText() {
		return pactText;
	}

	public void setPactText(String pactText) {
		this.pactText = pactText;
	}

	public java.math.BigDecimal getMinQuantity() {
		return minQuantity;
	}

	public void setMinQuantity(java.math.BigDecimal minQuantity) {
		this.minQuantity = minQuantity;
	}

	public java.math.BigDecimal getWs() {
		return ws;
	}

	public void setWs(java.math.BigDecimal ws) {
		this.ws = ws;
	}

	public String getWsRes() {
		return wsRes;
	}

	public void setWsRes(String wsRes) {
		this.wsRes = wsRes;
	}

	public String getPactSpeed() {
		return pactSpeed;
	}

	public void setPactSpeed(String pactSpeed) {
		this.pactSpeed = pactSpeed;
	}

	public java.math.BigDecimal getDemurrage() {
		return demurrage;
	}

	public void setDemurrage(java.math.BigDecimal demurrage) {
		this.demurrage = demurrage;
	}

	public Long getBrokerId() {
		return brokerId;
	}

	public void setBrokerId(Long brokerId) {
		this.brokerId = brokerId;
	}

	public String getBrokerName() {
		return brokerName;
	}

	public void setBrokerName(String brokerName) {
		this.brokerName = brokerName;
	}

	public java.math.BigDecimal getDockTime() {
		return dockTime;
	}

	public void setDockTime(java.math.BigDecimal dockTime) {
		this.dockTime = dockTime;
	}

	public Long getShipPactId() {
		return shipPactId;
	}

	public void setShipPactId(Long shipPactId) {
		this.shipPactId = shipPactId;
	}

	public String getShipPactUuid() {
		return shipPactUuid;
	}

	public void setShipPactUuid(String shipPactUuid) {
		this.shipPactUuid = shipPactUuid;
	}

	/*
	 * public Map<String, Object> toMap() { Map<String,Object> map = new
	 * HashMap<String,Object>(); map.put("agreementId",this.agreementId);
	 * map.put("uuid",this.uuid); map.put("agreementCode",this.agreementCode);
	 * map.put("epMemberId",this.epMemberId);
	 * map.put("orderCode",this.orderCode); map.put("orderNo",this.orderNo);
	 * map.put("signDate",this.signDate); map.put("palletId",this.palletId);
	 * map.put("palletUuid",this.palletUuid); map.put("firParty",this.firParty);
	 * map.put("secParty",this.secParty); map.put("shipName",this.shipName);
	 * map.put("sysShipUuid",this.sysShipUuid);
	 * map.put("sysShipId",this.sysShipId); map.put("status",this.status);
	 * map.put("allQuantity",this.allQuantity);
	 * map.put("quantity",this.quantity); map.put("oilType",this.oilType);
	 * map.put("loadPort",this.loadPort); map.put("unloadPort",this.unloadPort);
	 * map.put("laycan",this.laycan); map.put("accessory",this.accessory);
	 * map.put("aliveFlag",this.aliveFlag); map.put("version",this.version);
	 * map.put("langVer",this.langVer); map.put("createDate",this.createDate);
	 * map.put("updateDate",this.updateDate);
	 * map.put("createUser",this.createUser);
	 * map.put("updateUser",this.updateUser); map.put("ext1",this.ext1);
	 * map.put("accessoryPath",this.accessoryPath); return map; }
	 */
}