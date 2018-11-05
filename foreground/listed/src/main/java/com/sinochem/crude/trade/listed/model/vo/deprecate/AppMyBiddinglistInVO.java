package com.sinochem.crude.trade.listed.model.vo.deprecate;

import java.io.Serializable;

@Deprecated
public class AppMyBiddinglistInVO implements Serializable {
	// 查询条件
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
     * 贸易条款
     */
	private String tradeItem;
	
    /**
     * 状态
     */
	private String status;
	
	// 用户信息
	private Long epMemberId;
	
	// 分页信息
	private int pageNum;
	private int pageSize;

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

	public String getTradeItem() {
		return tradeItem;
	}

	public void setTradeItem(String tradeItem) {
		this.tradeItem = tradeItem;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
