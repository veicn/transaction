package com.sinochem.crude.trade.orderexecute.controller.openapi.vo;


/**
 * 
 * @author Down
 *
 */
public class ShipInfoVO extends InputVO {

	private static final long serialVersionUID = 1L;

	/**
	 * 总吨
	 */
	private Double allQuantity;
	
	/**
	 * 满载吃水（米）
	 */
	private Double draft;
	
	/**
	 * 意向运输货物
	 */
	private String intentGoods;
	
	/**
	 * intentQuantity
	 */
	private Double intentQuantity;
	
	/**
	 * 意向运输范围
	 */
	private String intentRange;
	
	/**
	 * 船舶总长（米）
	 */
	private Double length;
	
	/**
	 * MMSI号
	 */
	private String mmsi;
	
	/**
	 * 船舶名称
	 */
	private String name;
	
	/**
	 * 拼音简称
	 */
	private String pinyinSim;
	
	/**
	 * 参考载重吨
	 */
	private Double refQuantity;
	
	/**
	 * 材质涂层
	 */
	private String stuffCoat;
	
	/**
	 * 材质种类
	 */
	private String stuffType;
	
	/**
	 * 船舶类型
	 */
	private String type;
	
	/**
	 * 订单ID
	 */
	private String uuid;
	
	/**
	 * 货仓数量
	 */
	private Double warehouseQuantity;
	
	/**
	 * 运单号
	 */
	private String waybillId;
	
	/**
	 * 船宽（米）
	 */
	private Double wide;

	public Double getAllQuantity() {
		return allQuantity;
	}

	public void setAllQuantity(Double allQuantity) {
		this.allQuantity = allQuantity;
	}

	public Double getDraft() {
		return draft;
	}

	public void setDraft(Double draft) {
		this.draft = draft;
	}

	public String getIntentGoods() {
		return intentGoods;
	}

	public void setIntentGoods(String intentGoods) {
		this.intentGoods = intentGoods;
	}

	public Double getIntentQuantity() {
		return intentQuantity;
	}

	public void setIntentQuantity(Double intentQuantity) {
		this.intentQuantity = intentQuantity;
	}

	public String getIntentRange() {
		return intentRange;
	}

	public void setIntentRange(String intentRange) {
		this.intentRange = intentRange;
	}

	public Double getLength() {
		return length;
	}

	public void setLength(Double length) {
		this.length = length;
	}

	public String getMmsi() {
		return mmsi;
	}

	public void setMmsi(String mmsi) {
		this.mmsi = mmsi;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPinyinSim() {
		return pinyinSim;
	}

	public void setPinyinSim(String pinyinSim) {
		this.pinyinSim = pinyinSim;
	}

	public Double getRefQuantity() {
		return refQuantity;
	}

	public void setRefQuantity(Double refQuantity) {
		this.refQuantity = refQuantity;
	}

	public String getStuffCoat() {
		return stuffCoat;
	}

	public void setStuffCoat(String stuffCoat) {
		this.stuffCoat = stuffCoat;
	}

	public String getStuffType() {
		return stuffType;
	}

	public void setStuffType(String stuffType) {
		this.stuffType = stuffType;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Double getWarehouseQuantity() {
		return warehouseQuantity;
	}

	public void setWarehouseQuantity(Double warehouseQuantity) {
		this.warehouseQuantity = warehouseQuantity;
	}

	public String getWaybillId() {
		return waybillId;
	}

	public void setWaybillId(String waybillId) {
		this.waybillId = waybillId;
	}

	public Double getWide() {
		return wide;
	}

	public void setWide(Double wide) {
		this.wide = wide;
	}
}