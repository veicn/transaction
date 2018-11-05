package com.sinochem.crude.trade.transport.domain;

import java.io.Serializable;

public class LoadPort implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 业务唯一键 */
	private Long loadPortId;

	/** UUID */
	private String uuid;

	/** 船盘uuid */
	private String shipPlateUuid;

	/** 船盘id */
	private Long shipPlateId;

	/** 船合同uuid */
	private String shipPactUuid;

	/** 船合同id */
	private Long shipPactId;

	/** 代理协议uuid */
	private String agreementUuid;

	/** 代理协议编号 */
	private String agreementCode;

	/** 公司id */
	private Long epMemberId;

	/** 装港 */
	private String loadPort;

	/** 油种 */
	private String oilType;

	/** ETA */
	private java.util.Date eta;

	/** 预计靠泊时间 */
	private java.util.Date exBerth;

	/** NOR递交时间 */
	private java.util.Date norDate;

	/** 引水上船时间 */
	private java.util.Date waterDate;

	/** 起锚时间 */
	private java.util.Date atripDate;

	/** 靠泊完成时间 */
	private java.util.Date berthDate;

	/** 实际装货开始时间 */
	private java.util.Date acStart;

	/** 实际装货完成时间 */
	private java.util.Date acFinish;

	/** 预计离港时间 */
	private java.util.Date exLeave;

	/** 实际离港时间 */
	private java.util.Date acLeave;

	/** 拆管时间 */
	private java.util.Date remTubeDate;

	/** 装港商检 */
	private String inspection;

	/** 装港船代 */
	private String agency;

	/** 附件一 */
	private String accessory1;

	/** 附件二 */
	private String accessory2;

	/** 附件三 */
	private String accessory3;

	/** 附件一路径 */
	private String accessory1Path;

	/** 附件二路径 */
	private String accessory2Path;

	/** 附件三路径 */
	private String accessory3Path;

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

	/** 装港英文 */
	private String loadPortEn;

	/** 装港code */
	private String loadPortCode;

	/** 油种英文 */
	private String oilTypeEn;

	/** 油种名称code */
	private String oilTypeCode;

	public String getLoadPortEn() {
		return loadPortEn;
	}

	public void setLoadPortEn(String loadPortEn) {
		this.loadPortEn = loadPortEn;
	}

	public String getLoadPortCode() {
		return loadPortCode;
	}

	public void setLoadPortCode(String loadPortCode) {
		this.loadPortCode = loadPortCode;
	}

	public String getOilTypeEn() {
		return oilTypeEn;
	}

	public void setOilTypeEn(String oilTypeEn) {
		this.oilTypeEn = oilTypeEn;
	}

	public String getOilTypeCode() {
		return oilTypeCode;
	}

	public void setOilTypeCode(String oilTypeCode) {
		this.oilTypeCode = oilTypeCode;
	}

	/** 业务唯一键 */
	public void setLoadPortId(Long loadPortId) {
		this.loadPortId = loadPortId;
	}

	/** 业务唯一键 */
	public Long getLoadPortId() {
		return this.loadPortId;
	}

	/** UUID */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	/** UUID */
	public String getUuid() {
		return this.uuid;
	}

	/** 船盘uuid */
	public void setShipPlateUuid(String shipPlateUuid) {
		this.shipPlateUuid = shipPlateUuid;
	}

	/** 船盘uuid */
	public String getShipPlateUuid() {
		return this.shipPlateUuid;
	}

	/** 船盘id */
	public void setShipPlateId(Long shipPlateId) {
		this.shipPlateId = shipPlateId;
	}

	/** 船盘id */
	public Long getShipPlateId() {
		return this.shipPlateId;
	}

	/** 船合同uuid */
	public void setShipPactUuid(String shipPactUuid) {
		this.shipPactUuid = shipPactUuid;
	}

	/** 船合同uuid */
	public String getShipPactUuid() {
		return this.shipPactUuid;
	}

	/** 船合同id */
	public void setShipPactId(Long shipPactId) {
		this.shipPactId = shipPactId;
	}

	/** 船合同id */
	public Long getShipPactId() {
		return this.shipPactId;
	}

	/** 代理协议uuid */
	public void setAgreementUuid(String agreementUuid) {
		this.agreementUuid = agreementUuid;
	}

	/** 代理协议uuid */
	public String getAgreementUuid() {
		return this.agreementUuid;
	}

	/** 代理协议编号 */
	public void setAgreementCode(String agreementCode) {
		this.agreementCode = agreementCode;
	}

	/** 代理协议编号 */
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

	/** 装港 */
	public void setLoadPort(String loadPort) {
		this.loadPort = loadPort;
	}

	/** 装港 */
	public String getLoadPort() {
		return this.loadPort;
	}

	/** 油种 */
	public void setOilType(String oilType) {
		this.oilType = oilType;
	}

	/** 油种 */
	public String getOilType() {
		return this.oilType;
	}

	/** ETA */
	public void setEta(java.util.Date eta) {
		this.eta = eta;
	}

	/** ETA */
	public java.util.Date getEta() {
		return this.eta;
	}

	/** 预计靠泊时间 */
	public void setExBerth(java.util.Date exBerth) {
		this.exBerth = exBerth;
	}

	/** 预计靠泊时间 */
	public java.util.Date getExBerth() {
		return this.exBerth;
	}

	/** NOR递交时间 */
	public void setNorDate(java.util.Date norDate) {
		this.norDate = norDate;
	}

	/** NOR递交时间 */
	public java.util.Date getNorDate() {
		return this.norDate;
	}

	/** 引水上船时间 */
	public void setWaterDate(java.util.Date waterDate) {
		this.waterDate = waterDate;
	}

	/** 引水上船时间 */
	public java.util.Date getWaterDate() {
		return this.waterDate;
	}

	/** 起锚时间 */
	public void setAtripDate(java.util.Date atripDate) {
		this.atripDate = atripDate;
	}

	/** 起锚时间 */
	public java.util.Date getAtripDate() {
		return this.atripDate;
	}

	/** 靠泊完成时间 */
	public void setBerthDate(java.util.Date berthDate) {
		this.berthDate = berthDate;
	}

	/** 靠泊完成时间 */
	public java.util.Date getBerthDate() {
		return this.berthDate;
	}

	/** 实际装货开始时间 */
	public void setAcStart(java.util.Date acStart) {
		this.acStart = acStart;
	}

	/** 实际装货开始时间 */
	public java.util.Date getAcStart() {
		return this.acStart;
	}

	/** 实际装货完成时间 */
	public void setAcFinish(java.util.Date acFinish) {
		this.acFinish = acFinish;
	}

	/** 实际装货完成时间 */
	public java.util.Date getAcFinish() {
		return this.acFinish;
	}

	/** 预计离港时间 */
	public void setExLeave(java.util.Date exLeave) {
		this.exLeave = exLeave;
	}

	/** 预计离港时间 */
	public java.util.Date getExLeave() {
		return this.exLeave;
	}

	/** 实际离港时间 */
	public void setAcLeave(java.util.Date acLeave) {
		this.acLeave = acLeave;
	}

	/** 实际离港时间 */
	public java.util.Date getAcLeave() {
		return this.acLeave;
	}

	/** 拆管时间 */
	public void setRemTubeDate(java.util.Date remTubeDate) {
		this.remTubeDate = remTubeDate;
	}

	/** 拆管时间 */
	public java.util.Date getRemTubeDate() {
		return this.remTubeDate;
	}

	/** 装港商检 */
	public void setInspection(String inspection) {
		this.inspection = inspection;
	}

	/** 装港商检 */
	public String getInspection() {
		return this.inspection;
	}

	/** 装港船代 */
	public void setAgency(String agency) {
		this.agency = agency;
	}

	/** 装港船代 */
	public String getAgency() {
		return this.agency;
	}

	/** 附件一 */
	public void setAccessory1(String accessory1) {
		this.accessory1 = accessory1;
	}

	/** 附件一 */
	public String getAccessory1() {
		return this.accessory1;
	}

	/** 附件二 */
	public void setAccessory2(String accessory2) {
		this.accessory2 = accessory2;
	}

	/** 附件二 */
	public String getAccessory2() {
		return this.accessory2;
	}

	/** 附件三 */
	public void setAccessory3(String accessory3) {
		this.accessory3 = accessory3;
	}

	/** 附件三 */
	public String getAccessory3() {
		return this.accessory3;
	}

	/** 附件一路径 */
	public void setAccessory1Path(String accessory1Path) {
		this.accessory1Path = accessory1Path;
	}

	/** 附件一路径 */
	public String getAccessory1Path() {
		return this.accessory1Path;
	}

	/** 附件二路径 */
	public void setAccessory2Path(String accessory2Path) {
		this.accessory2Path = accessory2Path;
	}

	/** 附件二路径 */
	public String getAccessory2Path() {
		return this.accessory2Path;
	}

	/** 附件三路径 */
	public void setAccessory3Path(String accessory3Path) {
		this.accessory3Path = accessory3Path;
	}

	/** 附件三路径 */
	public String getAccessory3Path() {
		return this.accessory3Path;
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

	/*
	 * public Map<String, Object> toMap() { Map<String,Object> map = new
	 * HashMap<String,Object>(); map.put("loadPortId",this.loadPortId);
	 * map.put("uuid",this.uuid); map.put("shipPlateUuid",this.shipPlateUuid);
	 * map.put("shipPlateId",this.shipPlateId);
	 * map.put("shipPactUuid",this.shipPactUuid);
	 * map.put("shipPactId",this.shipPactId);
	 * map.put("agreementUuid",this.agreementUuid);
	 * map.put("agreementCode",this.agreementCode);
	 * map.put("epMemberId",this.epMemberId); map.put("loadPort",this.loadPort);
	 * map.put("oilType",this.oilType); map.put("eta",this.eta);
	 * map.put("exBerth",this.exBerth); map.put("norDate",this.norDate);
	 * map.put("waterDate",this.waterDate); map.put("atripDate",this.atripDate);
	 * map.put("berthDate",this.berthDate); map.put("acStart",this.acStart);
	 * map.put("acFinish",this.acFinish); map.put("exLeave",this.exLeave);
	 * map.put("acLeave",this.acLeave); map.put("remTubeDate",this.remTubeDate);
	 * map.put("inspection",this.inspection); map.put("agency",this.agency);
	 * map.put("accessory1",this.accessory1);
	 * map.put("accessory2",this.accessory2);
	 * map.put("accessory3",this.accessory3);
	 * map.put("accessory1Path",this.accessory1Path);
	 * map.put("accessory2Path",this.accessory2Path);
	 * map.put("accessory3Path",this.accessory3Path);
	 * map.put("aliveFlag",this.aliveFlag); map.put("version",this.version);
	 * map.put("langVer",this.langVer); map.put("createDate",this.createDate);
	 * map.put("updateDate",this.updateDate);
	 * map.put("createUser",this.createUser);
	 * map.put("updateUser",this.updateUser); map.put("ext1",this.ext1); return
	 * map; }
	 */
}