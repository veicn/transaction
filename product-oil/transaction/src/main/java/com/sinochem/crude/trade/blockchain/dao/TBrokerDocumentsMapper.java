package com.sinochem.crude.trade.blockchain.dao;

import com.sinochem.crude.trade.blockchain.domain.TBrokerDocuments;

import java.util.List;

public interface TBrokerDocumentsMapper {
    int deleteByPrimaryKey(Long id);
    int deleteByUuid(String uuid);
    int insert(TBrokerDocuments record);

    int insertSelective(TBrokerDocuments record);

    int insertList(List<TBrokerDocuments> list);

    TBrokerDocuments selectByPrimaryKey(Long id);

    List<TBrokerDocuments> selectByUuid(String uuid);

    int updateByPrimaryKeySelective(TBrokerDocuments record);

    int updateByPrimaryKey(TBrokerDocuments record);
}