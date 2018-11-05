package com.sinochem.crude.trade.member.service;

import com.sinochem.crude.trade.member.domain.*;
import com.sinochem.it.b2b.common.exception.BizException;

import java.util.List;

/**
 * 资质审查服务
 */
public interface SualificationService {

    /**
     * 添加客户资质审查
     * @param info
     * @return
     */
    public MemberCredentials addCrudeCustomerInfo(CrudeCustomerInfo info,Long memberId);

    /**
     * 添加炼厂资质
     * @param info
     * @return
     */
    public MemberCredentials addCrudePotrerillosInfo(CrudePotrerillosInfo info,Long memberId);

    /**
     * 添加供应商资质
     * @param info
     * @return
     */
    public MemberCredentials addCrudeProviderInfo(CrudeProviderInfo info,Long memberId);

    /**
     * 添加贸易商资质
     * @param info
     * @return
     */
    public MemberCredentials addCrudeTradingCompanyInfo(CrudeTradingCompanyInfo info,Long memberId);

    /**
     * 审核一个资质为通过
     * @param credential_id
     * @return
     */
    public boolean audit(Long credential_id);

    /**
     * 获取所有资质列表
     * @param memberId
     * @return
     */
    public List<MemberCredentials> getRedentials(Long memberId);

    /**
     * 添加客户资质审查
     * @param info
     * @return
     */
    public CrudeCustomerInfo getCrudeCustomerInfo(Long memberId);

    /**
     * 添加炼厂资质
     * @param info
     * @return
     */
    public CrudePotrerillosInfo getCrudePotrerillosInfo(Long memberId);

    /**
     * 添加供应商资质
     * @param info
     * @return
     */
    public CrudeProviderInfo getCrudeProviderInfo(Long memberId);

    /**
     * 添加贸易商资质
     * @param info
     * @return
     */
    public CrudeTradingCompanyInfo getCrudeTradingCompanyInfo(Long memberId);

    /**
     * 添加船代资质
     * @param info
     * @param epMemberId
     */
    public MemberCredentials addCrudeShipAgencyInfo(CrudeShipAgencyInfo info, Long epMemberId);

    /**
     * 获得船代资质详情
     * @param epMemberId
     * @return
     */
    public CrudeShipAgencyInfo getCrudeShipAgencyInfo(Long epMemberId);


    public Enterprise getEnterprisesInfo(Long memberId);

    /**
     * 申请各种资质
     * @param epMemberId 用户编号
     * @param t 资质code
     */
    public void requestSualification(Long epMemberId, String code) throws BizException;

    /**
     * 删除资质
     * @param epMemberId
     * @param code
     */
    void credentialsDelete(Long epMemberId, String code,Long loginMemberId)throws BizException;
}
