package com.sinochem.crude.trade.order.remote;

import java.io.Serializable;

/**
 * 取消 订单
 * @author junbo.lv
 */
public class CancelOrderVO implements Serializable {
	private static final long serialVersionUID = -401964042393320617L;
	
	/**
	 * 交易订单 ID
	 */
	private Long tradeId ;
	
	/**
	 * 操作类型
	 * 1：申请
	 * 2：同意
	 * 3：驳回
	 * 4：撤回
	 */
	private Integer cancelType;
	
	/**
	 * 操作人
	 */
	private Long userId;
	
	public Long getTradeId() {
		return tradeId;
	}
	public void setTradeId(Long tradeId) {
		this.tradeId = tradeId;
	}
	public Integer getCancelType() {
		return cancelType;
	}
	public void setCancelType(Integer cancelType) {
		this.cancelType = cancelType;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
