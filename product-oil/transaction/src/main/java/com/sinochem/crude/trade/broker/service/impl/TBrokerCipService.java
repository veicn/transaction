package com.sinochem.crude.trade.broker.service.impl;

import com.sinochem.crude.trade.blockchain.domain.TBrokerCip;
import com.sinochem.crude.trade.broker.model.vo.TBrokerVO;

import java.util.List;

/**
 * @Author: fengzk
 * @CreateDate: 2018/9/29 15:23
 * @Version: [v1.0]
 */
public interface TBrokerCipService {
    List<TBrokerCip> selectByUuid(String uuid);
    int insertList(List<TBrokerCip> list);
    int deleteByUuid(String uuid);
    int updateByUuid(List<TBrokerCip> list);
    int updateByUuid(TBrokerVO tBrokerVO);
}
