package com.sinochem.crude.trade.listed.model.design.base;

import java.util.Calendar;
import java.util.TreeSet;

/**
 * 月差价合约的键的基类
 * @author Yichen Zhao
 * date: 20180204
 */
public class VariationalSymbolKeyModel extends SymbolKeyModel {

    private String oilType;

    private TreeSet<Calendar> calendarPair;

    @Override
    public int hashCode() {
        String hashCodeString = "";
        for (Calendar calendar : calendarPair) {
            hashCodeString += calendar.get(Calendar.YEAR);
            hashCodeString += calendar.get(Calendar.MONTH);
        }

        return (oilType + hashCodeString).hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }

        if (!(other instanceof VariationalSymbolKeyModel)) {
            return false;
        }

        return this.hashCode() == other.hashCode();
    }

    @Override
    public String toString() {
        String calendarPairString = "";
        for (Calendar calendar : calendarPair) {
            calendarPairString = calendarPairString + "_" + calendar.get(Calendar.YEAR) + "_" + calendar.get(Calendar.MONTH);
        }

        return getOilType() + calendarPairString;
    }

    /** getters and setters */
    public String getOilType() {
        return oilType;
    }

    public void setOilType(String oilType) {
        this.oilType = oilType;
    }

    public TreeSet<Calendar> getCalendarPair() {
        return calendarPair;
    }

    public void setCalendarPair(TreeSet<Calendar> calendarPair) {
        this.calendarPair = calendarPair;
    }
}
