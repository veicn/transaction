package com.sinochem.crude.trade.listed.service;

import java.util.HashMap;

import com.sinochem.crude.trade.listed.model.vo.DictionaryVO;

public interface DictionaryService {

	/**
	 * 汽油
	 * 
	 * @return
	 */
	HashMap<Integer, DictionaryVO> getProductOilKindMap();
	DictionaryVO getProductOilKind(String code);

	/**
	 * 汽油
	 * 
	 * @return
	 */
	HashMap<Integer, DictionaryVO> getGasolineProductOilKindMap();
	DictionaryVO getGasolineProductOilKind(String code);
	
	/**
	 * 贸易条款
	 *
	 * @return
	 */
	HashMap<Integer, DictionaryVO> getTradeItemMap();
	DictionaryVO getTradeItem(String code);
	
	/**
	 * 付款日期
	 * @return
	 */
	HashMap<Integer, DictionaryVO> getOldPayItemMap();
	DictionaryVO getOldPayItem(String code);
	
	/**
	 * 付款日期下拉框
	 * @return
	 */
	HashMap<Integer, DictionaryVO> getPayItemMap();
	DictionaryVO getPayItem(String code);
	
	/**
	 * 价格方式
	 *
	 * @return
	 */
	HashMap<Integer, DictionaryVO> getPricingModeMap();
	DictionaryVO getPricingMode(String code);
	
	/**
	 * 定价计量单位
	 *
	 * @return
	 */
	HashMap<Integer, DictionaryVO> getPricingMeasureUnitMap();
	DictionaryVO getPricingMeasureUnit(String code);
	
	/**
	 * 计价基准 TODO
	 *
	 * @return
	 */
	HashMap<Integer, DictionaryVO> getValuationBaseMap();
	DictionaryVO getValuationBase(String code);
	
	/**
	 * 成品油计价基准
	 *
	 * @return
	 */
	HashMap<Integer, DictionaryVO> getProductOilValuationBaseMap();
	DictionaryVO getProductOilValuationBase(String code);
	
	/**
	 * 计价货币
	 *
	 * @return
	 */
	HashMap<Integer, DictionaryVO> getValuationCurrencyMap();
	DictionaryVO getValuationCurrency(String code);
	
	/**
	 * 计量方式 提单量、船检量、VEF船检量、船检量-ROB、VEF船检量-ROB、罐收量、罐出量
	 * 
	 * @return
	 */
	HashMap<Integer, DictionaryVO> getMeasureModeMap();
	DictionaryVO getMeasureMode(String code);
	
	/**
	 * 计价期类别
	 *
	 * @return
	 */
	HashMap<Integer, DictionaryVO> getValuationProidTypeMap();
	DictionaryVO getValuationProidType(String code);
	
	/**
	 * 原油产地
	 *
	 * @return
	 */
	HashMap<Integer, DictionaryVO> getCrudeOilOriginMap();
	DictionaryVO getCrudeOilOrigin(String code);
	
	/**
	 * 信用条款
	 */
	HashMap<Integer, DictionaryVO> getCreditItemMap();
	DictionaryVO getCreditItem(String code);
	
	/**
	 * 采购方式
	 *
	 * @return
	 */
	HashMap<Integer, DictionaryVO> getPurchaseModesMap();
	DictionaryVO getPurchaseModes(String code);
	
	/**
	 * 原油装货港
	 *
	 * @return
	 */
	HashMap<Integer, DictionaryVO> getShipmentPortMap();
	DictionaryVO getShipmentPort(String code);
	
	/**
	 * 原油卸货港
	 *
	 * @return
	 */
	HashMap<Integer, DictionaryVO> getCrudeOilDischargePortMap();
	DictionaryVO getCrudeOilDischargePort(String code);
	
	/**
	 * 船型
	 *
	 * @return
	 */
	HashMap<Integer, DictionaryVO> getShipTypeMap();
	DictionaryVO getShipType(String code);
	
	/**
	 * 商品分类： 汽油 柴油 煤油
	 *
	 * @return
	 */
	HashMap<Integer, DictionaryVO> getProductOilClassifyMap();
	DictionaryVO getProductOilClassify(String code);
	
	/**
	 * 出口类型
	 *
	 * @return
	 */
	HashMap<Integer, DictionaryVO> getExportTypeMap();
	DictionaryVO getExportType(String code);
	
	/**
	 * 成品油地区
	 *
	 * @return
	 */
	HashMap<Integer, DictionaryVO> getRegionProductOilMap();
	DictionaryVO getRegionProductOil(String code);
	
	/**
	 * 商检机构
	 *
	 * @return
	 */
	HashMap<Integer, DictionaryVO> getBusinessCheckOrgMap();
	DictionaryVO getBusinessCheckOrg(String code);
	
	/**
	 * 计价事件
	 *
	 * @return
	 */
	HashMap<Integer, DictionaryVO> EventPricingTypeMap();
	DictionaryVO EventPricingType(String code);
	
	/**
	 * H/L/C
	 *
	 * @return
	 */
	HashMap<Integer, DictionaryVO> getHLCTypeMap();
	DictionaryVO getHLCType(String code);
	
	/**
	 * 交易方式
	 * 
	 * @return
	 */
	HashMap<Integer, DictionaryVO> getPurchaseTypeMap();
	DictionaryVO getPurchaseType(String code);
	
	/**
	 * 原油交易数量
	 * 
	 * @return
	 */
	HashMap<Integer, DictionaryVO> getDemandNumMap();
	DictionaryVO getDemandNum(String code);
	
	/**
	 * 价格类型
	 * 
	 * @return
	 */
	HashMap<Integer, DictionaryVO> getPriceTypeMap();
	DictionaryVO getPriceType(String code);
}
