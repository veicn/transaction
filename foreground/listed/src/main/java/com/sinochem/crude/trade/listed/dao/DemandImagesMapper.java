package com.sinochem.crude.trade.listed.dao;

import com.sinochem.crude.trade.listed.domain.DemandImages;

import java.util.List;

public interface DemandImagesMapper {

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
    Long insert(DemandImages record);

    /**
     * 插入
     * @param record
     * @return
     */
    Long insertSelective(DemandImages record);

    /**
     * 按主键查询
     * @param id
     * @return
     */
    DemandImages selectByPrimaryKey(Long id);

    /**
     * 按主键更新
     * @param record
     *
     */
    void updateByPrimaryKeySelective(DemandImages record);

    /**
     * 按主键更新
     * @param record
     *
     */
    void updateByPrimaryKey(DemandImages record);

    /**
     * 根据需求，查询该需求的所有图片
     * @param demandId
     * @return
     */
    List<DemandImages> selectByDemandId(Long demandId);

    /**
     * 根据demandid 删除 图片信息
     * @param demandId
     */
    void deleteByDemandId(Long demandId);
}