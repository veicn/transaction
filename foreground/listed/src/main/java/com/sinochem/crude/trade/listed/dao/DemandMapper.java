package com.sinochem.crude.trade.listed.dao;

import com.sinochem.crude.trade.listed.domain.Demand;

import java.util.List;

public interface DemandMapper {

    /**
     * 按主键删除
     * @param id　
     *
     */
    void deleteByPrimaryKey(Long id);

    /**
     * 插入
     * @param record　
     * @return
     */
    Long insert(Demand record);

    /**
     * 插入
     * @param record
     * @return
     */
    Long insertSelective(Demand record);

    /**
     * 按主键查询
     * @param id
     * @return
     */
    Demand selectByPrimaryKey(Long id);

    /**
     * 按主键查询并加锁
     * @param id
     * @return
     */
    Demand selectByPrimaryKeyLock(Long id);
    
    /**
     * 按主键查询
     * @return
     */
    List<Demand> getDemandsByIds(List<Long> ids);

    /**
     * 按主键更新
     * @param record
     *
     */
    void updateByPrimaryKeySelective(Demand record);

    /**
     * 按主键更新
     * @param record
     *
     */
    void updateByPrimaryKey(Demand record);

    /**
     *
     * @param parentId
     * @return
     */
    List<Demand> selectDemandsByParentId(Long parentId);

    /**
     * 按uuid查询
     * @param uuid
     * @return
     */
    Demand selectByUuid(String uuid);


    /**
     * 查询所有发布信息条数
     * @return
     */
    Long getDemandCount();
    
    /**
     * 判断当前报价单是否已结标
     * @param demandId 报价单id
     * @return 1已结标 0未结标
     */
    Integer checkStopBid(Long demandId);
}