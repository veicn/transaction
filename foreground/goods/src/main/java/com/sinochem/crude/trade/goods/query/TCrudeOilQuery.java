package com.sinochem.crude.trade.goods.query;

public class TCrudeOilQuery {

	/**
	 * 主键
	 */
	private Long id;

	/**
	 * UUID
	 */
	private String uuid;

	/**
	 * 原油中英文名
	 */
	private String crudeName;

	/**
	 * 产地区域id
	 */
	private Long originAreaId;

	/**
	 * 产地id
	 */
	private Long originId;

	/**
	 * 种类id
	 */
	private Long catagoryId;

	/**
	 * 有效标志
	 */
	private String aliveFlag;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getAliveFlag() {
		return aliveFlag;
	}

	public void setAliveFlag(String aliveFlag) {
		this.aliveFlag = aliveFlag;
	}

	public String getCrudeName() {
		return crudeName;
	}

	public void setCrudeName(String crudeName) {
		this.crudeName = crudeName;
	}

	public Long getOriginAreaId() {
		return originAreaId;
	}

	public void setOriginAreaId(Long originAreaId) {
		this.originAreaId = originAreaId;
	}

	public Long getOriginId() {
		return originId;
	}

	public void setOriginId(Long originId) {
		this.originId = originId;
	}

	public Long getCatagoryId() {
		return catagoryId;
	}

	public void setCatagoryId(Long catagoryId) {
		this.catagoryId = catagoryId;
	}

}