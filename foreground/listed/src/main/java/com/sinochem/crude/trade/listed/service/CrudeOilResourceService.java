package com.sinochem.crude.trade.listed.service;

import com.sinochem.crude.trade.listed.domain.CrudeOil;

import java.util.List;

/**
 * Created by sijliu on 23/11/2017.
 */
public interface CrudeOilResourceService {

    /**
     * 保存或更新
     * @param record
     * @return
     */
    int saveOrUpdateObj(CrudeOil record);

    /**
     * demandId查询油种
     * @param demandId
     * @return
     */
    List<CrudeOil> getCrudeListDemandId(Long demandId);

    /**
     * 根据需求id删除数据
     * @param demandId
     */
    void deleteByDemandId(Long demandId);

}
