package com.sinochem.crude.trade.shiprefueling.service;


import com.sinochem.crude.trade.shiprefueling.controller.common.DictionaryVO;

import java.util.HashMap;

/**
 * 字典的Service，一期先使用Enum，之后可使用值集系统
 * @author DimitriZhao
 * date: 20180301
 */
public interface BuyShipDictionaryService {

    HashMap<String, DictionaryVO> deliveryModeStatusMap();
    DictionaryVO getDeliveryModeStatus(String code);

    HashMap<String, DictionaryVO> oilTypeEnumsMap();
    DictionaryVO getOilTypeEnums(String code);

    HashMap<String, DictionaryVO> fuelOilEnumsMap();
    DictionaryVO getFuelOilEnums(String code);

    HashMap<String, DictionaryVO> dieselOilEnumsMap();
    DictionaryVO getDieselOilEnums(String code);

    HashMap<String, DictionaryVO> engineOilEnumsMap();
    DictionaryVO geEngineOilEnums(String code);

    HashMap<String, DictionaryVO> typeOfShippingEnumsMap();
    DictionaryVO getypeOfShippingEnumsEnums(String code);


}
