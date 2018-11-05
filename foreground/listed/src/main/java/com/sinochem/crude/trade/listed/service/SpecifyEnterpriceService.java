package com.sinochem.crude.trade.listed.service;

import com.sinochem.crude.trade.listed.domain.DemandSpecifyEnterprise;

import java.util.List;

/**
 * 需求定向企业信息  操作服务类
 * Created by sijliu on 18/01/2018.
 */
public interface SpecifyEnterpriceService {

    /**
     * 根据需求id获取其对应的定向企业信息
     * @param demandId
     * @return
     */
    List<DemandSpecifyEnterprise> getDemandSpecifyEnterpriseListByDemandId(Long demandId);
}
