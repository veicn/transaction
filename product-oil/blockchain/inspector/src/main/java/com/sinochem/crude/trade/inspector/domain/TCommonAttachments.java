package com.sinochem.crude.trade.inspector.domain;

import java.util.Date;

public class TCommonAttachments {
    private Long id;

    private String fileOssId;

    private String channel;

    private String name;

    private String path;

    private String aliveFlag;

    private Date createDate;

    private Date updateDate;

    private Long createUser;

    private Long updateUser;

    private String fileType;

    private String businessUuid;

    private String brokerBillsFileType;

    private String brokerBillsNo;

    public String getBrokerBillsFileType() {
        return brokerBillsFileType;
    }

    public void setBrokerBillsFileType(String brokerBillsFileType) {
        this.brokerBillsFileType = brokerBillsFileType;
    }

    public String getBrokerBillsNo() {
        return brokerBillsNo;
    }

    public void setBrokerBillsNo(String brokerBillsNo) {
        this.brokerBillsNo = brokerBillsNo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileOssId() {
        return fileOssId;
    }

    public void setFileOssId(String fileOssId) {
        this.fileOssId = fileOssId == null ? null : fileOssId.trim();
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel == null ? null : channel.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
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

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType == null ? null : fileType.trim();
    }

    public String getBusinessUuid() {
        return businessUuid;
    }

    public void setBusinessUuid(String businessUuid) {
        this.businessUuid = businessUuid == null ? null : businessUuid.trim();
    }
}