package com.sinochem.crude.trade.transaction.dao;

import com.sinochem.crude.trade.transaction.domain.po.ContractSheet;

public interface ContractSheetHistoryMapper {

    /**
     * 新增对象
     */
    int insert(ContractSheet contractSheet);

    /**
     * 根据主键物理删除, 慎用
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 根据主键修改对象
     */
    int updateByPrimaryKey(ContractSheet contractSheet);

    /**
     * 根据主键修改对象，更新有值的字段
     */
    int updateByPrimaryKeySelective(ContractSheet contractSheet);

    /**
     * 根据主键查询对象
     */
    ContractSheet selectByPrimaryKey(Long id);

    /**
     * 根据uuid查询对象
     */
    ContractSheet selectByUuid(String uuid);

    //**************************以下方法由开发者补充*********************************/
}