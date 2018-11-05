package com.sinochem.crude.trade.member.service.udbservice;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.sinochem.crude.trade.member.contact.UdbUrlMapping;
import com.sinochem.crude.trade.member.dao.EnterpriseCrudeMapper;
import com.sinochem.crude.trade.member.dao.EnterpriseMapper;
import com.sinochem.crude.trade.member.dao.MemberMapper;
import com.sinochem.crude.trade.member.domain.Enterprise;
import com.sinochem.crude.trade.member.domain.EnterpriseCrude;
import com.sinochem.crude.trade.member.domain.Member;
import com.sinochem.crude.trade.member.helper.HttpClientHelper;
import com.sinochem.crude.trade.member.model.udbvo.EnterpriseBankUdbVo;
import com.sinochem.crude.trade.member.model.udbvo.UdbEnterpriseVO;
import com.sinochem.crude.trade.member.util.UdbResult;
import com.sinochem.it.b2b.common.exception.BizException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by wgp on 2018/8/4.
 */

@Component
public class EnterpriseBankUdbService {
    @Autowired
    private HttpClientHelper helper;

    @Value("${UDB.webSite}")
    private String WEBSITE;

    @Autowired
    private EnterpriseMapper enterpriseMapper;
    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private EnterpriseCrudeMapper enterpriseCrudeMapper;


    /**
     * 插入企业银行开票信息
     * @param enterpriseBankUdbVo
     * @return
     * @throws BizException
     */
    public UdbResult<EnterpriseBankUdbVo> udbInsert(EnterpriseBankUdbVo enterpriseBankUdbVo){
        if(enterpriseBankUdbVo== null || enterpriseBankUdbVo.getAccountUid()==null || enterpriseBankUdbVo.getEnterpriseUid()==null){
            return new UdbResult<>("必要参数为空！");
        }
        String result = helper.httpPost(enterpriseBankUdbVo, UdbUrlMapping.ENTERPRISE_BANK_ADD);
        UdbResult<EnterpriseBankUdbVo> udbObj =  JSON.parseObject(result, new TypeReference<UdbResult<EnterpriseBankUdbVo>>(){});
        return udbObj;
    }

    /**
     * 更新企业银行开票信息
     * @param enterpriseBankUdbVo
     * @return
     * @throws BizException
     */
    public UdbResult<EnterpriseBankUdbVo> udbUpdate(EnterpriseBankUdbVo enterpriseBankUdbVo){
        if(enterpriseBankUdbVo== null || enterpriseBankUdbVo.getAccountUid()==null || enterpriseBankUdbVo.getEnterpriseUid()==null){
            return new UdbResult<>("必要参数为空！");
        }
        String result = helper.httpPut(enterpriseBankUdbVo, UdbUrlMapping.ENTERPRISE_BANK_UPDATE);
        UdbResult<EnterpriseBankUdbVo> udbObj =  JSON.parseObject(result, new TypeReference<UdbResult<EnterpriseBankUdbVo>>(){});
        return udbObj;
    }

}
