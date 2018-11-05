package com.sinochem.crude.trade.broker.service.impl;

import com.sinochem.crude.trade.blockchain.domain.TBrokerOtherpackinfo;
import com.sinochem.crude.trade.broker.model.vo.TBrokerVO;

import java.util.List;

/**
 * @Author: fengzk
 * @CreateDate: 2018/9/10 15:14
 * @Version: [v1.0]
 */
public interface TBrokerOtherpackinfoService {
    List<TBrokerOtherpackinfo> selectByUuid(String uuid);
    int insertList(List<TBrokerOtherpackinfo> list);
    int deleteByUuid(String uuid);
    int updateByUuid(List<TBrokerOtherpackinfo> list);
    int updateByUuid(TBrokerVO tBrokerVO);
}
