package com.sinochem.crude.trade.order.model.result;

/**
 * Created by sijliu on 24/02/2018.
 */
public class CrudeStatisticsResult {

    private double ymTotalNum;
    private String ym;

    public CrudeStatisticsResult(){}
    public CrudeStatisticsResult(double ymTotalNum, String ym) {
        this.ym = ym;
        this.ymTotalNum = ymTotalNum;
    }

    public double getYmTotalNum() {
        return ymTotalNum;
    }

    public void setYmTotalNum(double ymTotalNum) {
        this.ymTotalNum = ymTotalNum;
    }

    public String getYm() {
        return ym;
    }

    public void setYm(String ym) {
        this.ym = ym;
    }
}
