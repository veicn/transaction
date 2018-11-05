package com.sinochem.crude.trade.blockchain.domain;

import java.util.Date;

public class TShipagentSofHead {
    private Long id;

    private String enterpriseName;

    private String enterpriseNameEn;

    private Long enterpriseId;

    private String selected;

    private String headContent;

    private Long createUser;

    private Date createDate;

    private Long updateUser;

    private Date updateDate;

    private String aliveFlag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName == null ? null : enterpriseName.trim();
    }

    public String getEnterpriseNameEn() {
        return enterpriseNameEn;
    }

    public void setEnterpriseNameEn(String enterpriseNameEn) {
        this.enterpriseNameEn = enterpriseNameEn == null ? null : enterpriseNameEn.trim();
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getSelected() {
        return selected;
    }

    public void setSelected(String selected) {
        this.selected = selected == null ? null : selected.trim();
    }

    public String getHeadContent() {
        return headContent;
    }

    public void setHeadContent(String headContent) {
        this.headContent = headContent == null ? null : headContent.trim();
    }

    public Long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Long getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Long updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getAliveFlag() {
        return aliveFlag;
    }

    public void setAliveFlag(String aliveFlag) {
        this.aliveFlag = aliveFlag == null ? null : aliveFlag.trim();
    }
}