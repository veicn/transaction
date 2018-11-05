package com.sinochem.crude.trade.member.dao;

import com.sinochem.crude.trade.member.domain.MemberCredentialsLogs;

public interface MemberCredentialsLogsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MemberCredentialsLogs record);

    int insertSelective(MemberCredentialsLogs record);

    MemberCredentialsLogs selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MemberCredentialsLogs record);

    int updateByPrimaryKey(MemberCredentialsLogs record);
}