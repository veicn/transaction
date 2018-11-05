package com.sinochem.crude.trade.member.service.udbservice;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.sinochem.crude.trade.member.contact.UdbUrlMapping;
import com.sinochem.crude.trade.member.dao.EnterpriseContactMapper;
import com.sinochem.crude.trade.member.dao.EnterpriseMapper;
import com.sinochem.crude.trade.member.dao.MemberMapper;
import com.sinochem.crude.trade.member.domain.Enterprise;
import com.sinochem.crude.trade.member.domain.EnterpriseContact;
import com.sinochem.crude.trade.member.domain.Member;
import com.sinochem.crude.trade.member.helper.HttpClientHelper;
import com.sinochem.crude.trade.member.model.udbvo.UdbEnterpriseContactVo;
import com.sinochem.crude.trade.member.util.UdbResult;
import com.sinochem.it.b2b.common.exception.BizException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by wgp on 2018/7/30.
 */

@Component
public class EnterpriseContactUdbService {


    @Autowired
    private HttpClientHelper helper;

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private EnterpriseMapper enterpriseMapper;

    @Autowired
    private EnterpriseContactMapper enterpriseContactMapper;


    @Value("${UDB.webSite}")
    private String WEBSITE;

    /**
     *
     * 添加企业的联系人
     * @param contact
     * @return
     * @throws BizException
     */
    public UdbResult<UdbEnterpriseContactVo> udbInsert(EnterpriseContact contact){
        Member member = memberMapper.selectByPrimaryKey(contact.getMemberId());
        Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(contact.getEnterpriseId());
        UdbEnterpriseContactVo vo = new UdbEnterpriseContactVo();
        String accountUid = member.getUdbUUid();
        String enterpriseUid = enterprise.getUdbUuid();
        vo.convertToUdbVo(contact,accountUid,enterpriseUid,WEBSITE);

        String result = helper.httpPost(vo,UdbUrlMapping.ENTERPRISE_CONTACT_ADD);
        UdbResult<UdbEnterpriseContactVo> udbResult = JSON.parseObject(result,new TypeReference<UdbResult<UdbEnterpriseContactVo>>(){});
        return udbResult;
    }

    /**
     *
     * 添加企业的联系人，无需做转换实体
     * @param enterpriseContactVo
     * @return
     * @throws BizException
     */
    public UdbResult<UdbEnterpriseContactVo> udbInsert(UdbEnterpriseContactVo enterpriseContactVo){
        String result = helper.httpPost(enterpriseContactVo,UdbUrlMapping.ENTERPRISE_CONTACT_ADD);
        UdbResult<UdbEnterpriseContactVo> udbResult = JSON.parseObject(result,new TypeReference<UdbResult<UdbEnterpriseContactVo>>(){});
        return udbResult;
    }
    /**
     * 更新企业的联系人
     * @param contact
     * @return
     */
    public UdbResult udbUpdate(EnterpriseContact contact){
        Member member = memberMapper.selectByPrimaryKey(contact.getMemberId());
        UdbEnterpriseContactVo vo = new UdbEnterpriseContactVo();
        String accountUid = member.getUdbUUid();
        if(contact.getUdbUuid() == null){
            EnterpriseContact enterpriseContact = enterpriseContactMapper.selectByMemberId(member.getId());
            contact.setUdbUuid(enterpriseContact.getUdbUuid());
        }
        vo.convertToUdbVo(contact,accountUid,null,WEBSITE);
        String result = helper.httpPut(vo,UdbUrlMapping.ENTERPRISE_CONTACT_UPDATE);
        UdbResult udbResult = JSON.parseObject(result,UdbResult.class);
        return udbResult;
    }


//    写查询
    public UdbResult<UdbEnterpriseContactVo> udbSelect(String accountUuid,String entpContsUid){
        UdbEnterpriseContactVo udbEnterpriseContactVo = new UdbEnterpriseContactVo();
        udbEnterpriseContactVo.setAccountUid(accountUuid);
        udbEnterpriseContactVo.setEntpContsUid(entpContsUid);
        udbEnterpriseContactVo.setWebSite(WEBSITE);
        String result = helper.httpGet(udbEnterpriseContactVo,UdbUrlMapping.ENTERPRISE_CONTACT_SELECT);
        UdbResult<UdbEnterpriseContactVo> udbResult = JSON.parseObject(result,new TypeReference<UdbResult<UdbEnterpriseContactVo>>(){});
        return udbResult;
    }

}

