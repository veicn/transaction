package com.sinochem.crude.trade.transaction.model.vo;

import java.util.List;

/**
 * @Author: fengzk
 * @CreateDate: 2018/7/14 16:03
 * @Version: [v1.0]
 */
public class BillAllUploadVO {
    public String getContractUUid() {
        return contractUUid;
    }

    public void setContractUUid(String contractUUid) {
        this.contractUUid = contractUUid;
    }

    public List<BillCoreUploadVo> getProInvoiceList() {
        return proInvoiceList;
    }

    public void setProInvoiceList(List<BillCoreUploadVo> proInvoiceList) {
        this.proInvoiceList = proInvoiceList;
    }

    public List<BillCoreUploadVo> getSettleInvoiceList() {
        return settleInvoiceList;
    }

    public void setSettleInvoiceList(List<BillCoreUploadVo> settleInvoiceList) {
        this.settleInvoiceList = settleInvoiceList;
    }

    private String contractUUid;

    public String getEpMemberId() {
        return epMemberId;
    }

    public void setEpMemberId(String epMemberId) {
        this.epMemberId = epMemberId;
    }

    private String epMemberId;
    private List<BillCoreUploadVo> proInvoiceList;
    private List<BillCoreUploadVo> settleInvoiceList;
}
