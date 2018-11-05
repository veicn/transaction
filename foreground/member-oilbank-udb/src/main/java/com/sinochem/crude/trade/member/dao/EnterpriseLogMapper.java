package com.sinochem.crude.trade.member.dao;

import com.sinochem.crude.trade.member.domain.EnterpriseLog;

public interface EnterpriseLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(EnterpriseLog record);

    int insertSelective(EnterpriseLog record);

    EnterpriseLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(EnterpriseLog record);

    int updateByPrimaryKeyWithBLOBs(EnterpriseLog record);

    int updateByPrimaryKey(EnterpriseLog record);
}