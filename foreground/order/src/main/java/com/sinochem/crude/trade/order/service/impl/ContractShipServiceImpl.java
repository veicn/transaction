package com.sinochem.crude.trade.order.service.impl;

import com.sinochem.crude.trade.order.dao.ContractShipMapper;
import com.sinochem.crude.trade.order.domain.ContractShip;
import com.sinochem.crude.trade.order.service.ContractShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContractShipServiceImpl implements ContractShipService {

    @Autowired
    ContractShipMapper contractShipMapper;
    @Override
    public ContractShip queryByContractId(Long contractId) {

        ContractShip contractShip=contractShipMapper.queryByContractId(contractId);
        return contractShip;
    }
}
