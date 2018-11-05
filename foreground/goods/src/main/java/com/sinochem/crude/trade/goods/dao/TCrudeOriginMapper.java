package com.sinochem.crude.trade.goods.dao;

import java.util.List;

import com.sinochem.crude.trade.goods.model.TCrudeOrigin;
import com.sinochem.crude.trade.goods.remote.CrudeOilInfoVO;

public interface TCrudeOriginMapper {
    /**
    * t_crude_origin
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
    * t_crude_origin
     *
     * @mbggenerated
     */
    int insert(TCrudeOrigin record);

    /**
    * t_crude_origin
     *
     * @mbggenerated
     */
    int insertSelective(TCrudeOrigin record);

    /**
    * t_crude_origin
     *
     * @mbggenerated
     */
    TCrudeOrigin selectByPrimaryKey(Long id);

    /**
    * t_crude_origin
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(TCrudeOrigin record);

    /**
    * t_crude_origin
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(TCrudeOrigin record);

    /**
     * 通过ID 查询地区
     * @param areaId
     * @return
     */
    CrudeOilInfoVO queryByAndOrginById(Long areaId);
}