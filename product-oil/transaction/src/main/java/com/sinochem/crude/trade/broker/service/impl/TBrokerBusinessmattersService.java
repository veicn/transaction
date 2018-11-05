package com.sinochem.crude.trade.broker.service.impl;

import com.sinochem.crude.trade.blockchain.domain.TBrokerBusinessmatters;
import com.sinochem.crude.trade.broker.model.vo.TBrokerVO;

import java.util.List;

/**
 * @Author: fengzk
 * @CreateDate: 2018/9/10 15:04
 * @Version: [v1.0]
 */
public interface TBrokerBusinessmattersService {
    List<TBrokerBusinessmatters> selectByUuid(String uuid);
    int insertList(List<TBrokerBusinessmatters> list);
    int deleteByUuid(String uuid);
    int updateByUuid(List<TBrokerBusinessmatters> list);
    int updateByUuid(TBrokerVO tBrokerVO);
}
