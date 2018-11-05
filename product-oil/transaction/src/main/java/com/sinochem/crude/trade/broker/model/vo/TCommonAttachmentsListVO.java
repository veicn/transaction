package com.sinochem.crude.trade.broker.model.vo;

import com.sinochem.crude.trade.blockchain.domain.TCommonAttachments;

import java.util.List;

/**
 * @Author: fengzk
 * @CreateDate: 2018/9/14 14:12
 * @Version: [v1.0]
 */
public class TCommonAttachmentsListVO {
    private List<TCommonAttachments> list;

    public List<TCommonAttachments> getList() {
        return list;
    }

    public void setList(List<TCommonAttachments> list) {
        this.list = list;
    }
}
