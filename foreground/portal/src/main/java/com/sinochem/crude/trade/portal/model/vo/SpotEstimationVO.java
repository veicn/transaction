package com.sinochem.crude.trade.portal.model.vo;

import com.eyeieye.melody.util.StringUtil;
import com.sinochem.crude.trade.portal.enums.AvailableArrivalMonthDifferenceEnum;
import com.sinochem.crude.trade.portal.enums.AvailableDischargePortEnum;
import com.sinochem.crude.trade.portal.enums.CurrencyEnum;
import com.sinochem.crude.trade.portal.enums.OilTypeEnum;
import com.sinochem.crude.trade.portal.helper.ExchangeRateHelper;
import com.sinochem.crude.trade.portal.service.impl.factory.SpotPriceStrategyFactory;
import com.sinochem.crude.trade.portal.service.impl.strategy.YearMonthPairStrategy;
import com.sinochem.crude.trade.portal.service.impl.strategy.base.AbstractSpotPriceStrategy;
import org.springframework.web.context.ContextLoader;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

/**
 * 实货预测价格的参数VO
 * @author Yichen Zhao
 * date: 20180415
 */
public class SpotEstimationVO implements Serializable {

    private final int SCALE = 4;

    /**
     * 计价货币种类
     */
    private String currencyCode;

    /**
     * 实货计算结果
     */
    private String spotPriceResult;

    /**
     * 油种
     */
    private String oilTypeCode;

    /**
     * 所选的期货
     */
    private String futuresSymbol;

    /**
     * 期货所选年月，格式为yyyyMM
     */
    private String futuresYearMonthPair;

    /**
     * 现货所选年月，格式为yyyyMM
     */
    private String arrivalYearMonthPair;

    /**
     * 卸港代码
     */
    private String shipPortCode;

    /**
     * 仓储成本
     */
    private String storageCost;

    /**
     * FOB现货成本
     */
    private String spotCostFOB;

    /**
     * 国外运费
     */
    private String abroadTransportationFee;

    /**
     * 滞期费
     */
    private String demurrageCharge;

    /**
     * 货物保险费
     */
    private String cargoPremium;

    /**
     * 资金成本
     */
    private String capitalCost;

    /**
     * 固定费用
     */
    private String fixedCost;

    /**
     * 其它费用
     */
    private String otherCost;

    /**
     * 人民币远期汇率
     */
    private String exchangeRate;

    /**
     * 货损
     */
    private String storageLoss;

    /** getters and setters */
    public String getOilTypeCode() {
        return oilTypeCode;
    }

    public void setOilTypeCode(String oilTypeCode) {
        this.oilTypeCode = oilTypeCode;
    }

    public String getFuturesYearMonthPair() {
        return futuresYearMonthPair;
    }

    public void setFuturesYearMonthPair(String futuresYearMonthPair) {
        this.futuresYearMonthPair = futuresYearMonthPair;
    }

    public String getArrivalYearMonthPair() {
        return arrivalYearMonthPair;
    }

    public void setArrivalYearMonthPair(String arrivalYearMonthPair) {
        this.arrivalYearMonthPair = arrivalYearMonthPair;
    }

    public String getShipPortCode() {
        return shipPortCode;
    }

    public void setShipPortCode(String shipPortCode) {
        this.shipPortCode = shipPortCode;
    }

    public String getStorageCost() {
        return storageCost;
    }

    public void setStorageCost(String storageCost) {
        this.storageCost = storageCost;
    }

    public String getSpotCostFOB() {
        return spotCostFOB;
    }

    public void setSpotCostFOB(String spotCostFOB) {
        this.spotCostFOB = spotCostFOB;
    }


    public String getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(String exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public String getStorageLoss() {
        return storageLoss;
    }

    public void setStorageLoss(String storageLoss) {
        this.storageLoss = storageLoss;
    }

    public String getOtherCost() {
        return otherCost;
    }

    public void setOtherCost(String otherCost) {
        this.otherCost = otherCost;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getSpotPriceResult() {
        return spotPriceResult;
    }

    public void setSpotPriceResult(String spotPriceResult) {
        this.spotPriceResult = spotPriceResult;
    }

    public int getSCALE() {
        return SCALE;
    }

    public String getAbroadTransportationFee() {
        return abroadTransportationFee;
    }

    public void setAbroadTransportationFee(String abroadTransportationFee) {
        this.abroadTransportationFee = abroadTransportationFee;
    }

    public String getDemurrageCharge() {
        return demurrageCharge;
    }

    public void setDemurrageCharge(String demurrageCharge) {
        this.demurrageCharge = demurrageCharge;
    }

    public String getCargoPremium() {
        return cargoPremium;
    }

    public void setCargoPremium(String cargoPremium) {
        this.cargoPremium = cargoPremium;
    }

    public String getCapitalCost() {
        return capitalCost;
    }

    public void setCapitalCost(String capitalCost) {
        this.capitalCost = capitalCost;
    }

    public String getFixedCost() {
        return fixedCost;
    }

    public void setFixedCost(String fixedCost) {
        this.fixedCost = fixedCost;
    }

    public String getFuturesSymbol() {
        return futuresSymbol;
    }

    public void setFuturesSymbol(String futuresSymbol) {
        this.futuresSymbol = futuresSymbol;
    }
}
