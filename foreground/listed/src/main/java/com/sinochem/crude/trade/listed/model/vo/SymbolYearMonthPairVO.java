package com.sinochem.crude.trade.listed.model.vo;

import java.io.Serializable;

/**
 * 资讯合约的年，月组合
 * @author Yichen Zhao
 * date: 20180204
 */
public class SymbolYearMonthPairVO implements Serializable {

    private Integer year;

    private Integer month;

    private String yearMonthPair;

    /** getters and setters */
    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public String getYearMonthPair() {
        return yearMonthPair;
    }

    public void setYearMonthPair(String yearMonthPair) {
        this.yearMonthPair = yearMonthPair;
    }
}
