package com.sinochem.crude.trade.broker.model.vo;

/**
 * @Author: fengzk
 * @CreateDate: 2018/9/7 10:38
 * @Version: [v1.0]
 */
public class ForwarderListVO {
    private String dealNO;
    private String category;
    private String specification;
    private String quantity;
    private String laycan;
    private String forwarderStatus;
    private String nomineeCompany;
    private String dealUuid;
    private String purchaseContractNo;

    public String getDealUuid() {
        return dealUuid;
    }

    public void setDealUuid(String dealUuid) {
        this.dealUuid = dealUuid;
    }

    public String getDealNO() {
        return dealNO;
    }

    public void setDealNO(String dealNO) {
        this.dealNO = dealNO;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getLaycan() {
        return laycan;
    }

    public void setLaycan(String laycan) {
        this.laycan = laycan;
    }

    public String getForwarderStatus() {
        return forwarderStatus;
    }

    public void setForwarderStatus(String forwarderstatus) {
        this.forwarderStatus = forwarderstatus;
    }

    public String getNomineeCompany() {
        return nomineeCompany;
    }

    public void setNomineeCompany(String nomineeCompany) {
        this.nomineeCompany = nomineeCompany;
    }

    public String getPurchaseContractNo() {
        return purchaseContractNo;
    }

    public void setPurchaseContractNo(String purchaseContractNo) {
        this.purchaseContractNo = purchaseContractNo;
    }
}
