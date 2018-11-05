package com.sinochem.crude.trade.info.query;

public class FractionQuery {
	/**原油名称(英文)*/
	private String crudeNameE;
	private Integer pageNum;
	private Integer pageSize;

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getCrudeNameE() {
		return crudeNameE;
	}

	public void setCrudeNameE(String crudeNameE) {
		this.crudeNameE = crudeNameE;
	}

}
