package com.sinochem.crude.trade.member.domain;

import java.math.BigDecimal;

public class CrudeCustomerInfo extends SualificationInfo {
    private Long id;

    private BigDecimal lastYearOperationRevenue;

    private BigDecimal lastYearTradeVolume;

    private String masterCompany;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public BigDecimal getLastYearOperationRevenue() {
        return lastYearOperationRevenue;
    }

    public void setLastYearOperationRevenue(BigDecimal lastYearOperationRevenue) {
        this.lastYearOperationRevenue = lastYearOperationRevenue;
    }

    public BigDecimal getLastYearTradeVolume() {
        return lastYearTradeVolume;
    }

    public void setLastYearTradeVolume(BigDecimal lastYearTradeVolume) {
        this.lastYearTradeVolume = lastYearTradeVolume;
    }

    public String getMasterCompany() {
        return masterCompany;
    }

    public void setMasterCompany(String masterCompany) {
        this.masterCompany = masterCompany == null ? null : masterCompany.trim();
    }

}