package com.sinochem.crude.trade.customs.domain;

import com.google.common.base.MoreObjects;

import java.io.Serializable;

public class SimplePageInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer pageNum = Integer.valueOf(1);
	private Integer pageSize = Integer.valueOf(10);
	private Boolean isCount;

	public SimplePageInfo() {
		this.isCount = Boolean.TRUE;
	}

	public Integer getPageNum() {
		return this.pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		return this.pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Boolean getCount() {
		return this.isCount;
	}

	public void setCount(Boolean count) {
		this.isCount = count;
	}

	public String toString() {
		return MoreObjects.toStringHelper(this).add("pageNum", this.pageNum)
				.add("pageSize", this.pageSize).add("isCount", this.isCount)
				.toString();
	}
}