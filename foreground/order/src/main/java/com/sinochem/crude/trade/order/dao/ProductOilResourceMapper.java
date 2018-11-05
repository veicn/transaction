package com.sinochem.crude.trade.order.dao;


import com.sinochem.crude.trade.order.domain.ProductOilResource;

public interface ProductOilResourceMapper {
    /**
     * 根据主键删除
     */
    void deleteByPrimaryKey(Long id);

    /**
     *插入
     */
    Long insert(ProductOilResource record);

    /**
     * 根据非空字段插入
     */
    Long insertSelective(ProductOilResource record);

    /**
     *
     * 根据id查询
     */
    ProductOilResource selectByPrimaryKey(Long contractId);

    /**
     *
     * 非空字段更新
     */
    void updateByPrimaryKeySelective(ProductOilResource record);

    /**
     *
     * 根据主键更新
     */
    void updateByPrimaryKey(ProductOilResource record);
}