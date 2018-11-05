package com.sinochem.crude.trade.news.model.query;

/**
 * 商品价格查询条件对象
 * @author Meng Cai
 * date: 20180322
 */
public class ProductInfoQuery {

	/**
	 * 商品编号
	 */
	private String productCode;
	
	/**
	 * 价格日期
	 */
	private String priceDate;
	
	/**
	 * 产品名称
	 */
	private String productName;
	
	/**
	 * 语言类型
	 */
	private String extend10;

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getPriceDate() {
		return priceDate;
	}

	public void setPriceDate(String priceDate) {
		this.priceDate = priceDate;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getExtend10() {
		return extend10;
	}

	public void setExtend10(String extend10) {
		this.extend10 = extend10;
	}
	
}
