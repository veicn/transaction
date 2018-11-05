package com.sinochem.crude.trade.member.domain;

import java.util.Date;

public class VersionBean {
    private Long id;

    private Long versionCode;

    private String versionName;

    private String content;

    private String url;

    private Date createTime;

    private Long createUser;

    private Date updateTime;

    private Long updateUser;

    private String fileName;

    private String versionLanguage;

    private Long isForcedUpdate;

    public String getVersionLanguage() {
        return versionLanguage;
    }

    public void setVersionLanguage(String versionLanguage) {
        this.versionLanguage = versionLanguage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(Long versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName == null ? null : versionName.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Long updateUser) {
        this.updateUser = updateUser;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    public Long getIsForcedUpdate() {
        return isForcedUpdate;
    }

    public void setIsForcedUpdate(Long isForcedUpdate) {
        this.isForcedUpdate = isForcedUpdate;
    }
}