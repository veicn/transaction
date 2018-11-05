package com.sinochem.crude.trade.order.service;


import com.sinochem.crude.trade.order.model.vo.MemberInfoVO;

public interface MemberInfoService {
    /**
     * 获取用户信息
     * @param memberId
     * @return
     */
    public MemberInfoVO memberInfo(Long memberId);
}
