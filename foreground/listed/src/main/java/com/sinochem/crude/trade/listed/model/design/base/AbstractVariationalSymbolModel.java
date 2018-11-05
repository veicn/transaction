package com.sinochem.crude.trade.listed.model.design.base;

import java.util.Calendar;

/**
 * 月差价基类
 * @author Yichen Zhao
 * date: 20180204
 */
public abstract class AbstractVariationalSymbolModel extends SymbolModel {

    private Calendar startCalendar;
    private Calendar endCalendar;

    public abstract boolean ordered(OilModel origin, OilModel target) throws Exception;

    public abstract String getOilType();

    @Override
    public String toString() {
        return getOilType()
                + "_"
                + startCalendar.get(Calendar.YEAR)
                + "_"
                + startCalendar.get(Calendar.MONTH)
                + "_"
                + endCalendar.get(Calendar.YEAR)
                + "_"
                + endCalendar.get(Calendar.MONTH);
    }

    /** getters and setters */
    public Calendar getStartCalendar() {
        return startCalendar;
    }

    public void setStartCalendar(Calendar startCalendar) {
        this.startCalendar = startCalendar;
    }

    public Calendar getEndCalendar() {
        return endCalendar;
    }

    public void setEndCalendar(Calendar endCalendar) {
        this.endCalendar = endCalendar;
    }
}
