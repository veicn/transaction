package com.sinochem.crude.trade.member.domain;

import java.math.BigDecimal;

public class CrudeTradingCompanyInfo extends SualificationInfo  {
    private Long id;

    private Boolean canTrading;

    private String tradingComplayCode;

    private String mainImportProduct;

    private String mainExportProduct;

    private BigDecimal lastYearOperationRevenue;

    private BigDecimal lastYearTradeVolume;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getCanTrading() {
        return canTrading;
    }

    public void setCanTrading(Boolean canTrading) {
        this.canTrading = canTrading;
    }

    public String getTradingComplayCode() {
        return tradingComplayCode;
    }

    public void setTradingComplayCode(String tradingComplayCode) {
        this.tradingComplayCode = tradingComplayCode == null ? null : tradingComplayCode.trim();
    }

    public String getMainImportProduct() {
        return mainImportProduct;
    }

    public void setMainImportProduct(String mainImportProduct) {
        this.mainImportProduct = mainImportProduct == null ? null : mainImportProduct.trim();
    }

    public String getMainExportProduct() {
        return mainExportProduct;
    }

    public void setMainExportProduct(String mainExportProduct) {
        this.mainExportProduct = mainExportProduct == null ? null : mainExportProduct.trim();
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

}