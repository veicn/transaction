package com.sinochem.crude.trade.blockchain.domain;

public class TBrokerOtherpackinfo {
    private Long id;

    private String declarationuuid;

    private String packtypecode;

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

    public String getPacktypecode() {
        return packtypecode;
    }

    public void setPacktypecode(String packtypecode) {
        this.packtypecode = packtypecode == null ? null : packtypecode.trim();
    }
}