package com.sinochem.crude.trade.customs.model.query;

import com.sinochem.crude.trade.customs.domain.po.BlockchainInfoVO;
import com.sinochem.crude.trade.transaction.domain.SimplePageInfo;
import org.codehaus.jackson.annotate.JsonUnwrapped;

/**
 * 商检报告
 * @author wdh
 * @date 2018/09/7
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
