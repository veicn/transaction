package com.sinochem.crude.trade.orderexecute.query;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.orderexecute.commons.QueryBaseExt;
import com.sinochem.crude.trade.orderexecute.commons.constants.Constants;
import com.sinochem.it.b2b.common.utils.DateUtil;

public class OrderSettlementQuery extends QueryBaseExt {

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
		params.put("orderSettlementId", orderSettlementId);
		String[] dateArray = getDateBetween();
		if(dateArray[0] != null){
			params.put("createDateStart", DateUtil.getDate(dateArray[0], "yyyy-MM-dd"));
		}
		if(dateArray[1] != null){
			params.put("createDateEnd", DateUtil.getDate(dateArray[1], "yyyy-MM-dd"));
		}
		params.put("orderSettlementNo", orderSettlementNo);
		//params.put("checkTimeDesc", checkTimeDesc);
		params.put("confirmPerson", confirmPerson);
		params.put("customerName", customerName);
		params.put("contactName", contactName);
		params.put("tel", tel);
		params.put("sellerCustomerId", sellerCustomerId);
		params.put("buyerCustomerId", buyerCustomerId);
		params.put("status", status);
		params.put("settlementType", settlementType);
		if(orderNo != null && orderNo != ""){
			params.put("orderSettlementNo", orderNo + Constants.SETTLEMENT_PRE);
		}
		return params;
	}
	
	@Override
	public Map<String, String> getParameters() {
		Map<String, String> params = new HashMap<>();
		params.put("orderSettlementId", orderSettlementId);
		params.put("orderSettlementNo", orderSettlementNo);
		params.put("checkTimeDesc", checkTimeDesc);
		params.put("confirmPerson", confirmPerson);
		params.put("customerName", customerName);
		params.put("contactName", contactName);
		params.put("tel", tel);
		params.put("status", status);
		params.put("settlementType", settlementType);
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
	
	private String orderSettlementId;
	
	/**结算单编号*/
	private String orderSettlementNo;
	
	private String orderNo;
	/**查询时间**/
	private String checkTimeDesc;
	
	private String confirmPerson;
	
	private String customerName;
	
	private String contactName;
	
	private String tel;
	
	private Long sellerCustomerId;
	
	private Long buyerCustomerId;
	
	private String status;
	
	private String settlementType;


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getOrderSettlementId() {
		return orderSettlementId;
	}

	public void setOrderSettlementId(String orderSettlementId) {
		this.orderSettlementId = orderSettlementId;
	}

	public String getOrderSettlementNo() {
		return orderSettlementNo;
	}

	public void setOrderSettlementNo(String orderSettlementNo) {
		this.orderSettlementNo = orderSettlementNo;
	}

	public Long getEpMemberId() {
		return epMemberId;
	}

	public void setEpMemberId(Long epMemberId) {
		this.epMemberId = epMemberId;
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

	public String getSettlementType() {
		return settlementType;
	}

	public void setSettlementType(String settlementType) {
		this.settlementType = settlementType;
	}

}
