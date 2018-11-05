package com.sinochem.crude.trade.blockchain.domain;

import java.io.Serializable;
import java.util.Date;

public class TShipagentDocument implements Serializable{
    private Long id;

    private Long appointId;

    private String dealNo;

    private Long enterpriseId;

    private String enterpriseName;

    private Long appointEnterpriseId;

    private String appointEnterpriseName;

    private String channel;

    private Long sofId;

    private Long blId;

    private String blUrl;

    private String sofUrl;

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

    public Long getAppointId() {
        return appointId;
    }

    public void setAppointId(Long appointId) {
        this.appointId = appointId;
    }

    public String getDealNo() {
        return dealNo;
    }

    public void setDealNo(String dealNo) {
        this.dealNo = dealNo == null ? null : dealNo.trim();
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

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel == null ? null : channel.trim();
    }

    public Long getSofId() {
        return sofId;
    }

    public void setSofId(Long sofId) {
        this.sofId = sofId;
    }

    public Long getBlId() {
        return blId;
    }

    public void setBlId(Long blId) {
        this.blId = blId;
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

    public String getBlUrl() {
        return blUrl;
    }

    public void setBlUrl(String blUrl) {
        this.blUrl = blUrl;
    }

    public String getSofUrl() {
        return sofUrl;
    }

    public void setSofUrl(String sofUrl) {
        this.sofUrl = sofUrl;
    }
}