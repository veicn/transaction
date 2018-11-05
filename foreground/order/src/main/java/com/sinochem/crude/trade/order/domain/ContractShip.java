package com.sinochem.crude.trade.order.domain;

import com.sinochem.crude.trade.order.model.valid.CrudeOilValid;
import com.sinochem.crude.trade.order.model.valid.LongTermValid;
import com.sinochem.crude.trade.order.model.valid.ProductOilValid;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Date;
import java.util.List;

/**
 * 挂买船务信息 这里注意船务的信息放在一起
 * 
 * @author Leo
 *
 */
public class ContractShip {

	/**
     */
	private Long id;

	/**
	 * 运输方式
	 */
	private Integer transportModes;

	/**
	 * 采购单信息
	 */
	private Long contractId;

	/**
	 * 卸货港
	 */
	private String dischargePort;

	/**
	 * 装货港
	 */
	private String shipmentPort;

	/**
	 * 装货开始时间
	 */
	private Date shipmentStartTime;

	/**
	 * 装货结束时间
	 */
	private Date shipmentEndTime;

	/**
	 * 到货开始时间
	 */
	private Date dischargeStartTime;

	/**
	 * 到货结束时间
	 */
	private Date dischargeEndTime;

	/**
	 * 泊位信息
	 */
	private List<ContractShipBerth> contractShipBerths;

	/**
	 * 是否可以外轮靠岸
	 */
	private boolean abroad;

	/**
	 * 船型
	 */
	private Integer shipType;
	/**
	 * 创建人MemberID
	 */
	private Long creater;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 修改人
	 */
	private Long updater;
	/**
	 * 修改时间
	 */
	private Date updateTime;

	public Long getContractId() {
		return contractId;
	}

	public void setContractId(Long contractId) {
		this.contractId = contractId;
	}

	public List<ContractShipBerth> getContractShipBerths() {
		return contractShipBerths;
	}

	public void setContractShipBerths(List<ContractShipBerth> contractShipBerths) {
		this.contractShipBerths = contractShipBerths;
	}

	public boolean isAbroad() {
		return abroad;
	}

	public void setAbroad(boolean abroad) {
		this.abroad = abroad;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getTransportModes() {
		return transportModes == null ? 0 : transportModes.intValue();
	}

	public void setTransportModes(Integer transportModes) {
		this.transportModes = transportModes;
	}

	@NotEmpty(groups = {CrudeOilValid.class, ProductOilValid.class})
	public String getDischargePort() {
		return dischargePort;
	}

	public void setDischargePort(String dischargePort) {
		this.dischargePort = dischargePort;
	}

	@NotEmpty(groups = {CrudeOilValid.class, ProductOilValid.class})
	public String getShipmentPort() {
		return shipmentPort;
	}

	public void setShipmentPort(String shipmentPort) {
		this.shipmentPort = shipmentPort;
	}

	public Date getShipmentStartTime() {
		return shipmentStartTime;
	}

	public void setShipmentStartTime(Date shipmentStartTime) {
		this.shipmentStartTime = shipmentStartTime;
	}

	public Date getShipmentEndTime() {
		return shipmentEndTime;
	}

	public void setShipmentEndTime(Date shipmentEndTime) {
		this.shipmentEndTime = shipmentEndTime;
	}

	public Date getDischargeStartTime() {
		return dischargeStartTime;
	}

	public void setDischargeStartTime(Date dischargeStartTime) {
		this.dischargeStartTime = dischargeStartTime;
	}

	public Date getDischargeEndTime() {
		return dischargeEndTime;
	}

	public void setDischargeEndTime(Date dischargeEndTime) {
		this.dischargeEndTime = dischargeEndTime;
	}

	public int getShipType() {
		return shipType == null ? 0 : shipType.intValue();
	}

	public void setShipType(Integer shipType) {
		this.shipType = shipType;
	}

	public Long getCreater() {
		return creater;
	}

	public void setCreater(Long creater) {
		this.creater = creater;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getUpdater() {
		return updater;
	}

	public void setUpdater(Long updater) {
		this.updater = updater;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
