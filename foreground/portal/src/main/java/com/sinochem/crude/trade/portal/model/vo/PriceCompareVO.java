package com.sinochem.crude.trade.portal.model.vo;

import java.io.Serializable;

/**
 * 用作期现比价的VO
 * @author Yichen Zhao
 * date: 20180423
 */
public class PriceCompareVO implements Serializable {

    private String currencyCode;

    private String futuresYearMonthPair;

    private String futuresPrice;

    private String spotPrice;

    /** getters and setters */
    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getFuturesYearMonthPair() {
        return futuresYearMonthPair;
    }

    public void setFuturesYearMonthPair(String futuresYearMonthPair) {
        this.futuresYearMonthPair = futuresYearMonthPair;
    }

    public String getFuturesPrice() {
        return futuresPrice;
    }

    public void setFuturesPrice(String futuresPrice) {
        this.futuresPrice = futuresPrice;
    }

    public String getSpotPrice() {
        return spotPrice;
    }

    public void setSpotPrice(String spotPrice) {
        this.spotPrice = spotPrice;
    }
}
