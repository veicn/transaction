package com.sinochem.crude.trade.member.service.impl;

import com.sinochem.crude.trade.common.i18n.VisitorLocale;
import com.sinochem.crude.trade.member.contact.MsgConstant;
import com.sinochem.crude.trade.member.dao.TMemberAccountMapper;
import com.sinochem.crude.trade.member.domain.TMemberAccount;
import com.sinochem.crude.trade.member.model.MemberAccountResut;
import com.sinochem.crude.trade.member.model.MemberAccountVO;
import com.sinochem.crude.trade.member.model.MemberResut;
import com.sinochem.crude.trade.member.service.ExosystemAccService;
import com.sinochem.it.b2b.common.exception.BizException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ExosystemAccServiceImpl implements ExosystemAccService {
    Logger logger = LoggerFactory.getLogger(ExosystemAccServiceImpl.class);

    @Autowired
    private TMemberAccountMapper memberAccountMapper;

    @Override
    public void save(Long epMemberId, String sys, String account){
        TMemberAccount memberAccount = memberAccountMapper.selectByMemberIdAndSys(epMemberId, sys);
        if(memberAccount != null){
            memberAccount.setAccount(account);
            memberAccount.setUpdateTime(new Date());
            memberAccount.setUpdateUser(epMemberId);
            memberAccountMapper.updateByPrimaryKey(memberAccount);
        }else{
            memberAccount = new TMemberAccount();
            memberAccount.setMemberId(epMemberId);
            memberAccount.setSysCode(sys);
            memberAccount.setAccount(account);
            memberAccount.setCreateTime(new Date());
            memberAccount.setCreateUser(epMemberId);
            memberAccount.setUpdateTime(new Date());
            memberAccount.setUpdateUser(epMemberId);
            memberAccountMapper.insert(memberAccount);
        }
    }

    @Override
    public List<MemberAccountResut> getList(){
        return memberAccountMapper.selectAll();
    }

    @Override
    public MemberResut findMemberByVO(MemberAccountVO memberAccountVO){
        return memberAccountMapper.selectMember(memberAccountVO);
    }

    @Override
    public MemberAccountResut findMemberAccountResutById(Long id){
        return memberAccountMapper.selectMemberAccountResut(id);
    }

    @Override
    public String getBindingAccount(Long epMemberId, String sys) throws BizException {
        try{
            TMemberAccount memberAccount = memberAccountMapper.selectByMemberIdAndSys(epMemberId, sys);
            if(memberAccount != null){
                return memberAccount.getAccount();
            }
        } catch(Exception e) {
            logger.error("获取绑定账号失败：" ,e);
            throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.MEMBEROO24));
        }
        return null;
    }

    @Override
    public TMemberAccount getByKey(Long id) {
        return memberAccountMapper.selectByPrimaryKey(id);
    }

    @Override
    public int delete(Long id) {
        return memberAccountMapper.deleteByPrimaryKey(id);
    }

}
