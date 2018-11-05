package com.sinochem.crude.trade.listed.service;

import java.util.List;

import com.sinochem.crude.trade.listed.domain.DemandBiddingHistory;

/**
 * Created by sijliu on 15/11/2017.
 */
public interface DemandBiddingHistoryService {
    /**
     * 保存更新
     * @param record
     */
    Long insert(DemandBiddingHistory record);
    
    /**
     * 按需求id查询
     * @param demandId
     * @return
     */
    List<DemandBiddingHistory> getDetailByDemandId(Long demandId);
}
