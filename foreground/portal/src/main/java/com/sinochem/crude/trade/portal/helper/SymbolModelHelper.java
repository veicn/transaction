package com.sinochem.crude.trade.portal.helper;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.eyeieye.melody.util.StringUtil;
import com.eyeieye.melody.web.url.URLBroker;
import com.sinochem.crude.trade.portal.config.RedissonConfig;
import com.sinochem.crude.trade.portal.model.vo.*;
import com.sinochem.it.b2b.common.http.HttpConnectionManager;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.redisson.api.RMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.*;

/**
 * SymbolModel的Helper，这个下期要重新设计下
 * @author Yichen Zhao
 * date: 20180415
 */
@Component
public class SymbolModelHelper {

    private final String SUCCESS = "1";

    private final String QUERY_SYMBOL_URL_SUFFIX = "/query/symbol.json";
    private final String NEW_SYMBOL_PRICE_URL_SUFFIX = "/query/newSymbolPrice.json";
    private final String DATA_FEED_URL = "http://211.157.2.89:7779/sinochem/pagei?uid=83000001&sid=jcache-sinochem-data&page=9921.pag";

    private final String SYMBOL_NAME_KEY = "2";
    private final String LATEST_PRICE_KEY = "3";

    @Autowired
    private HttpConnectionHelper httpConnectionHelper;

    @Autowired
    private HttpConnectionManager httpConnectionManager;

    @Autowired
    private URLBroker infoServer;

    @Autowired
    private RedissonConfig redissonConfig;

    public BigDecimal getIneSymbolPriceRealtime(String symbolName) throws Exception {

        if (symbolName == null) {
            throw new RuntimeException("参数为空");
        }

        CloseableHttpResponse httpResponse = null;
        CloseableHttpClient httpClient = httpConnectionManager.getHttpClient();
        HttpGet httpGet = new HttpGet(DATA_FEED_URL);

        try {
            httpResponse = httpClient.execute(httpGet);
            HttpEntity httpEntity = httpResponse.getEntity();

            if (httpEntity == null) {
                throw new RuntimeException("网络异常");
            }

            InputStream in = httpEntity.getContent();
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(in);
            Element rootElement = document.getDocumentElement();

            String idsAsString = rootElement.getElementsByTagName("ids").item(0).getTextContent();
            String[] ids = idsAsString.split(",");

            RMap feedIdMap = redissonConfig.redissonClient().getMap("_feed_id_map");
            String suffix = symbolName.substring(symbolName.length() - 4);

            for (String id : ids) {
                HashMap symbol = (HashMap) feedIdMap.get(id);

                if (symbol.get(SYMBOL_NAME_KEY) != null && symbol.get(SYMBOL_NAME_KEY).toString().endsWith(suffix)) {
                    return new BigDecimal(symbol.get(LATEST_PRICE_KEY).toString());
                }
            }

            return null;
        } catch (Exception e) {
            throw e;
        } finally {
            if (httpResponse != null) {
                httpResponse.close();
            }
        }
    }

    public List<SymbolResponseVO> getINESymbolLost() throws Exception {
        final String COMMODITY = "SC";
        final String PRICE_SOURCE = "EXCHANGE";
        final String MARKET = "SHFE";

        List<SymbolResponseVO> symbolResponseVOList = requestSymbolResponseVO(COMMODITY, PRICE_SOURCE, MARKET);
        int size = symbolResponseVOList.size();
        if (size > 3) {
            symbolResponseVOList.remove(size - 1);
            symbolResponseVOList.remove(size - 2);
            symbolResponseVOList.remove(size - 3);
        }

        return symbolResponseVOList;
    }

    public List<SymbolResponseVO> requestSymbolResponseVO(
            String commodity, String priceSource, String market) throws Exception {
        SymbolQueryVO symbolQueryVO = new SymbolQueryVO();
        symbolQueryVO.setCommodity(commodity);
        symbolQueryVO.setPriceSource(priceSource);
        symbolQueryVO.setMarket(market);

        JSONObject symbolResultJson = httpConnectionHelper.httpPost(
                JSONObject.parseObject(symbolQueryVO.toJSONString()),
                infoServer.get(QUERY_SYMBOL_URL_SUFFIX).toString()
        );
        InfoResponseVO<List<SymbolResponseVO>> infoResponseVO =
                JSONObject.parseObject(symbolResultJson.toJSONString(), new TypeReference<InfoResponseVO<List<SymbolResponseVO>>>(){ });

        if (infoResponseVO == null || !SUCCESS.equals(infoResponseVO.getStatus())) {
            throw new Exception("网络异常");
        }

        List<SymbolResponseVO> symbolResponseVOList = infoResponseVO.getDataList();
        if (symbolResponseVOList == null || symbolResponseVOList.size() == 0) {
            throw new Exception("没有数据");
        }

        return symbolResponseVOList;
    }

    public SymbolPriceResponseVO requestSymbolPriceVO(
            String symbol, String symbolName) throws Exception {
        SymbolPriceQueryVO symbolPriceQueryVO = new SymbolPriceQueryVO();
        symbolPriceQueryVO.setSymbol(symbol);
        symbolPriceQueryVO.setSymbolName(symbolName);

        JSONObject symbolPriceResultJson = httpConnectionHelper.httpPost(
                JSONObject.parseObject(symbolPriceQueryVO.toJSONString()),
                infoServer.get(NEW_SYMBOL_PRICE_URL_SUFFIX).toString()
        );
        InfoResponseVO<SymbolPriceResponseVO> infoResponseVO =
                JSONObject.parseObject(symbolPriceResultJson.toJSONString(), new TypeReference<InfoResponseVO<SymbolPriceResponseVO>>() {});

        if (infoResponseVO == null || !SUCCESS.equals(infoResponseVO.getStatus())) {
            throw new Exception("网络异常");
        }

        SymbolPriceResponseVO symbolPriceResponseVO = infoResponseVO.getDataList();
        if (symbolPriceResponseVO == null) {
            throw new Exception("没有数据");
        }

        return symbolPriceResponseVO;
    }
}
