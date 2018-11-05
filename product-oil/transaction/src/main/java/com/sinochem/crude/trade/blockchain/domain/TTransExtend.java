package com.sinochem.crude.trade.blockchain.domain;

import java.util.Date;

public class TTransExtend {
    private Long id;

    private String dealNo;

    private String purchaseContractNo;

    private String agencyAgreementNo;

    private Long buyerEnterpriseId;

    private String buyerEnterpriseName;

    private Long sellerEnterpriseId;

    private String sellerEnterpriseName;

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

    public String getPurchaseContractNo() {
        return purchaseContractNo;
    }

    public void setPurchaseContractNo(String purchaseContractNo) {
        this.purchaseContractNo = purchaseContractNo == null ? null : purchaseContractNo.trim();
    }

    public String getAgencyAgreementNo() {
        return agencyAgreementNo;
    }

    public void setAgencyAgreementNo(String agencyAgreementNo) {
        this.agencyAgreementNo = agencyAgreementNo == null ? null : agencyAgreementNo.trim();
    }

    public Long getBuyerEnterpriseId() {
        return buyerEnterpriseId;
    }

    public void setBuyerEnterpriseId(Long buyerEnterpriseId) {
        this.buyerEnterpriseId = buyerEnterpriseId;
    }

    public String getBuyerEnterpriseName() {
        return buyerEnterpriseName;
    }

    public void setBuyerEnterpriseName(String buyerEnterpriseName) {
        this.buyerEnterpriseName = buyerEnterpriseName == null ? null : buyerEnterpriseName.trim();
    }

    public Long getSellerEnterpriseId() {
        return sellerEnterpriseId;
    }

    public void setSellerEnterpriseId(Long sellerEnterpriseId) {
        this.sellerEnterpriseId = sellerEnterpriseId;
    }

    public String getSellerEnterpriseName() {
        return sellerEnterpriseName;
    }

    public void setSellerEnterpriseName(String sellerEnterpriseName) {
        this.sellerEnterpriseName = sellerEnterpriseName == null ? null : sellerEnterpriseName.trim();
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