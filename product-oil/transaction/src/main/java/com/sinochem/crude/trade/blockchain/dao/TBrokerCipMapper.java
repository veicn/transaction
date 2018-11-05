package com.sinochem.crude.trade.blockchain.dao;

import com.sinochem.crude.trade.blockchain.domain.TBrokerCip;

import java.util.List;

public interface TBrokerCipMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TBrokerCip record);

    int insertSelective(TBrokerCip record);

    TBrokerCip selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TBrokerCip record);

    int updateByPrimaryKey(TBrokerCip record);

    List<TBrokerCip> selectByUuid(String uuid);

    int deleteByUuid(String uuid);

    int insertList(List<TBrokerCip> list);
}