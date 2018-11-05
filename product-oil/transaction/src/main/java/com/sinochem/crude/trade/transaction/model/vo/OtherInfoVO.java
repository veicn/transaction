package com.sinochem.crude.trade.transaction.model.vo;

import com.sinochem.crude.trade.common.base.BasePersistVO;
import com.sinochem.crude.trade.transaction.domain.po.OtherInfo;

import java.util.Locale;

/**
 * 其它信息的VO
 * @author Yichen Zhao
 * date: 20180228
 */
public class OtherInfoVO extends BasePersistVO<OtherInfo> {

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

    public OtherInfoVO(){
    	
    }
    public OtherInfoVO(OtherInfo otherInfo) {
        super(otherInfo);
    }

    @Override
    protected void convertToVO(OtherInfo domain) {
        this.otherTerm = domain.getOtherTerm();
        this.exportQuota = domain.getExportQuota();
        this.exportQuotaName = domain.getExportQuotaName();
    }

    @Override
    protected OtherInfo convertToDomain() {
        OtherInfo otherInfo = new OtherInfo();

        otherInfo.setOtherTerm(this.otherTerm);
        otherInfo.setExportQuota(this.exportQuota);
        otherInfo.setExportQuotaName(this.exportQuotaName);

        return otherInfo;
    }

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
