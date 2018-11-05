package com.sinochem.crude.trade.blockchain.domain;

import java.math.BigDecimal;
import java.util.Date;

public class TBrokerDeclaration {
    private Integer id;

    private Long enterpriseId;

    private String status;

    private String unifiedcode;

    private String purchaseContractNo;

    private String inseastradeco;

    private String overseastradeco;

    private String enterprisenameCh;

    private String enterprisenameEn;

    private String checkoutno;

    private String customscode;

    private String declareCreditcode;

    private String declareCustomscode;

    private String declareCheckoutno;

    private String declareEnterprisename;

    private String productionCreditcode;

    private String productionCustomscode;

    private String productionCheckoutno;

    private String productionEnterprisename;

    private String customsinfoCustomscode;

    private String customsinfoIeportcode;

    private String customsinfoTrafmode;

    private String customsinfoVoyno;

    private String customsinfoDistinateport;

    private String customsinfoNativeshipname;

    private String customsinfoDeliveryno;

    private String customsinfoTrademode;

    private String customsinfoCutmode;

    private String customsinfoTransmode;

    private String customsinfoLicenseno;

    private String customsinfoTradecountry;

    private BigDecimal customsinfoPackqty;

    private String customsinfoPacktype;

    private BigDecimal customsinfoGrosswt;

    private BigDecimal customsinfoNetwt;

    private String customsinfoEntyportcode;

    private String customsinfoEntrytype;

    private String customsinfoTradeareacode;

    private String customsinfoGoodsplace;

    private String customsinfoVsaorgcode;

    private String customsinfoOrgcode;

    private String customsinfoInsporgcode;

    private String customsinfoNote;

    public String getCustomsinfoMarkNo() {
        return customsinfoMarkNo;
    }

    public void setCustomsinfoMarkNo(String customsinfoMarkNo) {
        this.customsinfoMarkNo = customsinfoMarkNo;
    }

    private String customsinfoMarkNo;

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

    private String contactperson;

    private String contactway;

    private String uuid;

    private Date createtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getUnifiedcode() {
        return unifiedcode;
    }

    public void setUnifiedcode(String unifiedcode) {
        this.unifiedcode = unifiedcode == null ? null : unifiedcode.trim();
    }

    public String getPurchaseContractNo() {
        return purchaseContractNo;
    }

    public void setPurchaseContractNo(String purchaseContractNo) {
        this.purchaseContractNo = purchaseContractNo == null ? null : purchaseContractNo.trim();
    }

    public String getInseastradeco() {
        return inseastradeco;
    }

    public void setInseastradeco(String inseastradeco) {
        this.inseastradeco = inseastradeco == null ? null : inseastradeco.trim();
    }

    public String getOverseastradeco() {
        return overseastradeco;
    }

    public void setOverseastradeco(String overseastradeco) {
        this.overseastradeco = overseastradeco == null ? null : overseastradeco.trim();
    }

    public String getEnterprisenameCh() {
        return enterprisenameCh;
    }

    public void setEnterprisenameCh(String enterprisenameCh) {
        this.enterprisenameCh = enterprisenameCh == null ? null : enterprisenameCh.trim();
    }

    public String getEnterprisenameEn() {
        return enterprisenameEn;
    }

    public void setEnterprisenameEn(String enterprisenameEn) {
        this.enterprisenameEn = enterprisenameEn == null ? null : enterprisenameEn.trim();
    }

    public String getCheckoutno() {
        return checkoutno;
    }

    public void setCheckoutno(String checkoutno) {
        this.checkoutno = checkoutno == null ? null : checkoutno.trim();
    }

    public String getCustomscode() {
        return customscode;
    }

    public void setCustomscode(String customscode) {
        this.customscode = customscode == null ? null : customscode.trim();
    }

    public String getDeclareCreditcode() {
        return declareCreditcode;
    }

    public void setDeclareCreditcode(String declareCreditcode) {
        this.declareCreditcode = declareCreditcode == null ? null : declareCreditcode.trim();
    }

    public String getDeclareCustomscode() {
        return declareCustomscode;
    }

    public void setDeclareCustomscode(String declareCustomscode) {
        this.declareCustomscode = declareCustomscode == null ? null : declareCustomscode.trim();
    }

    public String getDeclareCheckoutno() {
        return declareCheckoutno;
    }

    public void setDeclareCheckoutno(String declareCheckoutno) {
        this.declareCheckoutno = declareCheckoutno == null ? null : declareCheckoutno.trim();
    }

    public String getDeclareEnterprisename() {
        return declareEnterprisename;
    }

    public void setDeclareEnterprisename(String declareEnterprisename) {
        this.declareEnterprisename = declareEnterprisename == null ? null : declareEnterprisename.trim();
    }

    public String getProductionCreditcode() {
        return productionCreditcode;
    }

    public void setProductionCreditcode(String productionCreditcode) {
        this.productionCreditcode = productionCreditcode == null ? null : productionCreditcode.trim();
    }

    public String getProductionCustomscode() {
        return productionCustomscode;
    }

    public void setProductionCustomscode(String productionCustomscode) {
        this.productionCustomscode = productionCustomscode == null ? null : productionCustomscode.trim();
    }

    public String getProductionCheckoutno() {
        return productionCheckoutno;
    }

    public void setProductionCheckoutno(String productionCheckoutno) {
        this.productionCheckoutno = productionCheckoutno == null ? null : productionCheckoutno.trim();
    }

    public String getProductionEnterprisename() {
        return productionEnterprisename;
    }

    public void setProductionEnterprisename(String productionEnterprisename) {
        this.productionEnterprisename = productionEnterprisename == null ? null : productionEnterprisename.trim();
    }

    public String getCustomsinfoCustomscode() {
        return customsinfoCustomscode;
    }

    public void setCustomsinfoCustomscode(String customsinfoCustomscode) {
        this.customsinfoCustomscode = customsinfoCustomscode == null ? null : customsinfoCustomscode.trim();
    }

    public String getCustomsinfoIeportcode() {
        return customsinfoIeportcode;
    }

    public void setCustomsinfoIeportcode(String customsinfoIeportcode) {
        this.customsinfoIeportcode = customsinfoIeportcode == null ? null : customsinfoIeportcode.trim();
    }

    public String getCustomsinfoTrafmode() {
        return customsinfoTrafmode;
    }

    public void setCustomsinfoTrafmode(String customsinfoTrafmode) {
        this.customsinfoTrafmode = customsinfoTrafmode == null ? null : customsinfoTrafmode.trim();
    }

    public String getCustomsinfoVoyno() {
        return customsinfoVoyno;
    }

    public void setCustomsinfoVoyno(String customsinfoVoyno) {
        this.customsinfoVoyno = customsinfoVoyno == null ? null : customsinfoVoyno.trim();
    }

    public String getCustomsinfoDistinateport() {
        return customsinfoDistinateport;
    }

    public void setCustomsinfoDistinateport(String customsinfoDistinateport) {
        this.customsinfoDistinateport = customsinfoDistinateport == null ? null : customsinfoDistinateport.trim();
    }

    public String getCustomsinfoNativeshipname() {
        return customsinfoNativeshipname;
    }

    public void setCustomsinfoNativeshipname(String customsinfoNativeshipname) {
        this.customsinfoNativeshipname = customsinfoNativeshipname == null ? null : customsinfoNativeshipname.trim();
    }

    public String getCustomsinfoDeliveryno() {
        return customsinfoDeliveryno;
    }

    public void setCustomsinfoDeliveryno(String customsinfoDeliveryno) {
        this.customsinfoDeliveryno = customsinfoDeliveryno == null ? null : customsinfoDeliveryno.trim();
    }

    public String getCustomsinfoTrademode() {
        return customsinfoTrademode;
    }

    public void setCustomsinfoTrademode(String customsinfoTrademode) {
        this.customsinfoTrademode = customsinfoTrademode == null ? null : customsinfoTrademode.trim();
    }

    public String getCustomsinfoCutmode() {
        return customsinfoCutmode;
    }

    public void setCustomsinfoCutmode(String customsinfoCutmode) {
        this.customsinfoCutmode = customsinfoCutmode == null ? null : customsinfoCutmode.trim();
    }

    public String getCustomsinfoTransmode() {
        return customsinfoTransmode;
    }

    public void setCustomsinfoTransmode(String customsinfoTransmode) {
        this.customsinfoTransmode = customsinfoTransmode == null ? null : customsinfoTransmode.trim();
    }

    public String getCustomsinfoLicenseno() {
        return customsinfoLicenseno;
    }

    public void setCustomsinfoLicenseno(String customsinfoLicenseno) {
        this.customsinfoLicenseno = customsinfoLicenseno == null ? null : customsinfoLicenseno.trim();
    }

    public String getCustomsinfoTradecountry() {
        return customsinfoTradecountry;
    }

    public void setCustomsinfoTradecountry(String customsinfoTradecountry) {
        this.customsinfoTradecountry = customsinfoTradecountry == null ? null : customsinfoTradecountry.trim();
    }

    public BigDecimal getCustomsinfoPackqty() {
        return customsinfoPackqty;
    }

    public void setCustomsinfoPackqty(BigDecimal customsinfoPackqty) {
        this.customsinfoPackqty = customsinfoPackqty;
    }

    public String getCustomsinfoPacktype() {
        return customsinfoPacktype;
    }

    public void setCustomsinfoPacktype(String customsinfoPacktype) {
        this.customsinfoPacktype = customsinfoPacktype == null ? null : customsinfoPacktype.trim();
    }

    public BigDecimal getCustomsinfoGrosswt() {
        return customsinfoGrosswt;
    }

    public void setCustomsinfoGrosswt(BigDecimal customsinfoGrosswt) {
        this.customsinfoGrosswt = customsinfoGrosswt;
    }

    public BigDecimal getCustomsinfoNetwt() {
        return customsinfoNetwt;
    }

    public void setCustomsinfoNetwt(BigDecimal customsinfoNetwt) {
        this.customsinfoNetwt = customsinfoNetwt;
    }

    public String getCustomsinfoEntyportcode() {
        return customsinfoEntyportcode;
    }

    public void setCustomsinfoEntyportcode(String customsinfoEntyportcode) {
        this.customsinfoEntyportcode = customsinfoEntyportcode == null ? null : customsinfoEntyportcode.trim();
    }

    public String getCustomsinfoEntrytype() {
        return customsinfoEntrytype;
    }

    public void setCustomsinfoEntrytype(String customsinfoEntrytype) {
        this.customsinfoEntrytype = customsinfoEntrytype == null ? null : customsinfoEntrytype.trim();
    }

    public String getCustomsinfoTradeareacode() {
        return customsinfoTradeareacode;
    }

    public void setCustomsinfoTradeareacode(String customsinfoTradeareacode) {
        this.customsinfoTradeareacode = customsinfoTradeareacode == null ? null : customsinfoTradeareacode.trim();
    }

    public String getCustomsinfoGoodsplace() {
        return customsinfoGoodsplace;
    }

    public void setCustomsinfoGoodsplace(String customsinfoGoodsplace) {
        this.customsinfoGoodsplace = customsinfoGoodsplace == null ? null : customsinfoGoodsplace.trim();
    }

    public String getCustomsinfoVsaorgcode() {
        return customsinfoVsaorgcode;
    }

    public void setCustomsinfoVsaorgcode(String customsinfoVsaorgcode) {
        this.customsinfoVsaorgcode = customsinfoVsaorgcode == null ? null : customsinfoVsaorgcode.trim();
    }

    public String getCustomsinfoOrgcode() {
        return customsinfoOrgcode;
    }

    public void setCustomsinfoOrgcode(String customsinfoOrgcode) {
        this.customsinfoOrgcode = customsinfoOrgcode == null ? null : customsinfoOrgcode.trim();
    }

    public String getCustomsinfoInsporgcode() {
        return customsinfoInsporgcode;
    }

    public void setCustomsinfoInsporgcode(String customsinfoInsporgcode) {
        this.customsinfoInsporgcode = customsinfoInsporgcode == null ? null : customsinfoInsporgcode.trim();
    }

    public String getCustomsinfoNote() {
        return customsinfoNote;
    }

    public void setCustomsinfoNote(String customsinfoNote) {
        this.customsinfoNote = customsinfoNote == null ? null : customsinfoNote.trim();
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

    public String getContactperson() {
        return contactperson;
    }

    public void setContactperson(String contactperson) {
        this.contactperson = contactperson == null ? null : contactperson.trim();
    }

    public String getContactway() {
        return contactway;
    }

    public void setContactway(String contactway) {
        this.contactway = contactway == null ? null : contactway.trim();
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}