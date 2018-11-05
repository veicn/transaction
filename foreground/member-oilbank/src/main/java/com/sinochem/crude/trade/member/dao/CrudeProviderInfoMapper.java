package com.sinochem.crude.trade.member.dao;

import com.sinochem.crude.trade.member.domain.CrudeProviderInfo;

public interface CrudeProviderInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CrudeProviderInfo record);

    int insertSelective(CrudeProviderInfo record);

    CrudeProviderInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CrudeProviderInfo record);

    int updateByPrimaryKey(CrudeProviderInfo record);

    CrudeProviderInfo selectByMemberId(Long memberId);
}