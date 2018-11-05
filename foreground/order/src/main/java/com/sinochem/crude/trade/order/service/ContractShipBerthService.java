package com.sinochem.crude.trade.order.service;

import com.sinochem.crude.trade.order.domain.ContractShipBerth;

import java.util.List;

/**
 * @Description:
 * @Author : chenyz
 * @Date: 2017/11/29
 */
public interface ContractShipBerthService {

    List<ContractShipBerth> queryBycontractId(Long contractId);
}
