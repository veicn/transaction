package com.sinochem.crude.trade.member.service.udbservice;

import com.alibaba.fastjson.JSON;
import com.sinochem.crude.trade.member.contact.UdbCodeConstant;
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
 * udb添加公司时，添加法人信息的接口
 * Created by wgp on 2018/8/3.
 */
@Component
public class EnterpriseCrudeUdbService {

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

    @Autowired
    private EnterpriseBankUdbService enterpriseBankUdbService;

    /**
     * 更新企业银行开票信息和法人信息
     * @param enterpriseCrude
     * @return
     * @throws BizException
     */
    public UdbResult<UdbEnterpriseVO> udbUpdate(EnterpriseCrude enterpriseCrude){

        //获取必要参数
        UdbEnterpriseVO vo = getUdbEnterpriseVo(enterpriseCrude);
        if(vo == null || vo.getAccountUid()==null||vo.getEnterpriseUid()==null){
            return new UdbResult<>("缺少必要的参数！");
        }
        String result = helper.httpPut(vo, UdbUrlMapping.ENTERPRISE_UPDATE);
        UdbResult udbObj =  JSON.parseObject(result, UdbResult.class );
        if(UdbCodeConstant.SUCCESS.equals(udbObj.getCode())){
            EnterpriseBankUdbVo enterpriseBankUdbVo = new EnterpriseBankUdbVo();
            if(enterpriseCrude.getUdbUuid() == null){
                EnterpriseCrude dbEnterpriseCurde = enterpriseCrudeMapper.selectByMemberId(enterpriseCrude.getMemberId());
                enterpriseCrude.setUdbUuid(dbEnterpriseCurde.getUdbUuid());
            }
            enterpriseBankUdbVo.convertToEnterpriseBankUdbVo(enterpriseCrude, vo.getAccountUid(), vo.getEnterpriseUid(),WEBSITE);
            if(check(enterpriseBankUdbVo)){
                UdbResult udbResult = enterpriseBankUdbService.udbUpdate(enterpriseBankUdbVo);
                return udbResult;
            }
        }
        return udbObj;
    }

    /**
     * 获取 更新/添加  企业银行开票信息和企业法人信息接口 需要的参数
     * @param enterpriseCrude
     * @return
     */
    private UdbEnterpriseVO getUdbEnterpriseVo(EnterpriseCrude enterpriseCrude){
        if(enterpriseCrude== null || enterpriseCrude.getMemberId()==null){
            return null;
        }
        Long memberId = enterpriseCrude.getMemberId();
        Member member = memberMapper.selectByPrimaryKey(memberId);
        Enterprise enterprise = enterpriseMapper.enterpersiceDetail(memberId);
        UdbEnterpriseVO vo = new UdbEnterpriseVO();
        if(member == null || enterprise==null || enterprise.getUdbUuid()==null || member.getUdbUUid()==null){
            return null;
        }
        vo.convertToEnterpriseCrudeUdbVo(enterpriseCrude,WEBSITE,member.getUdbUUid(),enterprise.getUdbUuid());
        return vo;
    }


    /**
     * 名为删除，其为更新法人和企业银行开票信息
     * @param id
     * @return
     * @throws BizException
     */
    public UdbResult udbDelete(Long id)throws BizException {
        EnterpriseCrude dbEnterpriseCrude = enterpriseCrudeMapper.selectByPrimaryKey(id);
        EnterpriseCrude enterpriseCrude = new EnterpriseCrude();
        if(dbEnterpriseCrude != null){
            enterpriseCrude.setMemberId(dbEnterpriseCrude.getMemberId());
            return udbUpdate(enterpriseCrude);
        }else{
            throw new BizException("没有该法人信息，无法删除");
        }

    }


    /**
     * 插入企业企业银行开票信息和法人信息
     * @param enterpriseCrude
     * @return
     */
    public UdbResult udbInsert(EnterpriseCrude enterpriseCrude) {

        UdbEnterpriseVO vo = getUdbEnterpriseVo(enterpriseCrude);
        if(vo == null || vo.getAccountUid()==null||vo.getEnterpriseUid()==null){
            return new UdbResult<>("缺少必要的参数！");
        }
        String result = helper.httpPost(vo, UdbUrlMapping.ENTERPRISE_UPDATE);
        UdbResult udbObj =  JSON.parseObject(result, UdbResult.class );
        if(UdbCodeConstant.SUCCESS.equals(udbObj.getCode())){
            EnterpriseBankUdbVo enterpriseBankUdbVo = new EnterpriseBankUdbVo();
            enterpriseBankUdbVo.convertToEnterpriseBankUdbVo(enterpriseCrude, vo.getAccountUid(), vo.getEnterpriseUid(),WEBSITE);
            if(check(enterpriseBankUdbVo)){
                UdbResult udbResult = enterpriseBankUdbService.udbInsert(enterpriseBankUdbVo);
                return udbResult;
            }
        }
        return udbObj;

    }

    private Boolean check(EnterpriseBankUdbVo enterpriseBankUdbVo){
        if(enterpriseBankUdbVo == null){
            return false;
        }
        if(enterpriseBankUdbVo.getBillingBankAccount() != null){
            return false;
        }
        if(enterpriseBankUdbVo.getBillingBankName() != null){
            return false;
        }
        if(enterpriseBankUdbVo.getInvoiceAddress() != null){
            return false;
        }
        if( enterpriseBankUdbVo.getContactTelephone() != null){
            return false;
        }
        return true;

    }






}
