package com.sinochem.crude.trade.member.dao;

import com.sinochem.crude.trade.member.domain.MemberTags;

public interface MemberTagsMapper {
    /**
     * 查询某企业是否同时注册了壹化网
     * @param memberId
     * @return 如果返回值大于0，则证明同时注册壹化网，否则则只注册了本网站
     */
    int isRegisterToYihuaByMemberId(Long memberId);

    void insert(MemberTags memberTags);

    /**
     * 查询用户是否是企业
     * @param memberId
     * @return  0是用户   大于0是企业
     */
    Integer checkEnterprise(Long memberId);
}
