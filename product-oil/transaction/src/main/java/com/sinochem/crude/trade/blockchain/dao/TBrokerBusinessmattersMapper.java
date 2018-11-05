package com.sinochem.crude.trade.blockchain.dao;

import com.sinochem.crude.trade.blockchain.domain.TBrokerBusinessmatters;

import java.util.List;

public interface TBrokerBusinessmattersMapper {
    int deleteByPrimaryKey(Long id);
    int deleteByUuid(String uuid);
    int insert(TBrokerBusinessmatters record);

    int insertSelective(TBrokerBusinessmatters record);

    int insertList(List<TBrokerBusinessmatters> list);

    TBrokerBusinessmatters selectByPrimaryKey(Long id);

    List<TBrokerBusinessmatters> selectByUuid(String uuid);

    int updateByPrimaryKeySelective(TBrokerBusinessmatters record);

    int updateByPrimaryKey(TBrokerBusinessmatters record);
}