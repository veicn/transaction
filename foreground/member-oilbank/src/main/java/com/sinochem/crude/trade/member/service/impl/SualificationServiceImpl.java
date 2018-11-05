package com.sinochem.crude.trade.member.service.impl;

import com.eyeieye.melody.util.ArrayUtil;
import com.sinochem.crude.trade.common.i18n.VisitorLocale;
import com.sinochem.crude.trade.member.contact.MsgConstant;
import com.sinochem.crude.trade.member.contact.UdbCodeConstant;
import com.sinochem.crude.trade.member.dao.*;
import com.sinochem.crude.trade.member.domain.*;
import com.sinochem.crude.trade.member.service.EnterpriseContactService;
import com.sinochem.crude.trade.member.service.EnterpriseService;
import com.sinochem.crude.trade.member.service.SualificationService;
import com.sinochem.crude.trade.member.service.udbservice.MemberCredentialsUdbService;
import com.sinochem.crude.trade.member.util.UdbResult;
import com.sinochem.it.b2b.common.exception.BizException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SualificationServiceImpl implements SualificationService {

    final String POTRERILLOS_CODE = "1";//炼厂
    final String TRADINGCOMPANY_CODE = "2";//贸易公司
    final String CUSTOMER_CODE = "3";//客户
    final String PROVIDER_CODE = "4";//供应商
     final String SHIPAGENCY_CODE = "5";//船务

    @Autowired
    MemberCredentialsMapper memberCredentialsMapper;
    @Autowired
    CrudeCustomerInfoMapper crudeCustomerInfoMapper;
    @Autowired
    CrudePotrerillosInfoMapper crudePotrerillosInfoMapper;
    @Autowired
    CrudeTradingCompanyInfoMapper crudeTradingCompanyInfoMapper;
    @Autowired
    CrudeProviderInfoMapper crudeProviderInfoMapper;
    @Autowired
    CrudeShipAgencyInfoMapper crudeShipAgencyInfoMapper;
    @Autowired
    EnterpriseService enterpriseService;
    @Autowired
    EnterpriseContactService enterpriseContactService;

    @Autowired
    MemberCredentialsLogsMapper memberCredentialsLogsMapper;

    @Autowired
    MemberCredentialsUdbService memberCredentialsUdbService;



    public MemberCredentials addSualificationInfo(SualificationInfo info,Long memberId,String code){
        MemberCredentials memberCredentials = new MemberCredentials();
//        memberCredentials.setAudit(1);
//        //TODO 这里测试版先设置通过，以后需要修改
        memberCredentials.setAudit(0);
        memberCredentials.setCredentialsCode(code);
        memberCredentials.setMemberId(memberId);
        memberCredentialsMapper.insert(memberCredentials);
        info.setMemberId(memberId);
        info.setCredentialId(memberCredentials.getId());
        return memberCredentials;
    }

    @Override
    public MemberCredentials addCrudeCustomerInfo(CrudeCustomerInfo info,Long memberId) {
        MemberCredentials memberCredentials = addSualificationInfo(info,memberId,CUSTOMER_CODE);
        crudeCustomerInfoMapper.insert(info);
        return memberCredentials;
    }

    @Override
    public MemberCredentials addCrudePotrerillosInfo(CrudePotrerillosInfo info,Long memberId) {
        MemberCredentials memberCredentials = addSualificationInfo(info,memberId,POTRERILLOS_CODE);
        crudePotrerillosInfoMapper.insert(info);
        return memberCredentials;
    }

    @Override
    public MemberCredentials addCrudeProviderInfo(CrudeProviderInfo info,Long memberId) {

        MemberCredentials memberCredentials = addSualificationInfo(info,memberId,PROVIDER_CODE);
        crudeProviderInfoMapper.insert(info);
        return memberCredentials;
    }

    @Override
    public MemberCredentials addCrudeTradingCompanyInfo(CrudeTradingCompanyInfo info,Long memberId) {
        MemberCredentials memberCredentials = addSualificationInfo(info,memberId,TRADINGCOMPANY_CODE);
        crudeTradingCompanyInfoMapper.insert(info);
        return memberCredentials;
    }


    @Override
    public boolean audit(Long credentialId) {

        return memberCredentialsMapper.updateAuditById(credentialId);
    }

    @Override
    public List<MemberCredentials> getRedentials(Long memberId) {
        List<MemberCredentials> memberCredentialsList = memberCredentialsMapper.selectByMemberId(memberId);

        return  memberCredentialsList;
    }

    @Override
    public CrudeCustomerInfo getCrudeCustomerInfo(Long memberId) {
        return crudeCustomerInfoMapper.selectByMemberId(memberId);
    }

    @Override
    public CrudePotrerillosInfo getCrudePotrerillosInfo(Long memberId) {
        return crudePotrerillosInfoMapper.selectByMemberId(memberId);
    }

    @Override
    public CrudeProviderInfo getCrudeProviderInfo(Long memberId) {
        return crudeProviderInfoMapper.selectByMemberId(memberId);
    }

    @Override
    public CrudeTradingCompanyInfo getCrudeTradingCompanyInfo(Long memberId) {
        return crudeTradingCompanyInfoMapper.selectByMemberId(memberId);
    }

    @Override
    public MemberCredentials addCrudeShipAgencyInfo(CrudeShipAgencyInfo info, Long memberId) {

        MemberCredentials memberCredentials = addSualificationInfo(info,memberId,SHIPAGENCY_CODE);
        crudeShipAgencyInfoMapper.insert(info);
        return memberCredentials;
    }

    @Override
    public CrudeShipAgencyInfo getCrudeShipAgencyInfo(Long memberId) {
        return crudeShipAgencyInfoMapper.selectByMemberId(memberId);
    }

    @Override
    public Enterprise getEnterprisesInfo(Long memberId){
        Enterprise enterprise = enterpriseService.enterpersiceDetail(memberId);
        if(enterprise != null){
            EnterpriseContact contact = new EnterpriseContact();
            contact.setMemberId(memberId);
            contact.setEnterpriseId(enterprise.getId());
            enterprise.setContact(enterpriseContactService.findContactWithMbIdAndEpId(contact));
        }
        return enterprise;
    }

    @Override
    public void requestSualification(Long epMemberId, String code) throws BizException {
        MemberCredentials memberCredentials = new MemberCredentials();
//        memberCredentials.setAudit(1);
//        //TODO 这里测试版先设置通过，以后需要修改
        memberCredentials.setAudit(0);
        memberCredentials.setCredentialsCode(code);
        memberCredentials.setMemberId(epMemberId);

        List<MemberCredentials> memberCredentialsList = memberCredentialsMapper.selectByMemberId(epMemberId);
        for(MemberCredentials memberCredentials1 : memberCredentialsList){

            if (memberCredentials1.getCredentialsCode().equals(code)&&(memberCredentials1.getAudit()==0||memberCredentials1.getAudit()==1)){
                throw new BizException("资质申请已存在,请勿重复提交");
            }
        }
        memberCredentialsMapper.insert(memberCredentials);

    }

    @Override
    public void credentialsDelete(Long epMemberId, String code,Long loginMemberId) throws BizException{
        List<MemberCredentials> memberCredentialsList = memberCredentialsMapper.selectByMemberIdAudited(epMemberId);
        String[] exts = new String[]{"0","99","98"};
        int credentialsNum = 0;
        for(MemberCredentials credential: memberCredentialsList){
            if(ArrayUtil.contains(exts,credential.getCredentialsCode()) == false ){
                credentialsNum ++;
            }
        }
        if(credentialsNum<=1){
            throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.MEMBEROO30));
        }
        MemberCredentials memberCredentials = memberCredentialsMapper.getCredentials(epMemberId,Long.parseLong(code));
        if(memberCredentials==null){
            throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.MEMBEROO29));
        }
        if(memberCredentials.getAudit()!=1){
            throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.MEMBEROO31));
        }


        UdbResult result = memberCredentialsUdbService.deleteEnterpriseCredential(memberCredentials);
        if(!UdbCodeConstant.SUCCESS.equals(result.getCode())){
            throw new BizException(result.getMessage());
        }
        memberCredentialsMapper.deleteAuditById(memberCredentials.getId());
        //写入日志
        MemberCredentialsLogs memberCredentialsLogs = new MemberCredentialsLogs();
        memberCredentialsLogs.setMemberId(memberCredentials.getMemberId());
        memberCredentialsLogs.setAudit(3);//删除
        memberCredentialsLogs.setCredentialsCode(memberCredentials.getCredentialsCode());
        memberCredentialsLogs.setCreateTime(new Date());
        memberCredentialsLogs.setMemberCredentialsId(memberCredentialsLogs.getId());
        memberCredentialsLogs.setContent("删除资质");
        memberCredentialsLogs.setCreater(loginMemberId);
        memberCredentialsLogsMapper.insertSelective(memberCredentialsLogs);
    }
}
