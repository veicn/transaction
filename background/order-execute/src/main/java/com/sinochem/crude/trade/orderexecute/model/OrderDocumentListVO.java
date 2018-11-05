package com.sinochem.crude.trade.orderexecute.model;

import org.codehaus.jackson.annotate.JsonUnwrapped;

import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.orderexecute.domain.OrderDocumentList;

public class OrderDocumentListVO extends OrderDocumentList {

	private static final long serialVersionUID = 1L;

	//**交易大类*//
	private String oldTradeCategory;

	//**类型名称*//
	private String oldDocumentTypeDesc;
	
	private String oldDocumentType;
	
	/**分页对象*/
	@JsonUnwrapped
	private SimplePageInfo pageInfo;
	
	
	
	public String getOldTradeCategory() {
		return oldTradeCategory;
	}

	public void setOldTradeCategory(String oldTradeCategory) {
		this.oldTradeCategory = oldTradeCategory;
	}

	public String getOldDocumentTypeDesc() {
		return oldDocumentTypeDesc;
	}

	public void setOldDocumentTypeDesc(String oldDocumentTypeDesc) {
		this.oldDocumentTypeDesc = oldDocumentTypeDesc;
	}

	public String getOldDocumentType() {
		return oldDocumentType;
	}

	public void setOldDocumentType(String oldDocumentType) {
		this.oldDocumentType = oldDocumentType;
	}

	public SimplePageInfo getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(SimplePageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}