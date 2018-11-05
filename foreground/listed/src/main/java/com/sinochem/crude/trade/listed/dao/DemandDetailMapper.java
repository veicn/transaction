package com.sinochem.crude.trade.listed.dao;

import com.sinochem.crude.trade.listed.domain.DemandDetail;

import java.util.List;

public interface DemandDetailMapper {
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
    Long insert(DemandDetail record);

    /**
     * 插入
     * @param record
     * @return
     */
    Long insertSelective(DemandDetail record);

    /**
     * 按主键查询
     * @param id
     * @return
     */
    DemandDetail selectByPrimaryKey(Long id);

    /**
     * 按主键更新
     * @param record
     *
     */
    void updateByPrimaryKeySelective(DemandDetail record);

    /**
     * 按主键更新
     * @param record
     *
     */
    void updateByPrimaryKey(DemandDetail record);

    /**
     * 根据需求demandId，取得详细信息
     * @param demandId
     * @return
     */
    List<DemandDetail> selectByDemandId(Long demandId);

    /**
     * 按需求删除
     * @param id
     *
     */
    void deleteByDemandId(Long id);
}