package com.sinochem.crude.trade.shipagent.domain;

import java.util.Date;

public class TShipagentSofDetailTemplate {
    private Long id;

    private String tempUuid;

    private Long enterpriseId;

    private String enterpriseName;

    private String introEn;

    private String introZh;

    private String aliveFlag;

    private Date createDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTempUuid() {
        return tempUuid;
    }

    public void setTempUuid(String tempUuid) {
        this.tempUuid = tempUuid == null ? null : tempUuid.trim();
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName == null ? null : enterpriseName.trim();
    }

    public String getIntroEn() {
        return introEn;
    }

    public void setIntroEn(String introEn) {
        this.introEn = introEn == null ? null : introEn.trim();
    }

    public String getIntroZh() {
        return introZh;
    }

    public void setIntroZh(String introZh) {
        this.introZh = introZh == null ? null : introZh.trim();
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
}