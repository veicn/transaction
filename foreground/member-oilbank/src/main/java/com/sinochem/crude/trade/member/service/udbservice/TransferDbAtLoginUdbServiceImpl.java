
package com.sinochem.crude.trade.member.service.udbservice;

import com.eyeieye.melody.web.url.URLBroker;
import com.sinochem.crude.trade.member.dao.*;
import com.sinochem.crude.trade.member.domain.*;
import com.sinochem.crude.trade.member.model.udbvo.PersonUdbVo;
import com.sinochem.crude.trade.member.model.udbvo.UdbEnterpriseContactVo;
import com.sinochem.crude.trade.member.model.udbvo.UdbEnterpriseVO;
import com.sinochem.crude.trade.member.service.CredentialsDao;
import com.sinochem.crude.trade.member.service.MemberCredentialsService;
import com.sinochem.crude.trade.member.util.UdbResult;
import com.sinochem.it.b2b.common.exception.BizException;
import com.sinochem.it.b2b.member.user.User;
import com.sun.tools.javac.comp.Enter;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Summer on 2018-08-08.
 */
@Service
public class TransferDbAtLoginUdbServiceImpl {

    private static final Logger logger = LoggerFactory.getLogger(TransferDbAtLoginUdbServiceImpl.class);

    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private PersonUdbServiceImpl personUdbService;
    @Autowired
    private PersonMapper personMapper;
    @Autowired
    private EnterpriseMapper enterpriseMapper;
    @Autowired
    private EnterpriseUdbServiceImpl enterpriseUdbService;
    @Autowired
    private EnterpriseContactUdbService enterpriseContactUdbService;
    @Autowired
    private EnterpriseCrudeMapper enterpriseCrudeMapper;
    @Autowired
    private EnterpriseContactMapper enterpriseContactMapper;



    @Autowired
    private MemberCredentialsService memberCredentialsService;


//    @Async
    public void transferDbforPerson(User user) throws BizException, InterruptedException {
        Long memerId = user.getMemberId();
        if (memerId == null) throw new BizException("memberId不能为空");
        //获得udbUUid
        String udbUUid = memberMapper.selectUdbUUidByMemberId(memerId);
        if (!StringUtils.isBlank(udbUUid)) {
            //根据udbUUid查询udb里的person
            UdbResult<PersonUdbVo> udbResult = personUdbService.selectUdb(udbUUid);
            PersonUdbVo personUdbVo = udbResult.getResponse();

            if (personUdbVo != null) {
                Person person = new Person();
                person.transferPersonUdbVoToPersonVo(personUdbVo);

                Date time = new Date();
                //根据memberId查person的id
                Person person1 = personMapper.selectByMemberId(memerId);
                person.setId(person1.getId());
                person.setMemberId(user.getBizMemberId());
                person.setUpdateUser(memerId.toString());
                person.setUpdateTime(time);

                //更新person表
                personMapper.updateByPrimaryKeySelective(person);

            }
        }
    }




    //  同步enterprise表,enterpriseCrude表和enterpriseContact表
//    @Async
    public void transferDbforEnterprise(User user) throws BizException, InterruptedException {
        //1.根据memberid查询enterprise表的id 和 udbuuid,member的udbuuid
        Long memberId = user.getMemberId();
        if (memberId == null) throw new BizException("memberId不能为空");
        Enterprise enterprise = enterpriseMapper.getByMemberId(memberId);
        Member member = memberMapper.selectByPrimaryKey(memberId);
        EnterpriseContact eContact = enterpriseContactMapper.selectByMemberId(memberId);

        String memberUid = member.getUdbUUid();


        //2、从udb里查询公司信息
        Long enterpriseId = null;
        UdbEnterpriseVO udbEnterpriseVO = null;
        if(enterprise != null && enterprise.getUdbUuid()!=null){
            enterpriseId = enterprise.getId();
            String enterpriseUid = enterprise.getUdbUuid();
            //2.查询udbenterprise的信息
            if (enterpriseUid == null || StringUtils.isBlank(memberUid) || enterpriseId == null)
                throw new BizException("enterpriseId 和 udbUUid 和 enterprise Code不能为空");
            UdbResult<UdbEnterpriseVO> udbResult = enterpriseUdbService.udbSelectSingleEnterprise(memberUid, enterpriseUid);
            udbEnterpriseVO = udbResult.getResponse();
            //如果企业类型不为空，则需要刷下企业资质表

            if(udbEnterpriseVO!= null && udbEnterpriseVO.getEnterpriseType()!=null && udbEnterpriseVO.getEnterpriseType().length>0){
                String[] types = udbEnterpriseVO.getEnterpriseType();
                memberCredentialsService.updateUdbCredentials(types,memberId);

            }
        }else{
            logger.error("业务系统刷信息memberId:" + memberId + ":——>未查到公司信息");
            return;
        }

        //3、从udb里查询相应的企业联系人信息
        UdbEnterpriseContactVo udbEnterpriseContactVo = null;
        if(eContact != null && eContact.getUdbUuid()!=null){
            String contactUid = eContact.getUdbUuid();
            //3.查询enterpriseContactUdb的信息
            UdbResult<UdbEnterpriseContactVo> udbEnterpriseContactResult = enterpriseContactUdbService.udbSelect(memberUid, contactUid);
            udbEnterpriseContactVo = udbEnterpriseContactResult.getResponse();
        }else{
            logger.error("业务系统刷信息memberId:" + memberId + ":——>未查到企业联系人信息");
        }

        //4.把udbEnterpriseVO转换成enterprisevo,enterpriseCrudeVo,把udbEnterpriseContactVo转换成enterpriseContactVo
        if (udbEnterpriseVO == null) throw new BizException("udb的enterprise为空，更新本地enterprise失败");
        //把UdbEnterpriseVO转换成Enterprise
        Enterprise enterprise1 = udbEnterpriseVO.convertToEnterpriseVo();
        enterprise1.setMemberId(memberId);
        enterprise1.setId(enterpriseId);

        //UdbEnterpriseVO转成enterpriseCrude
        EnterpriseCrude enterpriseCrude = udbEnterpriseVO.convertToEnterpriseCrudeVo(memberId, enterpriseId);
        EnterpriseCrude crude = enterpriseCrudeMapper.selectByEnterpriseId(enterpriseId);
        if (crude != null && crude.getId() != null) {
            enterpriseCrude.setId(crude.getId());
            if(enterpriseCrude != null){
                enterpriseCrudeMapper.updateByPrimaryKeySelective(enterpriseCrude);
            }
        }

        //把UdbEnterpriseContactVo转成enterpriseContact
        EnterpriseContact contact = udbEnterpriseContactVo.convertToEnterpriseContactVo(memberId, enterpriseId);
        contact.setId(eContact.getId());

        //5.更新本地enterprise表,enterpriseCrude,enterpriseContact表
        if(enterprise1 != null){
            enterpriseMapper.updateByPrimaryKeySelective(enterprise1);
        }

        if(contact!=null){
            enterpriseContactMapper.updateByPrimaryKeySelective(contact);
        }


    }

}
