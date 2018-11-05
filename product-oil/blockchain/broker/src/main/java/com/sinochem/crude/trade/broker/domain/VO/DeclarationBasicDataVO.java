package com.sinochem.crude.trade.broker.domain.VO;

import java.util.List;

/**
 * @Author: fengzk
 * @CreateDate: 2018/9/18 14:42
 * @Version: [v1.0]
 */
public class DeclarationBasicDataVO {

    private List<UDBResponseData> packInfo;
    private List<UDBResponseData> transportation;
    private List<UDBResponseData> trademode;
    private List<UDBResponseData> cutmode;
    private List<UDBResponseData> transmode;
    private List<UDBResponseData> entrytype;
    private List<UDBResponseData> country;
    private List<UDBResponseData> quarantinePort;
    private List<UDBResponseData> tradecurr;
    private List<UDBResponseData> gunit;
    private List<UDBResponseData> port;
    private List<UDBResponseData> businessMatters;

    public List<UDBResponseData> getBusinessMatters() {
        return businessMatters;
    }

    public void setBusinessMatters(List<UDBResponseData> businessMatters) {
        this.businessMatters = businessMatters;
    }

    public List<UDBResponseData> getPackInfo() {
        return packInfo;
    }

    public void setPackInfo(List<UDBResponseData> packInfo) {
        this.packInfo = packInfo;
    }

    public List<UDBResponseData> getTransportation() {
        return transportation;
    }

    public void setTransportation(List<UDBResponseData> transportation) {
        this.transportation = transportation;
    }

    public List<UDBResponseData> getTrademode() {
        return trademode;
    }

    public void setTrademode(List<UDBResponseData> trademode) {
        this.trademode = trademode;
    }

    public List<UDBResponseData> getCutmode() {
        return cutmode;
    }

    public void setCutmode(List<UDBResponseData> cutmode) {
        this.cutmode = cutmode;
    }

    public List<UDBResponseData> getTransmode() {
        return transmode;
    }

    public void setTransmode(List<UDBResponseData> transmode) {
        this.transmode = transmode;
    }

    public List<UDBResponseData> getEntrytype() {
        return entrytype;
    }

    public void setEntrytype(List<UDBResponseData> entrytype) {
        this.entrytype = entrytype;
    }

    public List<UDBResponseData> getCountry() {
        return country;
    }

    public void setCountry(List<UDBResponseData> country) {
        this.country = country;
    }

    public List<UDBResponseData> getQuarantinePort() {
        return quarantinePort;
    }

    public void setQuarantinePort(List<UDBResponseData> quarantinePort) {
        this.quarantinePort = quarantinePort;
    }

    public List<UDBResponseData> getTradecurr() {
        return tradecurr;
    }

    public void setTradecurr(List<UDBResponseData> tradecurr) {
        this.tradecurr = tradecurr;
    }

    public List<UDBResponseData> getGunit() {
        return gunit;
    }

    public void setGunit(List<UDBResponseData> gunit) {
        this.gunit = gunit;
    }

    public List<UDBResponseData> getPort() {
        return port;
    }

    public void setPort(List<UDBResponseData> port) {
        this.port = port;
    }

    public List<UDBResponseData> getDestination() {
        return destination;
    }

    public void setDestination(List<UDBResponseData> destination) {
        this.destination = destination;
    }

    public List<UDBResponseData> getDocumentType() {
        return documentType;
    }

    public void setDocumentType(List<UDBResponseData> documentType) {
        this.documentType = documentType;
    }

    public List<UDBResponseData> getVoucherType() {
        return voucherType;
    }

    public void setVoucherType(List<UDBResponseData> voucherType) {
        this.voucherType = voucherType;
    }

    private List<UDBResponseData> destination;
    private List<UDBResponseData> documentType;
    private List<UDBResponseData> voucherType;

}
