package com.sinochem.crude.trade.portal.model;

import java.io.Serializable;

/**
 * 模拟的价格对象
 * @author huling02
 *
 */
public class Contract implements Serializable{
    /**
     * 油的品种
     */
    private String oilType;
    /**
     * 年月
     */
    private int year;
    /**
     * 月份
     */
    private int month;
    /**
     * 价格
     */
    private double price;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getOilType() {
        return oilType;
    }

    public void setOil(String oilType) {
        this.oilType = oilType;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonthr() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }
}
