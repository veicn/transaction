package com.sinochem.crude.trade.listed.model.vo.deprecate;

import java.io.Serializable;

/**
 * Created by wangn on 03/01/2018
 */
@Deprecated
public class BuyLeadsDemandVO implements Serializable{
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
     * 发布时间-开始
     */
    public String pubDateStart;

    /**
     * 发布时间-结束
     */
    public String pubDateEnd;
	
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

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDemandCode() {
		return demandCode;
	}

	public void setDemandCode(String demandCode) {
		this.demandCode = demandCode;
	}

	public String getPubDateStart() {
		return pubDateStart;
	}

	public void setPubDateStart(String pubDateStart) {
		this.pubDateStart = pubDateStart;
	}

	public String getPubDateEnd() {
		return pubDateEnd;
	}

	public void setPubDateEnd(String pubDateEnd) {
		this.pubDateEnd = pubDateEnd;
	}

	public Integer getPayItem() {
		return payItem;
	}

	public void setPayItem(Integer payItem) {
		this.payItem = payItem;
	}
    
}
