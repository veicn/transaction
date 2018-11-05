package com.sinochem.crude.trade.listed.model.vo;

import java.io.Serializable;

/**
 * 用于实时转计价功能的VO
 * @author Yichen Zhao
 * date: 20180203
 */
public class OilModelVO implements Serializable {

    private static final long serialVersionUID = 8349060217076101214L;

    /**
     * 油种名称
     */
    private String oilType;

    /**
     * 年份
     */
    private Integer year;

    /**
     * 月份
     */
    private Integer month;

    /** getters and setters */
    public String getOilType() {
        return oilType;
    }

    public void setOilType(String oilType) {
        this.oilType = oilType;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
}
