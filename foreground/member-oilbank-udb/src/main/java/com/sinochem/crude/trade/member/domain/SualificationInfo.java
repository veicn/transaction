package com.sinochem.crude.trade.member.domain;

/**
 * 资质材料
 */
public class SualificationInfo {

    /**
     * 所属账户
     */
    private Long memberId;

    /**
     * 对应资质编号
     */
    private Long credentialId;

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getCredentialId() {
        return credentialId;
    }

    public void setCredentialId(Long credentialId) {
        this.credentialId = credentialId;
    }
}
