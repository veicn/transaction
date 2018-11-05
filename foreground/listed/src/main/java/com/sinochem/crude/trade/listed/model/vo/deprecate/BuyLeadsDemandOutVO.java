package com.sinochem.crude.trade.listed.model.vo.deprecate;

import java.io.Serializable;

/**
 * Created by wangn on 03/01/2018
 */
@Deprecated
public class BuyLeadsDemandOutVO implements Serializable{
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	private Long id;

	/**
	 * 单号
	 */
	private String demandNo;

	/**
	 * 原油油种
	 */
	private String oil;

	/**
	 * 数量
	 */
	private String num;

	/**
	 * 贸易条款
	 */
	private String tradeItem;

	/**
	 * 采购类型
	 */
	private Integer purchaseType;

	/**
	 * 采购类型 内容
	 */
	private String purchaseTypeContent;

	/**
	 * 到货期
	 */
	private String arrivalDate;

	/**
	 * 付款条款
	 */
	private String payItem;

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

	/**
	 * 创建时间
	 */
	private String createTime;

	/**
	 * 状态
	 * 包括需求状态和报价状态
	 * 0：删除
	 * 需求状态：1：新建  2：发布  3：完成
	 * 报价状态：10：报价  20：中标  30：结束
	 */
	private Integer status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getDemandNo() {
		return demandNo;
	}

	public void setDemandNo(String demandNo) {
		this.demandNo = demandNo;
	}

	public String getOil() {
		return oil;
	}

	public void setOil(String oil) {
		this.oil = oil;
	}

	public String getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(String arrivalDate) {
		this.arrivalDate = arrivalDate;
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

	public Integer getPurchaseType() {
		return purchaseType;
	}

	public void setPurchaseType(Integer purchaseType) {
		this.purchaseType = purchaseType;
	}

	public String getPayItem() {
		return payItem;
	}

	public void setPayItem(String payItem) {
		this.payItem = payItem;
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

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getPurchaseTypeContent() {
		return purchaseTypeContent;
	}

	public void setPurchaseTypeContent(String purchaseTypeContent) {
		this.purchaseTypeContent = purchaseTypeContent;
	}

	public String getPubDate() {
		return pubDate;
	}

	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}
}

