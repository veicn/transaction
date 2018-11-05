package com.sinochem.crude.trade.orderexecute.query;

import java.util.HashMap;
import java.util.Map;

import com.sinochem.crude.trade.orderexecute.commons.QueryBaseExt;

public class OrderFeeQuery extends QueryBaseExt {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Map<String, String> getParameters() {
		
		Map<String, String> demo = new HashMap<String, String>();
		
		return demo;
	}
	
	private Long epMemberId;
	
	private String valuationModel;
	
	private String quantity;
	
	private String feePrice;
	
	private String totalFee;
	
	private String isAgent;
	
	private Long memberId;
	
	private long orderId;
	
	private String uuid;
	
	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public Long getEpMemberId() {
		return epMemberId;
	}

	public void setEpMemberId(Long epMemberId) {
		this.epMemberId = epMemberId;
	}

	public String getValuationModel() {
		return valuationModel;
	}

	public void setValuationModel(String valuationModel) {
		this.valuationModel = valuationModel;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getFeePrice() {
		return feePrice;
	}

	public void setFeePrice(String feePrice) {
		this.feePrice = feePrice;
	}

	public String getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(String totalFee) {
		this.totalFee = totalFee;
	}

	public String getIsAgent() {
		return isAgent;
	}

	public void setIsAgent(String isAgent) {
		this.isAgent = isAgent;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	


	

}
