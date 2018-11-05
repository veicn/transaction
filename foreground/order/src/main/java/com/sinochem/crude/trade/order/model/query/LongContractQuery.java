package com.sinochem.crude.trade.order.model.query;

public class LongContractQuery {

    /**
     * 长协编号
     */
    private String contractId;
    /**
     * 供应商联系人
     */
    private String contacter;
    /**
     * 供应商名称
     */
    private String cupplier;

    /**
     * 企业id
     */
    private Long memberid;
    /**
     * 排序
     */
    private String orderByClause;

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getContacter() {
        return contacter;
    }

    public void setContacter(String contacter) {
        this.contacter = contacter;
    }

    public String getCupplier() {
        return cupplier;
    }

    public void setCupplier(String cupplier) {
        this.cupplier = cupplier;
    }

    public Long getMemberid() {
        return memberid;
    }

    public void setMemberid(Long memberid) {
        this.memberid = memberid;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }
}
