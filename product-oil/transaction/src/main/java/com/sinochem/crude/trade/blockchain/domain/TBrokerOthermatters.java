package com.sinochem.crude.trade.blockchain.domain;

public class TBrokerOthermatters {
    private Long id;

    private String declarationuuid;

    private Integer type;

    private Integer value;

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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}