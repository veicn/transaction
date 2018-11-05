package com.sinochem.crude.trade.order.model.query;

/**
 * Created by sijliu on 24/02/2018.
 */
public class StatisticsQuery {

    private String bizType;
    private String dealType;
    private String year;
    private Long userId;

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    public String getDealType() {
        return dealType;
    }

    public void setDealType(String dealType) {
        this.dealType = dealType;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
