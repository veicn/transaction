package com.sinochem.crude.trade.info.query;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.github.pagehelper.PageInfo;
import com.sinochem.crude.trade.common.QueryBase;
import com.sinochem.crude.trade.common.SimplePageInfo;

public class DomesticPriceQuery extends QueryBase{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String uuid;
	private String productCode;
	private String productName;
	private String price;
	private String priceUnit;
	private String priceDate;
	private String areaName;
	private String priceSource;
	private String priceTrend;
	private String priceChange;
	private String selProductName;
	
	@JsonUnwrapped
	private SimplePageInfo pageInfo;

	@Override
	public Map<String, String> getParameters() {
		Map<String,String> map = new HashMap<String,String>();
		map.put("id", getId());
		map.put("uuid", getUuid());
		
		return map;
	}
	
	
	
	
	public String getSelProductName() {
		return selProductName;
	}




	public void setSelProductName(String selProductName) {
		this.selProductName = selProductName;
	}




	public String getId() {
		return id;
	}




	public void setId(String id) {
		this.id = id;
	}




	public String getUuid() {
		return uuid;
	}




	public void setUuid(String uuid) {
		this.uuid = uuid;
	}




	public String getProductCode() {
		return productCode;
	}




	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}




	public String getProductName() {
		return productName;
	}




	public void setProductName(String productName) {
		this.productName = productName;
	}




	public String getPrice() {
		return price;
	}




	public void setPrice(String price) {
		this.price = price;
	}




	public String getPriceUnit() {
		return priceUnit;
	}




	public void setPriceUnit(String priceUnit) {
		this.priceUnit = priceUnit;
	}




	public String getPriceDate() {
		return priceDate;
	}




	public void setPriceDate(String priceDate) {
		this.priceDate = priceDate;
	}




	public String getAreaName() {
		return areaName;
	}




	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}




	public String getPriceSource() {
		return priceSource;
	}




	public void setPriceSource(String priceSource) {
		this.priceSource = priceSource;
	}




	public String getPriceTrend() {
		return priceTrend;
	}




	public void setPriceTrend(String priceTrend) {
		this.priceTrend = priceTrend;
	}




	public String getPriceChange() {
		return priceChange;
	}




	public void setPriceChange(String priceChange) {
		this.priceChange = priceChange;
	}




	public SimplePageInfo getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(SimplePageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}

}
