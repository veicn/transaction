package com.sinochem.crude.trade.inspector.model.query;

import com.sinochem.crude.trade.inspector.domain.po.ReportInfo;
import com.sinochem.crude.trade.transaction.domain.SimplePageInfo;
import org.codehaus.jackson.annotate.JsonUnwrapped;

import java.util.HashMap;
import java.util.Map;

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


    public Map<String, Object> toMap() {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("billno",this.billno);
        return map;
    }
}
