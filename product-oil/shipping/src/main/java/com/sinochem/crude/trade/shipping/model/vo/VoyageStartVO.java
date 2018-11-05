package com.sinochem.crude.trade.shipping.model.vo;

import com.eyeieye.melody.util.StringUtil;
import com.sinochem.crude.trade.common.base.BaseDomainVO;
import com.sinochem.crude.trade.shipping.domain.VoyageStart;

public class VoyageStartVO extends BaseDomainVO<VoyageStart> {

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

	private String di;

	private String diFileNm;

	public String getDiFileNm() {
		return diFileNm;
	}

	public void setDiFileNm(String diFileNm) {
		this.diFileNm = diFileNm;
	}

	public String getDi() {
		return di;
	}

	public void setDi(String di) {
		this.di = di;
	}


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

	@Override
	protected void convertToVO(VoyageStart voyageStart) {

		if (voyageStart.getUuid() != null) {
			this.uuid = voyageStart.getUuid();
		}
		if (voyageStart.getAccessory() != null) {
			this.accessory = voyageStart.getAccessory();
		}
		if (voyageStart.getAccessoryFileNm() != null) {
			this.accessoryFileNm = voyageStart.getAccessoryFileNm();
		}
		if (voyageStart.getDi() != null) {
			this.di = voyageStart.getDi();
		}
		if (voyageStart.getDiFileNm() != null) {
			this.diFileNm = voyageStart.getDiFileNm();
		}

		if (voyageStart.getBbls() != null) {
			this.bbls = voyageStart.getBbls();
		}

	}

	/**
	 * 领域类domain转化为VO
	 * 
	 * @param voyageStart
	 * @return
	 */

	public VoyageStartVO domainToVO(VoyageStart voyageStart) {
		VoyageStartVO voyageStartVO = new VoyageStartVO();

		if (StringUtil.isNotBlank(voyageStart.getAccessory())) {
			voyageStartVO.setAccessory(voyageStart.getAccessory());
		}

		if (StringUtil.isNotBlank(voyageStart.getAccessoryFileNm())) {
			voyageStartVO.setAccessoryFileNm(voyageStart.getAccessoryFileNm());
		}

		if (StringUtil.isNotBlank(voyageStart.getBbls())) {
			voyageStartVO.setBbls(voyageStart.getBbls());
		}

		if (StringUtil.isNotBlank(voyageStart.getDraftRestrictionOfDischargingPort())) {
			voyageStartVO.setDraftRestrictionOfDischargingPort(voyageStart.getDraftRestrictionOfDischargingPort());
		}

		if (StringUtil.isNotBlank(voyageStart.getDraftRestrictionOfLoadingPort())) {
			voyageStartVO.setDraftRestrictionOfLoadingPort(voyageStart.getDraftRestrictionOfLoadingPort());
		}

		if (StringUtil.isNotBlank(voyageStart.getLoadingTemp())) {
			voyageStartVO.setLoadingTemp(voyageStart.getLoadingTemp());
		}

		if (StringUtil.isNotBlank(voyageStart.getMetricTons())) {
			voyageStartVO.setMetricTons(voyageStart.getMetricTons());
		}

		if (StringUtil.isNotBlank(voyageStart.getProductNm())) {
			voyageStartVO.setProductNm(voyageStart.getProductNm());
		}

		if (voyageStart.getShipConfirmationSheetId() != null) {
			voyageStartVO.setShipConfirmationSheetId(voyageStart.getShipConfirmationSheetId());
		}

		if (StringUtil.isNotBlank(voyageStart.getUuid())) {
			voyageStartVO.setUuid(voyageStart.getUuid());
		}

		if (StringUtil.isNotBlank(voyageStart.getRangeOfError())) {
			voyageStartVO.setRangeOfError(voyageStart.getRangeOfError());
		}
		if (StringUtil.isNotBlank(voyageStart.getDi())) {
			voyageStartVO.setDi(voyageStart.getDi());
		}
		if (StringUtil.isNotBlank(voyageStart.getDiFileNm())) {
			voyageStartVO.setDiFileNm(voyageStart.getDiFileNm());
		}

		return voyageStartVO;
	}

	@Override
	protected VoyageStart convertToDomain() {
		VoyageStart voyageStart = new VoyageStart();

		if (this.uuid != null) {
			voyageStart.setUuid(this.uuid);
		}
		if (this.accessory != null) {

		}

		return voyageStart;
	}

	/**
	 * VO转化为领域类domain
	 * 
	 * @return
	 */
	public VoyageStart voToDomain() {
		VoyageStart voyageStart = new VoyageStart();

		if (this.accessory!=null) {
			voyageStart.setAccessory(this.accessory);
		}

		if (this.accessoryFileNm!=null) {
			voyageStart.setAccessoryFileNm(this.accessoryFileNm);
		}

		if (StringUtil.isNotBlank(this.bbls)) {
			voyageStart.setBbls(this.bbls);
		}

		if (StringUtil.isNotBlank(this.draftRestrictionOfDischargingPort)) {
			voyageStart.setDraftRestrictionOfDischargingPort(this.draftRestrictionOfDischargingPort);
		}

		if (StringUtil.isNotBlank(this.draftRestrictionOfLoadingPort)) {
			voyageStart.setDraftRestrictionOfLoadingPort(this.draftRestrictionOfLoadingPort);
		}

		if (StringUtil.isNotBlank(this.loadingTemp)) {
			voyageStart.setLoadingTemp(this.loadingTemp);
		}

		if (StringUtil.isNotBlank(this.metricTons)) {
			voyageStart.setMetricTons(this.metricTons);
		}

		if (StringUtil.isNotBlank(this.productNm)) {
			voyageStart.setProductNm(this.productNm);

		}

		if (StringUtil.isNotBlank(this.uuid)) {
			voyageStart.setUuid(this.uuid);
		}

		if (StringUtil.isNotBlank(this.rangeOfError)) {
			voyageStart.setRangeOfError(this.rangeOfError);
		}
		if(this.shipConfirmationSheetId!= null ){
			voyageStart.setShipConfirmationSheetId(this.shipConfirmationSheetId);
		}

		if(this.di!= null ){
			voyageStart.setDi(this.di);
		}
		if(this.diFileNm!= null ){
			voyageStart.setDiFileNm(this.diFileNm);
		}

		return voyageStart;
	}

}