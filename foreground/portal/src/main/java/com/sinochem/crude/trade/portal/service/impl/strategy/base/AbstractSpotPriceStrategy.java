package com.sinochem.crude.trade.portal.service.impl.strategy.base;

import com.eyeieye.melody.util.StringUtil;
import com.sinochem.crude.trade.portal.enums.AvailableArrivalMonthDifferenceEnum;
import com.sinochem.crude.trade.portal.enums.AvailableDischargePortEnum;
import com.sinochem.crude.trade.portal.enums.CurrencyEnum;
import com.sinochem.crude.trade.portal.enums.OilTypeEnum;
import com.sinochem.crude.trade.portal.helper.ExchangeRateHelper;
import com.sinochem.crude.trade.portal.model.vo.SpotEstimationVO;
import com.sinochem.crude.trade.portal.model.vo.SymbolResponseVO;
import com.sinochem.crude.trade.portal.service.impl.factory.SpotPriceStrategyFactory;
import com.sinochem.crude.trade.portal.service.impl.strategy.YearMonthPairStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoader;

import java.math.BigDecimal;
import java.util.*;

/**
 * 用于计算油种现货的抽象策略
 * @author Yichen Zhao
 * date: 20180411
 */
public abstract class AbstractSpotPriceStrategy {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getName());

    private final int SCALE = 4;

    /* 滞期费 */
    public final BigDecimal DEMURRAGE_CHARGE = BigDecimal.valueOf(0.102);

    /* 货物保险费 */
    public final BigDecimal CARGO_PREMIUM = BigDecimal.valueOf(0.030);

    /* 仓储成本常量 */
    private final BigDecimal MIN_STORAGE_COST = BigDecimal.valueOf(4);
    private final BigDecimal ESTIMATED_COST = BigDecimal.valueOf(0.2);
    private final BigDecimal ESTIMATED_DAYS = BigDecimal.valueOf(20);

    /* 商检费 */
    private final BigDecimal COMMODITY_INSPECTION_FEE = BigDecimal.valueOf(0.04);

    /* 货代代理费 */
    private final BigDecimal FORWARDER_AGENCY_FEE = BigDecimal.valueOf(0.05/7.179);

    /* 装卸费 */
    private final BigDecimal HANDLING_CHARGES = BigDecimal.valueOf(10.000/7.179);

    /* 油污损害赔偿基金 */
    private final BigDecimal OIL_POLLUTION_COMPENSATION = BigDecimal.valueOf(0.300/7.179);

    /* 代收安保费 */
    private final BigDecimal PROXY_SECURITY_CHARGE = BigDecimal.valueOf(0.250/7.179);

    /* 代收进油港务费 */
    private final BigDecimal PROXY_OIL_IN_HARBOUR_CHARGE = BigDecimal.valueOf(3.300/7.179);

    /* 代收出油港务费 */
    private final BigDecimal PROXY_OIL_OUT_HARBOUR_CHARGE = BigDecimal.valueOf(0);

    /* 交割手续费 */
    private final BigDecimal CLOSING_CHARGE = BigDecimal.valueOf(0.050);

    /* 国内运费 */
    private final BigDecimal DOMESTIC_FREIGHT = BigDecimal.valueOf(0);

    /* 融资成本 */
    private final BigDecimal FINANCE_COST = BigDecimal.valueOf(0.07);

    /**
     * 获取现货价格（人民币）
     */
    public BigDecimal getSpotPrice(SpotEstimationVO spotEstimationVO) throws Exception {

        YearMonthPairStrategy yearMonthPairStrategy = ContextLoader.getCurrentWebApplicationContext()
                .getBean(YearMonthPairStrategy.class);

        if (spotEstimationVO == null) {
            throw new RuntimeException("参数为空");
        }

        if (StringUtil.isEmpty(spotEstimationVO.getOilTypeCode())) {
            throw new RuntimeException("油种为空");
        }

        if (StringUtil.isEmpty(spotEstimationVO.getCurrencyCode())) {
            spotEstimationVO.setCurrencyCode(CurrencyEnum.CNY.getCode());
        }

        if (StringUtil.isEmpty(spotEstimationVO.getFuturesYearMonthPair())) {
            List<SymbolResponseVO> symbolList = this.getAllSymbolList();
            String symbolName = symbolList.get(0).getSymbolName();
            spotEstimationVO.setFuturesYearMonthPair(symbolName.substring(symbolName.length() - 4));
        }

        if (StringUtil.isEmpty(spotEstimationVO.getArrivalYearMonthPair())) {
            Calendar arrivalCalendar = Calendar.getInstance();
            arrivalCalendar.add(Calendar.MONTH, AvailableArrivalMonthDifferenceEnum.values()[0].getMonthDifference().intValue());
            spotEstimationVO.setArrivalYearMonthPair(yearMonthPairStrategy.convertCalendarToYearMonthPair(arrivalCalendar));
        }

        if (StringUtil.isEmpty(spotEstimationVO.getShipPortCode())) {
            spotEstimationVO.setShipPortCode(AvailableDischargePortEnum.values()[0].getCode());
        }

        Calendar futuresCalendar = yearMonthPairStrategy.convertYearMonthPairToCalendar(spotEstimationVO.getFuturesYearMonthPair());
        Calendar arrivalCalendar = yearMonthPairStrategy.convertYearMonthPairToCalendar(spotEstimationVO.getArrivalYearMonthPair());
        String shipPortCode = spotEstimationVO.getShipPortCode();

        BigDecimal spotCostFOB;
        String spotCostFOBAsString = spotEstimationVO.getSpotCostFOB();
        if (StringUtil.isEmpty(spotCostFOBAsString)) {
            spotCostFOB = getFOBSpotCost(arrivalCalendar);
            spotEstimationVO.setSpotCostFOB(spotCostFOB.setScale(SCALE, BigDecimal.ROUND_HALF_UP).toPlainString());
        } else {
            spotCostFOB = new BigDecimal(spotCostFOBAsString);
        }

        BigDecimal wsPoint = getWsPoint();
        String abroadTransportationFeeAsString = spotEstimationVO.getAbroadTransportationFee();
        BigDecimal abroadTransportationFee;
        if (StringUtil.isEmpty(abroadTransportationFeeAsString)) {
            abroadTransportationFee = getAbroadTransportationFee(arrivalCalendar, shipPortCode, wsPoint);
            spotEstimationVO.setAbroadTransportationFee(abroadTransportationFee.setScale(SCALE, BigDecimal.ROUND_HALF_UP).toPlainString());
        } else {
            abroadTransportationFee = new BigDecimal(abroadTransportationFeeAsString);
        }

        String demurrageChargeAsString = spotEstimationVO.getDemurrageCharge();
        BigDecimal demurrageCharge;
        if (StringUtil.isEmpty(demurrageChargeAsString)) {
            demurrageCharge = getDemurrageCharge();
            spotEstimationVO.setDemurrageCharge(demurrageCharge.setScale(SCALE, BigDecimal.ROUND_HALF_UP).toPlainString());
        } else {
            demurrageCharge = new BigDecimal(demurrageChargeAsString);
        }

        String cargoPremiunAsString = spotEstimationVO.getCargoPremium();
        BigDecimal cargoPremium;
        if (StringUtil.isEmpty(cargoPremiunAsString)) {
            cargoPremium = getCargoPremium();
            spotEstimationVO.setCargoPremium(cargoPremium.setScale(SCALE, BigDecimal.ROUND_HALF_UP).toPlainString());
        } else {
            cargoPremium = new BigDecimal(cargoPremiunAsString);
        }

        String capitalCostAsString = spotEstimationVO.getCapitalCost();
        BigDecimal capitalCost;
        if (StringUtil.isEmpty(capitalCostAsString)) {
            capitalCost = getCapitalCost(arrivalCalendar);
            spotEstimationVO.setCapitalCost(capitalCost.setScale(SCALE, BigDecimal.ROUND_HALF_UP).toPlainString());
        } else {
            capitalCost = new BigDecimal(capitalCostAsString);
        }

        String fixedCostAsString = spotEstimationVO.getFixedCost();
        BigDecimal fixedCost;
        if (StringUtil.isEmpty(fixedCostAsString)) {
            fixedCost = getFixedCost();
            spotEstimationVO.setFixedCost(fixedCost.setScale(SCALE, BigDecimal.ROUND_HALF_UP).toPlainString());
        } else {
            fixedCost = new BigDecimal(fixedCostAsString);
        }

        String otherCostAsString = spotEstimationVO.getOtherCost();
        BigDecimal otherCost;
        if (StringUtil.isEmpty(otherCostAsString)) {
            otherCost = getOtherCost();
            spotEstimationVO.setOtherCost(otherCost.setScale(SCALE, BigDecimal.ROUND_HALF_UP).toPlainString());
        } else {
            otherCost = new BigDecimal(otherCostAsString);
        }

        String storageCostAsString = spotEstimationVO.getStorageCost();
        BigDecimal storageCost;
        if (StringUtil.isEmpty(storageCostAsString) || MIN_STORAGE_COST.compareTo(new BigDecimal(storageCostAsString)) > 0) {
            storageCost = getStorageCost(futuresCalendar, arrivalCalendar);
            spotEstimationVO.setStorageCost(storageCost.setScale(SCALE, BigDecimal.ROUND_HALF_UP).toPlainString());
        } else {
            storageCost = new BigDecimal(storageCostAsString);
        }

        if (MIN_STORAGE_COST.compareTo(storageCost) > 0) {
            storageCost = MIN_STORAGE_COST;
            spotEstimationVO.setStorageCost(MIN_STORAGE_COST.setScale(SCALE, BigDecimal.ROUND_HALF_UP).toPlainString());
        }

        String storageLossAsString = spotEstimationVO.getStorageLoss();
        BigDecimal storageLoss;
        if (StringUtil.isEmpty(storageLossAsString)) {
            storageLoss = getStorageLoss(futuresCalendar, arrivalCalendar, shipPortCode, wsPoint);
            spotEstimationVO.setStorageLoss(storageLoss.setScale(SCALE, BigDecimal.ROUND_HALF_UP).toPlainString());
        } else {
            storageLoss = new BigDecimal(storageLossAsString);
        }

        BigDecimal exchangeRateUSD =  getExchangeRateUSD();
        BigDecimal spotCost = spotCostFOB
                .add(abroadTransportationFee)
                .add(demurrageCharge)
                .add(cargoPremium)
                .multiply(exchangeRateUSD)
                .add(storageCost)
                .add(otherCost)
                .add(getProxyOilOutHarbourCharge())
                .add(capitalCost)
                .add(fixedCost)
                .add(storageLoss)
                .multiply(BigDecimal.valueOf(1 - 0.005)); //换算为人民币

        //处理汇率
        ExchangeRateHelper exchangeRateHelper = ContextLoader.getCurrentWebApplicationContext().getBean(ExchangeRateHelper.class);
        String currencyCode = spotEstimationVO.getCurrencyCode();
        BigDecimal exchangeRate = exchangeRateHelper.getExchangeRate(currencyCode);
        spotEstimationVO.setExchangeRate(exchangeRate.setScale(SCALE, BigDecimal.ROUND_HALF_UP).toPlainString());

        spotCost = spotCost.divide(exchangeRate, SCALE, BigDecimal.ROUND_HALF_UP);

        spotEstimationVO.setSpotPriceResult(spotCost.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());
        return spotCost;
    }

    /** 计算现货价格所需信息 */

    /**
     * FOB现货成本
     */
    public abstract BigDecimal getFOBSpotCost(Calendar arrivalCalendar);

    /**
     * 国外运费
     */
    public abstract BigDecimal getAbroadTransportationFee(Calendar arrivalCalendar, String portCode, BigDecimal wsPoint);

    /**
     * 滞期费
     */
    public BigDecimal getDemurrageCharge() {
        return DEMURRAGE_CHARGE;
    }

    /**
     * 货物保险费
     */
    public BigDecimal getCargoPremium() {
        return CARGO_PREMIUM;
    }

    /**
     * 美元汇率
     */
    private BigDecimal getExchangeRateUSD() throws Exception {
        ExchangeRateHelper exchangeRateHelper = ContextLoader.getCurrentWebApplicationContext().getBean(ExchangeRateHelper.class);

        return exchangeRateHelper.getExchangeRate(CurrencyEnum.USD.getCode());
    }

    /**
     * 仓储成本
     */
    public BigDecimal getStorageCost(Calendar futuresCalendar, Calendar arrivalCalendar) {

        int monthDifference = (futuresCalendar.get(Calendar.YEAR) - arrivalCalendar.get(Calendar.YEAR)) * 12
                + (futuresCalendar.get(Calendar.MONTH) - arrivalCalendar.get(Calendar.MONTH));

        return MIN_STORAGE_COST.add(ESTIMATED_COST.multiply(
                BigDecimal.valueOf(monthDifference).multiply(ESTIMATED_DAYS)));
    }

    public BigDecimal getMinStorageCost() {
        return MIN_STORAGE_COST;
    }

    /**
     * 商检费
     */
    public BigDecimal getCommodityInspectionFee() {
        return COMMODITY_INSPECTION_FEE;
    }

    /**
     * 货代代理费
     */
    public BigDecimal getForwarderAgencyFee() {
        return FORWARDER_AGENCY_FEE;
    }

    /**
     * 装卸费
     */
    public BigDecimal getHandlingCharges() {
        return HANDLING_CHARGES;
    }

    /**
     * 油污损害赔偿基金
     */
    public BigDecimal getOilPollutionCompensation() {
        return OIL_POLLUTION_COMPENSATION;
    }

    /**
     * 代收安保费
     */
    public BigDecimal getProxySecurityCharge() {
        return PROXY_SECURITY_CHARGE;
    }

    /**
     * 代收进油港务费
     */
    public BigDecimal getProxyOilInHarbourCharge() {
        return PROXY_OIL_IN_HARBOUR_CHARGE;
    }

    /**
     * 代收出油港务费
     */
    public BigDecimal getProxyOilOutHarbourCharge() {
        return PROXY_OIL_OUT_HARBOUR_CHARGE;
    }

    /**
     * 交割手续费
     */
    public BigDecimal getClosingCharge() {
        return CLOSING_CHARGE;
    }

    /**
     * 国内运费
     */
    public BigDecimal getDomesticFreight() {
        return DOMESTIC_FREIGHT;
    }

    /**
     * 资金成本
     */
    public BigDecimal getCapitalCost(Calendar arrivalCalendar) throws Exception {

        //保证金
        BigDecimal cashDeposit = getFOBSpotCost(arrivalCalendar)
                .multiply(getExchangeRateUSD())
                .multiply(FINANCE_COST)
                .multiply(BigDecimal.valueOf(0.05*30 + 0.1 * 28 + 0.2 * 8))
                .divide(BigDecimal.valueOf(365), 3, BigDecimal.ROUND_HALF_UP);

        //入库押金
        BigDecimal stockDeposit = BigDecimal.valueOf(1.5)
                .multiply(FINANCE_COST)
                .multiply(BigDecimal.valueOf(30))
                .divide(BigDecimal.valueOf(365), 3, BigDecimal.ROUND_HALF_UP);

        return cashDeposit.add(stockDeposit);
    }

    /**
     * 交割升贴水
     */
    public abstract BigDecimal getPremiumDelivery();

    /**
     * 入库损耗
     */
    public BigDecimal getStorageLoss(Calendar futuresCalendar, Calendar arrivalCalendar, String portCode, BigDecimal wsPoint) throws Exception {
        return getFOBSpotCost(arrivalCalendar)
                .add(getAbroadTransportationFee(arrivalCalendar, portCode, wsPoint))
                .add(getDemurrageCharge())
                .multiply(getExchangeRateUSD())
                .add(getStorageCost(futuresCalendar, arrivalCalendar))
                .add(getCommodityInspectionFee())
                .add(getForwarderAgencyFee())
                .add(getHandlingCharges())
                .add(getOilPollutionCompensation())
                .add(getProxySecurityCharge())
                .add(getProxyOilInHarbourCharge())
                .add(getProxyOilOutHarbourCharge())
                .add(getClosingCharge())
                .add(getDomesticFreight())
                .add(getCapitalCost(arrivalCalendar))
                .add(getCargoPremium())
                .multiply(BigDecimal.valueOf(0.0006));
    }

    /**
     * 固定费用
     */
    public BigDecimal getFixedCost() {

        return getClosingCharge().add(getPremiumDelivery());
    }

    /**
     * 其它费用
     */
    public BigDecimal getOtherCost() {
        return getCommodityInspectionFee()
                .add(getForwarderAgencyFee())
                .add(getHandlingCharges())
                .add(getOilPollutionCompensation())
                .add(getProxySecurityCharge())
                .add(getProxyOilInHarbourCharge())
                .add(getDomesticFreight());
    }

    /**
     * FlatRate
     */
    public Map<String, BigDecimal> getFlatRateMap() {
        Map<String, BigDecimal> flatRateMap = new HashMap<>();

        flatRateMap.put(AvailableDischargePortEnum.ZHOUSHAN.getCode(), BigDecimal.valueOf(14.78));
        flatRateMap.put(AvailableDischargePortEnum.RIZHAO.getCode(), BigDecimal.valueOf(15.47));
        flatRateMap.put(AvailableDischargePortEnum.NINGBO.getCode(), BigDecimal.valueOf(14.79));
        flatRateMap.put(AvailableDischargePortEnum.ZHANJIANG.getCode(), BigDecimal.valueOf(12.85));
        flatRateMap.put(AvailableDischargePortEnum.DALIAN.getCode(), BigDecimal.valueOf(15.96));
        flatRateMap.put(AvailableDischargePortEnum.QINGDAO.getCode(), BigDecimal.valueOf(15.56));
        flatRateMap.put(AvailableDischargePortEnum.QUANZHOU.getCode(), BigDecimal.valueOf(15.7));

        return flatRateMap;
    }

    /**
     * 杂费
     */
    public Map<String, BigDecimal> getSundryChargeMap() {
        Map<String, BigDecimal> sundryChargeMap = new HashMap<>();

        sundryChargeMap.put(AvailableDischargePortEnum.ZHOUSHAN.getCode(), BigDecimal.valueOf(31000));
        sundryChargeMap.put(AvailableDischargePortEnum.RIZHAO.getCode(), BigDecimal.valueOf(25500));
        sundryChargeMap.put(AvailableDischargePortEnum.NINGBO.getCode(), BigDecimal.valueOf(31000));
        sundryChargeMap.put(AvailableDischargePortEnum.ZHANJIANG.getCode(), BigDecimal.valueOf(25500));
        sundryChargeMap.put(AvailableDischargePortEnum.DALIAN.getCode(), BigDecimal.valueOf(25500));
        sundryChargeMap.put(AvailableDischargePortEnum.QINGDAO.getCode(), BigDecimal.valueOf(25500));
        sundryChargeMap.put(AvailableDischargePortEnum.QUANZHOU.getCode(), BigDecimal.valueOf(25500));

        return sundryChargeMap;
    }

    /**
     * 获取所有合约
     */
    public abstract List<SymbolResponseVO> getAllSymbolList() throws Exception;

    /**
     * 获取WS点数
     */
    public abstract BigDecimal getWsPoint() throws Exception;

}