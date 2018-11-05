package com.sinochem.crude.trade.goods.dao;

import java.util.List;

import com.sinochem.crude.trade.goods.model.TCrudeOil;
import com.sinochem.crude.trade.goods.query.TCrudeOilQuery;
import com.sinochem.crude.trade.goods.remote.CrudeOilInfoVO;

public interface TCrudeOilMapper {
    /**
    * t_crude_oil
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
    * t_crude_oil
     *
     * @mbggenerated
     */
    int insert(TCrudeOil record);

    /**
    * t_crude_oil
     *
     * @mbggenerated
     */
    int insertSelective(TCrudeOil record);

    /**
    * t_crude_oil
     *
     * @mbggenerated
     */
    TCrudeOil selectByPrimaryKey(Long id);

    /**
    * t_crude_oil
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(TCrudeOil record);

    /**
    * t_crude_oil
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(TCrudeOil record);
    
    /**
     * 查询原油信息
     * @return
     */
    List<CrudeOilInfoVO> selectCrudeOilInfos(TCrudeOilQuery query);


}