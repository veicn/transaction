package com.sinochem.crude.trade.member.dao;

import com.sinochem.crude.trade.member.domain.MessageLog;

import java.util.List;

public interface MessageLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MessageLog record);

    int insertSelective(MessageLog record);

    MessageLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MessageLog record);

    int updateByPrimaryKeyWithBLOBs(MessageLog record);

    int updateByPrimaryKey(MessageLog record);

    List<MessageLog> getLogList(MessageLog messageLog);
}