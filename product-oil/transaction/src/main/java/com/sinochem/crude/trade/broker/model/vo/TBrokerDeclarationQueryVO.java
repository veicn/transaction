package com.sinochem.crude.trade.broker.model.vo;


import com.sinochem.crude.trade.blockchain.domain.TBrokerDeclaration;

/**
 * @Author: fengzk
 * @CreateDate: 2018/9/10 13:39
 * @Version: [v1.0]
 */
public class TBrokerDeclarationQueryVO extends TBrokerDeclaration {
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
