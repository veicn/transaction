package com.sinochem.crude.trade.order.dao;

import com.sinochem.crude.trade.order.domain.CrudeOilResource;

public interface CrudeOilResourceMapper {
    /**
     * 根据主键删除
     */
    void deleteByPrimaryKey(Long id);

    /**
     * 插入
     */
    Long insert(CrudeOilResource record);

    /**
     * 非空字段插入
     */
    Long insertSelective(CrudeOilResource record);

    /**
     *
     * 根据主键查询
     */
    CrudeOilResource selectByPrimaryKey(Long contractId);

    /**
     *
     * 更新非空字段
     */
    void updateByPrimaryKeySelective(CrudeOilResource record);

    /**
     * 根据主键更新
     */
    void updateByPrimaryKey(CrudeOilResource record);

    //=========================自动生成方法结束===============================

    CrudeOilResource selectById(Long id);
}