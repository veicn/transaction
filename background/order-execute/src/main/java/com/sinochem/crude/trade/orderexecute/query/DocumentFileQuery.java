package com.sinochem.crude.trade.orderexecute.query;

import java.util.HashMap;
import java.util.Map;

import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.orderexecute.commons.QueryBaseExt;

public class DocumentFileQuery extends QueryBaseExt {

	private static final long serialVersionUID = 4607791991273674516L;
	
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
		params.put("documentType", this.documentType);
		params.put("epMemberId", this.epMemberId);
		params.put("tradeCategory", this.tradeCategory);
		params.put("shipName", this.shipName);
		return params;
	}
	
	@Override
	public Map<String, String> getParameters() {
		Map<String, String> params = new HashMap<>();
		params.put("orderNo", this.orderNo);
		params.put("contractNo", this.contractNo);
		params.put("documentType", this.documentType);
		params.put("epMemberId", this.epMemberId);
		params.put("tradeCategory", this.tradeCategory);
		params.put("shipName", this.shipName);
		
		return params;
	}
	
	public SimplePageInfo getPageInfo(){
		SimplePageInfo pageInfo = new SimplePageInfo();
		pageInfo.setPageNum(super.getCurrentPage());
		pageInfo.setPageSize(super.getPageSize());
		
		return pageInfo;
	}
	private String shipName;
	
	public String getShipName() {
		return shipName;
	}

	public void setShipName(String shipName) {
		this.shipName = shipName;
	}
	private String orderNo;
	
	private String contractNo;

	private String documentType;

	private String epMemberId;
	
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	public String getEpMemberId() {
		return epMemberId;
	}

	public void setEpMemberId(String epMemberId) {
		this.epMemberId = epMemberId;
	}
}
