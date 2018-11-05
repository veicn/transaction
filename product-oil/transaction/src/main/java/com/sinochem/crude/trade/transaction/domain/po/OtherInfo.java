package com.sinochem.crude.trade.transaction.domain.po;

import com.sinochem.crude.trade.common.base.BasePO;

/**
 * 其它信息
 * @author Yichen Zhao
 * date: 20180226
 */
public class OtherInfo extends BasePO {

    /**
     * 其它条款
     */
    private String otherTerm;

    /**
     * 出口配额文件
     */
    private String exportQuota;

    /**
     * 出口配额文件的名称
     */
    private String exportQuotaName;

    /** getters and setters */
    public String getOtherTerm() {
        return otherTerm;
    }

    public void setOtherTerm(String otherTerm) {
        this.otherTerm = otherTerm;
    }

    public String getExportQuota() {
        return exportQuota;
    }

    public void setExportQuota(String exportQuota) {
        this.exportQuota = exportQuota;
    }

    public String getExportQuotaName() {
        return exportQuotaName;
    }

    public void setExportQuotaName(String exportQuotaName) {
        this.exportQuotaName = exportQuotaName;
    }
}
