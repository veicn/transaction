package com.sinochem.crude.trade.blockchain.dao;

import com.sinochem.crude.trade.blockchain.domain.TTransExtend;
import java.util.*;

public interface TTransExtendMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TTransExtend record);

    int insertSelective(TTransExtend record);

    TTransExtend selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TTransExtend record);

    int updateByPrimaryKey(TTransExtend record);

    int updateBySelective(TTransExtend record);

    TTransExtend selectByDealNo(String dealNo);

    TTransExtend selectByPurchaseContractNo(String purchaseContractNo);

    List<TTransExtend> selectAllList();

}