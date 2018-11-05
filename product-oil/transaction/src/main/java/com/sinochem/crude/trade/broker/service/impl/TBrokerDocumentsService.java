package com.sinochem.crude.trade.broker.service.impl;

import com.sinochem.crude.trade.blockchain.domain.TBrokerDocuments;
import com.sinochem.crude.trade.broker.model.vo.TBrokerVO;

import java.util.List;

/**
 * @Author: fengzk
 * @CreateDate: 2018/9/10 15:10
 * @Version: [v1.0]
 */
public interface TBrokerDocumentsService {
    List<TBrokerDocuments> selectByUuid(String uuid);
    int deleteByUuid(String uuid);
    int insertList(List<TBrokerDocuments> list);
    int updateByUuid(List<TBrokerDocuments> list);
    int updateByUuid(TBrokerVO tBrokerVO);
}
