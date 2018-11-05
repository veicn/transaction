package com.sinochem.crude.trade.orderexecute.query;

import java.util.HashMap;
import java.util.Map;

import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.orderexecute.commons.QueryBaseExt;

public class ContractQuery extends QueryBaseExt {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5012036298812787135L;
	
	/** 订单编号 */
	private String orderNo;
	/** 合同编号 */
	private String contractNo;
	/** 合同名称 */
	private String contractNameLike;
	/**
	 * 订单类别
	 */
	private String tradeCategory;
	
	
	public String getTradeCategory() {
		return tradeCategory;
	}

	public void setTradeCategory(String tradeCategory) {
		this.tradeCategory = tradeCategory;
	}

	public Map<String, Object> getQueryParam() {
		Map<String, Object> params = new HashMap<>();
		params.put("orderNo", this.orderNo);
		params.put("contractNo", this.contractNo);
		params.put("contractNameLike", this.contractNameLike);
		params.put("tradeCategory", this.tradeCategory);
		return params;
	}
	
	@Override
	public Map<String, String> getParameters() {
		Map<String, String> params = new HashMap<>();
		params.put("orderNo", this.orderNo);
		params.put("contractNo", this.contractNo);
		params.put("contractNameLike", this.contractNameLike);
		params.put("tradeCategory", this.tradeCategory);
		return params;
	}
	
	public SimplePageInfo getPageInfo(){
		SimplePageInfo pageInfo = new SimplePageInfo();
		pageInfo.setPageNum(super.getCurrentPage());
		pageInfo.setPageSize(super.getPageSize());
		
		return pageInfo;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public String getContractNo() {
		return contractNo;
	}

	public String getContractNameLike() {
		return contractNameLike;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public void setContractNameLike(String contractNameLike) {
		this.contractNameLike = contractNameLike;
	}
}
