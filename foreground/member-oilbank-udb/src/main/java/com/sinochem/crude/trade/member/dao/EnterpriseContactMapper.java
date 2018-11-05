package com.sinochem.crude.trade.member.dao;

import com.sinochem.crude.trade.member.domain.EnterpriseContact;

public interface EnterpriseContactMapper {
    int deleteByPrimaryKey(Long id);

    int insert(EnterpriseContact record);

    int insertSelective(EnterpriseContact record);

    EnterpriseContact selectByPrimaryKey(Long id);

    EnterpriseContact selectByMemberId(Long memberId);

    int updateByPrimaryKeySelective(EnterpriseContact record);

    int updateByPrimaryKey(EnterpriseContact record);

    EnterpriseContact findContactWithMbIdAndEpId(EnterpriseContact contact);
}