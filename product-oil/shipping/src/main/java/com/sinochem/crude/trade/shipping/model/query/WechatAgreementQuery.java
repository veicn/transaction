package com.sinochem.crude.trade.shipping.model.query;

/**
 * 租船协议 查询列表 微信API query
 * Wh
 * 2018年3月14日
 * @version V1.0
 */
public class WechatAgreementQuery {
	
	private String productLoadingAndDischarge;
	
	private Long epMemberId;

	public String getProductLoadingAndDischarge() {
		return productLoadingAndDischarge;
	}

	public void setProductLoadingAndDischarge(String productLoadingAndDischarge) {
		this.productLoadingAndDischarge = productLoadingAndDischarge;
	}

	public Long getEpMemberId() {
		return epMemberId;
	}

	public void setEpMemberId(Long epMemberId) {
		this.epMemberId = epMemberId;
	}
	
	
	
}
