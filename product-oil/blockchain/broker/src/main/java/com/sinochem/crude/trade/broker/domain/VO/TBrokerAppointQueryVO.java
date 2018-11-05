package com.sinochem.crude.trade.broker.domain.VO;

import com.sinochem.crude.trade.broker.domain.TBrokerAppoint;

/**
 * @Author: fengzk
 * @CreateDate: 2018/9/10 10:32
 * @Version: [v1.0]
 */
public class TBrokerAppointQueryVO extends TBrokerAppoint {
    private Integer pageNum = Integer.valueOf(1);

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    private Integer pageSize = Integer.valueOf(10);
}
