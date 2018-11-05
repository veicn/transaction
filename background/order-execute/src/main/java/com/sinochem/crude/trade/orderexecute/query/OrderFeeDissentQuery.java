package com.sinochem.crude.trade.orderexecute.query;

import java.util.HashMap;
import java.util.Map;

import com.sinochem.crude.trade.orderexecute.commons.QueryBaseExt;

public class OrderFeeDissentQuery extends QueryBaseExt {

	private static final long serialVersionUID = 4607791991273674516L;

	@Override
	public Map<String, String> getParameters() {
		Map<String, String> demo = new HashMap<String, String>();
		
		return demo;
	}
	
	private int canReply; 
	
	private Long orderId;

	private Long orderStatementId;
	
	private String dissentType;
	
	public int getCanReply() {
		return canReply;
	}

	public void setCanReply(int canReply) {
		this.canReply = canReply;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getOrderStatementId() {
		return orderStatementId;
	}

	public void setOrderStatementId(Long orderStatementId) {
		this.orderStatementId = orderStatementId;
	}

	public String getDissentType() {
		return dissentType;
	}

	public void setDissentType(String dissentType) {
		this.dissentType = dissentType;
	}
	
	
}
