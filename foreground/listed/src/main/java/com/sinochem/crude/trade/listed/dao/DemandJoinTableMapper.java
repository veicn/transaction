package com.sinochem.crude.trade.listed.dao;

import com.sinochem.crude.trade.listed.model.query.CrudeOilDemandQuery;
import com.sinochem.crude.trade.listed.model.result.DemandJoinResult;
import com.sinochem.crude.trade.listed.model.query.ResourceQuery;

import java.util.List;

/**
 * Created by sijliu on 28/11/2017.
 */
public interface DemandJoinTableMapper {

    /**
     * 条件联合查询需求单
     * @param resourceQuery
     * @return
     */
    List<DemandJoinResult> selectDemandByCondition(ResourceQuery resourceQuery);
    
    /**
     * 条件联合查询需求单（无效的截止时间）
     * @param resourceQuery
     * @return
     */
    List<DemandJoinResult> selectInvalidDemandByCondition(ResourceQuery resourceQuery);

    /**
     * 条件联合查询报价单单
     * @param resourceQuery
     * @return
     */
    List<DemandJoinResult> selectBiddingByCondition(ResourceQuery resourceQuery);

    /**
     * 查询5条报价信息
     * @param resourceQuery
     * @return
     */
    List<DemandJoinResult> selectBiddingFiveByCondition(ResourceQuery resourceQuery);

    /**
     * 查询我的需求单总数
     * @param resourceQuery
     * @return
     */
    Long countDemandNumByCondition(ResourceQuery resourceQuery);

    /**
     * 根据给定的demandId集合，按原油对比数据项查询
     * added by Yichen Zhao on 20180110
     * @param demandIds
     * @return
     */
    List<DemandJoinResult> getCrudeOilCompareByIds(List<Long> demandIds);

    /**
     * 根据给定条件查询原油采购单
     * added by Yichen Zhao on 20180112
     * @param crudeOilDemandQuery
     * @return
     */
    List<DemandJoinResult> queryCrudeOilDemandByCondition(CrudeOilDemandQuery crudeOilDemandQuery);
    /**
     * 根据给定条件查询原油需求单或资源单数量
     * @return
     */
    Long countDemandByUserEpId(ResourceQuery resourceQuery);
}
