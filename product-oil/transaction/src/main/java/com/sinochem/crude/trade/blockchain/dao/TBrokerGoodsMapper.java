package com.sinochem.crude.trade.blockchain.dao;

import com.sinochem.crude.trade.blockchain.domain.TBrokerGoods;

import java.util.List;

public interface TBrokerGoodsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TBrokerGoods record);

    int insertSelective(TBrokerGoods record);

    TBrokerGoods selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TBrokerGoods record);

    int updateByPrimaryKey(TBrokerGoods record);

    int deleteByUuid(String uuid);

    List<TBrokerGoods> selectByUuid(String uuid);

    int insertList(List<TBrokerGoods> list);
}