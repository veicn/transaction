package com.sinochem.crude.trade.member.dao;

import com.sinochem.crude.trade.member.domain.Credentials;
import com.sinochem.crude.trade.member.domain.MemberCredentials;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MemberCredentialsMapper {

    /**
     * 查询会员资质（不包含驳回的）
     *
     * @param epMemberId
     * @return
     */
    public List<MemberCredentials> selectByMemberId(Long epMemberId);

    /**
     * 查询会员资质（只查询审核通过的）
     *
     * @param epMemberId
     * @return
     */
    public List<MemberCredentials> selectByMemberIdAudited(Long epMemberId);

    void insert(MemberCredentials memberCredentials);

    boolean updateAuditById(@Param("id") Long id);

    boolean updateApplyById(@Param("id") Long id);

    boolean auditRejectedById(@Param("id") Long id);

    List<MemberCredentials> selectAll(@Param("name") String name, @Param("audit") Integer audit);

    MemberCredentials getById(@Param("id") Long id);

    //不包含驳回的
    MemberCredentials getCredentials(@Param("memberId") Long memberId, @Param("code") Long code);

    boolean deleteAuditById(@Param("id") Long id);

    List<MemberCredentials> getRejects(@Param("memberId") Long memberId);

    List<MemberCredentials> getList(MemberCredentials param);


    int deleteListByMemberIdAndAudit( int audit,Long memberId);

    int insertAll( List<MemberCredentials> list);

    MemberCredentials queryEnterpriseCredential(@Param("id") Long id, @Param("code") String code);
}
