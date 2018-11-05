package com.sinochem.crude.trade.order.domain;

public class ProductOilSaleContract extends Contract {

	public ProductOilSaleContract() {
		super();
		super.setDealType("S");
		super.setBizType("ProductOil");
	}

	private ProductOilResource productOilResource;

	public ProductOilResource getProductOilResource() {
		return productOilResource;
	}

	public void setProductOilResource(ProductOilResource productOilResource) {
		this.productOilResource = productOilResource;
	}
}
