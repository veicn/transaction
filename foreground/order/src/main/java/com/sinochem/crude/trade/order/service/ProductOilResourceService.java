package com.sinochem.crude.trade.order.service;

import com.sinochem.crude.trade.order.domain.ProductOilResource;

/**
 * @Description:
 * @Author : chenyz
 * @Date: 2017/12/19
 */
public interface ProductOilResourceService {

    ProductOilResource queryProductOilResource(Long contractId);
}
