package com.sinochem.crude.trade.shiprefueling.service.impl;

import com.eyeieye.melody.util.StringUtil;
import com.sinochem.crude.trade.shiprefueling.controller.common.DictionaryVO;
import com.sinochem.crude.trade.shiprefueling.enums.*;
import com.sinochem.crude.trade.shiprefueling.service.BuyShipDictionaryService;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * DictionaryService的实现，后期若做字典可更换实现类
 * 该实现类线程安全
 * @author YuLong Zhao
 * date: 20180308
 */
@Service
public class ShipDictionaryServiceImpl implements BuyShipDictionaryService {

	private HashMap<String, DictionaryVO> deliveryModeStatusMap;
	private HashMap<String, DictionaryVO> oilTypeEnumsMap;
	private HashMap<String, DictionaryVO> fuelOilEnumsMap;
	private HashMap<String, DictionaryVO> engineOilEnumsMap;
	private HashMap<String, DictionaryVO> dieselOilEnumsMap;
	private HashMap<String, DictionaryVO> typeOfShippingEnumsMap;

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, DictionaryVO> deliveryModeStatusMap() {
		HashMap<String, DictionaryVO> rlsMap = new HashMap<>();
		if (this.deliveryModeStatusMap != null) {
			rlsMap = (HashMap<String, DictionaryVO>) this.deliveryModeStatusMap.clone();
			return rlsMap;
		}

		for (TypeOfShippingEnums agreementStatusEnums : TypeOfShippingEnums.values()) {
			DictionaryVO dictionaryVO = new DictionaryVO(
					agreementStatusEnums.getCode(),
					agreementStatusEnums.getZhName(),
					agreementStatusEnums.getEnName()
			);

			rlsMap.put(dictionaryVO.getCode(), dictionaryVO);
		}

		this.deliveryModeStatusMap = rlsMap;
		return rlsMap;
	}

	@Override
	public DictionaryVO getDeliveryModeStatus(String code) {
		if (StringUtil.isEmpty(code)) {
			return null;
		}

		return deliveryModeStatusMap().get(code);
	}


	@Override
	public HashMap<String, DictionaryVO> oilTypeEnumsMap() {
		HashMap<String, DictionaryVO> rlsMap = new HashMap<>();
		if (this.oilTypeEnumsMap != null) {
			rlsMap = (HashMap<String, DictionaryVO>) this.oilTypeEnumsMap.clone();
			return rlsMap;
		}

		for (OilTypeEnums agreementStatusEnums : OilTypeEnums.values()) {
			DictionaryVO dictionaryVO = new DictionaryVO(
					agreementStatusEnums.getCode(),
					agreementStatusEnums.getZhName(),
					agreementStatusEnums.getEnName()
			);

			rlsMap.put(dictionaryVO.getCode(), dictionaryVO);
		}

		this.oilTypeEnumsMap = rlsMap;
		return rlsMap;
	}

	@Override
	public DictionaryVO getOilTypeEnums(String code) {
		if (StringUtil.isEmpty(code)) {
			return null;
		}

		return oilTypeEnumsMap().get(code);
	}

	@Override
	public HashMap<String, DictionaryVO> fuelOilEnumsMap() {
		HashMap<String, DictionaryVO> rlsMap = new HashMap<>();
		if (this.fuelOilEnumsMap != null) {
			rlsMap = (HashMap<String, DictionaryVO>) this.fuelOilEnumsMap.clone();
			return rlsMap;
		}

		for (FuelOilEnums agreementStatusEnums : FuelOilEnums.values()) {
			DictionaryVO dictionaryVO = new DictionaryVO(
					agreementStatusEnums.getCode(),
					agreementStatusEnums.getZhName(),
					agreementStatusEnums.getEnName()
			);

			rlsMap.put(dictionaryVO.getCode(), dictionaryVO);
		}

		this.fuelOilEnumsMap = rlsMap;
		return rlsMap;
	}

	@Override
	public DictionaryVO getFuelOilEnums(String code) {
		if (StringUtil.isEmpty(code)) {
			return null;
		}

		return fuelOilEnumsMap().get(code);
	}

	@Override
	public HashMap<String, DictionaryVO> dieselOilEnumsMap() {
		HashMap<String, DictionaryVO> rlsMap = new HashMap<>();
		if (this.dieselOilEnumsMap != null) {
			rlsMap = (HashMap<String, DictionaryVO>) this.dieselOilEnumsMap.clone();
			return rlsMap;
		}

		for (DieselOilEnums agreementStatusEnums : DieselOilEnums.values()) {
			DictionaryVO dictionaryVO = new DictionaryVO(
					agreementStatusEnums.getCode(),
					agreementStatusEnums.getZhName(),
					agreementStatusEnums.getEnName()
			);

			rlsMap.put(dictionaryVO.getCode(), dictionaryVO);
		}

		this.dieselOilEnumsMap = rlsMap;
		return rlsMap;
	}

	@Override
	public DictionaryVO getDieselOilEnums(String code) {
		if (StringUtil.isEmpty(code)) {
			return null;
		}

		return dieselOilEnumsMap().get(code);
	}

	@Override
	public HashMap<String, DictionaryVO> engineOilEnumsMap() {
		HashMap<String, DictionaryVO> rlsMap = new HashMap<>();
		if (this.engineOilEnumsMap != null) {
			rlsMap = (HashMap<String, DictionaryVO>) this.engineOilEnumsMap.clone();
			return rlsMap;
		}

		for (EngineOilEnums agreementStatusEnums : EngineOilEnums.values()) {
			DictionaryVO dictionaryVO = new DictionaryVO(
					agreementStatusEnums.getCode(),
					agreementStatusEnums.getZhName(),
					agreementStatusEnums.getEnName()
			);

			rlsMap.put(dictionaryVO.getCode(), dictionaryVO);
		}

		this.engineOilEnumsMap = rlsMap;
		return rlsMap;
	}

	@Override
	public DictionaryVO geEngineOilEnums(String code) {
		if (StringUtil.isEmpty(code)) {
			return null;
		}

		return engineOilEnumsMap().get(code);
	}


	@Override
	public HashMap<String, DictionaryVO> typeOfShippingEnumsMap() {
		HashMap<String, DictionaryVO> rlsMap = new HashMap<>();
		if (this.typeOfShippingEnumsMap != null) {
			rlsMap = (HashMap<String, DictionaryVO>) this.typeOfShippingEnumsMap.clone();
			return rlsMap;
		}

		for (TypeOfShippingEnums agreementStatusEnums : TypeOfShippingEnums.values()) {
			DictionaryVO dictionaryVO = new DictionaryVO(
					agreementStatusEnums.getCode(),
					agreementStatusEnums.getZhName(),
					agreementStatusEnums.getEnName()
			);

			rlsMap.put(dictionaryVO.getCode(), dictionaryVO);
		}

		this.typeOfShippingEnumsMap = rlsMap;
		return rlsMap;
	}

	@Override
	public DictionaryVO getypeOfShippingEnumsEnums(String code) {
		if (StringUtil.isEmpty(code)) {
			return null;
		}
		return typeOfShippingEnumsMap().get(code);
	}
}
