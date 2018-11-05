package com.sinochem.crude.trade.order.service;

import com.sinochem.crude.trade.order.domain.CrudeOilResource;

/**
 * @Description:
 * @Author : chenyz
 * @Date: 2017/11/29
 */
public interface CrudeOilResourceService {

    CrudeOilResource queryByContractId(Long contractId);

    /**
     * 主键查询
     * @param id
     * @return
     */
    CrudeOilResource selectById(Long id);
}
