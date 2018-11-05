package com.sinochem.crude.trade.member.service.impl;

import com.sinochem.crude.trade.member.dao.MemberTagsMapper;
import com.sinochem.crude.trade.member.domain.MemberTags;
import com.sinochem.crude.trade.member.service.MemberTagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberTagsServiceImpl implements MemberTagsService {
    @Autowired
    private MemberTagsMapper memberTagsMapper;

    @Override
    public boolean isRegisterToYihuaByMemberId(Long memberId) {
        return memberTagsMapper.isRegisterToYihuaByMemberId(memberId) > 0 ? true : false;
    }

    @Override
    public void insert(MemberTags memberTags) {
        memberTagsMapper.insert(memberTags);
    }

    @Override
    public Integer checkEnterprise(Long memberId) {
        return memberTagsMapper.checkEnterprise(memberId);
    }

}
