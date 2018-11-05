package com.sinochem.crude.trade.shipagent.domain;

import java.util.Date;

public class TBlockchainEventRecord {
    private Long id;

    private String dealNo;

    private Long busiId;

    private String postData;

    private String eventCode;

    private String description;

    private String blockchainId;

    private String blockchainTxid;

    private Date createDate;

    private Long createUser;

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

    public Long getBusiId() {
        return busiId;
    }

    public void setBusiId(Long busiId) {
        this.busiId = busiId;
    }

    public String getPostData() {
        return postData;
    }

    public void setPostData(String postData) {
        this.postData = postData == null ? null : postData.trim();
    }

    public String getEventCode() {
        return eventCode;
    }

    public void setEventCode(String eventCode) {
        this.eventCode = eventCode == null ? null : eventCode.trim();
    }

    public String getBlockchainId() {
        return blockchainId;
    }

    public void setBlockchainId(String blockchainId) {
        this.blockchainId = blockchainId == null ? null : blockchainId.trim();
    }

    public String getBlockchainTxid() {
        return blockchainTxid;
    }

    public void setBlockchainTxid(String blockchainTxid) {
        this.blockchainTxid = blockchainTxid == null ? null : blockchainTxid.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}