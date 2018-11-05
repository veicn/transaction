package com.sinochem.crude.trade.order.dao;

import com.sinochem.crude.trade.order.domain.ContractRelevanter;

public interface ContractRelevanterMapper {
    /**
     * 根据主键删除
     */
    void deleteByPrimaryKey(Long id);

    /**
     *插入合约
     */
    Long insert(ContractRelevanter record);

    /**
     *
     * 非空字段插入
     */
    Long insertSelective(ContractRelevanter record);

    /**
     *根据主键查询合约联系人
     */
    ContractRelevanter selectByPrimaryKey(Long id);

    /**
     *
     * 按非空字段更新联系人
     */
    void updateByPrimaryKeySelective(ContractRelevanter record);

    /**
     *根据主键更新联系人
     */
    void updateByPrimaryKey(ContractRelevanter record);



    //=========================自动生成方法结束===============================

    /**
     * 查询联系人信息
     * @param contractRelevanter
     * @return
     */
    ContractRelevanter selectByCondition(ContractRelevanter contractRelevanter);
}