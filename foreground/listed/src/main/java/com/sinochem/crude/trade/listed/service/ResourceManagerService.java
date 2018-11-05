package com.sinochem.crude.trade.listed.service;

import java.util.List;

import com.sinochem.crude.trade.listed.domain.Demand;
import com.sinochem.crude.trade.listed.domain.DemandRelevanter;
import com.sinochem.crude.trade.listed.domain.DemandShip;
import com.sinochem.crude.trade.listed.domain.DemandShipBerth;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.it.b2b.common.exception.BizException;

/**
 * Created by sijliu on 20/11/2017.
 */
public interface ResourceManagerService {


    /**
     * 批量修改状态
     * @param ids
     * @param status
     */
    void batchUpdateStatus(String ids, int status) throws BizException;

    /**
     * 获取demand
     * @param demandId
     * @return
     */
    Demand getDemandByKey(Long demandId);

    /**
     * 批量查看状态
     * @param ids
     * @param status
     */
    String batchUpdateStatused(String ids, int status) throws BizException;

}
