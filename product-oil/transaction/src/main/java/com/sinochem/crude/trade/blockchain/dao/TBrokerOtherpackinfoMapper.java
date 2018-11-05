package com.sinochem.crude.trade.blockchain.dao;

import com.sinochem.crude.trade.blockchain.domain.TBrokerOtherpackinfo;

import java.util.List;

public interface TBrokerOtherpackinfoMapper {
    int deleteByPrimaryKey(Long id);

    int deleteByUuid(String uuid);

    int insert(TBrokerOtherpackinfo record);

    int insertSelective(TBrokerOtherpackinfo record);

    int insertList(List<TBrokerOtherpackinfo> list);

    TBrokerOtherpackinfo selectByPrimaryKey(Long id);

    List<TBrokerOtherpackinfo> selectByUuid(String uuid);

    int updateByPrimaryKeySelective(TBrokerOtherpackinfo record);

    int updateByPrimaryKey(TBrokerOtherpackinfo record);
}