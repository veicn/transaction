package com.sinochem.crude.trade.member.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.sinochem.crude.trade.member.domain.Credentials;
import com.sinochem.crude.trade.member.domain.CredentialsDetail;
import com.sinochem.crude.trade.member.domain.MemberCredentials;
import com.sinochem.it.b2b.common.exception.BizException;
import com.sinochem.it.b2b.common.page.PageInfoResult;

public interface MemberCredentialsService {

    /**
     * 通过企业id找到所有对应开通的资质
     */
    public List<String> getByMember(Long epMemberId);

    /**
     * 得到所有的角色
     */
    public List<String> getAllRoles();

    /**
     * 得到所有的资质详情
     *
     * @return
     */
    public List<CredentialsDetail> getAllCredentialsDetails();

    List<Credentials> getCredentials(boolean filter);

    List<Credentials> getCredentials();

    /**
     * 获取所有的审核页面
     *
     * @param pageInfo
     * @return
     */
    public PageInfoResult<MemberCredentials> selectCredentails(String name, Integer audit,
                                                               PageInfo pageInfo);

    /**
     * 审核通过资质
     *
     * @param id
     */
    public MemberCredentials setAudited(Long id) throws BizException;

    /**
     * 重新申请资质
     *
     * @param id
     * @return
     * @throws BizException
     */
    MemberCredentials setApply(Long id) throws BizException;

    /**
     * 驳回审核资质
     *
     * @param id
     * @param reason 理由
     */
    public void auditRejected(Long id, String reason, Long loginMemberId) throws BizException;

    void save(MemberCredentials memberCredentials);

    MemberCredentials getCredentials(Long memberId, Long type);

    MemberCredentials getCredentialsById(Long id);

    String[] getRolesByCode(String credentialsCode, boolean b);

    /**
     * 只存在驳回记录
     *
     * @param memberId
     * @return
     */
    List<MemberCredentials> getRejects(Long memberId);


   void updateUdbCredentials(String[] types,Long memberId);

    /**
     * 根据资质编号查询企业资质是否审核通过
     * @param memberId
     * @param code
     * @return
     */
    MemberCredentials queryEnterpriseCredential(Long memberId,String code);

}
