package com.sinochem.crude.trade.listed.model.query;

import java.util.Date;

/**
 * 贸易链查询类
 * Made By WangTing
 */
public class TradeChainQuery {
//  企业名称(条件搜索时候用)
    private String enterpriseId;

//  当前登录人代表的企业id
    private Long memberId;
    //  贸易链有效期
    private String tradingChainIndate1;

    private String tradingChainIndate2;

//  贸易链发布时间
    private String dataTime;

    /**
     * 开始时间结束时间
     */
    private Date startTime;
    private Date endTime;

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(String enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getTradingChainIndate1() {
        return tradingChainIndate1;
    }

    public void setTradingChainIndate1(String tradingChainIndate1) {
        this.tradingChainIndate1 = tradingChainIndate1;
    }

    public String getTradingChainIndate2() {
        return tradingChainIndate2;
    }

    public void setTradingChainIndate2(String tradingChainIndate2) {
        this.tradingChainIndate2 = tradingChainIndate2;
    }

    public String getDataTime() {
        return dataTime;
    }

    public void setDataTime(String dataTime) {
        this.dataTime = dataTime;
    }
}
