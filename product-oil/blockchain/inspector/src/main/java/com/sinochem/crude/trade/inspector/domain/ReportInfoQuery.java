package com.sinochem.crude.trade.inspector.domain;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

/**
 * 商检报告
 * @author wdh
 * @date 2018/09/7
 */
public class ReportInfoQuery extends ReportInfo {

    //商检报告编号
    private String billno;

    @JsonUnwrapped
    private SimplePageInfo pageInfo;

    public SimplePageInfo getPageInfo() {
        return pageInfo;
    }
    public void setPageInfo(SimplePageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public String getBillno() {
        return billno;
    }

    public void setBillno(String billno) {
        this.billno = billno;
    }
}
