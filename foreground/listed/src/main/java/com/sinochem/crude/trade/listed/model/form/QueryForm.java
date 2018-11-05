package com.sinochem.crude.trade.listed.model.form;


/**
 * 需求筛选条件
 */
public class QueryForm {
    /**
     * 需求编号
     */
    private String demandCode;

    /**
     * 发布时间始
     */
    private String pubDateStart;

    /**
     * 发布结束时间
     */
    private String pubDateEnd;

    /**
     * 创建时间始
     */
    private String createTimeStart;

    /**
     * 创建结束时间
     */
    private String createTimeEnd;
    /**
     * 付款日期
     */
    private String payItem;

    /**
     * 付款日期JSON串
     */
    private String payItemJSON;

    public String getPayItemJSON() {
        return payItemJSON;
    }

    public void setPayItemJSON(String payItemJSON) {
        this.payItemJSON = payItemJSON;
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

	public String getPayItem() {
        return payItem;
    }

    public void setPayItem(String payItem) {
        this.payItem = payItem;
    }
}
