package com.sinochem.crude.trade.listed.model.form;

import java.io.Serializable;

public class AppBiddingDetailedListForm implements Serializable {
	// 查询条件
    /**
     * 父节点id
     */
	private Long demandId;
	
	// 用户信息
	private Long epMemberId;
	// 分页信息
	private int pageNum;
	private int pageSize;

	public Long getDemandId() {
		return demandId;
	}

	public void setDemandId(Long demandId) {
		this.demandId = demandId;
	}

	public Long getEpMemberId() {
		return epMemberId;
	}

	public void setEpMemberId(Long epMemberId) {
		this.epMemberId = epMemberId;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

}
