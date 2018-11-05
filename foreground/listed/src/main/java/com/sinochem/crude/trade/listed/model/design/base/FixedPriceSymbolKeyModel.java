package com.sinochem.crude.trade.listed.model.design.base;

import java.util.Calendar;

/**
 * 固定价格基类
 * @author Yichen Zhao
 * date: 20180204
 */
public class FixedPriceSymbolKeyModel extends SymbolKeyModel {

    private String oilType;

    private Calendar calendar;

    @Override
    public int hashCode() {
        return (((oilType + calendar.get(Calendar.YEAR)) + calendar.get(Calendar.MONTH))).hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }

        if (!(other instanceof FixedPriceSymbolKeyModel)) {
            return false;
        }

        return this.hashCode() == other.hashCode();
    }

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

    public String getOilType() {
        return oilType;
    }

    public void setOilType(String oilType) {
        this.oilType = oilType;
    }
}
