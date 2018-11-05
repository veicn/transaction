package com.sinochem.crude.trade.shipping.model.vo;

import com.eyeieye.melody.util.StringUtil;
import com.sinochem.crude.trade.common.base.BaseDomainVO;
import com.sinochem.crude.trade.common.utils.DateUtil;
import com.sinochem.crude.trade.shipping.domain.UnloadPort;

public class UnloadPortVO extends BaseDomainVO<UnloadPort> {

	private static final long serialVersionUID = 1L;

	/** UUID */
	private String uuid;

	/** 船舶确认单ID */
	private Long shipConfirmationSheetId;

	/** 卸货港口ID */
	private String dischargingPortId;

	/** 卸货港口 */
	private String dischargingPort;

	/** 起锚时间 */
	private String anchorAweigh;

	/** 引水上船时间 */
	private String pob;

	/** 靠泊时间 */
	private String allFast;

	/** 拆臂扫管线时间 */
	private String shoreArmDisconnecting;

	/** 完成卸货日期时间 */
	private String completedDischarged;

	/** 离泊 */
	private String allLinesCastOffAndVesselSailed;

	/** 公吨 */
	private String metricTons;

	/** UUID */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	/** UUID */
	public String getUuid() {
		return this.uuid;
	}

	/** 船舶确认单ID */
	public void setShipConfirmationSheetId(Long shipConfirmationSheetId) {
		this.shipConfirmationSheetId = shipConfirmationSheetId;
	}

	/** 船舶确认单ID */
	public Long getShipConfirmationSheetId() {
		return this.shipConfirmationSheetId;
	}

	/** 卸货港口ID */
	public void setDischargingPortId(String dischargingPortId) {
		this.dischargingPortId = dischargingPortId;
	}

	/** 卸货港口ID */
	public String getDischargingPortId() {
		return this.dischargingPortId;
	}

	/** 卸货港口 */
	public void setDischargingPort(String dischargingPort) {
		this.dischargingPort = dischargingPort;
	}

	/** 卸货港口 */
	public String getDischargingPort() {
		return this.dischargingPort;
	}

	/** 起锚时间 */
	public void setAnchorAweigh(String anchorAweigh) {
		this.anchorAweigh = anchorAweigh;
	}

	/** 起锚时间 */
	public String getAnchorAweigh() {
		return this.anchorAweigh;
	}

	/** 引水上船时间 */
	public void setPob(String pob) {
		this.pob = pob;
	}

	/** 引水上船时间 */
	public String getPob() {
		return this.pob;
	}

	/** 靠泊时间 */
	public void setAllFast(String allFast) {
		this.allFast = allFast;
	}

	/** 靠泊时间 */
	public String getAllFast() {
		return this.allFast;
	}

	/** 拆臂扫管线时间 */
	public void setShoreArmDisconnecting(String shoreArmDisconnecting) {
		this.shoreArmDisconnecting = shoreArmDisconnecting;
	}

	/** 拆臂扫管线时间 */
	public String getShoreArmDisconnecting() {
		return this.shoreArmDisconnecting;
	}

	/** 完成卸货日期时间 */
	public void setCompletedDischarged(String completedDischarged) {
		this.completedDischarged = completedDischarged;
	}

	/** 完成卸货日期时间 */
	public String getCompletedDischarged() {
		return this.completedDischarged;
	}

	/** 离泊 */
	public void setAllLinesCastOffAndVesselSailed(String allLinesCastOffAndVesselSailed) {
		this.allLinesCastOffAndVesselSailed = allLinesCastOffAndVesselSailed;
	}

	/** 离泊 */
	public String getAllLinesCastOffAndVesselSailed() {
		return this.allLinesCastOffAndVesselSailed;
	}

	/** 公吨 */
	public void setMetricTons(String metricTons) {
		this.metricTons = metricTons;
	}

	/** 公吨 */
	public String getMetricTons() {
		return this.metricTons;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * 领域类domain转化为VO
	 */
	@Override
	protected void convertToVO(UnloadPort domain) {
		// TODO Auto-generated method stub

	}

	public UnloadPortVO domainToVO(UnloadPort unloadPort) {
		UnloadPortVO unloadPortVO = new UnloadPortVO();

		if (unloadPort.getAllFast() != null) {
			unloadPortVO.setAllFast(DateUtil.formatDateTime(unloadPort.getAllFast()));
		}

		if (unloadPort.getAllLinesCastOffAndVesselSailed() != null) {
			unloadPortVO.setAllLinesCastOffAndVesselSailed(
					DateUtil.formatDateTime(unloadPort.getAllLinesCastOffAndVesselSailed()));
		}

		if (unloadPort.getAnchorAweigh() != null) {
			unloadPortVO.setAnchorAweigh(DateUtil.formatDateTime(unloadPort.getAnchorAweigh()));
		}

		if (unloadPort.getCompletedDischarged() != null) {
			unloadPortVO.setCompletedDischarged(DateUtil.formatDateTime(unloadPort.getCompletedDischarged()));
		}

		if (StringUtil.isNotBlank(unloadPort.getDischargingPort())) {
			unloadPortVO.setDischargingPort(unloadPort.getDischargingPort());
		}

		if (StringUtil.isNotBlank(unloadPort.getDischargingPortId())) {
			unloadPortVO.setDischargingPortId(unloadPort.getDischargingPortId());
		}

		if (StringUtil.isNotBlank(unloadPort.getMetricTons())) {
			unloadPortVO.setMetricTons(unloadPort.getMetricTons());
		}

		if (unloadPort.getPob() != null) {
			unloadPortVO.setPob(DateUtil.formatDateTime(unloadPort.getPob()));
		}

		if (unloadPort.getShipConfirmationSheetId() != null) {
			unloadPortVO.setShipConfirmationSheetId(unloadPort.getShipConfirmationSheetId());
		}

		if (unloadPort.getShoreArmDisconnecting() != null) {
			unloadPortVO.setShoreArmDisconnecting(DateUtil.formatDateTime(unloadPort.getShoreArmDisconnecting()));
		}

		if (StringUtil.isNotBlank(unloadPort.getUuid())) {
			unloadPortVO.setUuid(unloadPort.getUuid());
		}

		return unloadPortVO;

	}

	/**
	 * VO转化为领域类domain
	 */
	@Override
	protected UnloadPort convertToDomain() {
		// TODO Auto-generated method stub
		return null;
	}

	public UnloadPort voToDomain() {
		UnloadPort unloadPort = new UnloadPort();

		if (StringUtil.isNotBlank(this.allFast)) {
			unloadPort.setAllFast(DateUtil.getDateTime(this.allFast));
		}

		if (StringUtil.isNotBlank(this.allLinesCastOffAndVesselSailed)) {
			unloadPort.setAllLinesCastOffAndVesselSailed(DateUtil.getDateTime(this.allLinesCastOffAndVesselSailed));
		}

		if (StringUtil.isNotBlank(this.anchorAweigh)) {
			unloadPort.setAnchorAweigh(DateUtil.getDateTime(this.anchorAweigh));
		}

		if (StringUtil.isNotBlank(this.completedDischarged)) {
			unloadPort.setCompletedDischarged(DateUtil.getDateTime(this.completedDischarged));
		}

		if (StringUtil.isNotBlank(this.dischargingPort)) {
			unloadPort.setDischargingPort(this.dischargingPort);
		}

		if (StringUtil.isNotBlank(this.dischargingPortId)) {
			unloadPort.setDischargingPortId(this.dischargingPortId);
		}

		if (StringUtil.isNotBlank(this.metricTons)) {
			unloadPort.setMetricTons(this.metricTons);
		}

		if (StringUtil.isNotBlank(this.pob)) {
			unloadPort.setPob(DateUtil.getDate(this.pob));
		}

		if (StringUtil.isNotBlank(this.shoreArmDisconnecting)) {
			unloadPort.setShoreArmDisconnecting(DateUtil.getDateTime(this.shoreArmDisconnecting));
		}

		if (StringUtil.isNotBlank(this.uuid)) {
			unloadPort.setUuid(this.uuid);
		}
		if(StringUtil.isNotBlank(this.shipConfirmationSheetId.toString())){
			unloadPort.setShipConfirmationSheetId(this.shipConfirmationSheetId);
		}

		return unloadPort;
	}
}