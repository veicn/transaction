package com.sinochem.crude.trade.member.dao;

import com.sinochem.crude.trade.member.domain.EnterpriseCrudeLog;
import com.sinochem.crude.trade.member.domain.EnterpriseLog;

public interface EnterpriseCrudeLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(EnterpriseCrudeLog enterpriseLog);

    int insertSelective(EnterpriseLog record);

    EnterpriseLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(EnterpriseLog record);

    int updateByPrimaryKey(EnterpriseLog record);
}