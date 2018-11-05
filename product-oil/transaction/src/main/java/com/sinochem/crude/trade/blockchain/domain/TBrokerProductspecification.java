package com.sinochem.crude.trade.blockchain.domain;

public class TBrokerProductspecification {
    private Long id;

    private String declarationuuid;

    private String rules;

    private String productinfo;

    private String brandtype;

    private String exitdiscount;

    private String distill;

    private String sulphurcontent;

    private String gtin;

    private String cas;

    private String other;

    private String modelrule;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeclarationuuid() {
        return declarationuuid;
    }

    public void setDeclarationuuid(String declarationuuid) {
        this.declarationuuid = declarationuuid == null ? null : declarationuuid.trim();
    }

    public String getRules() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules = rules == null ? null : rules.trim();
    }

    public String getProductinfo() {
        return productinfo;
    }

    public void setProductinfo(String productinfo) {
        this.productinfo = productinfo == null ? null : productinfo.trim();
    }

    public String getBrandtype() {
        return brandtype;
    }

    public void setBrandtype(String brandtype) {
        this.brandtype = brandtype == null ? null : brandtype.trim();
    }

    public String getExitdiscount() {
        return exitdiscount;
    }

    public void setExitdiscount(String exitdiscount) {
        this.exitdiscount = exitdiscount == null ? null : exitdiscount.trim();
    }

    public String getDistill() {
        return distill;
    }

    public void setDistill(String distill) {
        this.distill = distill == null ? null : distill.trim();
    }

    public String getSulphurcontent() {
        return sulphurcontent;
    }

    public void setSulphurcontent(String sulphurcontent) {
        this.sulphurcontent = sulphurcontent == null ? null : sulphurcontent.trim();
    }

    public String getGtin() {
        return gtin;
    }

    public void setGtin(String gtin) {
        this.gtin = gtin == null ? null : gtin.trim();
    }

    public String getCas() {
        return cas;
    }

    public void setCas(String cas) {
        this.cas = cas == null ? null : cas.trim();
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other == null ? null : other.trim();
    }

    public String getModelrule() {
        return modelrule;
    }

    public void setModelrule(String modelrule) {
        this.modelrule = modelrule == null ? null : modelrule.trim();
    }
}