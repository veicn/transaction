package com.sinochem.crude.trade.listed.domain;

import com.sinochem.crude.trade.order.remote.ShipBerth;

import java.math.BigDecimal;

/**
 * 泊位信息 ,这个对象加载的时候要做成widget
 */
public class DemandShipBerth {
	private Long id;

	/**
	 * 泊位名称
	 */
	private String berthName;

	/**
	 * 泊位等级
	 */
	private Integer berthGrade;

	/**
	 * 吃水限制
	 */
	private BigDecimal draftLimitation;

	/**
	 * 船型
	 */
	private Integer shipType;

	/**
	 * 载重吨位最小值
	 */
	private Integer carryingCapacityMin;

	/**
	 * 载重吨位最大值
	 */
	private Integer carryingCapacityMax;

	/**
	 * 采购需求id
	 */
	private Long demandId;

	/**
	 * 泊位说明
	 */
	private String berthDesc;

	public DemandShipBerth(){}

	/** getters and setters */
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBerthName() {
		return berthName;
	}

	public void setBerthName(String berthName) {
		this.berthName = berthName;
	}

	public Integer getBerthGrade() {
		return berthGrade;
	}

	public void setBerthGrade(Integer berthGrade) {
		this.berthGrade = berthGrade;
	}

	public BigDecimal getDraftLimitation() {
		return draftLimitation;
	}

	public void setDraftLimitation(BigDecimal draftLimitation) {
		this.draftLimitation = draftLimitation;
	}


	public Integer getShipType() {
		return shipType;
	}

	public void setShipType(Integer shipType) {
		this.shipType = shipType;
	}

	public void setCarryingCapacityMin(Integer carryingCapacityMin) {
		this.carryingCapacityMin = carryingCapacityMin;
	}

	public Integer getCarryingCapacityMax() {
		return carryingCapacityMax;
	}

	public void setCarryingCapacityMax(Integer carryingCapacityMax) {
		this.carryingCapacityMax = carryingCapacityMax;
	}

	public Long getDemandId() {
		return demandId;
	}

	public void setDemandId(Long demandId) {
		this.demandId = demandId;
	}

	public String getBerthDesc() {
		return berthDesc;
	}

	public void setBerthDesc(String berthDesc) {
		this.berthDesc = berthDesc;
	}

	public Integer getCarryingCapacityMin() {
		return carryingCapacityMin;
	}
}