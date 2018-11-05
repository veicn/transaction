package com.sinochem.crude.trade.listed.model.vo.deprecate;

import java.io.Serializable;

/**
 * Created by wangn on 03/01/2018
 */
@Deprecated
public class BiddingDemandVO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/**
     * 页码
     */
	private int pageNum;
	
	/**
     * 翻页大小
     */
	private int pageSize;
	
	/**
     * 需求ID
     */
    private Long demandId;

	public int getPageNum() {
		return pageNum == 0 ? 1 : pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize == 0 ? 20 : pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public Long getDemandId() {
		return demandId;
	}

	public void setDemandId(Long demandId) {
		this.demandId = demandId;
	}
    
}
