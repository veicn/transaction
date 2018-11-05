package com.sinochem.crude.trade.broker.service.impl;

import com.sinochem.crude.trade.blockchain.domain.TBrokerDangerinfo;
import com.sinochem.crude.trade.broker.model.vo.TBrokerVO;

/**
 * @Author: fengzk
 * @CreateDate: 2018/9/10 15:04
 * @Version: [v1.0]
 */
public interface TBrokeDangerinfoService {
    TBrokerDangerinfo selectByUuid(String uuid);
    int insertSelective(TBrokerDangerinfo record);
    int deleteByUuid(String uuid);

    int updateByUuid(TBrokerDangerinfo record);
    int updateByUuid(TBrokerVO tBrokerVO);
}
