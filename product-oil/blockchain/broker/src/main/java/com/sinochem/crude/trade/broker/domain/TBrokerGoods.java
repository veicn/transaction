package com.sinochem.crude.trade.broker.domain;

import java.math.BigDecimal;

public class TBrokerGoods {
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    private Integer status;
    private Integer id;

    private String goodsinfoDecgno;

    private BigDecimal goodsinfoPriceperunit;

    private BigDecimal goodsinfoDecltotal;

    private BigDecimal goodsinfoGqty;

    private String goodsinfoGunit;

    private String goodsinfoCutmode;

    private String goodsinfoTradecurr;

    private String goodsinfoCodets;

    private String goodsinfoGname;

    private BigDecimal goodsinfoQty1;

    private BigDecimal goodsinfoQty2;

    private String goodsinfoDestinationcountry;

    private String goodsinfoOrigincountry;

    private String goodsinfoDistrictcode;

    private String declarationuuid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGoodsinfoDecgno() {
        return goodsinfoDecgno;
    }

    public void setGoodsinfoDecgno(String goodsinfoDecgno) {
        this.goodsinfoDecgno = goodsinfoDecgno == null ? null : goodsinfoDecgno.trim();
    }

    public BigDecimal getGoodsinfoPriceperunit() {
        return goodsinfoPriceperunit;
    }

    public void setGoodsinfoPriceperunit(BigDecimal goodsinfoPriceperunit) {
        this.goodsinfoPriceperunit = goodsinfoPriceperunit;
    }

    public BigDecimal getGoodsinfoDecltotal() {
        return goodsinfoDecltotal;
    }

    public void setGoodsinfoDecltotal(BigDecimal goodsinfoDecltotal) {
        this.goodsinfoDecltotal = goodsinfoDecltotal;
    }

    public BigDecimal getGoodsinfoGqty() {
        return goodsinfoGqty;
    }

    public void setGoodsinfoGqty(BigDecimal goodsinfoGqty) {
        this.goodsinfoGqty = goodsinfoGqty;
    }

    public String getGoodsinfoGunit() {
        return goodsinfoGunit;
    }

    public void setGoodsinfoGunit(String goodsinfoGunit) {
        this.goodsinfoGunit = goodsinfoGunit == null ? null : goodsinfoGunit.trim();
    }

    public String getGoodsinfoCutmode() {
        return goodsinfoCutmode;
    }

    public void setGoodsinfoCutmode(String goodsinfoCutmode) {
        this.goodsinfoCutmode = goodsinfoCutmode == null ? null : goodsinfoCutmode.trim();
    }

    public String getGoodsinfoTradecurr() {
        return goodsinfoTradecurr;
    }

    public void setGoodsinfoTradecurr(String goodsinfoTradecurr) {
        this.goodsinfoTradecurr = goodsinfoTradecurr == null ? null : goodsinfoTradecurr.trim();
    }

    public String getGoodsinfoCodets() {
        return goodsinfoCodets;
    }

    public void setGoodsinfoCodets(String goodsinfoCodets) {
        this.goodsinfoCodets = goodsinfoCodets == null ? null : goodsinfoCodets.trim();
    }

    public String getGoodsinfoGname() {
        return goodsinfoGname;
    }

    public void setGoodsinfoGname(String goodsinfoGname) {
        this.goodsinfoGname = goodsinfoGname == null ? null : goodsinfoGname.trim();
    }

    public BigDecimal getGoodsinfoQty1() {
        return goodsinfoQty1;
    }

    public void setGoodsinfoQty1(BigDecimal goodsinfoQty1) {
        this.goodsinfoQty1 = goodsinfoQty1;
    }

    public BigDecimal getGoodsinfoQty2() {
        return goodsinfoQty2;
    }

    public void setGoodsinfoQty2(BigDecimal goodsinfoQty2) {
        this.goodsinfoQty2 = goodsinfoQty2;
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

    public String getGoodsinfoDistrictcode() {
        return goodsinfoDistrictcode;
    }

    public void setGoodsinfoDistrictcode(String goodsinfoDistrictcode) {
        this.goodsinfoDistrictcode = goodsinfoDistrictcode == null ? null : goodsinfoDistrictcode.trim();
    }

    public String getDeclarationuuid() {
        return declarationuuid;
    }

    public void setDeclarationuuid(String declarationuuid) {
        this.declarationuuid = declarationuuid == null ? null : declarationuuid.trim();
    }
}