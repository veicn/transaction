package com.sinochem.crude.trade.goods.dao;

import com.sinochem.crude.trade.goods.model.TCrudeCatagory;

public interface TCrudeCatagoryMapper {
    /**
    * t_crude_catagory
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
    * t_crude_catagory
     *
     * @mbggenerated
     */
    int insert(TCrudeCatagory record);

    /**
    * t_crude_catagory
     *
     * @mbggenerated
     */
    int insertSelective(TCrudeCatagory record);

    /**
    * t_crude_catagory
     *
     * @mbggenerated
     */
    TCrudeCatagory selectByPrimaryKey(Long id);

    /**
    * t_crude_catagory
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(TCrudeCatagory record);

    /**
    * t_crude_catagory
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(TCrudeCatagory record);
}