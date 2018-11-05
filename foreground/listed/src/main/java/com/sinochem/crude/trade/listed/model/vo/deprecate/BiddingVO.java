package com.sinochem.crude.trade.listed.model.vo.deprecate;

import java.io.Serializable;

/**
 * Created by wangn on 03/01/2018
 */
@Deprecated
public class BiddingVO implements Serializable {
	
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
     * 状态
     */
    private String status;

	/**
     * 需求单编号
     */
    private String demandCode;
    
    /**
     * 创建时间-开始
     */
    private String createTimeStart;

    /**
     * 创建时间-结束
     */
    private String createTimeEnd;
    
    /**
     * 付款日期
     */
    private Integer payItem;

	public int getPageNum() {
		return pageNum == 0 ? 1 : pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize == 0 ? 20 : pageSize;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getDemandCode() {
		return demandCode;
	}

	public void setDemandCode(String demandCode) {
		this.demandCode = demandCode;
	}

	public String getCreateTimeStart() {
		return createTimeStart;
	}

	public void setCreateTimeStart(String createTimeStart) {
		this.createTimeStart = createTimeStart;
	}

	public String getCreateTimeEnd() {
		return createTimeEnd;
	}

	public void setCreateTimeEnd(String createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}

	public Integer getPayItem() {
		return payItem;
	}

	public void setPayItem(Integer payItem) {
		this.payItem = payItem;
	}
}
