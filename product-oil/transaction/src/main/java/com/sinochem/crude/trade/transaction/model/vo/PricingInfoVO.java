package com.sinochem.crude.trade.transaction.model.vo;

import com.eyeieye.melody.util.StringUtil;
import com.sinochem.crude.trade.common.base.BasePersistVO;
import com.sinochem.crude.trade.common.model.vo.DictionaryVO;
import com.sinochem.crude.trade.transaction.domain.po.PricingInfo;
import com.sinochem.crude.trade.transaction.service.DictionaryService;
import org.springframework.web.context.ContextLoader;

import java.math.BigDecimal;

/**
 * 价格信息的VO
 * @author Yichen Zhao
 * date: 20180228
 */
public class PricingInfoVO extends BasePersistVO<PricingInfo> {

    /**
     * 计价货币（CurrencyEnum）
     */
    private DictionaryVO currencyVO;

    /**
     * 计价基准（PricingBenchmarkEnum）
     */
    private DictionaryVO pricingBenchmarkVO;

    /**
     * 计价基准（PricingBenchmarkEnum）
     */
    private DictionaryVO pricingBenchmarkVO2;

    /**
     * 计量单位（PricingUnitEnum）
     */
    private DictionaryVO pricingUnitVO;

    /**
     * 价格源（PriceSourceEnum）
     */
    private DictionaryVO priceSourceVO;

    /**
     * 价格源（PriceSourceEnum）
     */
    private DictionaryVO priceSourceVO2;

    /**
     * 市场（PriceRegionEnum）
     */
    private DictionaryVO priceRegionVO;
    /**
     * 市场（PriceRegionEnum）
     */
    private DictionaryVO priceRegionVO2;

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
     * 商检费
     */
    private DictionaryVO inspectionVO;

    /**
     * 法律
     */
    private String law;

    /**
     * 升贴水
     */
    private String premiumsAndDiscountsAsString;

    public PricingInfoVO(){
    	
    }
    public PricingInfoVO(PricingInfo pricingInfo) {
        super(pricingInfo);
    }

    @Override
    protected void convertToVO(PricingInfo domain) {
        DictionaryService dictionaryService =
                ContextLoader.getCurrentWebApplicationContext().getBean(DictionaryService.class);

        String currencyCode = domain.getCurrencyCode();
        if (!StringUtil.isEmpty(currencyCode)) {
            DictionaryVO currencyVO = dictionaryService.getCurrency(currencyCode);
            this.currencyVO = currencyVO;
        }

        String pricingBenchmarkCode = domain.getPricingBenchmarkCode();
        if (!StringUtil.isEmpty(pricingBenchmarkCode)) {
            DictionaryVO pricingBenchmarkVO = dictionaryService.getPricingBenchmark(pricingBenchmarkCode);
            this.pricingBenchmarkVO = pricingBenchmarkVO;
        }
        String pricingBenchmarkCode2 = domain.getPricingBenchmarkCode2();
        if (!StringUtil.isEmpty(pricingBenchmarkCode2)) {
            DictionaryVO pricingBenchmarkVO2 = dictionaryService.getPricingBenchmark2(pricingBenchmarkCode2);
            this.pricingBenchmarkVO2 = pricingBenchmarkVO2;
        }

        String pricingUnitCode = domain.getPricingUnitCode();
        if (!StringUtil.isEmpty(pricingUnitCode)) {
            DictionaryVO pricingUnitVO = dictionaryService.getPricingUnit(pricingUnitCode);
            this.pricingUnitVO = pricingUnitVO;
        }

        String priceSourceCode = domain.getPriceSourceCode();
        if (!StringUtil.isEmpty(priceSourceCode)) {
            DictionaryVO priceSourceVO = dictionaryService.getPriceSource(priceSourceCode);
            this.priceSourceVO = priceSourceVO;
        }

        String priceSourceCode2 = domain.getPriceSourceCode2();
        if (!StringUtil.isEmpty(priceSourceCode2)) {
            DictionaryVO priceSourceVO2 = dictionaryService.getPriceSource2(priceSourceCode2);
            this.priceSourceVO2 = priceSourceVO2;
        }

        String priceRegionCode = domain.getPriceRegionCode();
        if (!StringUtil.isEmpty(priceRegionCode)) {
            DictionaryVO priceRegionVO = dictionaryService.getPriceRegion(priceRegionCode);
            this.priceRegionVO = priceRegionVO;
        }

        String priceRegionCode2 = domain.getPriceRegionCode2();
        if (!StringUtil.isEmpty(priceRegionCode2)) {
            DictionaryVO priceRegionVO2 = dictionaryService.getPriceRegion2(priceRegionCode2);
            this.priceRegionVO2 = priceRegionVO2;
        }

        String settlementQuantity = domain.getSettlementQuantity();
        if (!StringUtil.isEmpty(settlementQuantity)) {
            this.settlementQuantity = settlementQuantity;
        }

        String pricingPeriod = domain.getPricingPeriod();
        if (!StringUtil.isEmpty(pricingPeriod)) {
            this.pricingPeriod = pricingPeriod;
        }

        String paymentTerm = domain.getPaymentTerm();
        if (!StringUtil.isEmpty(paymentTerm)) {
            //DictionaryVO paymentTermVO = dictionaryService.getPaymentTerm(paymentTermCode);
            this.paymentTerm = paymentTerm;
        }

        String inspectionCode = domain.getInspectionCode();
        if (!StringUtil.isEmpty(inspectionCode)) {
            DictionaryVO inspectionVO = dictionaryService.getInspetion(inspectionCode);
            this.inspectionVO = inspectionVO;
        }

        String law = domain.getLaw();
        if (!StringUtil.isEmpty(law)) {
            //DictionaryVO paymentTermVO = dictionaryService.getPaymentTerm(paymentTermCode);
            this.law = law;
        }



        BigDecimal premiumsAndDiscounts = domain.getPremiumsAndDiscounts();
        if (premiumsAndDiscounts != null) {
            this.premiumsAndDiscountsAsString = premiumsAndDiscounts.stripTrailingZeros().toPlainString();
        }
    }

    @Override
    protected PricingInfo convertToDomain() {
        PricingInfo pricingInfo = new PricingInfo();

        if (this.currencyVO != null) {
            pricingInfo.setCurrencyCode(this.currencyVO.getCode());
        }

        if (this.pricingBenchmarkVO != null) {
            pricingInfo.setPricingBenchmarkCode(this.pricingBenchmarkVO.getCode());
        }
        if (this.pricingBenchmarkVO2 != null) {
            pricingInfo.setPricingBenchmarkCode2(this.pricingBenchmarkVO2.getCode());
        }

        if (this.pricingUnitVO != null) {
            pricingInfo.setPricingUnitCode(this.pricingUnitVO.getCode());
        }

        if (this.priceSourceVO != null) {
            pricingInfo.setPriceSourceCode(this.priceSourceVO.getCode());
        }

        if (this.priceRegionVO != null) {
            pricingInfo.setPriceRegionCode(this.priceRegionVO.getCode());
        }

        if (this.priceSourceVO2 != null) {
            pricingInfo.setPriceSourceCode2(this.priceSourceVO2.getCode());
        }

        if (this.priceRegionVO2 != null) {
            pricingInfo.setPriceRegionCode2(this.priceRegionVO2.getCode());
        }

        if (!StringUtil.isEmpty(this.settlementQuantity)) {
            pricingInfo.setSettlementQuantity(this.settlementQuantity);
        }

        if (!StringUtil.isEmpty(this.law)) {
            pricingInfo.setLaw(this.law);
        }
        if (this.inspectionVO != null) {
            pricingInfo.setInspectionCode(this.inspectionVO.getCode());
        }

        if (!StringUtil.isEmpty(this.pricingPeriod)) {
            pricingInfo.setPricingPeriod(this.pricingPeriod);
        }

        if (!StringUtil.isEmpty(this.paymentTerm)) {
            pricingInfo.setPaymentTerm(this.paymentTerm);
        }

        if (!StringUtil.isEmpty(this.premiumsAndDiscountsAsString)) {
            pricingInfo.setPremiumsAndDiscounts(new BigDecimal(this.premiumsAndDiscountsAsString));
        }



        return pricingInfo;
    }

    /** getters and setters */
    public DictionaryVO getCurrencyVO() {
        return currencyVO;
    }

    public void setCurrencyVO(DictionaryVO currencyVO) {
        this.currencyVO = currencyVO;
    }

    public DictionaryVO getPricingBenchmarkVO() {
        return pricingBenchmarkVO;
    }

    public void setPricingBenchmarkVO(DictionaryVO pricingBenchmarkVO) {
        this.pricingBenchmarkVO = pricingBenchmarkVO;
    }

    public DictionaryVO getPricingUnitVO() {
        return pricingUnitVO;
    }

    public void setPricingUnitVO(DictionaryVO pricingUnitVO) {
        this.pricingUnitVO = pricingUnitVO;
    }

    public DictionaryVO getPriceSourceVO() {
        return priceSourceVO;
    }

    public void setPriceSourceVO(DictionaryVO priceSourceVO) {
        this.priceSourceVO = priceSourceVO;
    }

    public DictionaryVO getPriceRegionVO() {
        return priceRegionVO;
    }

    public void setPriceRegionVO(DictionaryVO priceRegionVO) {
        this.priceRegionVO = priceRegionVO;
    }

    public DictionaryVO getInspectionVO() {
        return inspectionVO;
    }

    public void setInspectionVO(DictionaryVO inspectionVO) {
        this.inspectionVO = inspectionVO;
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

    public String getPremiumsAndDiscountsAsString() {
        return premiumsAndDiscountsAsString;
    }

    public void setPremiumsAndDiscountsAsString(String premiumsAndDiscountsAsString) {
        this.premiumsAndDiscountsAsString = premiumsAndDiscountsAsString;
    }

    public DictionaryVO getPricingBenchmarkVO2() {
        return pricingBenchmarkVO2;
    }

    public void setPricingBenchmarkVO2(DictionaryVO pricingBenchmarkVO2) {
        this.pricingBenchmarkVO2 = pricingBenchmarkVO2;
    }

    public DictionaryVO getPriceSourceVO2() {
        return priceSourceVO2;
    }

    public void setPriceSourceVO2(DictionaryVO priceSourceVO2) {
        this.priceSourceVO2 = priceSourceVO2;
    }

    public DictionaryVO getPriceRegionVO2() {
        return priceRegionVO2;
    }

    public void setPriceRegionVO2(DictionaryVO priceRegionVO2) {
        this.priceRegionVO2 = priceRegionVO2;
    }

    public String getLaw() {
        return law;
    }

    public void setLaw(String law) {
        this.law = law;
    }
}
