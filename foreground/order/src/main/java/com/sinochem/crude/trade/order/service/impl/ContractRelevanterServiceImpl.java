package com.sinochem.crude.trade.order.service.impl;

import com.sinochem.crude.trade.order.dao.ContractRelevanterMapper;
import com.sinochem.crude.trade.order.domain.ContractRelevanter;
import com.sinochem.crude.trade.order.service.ContractRelevanterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContractRelevanterServiceImpl implements ContractRelevanterService {

    @Autowired
    ContractRelevanterMapper contractRelevanterMapper;


    @Override
    public ContractRelevanter query(Long contractId, String type) {
        ContractRelevanter contractRelevanter= new ContractRelevanter();
        contractRelevanter.setContractId(contractId);
        contractRelevanter.setType(type);
        ContractRelevanter ctr = contractRelevanterMapper.selectByCondition(contractRelevanter);
        return ctr;
    }
}
