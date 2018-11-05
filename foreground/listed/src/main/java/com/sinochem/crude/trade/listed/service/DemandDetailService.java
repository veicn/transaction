package com.sinochem.crude.trade.listed.service;

import com.sinochem.crude.trade.listed.domain.DemandDetail;

import java.util.List;

/**
 * Created by sijliu on 15/11/2017.
 */
public interface DemandDetailService {

    /**
     * 保存更新
     * @param record
     * @return
     */
    Long saveOrUpdateDetails(DemandDetail record);

    /**
     * 按需求id查询
     * @param demandId
     * @return
     */
    List<DemandDetail> getDetailByDemandId(Long demandId);

    /**
     * 按需求删除
     * @param demandId
     */
    void deleteDemandDetailByDemandId(Long demandId);
}
