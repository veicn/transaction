package com.sinochem.crude.trade.news.model.query;

/**
 * 商品code查询条件对象
 * @author Meng Cai
 * date: 20180322
 */
public class ProductCodeQuery {

	/**
	 * 	代码集名称
	 */
	private String itemName;
	
	/**
	 * 语言类型（ZH：中文，EN：英文）
	 */
	private String langVer;

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getLangVer() {
		return langVer;
	}

	public void setLangVer(String langVer) {
		this.langVer = langVer;
	}
	
	
	
}
