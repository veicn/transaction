package com.sinochem.crude.trade.listed.model.design.base;

import java.util.Calendar;
import java.util.TreeSet;

/**
 * 油种差价合约的键
 * @author Yichen Zhao
 * date: 20180204
 */
public class DifferenceSymbolKeyModel extends SymbolKeyModel {

    private TreeSet<String> oilTypePair;

    private Calendar calendar;

    @Override
    public int hashCode() {
        String hashCodeString = "";

        for (String oilType : oilTypePair) {
            hashCodeString += oilType;
        }

        return (((hashCodeString + calendar.get(Calendar.YEAR)) + calendar.get(Calendar.MONTH))).hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }

        if (!(other instanceof DifferenceSymbolKeyModel)) {
            return false;
        }

        return this.hashCode() == other.hashCode();
    }

    @Override
    public String toString() {
        String oilPairString = "";
        for (String oilType : oilTypePair) {
            oilPairString = oilPairString + oilType + "_";
        }

        return oilPairString
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

    public TreeSet<String> getOilTypePair() {
        return oilTypePair;
    }

    public void setOilTypePair(TreeSet<String> oilTypePair) {
        this.oilTypePair = oilTypePair;
    }
}
