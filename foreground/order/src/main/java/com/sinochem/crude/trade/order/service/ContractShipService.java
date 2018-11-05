package com.sinochem.crude.trade.order.service;

import com.sinochem.crude.trade.order.domain.ContractShip;

public interface ContractShipService {


    ContractShip queryByContractId(Long contractId);
}
