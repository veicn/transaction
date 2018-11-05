package com.sinochem.crude.trade.blockchain.dao;

import com.sinochem.crude.trade.blockchain.domain.TDataBlockchainEvent;

public interface TDataBlockchainEventMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TDataBlockchainEvent record);

    int insertSelective(TDataBlockchainEvent record);

    TDataBlockchainEvent selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TDataBlockchainEvent record);

    int updateByPrimaryKey(TDataBlockchainEvent record);
}