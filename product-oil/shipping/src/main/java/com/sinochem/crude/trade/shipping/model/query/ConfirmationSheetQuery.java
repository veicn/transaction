package com.sinochem.crude.trade.shipping.model.query;

import java.util.Date;

public class ConfirmationSheetQuery {

	/** 租船合同编号 */
	private String charterPartyNumber;

	/** 产品 */
	private String product;

	/** 当前有效状态 */
	private String status;

	/** 创建时间-开始 */
	private Date createDateStart;

	/** 创建时间-结束 */
	private Date createDateEnd;
	
	//创建者id
	private Long epMemberId;

	private String confirmationSheetCd;

	/**买家ID*/
	private Long buyerId;  
	
	/**卖家ID*/
	private Long sellerId;

	//中化新ID
	private Long singaporeId;

	//船代id
	private Long shippingAgentId;

	/**
	 * 关键字  微信端字段
	 */
	private String keywords;
	
	public Long getShippingAgentId() {
		return shippingAgentId;
	}

	public void setShippingAgentId(Long shippingAgentId) {
		this.shippingAgentId = shippingAgentId;
	}

	public String getConfirmationSheetCd() {
		return confirmationSheetCd;
	}

	public void setConfirmationSheetCd(String confirmationSheetCd) {
		this.confirmationSheetCd = confirmationSheetCd;
	}

	public Long getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(Long buyerId) {
		this.buyerId = buyerId;
	}

	public Long getSellerId() {
		return sellerId;
	}

	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}

	public Long getSingaporeId() {
		return singaporeId;
	}

	public void setSingaporeId(Long singaporeId) {
		this.singaporeId = singaporeId;
	}

	public Long getEpMemberId() {
		return epMemberId;
	}

	public void setEpMemberId(Long epMemberId) {
		this.epMemberId = epMemberId;
	}

	public String getCharterPartyNumber() {
		return charterPartyNumber;
	}

	public void setCharterPartyNumber(String charterPartyNumber) {
		this.charterPartyNumber = charterPartyNumber;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreateDateStart() {
		return createDateStart;
	}

	public void setCreateDateStart(Date createDateStart) {
		this.createDateStart = createDateStart;
	}

	public Date getCreateDateEnd() {
		return createDateEnd;
	}

	public void setCreateDateEnd(Date createDateEnd) {
		this.createDateEnd = createDateEnd;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
}
