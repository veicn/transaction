package com.sinochem.crude.trade.member.dao;

import com.sinochem.crude.trade.member.domain.MessageLogMember;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MessageLogMemberMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MessageLogMember record);

    int batchInsert(List<MessageLogMember> messageLogMembers);

    int insertSelective(MessageLogMember record);

    MessageLogMember selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MessageLogMember record);

    int updateByPrimaryKey(MessageLogMember record);

    List<MessageLogMember> getLogMemberListByMessageId(@Param("messageLogId") Long messageLogId);
}