package com.sinochem.crude.trade.order.dao;

import com.sinochem.crude.trade.order.domain.CrudeOilLongTermContractPlan;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CrudeOilLongTermContractPlanMapper {
    /**
     * 根据主键删除
     */
    void deleteByPrimaryKey(Long id);

    /**
     *插入长约
     */
    Long insert(CrudeOilLongTermContractPlan record);

    /**
     *
     * 插入非空字段
     */
    Long insertSelective(CrudeOilLongTermContractPlan record);

    /**
     *
     * 根据主键id查询
     */
    CrudeOilLongTermContractPlan selectByPrimaryKey(Long id);

    /**
     *根据主键更新非空字段
     */
    void updateByPrimaryKeySelective(CrudeOilLongTermContractPlan record);

    /**
     * 根据主键更新
     */
    void updateByPrimaryKey(CrudeOilLongTermContractPlan record);



    //=========================自动生成方法结束===============================

    /**
     * 根据合约id查询计划信息
     * @param contractId
     * @return
     */
    List<CrudeOilLongTermContractPlan> selectByContractId(Long contractId);

    /**
     * 根据合约id删除计划
     * @param contractId 合约id
     * @return 影响条数
     */
    int removeAllByContractId(@Param("contractId") Long contractId);
}