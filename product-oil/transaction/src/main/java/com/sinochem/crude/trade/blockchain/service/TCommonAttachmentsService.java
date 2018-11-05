package com.sinochem.crude.trade.blockchain.service;

import com.sinochem.crude.trade.blockchain.domain.TBlockchainEventRecord;
import com.sinochem.crude.trade.blockchain.domain.TCommonAttachments;
import com.sinochem.crude.trade.broker.model.vo.TBrokerVO;
import com.sinochem.crude.trade.shipagent.utils.ResultData;
import com.sinochem.crude.trade.transaction.model.vo.FileInfoVO;
import com.sinochem.it.b2b.common.exception.BizException;

import java.util.List;

/**
 * @Author: fengzk
 * @CreateDate: 2018/9/13 16:45
 * @Version: [v1.0]
 */
public interface TCommonAttachmentsService {
//    int deleteByUuid(String uuid);
    int updateByUuid(TBrokerVO tBrokerVO);
    int updateByUuid(List<TCommonAttachments> list);
    int deleteByBusinessUuid(String businessUuid);
    int insert(TCommonAttachments record);
    int insertList(List<TCommonAttachments> list) throws BizException;
    List<TCommonAttachments> selectByBusinessUuid(String businessUuid);

    public List<TCommonAttachments> selectByParameter(TCommonAttachments attachments);

    public TCommonAttachments insert(TCommonAttachments attachments , TBlockchainEventRecord tBlockchainEventRecord)throws Exception;

    int deleteByPrimaryKey(FileInfoVO fileInfoVO, String event_code)throws Exception;

    TCommonAttachments selectByPrimaryKey(String id)throws Exception;

    int updateByPrimaryKeySelective(TCommonAttachments attachments);

	ResultData deleteSofOrBl(TCommonAttachments attachments )throws Exception;
}
