package com.sinochem.crude.trade.listed.dao;

import com.sinochem.crude.trade.listed.domain.DemandShip;

import java.util.List;

public interface DemandShipMapper {

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
    Long insert(DemandShip record);

    /**
     * 插入
     * @param record
     * @return
     */
    Long insertSelective(DemandShip record);

    /**
     * 按主键查询
     * @param id
     * @return
     */
    DemandShip selectByPrimaryKey(Long id);

    /**
     * 按主键更新
     * @param record
     *
     */
    void updateByPrimaryKeySelective(DemandShip record);

    /**
     * 按主键更新
     * @param record
     *
     */
    void updateByPrimaryKey(DemandShip record);

    /**
     * 查询当前需求的运输信息
     * @param demandId
     * @return
     */
    List<DemandShip> selectByDemandId(Long demandId);

    /**
     * 删除需求船务
     * @param demandId
     *
     */
    void deleteByDemandId(Long demandId);
    
    /**
     * 按主键更新-报价单修改用
     * @param record
     *
     */
    void updateBiddingDemandShip(DemandShip record);
}