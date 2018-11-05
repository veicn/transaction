package com.sinochem.crude.trade.listed.model.vo.deprecate;

import java.io.Serializable;

@Deprecated
public class AppDemandJoinVO implements Serializable {
	/**
	 * 主键
	 */
	private Long id;

	/**
	 * 单据编号
	 */
	private String demandNo;

	/**
	 * 创建时间
	 */
	private String createTime;

	/**
	 * 创建人
	 */
	private Long creater;

	/**
	 * 状态
	 * 包括需求状态和报价状态
	 * 0：删除
	 * 需求状态：1：新建  2：发布  3：完成
	 * 报价状态：10：报价  20：中标  30：结束
	 */
	private Integer status;

	/**
	 * 种类
	 */
	private String oil;

	/**
	 * 规格
	 */
	private String specs;

	/**
	 * 采购类型
	 */
	private Integer purchaseType;

	/**
	 * 采购类型 内容
	 */
	private String purchaseTypeContent;

	/**
	 * 数量
	 */
	private String num;

	/**
	 * 贸易条款
	 */
	private String tradeItem;

	/**
	 * 付款条款
	 */
	private String payItem;

	/**
	 * 装期
	 */
	private String shipmentDate;

	/**
	 * 截标时间
	 */
	private String stopBidTime;

	/**
	 * 出标时间
	 */
	private String tenderOutTime;

	/**
	 * 发布日期
	 */
	private String pubDate;

	// myBiddinglist用***************************************************************
	/**
	 * 企业名称
	 */
	private String enterpriseName;

	/**
	 * 计价期类型
	 */
	private String valuationProidType;

	/**
	 * 计价公式
	 */
	private String valuationFormula;

	private Long imgEMemberId;

	public Long getImgEMemberId() {
		return imgEMemberId;
	}

	public void setImgEMemberId(Long imgEMemberId) {
		this.imgEMemberId = imgEMemberId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDemandNo() {
		return demandNo;
	}

	public void setDemandNo(String demandNo) {
		this.demandNo = demandNo;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Long getCreater() {
		return creater;
	}

	public void setCreater(Long creater) {
		this.creater = creater;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getOil() {
		return oil;
	}

	public void setOil(String oil) {
		this.oil = oil;
	}

	public String getSpecs() {
		return specs;
	}

	public void setSpecs(String specs) {
		this.specs = specs;
	}

	public Integer getPurchaseType() {
		return purchaseType;
	}

	public void setPurchaseType(Integer purchaseType) {
		this.purchaseType = purchaseType;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getTradeItem() {
		return tradeItem;
	}

	public void setTradeItem(String tradeItem) {
		this.tradeItem = tradeItem;
	}

	public String getPayItem() {
		return payItem;
	}

	public void setPayItem(String payItem) {
		this.payItem = payItem;
	}

	public String getShipmentDate() {
		return shipmentDate;
	}

	public void setShipmentDate(String shipmentDate) {
		this.shipmentDate = shipmentDate;
	}

	public String getStopBidTime() {
		return stopBidTime;
	}

	public void setStopBidTime(String stopBidTime) {
		this.stopBidTime = stopBidTime;
	}

	public String getTenderOutTime() {
		return tenderOutTime;
	}

	public void setTenderOutTime(String tenderOutTime) {
		this.tenderOutTime = tenderOutTime;
	}

	public String getPubDate() {
		return pubDate;
	}

	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}

	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	public String getValuationProidType() {
		return valuationProidType;
	}

	public void setValuationProidType(String valuationProidType) {
		this.valuationProidType = valuationProidType;
	}

	public String getValuationFormula() {
		return valuationFormula;
	}

	public void setValuationFormula(String valuationFormula) {
		this.valuationFormula = valuationFormula;
	}

	public String getPurchaseTypeContent() {
		return purchaseTypeContent;
	}

	public void setPurchaseTypeContent(String purchaseTypeContent) {
		this.purchaseTypeContent = purchaseTypeContent;
	}
}