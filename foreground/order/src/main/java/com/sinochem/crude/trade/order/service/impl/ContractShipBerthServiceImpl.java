package com.sinochem.crude.trade.order.service.impl;

import com.sinochem.crude.trade.order.dao.ContractShipBerthMapper;
import com.sinochem.crude.trade.order.domain.ContractShipBerth;
import com.sinochem.crude.trade.order.service.ContractShipBerthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Author : chenyz
 * @Date: 2017/11/29
 */
@Service
public class ContractShipBerthServiceImpl implements ContractShipBerthService {
    @Autowired
    private ContractShipBerthMapper contractShipBerthMapper;


    @Override
    public List<ContractShipBerth> queryBycontractId(Long contractId) {
        return contractShipBerthMapper.selectByContractId(contractId);
    }
}
