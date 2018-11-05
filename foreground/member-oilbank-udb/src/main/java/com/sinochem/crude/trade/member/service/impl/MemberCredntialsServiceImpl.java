package com.sinochem.crude.trade.member.service.impl;

import com.eyeieye.melody.util.ArrayUtil;
import com.eyeieye.melody.web.url.URLBroker;
import com.github.pagehelper.PageInfo;
import com.sinochem.crude.trade.member.contact.MsgConstant;
import com.sinochem.crude.trade.member.dao.MemberCredentialsLogsMapper;
import com.sinochem.crude.trade.member.dao.MemberCredentialsMapper;
import com.sinochem.crude.trade.member.domain.*;
import com.sinochem.crude.trade.member.model.EnterpriseCredentialsEnum;
import com.sinochem.crude.trade.member.service.CredentialsDao;
import com.sinochem.crude.trade.member.service.EnterpriseService;
import com.sinochem.crude.trade.member.service.MemberCredentialsService;
import com.sinochem.crude.trade.member.service.MessageService;
import com.sinochem.it.b2b.common.CommonUtils;
import com.sinochem.it.b2b.common.exception.BizException;
import com.sinochem.it.b2b.common.http.HttpConnectionManager;
import com.sinochem.it.b2b.common.page.PageInfoResult;
import com.sinochem.it.b2b.common.page.PageUtils;
import com.sinochem.it.b2b.common.utils.VisitorLocale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MemberCredntialsServiceImpl implements MemberCredentialsService, InitializingBean {


    private static Logger logger = LoggerFactory.getLogger(MemberCredntialsServiceImpl.class);


    @Autowired
    EnterpriseService enterpriseService;
    @Autowired
    private MemberCredentialsLogsMapper memberCredentialsLogsMapper;


    class MockCredentials extends Credentials {
        public MockCredentials(String code, String name) {
            this.setCode(code);
            this.setName(name);
        }
    }

    @Autowired
    CredentialsDao credentialsDao;
    @Autowired
    MemberCredentialsMapper memberCredentialsMapper;



    @Autowired
    MessageService messageService;

    static List<Credentials> credentials = new ArrayList<>();


    class MockCredentialsDetail extends CredentialsDetail {
        public MockCredentialsDetail(String code, Set<java.lang.String> roles) {
            this.setRoles(roles.toArray(new String[0]));
            this.setCode(code);
        }
    }

    static List<CredentialsDetail> credentialsDetailList = new ArrayList<>();

    @Override
    public List<String> getByMember(Long epMemberId) {
        List<MemberCredentials> memberCredentialsList = memberCredentialsMapper.selectByMemberId(epMemberId);
        Set<String> roleSet = new HashSet<>();
        for (MemberCredentials memberCredentials : memberCredentialsList) {

            if (memberCredentials.getAudit() == 1) {
                roleSet.add(memberCredentials.getCredentialsCode());
            }
        }
        return new ArrayList<>(roleSet);
    }

    @Override
    public List<String> getAllRoles() {

        return credentialsDao.getAllRoles();
    }

    @Override
    public List<CredentialsDetail> getAllCredentialsDetails() {
        return credentialsDetailList;
    }

    @Override
    public List<Credentials> getCredentials(boolean filter) {
        List<Credentials> list = new ArrayList<>();
        String[] exts = new String[]{"0", "99", "98"};
        for (Credentials credential : credentials) {
            if (filter == false || ArrayUtil.contains(exts, credential.getCode()) == false) {
                list.add(credential);
            }
        }
        return list;
    }

    @Override
    public List<Credentials> getCredentials() {
        return credentials;
    }

    //    @Override
    public boolean hasAllCredentials(Long epMemberId) {

        List<MemberCredentials> credentials = memberCredentialsMapper.selectByMemberIdAudited(epMemberId);
        if (ArrayUtil.isEmpty(credentials)) return false;
        for (CredentialsDetail credentialsDetail : credentialsDetailList) {
            for (MemberCredentials memberCredential : credentials) {

                if (memberCredential.getCredentialsCode().equals(credentialsDetail.getCode())) {
                    break;
                }
                return false;
            }
        }
        return true;
    }

    //    @Override
    public String[] getRolesByMemberId(Long epMemberId) {
        Set<String> roleCodeList = new HashSet<String>();
        List<MemberCredentials> credentials = memberCredentialsMapper.selectByMemberIdAudited(epMemberId);
        for (CredentialsDetail credentialsDetail : credentialsDetailList) {
            for (MemberCredentials memberCredential : credentials) {
                if (memberCredential.getCredentialsCode().equals(credentialsDetail.getCode())) {
                    //TODO
//                    roleCodeList.add(credentialsDetail.getRole());
                }
            }
        }
        return roleCodeList.toArray(new String[]{});
    }

    @Override
    public PageInfoResult<MemberCredentials> selectCredentails(String name, Integer audit, PageInfo pageInfo) {
        PageUtils.page(pageInfo);
        return new PageInfoResult<MemberCredentials>(memberCredentialsMapper.selectAll(name, audit));
    }



    /**
     * 审核通过资质
     * @param id
     * @return
     * @throws BizException
     */
    @Override
    public MemberCredentials setAudited(Long id) throws BizException {
        MemberCredentials memberCredentials = memberCredentialsMapper.getById(id);

        Map model = new HashMap();
        if (memberCredentials != null) {
            Credentials credentials = credentialsDao.getByCode(memberCredentials.getCredentialsCode());

            Enterprise enterprise = enterpriseService.getByMemberId(memberCredentials.getMemberId());
            String tplName = "enter_audited";
            String auditName = VisitorLocale.getByCurrentLanguage(MsgConstant.MEMBEROO1);
            if (enterprise.getEpType() != null && enterprise.getEpType() == 1) {
                tplName += "_en";
                auditName = "Credentials audit pass";
            }
            if (credentials != null) {
                model.put("cretName", credentials.getName());
                model.put("cretNameEn", credentials.getNameEn());
            }

            messageService.sendMessage(auditName
                    , memberCredentials.getMemberId(),
                    tplName, model);

            memberCredentialsMapper.updateAuditById(id);
        } else {
            throw new BizException("未找到申请记录：" + id);
        }
        return memberCredentials;
    }

    @Override
    public MemberCredentials setApply(Long id) throws BizException {
        MemberCredentials memberCredentials = memberCredentialsMapper.getById(id);
        if (memberCredentials == null) {
            throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.MEMBEROO29));
        }
        //理由写入日志
        MemberCredentialsLogs memberCredentialsLogs = new MemberCredentialsLogs();
        memberCredentialsLogs.setMemberId(memberCredentials.getMemberId());
        memberCredentialsLogs.setAudit(0);//申请
        memberCredentialsLogs.setCredentialsCode(memberCredentials.getCredentialsCode());
        memberCredentialsLogs.setCreateTime(new Date());
        memberCredentialsLogs.setMemberCredentialsId(id);
        memberCredentialsLogs.setContent("重新申请");
        memberCredentialsLogs.setCreater(CommonUtils.PLANTFORM_MEMBER_ID);
        memberCredentialsLogsMapper.insertSelective(memberCredentialsLogs);
        memberCredentialsMapper.updateApplyById(id);
        return memberCredentials;
    }

    @Override
    public void auditRejected(Long id, String reason, Long loginMemberId) throws BizException {
        MemberCredentials memberCredentials = memberCredentialsMapper.getById(id);
        if (memberCredentials == null) {
            throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.MEMBEROO29));
        }
        //理由写入日志
        MemberCredentialsLogs memberCredentialsLogs = new MemberCredentialsLogs();
        memberCredentialsLogs.setMemberId(memberCredentials.getMemberId());
        memberCredentialsLogs.setAudit(2);//驳回
        memberCredentialsLogs.setCredentialsCode(memberCredentials.getCredentialsCode());
        memberCredentialsLogs.setCreateTime(new Date());
        memberCredentialsLogs.setMemberCredentialsId(id);
        memberCredentialsLogs.setContent(reason);
        memberCredentialsLogs.setCreater(loginMemberId);
        memberCredentialsLogsMapper.insertSelective(memberCredentialsLogs);
        memberCredentialsMapper.auditRejectedById(id);
        //发送邮件
        Map model = new HashMap();
        Credentials credentials = credentialsDao.getByCode(memberCredentials.getCredentialsCode());
        Enterprise enterprise = enterpriseService.getByMemberId(memberCredentials.getMemberId());
        String tplName = "enter_not_audited";
        String auditName = VisitorLocale.getByCurrentLanguage(MsgConstant.MEMBEROO28);
        if (enterprise.getEpType() != null && enterprise.getEpType() == 1) {
            tplName += "_en";
            auditName = "Credentials audit pass";
        }
        if (credentials != null) {
            model.put("cretName", credentials.getName());
            model.put("cretNameEn", credentials.getNameEn());
        }
        model.put("reason", reason);
        messageService.sendMessage(auditName, memberCredentials.getMemberId(), tplName, model);
    }

    @Override
    public void save(MemberCredentials memberCredentials) {
        memberCredentialsMapper.insert(memberCredentials);
    }

    @Override
    public MemberCredentials getCredentials(Long memberId, Long type) {
        return memberCredentialsMapper.getCredentials(memberId, type);
    }

    @Override
    public MemberCredentials getCredentialsById(Long id) {
        return memberCredentialsMapper.getById(id);
    }

    @Override
    public String[] getRolesByCode(String credentialsCode, boolean b) {
        List<String> roles = new ArrayList<>();
        for (CredentialsDetail detail : credentialsDetailList) {
            if (detail.getCode().equals(credentialsCode)) {
                return detail.getRoles();
            }
        }
        return null;
    }

    @Override
    public List<MemberCredentials> getRejects(Long memberId) {
        //查出所有被驳回的资质请求
        List<MemberCredentials> rejectList = this.memberCredentialsMapper.getRejects(memberId);
        List<MemberCredentials> res = new ArrayList<MemberCredentials>();
        MemberCredentials memberCredentialsParam = new MemberCredentials();
        //对于只被驳回一次的请求，添加到集合里
        for (MemberCredentials memberCredentials : rejectList) {
            memberCredentialsParam.setMemberId(memberCredentials.getMemberId());
            memberCredentialsParam.setCredentialsCode(memberCredentials.getCredentialsCode());
            List<MemberCredentials> sizeList = this.memberCredentialsMapper.getList(memberCredentialsParam);
            if (sizeList.size() == 1) res.add(memberCredentials);
        }
        return res;
    }

    @Override
    public void updateUdbCredentials(String[] types, Long memberId) {
        //根据udb的code找出对应业务系统的资质code
         List<String> list = credentialsDao.getCodesByList(types);
         //如果list为null  或者长度为0 表示udb只有企业资质
         if(list == null || list.size()==0){
             return;
         }
         //1代表已经接受审核
         int audit = 1;
         //先删除全部的已经审核过去的资质
         memberCredentialsMapper.deleteListByMemberIdAndAudit(audit,memberId);
        // 然后添加从UDB拿过来的资质
        List<MemberCredentials> dbList = new ArrayList<>();
        for(int i=0;i<list.size();i++){
            MemberCredentials mc = new MemberCredentials();
            mc.setAudit(audit);
            mc.setCredentialsCode(list.get(i));
            mc.setMemberId(memberId);
            dbList.add(mc);
        }
        MemberCredentials mc = new MemberCredentials();
        mc.setMemberId(memberId);
        mc.setAudit(audit);
        mc.setCredentialsCode(Integer.valueOf(EnterpriseCredentialsEnum.ENTERPRISES.getCode()).toString());
        dbList.add(mc);
        memberCredentialsMapper.insertAll(dbList);


    }

    @Override
    public void afterPropertiesSet() throws Exception {
        credentials = credentialsDao.getCredentials();
        credentialsDetailList = credentialsDao.getCredentialsDetials();
    }
}
