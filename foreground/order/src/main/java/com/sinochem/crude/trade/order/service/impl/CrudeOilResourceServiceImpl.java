package com.sinochem.crude.trade.order.service.impl;

import com.sinochem.crude.trade.order.dao.CrudeOilResourceMapper;
import com.sinochem.crude.trade.order.domain.CrudeOilResource;
import com.sinochem.crude.trade.order.service.CrudeOilResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author : chenyz
 * @Date: 2017/11/29
 */
@Service
public class CrudeOilResourceServiceImpl implements CrudeOilResourceService {
    @Autowired
    private CrudeOilResourceMapper crudeOilResourceMapper;

    @Override
    public CrudeOilResource queryByContractId(Long contractId) {
        return crudeOilResourceMapper.selectByPrimaryKey(contractId);
    }

    @Override
    public CrudeOilResource selectById(Long id) {
        return crudeOilResourceMapper.selectById(id);
    }
}
