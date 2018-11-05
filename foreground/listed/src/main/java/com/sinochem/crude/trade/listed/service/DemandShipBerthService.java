package com.sinochem.crude.trade.listed.service;

import com.sinochem.crude.trade.listed.domain.DemandShipBerth;
import com.sinochem.it.b2b.common.exception.BizException;

import java.util.List;

/**
 * Created by sijliu on 15/11/2017.
 */
public interface DemandShipBerthService {

    /**
     * 保存更新
     * @param record
     */
    Long saveOrUpdateShipBerth (DemandShipBerth record) ;

    /**
     * 按需求id查询
     * @param demandId
     * @return
     */
    List<DemandShipBerth> getShipBerthByDemandId(Long demandId) throws BizException;
    
    /**
     * 保存报价信息时，保存泊位信息
     * 通过demandId（关联的需求Id，而不是报价对应的Id）查询出shipBerth，然后遍历该集合，通过shipBerthIds判断是否包含遍历的berth的Id来进行属性拷贝然后保存新的shipBerth
     * @param newDemandId
     * @param shipBerthIds 泊位ID，以逗号分割
     */
    void copySaveShipBerthByDemandIdAndBerthIds(Long demandId, Long newDemandId, String shipBerthIds) throws BizException;
}
