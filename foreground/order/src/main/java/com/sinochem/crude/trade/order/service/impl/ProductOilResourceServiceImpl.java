package com.sinochem.crude.trade.order.service.impl;

import com.sinochem.crude.trade.order.dao.ProductOilResourceMapper;
import com.sinochem.crude.trade.order.domain.ProductOilResource;
import com.sinochem.crude.trade.order.service.ProductOilResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author : chenyz
 * @Date: 2017/12/19
 */
@Service
public class ProductOilResourceServiceImpl implements ProductOilResourceService{

    @Autowired
    private ProductOilResourceMapper productOilResourceMapper;

    @Override
    public ProductOilResource queryProductOilResource(Long contractId) {
        return productOilResourceMapper.selectByPrimaryKey(contractId);
    }
}
