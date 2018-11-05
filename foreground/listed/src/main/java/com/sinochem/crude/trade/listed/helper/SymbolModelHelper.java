package com.sinochem.crude.trade.listed.helper;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.eyeieye.melody.web.url.URLBroker;
import com.sinochem.crude.trade.listed.enums.SymbolEnum;
import com.sinochem.crude.trade.listed.enums.SymbolInfoCombineEnum;
import com.sinochem.crude.trade.listed.model.design.base.*;
import com.sinochem.crude.trade.listed.model.vo.*;
import com.sinochem.crude.trade.listed.service.impl.factory.SymbolKeyModelFactory;
import com.sinochem.crude.trade.listed.service.impl.factory.SymbolModelFactory;
import com.sinochem.crude.trade.listed.service.impl.factory.SymbolQueryFactory;
import com.sinochem.it.b2b.common.http.ConnectionManager;
import com.sinochem.it.b2b.common.http.HttpConnectionManager;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.CharsetUtils;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoader;

import java.util.*;

/**
 * 获取SymbolModel的helper，该类线程安全
 * @author Yichen Zhao
 * date: 20180204
 */
public class SymbolModelHelper {

    private static final String INFO_SERVER_NAME = "infoServer"; //这个bean是byName的

    private static final int SYMBOL_COUNT = 36; //合约总数

    private static final String QUERY_SYMBOL_URL_SUFFIX = "/query/symbol.json";
    private static final String NEW_SYMBOL_PRICE_URL_SUFFIX = "/query/newSymbolPrice.json";

    private static final String SUCCESS = "1";

    private static Map<SymbolKeyModel, SymbolModel> symbolModelMap = new HashMap<>();
    private static Map<String, TreeSet<Calendar>> oilSymbolDateMap = new HashMap<>();

    private static Calendar calendar = Calendar.getInstance();

    /**
     * 根据给定的待转油种和原油油种，找到对应的资讯合约，针对需要转换的油种
     */
    public static SymbolModel getSymbolModel(OilModel origin, OilModel target) throws Exception {
        /* 判断是否有初始化和是否过期，并相应进行处理 */
        checkDeprecated();

        if (origin.getOilType().equals(target.getOilType())) {
            SymbolKeyModel symbolKeyModel = SymbolKeyModelFactory.getSymbolKeyModel(
                    origin.getOilType(), origin.getCalendar(), target.getCalendar()
            );
            return SymbolModelFactory.cloneSymbolModel(symbolModelMap.get(symbolKeyModel));
        } else {
            SymbolKeyModel symbolKeyModel = SymbolKeyModelFactory.getSymbolKeyModel(
                    origin.getOilType(), target.getOilType(), origin.getCalendar()
            );
            return SymbolModelFactory.cloneSymbolModel(symbolModelMap.get(symbolKeyModel));
        }
    }

    /**
     * 根据给定的待转油种和原油油种，找到对应的资讯合约，针对ICE和WTI
     */
    public static SymbolModel getSymbolModel(OilModel oilModel) throws Exception {
        /* 判断是否有初始化和是否过期，并相应进行处理 */
        checkDeprecated();

        SymbolKeyModel symbolKeyModel = SymbolKeyModelFactory.getSymbolKeyModel(
                oilModel.getOilType(),
                oilModel.getCalendar()
        );

        return SymbolModelFactory.cloneSymbolModel(symbolModelMap.get(symbolKeyModel));
    }

    public static List<Calendar> getSymbolDateListByOilType(String oilType) throws Exception {
        /* 判断是否有初始化和是否过期，并相应进行处理 */
        checkDeprecated();

        if (!oilSymbolDateMap.containsKey(oilType)) {
            return null;
        }

        TreeSet<Calendar> calendarSet = oilSymbolDateMap.get(oilType);
        List<Calendar> calendarList = new ArrayList<>();
        for (Calendar calendar : calendarSet) {
            calendarList.add((Calendar) calendar.clone());
        }

        return calendarList;
    }

    private static void initializeSymbolMap() throws Exception {
        initializeSymbol(SymbolInfoCombineEnum.DTD_BRENT_SPREAD);
        initializeSymbol(SymbolInfoCombineEnum.DUBAI_SPREAD);
        initializeSymbol(SymbolInfoCombineEnum.EFS);
        initializeSymbol(SymbolInfoCombineEnum.DTD_BRENT_DUBAI);
        initializeSymbol(SymbolInfoCombineEnum.BRENT);
        initializeSymbol(SymbolInfoCombineEnum.WTI);
    }

    private static void initializeSymbol(
            SymbolInfoCombineEnum combine) throws Exception {
        List<SymbolResponseVO> symbolResponseVOList = requestSymbolResponseVO(
                SymbolQueryFactory.genSymbolQueryVO(combine)
        );

        for (int i = 0; i < symbolResponseVOList.size(); i++) {
            SymbolEnum symbolEnum = SymbolEnum.getSymbolEnumByCombineAndOrder(combine, i + 1);
            if (symbolEnum == null) {
                throw new Exception("数据返回错误");
            }

            SymbolResponseVO symbolResponseVO = symbolResponseVOList.get(i);
            SymbolPriceResponseVO symbolPriceResponseVO = requestSymbolPriceVO(
                    symbolResponseVO.getSymbol(),
                    symbolResponseVO.getSymbolName()
            );

            SymbolModel symbolModel = SymbolModelFactory.getSymbolModel(
                    combine,
                    symbolResponseVO.getSymbolName(),
                    symbolResponseVO.getSymbol(),
                    symbolPriceResponseVO.getSettlementPrice(),
                    i + 1
            );

            if (symbolModel == null) { //symbolModel无法生成时，说明数据有误，废弃之
                continue;
            }

            SymbolKeyModel symbolKeyModel = SymbolKeyModelFactory.getSymbolKeyModel(symbolModel);
            symbolModelMap.put(symbolKeyModel, symbolModel);

            String oilType = SymbolModelFactory.getCorrespondingOilType(combine);
            Set<Calendar> calendars = SymbolModelFactory.getCalendarByCombineAndSymbolName(
                    combine, symbolModel.getSymbolName()
            );
            if (!oilSymbolDateMap.containsKey(oilType)) {
                TreeSet calendarSet = new TreeSet();

                if (calendars != null && calendars.size() != 0){
                    for (Calendar calendar : calendars) {
                        calendarSet.add(calendar);
                    }

                    oilSymbolDateMap.put(oilType, calendarSet);
                }
            } else {
                TreeSet calendarSet = oilSymbolDateMap.get(oilType);

                if (calendars != null) {
                    for (Calendar calendar : calendars) {
                        if (!hasCalendar(calendarSet, calendar)) {
                            calendarSet.add(calendar);
                        }
                    }
                }
            }
        }
    }

    public static List<SymbolResponseVO> requestSymbolResponseVO(
            SymbolQueryVO symbolQueryVO) throws Exception {

        JSONObject symbolResultJson = httpPost(
                JSONObject.parseObject(symbolQueryVO.toJSONString()),
                getInfoServer().get(QUERY_SYMBOL_URL_SUFFIX).toString()
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

    public static SymbolPriceResponseVO requestSymbolPriceVO(
            String symbol, String symbolName) throws Exception {
        SymbolPriceQueryVO symbolPriceQueryVO = new SymbolPriceQueryVO();
        symbolPriceQueryVO.setSymbol(symbol);
        symbolPriceQueryVO.setSymbolName(symbolName);

        JSONObject symbolPriceResultJson = httpPost(
                JSONObject.parseObject(symbolPriceQueryVO.toJSONString()),
                getInfoServer().get(NEW_SYMBOL_PRICE_URL_SUFFIX).toString()
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

    private static void checkDeprecated() throws Exception {
        Calendar today = Calendar.getInstance();
        if (today.get(Calendar.YEAR) != calendar.get(Calendar.YEAR)
                || today.get(Calendar.MONTH) != calendar.get(Calendar.MONTH)
                || today.get(Calendar.DAY_OF_MONTH) != calendar.get(Calendar.DAY_OF_MONTH)) {
            calendar = today;
            initializeSymbolMap();
            return;
        }

        if (SYMBOL_COUNT != symbolModelMap.size()) {
            initializeSymbolMap();
        }
    }

    private static JSONObject httpPost(JSONObject paramJson, String url) throws Exception {
        CloseableHttpResponse httpResponse = null;
        CloseableHttpClient closeableHttpClient = getConnectionManager().getHttpClient();
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("Content-type","application/json; charset=utf-8");
        httpPost.setEntity(new StringEntity(paramJson.toJSONString(), CharsetUtils.get("UTF-8")));

        try {
            httpResponse = closeableHttpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();

            if (httpEntity == null) {
                throw new Exception("网络异常");
            }

            String responseString = EntityUtils.toString(httpEntity, "UTF-8");
            EntityUtils.consume(httpEntity);

            return JSONObject.parseObject(responseString);
        } catch (Exception e) {
            throw e;
        } finally {
            if (httpResponse != null) {
                httpResponse.close();
            }
        }
    }

    private static boolean hasCalendar(Set<Calendar> calendarSet, Calendar calendar) {
        for (Calendar temp : calendarSet) {
            if (temp.get(Calendar.YEAR) == calendar.get(Calendar.YEAR)
                    && temp.get(Calendar.MONTH) == calendar.get(Calendar.MONTH)) {
                return true;
            }
        }

        return false;
    }

    /** getters */
    public static HttpConnectionManager getConnectionManager() {
        return ContextLoader.getCurrentWebApplicationContext().getBean(HttpConnectionManager.class);
    }

    public static URLBroker getInfoServer() {
        return (URLBroker) ContextLoader.getCurrentWebApplicationContext().getBean(INFO_SERVER_NAME);
    }
}
