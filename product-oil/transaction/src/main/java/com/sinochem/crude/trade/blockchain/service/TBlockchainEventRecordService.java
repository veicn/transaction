package com.sinochem.crude.trade.blockchain.service;

import com.sinochem.crude.trade.blockchain.domain.TBlockchainEventRecord;
import com.sinochem.crude.trade.blockchain.domain.TTransExtend;
import com.sinochem.crude.trade.transaction.domain.po.BillCoreUpload;
import com.sinochem.it.b2b.common.exception.BizException;

public interface TBlockchainEventRecordService {

    /**
     * 新增对象
     */

    public int saveTBlockchainEventRecord(TBlockchainEventRecord tBlockchainEventRecord) throws BizException;

    public TBlockchainEventRecord findByBusiId(long busiId) throws BizException;

}
