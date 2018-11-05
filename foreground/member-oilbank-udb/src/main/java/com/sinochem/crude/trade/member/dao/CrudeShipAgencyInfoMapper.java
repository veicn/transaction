package com.sinochem.crude.trade.member.dao;

import com.sinochem.crude.trade.member.domain.CrudeProviderInfo;
import com.sinochem.crude.trade.member.domain.CrudeShipAgencyInfo;

public interface CrudeShipAgencyInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CrudeShipAgencyInfo record);

    int insertSelective(CrudeShipAgencyInfo record);

    CrudeShipAgencyInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CrudeShipAgencyInfo record);

    int updateByPrimaryKey(CrudeShipAgencyInfo record);

    CrudeShipAgencyInfo selectByMemberId(Long memberId);
}