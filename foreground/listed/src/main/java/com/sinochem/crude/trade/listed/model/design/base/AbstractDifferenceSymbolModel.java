package com.sinochem.crude.trade.listed.model.design.base;

import java.util.Calendar;
import java.util.Set;

/**
 * 油种差价合约的基类
 * @author Yichen Zhao
 * date: 20180204
 */
public abstract class AbstractDifferenceSymbolModel extends SymbolModel {

    private Calendar calendar;

    public abstract boolean ordered(OilModel origin, OilModel target) throws Exception;

    public abstract Set<String> getOilTypePair();

    @Override
    public String toString() {
        String oilPairString = "";
        for (String oilType : getOilTypePair()) {
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
}
