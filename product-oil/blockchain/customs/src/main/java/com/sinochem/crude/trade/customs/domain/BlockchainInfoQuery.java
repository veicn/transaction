package com.sinochem.crude.trade.customs.domain;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

/**
 * 海关概况查询
 * @author wdh
 * @date 2018/09/25
 */
public class BlockchainInfoQuery extends BlockchainInfoVO {

    @JsonUnwrapped
    private SimplePageInfo pageInfo;

    public SimplePageInfo getPageInfo() {
        return pageInfo;
    }
    public void setPageInfo(SimplePageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }
}
