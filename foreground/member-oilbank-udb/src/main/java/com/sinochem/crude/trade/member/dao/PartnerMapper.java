package com.sinochem.crude.trade.member.dao;

import com.sinochem.crude.trade.member.domain.Partner;

import java.util.List;

public interface PartnerMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Partner record);

    int insertSelective(Partner record);

    Partner selectByPrimaryKey(Long id);

    List<Partner> selectByPrimary(Partner partner);

    int updateByPrimaryKeySelective(Partner record);

    int updateByPrimaryKey(Partner record);
}