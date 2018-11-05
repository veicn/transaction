package com.sinochem.crude.trade.transaction.dao;

import com.sinochem.crude.trade.transaction.domain.po.SaleSheet;

public interface SaleSheetHistoryMapper {

    /**
     * 新增对象
     */
    int insert(SaleSheet saleSheet);

    /**
     * 根据主键物理删除, 慎用
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 根据主键修改对象
     */
    int updateByPrimaryKey(SaleSheet saleSheet);

    /**
     * 根据主键修改对象，更新有值的字段
     */
    int updateByPrimaryKeySelective(SaleSheet saleSheet);

    /**
     * 根据主键查询对象
     */
    SaleSheet selectByPrimaryKey(Long id);

    /**
     * 根据uuid查询对象
     */
    SaleSheet selectByUuid(String uuid);

    //**************************以下方法由开发者补充*********************************/
}