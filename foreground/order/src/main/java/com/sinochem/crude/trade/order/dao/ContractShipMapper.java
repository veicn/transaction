package com.sinochem.crude.trade.order.dao;

import com.sinochem.crude.trade.order.domain.ContractShip;

/**
 * 挂买船务信息mapper
 */
public interface ContractShipMapper {
    /**
     *
     * 根据主键ID删除
     */
    void deleteByPrimaryKey(Long id);

    /**
     *插入船务信息表
     */
    Long insert(ContractShip record);

    /**
     * 根据非空字段插入
     */
    Long insertSelective(ContractShip record);

    /**
     *
     * 根据主键查询船务信息
     */
    ContractShip selectByPrimaryKey(Long id);

    /**
     * 根据主键更新非空字段
     */
    void updateByPrimaryKeySelective(ContractShip record);

    /**
     *根据主键更新表
     */
    void updateByPrimaryKey(ContractShip record);



    //=========================自动生成方法结束===============================

    /**
     * 合约id查询船务信息
     * @param contractId
     * @return
     */
    ContractShip queryByContractId(Long contractId);
}