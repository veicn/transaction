package com.sinochem.crude.trade.listed.dao;

import com.sinochem.crude.trade.listed.domain.DemandShipBerth;

import java.util.List;

public interface DemandShipBerthMapper {

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
    Long insert(DemandShipBerth record);

    /**
     * 插入
     * @param record
     * @return
     */
    Long insertSelective(DemandShipBerth record);

    /**
     * 按主键查询
     * @param id
     * @return
     */
    DemandShipBerth selectByPrimaryKey(Long id);

    /**
     * 按主键更新
     * @param record
     *
     */
    void updateByPrimaryKeySelective(DemandShipBerth record);

    /**
     * 按主键更新
     * @param record
     *
     */
    void updateByPrimaryKey(DemandShipBerth record);

    /**
     * 查询采购需求的所有泊位信息
     * @param demandId
     * @return
     */
    List<DemandShipBerth> selectByDemandId(Long demandId);

    /**
     * 根据需求id删除泊位信息
     * @param demandId
     */
    void deleteByDemandId(Long demandId);
}