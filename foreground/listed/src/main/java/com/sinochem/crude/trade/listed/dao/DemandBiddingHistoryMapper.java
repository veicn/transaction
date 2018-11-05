package com.sinochem.crude.trade.listed.dao;

import java.util.List;

import com.sinochem.crude.trade.listed.domain.DemandBiddingHistory;

public interface DemandBiddingHistoryMapper {

    /**
     * 插入
     * @param record
     * @return
     */
    Long insert(DemandBiddingHistory record);
    
    /**
     * 根据需求demandId，取得详细信息
     * @param demandId
     * @return
     */
    List<DemandBiddingHistory> selectByDemandId(Long demandId);
}