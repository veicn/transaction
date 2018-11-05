package com.sinochem.crude.trade.portal.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.eyeieye.melody.util.StringUtil;
import com.eyeieye.melody.web.url.URLBroker;
import com.sinochem.crude.trade.portal.constant.ExternalApi;
import com.sinochem.crude.trade.portal.enums.AvailableArrivalMonthDifferenceEnum;
import com.sinochem.crude.trade.portal.enums.AvailableDischargePortEnum;
import com.sinochem.crude.trade.portal.enums.CurrencyEnum;
import com.sinochem.crude.trade.portal.enums.OilTypeEnum;
import com.sinochem.crude.trade.portal.helper.HttpConnectionHelper;
import com.sinochem.crude.trade.portal.model.vo.DictionaryVO;
import com.sinochem.crude.trade.portal.model.vo.ShipPortVO;
import com.sinochem.crude.trade.portal.service.DictionaryService;
import com.sinochem.crude.trade.portal.service.impl.strategy.YearMonthPairStrategy;
import com.sinochem.it.b2b.common.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ContextLoader;

import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class DictionaryServceImpl implements DictionaryService {

    private HashMap<String, DictionaryVO> availableArrivalMonthDifferenceMap;
    private HashMap<String, ShipPortVO> availableDischargePortMap;
    private HashMap<String, DictionaryVO> currencyMap;
    private HashMap<String, DictionaryVO> oilTypeMap;

    @Autowired
    private YearMonthPairStrategy yearMonthPairStrategy;

    @Override
    public Map<String, DictionaryVO> availableArrivalMonthMap() throws Exception {
        if (this.availableArrivalMonthDifferenceMap != null) {
            return (HashMap<String, DictionaryVO>) this.availableArrivalMonthDifferenceMap.clone();
        }

        HashMap<String, DictionaryVO> availableArrivalMonthDifferenceMap = new HashMap<>();
        Calendar today = Calendar.getInstance();

        for (AvailableArrivalMonthDifferenceEnum availableArrivalMonthDifferenceEnum : AvailableArrivalMonthDifferenceEnum.values()) {
            Integer availableArrivalMonthDifference = availableArrivalMonthDifferenceEnum.getMonthDifference();
            Calendar arrivalCalendar = (Calendar) today.clone();
            arrivalCalendar.add(Calendar.MONTH, availableArrivalMonthDifference);
            String yearMonthPair = yearMonthPairStrategy.convertCalendarToYearMonthPair(arrivalCalendar);

            DictionaryVO dictionaryVO = new DictionaryVO(
                    yearMonthPair, yearMonthPair, yearMonthPair
            );

            availableArrivalMonthDifferenceMap.put(dictionaryVO.getCode(), dictionaryVO);
        }

        if (this.availableArrivalMonthDifferenceMap == null) {
            this.availableArrivalMonthDifferenceMap = availableArrivalMonthDifferenceMap;
        }

        return (Map<String, DictionaryVO>) this.availableArrivalMonthDifferenceMap.clone();
    }

    @Override
    public Map<String, ShipPortVO> availableDischargePortMap() throws Exception {
        if (this.availableDischargePortMap != null) {
            return (HashMap<String, ShipPortVO>) this.availableDischargePortMap.clone();
        }

        HttpConnectionHelper httpConnectionHelper = ContextLoader.getCurrentWebApplicationContext().getBean(HttpConnectionHelper.class);
        URLBroker shipServer = (URLBroker) ContextLoader.getCurrentWebApplicationContext().getBean("shipServer");

        String url = shipServer.get(ExternalApi.QUERY_VALUE_SET).toString();
        JSONObject requestJson = new JSONObject();
        requestJson.put("valueSetType", "4");

        JSONObject responseJson = httpConnectionHelper.httpPost(requestJson, url);

        Integer status = responseJson.getInteger("status");
        if (status == null || Result.ERROR == status.intValue()) {
            throw new RuntimeException("响应失败");
        }

        JSONArray dataJsonArray = responseJson.getJSONArray("datas");
        if (dataJsonArray == null) {
            throw new RuntimeException("数据获取失败");
        }

        HashMap<String, ShipPortVO> shipPortVOMap = new HashMap<>();
        for (int i = 0; i < dataJsonArray.size(); i++) {
            JSONObject dataJson = dataJsonArray.getJSONObject(i);
            String shipPortCode = dataJson.getString("code");

            if (!StringUtil.isEmpty(shipPortCode) && AvailableDischargePortEnum.containsCode(shipPortCode)) {
                ShipPortVO shipPortVO = new ShipPortVO();
                shipPortVO.setCode(dataJson.getString("code"));
                shipPortVO.setSubGroup(dataJson.getString("subGroup"));
                shipPortVO.setValue(dataJson.getString("value"));

                shipPortVOMap.put(shipPortVO.getCode(), shipPortVO);
            }
        }

        this.availableDischargePortMap = shipPortVOMap;
        return (HashMap<String, ShipPortVO>) this.availableDischargePortMap.clone();
    }

    @Override
    public Map<String, DictionaryVO> currencyMap() {
        if (this.currencyMap != null) {
            return (HashMap<String, DictionaryVO>) this.currencyMap.clone();
        }

        HashMap<String, DictionaryVO> currencyMap = new LinkedHashMap<>();
        for (CurrencyEnum currencyEnum : CurrencyEnum.values()) {
            DictionaryVO dictionaryVO = new DictionaryVO(
                    currencyEnum.getCode(),
                    currencyEnum.getZhName(),
                    currencyEnum.getEnName()
            );

            currencyMap.put(dictionaryVO.getCode(), dictionaryVO);
        }

        this.currencyMap = currencyMap;
        return (HashMap<String, DictionaryVO>) this.currencyMap.clone();
    }

    @Override
    public Map<String, DictionaryVO> oilTypeMap() {
        if (this.oilTypeMap != null) {
            return (HashMap<String, DictionaryVO>) this.oilTypeMap.clone();
        }

        HashMap<String, DictionaryVO> oilTypeMap = new LinkedHashMap<>();
        for (OilTypeEnum oilTypeEnum : OilTypeEnum.values()) {
            DictionaryVO dictionaryVO = new DictionaryVO(
                    oilTypeEnum.getCode(),
                    oilTypeEnum.getZhName(),
                    oilTypeEnum.getEnName()
            );

            oilTypeMap.put(dictionaryVO.getCode(), dictionaryVO);
        }

        this.oilTypeMap = oilTypeMap;
        return (HashMap<String, DictionaryVO>) this.oilTypeMap.clone();
    }
}
