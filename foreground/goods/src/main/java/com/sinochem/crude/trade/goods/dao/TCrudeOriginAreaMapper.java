package com.sinochem.crude.trade.goods.dao;

import java.util.List;

import com.sinochem.crude.trade.goods.model.TCrudeOriginArea;
import com.sinochem.crude.trade.goods.remote.CrudeOilOriginAreaResult;

public interface TCrudeOriginAreaMapper {
    /**
    * t_crude_origin_area
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
    * t_crude_origin_area
     *
     * @mbggenerated
     */
    int insert(TCrudeOriginArea record);

    /**
    * t_crude_origin_area
     *
     * @mbggenerated
     */
    int insertSelective(TCrudeOriginArea record);

    /**
    * t_crude_origin_area
     *
     * @mbggenerated
     */
    TCrudeOriginArea selectByPrimaryKey(Long id);

    /**
    * t_crude_origin_area
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(TCrudeOriginArea record);

    /**
    * t_crude_origin_area
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(TCrudeOriginArea record);

    /**
     * 返回所有的原油产地区域信息
     * added by Yichen Zhao on 20180109
     */
    List<CrudeOilOriginAreaResult> getAllCrudeOriginAreas();
}