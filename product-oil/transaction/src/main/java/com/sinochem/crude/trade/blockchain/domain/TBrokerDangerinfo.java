package com.sinochem.crude.trade.blockchain.domain;

public class TBrokerDangerinfo {
    private Long id;

    private String declarationuuid;

    private String uncode;

    private String goodsname;

    private String dangerouschemicals;

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

    public String getUncode() {
        return uncode;
    }

    public void setUncode(String uncode) {
        this.uncode = uncode == null ? null : uncode.trim();
    }

    public String getGoodsname() {
        return goodsname;
    }

    public void setGoodsname(String goodsname) {
        this.goodsname = goodsname == null ? null : goodsname.trim();
    }

    public String getDangerouschemicals() {
        return dangerouschemicals;
    }

    public void setDangerouschemicals(String dangerouschemicals) {
        this.dangerouschemicals = dangerouschemicals == null ? null : dangerouschemicals.trim();
    }
}