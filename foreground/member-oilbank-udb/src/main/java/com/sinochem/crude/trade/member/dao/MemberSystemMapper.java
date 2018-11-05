package com.sinochem.crude.trade.member.dao;

import com.sinochem.crude.trade.member.domain.MemberSystem;

import java.util.List;

public interface MemberSystemMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MemberSystem record);

    int insertSelective(MemberSystem record);

    MemberSystem selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MemberSystem record);

    int updateByPrimaryKey(MemberSystem record);

    /**
     * 根据用户编号，查询用户的系统权限
     * @param epMemberId
     * @return
     */
    List<MemberSystem> selectByMemberId(Long epMemberId);
}