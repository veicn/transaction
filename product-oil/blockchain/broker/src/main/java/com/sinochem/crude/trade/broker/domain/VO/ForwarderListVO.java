package com.sinochem.crude.trade.broker.domain.VO;

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
    private String forwarderstatus;
    private String nomineeCompany;

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

    public String getForwarderstatus() {
        return forwarderstatus;
    }

    public void setForwarderstatus(String forwarderstatus) {
        this.forwarderstatus = forwarderstatus;
    }

    public String getNomineeCompany() {
        return nomineeCompany;
    }

    public void setNomineeCompany(String nomineeCompany) {
        this.nomineeCompany = nomineeCompany;
    }
}
