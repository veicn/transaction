package com.sinochem.crude.trade.order.service;

import com.sinochem.crude.trade.order.domain.ContractRelevanter;
import com.sinochem.it.b2b.common.exception.BizException;

public interface ContractRelevanterService {

    /**
     * 查询联系人
     * @param contractId
     * @param type
     * @return
     */
    ContractRelevanter query(Long contractId, String type);
}
