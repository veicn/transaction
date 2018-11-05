package com.sinochem.crude.trade.portal.service;

import com.sinochem.crude.trade.portal.model.vo.DictionaryVO;
import com.sinochem.crude.trade.portal.model.vo.ShipPortVO;

import java.util.Map;

/**
 * 字典Service
 * @author Yichen Zhao
 * date: 20180416
 */
public interface DictionaryService {

    Map<String, DictionaryVO> availableArrivalMonthMap() throws Exception;

    Map<String, ShipPortVO> availableDischargePortMap() throws Exception;

    Map<String, DictionaryVO> currencyMap();

    Map<String, DictionaryVO> oilTypeMap();
}
