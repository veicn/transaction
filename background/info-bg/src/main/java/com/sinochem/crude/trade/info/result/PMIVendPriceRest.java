package com.sinochem.crude.trade.info.result;

import java.io.Serializable;
import java.math.BigDecimal;

public class PMIVendPriceRest implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/**产品名称*/
	private String productName;
	
	/**产品编号*/
	private String productCode;
	
	/**最高报价*/
	private BigDecimal highPrice;  
	
	/**最低报价*/
	private BigDecimal lowPrice;
	
	/**价格单位*/
	private String priceUnit;  
	
	/**价格日期*/
	private String priceDate;
	
	/**区域名称*/
	private String areaName;  
	
	/**价格来源(如：中石油)*/
	private String priceSource;
	
	private String quotationId;

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public java.math.BigDecimal getHighPrice() {
		return highPrice;
	}

	public void setHighPrice(java.math.BigDecimal highPrice) {
		this.highPrice = highPrice;
	}

	public java.math.BigDecimal getLowPrice() {
		return lowPrice;
	}

	public void setLowPrice(java.math.BigDecimal lowPrice) {
		this.lowPrice = lowPrice;
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

	public String getQuotationId() {
		return quotationId;
	}

	public void setQuotationId(String quotationId) {
		this.quotationId = quotationId;
	}
	
	

}
