package com.sinochem.crude.trade.transport.query;

import java.util.HashMap;
import java.util.Map;

import com.sinochem.crude.trade.common.QueryBase;
import com.sinochem.crude.trade.common.SimplePageInfo;

public class StatementsQuery extends QueryBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**角色类型*/
	
	/**发票号码*/
	String receiptCode;
	
	/**船合同号*/
	String pactCode;
	/**代理协议编号*/
	String agreementCode;
	
	public SimplePageInfo getPageInfo(){
		SimplePageInfo pageInfo = new SimplePageInfo();
		pageInfo.setPageNum(super.getCurrentPage());
		pageInfo.setPageSize(super.getPageSize());
		return pageInfo;
	}
	
	public String getReceiptCode() {
		return receiptCode;
	}
	public void setReceiptCode(String receiptCode) {
		this.receiptCode = receiptCode;
	}

	public String getPactCode() {
		return pactCode;
	}
	public void setPactCode(String pactCode) {
		this.pactCode = pactCode;
	}

	public String getAgreementCode() {
		return agreementCode;
	}
	public void setAgreementCode(String agreementCode) {
		this.agreementCode = agreementCode;
	}

	@Override
	public Map<String, String> getParameters() {
		Map<String, String> param = new HashMap<String, String>();
		param.put("receiptCode", getReceiptCode());
		param.put("pactCode", getPactCode());
		param.put("agreementCode", getAgreementCode());
		return param;
	}
	
	
	
}
