package com.sinochem.crude.trade.order.service.impl;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.eyeieye.melody.util.StringUtil;
import com.sinochem.crude.trade.order.enums.BusinessCheckOrgEnum;
import com.sinochem.crude.trade.order.enums.CreditItemEnum;
import com.sinochem.crude.trade.order.enums.CrudeOilDischargePortEnum;
import com.sinochem.crude.trade.order.enums.CrudeOilOriginEnum;
import com.sinochem.crude.trade.order.enums.EventPricingTypeEnum;
import com.sinochem.crude.trade.order.enums.ExportTypeEnum;
import com.sinochem.crude.trade.order.enums.HLCTypeEnum;
import com.sinochem.crude.trade.order.enums.MeasureModeEnum;
import com.sinochem.crude.trade.order.enums.OldPayItemEnum;
import com.sinochem.crude.trade.order.enums.PayItemEnum;
import com.sinochem.crude.trade.order.enums.PricingMeasureUnitEnum;
import com.sinochem.crude.trade.order.enums.PricingModeEnum;
import com.sinochem.crude.trade.order.enums.ProductOilClassifyEnum;
import com.sinochem.crude.trade.order.enums.ProductOilValuationBaseEnum;
import com.sinochem.crude.trade.order.enums.PurchaseModeEnum;
import com.sinochem.crude.trade.order.enums.RegionProductOilEnum;
import com.sinochem.crude.trade.order.enums.ShipTypeEnum;
import com.sinochem.crude.trade.order.enums.ShipmentPortEnum;
import com.sinochem.crude.trade.order.enums.TradeItemEnum;
import com.sinochem.crude.trade.order.enums.ValuationBaseEnum;
import com.sinochem.crude.trade.order.enums.ValuationCurrencyEnum;
import com.sinochem.crude.trade.order.enums.ValuationProidTypeEnum;
import com.sinochem.crude.trade.order.model.vo.DictionaryVO;
import com.sinochem.crude.trade.order.service.DictionaryService;

@Service
public class DictionaryServiceImpl implements DictionaryService {

	private HashMap<Integer, DictionaryVO> getOldPayItemMap;
	private HashMap<Integer, DictionaryVO> getTradeItemMap;
	private HashMap<Integer, DictionaryVO> getPayItemMap;
	private HashMap<Integer, DictionaryVO> getPricingModeMap;
	private HashMap<Integer, DictionaryVO> getPricingMeasureUnitMap;
	private HashMap<Integer, DictionaryVO> getValuationBaseMap;
	private HashMap<Integer, DictionaryVO> getProductOilValuationBaseMap;
	private HashMap<Integer, DictionaryVO> getValuationCurrencyMap;
	private HashMap<Integer, DictionaryVO> getMeasureModeMap;
	private HashMap<Integer, DictionaryVO> getValuationProidTypeMap;
	private HashMap<Integer, DictionaryVO> getCrudeOilOriginMap;
	private HashMap<Integer, DictionaryVO> getCreditItemMap;
	private HashMap<Integer, DictionaryVO> getPurchaseModesMap;
	private HashMap<Integer, DictionaryVO> getShipmentPortMap;
	private HashMap<Integer, DictionaryVO> getCrudeOilDischargePortMap;
	private HashMap<Integer, DictionaryVO> getShipTypeMap;
	private HashMap<Integer, DictionaryVO> getProductOilClassifyMap;
	private HashMap<Integer, DictionaryVO> getExportTypeMap;
	private HashMap<Integer, DictionaryVO> getRegionProductOilMap;
	private HashMap<Integer, DictionaryVO> getBusinessCheckOrgMap;
	private HashMap<Integer, DictionaryVO> EventPricingTypeMap;
	private HashMap<Integer, DictionaryVO> getHLCTypeMap;
	
	/**
	 * H/L/C
	 *
	 * @return
	 */
	 @SuppressWarnings("unchecked")
	@Override
	public HashMap<Integer, DictionaryVO> getHLCTypeMap() {
		 HashMap<Integer, DictionaryVO> rlsMap = new HashMap<>();
	        if (this.getHLCTypeMap != null) {
	        	rlsMap = (HashMap<Integer, DictionaryVO>) this.getHLCTypeMap.clone();
	            return rlsMap;
	        }

	        for (HLCTypeEnum hLCTypeEnum : HLCTypeEnum.values()) {
	            DictionaryVO dictionaryVO = new DictionaryVO(
	            		hLCTypeEnum.getCode(),
						hLCTypeEnum.getZhName(),
						hLCTypeEnum.getEnName()
	            );

	            rlsMap.put(dictionaryVO.getCode(), dictionaryVO);
	        }

	        this.getHLCTypeMap = rlsMap;
	        return rlsMap;
	}
	@Override
	public DictionaryVO getHLCType(String code) {
		 if (StringUtil.isEmpty(code)) {
				return null;
			}
			return getHLCTypeMap.get(code);
	}
	
	/**
	 * 计价事件
	 *
	 * @return
	 */
	 @SuppressWarnings("unchecked")
	 @Override
	public HashMap<Integer, DictionaryVO> EventPricingTypeMap() {
		 HashMap<Integer, DictionaryVO> rlsMap = new HashMap<>();
	        if (this.EventPricingTypeMap != null) {
	        	rlsMap = (HashMap<Integer, DictionaryVO>) this.EventPricingTypeMap.clone();
	            return rlsMap;
	        }

	        for (EventPricingTypeEnum eventPricingTypeEnum : EventPricingTypeEnum.values()) {
	            DictionaryVO dictionaryVO = new DictionaryVO(
	            		eventPricingTypeEnum.getCode(),
	            		eventPricingTypeEnum.getZhName(),
	            		eventPricingTypeEnum.getEnName()
	            );

	            rlsMap.put(dictionaryVO.getCode(), dictionaryVO);
	        }

	        this.EventPricingTypeMap = rlsMap;
	        return rlsMap;
	}
	 @Override
	public DictionaryVO EventPricingType(String code) {
		 if (StringUtil.isEmpty(code)) {
				return null;
			}
			return EventPricingTypeMap.get(code);
	}
	
	/**
	 * 商检机构
	 *
	 * @return
	 */
	 @SuppressWarnings("unchecked")
	 @Override
	public HashMap<Integer, DictionaryVO> getBusinessCheckOrgMap() {
		 HashMap<Integer, DictionaryVO> rlsMap = new HashMap<>();
	        if (this.getBusinessCheckOrgMap != null) {
	        	rlsMap = (HashMap<Integer, DictionaryVO>) this.getBusinessCheckOrgMap.clone();
	            return rlsMap;
	        }

	        for (BusinessCheckOrgEnum businessCheckOrgEnum : BusinessCheckOrgEnum.values()) {
	            DictionaryVO dictionaryVO = new DictionaryVO(
	            		businessCheckOrgEnum.getCode(),
	            		businessCheckOrgEnum.getZhName(),
	            		businessCheckOrgEnum.getEnName()
	            );

	            rlsMap.put(dictionaryVO.getCode(), dictionaryVO);
	        }

	        this.getBusinessCheckOrgMap = rlsMap;
	        return rlsMap;
	}
	 @Override
	public DictionaryVO getBusinessCheckOrg(String code) {
		 if (StringUtil.isEmpty(code)) {
				return null;
			}
			return getBusinessCheckOrgMap.get(code);
	}
	
	/**
	 * 成品油地区
	 *
	 * @return
	 */
	 @SuppressWarnings("unchecked")
	 @Override
	public HashMap<Integer, DictionaryVO> getRegionProductOilMap() {
		 HashMap<Integer, DictionaryVO> rlsMap = new HashMap<>();
	        if (this.getRegionProductOilMap != null) {
	        	rlsMap = (HashMap<Integer, DictionaryVO>) this.getRegionProductOilMap.clone();
	            return rlsMap;
	        }

	        for (RegionProductOilEnum regionProductOilEnum : RegionProductOilEnum.values()) {
	            DictionaryVO dictionaryVO = new DictionaryVO(
	            		regionProductOilEnum.getCode(),
	            		regionProductOilEnum.getZhName(),
	            		regionProductOilEnum.getEnName()
	            );

	            rlsMap.put(dictionaryVO.getCode(), dictionaryVO);
	        }

	        this.getRegionProductOilMap = rlsMap;
	        return rlsMap;
	}
	 @Override
	public DictionaryVO getRegionProductOil(String code) {
		 if (StringUtil.isEmpty(code)) {
				return null;
			}
			return getRegionProductOilMap.get(code);
	}
	
	 /**
	 * 出口类型
	 *
	 * @return
	 */
	 @SuppressWarnings("unchecked")
	 @Override
	public HashMap<Integer, DictionaryVO> getExportTypeMap() {
		 HashMap<Integer, DictionaryVO> rlsMap = new HashMap<>();
	        if (this.getExportTypeMap != null) {
	        	rlsMap = (HashMap<Integer, DictionaryVO>) this.getExportTypeMap.clone();
	            return rlsMap;
	        }

	        for (ExportTypeEnum exportTypeEnum : ExportTypeEnum.values()) {
	            DictionaryVO dictionaryVO = new DictionaryVO(
	            		exportTypeEnum.getCode(),
	            		exportTypeEnum.getZhName(),
	            		exportTypeEnum.getEnName()
	            );

	            rlsMap.put(dictionaryVO.getCode(), dictionaryVO);
	        }

	        this.getExportTypeMap = rlsMap;
	        return rlsMap;
	}
	 @Override
	public DictionaryVO getExportType(String code) {
		 if (StringUtil.isEmpty(code)) {
				return null;
			}
			return getExportTypeMap.get(code);
	}

	 /**
	 * 商品分类： 汽油 柴油 煤油
	 *
	 * @return
	 */
	 @SuppressWarnings("unchecked")
   @Override
	public HashMap<Integer, DictionaryVO> getProductOilClassifyMap() {
		 HashMap<Integer, DictionaryVO> rlsMap = new HashMap<>();
	        if (this.getProductOilClassifyMap != null) {
	        	rlsMap = (HashMap<Integer, DictionaryVO>) this.getProductOilClassifyMap.clone();
	            return rlsMap;
	        }

	        for (ProductOilClassifyEnum productOilClassifyEnum : ProductOilClassifyEnum.values()) {
	            DictionaryVO dictionaryVO = new DictionaryVO(
	            		productOilClassifyEnum.getCode(),
	            		productOilClassifyEnum.getZhName(),
	            		productOilClassifyEnum.getEnName()
	            );

	            rlsMap.put(dictionaryVO.getCode(), dictionaryVO);
	        }

	        this.getProductOilClassifyMap = rlsMap;
	        return rlsMap;
	}
	 @Override
	public DictionaryVO getProductOilClassify(String code) {
		 if (StringUtil.isEmpty(code)) {
				return null;
			}
			return getProductOilClassifyMap.get(code);
	}
	
	 /**
	 * 船型
	 *
	 * @return
	 */
	 @SuppressWarnings("unchecked")
	 @Override
	public HashMap<Integer, DictionaryVO> getShipTypeMap() {
		 HashMap<Integer, DictionaryVO> rlsMap = new HashMap<>();
	        if (this.getShipTypeMap != null) {
	        	rlsMap = (HashMap<Integer, DictionaryVO>) this.getShipTypeMap.clone();
	            return rlsMap;
	        }

	        for (ShipTypeEnum shipTypeEnum : ShipTypeEnum.values()) {
	            DictionaryVO dictionaryVO = new DictionaryVO(
	            		shipTypeEnum.getCode(),
	            		shipTypeEnum.getZhName(),
	            		shipTypeEnum.getEnName()
	            );

	            rlsMap.put(dictionaryVO.getCode(), dictionaryVO);
	        }

	        this.getShipTypeMap = rlsMap;
	        return rlsMap;
	}
	 @Override
	public DictionaryVO getShipType(String code) {
	   if (StringUtil.isEmpty(code)) {
			return null;
		}
		return getShipTypeMap.get(code);
	}
	
	 /**
	 * 原油卸货港
	 *
	 * @return
	 */
	 @SuppressWarnings("unchecked")
	 @Override
	public HashMap<Integer, DictionaryVO> getCrudeOilDischargePortMap() {
		 HashMap<Integer, DictionaryVO> rlsMap = new HashMap<>();
	        if (this.getCrudeOilDischargePortMap != null) {
	        	rlsMap = (HashMap<Integer, DictionaryVO>) this.getCrudeOilDischargePortMap.clone();
	            return rlsMap;
	        }

	        for (CrudeOilDischargePortEnum crudeOilDischargePortEnum : CrudeOilDischargePortEnum.values()) {
	            DictionaryVO dictionaryVO = new DictionaryVO(
	            		crudeOilDischargePortEnum.getCode(),
	            		crudeOilDischargePortEnum.getZhName(),
	            		crudeOilDischargePortEnum.getEnName()
	            );

	            rlsMap.put(dictionaryVO.getCode(), dictionaryVO);
	        }

	        this.getCrudeOilDischargePortMap = rlsMap;
	        return rlsMap;
	}
	 @Override
	public DictionaryVO getCrudeOilDischargePort(String code) {
		 if (StringUtil.isEmpty(code)) {
				return null;
			}
			return getCrudeOilDischargePortMap.get(code);
	}
		 
	/**
	 * 原油装货港
	 *
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public HashMap<Integer, DictionaryVO> getShipmentPortMap() {
		 HashMap<Integer, DictionaryVO> rlsMap = new HashMap<>();
	        if (this.getShipmentPortMap != null) {
	        	rlsMap = (HashMap<Integer, DictionaryVO>) this.getShipmentPortMap.clone();
	            return rlsMap;
	        }

	        for (ShipmentPortEnum shipmentPortEnum : ShipmentPortEnum.values()) {
	            DictionaryVO dictionaryVO = new DictionaryVO(
	            		shipmentPortEnum.getCode(),
	            		shipmentPortEnum.getZhName(),
	            		shipmentPortEnum.getEnName()
	            );

	            rlsMap.put(dictionaryVO.getCode(), dictionaryVO);
	        }

	        this.getShipmentPortMap = rlsMap;
	        return rlsMap;
	}
	 @Override
	public DictionaryVO getShipmentPort(String code) {
		 if (StringUtil.isEmpty(code)) {
				return null;
			}
			return getShipmentPortMap.get(code);
	}
	 
	
	/**
	 * 采购方式
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public HashMap<Integer, DictionaryVO> getPurchaseModesMap() {
		HashMap<Integer, DictionaryVO> rlsMap = new HashMap<>();
        if (this.getPurchaseModesMap != null) {
        	rlsMap = (HashMap<Integer, DictionaryVO>) this.getPurchaseModesMap.clone();
            return rlsMap;
        }

        for (PurchaseModeEnum purchaseModeEnum : PurchaseModeEnum.values()) {
            DictionaryVO dictionaryVO = new DictionaryVO(
            		purchaseModeEnum.getCode(),
            		purchaseModeEnum.getZhName(),
            		purchaseModeEnum.getEnName()
            );

            rlsMap.put(dictionaryVO.getCode(), dictionaryVO);
        }

        this.getPurchaseModesMap = rlsMap;
        return rlsMap;
	}
	@Override
	public DictionaryVO getPurchaseModes(String code) {
		if (StringUtil.isEmpty(code)) {
			return null;
		}
		return getPurchaseModesMap.get(code);
	}
	
	/**
	 * 信用条款
	 */
	@SuppressWarnings("unchecked")
	@Override
	public HashMap<Integer, DictionaryVO> getCreditItemMap() {
		HashMap<Integer, DictionaryVO> rlsMap = new HashMap<>();
        if (this.getCreditItemMap != null) {
        	rlsMap = (HashMap<Integer, DictionaryVO>) this.getCreditItemMap.clone();
            return rlsMap;
        }

        for (CreditItemEnum creditItemEnum : CreditItemEnum.values()) {
            DictionaryVO dictionaryVO = new DictionaryVO(
            		creditItemEnum.getCode(),
            		creditItemEnum.getZhName(),
            		creditItemEnum.getEnName()
            );

            rlsMap.put(dictionaryVO.getCode(), dictionaryVO);
        }

        this.getCreditItemMap = rlsMap;
        return rlsMap;
	}
	@Override
	public DictionaryVO getCreditItem(String code) {
		if (StringUtil.isEmpty(code)) {
			return null;
		}
		return getCreditItemMap.get(code);
	}
	
	/**
	 * 原油产地
	 *
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public HashMap<Integer, DictionaryVO> getCrudeOilOriginMap() {
		HashMap<Integer, DictionaryVO> rlsMap = new HashMap<>();
        if (this.getCrudeOilOriginMap != null) {
        	rlsMap = (HashMap<Integer, DictionaryVO>) this.getCrudeOilOriginMap.clone();
            return rlsMap;
        }

        for (CrudeOilOriginEnum crudeOilOriginEnum : CrudeOilOriginEnum.values()) {
            DictionaryVO dictionaryVO = new DictionaryVO(
            		crudeOilOriginEnum.getCode(),
            		crudeOilOriginEnum.getZhName(),
            		crudeOilOriginEnum.getEnName()
            );

            rlsMap.put(dictionaryVO.getCode(), dictionaryVO);
        }

        this.getCrudeOilOriginMap = rlsMap;
        return rlsMap;
	}
	@Override
	public DictionaryVO getCrudeOilOrigin(String code) {
		if (StringUtil.isEmpty(code)) {
			return null;
		}
		return getCrudeOilOriginMap.get(code);
	}
	
	
	/**
	 * 计价期类别
	 *
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public HashMap<Integer, DictionaryVO> getValuationProidTypeMap() {
		HashMap<Integer, DictionaryVO> rlsMap = new HashMap<>();
        if (this.getValuationProidTypeMap != null) {
        	rlsMap = (HashMap<Integer, DictionaryVO>) this.getValuationProidTypeMap.clone();
            return rlsMap;
        }

        for (ValuationProidTypeEnum valuationProidTypeEnum : ValuationProidTypeEnum.values()) {
            DictionaryVO dictionaryVO = new DictionaryVO(
            		valuationProidTypeEnum.getCode(),
            		valuationProidTypeEnum.getZhName(),
            		valuationProidTypeEnum.getEnName()
            );

            rlsMap.put(dictionaryVO.getCode(), dictionaryVO);
        }

        this.getValuationProidTypeMap = rlsMap;
        return rlsMap;
	}
	@Override
	public DictionaryVO getValuationProidType(String code) {
		if (StringUtil.isEmpty(code)) {
			return null;
		}
		return getValuationProidTypeMap.get(code);
	}
	
	
	/**
	 * 计量方式 提单量、船检量、VEF船检量、船检量-ROB、VEF船检量-ROB、罐收量、罐出量
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public HashMap<Integer, DictionaryVO> getMeasureModeMap() {
		HashMap<Integer, DictionaryVO> rlsMap = new HashMap<>();
        if (this.getMeasureModeMap != null) {
        	rlsMap = (HashMap<Integer, DictionaryVO>) this.getMeasureModeMap.clone();
            return rlsMap;
        }

        for (MeasureModeEnum measureModeEnum : MeasureModeEnum.values()) {
            DictionaryVO dictionaryVO = new DictionaryVO(
            		measureModeEnum.getCode(),
            		measureModeEnum.getZhName(),
            		measureModeEnum.getEnName()
            );

            rlsMap.put(dictionaryVO.getCode(), dictionaryVO);
        }

        this.getMeasureModeMap = rlsMap;
        return rlsMap;
	}
	@Override
	public DictionaryVO getMeasureMode(String code) {
		if (StringUtil.isEmpty(code)) {
			return null;
		}
		return getMeasureModeMap.get(code);
	}
	
	/**
	 * 计价货币
	 *
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public HashMap<Integer, DictionaryVO> getValuationCurrencyMap() {
		HashMap<Integer, DictionaryVO> rlsMap = new HashMap<>();
        if (this.getValuationCurrencyMap != null) {
        	rlsMap = (HashMap<Integer, DictionaryVO>) this.getValuationCurrencyMap.clone();
            return rlsMap;
        }

        for (ValuationCurrencyEnum valuationCurrencyEnum : ValuationCurrencyEnum.values()) {
            DictionaryVO dictionaryVO = new DictionaryVO(
            		valuationCurrencyEnum.getCode(),
            		valuationCurrencyEnum.getZhName(),
            		valuationCurrencyEnum.getEnName()
            );

            rlsMap.put(dictionaryVO.getCode(), dictionaryVO);
        }

        this.getValuationCurrencyMap = rlsMap;
        return rlsMap;
	}
	@Override
	public DictionaryVO getValuationCurrency(String code) {
		if (StringUtil.isEmpty(code)) {
			return null;
		}
		return getValuationCurrencyMap.get(code);
	}
	
	/**
	 * 成品油计价基准
	 *
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public HashMap<Integer, DictionaryVO> getProductOilValuationBaseMap() {
		HashMap<Integer, DictionaryVO> rlsMap = new HashMap<>();
        if (this.getProductOilValuationBaseMap != null) {
        	rlsMap = (HashMap<Integer, DictionaryVO>) this.getProductOilValuationBaseMap.clone();
            return rlsMap;
        }

        for (ProductOilValuationBaseEnum productOilValuationBaseEnum : ProductOilValuationBaseEnum.values()) {
            DictionaryVO dictionaryVO = new DictionaryVO(
            		productOilValuationBaseEnum.getCode(),
            		productOilValuationBaseEnum.getZhName(),
            		productOilValuationBaseEnum.getEnName()
            );

            rlsMap.put(dictionaryVO.getCode(), dictionaryVO);
        }

        this.getProductOilValuationBaseMap = rlsMap;
        return rlsMap;
	}
	@Override
	public DictionaryVO getProductOilValuationBase(String code) {
		if (StringUtil.isEmpty(code)) {
			return null;
		}
		return getProductOilValuationBaseMap.get(code);
	}
	
	/**
	 * 计价基准 TODO
	 *
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public HashMap<Integer, DictionaryVO> getValuationBaseMap() {
		HashMap<Integer, DictionaryVO> rlsMap = new HashMap<>();
        if (this.getValuationBaseMap != null) {
        	rlsMap = (HashMap<Integer, DictionaryVO>) this.getValuationBaseMap.clone();
            return rlsMap;
        }

        for (ValuationBaseEnum valuationBaseEnum : ValuationBaseEnum.values()) {
            DictionaryVO dictionaryVO = new DictionaryVO(
            		valuationBaseEnum.getCode(),
            		valuationBaseEnum.getZhName(),
            		valuationBaseEnum.getEnName()
            );

            rlsMap.put(dictionaryVO.getCode(), dictionaryVO);
        }

        this.getValuationBaseMap = rlsMap;
        return rlsMap;
	}
	@Override
	public DictionaryVO getValuationBase(String code) {
		if (StringUtil.isEmpty(code)) {
			return null;
		}
		return getValuationBaseMap.get(code);
	}
	
	/**
     * 贸易条款
     * @return
     */
	@SuppressWarnings("unchecked")
	@Override
	public HashMap<Integer, DictionaryVO> getTradeItemMap() {
		HashMap<Integer, DictionaryVO> rlsMap = new HashMap<>();
        if (this.getTradeItemMap != null) {
        	rlsMap = (HashMap<Integer, DictionaryVO>) this.getTradeItemMap.clone();
            return rlsMap;
        }

        for (TradeItemEnum tradeItemEnum : TradeItemEnum.values()) {
            DictionaryVO dictionaryVO = new DictionaryVO(
            		tradeItemEnum.getCode(),
            		tradeItemEnum.getZhName(),
            		tradeItemEnum.getEnName()
            );

            rlsMap.put(dictionaryVO.getCode(), dictionaryVO);
        }

        this.getTradeItemMap = rlsMap;
        return rlsMap;
	}
	@Override
	public DictionaryVO getTradeItem(String code) {
		if (StringUtil.isEmpty(code)) {
			return null;
		}
		return getTradeItemMap.get(code);
	}
	
	
	/**
	 * 付款日期
	 */
	@SuppressWarnings("unchecked")
	@Override
	public HashMap<Integer, DictionaryVO> getOldPayItemMap() {
		HashMap<Integer, DictionaryVO> rlsMap = new HashMap<>();
        if (this.getOldPayItemMap != null) {
        	rlsMap = (HashMap<Integer, DictionaryVO>) this.getOldPayItemMap.clone();
            return rlsMap;
        }

        for (OldPayItemEnum oldPayItemEnum : OldPayItemEnum.values()) {
            DictionaryVO dictionaryVO = new DictionaryVO(
            		oldPayItemEnum.getCode(),
            		oldPayItemEnum.getZhName(),
            		oldPayItemEnum.getEnName()
            );

            rlsMap.put(dictionaryVO.getCode(), dictionaryVO);
        }

        this.getOldPayItemMap = rlsMap;
        return rlsMap;
	}
	@Override
	public DictionaryVO getOldPayItem(String code) {
		if (StringUtil.isEmpty(code)) {
			return null;
		}
		return getOldPayItemMap.get(code);
	}
	
	/**
	 * 付款日期下拉框
	 */
	@SuppressWarnings("unchecked")
	@Override
	public HashMap<Integer, DictionaryVO> getPayItemMap() {
		HashMap<Integer, DictionaryVO> rlsMap = new HashMap<>();
        if (this.getPayItemMap != null) {
        	rlsMap = (HashMap<Integer, DictionaryVO>) this.getPayItemMap.clone();
            return rlsMap;
        }

        for (PayItemEnum payItemEnum : PayItemEnum.values()) {
            DictionaryVO dictionaryVO = new DictionaryVO(
            		payItemEnum.getCode(),
            		payItemEnum.getZhName(),
            		payItemEnum.getEnName()
            );

            rlsMap.put(dictionaryVO.getCode(), dictionaryVO);
        }

        this.getPayItemMap = rlsMap;
        return rlsMap;
	}
	@Override
	public DictionaryVO getPayItem(String code) {
		if (StringUtil.isEmpty(code)) {
			return null;
		}
		return getPayItemMap.get(code);
	}
	
	/**
	 * 价格方式
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public HashMap<Integer, DictionaryVO> getPricingModeMap() {
		HashMap<Integer, DictionaryVO> rlsMap = new HashMap<>();
        if (this.getPricingModeMap != null) {
        	rlsMap = (HashMap<Integer, DictionaryVO>) this.getPricingModeMap.clone();
            return rlsMap;
        }

        for (PricingModeEnum pricingModeEnum : PricingModeEnum.values()) {
            DictionaryVO dictionaryVO = new DictionaryVO(
            		pricingModeEnum.getCode(),
            		pricingModeEnum.getZhName(),
            		pricingModeEnum.getEnName()
            );

            rlsMap.put(dictionaryVO.getCode(), dictionaryVO);
        }

        this.getPricingModeMap = rlsMap;
        return rlsMap;
	}
	@Override
	public DictionaryVO getPricingMode(String code) {
		if (StringUtil.isEmpty(code)) {
			return null;
		}
		return getPricingModeMap.get(code);
	}
	
	/**
	 * 定价计量单位
	 *
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public HashMap<Integer, DictionaryVO> getPricingMeasureUnitMap() {
		HashMap<Integer, DictionaryVO> rlsMap = new HashMap<>();
        if (this.getPricingMeasureUnitMap != null) {
        	rlsMap = (HashMap<Integer, DictionaryVO>) this.getPricingMeasureUnitMap.clone();
            return rlsMap;
        }

        for (PricingMeasureUnitEnum pricingMeasureUnitEnum : PricingMeasureUnitEnum.values()) {
            DictionaryVO dictionaryVO = new DictionaryVO(
            		pricingMeasureUnitEnum.getCode(),
            		pricingMeasureUnitEnum.getZhName(),
            		pricingMeasureUnitEnum.getEnName()
            );

            rlsMap.put(dictionaryVO.getCode(), dictionaryVO);
        }

        this.getPricingMeasureUnitMap = rlsMap;
        return rlsMap;
	}
	@Override
	public DictionaryVO getPricingMeasureUnit(String code) {
		if (StringUtil.isEmpty(code)) {
			return null;
		}
		return getPricingMeasureUnitMap.get(code);
	}
}
