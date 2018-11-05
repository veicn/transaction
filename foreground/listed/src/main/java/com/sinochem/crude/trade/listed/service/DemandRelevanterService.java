package com.sinochem.crude.trade.listed.service;

import com.sinochem.crude.trade.listed.domain.DemandRelevanter;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.it.b2b.common.exception.BizException;

import java.util.List;

/**
 * Created by sijliu on 15/11/2017.
 */
public interface DemandRelevanterService {
    /**
     * 保存更新
     * @param recode
     */
    Long saveOrUpdateRelevanter(DemandRelevanter recode);

    /**
     * 按主键查询
     * @param id
     * @return
     */
    public DemandRelevanter getDemandRelevanterByKey(Long id);


    /**
     * 按需求查询
     * @param id
     * @return
     */
    List<DemandRelevanter> getDemandRelevantersByDemandId(Long id);

    /**
     * 查询买家信息联系人信息
     * @param demandId
     * @param i
     * @return
     */
    DemandRelevanter getDemandRelevanterByDemandIdAndTypeOne(Long demandId, String i);

    /**
     * 查询代理商信息
     * @param demandId
     * @param j
     * @return
     */
    DemandRelevanter getDemandRelevanterByDemandIdAndTypeTwo(Long demandId, String j) throws BizException;

    void copySaveRelevanterByDemandId(Long demandId, Long newDemandId,DemandRelevanter buyer);
}
