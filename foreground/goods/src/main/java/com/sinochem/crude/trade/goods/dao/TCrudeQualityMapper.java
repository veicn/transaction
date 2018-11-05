package com.sinochem.crude.trade.goods.dao;

import java.util.List;

import com.sinochem.crude.trade.goods.model.TCrudeQuality;

public interface TCrudeQualityMapper {
    /**
    * t_crude_quality
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
    * t_crude_quality
     *
     * @mbggenerated
     */
    int insert(TCrudeQuality record);

    /**
    * t_crude_quality
     *
     * @mbggenerated
     */
    int insertSelective(TCrudeQuality record);

    /**
    * t_crude_quality
     *
     * @mbggenerated
     */
    TCrudeQuality selectByPrimaryKey(Long id);

    /**
    * t_crude_quality
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(TCrudeQuality record);

    /**
    * t_crude_quality
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(TCrudeQuality record);
}