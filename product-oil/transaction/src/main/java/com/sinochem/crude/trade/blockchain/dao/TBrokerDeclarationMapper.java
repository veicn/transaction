package com.sinochem.crude.trade.blockchain.dao;

import com.github.pagehelper.Page;
import com.sinochem.crude.trade.blockchain.domain.TBrokerDeclaration;
import com.sinochem.crude.trade.broker.model.vo.TBrokerDeclarationQueryVO;

public interface TBrokerDeclarationMapper {
    int deleteByPrimaryKey(Integer id);

    int deleteByUuid(String uuid);

    int insert(TBrokerDeclaration record);

    int insertSelective(TBrokerDeclaration record);

    TBrokerDeclaration selectByPrimaryKey(Integer id);

    TBrokerDeclaration selectByUuid(String uuid);

    Page<TBrokerDeclaration> selectByQuery(TBrokerDeclarationQueryVO tBrokerDeclarationQueryVO);

    int updateByUuidSelective(TBrokerDeclaration record);

    int updateByPrimaryKey(TBrokerDeclaration record);
}