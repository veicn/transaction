package com.sinochem.crude.trade.transport.service;

import com.sinochem.crude.trade.transport.model.MemberInfoVO;



public interface MemberInfoService {
    /**
     * 获取用户信息
     * @param memberId
     * @return
     */
    public MemberInfoVO memberInfo(Long memberId);
}
