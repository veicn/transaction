package com.sinochem.crude.trade.member.service;


import com.sinochem.crude.trade.member.model.vo.MemberInfoVO;

public interface MemberInfoService {
    /**
     * 获取用户信息
     * @param memberId
     * @return
     */
    public MemberInfoVO memberInfo(Long memberId);
}
