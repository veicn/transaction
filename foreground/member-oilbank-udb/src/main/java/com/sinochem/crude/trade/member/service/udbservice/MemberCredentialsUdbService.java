package com.sinochem.crude.trade.member.service.udbservice;

import com.alibaba.fastjson.JSON;
import com.sinochem.crude.trade.member.contact.MsgConstant;
import com.sinochem.crude.trade.member.contact.UdbCodeConstant;
import com.sinochem.crude.trade.member.contact.UdbUrlMapping;
import com.sinochem.crude.trade.member.dao.*;
import com.sinochem.crude.trade.member.domain.*;
import com.sinochem.crude.trade.member.helper.HttpClientHelper;
import com.sinochem.crude.trade.member.model.EnterpriseCredentialsEnum;
import com.sinochem.crude.trade.member.model.udbvo.UdbEnterpriseContactVo;
import com.sinochem.crude.trade.member.model.udbvo.UdbEnterpriseVO;
import com.sinochem.crude.trade.member.service.EnterpriseContactService;
import com.sinochem.crude.trade.member.service.impl.CredentialsDaoImpl;
import com.sinochem.crude.trade.member.util.UdbResult;
import com.sinochem.it.b2b.common.exception.BizException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.rmi.MarshalException;
import java.util.ArrayList;
import java.util.List;

/**
 * 废弃类，资质通过审核后，才对调用UDB的资质接口
 * Created by wgp on 2018/7/30.
 */

@Component
public class MemberCredentialsUdbService {

    private static final Logger logger = LoggerFactory.getLogger(MemberCredentialsUdbService.class);

    @Autowired
    private HttpClientHelper helper;

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private EnterpriseMapper enterpriseMapper;

    @Autowired
    private MemberCredentialsMapper memberCredentialsMapper;

    @Autowired
    private CredentialsDaoImpl credentialsDao;

    @Autowired
    private EnterpriseContactMapper enterpriseContactMapper;

    @Autowired
    private EnterpriseCrudeMapper enterpriseCrudeMapper;

    @Autowired
    private EnterpriseUdbServiceImpl enterpriseUdbService;

    @Autowired
    private EnterpriseContactUdbService enterpriseContactUdbService;
    @Autowired
    private EnterpriseCrudeUdbService enterpriseCrudeUdbService;

    @Autowired
    private EnterpriseContactService enterpriseContactService;


    @Value("${UDB.webSite}")
    private String WEBSITE;

    /**
     * 并且当企业通过审核以后，才可以对udb添加企业类型
     * 添加企业资质信息,对Udb只添加一个企业类型 enterprises表
     * @param memberCredentials
     * @return
     * @throws BizException
     */
    public UdbResult udbInsertCredentials(MemberCredentials memberCredentials)throws BizException{
        //1、根据 memberId查询出 账户信息
        if(memberCredentials==null && memberCredentials.getMemberId() == null){
            logger.error("资质信息缺少memberId参数");
            throw new BizException();
        }
        Member member = memberMapper.selectByPrimaryKey(memberCredentials.getMemberId());

        //2、根据memberid查出公司信息
        if(member == null && member.getId() == null && member.getUdbUUid() == null){
            logger.error(memberCredentials.getMemberId() + "账户不存在");
            throw new BizException();
        }
        //3、 把企业信息和法人信息插入到udb里。
        Enterprise enterprise = enterpriseMapper.getByMemberId(member.getId());
        EnterpriseCrude enterpriseCrude = enterpriseCrudeMapper.selectByMemberId(member.getId());
        UdbEnterpriseVO udbVo = new UdbEnterpriseVO();
        udbVo.setAccountUid(member.getUdbUUid());
        UdbEnterpriseVO vo = new UdbEnterpriseVO();
        vo.convertAllToUdbVo(enterprise,enterpriseCrude,WEBSITE,member.getUdbUUid(),null);
        UdbResult<UdbEnterpriseVO>  enterpriseResult = enterpriseUdbService.udbInsert(vo);
        if( !UdbCodeConstant.SUCCESS.equals(enterpriseResult.getCode()) ){
            logger.info(enterpriseResult.getCode().toString(),enterpriseResult.getMessage());
            throw new BizException();
        }
        enterprise.setUdbUuid(enterpriseResult.getResponse().getEnterpriseUid());
        //3.1 更新uuid到 公司表
        enterpriseMapper.updateByPrimaryKey(enterprise);



        //4、把企业联系人插入到udb里。
        EnterpriseContact enterpriseContact = enterpriseContactMapper.selectByMemberId(member.getId());
        if(enterpriseContact == null){
            return enterpriseResult;
        }
        UdbEnterpriseContactVo enterpriseContactVo = new UdbEnterpriseContactVo();
        String accountUid = member.getUdbUUid();
        String enterpriseUid = enterpriseResult.getResponse().getEnterpriseUid();
        enterpriseContactVo.convertToUdbVo(enterpriseContact,accountUid,enterpriseUid,WEBSITE);
        UdbResult<UdbEnterpriseContactVo> contactResult =  enterpriseContactUdbService.udbInsert(enterpriseContactVo);
        //4.1更新联系人的uuid到企业联系人表
        enterpriseContact.setUdbUuid(contactResult.getResponse().getEntpContsUid());
        enterpriseContactMapper.updateByPrimaryKey(enterpriseContact);
        return contactResult;
    }

    public UdbResult updateEnterpriseCredential(MemberCredentials memberCredentials){

        Member member = memberMapper.selectByPrimaryKey(memberCredentials.getMemberId());
        Enterprise enterprise = enterpriseMapper.getByMemberId(memberCredentials.getMemberId());
        List<MemberCredentials> list = memberCredentialsMapper.selectByMemberId(member.getId());
        List<String> strList = credentialsDao.getUdbCodesByList(list);
        String[] types = new String[strList.size()];
        strList.toArray(types);
        UdbEnterpriseVO udbVo = new UdbEnterpriseVO();
        udbVo.setAccountUid(member.getUdbUUid());
        udbVo.setEnterpriseUid(enterprise.getUdbUuid());
        udbVo.setEnterpriseType(types);
        udbVo.setFullName(enterprise.getFullName());
        udbVo.setWebSite(WEBSITE);
        String result = helper.httpPost(udbVo,UdbUrlMapping.ENTERPRISE_UPDATE);
        UdbResult udbResult = JSON.parseObject(result,UdbResult.class);
        return udbResult;
    }



}
