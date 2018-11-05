package com.sinochem.crude.trade.blockchain.dao;

import com.sinochem.crude.trade.blockchain.domain.TShipagentSofHead;

public interface TShipagentSofHeadMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TShipagentSofHead record);

    int insertSelective(TShipagentSofHead record);

    TShipagentSofHead selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TShipagentSofHead record);

    int updateByPrimaryKey(TShipagentSofHead record);
}