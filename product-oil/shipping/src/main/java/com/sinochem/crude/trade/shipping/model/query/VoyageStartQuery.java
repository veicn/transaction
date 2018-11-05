package com.sinochem.crude.trade.shipping.model.query;

public class VoyageStartQuery {

	/** UUID */
	private String uuid;

	/** 船舶确认单ID */
	private Long shipConfirmationSheetId;

	/** 配载计划 */
	private String productNm;

	/** 公吨 */
	private String metricTons;

	/** 误差范围 */
	private String rangeOfError;

	/** 桶 */
	private String bbls;

	/** 计划装货温度 （℉） */
	private String loadingTemp;

	/** 装货港吃水限制 */
	private String draftRestrictionOfLoadingPort;

	/** 卸货港吃水限制 */
	private String draftRestrictionOfDischargingPort;

	/** 文件名称 */
	private String accessoryFileNm;

	/** 附件地址 */
	private String accessory;

	/** 预留字段1 */
	private String ext1;

	/** 版本号 */
	private String version;

	/** 当前有效状态 */
	private String aliveFlag;

	/** 创建时间 */
	private java.util.Date createDate;

	/** 创建者 */
	private Long createUser;

	/** 更新时间 */
	private java.util.Date updateDate;

	/** 更新者 */
	private Long updateUser;

	public String getRangeOfError() {
		return rangeOfError;
	}

	public void setRangeOfError(String rangeOfError) {
		this.rangeOfError = rangeOfError;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Long getShipConfirmationSheetId() {
		return shipConfirmationSheetId;
	}

	public void setShipConfirmationSheetId(Long shipConfirmationSheetId) {
		this.shipConfirmationSheetId = shipConfirmationSheetId;
	}

	public String getProductNm() {
		return productNm;
	}

	public void setProductNm(String productNm) {
		this.productNm = productNm;
	}

	public String getMetricTons() {
		return metricTons;
	}

	public void setMetricTons(String metricTons) {
		this.metricTons = metricTons;
	}

	public String getBbls() {
		return bbls;
	}

	public void setBbls(String bbls) {
		this.bbls = bbls;
	}

	public String getLoadingTemp() {
		return loadingTemp;
	}

	public void setLoadingTemp(String loadingTemp) {
		this.loadingTemp = loadingTemp;
	}

	public String getDraftRestrictionOfLoadingPort() {
		return draftRestrictionOfLoadingPort;
	}

	public void setDraftRestrictionOfLoadingPort(String draftRestrictionOfLoadingPort) {
		this.draftRestrictionOfLoadingPort = draftRestrictionOfLoadingPort;
	}

	public String getDraftRestrictionOfDischargingPort() {
		return draftRestrictionOfDischargingPort;
	}

	public void setDraftRestrictionOfDischargingPort(String draftRestrictionOfDischargingPort) {
		this.draftRestrictionOfDischargingPort = draftRestrictionOfDischargingPort;
	}

	public String getAccessoryFileNm() {
		return accessoryFileNm;
	}

	public void setAccessoryFileNm(String accessoryFileNm) {
		this.accessoryFileNm = accessoryFileNm;
	}

	public String getAccessory() {
		return accessory;
	}

	public void setAccessory(String accessory) {
		this.accessory = accessory;
	}

	public String getExt1() {
		return ext1;
	}

	public void setExt1(String ext1) {
		this.ext1 = ext1;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getAliveFlag() {
		return aliveFlag;
	}

	public void setAliveFlag(String aliveFlag) {
		this.aliveFlag = aliveFlag;
	}

	public java.util.Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}

	public Long getCreateUser() {
		return createUser;
	}

	public void setCreateUser(Long createUser) {
		this.createUser = createUser;
	}

	public java.util.Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(java.util.Date updateDate) {
		this.updateDate = updateDate;
	}

	public Long getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(Long updateUser) {
		this.updateUser = updateUser;
	}

}
