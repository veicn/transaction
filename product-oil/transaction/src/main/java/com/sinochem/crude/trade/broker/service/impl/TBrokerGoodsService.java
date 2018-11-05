package com.sinochem.crude.trade.broker.service.impl;

import com.sinochem.crude.trade.blockchain.domain.TBrokerGoods;
import com.sinochem.crude.trade.broker.model.vo.TBrokerVO;

import java.util.List;

/**
 * @Author: fengzk
 * @CreateDate: 2018/10/15 18:04
 * @Version: [v1.0]
 */
public interface TBrokerGoodsService {
    List<TBrokerGoods> selectByUuid(String uuid);
    int deleteByUuid(String uuid);
    int insertList(List<TBrokerGoods> list);
    int updateByUuid(List<TBrokerGoods> list);
    int updateByUuid(TBrokerVO tBrokerVO);
}
