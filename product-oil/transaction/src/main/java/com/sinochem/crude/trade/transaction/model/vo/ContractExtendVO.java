package com.sinochem.crude.trade.transaction.model.vo;

import com.sinochem.crude.trade.common.utils.DateUtil;
import com.sinochem.crude.trade.transaction.domain.po.ContractExtend;

import java.util.List;

/**
 * @Author: fengzk
 * @CreateDate: 2018/7/13 15:27
 * @Version: [v1.0]
 */
public class ContractExtendVO {
    private String createTime;
    private String createUser;
    private String settleNum;
    private String settleAmt;

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    private String contractNo;

    public String getContractDate() {
        return contractDate;
    }

    public void setContractDate(String contractDate) {
        this.contractDate = contractDate;
    }

    private String contractDate;
    private String settleNumUnit;
    private String settleAmtUnit;
    private String contractId;
    public ContractExtendVO(){}

    public ContractExtendVO(ContractExtend contractExtend){
        if(contractExtend!=null){

        if(contractExtend.getGmtCreated()!=null) {
            createTime = DateUtil.formatDateTime(contractExtend.getGmtCreated());
        }
        settleAmt=contractExtend.getSettleAmt().toString();
        settleAmtUnit=contractExtend.getAttr22();
        settleNum=contractExtend.getSettleNum().toString();
        settleNumUnit=contractExtend.getAttr21();
        contractDate=contractExtend.getContractDate();
        contractId=contractExtend.getRefContractId().toString();
        contractNo=contractExtend.getContractNo();
        }
    }
    public String getContractUUid() {
        return contractUUid;
    }

    public void setContractUUid(String contractUUid) {
        this.contractUUid = contractUUid;
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
    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getSettleNum() {
        return settleNum;
    }

    public void setSettleNum(String settleNum) {
        this.settleNum = settleNum;
    }

    public String getSettleAmt() {
        return settleAmt;
    }

    public void setSettleAmt(String settleAmt) {
        this.settleAmt = settleAmt;
    }

    public String getSettleNumUnit() {
        return settleNumUnit;
    }

    public void setSettleNumUnit(String settleNumUnit) {
        this.settleNumUnit = settleNumUnit;
    }

    public String getSettleAmtUnit() {
        return settleAmtUnit;
    }

    public void setSettleAmtUnit(String settleAmtUnit) {
        this.settleAmtUnit = settleAmtUnit;
    }
}
