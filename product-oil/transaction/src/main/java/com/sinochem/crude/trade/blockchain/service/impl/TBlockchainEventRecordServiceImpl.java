package com.sinochem.crude.trade.blockchain.service.impl;

import com.sinochem.crude.trade.blockchain.dao.TBlockchainEventRecordMapper;
import com.sinochem.crude.trade.blockchain.dao.TTransExtendMapper;
import com.sinochem.crude.trade.blockchain.domain.TBlockchainEventRecord;
import com.sinochem.crude.trade.blockchain.service.TBlockchainEventRecordService;
import com.sinochem.it.b2b.common.exception.BizException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TBlockchainEventRecordServiceImpl implements TBlockchainEventRecordService {

    @Autowired
    private TBlockchainEventRecordMapper tBlockchainEventRecordMapper;

    /**
     * 新增对象
     */

    public int saveTBlockchainEventRecord(TBlockchainEventRecord tBlockchainEventRecord) throws BizException{

        return tBlockchainEventRecordMapper.insert(tBlockchainEventRecord);
    }

    public TBlockchainEventRecord findByBusiId(long busiId) throws BizException{

        return tBlockchainEventRecordMapper.selectByBusiId(busiId);
    }
}
