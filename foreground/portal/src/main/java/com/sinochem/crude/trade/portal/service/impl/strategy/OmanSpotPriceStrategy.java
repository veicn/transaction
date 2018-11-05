package com.sinochem.crude.trade.portal.service.impl.strategy;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.eyeieye.melody.web.url.URLBroker;
import com.sinochem.crude.trade.portal.constant.ExternalApi;
import com.sinochem.crude.trade.portal.helper.HttpConnectionHelper;
import com.sinochem.crude.trade.portal.helper.SymbolModelHelper;
import com.sinochem.crude.trade.portal.model.vo.SymbolPriceResponseVO;
import com.sinochem.crude.trade.portal.model.vo.SymbolResponseVO;
import com.sinochem.crude.trade.portal.service.impl.strategy.base.AbstractSpotPriceStrategy;
import com.sinochem.it.b2b.common.result.Result;
import com.sinochem.it.b2b.common.result.ResultDatas;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

@Component
public class OmanSpotPriceStrategy extends AbstractSpotPriceStrategy {

    private final String WS_POINT_KEY = "price";

    private final String MIDDLE_EAST_DEAL_POINTS_ID = "中东";
    private final String DEAL_POINTS_KEY = "region";

    private final String COMMODITY = "Oman";
    private final String PRICE_SOURCE = "EXCHANGE";
    private final String MARKET = "DME";

    @Autowired
    private HttpConnectionHelper httpConnectionHelper;

    @Autowired
    private URLBroker shipServer;

    @Autowired
    private URLBroker listedServer;

    @Autowired
    private SymbolModelHelper symbolModelHelper;

    @Autowired
    private YearMonthPairStrategy yearMonthPairStrategy;

    private final Logger LOGGER = LoggerFactory.getLogger(OmanSpotPriceStrategy.class);

    @Override
    public BigDecimal getFOBSpotCost(Calendar arrivalCalendar) {
        try {
            if (arrivalCalendar == null) {
                throw new RuntimeException("参数为空");
            }

            Calendar today = Calendar.getInstance();

            if (arrivalCalendar.get(Calendar.MONTH) - today.get(Calendar.MONTH) == 4) {
                Calendar monthPlus2 = (Calendar) today.clone();
                monthPlus2.add(Calendar.MONTH, 2);

                List<SymbolResponseVO> symbolResponseVOList = getAllSymbolList();

                SymbolResponseVO targetSymbol = getTargetSymbol(symbolResponseVOList, monthPlus2);
                if (targetSymbol == null) {
                    throw new RuntimeException("获取Oman合约信息失败");
                }

                SymbolPriceResponseVO symbolPriceResponseVO = symbolModelHelper.requestSymbolPriceVO(
                        targetSymbol.getSymbol(), targetSymbol.getSymbolName()
                );

                if (symbolPriceResponseVO == null) {
                    throw new RuntimeException("获取Oman价格信息失败");
                }

                return getPremiumDelivery().add(
                        symbolPriceResponseVO.getSettlementPrice()
                );
            } else if (arrivalCalendar.get(Calendar.MONTH) - today.get(Calendar.MONTH) == 3) {
                Calendar monthPlus1 = (Calendar) today.clone();
                monthPlus1.add(Calendar.MONTH, 1);

                return getPremiumDelivery().add(
                        getDubaiSymbolPrice(monthPlus1)
                );
            } else {
                throw new RuntimeException("参数错误");
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    @Override
    public BigDecimal getAbroadTransportationFee(Calendar arrivalCalendar, String portCode, BigDecimal wsPoint) {

        try {
            BigDecimal flatRate = getFlatRateMap().get(portCode);
            if (flatRate == null) {
                throw new RuntimeException("获取flat rate失败");
            }

            BigDecimal sundryCharge = getSundryChargeMap().get(portCode);
            if (sundryCharge == null) {
                throw new RuntimeException("获取杂费失败");
            }

            if (wsPoint == null) {
                wsPoint = getWsPoint();
            }

            return wsPoint
                    .multiply(flatRate)
                    .multiply(BigDecimal.valueOf(2700))
                    .add(sundryCharge)
                    .divide(BigDecimal.valueOf(1950000), 3, BigDecimal.ROUND_HALF_UP);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    @Override
    public BigDecimal getPremiumDelivery() {
        return BigDecimal.valueOf(0);
    }

    @Override
    public List<SymbolResponseVO> getAllSymbolList() throws Exception {
        return symbolModelHelper.requestSymbolResponseVO(
                COMMODITY, PRICE_SOURCE, MARKET
        );
    }

    @Override
    public BigDecimal getWsPoint() throws Exception {

        JSONObject responseJson = httpConnectionHelper.httpPost(null, shipServer.get(ExternalApi.GET_TODAY_DEAL_POINTS).toString());

        Integer status = responseJson.getInteger("status");
        if (status == null || Result.ERROR == status.intValue()) {
            throw new RuntimeException("响应失败");
        }

        JSONArray dataJsonArray = responseJson.getJSONArray("datas");
        if (dataJsonArray == null) {
            throw new RuntimeException("数据获取失败");
        }

        BigDecimal wsPoint = null;
        for (int i = 0; i < dataJsonArray.size(); i++) {
            JSONObject dataJson = dataJsonArray.getJSONObject(i);

            if (MIDDLE_EAST_DEAL_POINTS_ID.equals(dataJson.getString(DEAL_POINTS_KEY))) {
                wsPoint = dataJson.getBigDecimal(WS_POINT_KEY);
                break;
            }
        }

        if (wsPoint == null) {
            throw new RuntimeException("WS点数获取失败");
        }

        return wsPoint;
    }

    private SymbolResponseVO getTargetSymbol(List<SymbolResponseVO> symbolResponseVOList, Calendar targetCalendar) throws Exception {
        if (symbolResponseVOList == null || targetCalendar == null) {
            return null;
        }

        for (SymbolResponseVO symbolResponseVO : symbolResponseVOList) {
            String symbolName = symbolResponseVO.getSymbolName();

            String suffix = yearMonthPairStrategy.convertCalendarToYearMonthPair(targetCalendar);
            if (symbolName.endsWith(suffix)) {
                return symbolResponseVO;
            }
        }

        return null;
    }

    private BigDecimal getDubaiSymbolPrice(Calendar targetCalendar) throws Exception {
        String targetYearMonthPair = yearMonthPairStrategy.convertCalendarToYearMonthPair(targetCalendar);

        String paramString = "targetYearMonthPair=" + targetYearMonthPair;
        String url = listedServer.get(ExternalApi.GET_DUBAI_SYMBOL_PRICE).toString();

        JSONObject responseJson = httpConnectionHelper.httpGet(paramString, url);
        Integer status = responseJson.getInteger("status");

        if (status != null && ResultDatas.SUCCESS == status.intValue()) {
            return responseJson.getBigDecimal("datas");
        }

        throw new Exception("获取Dubai价格失败");
    }
}
