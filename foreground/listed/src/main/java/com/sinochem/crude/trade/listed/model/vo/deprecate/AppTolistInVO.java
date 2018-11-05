package com.sinochem.crude.trade.listed.model.vo.deprecate;

import java.io.Serializable;

@Deprecated
public class AppTolistInVO implements Serializable {
	// 查询条件
    /**
     * 需求单编号
     */
	private String demandCode;
	
    /**
     * 发布时间-开始
     */
	private String pubDateStart;
	
    /**
     * 发布时间-结束
     */
	private String pubDateEnd;
	
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTradeItem() {
		return tradeItem;
	}

	public void setTradeItem(String tradeItem) {
		this.tradeItem = tradeItem;
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
