package com.sinochem.crude.trade.blockchain.dao;

import com.sinochem.crude.trade.blockchain.domain.TBrokerProductspecification;

import java.util.List;

public interface TBrokerProductspecificationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TBrokerProductspecification record);

    int insertSelective(TBrokerProductspecification record);

    TBrokerProductspecification selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TBrokerProductspecification record);

    int updateByPrimaryKey(TBrokerProductspecification record);

    List<TBrokerProductspecification> selectByUuid(String uuid);

    int deleteByUuid(String uuid);

    int insertList(List<TBrokerProductspecification> list);

}