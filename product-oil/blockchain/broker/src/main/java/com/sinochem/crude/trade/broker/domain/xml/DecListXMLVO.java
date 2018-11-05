package com.sinochem.crude.trade.broker.domain.xml;

import javax.xml.bind.annotation.XmlElement;

/**
 * @Author: fengzk
 * @CreateDate: 2018/6/20 11:01
 * @Version: [v1.0]
 */

public class DecListXMLVO {
    private String ClassMark;
    private String CodeTS;
    private String ContrItem;
    private String DeclPrice;
    private String DeclTotal;
    private String DutyMode;
    private String ExgNo;
    private String ExgVersion;
    private String Factor;
    @XmlElement(name="ClassMark")
    public String getClassMark() {
        return ClassMark;
    }

    public void setClassMark(String classMark) {
        ClassMark = classMark;
    }
    @XmlElement(name="CodeTS")
    public String getCodeTS() {
        return CodeTS;
    }

    public void setCodeTS(String codeTS) {
        CodeTS = codeTS;
    }
    @XmlElement(name="ContrItem")
    public String getContrItem() {
        return ContrItem;
    }

    public void setContrItem(String contrItem) {
        ContrItem = contrItem;
    }
    @XmlElement(name="DeclPrice")
    public String getDeclPrice() {
        return DeclPrice;
    }

    public void setDeclPrice(String declPrice) {
        DeclPrice = declPrice;
    }
    @XmlElement(name="DeclTotal")
    public String getDeclTotal() {
        return DeclTotal;
    }

    public void setDeclTotal(String declTotal) {
        DeclTotal = declTotal;
    }
    @XmlElement(name="DutyMode")
    public String getDutyMode() {
        return DutyMode;
    }

    public void setDutyMode(String dutyMode) {
        DutyMode = dutyMode;
    }
    @XmlElement(name="ExgNo")
    public String getExgNo() {
        return ExgNo;
    }

    public void setExgNo(String exgNo) {
        ExgNo = exgNo;
    }
    @XmlElement(name="ExgVersion")
    public String getExgVersion() {
        return ExgVersion;
    }

    public void setExgVersion(String exgVersion) {
        ExgVersion = exgVersion;
    }
    @XmlElement(name="Factor")
    public String getFactor() {
        return Factor;
    }

    public void setFactor(String factor) {
        Factor = factor;
    }
    @XmlElement(name="FirstQty")
    public String getFirstQty() {
        return FirstQty;
    }

    public void setFirstQty(String firstQty) {
        FirstQty = firstQty;
    }
    @XmlElement(name="FirstUnit")
    public String getFirstUnit() {
        return FirstUnit;
    }

    public void setFirstUnit(String firstUnit) {
        FirstUnit = firstUnit;
    }
    @XmlElement(name="GUnit")
    public String getGUnit() {
        return GUnit;
    }

    public void setGUnit(String GUnit) {
        this.GUnit = GUnit;
    }
    @XmlElement(name="GModel")
    public String getGModel() {
        return GModel;
    }

    public void setGModel(String GModel) {
        this.GModel = GModel;
    }
    @XmlElement(name="GName")
    public String getGName() {
        return GName;
    }

    public void setGName(String GName) {
        this.GName = GName;
    }
    @XmlElement(name="GNo")
    public String getGNo() {
        return GNo;
    }

    public void setGNo(String GNo) {
        this.GNo = GNo;
    }
    @XmlElement(name="GQty")
    public String getGQty() {
        return GQty;
    }

    public void setGQty(String GQty) {
        this.GQty = GQty;
    }
    @XmlElement(name="OriginCountry")
    public String getOriginCountry() {
        return OriginCountry;
    }

    public void setOriginCountry(String originCountry) {
        OriginCountry = originCountry;
    }
    @XmlElement(name="SecondUnit")
    public String getSecondUnit() {
        return SecondUnit;
    }

    public void setSecondUnit(String secondUnit) {
        SecondUnit = secondUnit;
    }
    @XmlElement(name="SecondQty")
    public String getSecondQty() {
        return SecondQty;
    }

    public void setSecondQty(String secondQty) {
        SecondQty = secondQty;
    }
    @XmlElement(name="TradeCurr")
    public String getTradeCurr() {
        return TradeCurr;
    }

    public void setTradeCurr(String tradeCurr) {
        TradeCurr = tradeCurr;
    }
    @XmlElement(name="UseTo")
    public String getUseTo() {
        return UseTo;
    }

    public void setUseTo(String useTo) {
        UseTo = useTo;
    }
    @XmlElement(name="WorkUsd")
    public String getWorkUsd() {
        return WorkUsd;
    }

    public void setWorkUsd(String workUsd) {
        WorkUsd = workUsd;
    }
    @XmlElement(name="DestinationCountry")
    public String getDestinationCountry() {
        return DestinationCountry;
    }

    public void setDestinationCountry(String destinationCountry) {
        DestinationCountry = destinationCountry;
    }

    private String FirstQty;
    private String FirstUnit;
    private String GUnit;
    private String GModel; private String GName;
    private String GNo;
    private String GQty;
    private String OriginCountry;
    private String SecondUnit;
    private String SecondQty;
    private String TradeCurr;
    private String UseTo;
    private String WorkUsd;
    private String DestinationCountry;



}
