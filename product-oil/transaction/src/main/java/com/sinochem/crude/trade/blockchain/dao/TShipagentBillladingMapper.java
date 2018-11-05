package com.sinochem.crude.trade.blockchain.dao;

import com.sinochem.crude.trade.blockchain.domain.TShipagentBillLoading;

public interface TShipagentBillladingMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TShipagentBillLoading record);

    int insertSelective(TShipagentBillLoading record);

    TShipagentBillLoading selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TShipagentBillLoading record);

    int updateByPrimaryKey(TShipagentBillLoading record);

	TShipagentBillLoading selectByUuid(String businessUuid);
}