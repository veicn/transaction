package com.sinochem.crude.trade.order.service;

import com.sinochem.crude.trade.order.domain.CrudeOilLongTermContractPlan;

import java.util.List;

public interface CrudeOilLongTermContractPlanService {
    /**
     * 根据合约id查询计划信息
     * @param contractId
     * @return
     */
    List<CrudeOilLongTermContractPlan> query(Long contractId);

    Integer selectCountCrude(Long user);

    Integer selectCountProduct(Long user);
}
