package com.sinochem.crude.trade.orderexecute.model;

import org.codehaus.jackson.annotate.JsonUnwrapped;

import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.orderexecute.domain.TradeSubject;

public class TradeSubjectVO extends TradeSubject {

	private static final long serialVersionUID = 1L;

	private String productType; 
	/**产品分类*/
	private String subjectName; 
	
	private String subjectCode; 
	/**费用代码*/
	@JsonUnwrapped
	private SimplePageInfo pageInfo;

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
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

	public String getSubjectCode() {
		return subjectCode;
	}

	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}
	
	
}