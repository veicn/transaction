package com.sinochem.crude.trade.shipping.service.impl;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.eyeieye.melody.util.StringUtil;
import com.sinochem.crude.trade.common.model.vo.DictionaryVO;
import com.sinochem.crude.trade.shipping.enums.AgreementEnums;
import com.sinochem.crude.trade.shipping.enums.AgreementStatusEnums;
import com.sinochem.crude.trade.shipping.enums.ConfirmOnlineEnums;
import com.sinochem.crude.trade.shipping.enums.ConfirmStatusEnums;
import com.sinochem.crude.trade.shipping.enums.DatebuiltEnums;
import com.sinochem.crude.trade.shipping.enums.DemanteStatusEnums;
import com.sinochem.crude.trade.shipping.enums.LoadPortSeaEnums;
import com.sinochem.crude.trade.shipping.enums.PortEnums;
import com.sinochem.crude.trade.shipping.enums.PortOfDischargIngEnums;
import com.sinochem.crude.trade.shipping.enums.PricingMethodEnums;
import com.sinochem.crude.trade.shipping.enums.ProductStatusEnums;
import com.sinochem.crude.trade.shipping.enums.RevenueTonEnums;
import com.sinochem.crude.trade.shipping.enums.ShippingAgentEnums;
import com.sinochem.crude.trade.shipping.enums.VesselSizeEnums;
import com.sinochem.crude.trade.shipping.enums.IndependentEnum;
import com.sinochem.crude.trade.shipping.service.ShipDictionaryService;

/**
 * DictionaryService的实现，后期若做字典可更换实现类
 * 该实现类线程安全
 * @author YuLong Zhao
 * date: 20180308
 */
@Service
public class ShipDictionaryServiceImpl implements ShipDictionaryService {

    private HashMap<String, DictionaryVO> shipAgreementStatusMap;
    private HashMap<String, DictionaryVO> shipConfirmStatusMap;
    private HashMap<String, DictionaryVO> shipDemanteStatusMap;
    private HashMap<String, DictionaryVO> productStatusMap;
    private HashMap<String, DictionaryVO> loadPortSeaStatusMap;
    private HashMap<String,DictionaryVO> portStatusMap;
    private HashMap<String,DictionaryVO> agreementMap;
    private HashMap<String,DictionaryVO> datebuiltMap;
    private HashMap<String, DictionaryVO> pricingMethodMap;
	private HashMap<String, DictionaryVO> revenueTonMap;
	private HashMap<String, DictionaryVO> shippingAgentMap;
	private HashMap<String, DictionaryVO> confirmOnlineMap;
	private HashMap<String,DictionaryVO> vesselSizeMap;
	private HashMap<String,DictionaryVO> independentsMap;

	private HashMap<String,DictionaryVO> unloadPortMap;
	@SuppressWarnings("unchecked")
	@Override	
	public HashMap<String, DictionaryVO> shipAgreementStatusMap() {
		HashMap<String, DictionaryVO> rlsMap = new HashMap<>();
        if (this.shipAgreementStatusMap != null) {
        	rlsMap = (HashMap<String, DictionaryVO>) this.shipAgreementStatusMap.clone();
            return rlsMap;
        }

        for (AgreementStatusEnums agreementStatusEnums : AgreementStatusEnums.values()) {
            DictionaryVO dictionaryVO = new DictionaryVO(
            		agreementStatusEnums.getCode(),
            		agreementStatusEnums.getZhName(),
            		agreementStatusEnums.getEnName()
            );

            rlsMap.put(dictionaryVO.getCode(), dictionaryVO);
        }

        this.shipAgreementStatusMap = rlsMap;
        return rlsMap;
	}

	@Override
	public DictionaryVO getShipAgreementStatus(String code) {
        if (StringUtil.isEmpty(code)) {
            return null;
        }

        return shipAgreementStatusMap().get(code);
	}

	@Override
	@SuppressWarnings("unchecked")
	public HashMap<String, DictionaryVO> shipConfirmStatusMap() {
		HashMap<String, DictionaryVO> rlsMap = new HashMap<>();
        if (this.shipConfirmStatusMap != null) {
        	rlsMap = (HashMap<String, DictionaryVO>) this.shipConfirmStatusMap.clone();
            return rlsMap;
        }

        for (ConfirmStatusEnums confirmStatusEnums : ConfirmStatusEnums.values()) {
            DictionaryVO dictionaryVO = new DictionaryVO(
            		confirmStatusEnums.getCode(),
            		confirmStatusEnums.getZhName(),
            		confirmStatusEnums.getEnName()
            );

            rlsMap.put(dictionaryVO.getCode(), dictionaryVO);
        }

        this.shipConfirmStatusMap = rlsMap;
        return rlsMap;
	}

	@Override
	public DictionaryVO getShipConfirmStatus(String code) {
        if (StringUtil.isEmpty(code)) {
            return null;
        }

        return shipConfirmStatusMap().get(code);
	}

	@Override
	@SuppressWarnings("unchecked")
	public HashMap<String, DictionaryVO> shipDemanteStatusMap() {
		HashMap<String, DictionaryVO> rlsMap = new HashMap<>();
        if (this.shipDemanteStatusMap != null) {
        	rlsMap = (HashMap<String, DictionaryVO>) this.shipDemanteStatusMap.clone();
            return rlsMap;
        }

        for (DemanteStatusEnums demanteStatusEnums : DemanteStatusEnums.values()) {
            DictionaryVO dictionaryVO = new DictionaryVO(
            		demanteStatusEnums.getCode(),
            		demanteStatusEnums.getZhName(),
            		demanteStatusEnums.getEnName()
            );

            rlsMap.put(dictionaryVO.getCode(), dictionaryVO);
        }

        this.shipDemanteStatusMap = rlsMap;
        return rlsMap;
	}

	@Override
	public DictionaryVO getShipDemanteStatusStatus(String code) {
        if (StringUtil.isEmpty(code)) {
            return null;
        }

        return shipDemanteStatusMap().get(code);
	}
	
	
	@Override
	public HashMap<String, DictionaryVO> productStatusMap() {
		HashMap<String, DictionaryVO> rlsMap = new HashMap<>();
        if (this.productStatusMap != null) {
        	rlsMap = (HashMap<String, DictionaryVO>) this.productStatusMap.clone();
            return rlsMap;
        }

        for (ProductStatusEnums productstatusenums : ProductStatusEnums.values()) {
            DictionaryVO dictionaryVO = new DictionaryVO(
            		productstatusenums.getCode(),
            		productstatusenums.getZhName(),
            		productstatusenums.getEnName()
            );

            rlsMap.put(dictionaryVO.getCode(), dictionaryVO);
        }

        this.productStatusMap = rlsMap;
        return rlsMap;
	}
	
	@Override
	public DictionaryVO getproductStatusStatus(String code) {
		 if (StringUtil.isEmpty(code)) {
	            return null;
	        }
		return productStatusMap.get(code);
	}
	
	@Override
	public HashMap<String, DictionaryVO> LoadPortSeaStatusMap() {
		HashMap<String, DictionaryVO> rlsMap = new HashMap<>();
        if (this.loadPortSeaStatusMap != null) {
        	rlsMap = (HashMap<String, DictionaryVO>) this.loadPortSeaStatusMap.clone();
            return rlsMap;
        }

        for (LoadPortSeaEnums productstatusenums : LoadPortSeaEnums.values()) {
            DictionaryVO dictionaryVO = new DictionaryVO(
            		productstatusenums.getCode(),
            		productstatusenums.getZhName(),
            		productstatusenums.getEnName()
            );

            rlsMap.put(dictionaryVO.getCode(), dictionaryVO);
        }

        this.loadPortSeaStatusMap = rlsMap;
        return rlsMap;
	}
	@Override
	public DictionaryVO getLoadPortSeaStatus(String code) {
		 if (StringUtil.isEmpty(code)) {
	            return null;
	        }
		return loadPortSeaStatusMap.get(code);
	}

	@Override
    public HashMap<String,DictionaryVO> PortStatusMap(){
	    HashMap<String,DictionaryVO> resMap = new HashMap<>();
       if(this.portStatusMap!=null){
           resMap =  (HashMap<String, DictionaryVO>) this.portStatusMap.clone();
       }else{
          for (PortEnums portEnums :PortEnums.values()){
              DictionaryVO dictionaryVO = new DictionaryVO(
                      portEnums.getCode(),
                      portEnums.getZhName(),
                      portEnums.getEnName()
              );
              resMap.put(dictionaryVO.getCode(),dictionaryVO);
           }
           this.portStatusMap = resMap;
       }
       return resMap;
    }
    @Override
    public DictionaryVO getPortStatus(String code){
        if (StringUtil.isEmpty(code)) {
            return null;
        }
        return portStatusMap.get(code);
    }
    
	@Override
    public HashMap<String,DictionaryVO> AgreeMentMap(){
	    HashMap<String,DictionaryVO> resMap = new HashMap<>();
       if(this.agreementMap!=null){
           resMap = (HashMap<String, DictionaryVO>) this.agreementMap.clone();
       }else{
          for (AgreementEnums agreementEnums :AgreementEnums.values()){
              DictionaryVO dictionaryVO = new DictionaryVO(
            		  agreementEnums.getCode(),
            		  agreementEnums.getZhName(),
            		  agreementEnums.getEnName()
              );
              resMap.put(dictionaryVO.getCode(),dictionaryVO);
           }
           this.agreementMap = resMap;
       }
       return resMap;
    }
    @Override
    public DictionaryVO getAgreeMent(String code){
        if (StringUtil.isEmpty(code)) {
            return null;
        }
        return agreementMap.get(code);
    }

	@Override
	public HashMap<String, DictionaryVO> DatebuiltMap() {
		HashMap<String,DictionaryVO> resMap = new HashMap<>();
		if(this.datebuiltMap!=null){
	        resMap = (HashMap<String, DictionaryVO>) this.datebuiltMap.clone();
	    }else{
	    	for (DatebuiltEnums datebuiltEnums :DatebuiltEnums.values()){
	    		DictionaryVO dictionaryVO = new DictionaryVO(
	    				datebuiltEnums.getCode(),
	    				datebuiltEnums.getZhName(),
	    				datebuiltEnums.getEnName()
			);
    		resMap.put(dictionaryVO.getCode(),dictionaryVO);
	    	}
	    	this.datebuiltMap = resMap;
	    }
		return resMap;
	}

	@Override
	public DictionaryVO getDatebuilt(String code) {
        if (StringUtil.isEmpty(code)) {
            return null;
        }
        return datebuiltMap.get(code);
	}
	@Override
	public HashMap<String, DictionaryVO> PricingMethodMap() {
		HashMap<String, DictionaryVO> resMap = new HashMap<>();
		if (this.pricingMethodMap != null) {
			resMap = (HashMap<String, DictionaryVO>) this.pricingMethodMap
					.clone();
		} else {
			for (PricingMethodEnums pricingMethodEnums : PricingMethodEnums
					.values()) {
				DictionaryVO dictionaryVO = new DictionaryVO(
						pricingMethodEnums.getCode(),
						pricingMethodEnums.getZhName(),
						pricingMethodEnums.getEnName());
				resMap.put(dictionaryVO.getCode(), dictionaryVO);
			}
			this.pricingMethodMap = resMap;
		}
		return resMap;
	}

	@Override
	public DictionaryVO getPricingMethod(String code) {
		if (StringUtil.isEmpty(code)) {
			return null;
		}
		return pricingMethodMap.get(code);
	}

	@Override
	public HashMap<String, DictionaryVO> RevenueTonMap() {
		HashMap<String, DictionaryVO> resMap = new HashMap<>();
		if (this.revenueTonMap != null) {
			resMap = (HashMap<String, DictionaryVO>) this.revenueTonMap.clone();
		} else {
			for (RevenueTonEnums revenueTonEnums : RevenueTonEnums.values()) {
				DictionaryVO dictionaryVO = new DictionaryVO(
						revenueTonEnums.getCode(), revenueTonEnums.getZhName(),
						revenueTonEnums.getEnName());
				resMap.put(dictionaryVO.getCode(), dictionaryVO);
			}
			this.revenueTonMap = resMap;
		}
		return resMap;
	}

	@Override
	public DictionaryVO getRevenueTon(String code) {
		if (StringUtil.isEmpty(code)) {
			return null;
		}
		return revenueTonMap.get(code);
	}

	@Override
	public HashMap<String, DictionaryVO> ShippingAgentMap() {
		HashMap<String, DictionaryVO> resMap = new HashMap<>();
		if (this.shippingAgentMap != null) {
			resMap = (HashMap<String, DictionaryVO>) this.shippingAgentMap
					.clone();
		} else {
			for (ShippingAgentEnums shippingAgentEnums : ShippingAgentEnums
					.values()) {
				DictionaryVO dictionaryVO = new DictionaryVO(
						shippingAgentEnums.getCode(),
						shippingAgentEnums.getZhName(),
						shippingAgentEnums.getEnName());
				resMap.put(dictionaryVO.getCode(), dictionaryVO);
			}
			this.shippingAgentMap = resMap;
		}
		return resMap;
	}

	@Override
	public DictionaryVO getShippingAgent(String code) {
		if (StringUtil.isEmpty(code)) {
			return null;
		}
		return shippingAgentMap.get(code);
	}

	@Override
	public HashMap<String, DictionaryVO> ConfirmOnlineMap() {
		HashMap<String, DictionaryVO> resMap = new HashMap<>();
		if (this.confirmOnlineMap != null) {
			resMap = (HashMap<String, DictionaryVO>) this.confirmOnlineMap
					.clone();
		} else {
			for (ConfirmOnlineEnums confirmOnlineEnums : ConfirmOnlineEnums
					.values()) {
				DictionaryVO dictionaryVO = new DictionaryVO(
						confirmOnlineEnums.getCode(),
						confirmOnlineEnums.getZhName(),
						confirmOnlineEnums.getEnName());
				resMap.put(dictionaryVO.getCode(), dictionaryVO);
			}
			this.confirmOnlineMap = resMap;
		}
		return resMap;
	}

	@Override
	public DictionaryVO getConfirmOnline(String code) {
		if (StringUtil.isEmpty(code)) {
			return null;
		}
		return confirmOnlineMap.get(code);
	}
	
	
	@Override
	 public HashMap<String,DictionaryVO> VesselSizeMap(){
			HashMap<String, DictionaryVO> resMap = new HashMap<>();
			if (this.vesselSizeMap != null) {
				resMap = (HashMap<String, DictionaryVO>) this.vesselSizeMap.clone();
			} else {
				for (VesselSizeEnums vesslSizeEnum : VesselSizeEnums.values()) {
					DictionaryVO dictionaryVO = new DictionaryVO(
							vesslSizeEnum.getCode(),
							vesslSizeEnum.getZhName(),
							vesslSizeEnum.getEnName());
					resMap.put(dictionaryVO.getCode(), dictionaryVO);
				}
				this.vesselSizeMap = resMap;
			}
			return resMap;
	 }
	 @Override
	    public DictionaryVO getVesselSize(String code){
	    	
	    	if (StringUtil.isEmpty(code)) {
				return null;
			}
			return vesselSizeMap.get(code);
	    }
	 

		@SuppressWarnings("unchecked")
		@Override
		public HashMap<String, DictionaryVO> UnLoadPortMap() {
			HashMap<String, DictionaryVO> resMap = new HashMap<>();
			if (this.unloadPortMap != null) {
				resMap = (HashMap<String, DictionaryVO>) this.unloadPortMap
						.clone();
			} else {
				for (PortOfDischargIngEnums portOfDischargIngEnums : PortOfDischargIngEnums
						.values()) {
					DictionaryVO dictionaryVO = new DictionaryVO(
							portOfDischargIngEnums.getCode(),
							portOfDischargIngEnums.getPort(),
							portOfDischargIngEnums.getCountry());
					resMap.put(dictionaryVO.getCode(), dictionaryVO);
				}
				this.unloadPortMap = resMap;
			}
			return resMap;
		}

		@Override
		public DictionaryVO getnLoadPort(String code) {
			if (StringUtil.isEmpty(code)) {
				return null;
			}
			return unloadPortMap.get(code);
		}

	@Override
	public HashMap<String, DictionaryVO> ConfirmIndependentMap() {
		HashMap<String, DictionaryVO> resMap = new HashMap<>();
		if (this.independentsMap != null) {
			resMap = (HashMap<String, DictionaryVO>) this.independentsMap.clone();
		} else {
			for (IndependentEnum independentenum : IndependentEnum.values()) {
				DictionaryVO dictionaryVO = new DictionaryVO(
						independentenum.getCode(),
						independentenum.getZhName(),
						independentenum.getEnName());
				resMap.put(dictionaryVO.getCode(), dictionaryVO);
			}
			this.independentsMap = resMap;
		}
		return resMap;
	}

	@Override
	public DictionaryVO getConfirmIndependentPort(String code) {
		if (StringUtil.isEmpty(code)) {
			return null;
		}
		return independentsMap.get(code);
	}
}
