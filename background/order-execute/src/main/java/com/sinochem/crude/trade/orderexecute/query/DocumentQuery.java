package com.sinochem.crude.trade.orderexecute.query;

import java.util.HashMap;
import java.util.Map;

import com.sinochem.crude.trade.orderexecute.commons.QueryBaseExt;

public class DocumentQuery extends QueryBaseExt {

	private static final long serialVersionUID = 4607791991273674516L;

	@Override
	public Map<String, String> getParameters() {
		Map<String, String> demo = new HashMap<String, String>();
		
		return demo;
	}
	
	private Long orderDocumentId;
	
	private String documentType;
	
	private String tradeCategory;

	private String formPage;
	
	private int showDetail;
	
	// 外部接口用
	private String uuid;

	public Long getOrderDocumentId() {
		return orderDocumentId;
	}

	public void setOrderDocumentId(Long orderDocumentId) {
		this.orderDocumentId = orderDocumentId;
	}

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	public String getTradeCategory() {
		return tradeCategory;
	}

	public void setTradeCategory(String tradeCategory) {
		this.tradeCategory = tradeCategory;
	}

	public String getFormPage() {
		return formPage;
	}

	public void setFormPage(String formPage) {
		this.formPage = formPage;
	}

	public int getShowDetail() {
		return showDetail;
	}

	public void setShowDetail(int showDetail) {
		this.showDetail = showDetail;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
}
