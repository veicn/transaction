package com.sinochem.crude.trade.member.model;

import com.sinochem.crude.trade.member.domain.VersionBean;

import java.util.Date;

public class VersionBeanVO {



    //数据库主键Id(隐藏)、版本号、版本名、更新内容、创建时间、操作列表(编辑、删除、下载)
    private Long id;

    private Long versionCode;

    private String versionName;

    private String content;

    private Date createTime;

    private String url;

    private String fileName;

    private String ossPath;

    private String versionLanguage;

    private Long isForcedUpdate;

    public String getVersionLanguage() {
        return versionLanguage;
    }

    public void setVersionLanguage(String versionLanguage) {
        this.versionLanguage = versionLanguage;
    }

    public VersionBeanVO(VersionBean versionBean) {
        this.id = versionBean.getId();
        this.versionCode = versionBean.getVersionCode();
        this.versionName = versionBean.getVersionName();
        this.content = versionBean.getContent();
        this.createTime = versionBean.getCreateTime();
        this.url = versionBean.getUrl();
        this.fileName = versionBean.getFileName();
        this.versionLanguage= versionBean.getVersionLanguage();
        this.isForcedUpdate= versionBean.getIsForcedUpdate();
    }

    public VersionBeanVO() {
    }


    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
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
        this.versionName = versionName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getOssPath() {
        return ossPath;
    }

    public void setOssPath(String ossPath) {
        this.ossPath = ossPath;
    }

    public Long getIsForcedUpdate() {
        return isForcedUpdate;
    }

    public void setIsForcedUpdate(Long isForcedUpdate) {
        this.isForcedUpdate = isForcedUpdate;
    }
}
