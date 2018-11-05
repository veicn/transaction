package com.sinochem.crude.trade.portal.helper;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.eyeieye.melody.web.url.URLBroker;
import com.sinochem.crude.trade.portal.constant.ExternalApi;
import com.sinochem.crude.trade.portal.enums.CurrencyEnum;
import com.sinochem.it.b2b.common.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoader;

import java.math.BigDecimal;

/**
 * 用于处理汇率的Helper
 * @author Yichen Zhao
 * date: 20180416
 */
@Component
public class ExchangeRateHelper {

    /* 汇率请求常量 */
    private final String INDEX_CODE = "indexCode";
    private final String EXCHANGE_RATE_MARK = "Dollar";
    private final String EXCHANGE_RATE_KEY = "smeiValue";

    @Autowired
    private URLBroker infoServer;

    @Autowired
    private HttpConnectionHelper httpConnectionHelper;

    /**
     *  获取汇率
     */
    public BigDecimal getExchangeRate(String currencyCode) throws Exception {

        if (CurrencyEnum.CNY.getCode().equals(currencyCode)) {
            return BigDecimal.ONE;
        }

        if (CurrencyEnum.USD.getCode().equals(currencyCode)) {
            JSONObject responseJson = httpConnectionHelper.httpGet(null, infoServer.get(ExternalApi.INDEC_PRICE_T).toString());

            Integer status = responseJson.getInteger("status");
            if (status == null || Result.ERROR == status.intValue()) {
                throw new RuntimeException("响应失败");
            }

            JSONArray dataJsonArray = responseJson.getJSONArray("datas");
            if (dataJsonArray == null) {
                throw new RuntimeException("数据获取失败");
            }

            for (int i = 0; i < dataJsonArray.size(); i++) {
                JSONObject dataJson = dataJsonArray.getJSONObject(i);

                if (EXCHANGE_RATE_MARK.equals(dataJson.getString(INDEX_CODE))) {
                    return dataJson.getBigDecimal(EXCHANGE_RATE_KEY);
                }
            }

            return null;
        }

        return null;
    }
}
