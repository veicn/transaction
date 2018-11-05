package com.sinochem.crude.trade.member.service;

import com.sinochem.crude.trade.member.domain.TMemberAccount;
import com.sinochem.crude.trade.member.model.MemberAccountResut;
import com.sinochem.crude.trade.member.model.MemberAccountVO;
import com.sinochem.crude.trade.member.model.MemberResut;
import com.sinochem.it.b2b.common.exception.BizException;

import java.util.List;

public interface ExosystemAccService {
    /**
     * 保存绑定的帐号
     */
    public void save(Long epMemberId, String sys, String account);
    /**
     * 得到绑定的帐号列表
     */
    public List<MemberAccountResut> getList();
    /**
     * 得到绑定的帐号
     */
    public String getBindingAccount(Long epMemberId, String sys) throws BizException;
    /**
     * 通过主键获取信息
     */
    public TMemberAccount getByKey(Long id);

    /**
     * 通过主键删除信息
     */
    public int delete(Long id);

    public MemberResut findMemberByVO(MemberAccountVO memberAccountVO);

    public MemberAccountResut findMemberAccountResutById(Long id);

}
