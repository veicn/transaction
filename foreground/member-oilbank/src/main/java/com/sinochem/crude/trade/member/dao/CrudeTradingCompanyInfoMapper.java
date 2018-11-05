package com.sinochem.crude.trade.member.dao;

import com.sinochem.crude.trade.member.domain.CrudeTradingCompanyInfo;

public interface CrudeTradingCompanyInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CrudeTradingCompanyInfo record);

    int insertSelective(CrudeTradingCompanyInfo record);

    CrudeTradingCompanyInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CrudeTradingCompanyInfo record);

    int updateByPrimaryKey(CrudeTradingCompanyInfo record);

    CrudeTradingCompanyInfo selectByMemberId(Long memberId);
}