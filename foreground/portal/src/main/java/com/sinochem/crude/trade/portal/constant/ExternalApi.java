package com.sinochem.crude.trade.portal.constant;

/**
 * 接口地址
 * @author Yichen Zhao
 * date: 20180411
 */
public class ExternalApi {

    //船务-值集查询
    public static final String QUERY_VALUE_SET = "/sysShip/queryValueSet.json";

    //资讯-汇率查询
    public static final String INDEC_PRICE_T = "/front/indecPriceT.json";

    //OM-ws点数
    public static final String GET_TODAY_DEAL_POINTS = "/om/dealPoints/queryTodayDealPointsList.json";

    //交易-Dubai目标月价
    public static final String GET_DUBAI_SYMBOL_PRICE = "/oilPriceConvertion/getDubaiSymbolPrice.json";
}
