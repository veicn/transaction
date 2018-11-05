package com.sinochem.crude.trade.member.service;

import com.github.pagehelper.PageInfo;
import com.sinochem.crude.trade.member.domain.MessageLog;
import com.sinochem.crude.trade.member.domain.MessageLogMember;
import com.sinochem.it.b2b.common.exception.BizException;

import java.util.List;

public interface MessageLogService {
    List<MessageLog> getLogList(MessageLog messageLog, boolean needDetail);

    List<MessageLog> getLogList(MessageLog messageLog, PageInfo pageInfo);

    void asyncBatchPush(Long messageLogId) throws BizException;

    void add(MessageLog messageLog, MessageLogMember messageLogMember, Long memberId, String credentials, String roles, String toId, String delayTime, String periodTime) throws BizException;

    MessageLog getLogDetail(Long logId);

    void addPushTask(Long messageLogId) throws BizException;

    void reSend(Long memberId,Long messageLogId,Long messageLogMemberId) throws BizException;
}
