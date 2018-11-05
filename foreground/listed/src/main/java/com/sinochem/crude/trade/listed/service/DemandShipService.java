package com.sinochem.crude.trade.listed.service;

import com.sinochem.crude.trade.listed.domain.DemandShip;
import com.sinochem.it.b2b.common.exception.BizException;

import java.util.List;

/**
 * Created by sijliu on 15/11/2017.
 */
public interface DemandShipService {
    /**
     * 保存更新
     * @param record
     */
    Long saveOrUpdateDemandShip(DemandShip record);

    /**
     * 按需求id查询
     * @param demandId
     * @return
     */
    List<DemandShip> getShipByDemandId (Long demandId) throws BizException;

    void deleteByDemandId(Long id);
    
    /**
     * 按主键更新-报价单修改用
     * @param record
     *
     */
    void updateBiddingDemandShip(DemandShip record);
}
