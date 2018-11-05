package com.sinochem.crude.trade.broker.service.impl;

import com.sinochem.crude.trade.blockchain.domain.TBrokerProductspecification;
import com.sinochem.crude.trade.broker.model.vo.TBrokerVO;

import java.util.List;

/**
 * @Author: fengzk
 * @CreateDate: 2018/9/29 15:25
 * @Version: [v1.0]
 */
public interface TBrokerProductspecificationService {
    List<TBrokerProductspecification> selectByUuid(String uuid);
    int insertList(List<TBrokerProductspecification> list);
    int deleteByUuid(String uuid);
    int updateByUuid(List<TBrokerProductspecification> list);
    int insertSelective(TBrokerProductspecification record);
    int updateByPrimaryKeySelective(TBrokerProductspecification record);
//    TBrokerProductspecification selectByUuid(String uuid);
    int updateByUuid(TBrokerVO tBrokerVO);
}
