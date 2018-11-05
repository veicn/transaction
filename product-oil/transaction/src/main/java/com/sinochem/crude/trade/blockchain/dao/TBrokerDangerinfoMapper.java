package com.sinochem.crude.trade.blockchain.dao;

import com.sinochem.crude.trade.blockchain.domain.TBrokerDangerinfo;

public interface TBrokerDangerinfoMapper {
    int deleteByPrimaryKey(Long id);
    int deleteByUuid(String uuid);
    int insert(TBrokerDangerinfo record);

    int insertSelective(TBrokerDangerinfo record);

    TBrokerDangerinfo selectByPrimaryKey(Long id);

    TBrokerDangerinfo selectByUuid(String uuid);

    int updateByPrimaryKeySelective(TBrokerDangerinfo record);

    int updateByPrimaryKey(TBrokerDangerinfo record);
}