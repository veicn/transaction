package com.sinochem.crude.trade.info.model;

import com.sinochem.crude.trade.info.domain.ColColumn;

public class ColColumnVO extends ColColumn {

	private static final long serialVersionUID = 1L;	
	
	private Integer pageNum;
    private Integer pageSize;
    private Integer pageCount;
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
	public Integer getPageCount() {
		return pageCount;
	}
	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}
    
	
}