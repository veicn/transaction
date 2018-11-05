package com.sinochem.crude.trade.member.model;

import com.sinochem.crude.trade.member.domain.PrizeCollection;

import java.util.Date;

public class PrizeCollectionVO {

    private Long id;

    private String content;

    private String enclosureUrl;

    private String enclosureName;

    private String contactUser;

    private String contactTelephone;

    private String contactEmail;

    private Date createTime;

    public PrizeCollectionVO(PrizeCollection prizeCollection) {
        this.id = prizeCollection.getId();
        this.content = prizeCollection.getContent();
        this.enclosureUrl = prizeCollection.getEnclosureUrl();
        this.enclosureName = prizeCollection.getEnclosureName();
        this.contactUser = prizeCollection.getContactUser();
        this.contactTelephone = prizeCollection.getContactTelephone();
        this.contactEmail = prizeCollection.getContactEmail();
        this.createTime = prizeCollection.getCreateTime();
    }

    public PrizeCollectionVO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getEnclosureUrl() {
        return enclosureUrl;
    }

    public void setEnclosureUrl(String enclosureUrl) {
        this.enclosureUrl = enclosureUrl;
    }

    public String getEnclosureName() {
        return enclosureName;
    }

    public void setEnclosureName(String enclosureName) {
        this.enclosureName = enclosureName;
    }

    public String getContactUser() {
        return contactUser;
    }

    public void setContactUser(String contactUser) {
        this.contactUser = contactUser;
    }

    public String getContactTelephone() {
        return contactTelephone;
    }

    public void setContactTelephone(String contactTelephone) {
        this.contactTelephone = contactTelephone;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
