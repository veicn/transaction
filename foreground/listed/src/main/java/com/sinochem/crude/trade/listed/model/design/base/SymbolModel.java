package com.sinochem.crude.trade.listed.model.design.base;

import java.math.BigDecimal;

/**
 * 资讯合约模型
 * @author Yichen Zhao
 * date: 20180204
 */
public class SymbolModel implements Cloneable {

    /**
     * 品类
     */
    private String commodity;

    /**
     * 来源
     */
    private String priceSource;

    /**
     * 市场
     */
    private String market;

    /**
     * 合约名称
     */
    private String symbolName;

    /**
     * 合约代码，通过调用资讯接口请求
     */
    private String symbolCode;

    /**
     * 合约结算价，同样通过请求获得
     */
    private BigDecimal settlementPrice;

    public SymbolModel() {}

    public SymbolModel(String commodity, String priceSource, String market, String symbolName, String symbolCode, BigDecimal settlementPrice) {
        this.commodity = commodity;
        this.priceSource = priceSource;
        this.market = market;
        this.symbolName = symbolName;
        this.symbolCode = symbolCode;
        this.settlementPrice = settlementPrice;
    }

    /**
     * Clone方法
     */
    public Object clone(SymbolModel clone) {

        clone.setCommodity(this.commodity);
        clone.setPriceSource(this.priceSource);
        clone.setMarket(this.market);
        clone.setSymbolName(this.symbolName);
        clone.setSymbolCode(this.symbolCode);
        clone.setSettlementPrice(new BigDecimal(this.settlementPrice.doubleValue()));

        return clone;
    }

    /** getters and setters */
    public String getCommodity() {
        return commodity;
    }

    public void setCommodity(String commodity) {
        this.commodity = commodity;
    }

    public String getPriceSource() {
        return priceSource;
    }

    public void setPriceSource(String priceSource) {
        this.priceSource = priceSource;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public String getSymbolName() {
        return symbolName;
    }

    public void setSymbolName(String symbolName) {
        this.symbolName = symbolName;
    }

    public String getSymbolCode() {
        return symbolCode;
    }

    public void setSymbolCode(String symbolCode) {
        this.symbolCode = symbolCode;
    }

    public BigDecimal getSettlementPrice() {
        return settlementPrice;
    }

    public void setSettlementPrice(BigDecimal settlementPrice) {
        this.settlementPrice = settlementPrice;
    }
}
