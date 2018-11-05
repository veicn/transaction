package com.sinochem.crude.trade.member.domain;

import java.util.Date;

public class MemberCredentialsLogs {
    private Long id;

    /**
     *会员资质id
     */
    private Long memberCredentialsId;

    /**
     * 会员id
     */
    private Long memberId;

    /**
     * 资质的Code
     */
    private String credentialsCode;

    /**
     * 审核状态：0-申请中 1-审批通过 2-驳回
     */
    private Integer audit;

    /**
     * 内容
     */
    private String content;

    private Long creater;

    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMemberCredentialsId() {
        return memberCredentialsId;
    }

    public void setMemberCredentialsId(Long memberCredentialsId) {
        this.memberCredentialsId = memberCredentialsId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getCredentialsCode() {
        return credentialsCode;
    }

    public void setCredentialsCode(String credentialsCode) {
        this.credentialsCode = credentialsCode == null ? null : credentialsCode.trim();
    }

    public Integer getAudit() {
        return audit;
    }

    public void setAudit(Integer audit) {
        this.audit = audit;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Long getCreater() {
        return creater;
    }

    public void setCreater(Long creater) {
        this.creater = creater;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}