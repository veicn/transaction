package com.sinochem.crude.trade.orderexecute.query;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.orderexecute.commons.QueryBaseExt;
import com.sinochem.it.b2b.common.utils.DateUtil;

public class OrderStatementQuery extends QueryBaseExt {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String[] getDateBetween(){
		String createDateStart = null;
		String createDateEnd = null;
		if(StringUtils.isNotEmpty(this.checkTimeDesc)){
			String[] dateArr = this.checkTimeDesc.split(" - ");
			if(dateArr.length == 2){
				createDateStart = dateArr[0].trim();
				createDateEnd = dateArr[1].trim();
			}
		}
		
		return new String[]{createDateStart, createDateEnd};
	}
	
	public Map<String, Object> getQueryParam() {
		Map<String, Object> params = new HashMap<>();
		params.put("orderStatementId", orderStatementId);
		String[] dateArray = getDateBetween();//时间区间查询
		if(dateArray[0] != null){
			params.put("createDateStart", DateUtil.getDate(dateArray[0], "yyyy-MM-dd"));
		}
		if(dateArray[1] != null){
			params.put("createDateEnd", DateUtil.getDate(dateArray[1], "yyyy-MM-dd"));
		}
		params.put("orderStatementNo", orderStatementNo);
		//params.put("checkTimeDesc", checkTimeDesc);
		params.put("confirmPerson", confirmPerson);
		params.put("customerName", customerName);
		params.put("contactName", contactName);
		params.put("tel", tel);
		params.put("sellerCustomerId", sellerCustomerId);
		params.put("buyerCustomerId", buyerCustomerId);
		params.put("status", status);
		params.put("statementType", statementType);
		params.put("noStatus", noStatus);
		params.put("orderNo", orderNo);
		return params;
	}
	
	@Override
	public Map<String, String> getParameters() {
		Map<String, String> params = new HashMap<>();
		params.put("orderStatementId", orderStatementId);
		params.put("orderStatementNo", orderStatementNo);
		params.put("checkTimeDesc", checkTimeDesc);
		params.put("confirmPerson", confirmPerson);
		params.put("customerName", customerName);
		params.put("contactName", contactName);
		params.put("tel", tel);
		params.put("status", status);
		params.put("statementType", statementType);
		params.put("noStatus", noStatus);
		params.put("orderNo", orderNo);
//		params.put("sellerCustomerId", sellerCustomerId);
//		params.put("buyerCustomerId", buyerCustomerId);
		return params;
	}
	
	public SimplePageInfo getPageInfo(){
		SimplePageInfo pageInfo = new SimplePageInfo();
		pageInfo.setPageNum(super.getCurrentPage());
		pageInfo.setPageSize(super.getPageSize());
		
		return pageInfo;
	}
	
	private Long epMemberId;
	
	private String orderStatementId;
	
	/**对账单编号*/
	private String orderStatementNo;
	
	private String checkTimeDesc;
	
	private String confirmPerson;
	
	private String customerName;
	
	private String contactName;
	
	private String tel;
	
	private Long sellerCustomerId;
	
	private Long buyerCustomerId;
	
	private String status;
	
	private String statementType;

	private String noStatus;
	
	private String orderNo;
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Long getEpMemberId() {
		return epMemberId;
	}

	public void setEpMemberId(Long epMemberId) {
		this.epMemberId = epMemberId;
	}


	public String getOrderStatementId() {
		return orderStatementId;
	}

	public void setOrderStatementId(String orderStatementId) {
		this.orderStatementId = orderStatementId;
	}
	

	public String getCheckTimeDesc() {
		return checkTimeDesc;
	}

	public void setCheckTimeDesc(String checkTimeDesc) {
		this.checkTimeDesc = checkTimeDesc;
	}

	public String getConfirmPerson() {
		return confirmPerson;
	}

	public void setConfirmPerson(String confirmPerson) {
		this.confirmPerson = confirmPerson;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getOrderStatementNo() {
		return orderStatementNo;
	}

	public void setOrderStatementNo(String orderStatementNo) {
		this.orderStatementNo = orderStatementNo;
	}

	public Long getSellerCustomerId() {
		return sellerCustomerId;
	}

	public Long getBuyerCustomerId() {
		return buyerCustomerId;
	}

	public void setSellerCustomerId(Long sellerCustomerId) {
		this.sellerCustomerId = sellerCustomerId;
	}

	public void setBuyerCustomerId(Long buyerCustomerId) {
		this.buyerCustomerId = buyerCustomerId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatementType() {
		return statementType;
	}

	public void setStatementType(String statementType) {
		this.statementType = statementType;
	}

	public String getNoStatus() {
		return noStatus;
	}

	public void setNoStatus(String noStatus) {
		this.noStatus = noStatus;
	}

}
