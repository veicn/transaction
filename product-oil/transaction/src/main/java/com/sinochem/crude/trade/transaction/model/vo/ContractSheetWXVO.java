package com.sinochem.crude.trade.transaction.model.vo;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.validator.internal.constraintvalidators.bv.past.PastValidatorForReadableInstant;

import java.util.Date;

/**
 * 合约单（微信订单）的VO
 * @author zhaokuifeng
 * date: 20180417
 */
//去掉返回null
@JsonSerialize(include= JsonSerialize.Inclusion.NON_NULL)
public class ContractSheetWXVO {
    /**
     * 合约单编号
     */
    private String serialNumber;

    public String getContractNO() {
        return contractNO;
    }

    public void setContractNO(String contractNO) {
        this.contractNO = contractNO;
    }

    private String quantityDter;

    public String getQuantityDter() {
        return quantityDter;
    }

    public void setQuantityDter(String quantityDter) {
        this.quantityDter = quantityDter;
    }

    private String laytimeAsString;

    public String getLaytimeAsString() {
        return laytimeAsString;
    }

    public void setLaytimeAsString(String laytimeAsString) {
        this.laytimeAsString = laytimeAsString;
    }

    private String qualityStandard;

    public String getQualityStandard() {
        return qualityStandard;
    }

    public void setQualityStandard(String qualityStandard) {
        this.qualityStandard = qualityStandard;
    }

    /**
     * 合约单编号
     */
    private String contractNO;

    public String getContractDate() {
        return contractDate;
    }

    public void setContractDate(String contractDate) {
        this.contractDate = contractDate;
    }

    /**
     * 合同日期
     */
    private String contractDate;

    /**
     * 合同日期
     */
    private String contractDateStart;

    public String getContractDateStart() {
        return contractDateStart;
    }

    public void setContractDateStart(String contractDateStart) {
        this.contractDateStart = contractDateStart;
    }

    public String getContractDateEnd() {
        return contractDateEnd;
    }

    public void setContractDateEnd(String contractDateEnd) {
        this.contractDateEnd = contractDateEnd;
    }

    /**
     * 合同日期

     */
    private String contractDateEnd;
    /**
     * 计价公式
     */
    private String pricingFormula;

    public String getPricingFormula() {
        return pricingFormula;
    }

    public void setPricingFormula(String pricingFormula) {
        this.pricingFormula = pricingFormula;
    }

    public String getInspection() {
        return inspection;
    }

    public void setInspection(String inspection) {
        this.inspection = inspection;
    }

    public String getPricingBenchmark2() {
        return pricingBenchmark2;
    }

    public void setPricingBenchmark2(String pricingBenchmark2) {
        this.pricingBenchmark2 = pricingBenchmark2;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    private String endpoint;

    /**
     * 商检费分摊
     */
    private String inspection;
    /**
     * 基准2
     */
    private String    pricingBenchmark2;

    public String getOtherTerm() {
        return otherTerm;
    }

    public void setOtherTerm(String otherTerm) {
        this.otherTerm = otherTerm;
    }


    private String buyerId;
    private String salerId;

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getSalerId() {
        return salerId;
    }

    public void setSalerId(String salerId) {
        this.salerId = salerId;
    }

    /**
     * 买家公司名称
     */

    private String buyerName;
    /**
     * 买家公司地址
     */
    private String buyerAddress;
    /**
     * 买家联系人
     */
    private String buyerTraderName;
    /**
     * 买家电话
     */
    private String buyerTelephone;
    /**
     * 买家邮箱
     */
    private String buyerEmail;
    /**
     * 买家传真
     */
    private String buyerFax;

    /**
     * 卖家公司名称
     */
    private String salerName;

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getBuyerAddress() {
        return buyerAddress;
    }

    public void setBuyerAddress(String buyerAddress) {
        this.buyerAddress = buyerAddress;
    }

    public String getBuyerTraderName() {
        return buyerTraderName;
    }

    public void setBuyerTraderName(String buyerTraderName) {
        this.buyerTraderName = buyerTraderName;
    }

    public String getBuyerTelephone() {
        return buyerTelephone;
    }

    public void setBuyerTelephone(String buyerTelephone) {
        this.buyerTelephone = buyerTelephone;
    }

    public String getBuyerEmail() {
        return buyerEmail;
    }

    public void setBuyerEmail(String buyerEmail) {
        this.buyerEmail = buyerEmail;
    }

    public String getBuyerFax() {
        return buyerFax;
    }

    public void setBuyerFax(String buyerFax) {
        this.buyerFax = buyerFax;
    }

    public String getSalerName() {
        return salerName;
    }

    public void setSalerName(String salerName) {
        this.salerName = salerName;
    }

    public String getSalerAddress() {
        return salerAddress;
    }

    public void setSalerAddress(String salerAddress) {
        this.salerAddress = salerAddress;
    }

    public String getSalerTraderName() {
        return salerTraderName;
    }

    public void setSalerTraderName(String salerTraderName) {
        this.salerTraderName = salerTraderName;
    }

    public String getSalerTelephone() {
        return salerTelephone;
    }

    public void setSalerTelephone(String salerTelephone) {
        this.salerTelephone = salerTelephone;
    }

    public String getSalerEmail() {
        return salerEmail;
    }

    public void setSalerEmail(String salerEmail) {
        this.salerEmail = salerEmail;
    }

    public String getSalerFax() {
        return salerFax;
    }

    public void setSalerFax(String salerFax) {
        this.salerFax = salerFax;
    }

    /**

     * 卖家公司地址
     */
    private String salerAddress;
    /**
     * 卖家联系人
     */
    private String salerTraderName;
    /**
     * 卖家电话
     */
    private String salerTelephone;
    /**
     * 卖家邮箱
     */
    private String salerEmail;
    /**
     * 卖家传真
     */
    private String salerFax;

    /**
     * 其他
     */
    private String otherTerm;

    public String getLaw() {
        return law;
    }

    public void setLaw(String law) {
        this.law = law;
    }

    /**
     * 法律
     */
    private String law;

    public String getPaymentTerm() {
        return paymentTerm;
    }

    public void setPaymentTerm(String paymentTerm) {
        this.paymentTerm = paymentTerm;
    }

    /**
     * 付款条件
     */
    private String  paymentTerm;



    public String getDemurrageRateNum() {
        return demurrageRateNum;
    }

    public void setDemurrageRateNum(String demurrageRateNum) {
        this.demurrageRateNum = demurrageRateNum;
    }

    /**
     * 滞期费率
     */
    private String  demurrageRateNum;

    public String getTransportMode() {
        return transportMode;
    }

    public void setTransportMode(String transportMode) {
        this.transportMode = transportMode;
    }

    /**
     * 运输方式
     */

    private String transportMode;
    /**
     * 合约单状态
     */
    private String contractSheetStatus;
    /**
     * 误差
     */
    private String  tolerance;

    public String getPricingBenchmark() {
        return pricingBenchmark;
    }

    public void setPricingBenchmark(String pricingBenchmark) {
        this.pricingBenchmark = pricingBenchmark;
    }

    /**
     * 基准
     */
    private String  pricingBenchmark;

    public String getLoadCountry() {
        return loadCountry;
    }

    public void setLoadCountry(String loadCountry) {
        this.loadCountry = loadCountry;
    }

    public String getLoadPort() {
        return loadPort;
    }

    public void setLoadPort(String loadPort) {
        this.loadPort = loadPort;
    }

    public String getDischargeCountry() {
        return dischargeCountry;
    }

    public void setDischargeCountry(String dischargeCountry) {
        this.dischargeCountry = dischargeCountry;
    }

    /**
     * 装港国家
     */
    private String loadCountry;


    /**
     * 装港港口
     */
    private String loadPort;

    /**
     * 卸港国家
     */
    private String dischargeCountry;


    /**
     * 卸港港口
     */
    private String dischargePort;

    public String getPricingPeriod() {
        return pricingPeriod;
    }

    public void setPricingPeriod(String pricingPeriod) {
        this.pricingPeriod = pricingPeriod;
    }

    /**
     * 卸港港口
     */
    private String pricingPeriod;

    public String getDischargePort() {
        return dischargePort;
    }

    public void setDischargePort(String dischargePort) {
        this.dischargePort = dischargePort;
    }

    private String createuser;
    private String createtime;

    private String settleNum;
    private String settleAmt;

    public String getTradeTerm() {
        return tradeTerm;
    }

    public void setTradeTerm(String tradeTerm) {
        this.tradeTerm = tradeTerm;
    }

    /**
     * 贸易条款
     */
    private String tradeTerm;

    public String getCreateuser() {
        return createuser;
    }

    public void setCreateuser(String createuser) {
        this.createuser = createuser;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getSettleNum() {
        return settleNum;
    }

    public void setSettleNum(String settleNum) {
        this.settleNum = settleNum;
    }

    public String getSettleAmt() {
        return settleAmt;
    }

    public void setSettleAmt(String settleAmt) {
        this.settleAmt = settleAmt;
    }

    public String getUpInvoiceStatus() {
        return upInvoiceStatus;
    }

    public void setUpInvoiceStatus(String upInvoiceStatus) {
        this.upInvoiceStatus = upInvoiceStatus;
    }

    private String upInvoiceStatus;

/*
油品
 */
    private String productCategory;
    /*
    规格
     */
    private String productSpecification;

    private String laycanStartDateAsString;

    private  String laycanEndDateAsString;

    public String getLaycanDateAsString() {
        return laycanDateAsString;
    }

    public void setLaycanDateAsString(String laycanDateAsString) {
        this.laycanDateAsString = laycanDateAsString;
    }

    private  String laycanDateAsString;

    private String premiumsAndDiscounts;

    public String getPricingUnit() {
        return pricingUnit;
    }

    public void setPricingUnit(String pricingUnit) {
        this.pricingUnit = pricingUnit;
    }

    private  String pricingUnit;

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    private  String quantity;

    public String getProductCategoryImg() {
        return productCategoryImg;
    }

    public void setProductCategoryImg(String productCategoryImg) {
        this.productCategoryImg = productCategoryImg;
    }

    private String productCategoryImg;

    public String getTolerance() {
        return tolerance;
    }

    public void setTolerance(String tolerance) {
        this.tolerance = tolerance;
    }


    public String getTradeTermCode() {
        return tradeTermCode;
    }

    public void setTradeTermCode(String tradeTermCode) {
        this.tradeTermCode = tradeTermCode;
    }

    private String tradeTermCode;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    private String uuid;

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getContractSheetStatus() {
        return contractSheetStatus;
    }

    public void setContractSheetStatus(String contractSheetStatus) {
        this.contractSheetStatus = contractSheetStatus;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getProductSpecification() {
        return productSpecification;
    }

    public void setProductSpecification(String productSpecification) {
        this.productSpecification = productSpecification;
    }

    public String getLaycanStartDateAsString() {
        return laycanStartDateAsString;
    }

    public void setLaycanStartDateAsString(String laycanStartDateAsString) {
        this.laycanStartDateAsString = laycanStartDateAsString;
    }

    public String getLaycanEndDateAsString() {
        return laycanEndDateAsString;
    }

    public void setLaycanEndDateAsString(String laycanEndDateAsString) {
        this.laycanEndDateAsString = laycanEndDateAsString;
    }

    public String getPremiumsAndDiscounts() {
        return premiumsAndDiscounts;
    }

    public void setPremiumsAndDiscounts(String premiumsAndDiscounts) {
        this.premiumsAndDiscounts = premiumsAndDiscounts;
    }


    /**
     * 出口代理商
     */
    private String agentName;
    private String agentContactAddress;
    private String agentContactName;
    private String agentEmail;
    private String agentContactPhone;

    public String getAgentFax() {
        return agentFax;
    }

    public void setAgentFax(String agentFax) {
        this.agentFax = agentFax;
    }

    private String agentFax;

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getAgentContactAddress() {
        return agentContactAddress;
    }

    public void setAgentContactAddress(String agentContactAddress) {
        this.agentContactAddress = agentContactAddress;
    }

    public String getAgentContactName() {
        return agentContactName;
    }

    public void setAgentContactName(String agentContactName) {
        this.agentContactName = agentContactName;
    }

    public String getAgentEmail() {
        return agentEmail;
    }

    public void setAgentEmail(String agentEmail) {
        this.agentEmail = agentEmail;
    }

    public String getAgentContactPhone() {
        return agentContactPhone;
    }

    public void setAgentContactPhone(String agentContactPhone) {
        this.agentContactPhone = agentContactPhone;
    }


}
