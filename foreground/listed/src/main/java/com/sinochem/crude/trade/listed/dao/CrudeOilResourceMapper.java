package com.sinochem.crude.trade.listed.dao;


import com.sinochem.crude.trade.listed.domain.CrudeOil;

import java.util.List;

public interface CrudeOilResourceMapper {

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
    Long insert(CrudeOil record);

    /**
     * 选择性插入
     * @param record
     * @return
     */
    Long insertSelective(CrudeOil record);

    /**
     * 按主键查询
     * @param id
     * @return
     */
    CrudeOil selectByPrimaryKey(Long id);

    /**
     * 按主键选择性更新
     * @param record
     *
     */
    void updateByPrimaryKeySelective(CrudeOil record);

    /**
     * 按主键全更新
     * @param record
     *
     */
    void updateByPrimaryKey(CrudeOil record);

    /**
     * 按demandId查询
     * @param demandId
     * @return
     */
    List<CrudeOil> getCrudeOilListByDemandId(Long demandId);

    /**
     * 按demandId删除数据
     * @param demandId
     */
    void deleteByDemandId(Long demandId);
}