package com.sinochem.crude.trade.shiprefueling.dao;


import com.sinochem.crude.trade.shiprefueling.domain.po.TDataDict;

import java.util.List;
import java.util.Map;

public interface TDataDictMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_data_dict
     *
     * @mbggenerated Mon Jul 23 16:18:25 CST 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_data_dict
     *
     * @mbggenerated Mon Jul 23 16:18:25 CST 2018
     */
    int insert(TDataDict record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_data_dict
     *
     * @mbggenerated Mon Jul 23 16:18:25 CST 2018
     */
    int insertSelective(TDataDict record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_data_dict
     *
     * @mbggenerated Mon Jul 23 16:18:25 CST 2018
     */
    TDataDict selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_data_dict
     *
     * @mbggenerated Mon Jul 23 16:18:25 CST 2018
     */
    int updateByPrimaryKeySelective(TDataDict record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_data_dict
     *
     * @mbggenerated Mon Jul 23 16:18:25 CST 2018
     */
    int updateByPrimaryKey(TDataDict record);


    /**
     * 查询油品分类或规格
     * @param tDataDict
     * @return
     */
    List<Map<String , String>> selectOil(TDataDict tDataDict);
}