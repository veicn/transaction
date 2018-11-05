package com.sinochem.crude.trade.broker.service.impl;

import com.sinochem.crude.trade.blockchain.domain.TBrokerOthermatters;
import com.sinochem.crude.trade.broker.model.vo.TBrokerVO;

import java.util.List;

/**
 * @Author: fengzk
 * @CreateDate: 2018/9/10 15:12
 * @Version: [v1.0]
 */
public interface TBrokerOthermattersService {
    List<TBrokerOthermatters> selectByUuid(String uuid);
    int insertList(List<TBrokerOthermatters> list);
    int deleteByUuid(String uuid);
    int updateByUuid(List<TBrokerOthermatters> list);
    int updateByUuid(TBrokerVO tBrokerVO);
}
