package com.sinochem.crude.trade.listed.dao;

import com.sinochem.crude.trade.listed.domain.DemandSpecifyEnterprise;

import java.util.List;

/**
 * Created by sijliu on 17/01/2018.
 */
public interface DemandSpecifyEnterpriseMapper {

    /**
     * 根据需求id删除关联的指定企业
     * @param demandId
     */
    void deleteByDemandId(Long demandId);

    /**
     * 根据需求id查询关联的指定企业信息
     * @param DemandId
     * @return
     */
    List<DemandSpecifyEnterprise> selectObjByDemandId(Long DemandId);

    /**
     * 插入需求-指定企业关联信息
     * @param specify
     * @return
     */
    Long insertSpecifyEnterprise(DemandSpecifyEnterprise specify);
}
