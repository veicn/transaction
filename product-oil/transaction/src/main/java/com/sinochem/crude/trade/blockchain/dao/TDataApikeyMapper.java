package com.sinochem.crude.trade.blockchain.dao;

import com.sinochem.crude.trade.blockchain.domain.TDataApikey;

public interface TDataApikeyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TDataApikey record);

    int insertSelective(TDataApikey record);

    TDataApikey selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TDataApikey record);

    int updateByPrimaryKey(TDataApikey record);
}