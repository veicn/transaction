package com.sinochem.crude.trade.order.dao;

import com.sinochem.crude.trade.order.domain.ContractShipBerth;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ContractShipBerthMapper {

    void batchSave(List<ContractShipBerth> list);

    void insertSelective(ContractShipBerth contractShipBerth);

    void updateContractShipBerth(List<ContractShipBerth> list);

    List<ContractShipBerth> selectByContractId(@Param("contractId") Long contractId);

    /**
     * 根据订单删除所有泊位
     * @param contractId
     *      订单ID
     * @return 影响条数
     */
    int removeAllByContractId(@Param("contractId") Long contractId);

}