package com.sinochem.crude.trade.member.service.udbservice;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.sinochem.crude.trade.member.contact.UdbUrlMapping;
import com.sinochem.crude.trade.member.dao.EnterpriseMapper;
import com.sinochem.crude.trade.member.dao.MemberMapper;
import com.sinochem.crude.trade.member.domain.Enterprise;
import com.sinochem.crude.trade.member.domain.Member;
import com.sinochem.crude.trade.member.helper.HttpClientHelper;
import com.sinochem.crude.trade.member.model.udbvo.UdbEnterpriseVO;
import com.sinochem.crude.trade.member.util.UdbResult;
import com.sinochem.it.b2b.common.exception.BizException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wgp on 2018/7/27.
 */
@Component
public class EnterpriseUdbServiceImpl {

    @Autowired
    private HttpClientHelper helper;

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private EnterpriseMapper  enterpriseMapper;

    @Value("${UDB.webSite}")
    private String WEBSITE;

    /**
     * 插入企业信息  enterprises表
     * @param enterprise
     * @return
     * @throws BizException
     */
    public   UdbResult<UdbEnterpriseVO> udbInsert(Enterprise enterprise){
         /* 注意 注册区域境内的话：是三证合一的话，得有统一企业信用吗。
            不是三证合一的话，必须得工商执照代码。
            无论境外还是境内必须有公司的全名。
        */
        Long memberId = enterprise.getMemberId();
        Member member = memberMapper.selectByPrimaryKey(memberId);
        UdbEnterpriseVO vo = new UdbEnterpriseVO();
        vo.convertToUdbVo(enterprise,WEBSITE,member.getUdbUUid());
        String result = helper.httpPost(vo, UdbUrlMapping.ENTERPRISE_ADD);
        UdbResult<UdbEnterpriseVO> udbObj = (UdbResult<UdbEnterpriseVO>)JSON.parseObject(result,new TypeReference<UdbResult<UdbEnterpriseVO>>(){} );
       return udbObj;
    }

    /**
     * 更新企业信息  enterprises表
     * @param enterprise
     * @return
     * @throws BizException
     */
    public  UdbResult<UdbEnterpriseVO> udbUpdate(Enterprise enterprise){
        //udb接口需要公司全局uid和账户全局uid参数，所以需要查询出members的udb_uid和enterprises表的udb_uid
        if(enterprise.getUdbUuid() == null){
            Enterprise dbEnterprise = enterpriseMapper.selectByPrimaryKey(enterprise.getId());
            enterprise.setUdbUuid(dbEnterprise.getUdbUuid());
        }
        Long memberId = enterprise.getMemberId();
        Member member = memberMapper.selectByPrimaryKey(memberId);

        UdbEnterpriseVO vo = new UdbEnterpriseVO();
        vo.convertToUdbVo(enterprise,WEBSITE,member.getUdbUUid());
        String result = helper.httpPut(vo, UdbUrlMapping.ENTERPRISE_UPDATE);
        UdbResult<UdbEnterpriseVO> udbObj = (UdbResult<UdbEnterpriseVO>)JSON.parseObject(result,new TypeReference<UdbResult<UdbEnterpriseVO>>(){} );
        return udbObj;
    }


    public UdbResult<UdbEnterpriseVO> udbSelectSingleEnterprise(String accountUuid,String enterpriseUid){
        UdbEnterpriseVO vo = new UdbEnterpriseVO();
        vo.setAccountUid(accountUuid);
        vo.setEnterpriseUid(enterpriseUid);
        vo.setWebSite(WEBSITE);
        String result = helper.httpGet(vo, UdbUrlMapping.ENTERPRISE_DETAILS);
        UdbResult<UdbEnterpriseVO> udbObj = (UdbResult<UdbEnterpriseVO>)JSON.parseObject(result,new TypeReference<UdbResult<UdbEnterpriseVO>>(){} );
        return udbObj;

    }

    //查询公司集合
    public List<Enterprise> udbSelectEnterpriseListByName(String name){
        UdbEnterpriseVO vo = new UdbEnterpriseVO();
       vo.setFullName(name);
        vo.setWebSite(WEBSITE);
        String result = helper.httpGet(vo, UdbUrlMapping.ENTERPRISE_DETAILS);
        UdbResult<UdbEnterpriseVO> udbObj = (UdbResult<UdbEnterpriseVO>)JSON.parseObject(result,UdbResult.class);
        List<UdbEnterpriseVO> list = JSONObject.parseArray(udbObj.getResponse().toString(),UdbEnterpriseVO.class);
        List<Enterprise> enterpriseList = new ArrayList<>();
        for (UdbEnterpriseVO listVo:list) {
            Enterprise enterprise = listVo.convertToEnterpriseVo();
            enterpriseList.add(enterprise);
        }
        return enterpriseList;
    }

    /**
     * 外部方法做公司信息的转换 该方法直接插入
     * @param vo
     * @return
     */
    public   UdbResult<UdbEnterpriseVO> udbInsert(UdbEnterpriseVO vo){
         /* 注意 注册区域境内的话：是三证合一的话，得有统一企业信用吗。
            不是三证合一的话，必须得工商执照代码。
            无论境外还是境内必须有公司的全名。
        */
        String result = helper.httpPost(vo, UdbUrlMapping.ENTERPRISE_ADD);
        UdbResult<UdbEnterpriseVO> udbObj = (UdbResult<UdbEnterpriseVO>)JSON.parseObject(result,new TypeReference<UdbResult<UdbEnterpriseVO>>(){} );
        return udbObj;
    }

}
