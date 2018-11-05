package com.sinochem.crude.trade.orderexecute.query;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.orderexecute.commons.QueryBaseExt;
import com.sinochem.it.b2b.common.utils.DateUtil;

public class OrderQuery extends QueryBaseExt {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7115926400799302377L;
	
	private Integer index;
	
	/**
	 * 订单编号
	 */
	private String orderNo;
	/**
	 * 创建时间区间
	 */
	private String createDateBetween;
	/**
	 * 订单状态
	 */
	private String orderStatus;
	/**
	 * 销售方式
	 */
	private String tradeType;
	/**
	 * 订单类别
	 */
	private String tradeCategory;
	/**
	 * 搜索关键字
	 */
	private String keyword;
	
	/**
	 * 买家公司ID
	 */
	private String buyerCustomerId;
	
	/**
	 * 卖家公司ID
	 */
	private String sellerCustomerId;
	
	/**
	 * 全部，包含买家，卖家，代理商
	 * @return
	 */
	private String allCustomerId;
	
	private String[] getDateBetween(){
		String createDateStart = null;
		String createDateEnd = null;
		if(StringUtils.isNotEmpty(this.createDateBetween)){
			String[] dateArr = this.createDateBetween.split(" - ");
			if(dateArr.length == 2){
				createDateStart = dateArr[0].trim();
				createDateEnd = dateArr[1].trim();
			}
		}
		
		return new String[]{createDateStart, createDateEnd};
	}
	
	public Map<String, Object> getQueryParam() {
		String[] dateArr = getDateBetween();
		
		Map<String, Object> params = new HashMap<>();
		params.put("orderNo", this.orderNo);
		if(dateArr[0] != null){
			params.put("createDateStart", DateUtil.getDate(dateArr[0], "yyyy-MM-dd"));
		}
		if(dateArr[1] != null){
			params.put("createDateEnd", DateUtil.getDate(dateArr[1], "yyyy-MM-dd"));
		}
		
		params.put("orderStatus", this.orderStatus);
		params.put("tradeType", this.tradeType);
		params.put("tradeCategory", this.tradeCategory);
		params.put("keyword", this.keyword);
		params.put("sellerCustomerId", this.sellerCustomerId);
		params.put("buyerCustomerId", this.buyerCustomerId);
		params.put("allCustomerId", this.allCustomerId);
		params.put("index", this.getIndex());
		
		return params;
	}
	
	@Override
	public Map<String, String> getParameters() {
		Map<String, String> params = new HashMap<>();
		params.put("orderNo", this.orderNo);
		params.put("createDateBetween", this.createDateBetween);
		params.put("orderStatus", this.orderStatus);
		params.put("tradeType", this.tradeType);
		params.put("tradeCategory", this.tradeCategory);
		params.put("keyword", this.keyword);
		params.put("sellerCustomerId", this.sellerCustomerId);
		params.put("buyerCustomerId", this.buyerCustomerId);
		params.put("allCustomerId", this.allCustomerId);
		params.put("index", String.valueOf(this.getIndex()));
		
		return params;
	}
	
	public SimplePageInfo getPageInfo(){
		SimplePageInfo pageInfo = new SimplePageInfo();
		pageInfo.setPageNum(super.getCurrentPage());
		pageInfo.setPageSize(super.getPageSize());
		
		return pageInfo;
	}

	public Integer getIndex() {
		if(index == null) {
			return 0;
		}
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getCreateDateBetween() {
		return createDateBetween;
	}

	public void setCreateDateBetween(String createDateBetween) {
		this.createDateBetween = createDateBetween;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public String getTradeCategory() {
		return tradeCategory;
	}

	public void setTradeCategory(String tradeCategory) {
		this.tradeCategory = tradeCategory;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getBuyerCustomerId() {
		return buyerCustomerId;
	}

	public String getSellerCustomerId() {
		return sellerCustomerId;
	}

	public void setBuyerCustomerId(String buyerCustomerId) {
		this.buyerCustomerId = buyerCustomerId;
	}

	public void setSellerCustomerId(String sellerCustomerId) {
		this.sellerCustomerId = sellerCustomerId;
	}

	public String getAllCustomerId() {
		return allCustomerId;
	}

	public void setAllCustomerId(String allCustomerId) {
		this.allCustomerId = allCustomerId;
	}
	
}
