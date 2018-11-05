package com.sinochem.crude.trade.blockchain.dao;

import com.sinochem.crude.trade.blockchain.domain.TShipagentBillLoading;
import com.sinochem.crude.trade.blockchain.domain.TShipagentSof;

public interface TShipagentSofMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TShipagentSof record);

    int insertSelective(TShipagentSof record);

    TShipagentSof selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TShipagentSof record);

    int updateByPrimaryKey(TShipagentSof record);

    TShipagentSof selectByUuid(String uuid);
}