package com.sinochem.crude.trade.info.query;

public class CrudeOfficialQuery {

	private String crudeNameE;
	
	private String startDate;
	
	private Integer pageNum;
	
	private Integer pageSize;

	public Integer getPageNum() {
		return pageNum;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
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
