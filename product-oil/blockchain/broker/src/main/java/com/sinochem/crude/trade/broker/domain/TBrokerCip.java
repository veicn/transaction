package com.sinochem.crude.trade.broker.domain;

public class TBrokerCip {
    private Long id;

    private String declarationuuid;

    private String code;

    private String recordno;

    private String goodsno;

    private String cipname;

    private String goodsname;

    private String rule;

    private String gqty;

    private String gunit;

    private String priceperunit;

    private String decltotal;

    private String tradecurr;

    private String goodsinfoDestinationcountry;

    private String goodsinfoOrigincountry;

    private String goodsinfoCutmode;

    private String superviserequire;

    private String currentchecked;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getRecordno() {
        return recordno;
    }

    public void setRecordno(String recordno) {
        this.recordno = recordno == null ? null : recordno.trim();
    }

    public String getGoodsno() {
        return goodsno;
    }

    public void setGoodsno(String goodsno) {
        this.goodsno = goodsno == null ? null : goodsno.trim();
    }

    public String getCipname() {
        return cipname;
    }

    public void setCipname(String cipname) {
        this.cipname = cipname == null ? null : cipname.trim();
    }

    public String getGoodsname() {
        return goodsname;
    }

    public void setGoodsname(String goodsname) {
        this.goodsname = goodsname == null ? null : goodsname.trim();
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule == null ? null : rule.trim();
    }

    public String getGqty() {
        return gqty;
    }

    public void setGqty(String gqty) {
        this.gqty = gqty == null ? null : gqty.trim();
    }

    public String getGunit() {
        return gunit;
    }

    public void setGunit(String gunit) {
        this.gunit = gunit == null ? null : gunit.trim();
    }

    public String getPriceperunit() {
        return priceperunit;
    }

    public void setPriceperunit(String priceperunit) {
        this.priceperunit = priceperunit == null ? null : priceperunit.trim();
    }

    public String getDecltotal() {
        return decltotal;
    }

    public void setDecltotal(String decltotal) {
        this.decltotal = decltotal == null ? null : decltotal.trim();
    }

    public String getTradecurr() {
        return tradecurr;
    }

    public void setTradecurr(String tradecurr) {
        this.tradecurr = tradecurr == null ? null : tradecurr.trim();
    }

    public String getGoodsinfoDestinationcountry() {
        return goodsinfoDestinationcountry;
    }

    public void setGoodsinfoDestinationcountry(String goodsinfoDestinationcountry) {
        this.goodsinfoDestinationcountry = goodsinfoDestinationcountry == null ? null : goodsinfoDestinationcountry.trim();
    }

    public String getGoodsinfoOrigincountry() {
        return goodsinfoOrigincountry;
    }

    public void setGoodsinfoOrigincountry(String goodsinfoOrigincountry) {
        this.goodsinfoOrigincountry = goodsinfoOrigincountry == null ? null : goodsinfoOrigincountry.trim();
    }

    public String getGoodsinfoCutmode() {
        return goodsinfoCutmode;
    }

    public void setGoodsinfoCutmode(String goodsinfoCutmode) {
        this.goodsinfoCutmode = goodsinfoCutmode == null ? null : goodsinfoCutmode.trim();
    }

    public String getSuperviserequire() {
        return superviserequire;
    }

    public void setSuperviserequire(String superviserequire) {
        this.superviserequire = superviserequire == null ? null : superviserequire.trim();
    }

    public String getCurrentchecked() {
        return currentchecked;
    }

    public void setCurrentchecked(String currentchecked) {
        this.currentchecked = currentchecked == null ? null : currentchecked.trim();
    }
}