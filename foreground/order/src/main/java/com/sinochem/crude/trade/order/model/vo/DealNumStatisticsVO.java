package com.sinochem.crude.trade.order.model.vo;

import com.sinochem.crude.trade.order.model.result.CrudeStatisticsResult;

import java.util.List;

/**
 * Created by sijliu on 24/02/2018.
 */
public class DealNumStatisticsVO {

    private String legendName;
    private List<CrudeStatisticsResult> list;

    public String getLegendName() {
        return legendName;
    }

    public void setLegendName(String legendName) {
        this.legendName = legendName;
    }

    public List<CrudeStatisticsResult> getList() {
        return list;
    }

    public void setList(List<CrudeStatisticsResult> list) {
        this.list = list;
    }
}
