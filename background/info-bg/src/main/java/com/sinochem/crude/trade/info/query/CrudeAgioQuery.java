package com.sinochem.crude.trade.info.query;

public class CrudeAgioQuery {

	private String crudeNameE;
	
	private String startDate;
	
	private String endDate;
	
	/**是否最新(1是0否)*/
	private String latestFlag;
	
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

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getLatestFlag() {
		return latestFlag;
	}

	public void setLatestFlag(String latestFlag) {
		this.latestFlag = latestFlag;
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
