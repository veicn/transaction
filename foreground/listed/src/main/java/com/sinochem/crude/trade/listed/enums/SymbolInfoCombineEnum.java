package com.sinochem.crude.trade.listed.enums;

/**
 * 资讯合约中的字段组合情况
 * @author Yichen Zhao
 * date: 20180204
 */
public enum SymbolInfoCombineEnum {

    DTD_BRENT_SPREAD(
            CommodityEnum.DTD_BRENT_SPREAD.getCode(),
            PriceSourceEnum.PLATTS.getCode(),
            MarketEnum.DATED_NORTH_SEA.getCode()
    ),
    DUBAI_SPREAD(
            CommodityEnum.DUBAI_SPREAD.getCode(),
            PriceSourceEnum.PLATTS.getCode(),
            MarketEnum.FOB_ARAB_GULF.getCode()
    ),
    EFS(
            CommodityEnum.EFS.getCode(),
            PriceSourceEnum.PLATTS.getCode(),
            MarketEnum.ICE_DUBAI_SWAP.getCode()
    ),
    DTD_BRENT_DUBAI(
            CommodityEnum.DTD_BRENT_DUBAI.getCode(),
            PriceSourceEnum.PLATTS.getCode(),
            MarketEnum.DTD_BRENT_DUBAI.getCode()
    ),
    BRENT(
            CommodityEnum.BRENT.getCode(),
            PriceSourceEnum.EXCHANGE.getCode(),
            MarketEnum.ICE.getCode()
    ),
    WTI(
            CommodityEnum.WTI.getCode(),
            PriceSourceEnum.EXCHANGE.getCode(),
            MarketEnum.NYMEX.getCode()
    );

    String commodity;
    String priceSource;
    String market;

    SymbolInfoCombineEnum(String commodity, String priceSource, String market) {
        this.commodity = commodity;
        this.priceSource = priceSource;
        this.market = market;
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
}
