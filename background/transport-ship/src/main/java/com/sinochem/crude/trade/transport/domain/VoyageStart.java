package com.sinochem.crude.trade.transport.domain;

import java.io.Serializable;

public class VoyageStart implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 业务唯一键 */
	private Long voyageStartId;

	/** UUID */
	private String uuid;

	/** 船盘id */
	private Long shipPlateId;

	/** 船盘uuid */
	private String shipPlateUuid;

	/** 船合同uuid */
	private String shipPactUuid;

	/** 船合同id */
	private Long shipPactId;

	/** 公司id */
	private Long epMemberId;

	/** 配载计划装量（桶） */
	private java.math.BigDecimal quantityCask;

	/** 配载计划装量（吨） */
	private java.math.BigDecimal quantity;

	/** 配载计划油种 */
	private String oilType;

	/** 配载计划API */
	private java.math.BigDecimal api;

	/** 配载计划装货温度 */
	private java.math.BigDecimal loadTemp;

	/** 配载计划装港吃水 */
	private java.math.BigDecimal loadDraft;

	/** 配载计划卸港吃水 */
	private java.math.BigDecimal unloadDraft;

	/** 附件 */
	private String accessory;

	/** 附件路径 */
	private String accessoryPath;

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

	/** 油种英文 */
	private String oilTypeEn;

	/** 油种名称code */
	private String oilTypeCode;

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
	public void setVoyageStartId(Long voyageStartId) {
		this.voyageStartId = voyageStartId;
	}

	/** 业务唯一键 */
	public Long getVoyageStartId() {
		return this.voyageStartId;
	}

	/** UUID */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	/** UUID */
	public String getUuid() {
		return this.uuid;
	}

	/** 船盘id */
	public void setShipPlateId(Long shipPlateId) {
		this.shipPlateId = shipPlateId;
	}

	/** 船盘id */
	public Long getShipPlateId() {
		return this.shipPlateId;
	}

	/** 船盘uuid */
	public void setShipPlateUuid(String shipPlateUuid) {
		this.shipPlateUuid = shipPlateUuid;
	}

	/** 船盘uuid */
	public String getShipPlateUuid() {
		return this.shipPlateUuid;
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

	/** 公司id */
	public void setEpMemberId(Long epMemberId) {
		this.epMemberId = epMemberId;
	}

	/** 公司id */
	public Long getEpMemberId() {
		return this.epMemberId;
	}

	/** 配载计划装量（桶） */
	public void setQuantityCask(java.math.BigDecimal quantityCask) {
		this.quantityCask = quantityCask;
	}

	/** 配载计划装量（桶） */
	public java.math.BigDecimal getQuantityCask() {
		return this.quantityCask;
	}

	/** 配载计划装量（吨） */
	public void setQuantity(java.math.BigDecimal quantity) {
		this.quantity = quantity;
	}

	/** 配载计划装量（吨） */
	public java.math.BigDecimal getQuantity() {
		return this.quantity;
	}

	/** 配载计划油种 */
	public void setOilType(String oilType) {
		this.oilType = oilType;
	}

	/** 配载计划油种 */
	public String getOilType() {
		return this.oilType;
	}

	/** 配载计划API */
	public void setApi(java.math.BigDecimal api) {
		this.api = api;
	}

	/** 配载计划API */
	public java.math.BigDecimal getApi() {
		return this.api;
	}

	/** 配载计划装货温度 */
	public void setLoadTemp(java.math.BigDecimal loadTemp) {
		this.loadTemp = loadTemp;
	}

	/** 配载计划装货温度 */
	public java.math.BigDecimal getLoadTemp() {
		return this.loadTemp;
	}

	/** 配载计划装港吃水 */
	public void setLoadDraft(java.math.BigDecimal loadDraft) {
		this.loadDraft = loadDraft;
	}

	/** 配载计划装港吃水 */
	public java.math.BigDecimal getLoadDraft() {
		return this.loadDraft;
	}

	/** 配载计划卸港吃水 */
	public void setUnloadDraft(java.math.BigDecimal unloadDraft) {
		this.unloadDraft = unloadDraft;
	}

	/** 配载计划卸港吃水 */
	public java.math.BigDecimal getUnloadDraft() {
		return this.unloadDraft;
	}

	/** 附件 */
	public void setAccessory(String accessory) {
		this.accessory = accessory;
	}

	/** 附件 */
	public String getAccessory() {
		return this.accessory;
	}

	/** 附件路径 */
	public void setAccessoryPath(String accessoryPath) {
		this.accessoryPath = accessoryPath;
	}

	/** 附件路径 */
	public String getAccessoryPath() {
		return this.accessoryPath;
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
	 * HashMap<String,Object>(); map.put("voyageStartId",this.voyageStartId);
	 * map.put("uuid",this.uuid); map.put("shipPlateId",this.shipPlateId);
	 * map.put("shipPlateUuid",this.shipPlateUuid);
	 * map.put("shipPactUuid",this.shipPactUuid);
	 * map.put("shipPactId",this.shipPactId);
	 * map.put("epMemberId",this.epMemberId);
	 * map.put("quantityCask",this.quantityCask);
	 * map.put("quantity",this.quantity); map.put("oilType",this.oilType);
	 * map.put("api",this.api); map.put("loadTemp",this.loadTemp);
	 * map.put("loadDraft",this.loadDraft);
	 * map.put("unloadDraft",this.unloadDraft);
	 * map.put("accessory",this.accessory);
	 * map.put("accessoryPath",this.accessoryPath);
	 * map.put("aliveFlag",this.aliveFlag); map.put("version",this.version);
	 * map.put("langVer",this.langVer); map.put("createDate",this.createDate);
	 * map.put("updateDate",this.updateDate);
	 * map.put("createUser",this.createUser);
	 * map.put("updateUser",this.updateUser); map.put("ext1",this.ext1); return
	 * map; }
	 */
}