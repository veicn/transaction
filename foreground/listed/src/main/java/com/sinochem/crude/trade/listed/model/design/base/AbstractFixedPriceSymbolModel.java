package com.sinochem.crude.trade.listed.model.design.base;

import java.util.Calendar;

/**
 * 固定月价合约基类
 * @author Yichen Zhao
 * date: 20180204
 */
public abstract class AbstractFixedPriceSymbolModel extends SymbolModel {

    private Calendar calendar;

    public abstract String getOilType();

    @Override
    public String toString() {
        return getOilType()
                + "_"
                + calendar.get(Calendar.YEAR)
                + "_"
                + calendar.get(Calendar.MONTH);
    }

    /** getters and setters */
    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }
}
