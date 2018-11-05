package com.sinochem.crude.trade.shipping.model.vo;

import com.eyeieye.melody.util.StringUtil;
import com.sinochem.crude.trade.common.base.BaseDomainVO;
import com.sinochem.crude.trade.common.utils.DateUtil;
import com.sinochem.crude.trade.shipping.domain.TransitUnloading;

public class TransitUnloadingVO extends BaseDomainVO<TransitUnloading> {

	private static final long serialVersionUID = 1L;

	/** UUID */
	private String uuid;

	/** 船舶确认单ID */
	private Long shipConfirmationSheetId;

	/** 日期 */
	private String datetime;

	/** 坐标（经纬度） */
	private String posn;

	/** 航速（24小时） */
	private java.math.BigDecimal aveSpd;

	/** 航速（全程航速） */
	private java.math.BigDecimal gAveSpd;

	/** 转速 */
	private String rpm;

	/** 海况CD */
	private String seaId;

	/** 海况 */
	private String sea;

	/** 目的港 */
	private String destination;

	/** 预计到港时间 */
	private String eta;



	private String accessoryFileNm;
	/** 附件 */
	private String accessory;

	public static long getSerialversionuid() {
		return serialVersionUID;
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
	public String getAccessoryFileNm() {
		return accessoryFileNm;
	}

	public void setAccessoryFileNm(String accessoryFileNm) {
		this.accessoryFileNm = accessoryFileNm;
	}
	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public String getPosn() {
		return posn;
	}

	public void setPosn(String posn) {
		this.posn = posn;
	}

	public java.math.BigDecimal getAveSpd() {
		return aveSpd;
	}

	public void setAveSpd(java.math.BigDecimal aveSpd) {
		this.aveSpd = aveSpd;
	}

	public java.math.BigDecimal getgAveSpd() {
		return gAveSpd;
	}

	public void setgAveSpd(java.math.BigDecimal gAveSpd) {
		this.gAveSpd = gAveSpd;
	}

	public String getRpm() {
		return rpm;
	}

	public void setRpm(String rpm) {
		this.rpm = rpm;
	}

	public String getSeaId() {
		return seaId;
	}

	public void setSeaId(String seaId) {
		this.seaId = seaId;
	}

	public String getSea() {
		return sea;
	}

	public void setSea(String sea) {
		this.sea = sea;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getEta() {
		return eta;
	}

	public void setEta(String eta) {
		this.eta = eta;
	}

	public String getAccessory() {
		return accessory;
	}

	public void setAccessory(String accessory) {
		this.accessory = accessory;
	}

	/**
	 * 将领域类domain转化为VO
	 */
	@Override
	protected void convertToVO(TransitUnloading transitUnloading) {
		if (transitUnloading.getUuid() != null) {
			this.uuid = transitUnloading.getUuid();
		}

	}

	/**
	 * 将领域类domain转化为VO
	 */
	public TransitUnloadingVO domainToVO(TransitUnloading transitUnloading) {

		TransitUnloadingVO transitUnloadingVO = new TransitUnloadingVO();

		if (StringUtil.isNotBlank(transitUnloading.getAccessory())) {
			transitUnloadingVO.setAccessory(transitUnloading.getAccessory());
		}

		if (transitUnloading.getAveSpd() != null) {
			transitUnloadingVO.setAveSpd(transitUnloading.getAveSpd());
		}

		if (transitUnloading.getDatetime() != null) {
			transitUnloadingVO.setDatetime(DateUtil.formatDateTime(transitUnloading.getDatetime()));
		}

		if (StringUtil.isNotBlank(transitUnloading.getDestination())) {
			transitUnloadingVO.setDestination(transitUnloading.getDestination());
		}

		if (transitUnloading.getEta() != null) {
			transitUnloadingVO.setEta(DateUtil.formatDateTime(transitUnloading.getEta()));
		}

		if (transitUnloading.getGAveSpd() != null) {
			transitUnloadingVO.setgAveSpd(transitUnloading.getGAveSpd());
		}

		if (StringUtil.isNotBlank(transitUnloading.getPosn())) {
			transitUnloadingVO.setPosn(transitUnloading.getPosn());
		}

		if (StringUtil.isNotBlank(transitUnloading.getRpm())) {
			transitUnloadingVO.setRpm(transitUnloading.getRpm());
		}

		if (StringUtil.isNotBlank(transitUnloading.getSea())) {
			transitUnloadingVO.setSea(transitUnloading.getSea());
		}

		if (StringUtil.isNotBlank(transitUnloading.getSeaId())) {
			transitUnloadingVO.setSeaId(transitUnloading.getSeaId());
		}

		if (transitUnloading.getShipConfirmationSheetId() != null) {
			transitUnloadingVO.setShipConfirmationSheetId(transitUnloading.getShipConfirmationSheetId());
		}

		if (StringUtil.isNotBlank(transitUnloading.getUuid())) {
			transitUnloadingVO.setUuid(transitUnloading.getUuid());
		}
		if(StringUtil.isNotBlank(transitUnloading.getAccessoryFileNm())){
			transitUnloadingVO.setAccessoryFileNm(transitUnloading.getAccessoryFileNm());
		}

		return transitUnloadingVO;
	}

	/**
	 * 将VO转换为领域类domain
	 */
	@Override
	protected TransitUnloading convertToDomain() {
		TransitUnloading transitUnloading = new TransitUnloading();

		if (this.uuid != null) {
			// ConfirmationSheet.setUuid(this.uuid);
		}
		return transitUnloading;
	}

	/**
	 * 将VO转换为领域类domain
	 */
	public TransitUnloading voToDomain() {
		TransitUnloading transitUnloading = new TransitUnloading();

		if (StringUtil.isNotBlank(this.accessory)) {
			transitUnloading.setAccessory(this.accessory);
		}

		if (StringUtil.isNotBlank(this.datetime)) {
			transitUnloading.setDatetime(DateUtil.getDateTime(this.datetime));
		}

		if (StringUtil.isNotBlank(this.destination)) {
			transitUnloading.setDestination(this.destination);
		}

		if (StringUtil.isNotBlank(this.eta)) {
			transitUnloading.setEta(DateUtil.getDateTime(this.eta));
		}

		if (StringUtil.isNotBlank(this.posn)) {
			transitUnloading.setPosn(this.posn);
		}

		if (StringUtil.isNotBlank(this.rpm)) {
			transitUnloading.setRpm(this.rpm);
		}

		if (StringUtil.isNotBlank(this.sea)) {
			transitUnloading.setSea(this.sea);
		}

		if (StringUtil.isNotBlank(this.seaId)) {
			transitUnloading.setSeaId(this.seaId);
		}

		if (StringUtil.isNotBlank(this.uuid)) {
			transitUnloading.setUuid(this.uuid);
		}
		if(StringUtil.isNotBlank(String.valueOf(this.shipConfirmationSheetId))){
			transitUnloading.setShipConfirmationSheetId(this.shipConfirmationSheetId);
		}
		if(StringUtil.isNotBlank(String.valueOf(this.aveSpd))){
			transitUnloading.setAveSpd(this.aveSpd);
		}
		if(StringUtil.isNotBlank(String.valueOf(this.gAveSpd))){
			transitUnloading.setGAveSpd(this.gAveSpd);
		}
		if(StringUtil.isNotBlank(this.accessoryFileNm)){
			transitUnloading.setAccessoryFileNm(this.accessoryFileNm);
		}


		return transitUnloading;
	}

}