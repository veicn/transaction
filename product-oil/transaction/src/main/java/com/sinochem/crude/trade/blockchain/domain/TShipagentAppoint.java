package com.sinochem.crude.trade.blockchain.domain;

import java.util.Date;

public class TShipagentAppoint {

    public static final String STATUS_ACCEPTED = "1";
    public static final String STATUS_COMPLETED = "2";

    private Long id;

    private String dealNo;

    private String dealUuid;

    private Long enterpriseId;

    private String enterpriseName;

    private String status;

    private Long appointEnterpriseId;

    private String appointEnterpriseName;

    private String aliveFlag;

    private Date createDate;

    private Date updateDate;

    private Long createUser;

    private Long updateUser;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDealNo() {
        return dealNo;
    }

    public void setDealNo(String dealNo) {
        this.dealNo = dealNo == null ? null : dealNo.trim();
    }

    public String getDealUuid() {
        return dealUuid;
    }

    public void setDealUuid(String dealUuid) {
        this.dealUuid = dealUuid == null ? null : dealUuid.trim();
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName == null ? null : enterpriseName.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Long getAppointEnterpriseId() {
        return appointEnterpriseId;
    }

    public void setAppointEnterpriseId(Long appointEnterpriseId) {
        this.appointEnterpriseId = appointEnterpriseId;
    }

    public String getAppointEnterpriseName() {
        return appointEnterpriseName;
    }

    public void setAppointEnterpriseName(String appointEnterpriseName) {
        this.appointEnterpriseName = appointEnterpriseName == null ? null : appointEnterpriseName.trim();
    }

    public String getAliveFlag() {
        return aliveFlag;
    }

    public void setAliveFlag(String aliveFlag) {
        this.aliveFlag = aliveFlag == null ? null : aliveFlag.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    public Long getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Long updateUser) {
        this.updateUser = updateUser;
    }
}