package com.sinochem.crude.trade.member.service;

import com.sinochem.crude.trade.member.domain.MemberTags;

public interface MemberTagsService {
    /**
     * 查询某企业是否同时注册了壹化网
     *
     * @param memberId
     * @return 如果返回值为true，则证明同时注册壹化网，否则则只注册了本网站
     */
    boolean isRegisterToYihuaByMemberId(Long memberId);

    void insert(MemberTags memberTags);

    Integer checkEnterprise(Long memberId);
}
