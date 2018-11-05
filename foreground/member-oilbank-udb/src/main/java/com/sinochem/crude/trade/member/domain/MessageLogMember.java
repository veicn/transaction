package com.sinochem.crude.trade.member.domain;

import java.util.Date;

public class MessageLogMember {
    private Long id;

    private Long messageLogId;

    private Long memberId;

    private String response;

    private Boolean success;

    private Date createTime;

    private String memberName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMessageLogId() {
        return messageLogId;
    }

    public void setMessageLogId(Long messageLogId) {
        this.messageLogId = messageLogId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public Boolean isSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }


    public MessageLogMember() {
    }

    public MessageLogMember(Long memberId) {
        this.memberId = memberId;
    }

    public MessageLogMember(Long messageLogId, Long memberId) {
        this.messageLogId = messageLogId;
        this.memberId = memberId;
    }
}