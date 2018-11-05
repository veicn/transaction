package com.sinochem.crude.trade.member.dao;

import com.sinochem.crude.trade.member.domain.CrudePotrerillosInfo;

public interface CrudePotrerillosInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CrudePotrerillosInfo record);

    int insertSelective(CrudePotrerillosInfo record);

    CrudePotrerillosInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CrudePotrerillosInfo record);

    int updateByPrimaryKey(CrudePotrerillosInfo record);

    CrudePotrerillosInfo selectByMemberId(Long memberId);
}