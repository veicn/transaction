package com.sinochem.crude.trade.member.domain;

public class CrudePotrerillosInfo extends SualificationInfo {
    private Long id;

    private Long totalInvestment;

    private Long fixedAssets;

    private String mainProducts;

    private String deviceDescription;

    private Boolean canTrading;

    private String mainImportProduct;

    private String mainExportProduct;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTotalInvestment() {
        return totalInvestment;
    }

    public void setTotalInvestment(Long totalInvestment) {
        this.totalInvestment = totalInvestment;
    }

    public Long getFixedAssets() {
        return fixedAssets;
    }

    public void setFixedAssets(Long fixedAssets) {
        this.fixedAssets = fixedAssets;
    }

    public String getMainProducts() {
        return mainProducts;
    }

    public void setMainProducts(String mainProducts) {
        this.mainProducts = mainProducts == null ? null : mainProducts.trim();
    }

    public String getDeviceDescription() {
        return deviceDescription;
    }

    public void setDeviceDescription(String deviceDescription) {
        this.deviceDescription = deviceDescription == null ? null : deviceDescription.trim();
    }

    public Boolean getCanTrading() {
        return canTrading;
    }

    public void setCanTrading(Boolean canTrading) {
        this.canTrading = canTrading;
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

}