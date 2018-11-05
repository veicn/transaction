package com.sinochem.crude.trade.blockchain.domain;

import java.util.Date;

public class TSapEventRecord {
    private Long id;

    private String purchaseContractNo;

    private String postData;

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

    public String getPurchaseContractNo() {
        return purchaseContractNo;
    }

    public void setPurchaseContractNo(String purchaseContractNo) {
        this.purchaseContractNo = purchaseContractNo == null ? null : purchaseContractNo.trim();
    }

    public String getPostData() {
        return postData;
    }

    public void setPostData(String postData) {
        this.postData = postData == null ? null : postData.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
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
}