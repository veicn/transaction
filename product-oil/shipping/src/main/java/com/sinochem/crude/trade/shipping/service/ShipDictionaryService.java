package com.sinochem.crude.trade.shipping.service;

import java.util.HashMap;

import com.sinochem.crude.trade.common.model.vo.DictionaryVO;

/**
 * 字典的Service，一期先使用Enum，之后可使用值集系统
 * @author DimitriZhao
 * date: 20180301
 */
public interface ShipDictionaryService {

    HashMap<String, DictionaryVO> shipAgreementStatusMap();
    DictionaryVO getShipAgreementStatus(String code);

    HashMap<String, DictionaryVO> shipConfirmStatusMap();
    DictionaryVO getShipConfirmStatus(String code);
    
    HashMap<String, DictionaryVO> shipDemanteStatusMap();
    DictionaryVO getShipDemanteStatusStatus(String code);
    //商品
    HashMap<String, DictionaryVO> productStatusMap();
    DictionaryVO getproductStatusStatus(String code);
    
    HashMap<String, DictionaryVO> LoadPortSeaStatusMap();
    DictionaryVO getLoadPortSeaStatus(String code);

    HashMap<String,DictionaryVO>PortStatusMap();
    DictionaryVO getPortStatus(String code);
    
    HashMap<String,DictionaryVO> PricingMethodMap();
    DictionaryVO getPricingMethod(String code);
    
    HashMap<String,DictionaryVO>AgreeMentMap();
    DictionaryVO getAgreeMent(String code);
    
    HashMap<String,DictionaryVO>DatebuiltMap();
    DictionaryVO getDatebuilt(String code);
    
    HashMap<String,DictionaryVO> RevenueTonMap();
    DictionaryVO getRevenueTon(String code);
    
    HashMap<String,DictionaryVO> ShippingAgentMap();
    DictionaryVO getShippingAgent(String code);
    
    HashMap<String,DictionaryVO> ConfirmOnlineMap();
    DictionaryVO getConfirmOnline(String code);
    
    HashMap<String,DictionaryVO> VesselSizeMap();
    DictionaryVO getVesselSize(String code);

    HashMap<String,DictionaryVO> UnLoadPortMap();
    DictionaryVO getnLoadPort(String code);

    HashMap<String,DictionaryVO> ConfirmIndependentMap();
    DictionaryVO getConfirmIndependentPort(String code);
}
