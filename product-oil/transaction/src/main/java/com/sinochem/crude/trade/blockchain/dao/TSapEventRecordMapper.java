package com.sinochem.crude.trade.blockchain.dao;

import com.sinochem.crude.trade.blockchain.domain.SapSettlement;
import com.sinochem.crude.trade.blockchain.domain.TSapEventRecord;

public interface TSapEventRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TSapEventRecord record);

    int insertSelective(TSapEventRecord record);

    TSapEventRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TSapEventRecord record);

    int updateByPrimaryKey(TSapEventRecord record);

    TSapEventRecord selectByPurchaseContractNo(String purchaseContractNo);

    SapSettlement selectSapByBSTKD(String purchaseContractNo);

    int deleteByPurchaseContractNo(String purchaseContractNo);
}