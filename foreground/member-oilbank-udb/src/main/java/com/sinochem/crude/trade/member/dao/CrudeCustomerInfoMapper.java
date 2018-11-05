package com.sinochem.crude.trade.member.dao;

import com.sinochem.crude.trade.member.domain.CrudeCustomerInfo;

public interface CrudeCustomerInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CrudeCustomerInfo record);

    int insertSelective(CrudeCustomerInfo record);

    CrudeCustomerInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CrudeCustomerInfo record);

    int updateByPrimaryKey(CrudeCustomerInfo record);

    CrudeCustomerInfo selectByMemberId(Long memberId);
}