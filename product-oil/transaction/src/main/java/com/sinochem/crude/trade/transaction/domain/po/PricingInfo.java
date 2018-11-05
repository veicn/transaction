package com.sinochem.crude.trade.transaction.domain.po;

import com.sinochem.crude.trade.common.base.BasePO;

import java.math.BigDecimal;

/**
 * 价格信息，包括需求单和报价单
 * @author Yichen Zhao
 * date: 20180226
 */
public class PricingInfo extends BasePO {

    /**
     * 计价货币（CurrencyEnum）
     */
    private String currencyCode;

    /**
     * 计价基准（PricingBenchmarkEnum）
     */
    private String pricingBenchmarkCode;

    /**
     * 计价基准（PricingBenchmarkEnum）
     */
    private String pricingBenchmarkCode2;

    /**
     * 计量单位（PricingUnitEnumerate）
     */
    private String pricingUnitCode;

    /**
     * 价格源（PriceSourceEnum）
     */
    private String priceSourceCode;

    /**
     * 价格源（PriceSourceEnum）
     */
    private String priceSourceCode2;

    /**
     * 市场（PriceRegionEnum）
     */
    private String priceRegionCode;

    /**
     * 市场（PriceRegionEnum）
     */
    private String priceRegionCode2;

    /**
     * 结算量标准（SettlemenQuantityEnum）
     */
    private String settlementQuantity;

    /**
     * 计价期（PricingPeriodEnum）
     */
    private String pricingPeriod;

    /**
     * 付款条件（PaymentTermEnum）
     */
    private String paymentTerm;

    /**
     * 升贴水
     */
    private BigDecimal premiumsAndDiscounts;

    /**
     *
     */
    private String InspectionCode;

    /**
     *法律
     */
    private String law;

    /** getters and setters */
    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getInspectionCode() {
        return InspectionCode;
    }

    public void setInspectionCode(String inspectionCode) {
        InspectionCode = inspectionCode;
    }

    public String getLaw() {
        return law;
    }

    public void setLaw(String law) {
        this.law = law;
    }

    public String getPricingBenchmarkCode() {
        return pricingBenchmarkCode;
    }

    public void setPricingBenchmarkCode(String pricingBenchmarkCode) {
        this.pricingBenchmarkCode = pricingBenchmarkCode;
    }

    public String getPriceSourceCode() {
        return priceSourceCode;
    }

    public void setPriceSourceCode(String priceSourceCode) {
        this.priceSourceCode = priceSourceCode;
    }

    public String getPriceRegionCode() {
        return priceRegionCode;
    }

    public void setPriceRegionCode(String priceRegionCode) {
        this.priceRegionCode = priceRegionCode;
    }

    public String getPricingUnitCode() {
        return pricingUnitCode;
    }

    public void setPricingUnitCode(String pricingUnitCode) {
        this.pricingUnitCode = pricingUnitCode;
    }

    public String getSettlementQuantity() {
        return settlementQuantity;
    }

    public void setSettlementQuantity(String settlementQuantity) {
        this.settlementQuantity = settlementQuantity;
    }

    public String getPricingPeriod() {
        return pricingPeriod;
    }

    public void setPricingPeriod(String pricingPeriod) {
        this.pricingPeriod = pricingPeriod;
    }

    public String getPaymentTerm() {
        return paymentTerm;
    }

    public void setPaymentTerm(String paymentTerm) {
        this.paymentTerm = paymentTerm;
    }

    public BigDecimal getPremiumsAndDiscounts() {
        return premiumsAndDiscounts;
    }

    public void setPremiumsAndDiscounts(BigDecimal premiumsAndDiscounts) {
        this.premiumsAndDiscounts = premiumsAndDiscounts;
    }

    public String getPricingBenchmarkCode2() {
        return pricingBenchmarkCode2;
    }

    public void setPricingBenchmarkCode2(String pricingBenchmarkCode2) {
        this.pricingBenchmarkCode2 = pricingBenchmarkCode2;
    }

    public String getPriceSourceCode2() {
        return priceSourceCode2;
    }

    public void setPriceSourceCode2(String priceSourceCode2) {
        this.priceSourceCode2 = priceSourceCode2;
    }

    public String getPriceRegionCode2() {
        return priceRegionCode2;
    }

    public void setPriceRegionCode2(String priceRegionCode2) {
        this.priceRegionCode2 = priceRegionCode2;
    }
}
