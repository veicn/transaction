package com.sinochem.crude.trade.blockchain.dao;

import com.sinochem.crude.trade.blockchain.domain.TBrokerOthermatters;

import java.util.List;

public interface TBrokerOthermattersMapper {
    int deleteByPrimaryKey(Long id);
    int deleteByUuid(String uuid);
    int insert(TBrokerOthermatters record);

    int insertSelective(TBrokerOthermatters record);

    int insertList(List<TBrokerOthermatters> list);

    TBrokerOthermatters selectByPrimaryKey(Long id);

    List<TBrokerOthermatters> selectByUuid(String uuid);

    int updateByPrimaryKeySelective(TBrokerOthermatters record);

    int updateByPrimaryKey(TBrokerOthermatters record);
}