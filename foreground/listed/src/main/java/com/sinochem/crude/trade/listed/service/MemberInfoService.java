package com.sinochem.crude.trade.listed.service;

import com.sinochem.crude.trade.listed.model.vo.MemberInfoVO;

public interface MemberInfoService {
    /**
     * 获取用户信息
     * @param memberId
     * @return
     */
    public MemberInfoVO memberInfo(Long memberId);
}
