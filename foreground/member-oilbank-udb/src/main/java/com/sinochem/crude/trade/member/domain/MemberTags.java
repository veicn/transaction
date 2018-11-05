package com.sinochem.crude.trade.member.domain;

import java.io.Serializable;

public class MemberTags implements Serializable {
    /**
     * member_tags.id (编号)
     */
    private Long id;

    /**
     * member_tags.code (标识)
     * 0：个人；1：企业；2：企业+壹化网
     */
    private Integer code;

    /**
     * member_tags.description (描述)
     */
    private String description;

    /**
     * member_tags.owner (业务主体)
     */
    private String owner;

    /**
     * member_tags.member_id (用户名)
     */
    private Long memberId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }
}