package com.sinochem.crude.trade.member.dao;

import com.sinochem.crude.trade.member.domain.TMemberAccount;
import com.sinochem.crude.trade.member.model.MemberAccountResut;
import com.sinochem.crude.trade.member.model.MemberAccountVO;
import com.sinochem.crude.trade.member.model.MemberResut;

import java.util.List;

public interface TMemberAccountMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TMemberAccount record);

    int insertSelective(TMemberAccount record);

    TMemberAccount selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TMemberAccount record);

    int updateByPrimaryKey(TMemberAccount record);

    TMemberAccount selectByMemberIdAndSys(Long memberId, String sysCode);

    List<MemberAccountResut> selectAll();

    MemberResut selectMember(MemberAccountVO memberAccountVO);

    MemberAccountResut selectMemberAccountResut(Long id);
}