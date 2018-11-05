package com.sinochem.crude.trade.order.service.impl;



import com.sinochem.crude.trade.order.dao.ContractMapper;
import com.sinochem.crude.trade.order.dao.CrudeOilLongTermContractPlanMapper;
import com.sinochem.crude.trade.order.domain.CrudeOilLongTermContractPlan;
import com.sinochem.crude.trade.order.service.CrudeOilLongTermContractPlanService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class CrudeOilLongTermContractPlanServiceImpl implements CrudeOilLongTermContractPlanService {

    @Autowired
    private CrudeOilLongTermContractPlanMapper crudeOilLongTermContractPlanMapper;

    @Autowired
    private ContractMapper contractMapper;

    /**
     * 根据合约id查询计划信息
     * @param contractId
     * @return
     */
    @Override
    public List<CrudeOilLongTermContractPlan> query(Long contractId) {
        List<CrudeOilLongTermContractPlan> planList = crudeOilLongTermContractPlanMapper.selectByContractId(contractId);
        return planList;
    }

    @Override
    public Integer selectCountCrude(@Param("memberId") Long memberId) {

        return contractMapper.selectCountCrude(memberId);
    }

    @Override
    public Integer selectCountProduct(@Param("memberId") Long memberid) {

        return contractMapper.selectCountProduct(memberid);
    }
}
