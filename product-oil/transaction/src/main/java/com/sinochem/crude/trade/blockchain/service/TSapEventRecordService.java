package com.sinochem.crude.trade.blockchain.service;

import com.sinochem.crude.trade.blockchain.domain.SapSettlement;
import com.sinochem.crude.trade.blockchain.domain.TBlockchainEventRecord;
import com.sinochem.crude.trade.blockchain.domain.TSapEventRecord;
import com.sinochem.it.b2b.common.exception.BizException;

public interface TSapEventRecordService {

    /**
     * 新增对象
     */
    public int insert(TSapEventRecord tSapEventRecord) throws BizException;

    public void saveTSapEventRecord() throws BizException, InterruptedException;

    public TSapEventRecord findByPurchaseContractNo(String purchaseContractNo) throws BizException;

    public SapSettlement selectSapByBSTKD(String purchaseContractNo) throws BizException;

    public int deleteByPurchaseContractNo(String purchaseContractNo) throws BizException;

}
