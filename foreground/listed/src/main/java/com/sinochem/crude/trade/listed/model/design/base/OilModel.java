package com.sinochem.crude.trade.listed.model.design.base;

import java.util.Calendar;

/**
 * 用于实时转计价工具的油种模型
 * @author Yichen Zhao
 * date: 20180201
 */
public class OilModel {

    protected String oilType;
    protected Integer oilCategory;
    protected Calendar calendar;

    public OilModel() {

    }

    public OilModel(String oilType, Integer year, Integer month) {
        this.oilType = oilType;

        if (year != null && month != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, year.intValue());
            calendar.set(Calendar.MONTH, month.intValue());

            this.calendar = calendar;
        }
    }

    public boolean sameType(OilModel other) {
        return this.oilType.equals(other.getOilType());
    }

    @Override
    public int hashCode() {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        return ((oilType + year) + month).hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof OilModel)) {
            return false;
        }

        OilModel otherModel = (OilModel) other;

        return this.getOilType().equals(otherModel.getOilType())
                && this.getCalendar().get(Calendar.YEAR) == otherModel.getCalendar().get(Calendar.YEAR)
                && this.getCalendar().get(Calendar.MONTH) == otherModel.getCalendar().get(Calendar.MONTH);
    }

    /** getters and setters */
    public String getOilType() {
        return oilType;
    }

    public void setOilType(String oilType) {
        this.oilType = oilType;
    }

    public Integer getOilCategory() {
        return oilCategory;
    }

    public void setOilCategory(Integer oilCategory) {
        this.oilCategory = oilCategory;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }
}
