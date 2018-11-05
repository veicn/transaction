package com.sinochem.crude.trade.order.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.context.ContextLoaderListener;

import com.eyeieye.melody.util.StringUtil;
import com.sinochem.crude.trade.common.i18n.VisitorLocale;
import com.sinochem.crude.trade.order.constart.Constart;
import com.sinochem.crude.trade.order.model.vo.DictionaryVO;
import com.sinochem.crude.trade.order.service.DictionaryService;



/**
 * Created by sijliu on 11/12/2017.
 */
public class DictUtils {
	/**
     * 根据卸货港值获取当前语言环境的卸货港值
     * @return
     */
    public static String[][] getUnLoadPortValue(String shipmentPort){
    	Map<String, String[][]> map = getUnLoadPortMap();
    	for(String key:map.keySet()){
    		if(key.equals(shipmentPort)){
    			String[][] value = map.get(key);
    			return value;
    		}
        }
		return null;
	}
	/**
	 * 卸货港中英文
	 * @return
	 */
	public static Map<String,String[][]> getUnLoadPortMap(){
    	Map<String,String[][]> map = new HashMap<String,String[][]>();
    	map.put(Constart.UN_LOAD_PORT_01[0][1],Constart.UN_LOAD_PORT_01);
    	map.put(Constart.UN_LOAD_PORT_01[1][1],Constart.UN_LOAD_PORT_01);
    	map.put(Constart.UN_LOAD_PORT_02[0][1],Constart.UN_LOAD_PORT_02);
    	map.put(Constart.UN_LOAD_PORT_02[1][1],Constart.UN_LOAD_PORT_02);
    	map.put(Constart.UN_LOAD_PORT_03[0][1],Constart.UN_LOAD_PORT_03);
    	map.put(Constart.UN_LOAD_PORT_03[1][1],Constart.UN_LOAD_PORT_03);
    	map.put(Constart.UN_LOAD_PORT_04[0][1],Constart.UN_LOAD_PORT_04);
    	map.put(Constart.UN_LOAD_PORT_04[1][1],Constart.UN_LOAD_PORT_04);
    	map.put(Constart.UN_LOAD_PORT_05[0][1],Constart.UN_LOAD_PORT_05);
    	map.put(Constart.UN_LOAD_PORT_05[1][1],Constart.UN_LOAD_PORT_05);
    	map.put(Constart.UN_LOAD_PORT_06[0][1],Constart.UN_LOAD_PORT_06);
    	map.put(Constart.UN_LOAD_PORT_06[1][1],Constart.UN_LOAD_PORT_06);
    	map.put(Constart.UN_LOAD_PORT_07[0][1],Constart.UN_LOAD_PORT_07);
    	map.put(Constart.UN_LOAD_PORT_07[1][1],Constart.UN_LOAD_PORT_07);
    	map.put(Constart.UN_LOAD_PORT_08[0][1],Constart.UN_LOAD_PORT_08);
    	map.put(Constart.UN_LOAD_PORT_08[1][1],Constart.UN_LOAD_PORT_08);
    	map.put(Constart.UN_LOAD_PORT_09[0][1],Constart.UN_LOAD_PORT_09);
    	map.put(Constart.UN_LOAD_PORT_09[1][1],Constart.UN_LOAD_PORT_09);
    	map.put(Constart.UN_LOAD_PORT_10[0][1],Constart.UN_LOAD_PORT_10);
    	map.put(Constart.UN_LOAD_PORT_10[1][1],Constart.UN_LOAD_PORT_10);
    	map.put(Constart.UN_LOAD_PORT_11[0][1],Constart.UN_LOAD_PORT_11);
    	map.put(Constart.UN_LOAD_PORT_11[1][1],Constart.UN_LOAD_PORT_11);
    	map.put(Constart.UN_LOAD_PORT_12[0][1],Constart.UN_LOAD_PORT_12);
    	map.put(Constart.UN_LOAD_PORT_12[1][1],Constart.UN_LOAD_PORT_12);
    	map.put(Constart.UN_LOAD_PORT_13[0][1],Constart.UN_LOAD_PORT_13);
    	map.put(Constart.UN_LOAD_PORT_13[1][1],Constart.UN_LOAD_PORT_13);
    	map.put(Constart.UN_LOAD_PORT_14[0][1],Constart.UN_LOAD_PORT_14);
    	map.put(Constart.UN_LOAD_PORT_14[1][1],Constart.UN_LOAD_PORT_14);
    	map.put(Constart.UN_LOAD_PORT_15[0][1],Constart.UN_LOAD_PORT_15);
    	map.put(Constart.UN_LOAD_PORT_15[1][1],Constart.UN_LOAD_PORT_15);
    	map.put(Constart.UN_LOAD_PORT_16[0][1],Constart.UN_LOAD_PORT_16);
    	map.put(Constart.UN_LOAD_PORT_16[1][1],Constart.UN_LOAD_PORT_16);
    	map.put(Constart.UN_LOAD_PORT_17[0][1],Constart.UN_LOAD_PORT_17);
    	map.put(Constart.UN_LOAD_PORT_17[1][1],Constart.UN_LOAD_PORT_17);
    	map.put(Constart.UN_LOAD_PORT_18[0][1],Constart.UN_LOAD_PORT_18);
    	map.put(Constart.UN_LOAD_PORT_18[1][1],Constart.UN_LOAD_PORT_18);
    	map.put(Constart.UN_LOAD_PORT_19[0][1],Constart.UN_LOAD_PORT_19);
    	map.put(Constart.UN_LOAD_PORT_19[1][1],Constart.UN_LOAD_PORT_19);
    	map.put(Constart.UN_LOAD_PORT_20[0][1],Constart.UN_LOAD_PORT_20);
    	map.put(Constart.UN_LOAD_PORT_20[1][1],Constart.UN_LOAD_PORT_20);
    	map.put(Constart.UN_LOAD_PORT_21[0][1],Constart.UN_LOAD_PORT_21);
    	map.put(Constart.UN_LOAD_PORT_21[1][1],Constart.UN_LOAD_PORT_21);
    	map.put(Constart.UN_LOAD_PORT_22[0][1],Constart.UN_LOAD_PORT_22);
    	map.put(Constart.UN_LOAD_PORT_22[1][1],Constart.UN_LOAD_PORT_22);
    	map.put(Constart.UN_LOAD_PORT_23[0][1],Constart.UN_LOAD_PORT_23);
    	map.put(Constart.UN_LOAD_PORT_23[1][1],Constart.UN_LOAD_PORT_23);
    	return map;
    }
	
	/**
	 * 商检费分摊
	 */
	public static Map<Integer,String> getInspectionFeeSharingTypeMap(){
        Map<Integer,String> map = new HashMap<Integer, String>();

        map.put(1, "seller’s account");
        map.put(2, "buyer’s account");
        map.put(3, "50/50 between seller&buyer");
        map.put(4, "shared between parties");
        return map;
    }
    /**
     * 贸易条款
     * @return
     */
    public static Map<Integer,String> getTradeItemMap(){
    	// 引入值集接口
		DictionaryService dictionaryService = ContextLoaderListener.getCurrentWebApplicationContext().getBean(DictionaryService.class);
		HashMap<Integer,DictionaryVO> getTradeItemMap = dictionaryService.getTradeItemMap();
		
		Collection<DictionaryVO> values = getTradeItemMap.values();
		
		HashMap<Integer, String> map =new HashMap<Integer, String>();
		for (DictionaryVO dictionaryVO : values) {
			map.put(dictionaryVO.getCode(), dictionaryVO.getZhName());
		}
		return map;
    }

    /**
     * 付款日期
     *
     * @return
     */
    public static Map<Object, String> getOldPayItemMap() {
    	// 引入值集接口
		DictionaryService dictionaryService = ContextLoaderListener.getCurrentWebApplicationContext().getBean(DictionaryService.class);
		HashMap<Integer,DictionaryVO> getOldPayItemMap = dictionaryService.getOldPayItemMap();
		
		Collection<DictionaryVO> values = getOldPayItemMap.values();
		
		HashMap<Object, String> map =new HashMap<Object, String>();
		for (DictionaryVO dictionaryVO : values) {
			map.put(dictionaryVO.getCode(), dictionaryVO.getZhName());
		}
		return map;
    }


    /**
     * 付款日期下拉框
     */
    public static Map<Object, String> getPayItemMap() {
    	// 引入值集接口
		DictionaryService dictionaryService = ContextLoaderListener.getCurrentWebApplicationContext().getBean(DictionaryService.class);
		HashMap<Integer,DictionaryVO> getPayItemMap = dictionaryService.getPayItemMap();
		
		Collection<DictionaryVO> values = getPayItemMap.values();
		
		HashMap<Object, String> map =new HashMap<Object, String>();
		for (DictionaryVO dictionaryVO : values) {
			map.put(dictionaryVO.getCode(), dictionaryVO.getZhName());
		}
		return map;
    }
    /**
     * 价格方式
     * @return
     */
    public static Map<Object,String> getPricingModeMap(){
    	// 引入值集接口
		DictionaryService dictionaryService = ContextLoaderListener.getCurrentWebApplicationContext().getBean(DictionaryService.class);
		HashMap<Integer,DictionaryVO> getPricingModeMap = dictionaryService.getPricingModeMap();
		
		Collection<DictionaryVO> values = getPricingModeMap.values();
		
		HashMap<Object, String> map =new HashMap<Object, String>();
		for (DictionaryVO dictionaryVO : values) {
			map.put(dictionaryVO.getCode(), dictionaryVO.getZhName());
		}
        return map;
    }
    /**
     * 定价计量单位
     * @return
     */
    public static Map<Object,String> getPricingMeasureUnitMap(){
    	// 引入值集接口
		DictionaryService dictionaryService = ContextLoaderListener.getCurrentWebApplicationContext().getBean(DictionaryService.class);
		HashMap<Integer,DictionaryVO> getPricingMeasureUnitMap = dictionaryService.getPricingMeasureUnitMap();
		
		Collection<DictionaryVO> values = getPricingMeasureUnitMap.values();
		
		HashMap<Object, String> map =new HashMap<Object, String>();
		for (DictionaryVO dictionaryVO : values) {
			map.put(dictionaryVO.getCode(), dictionaryVO.getZhName());
		}
		return map;
    }
    /**
     * 计价基准 TODO
     * @return
     */
    public static Map<Object, String> getValuationBaseMap(){
    	// 引入值集接口
		DictionaryService dictionaryService = ContextLoaderListener.getCurrentWebApplicationContext().getBean(DictionaryService.class);
		HashMap<Integer,DictionaryVO> getValuationBaseMap = dictionaryService.getValuationBaseMap();
		
		Collection<DictionaryVO> values = getValuationBaseMap.values();
		
		HashMap<Object, String> map =new HashMap<Object, String>();
		for (DictionaryVO dictionaryVO : values) {
			map.put(dictionaryVO.getCode().toString(), dictionaryVO.getZhName());
		}
		return map;
    }

    /**
     * 成品油计价基准
     *
     * @return
     */
    public static Map<Object, String> getProductOilValuationBaseMap() {
    	// 引入值集接口
		DictionaryService dictionaryService = ContextLoaderListener.getCurrentWebApplicationContext().getBean(DictionaryService.class);
		HashMap<Integer,DictionaryVO> getProductOilValuationBaseMap = dictionaryService.getProductOilValuationBaseMap();
		
		Collection<DictionaryVO> values = getProductOilValuationBaseMap.values();
		
		HashMap<Object, String> map =new HashMap<Object, String>();
		for (DictionaryVO dictionaryVO : values) {
			map.put(dictionaryVO.getCode(), dictionaryVO.getZhName());
		}
		return map;
    }

    /**
     * 计价货币
     * @return
     */
    public static Map<Object,String> getValuationCurrencyMap(){
    	// 引入值集接口
		DictionaryService dictionaryService = ContextLoaderListener.getCurrentWebApplicationContext().getBean(DictionaryService.class);
		HashMap<Integer,DictionaryVO> getValuationCurrencyMap = dictionaryService.getValuationCurrencyMap();
		
		Collection<DictionaryVO> values = getValuationCurrencyMap.values();
		
		HashMap<Object, String> map =new HashMap<Object, String>();
		for (DictionaryVO dictionaryVO : values) {
			map.put(dictionaryVO.getCode(), dictionaryVO.getZhName());
		}
		return map;
    }
    /**
     * 计量方式
     * @return
     */
    public static Map<Object,String> getMeasureModeMap(){
    	// 引入值集接口
		DictionaryService dictionaryService = ContextLoaderListener.getCurrentWebApplicationContext().getBean(DictionaryService.class);
		HashMap<Integer,DictionaryVO> getMeasureModeMap = dictionaryService.getMeasureModeMap();
		
		Collection<DictionaryVO> values = getMeasureModeMap.values();
		
		HashMap<Object, String> map =new HashMap<Object, String>();

		if ("en".equals(VisitorLocale.getCurrent().getLanguage())) {
			for (DictionaryVO dictionaryVO : values) {
				map.put(dictionaryVO.getCode(), dictionaryVO.getEnName());
			}
			return map;
		} else {
			for (DictionaryVO dictionaryVO : values) {
				map.put(dictionaryVO.getCode(), dictionaryVO.getZhName());
			}
			return map;
		}
    }
    /**
     * 计价期类别
     * @return
     */
    public static Map<Object,String> getValuationProidTypeMap(){
    	// 引入值集接口
		DictionaryService dictionaryService = ContextLoaderListener.getCurrentWebApplicationContext().getBean(DictionaryService.class);
		HashMap<Integer,DictionaryVO> getValuationProidTypeMap = dictionaryService.getValuationProidTypeMap();
		
		Collection<DictionaryVO> values = getValuationProidTypeMap.values();
		
		HashMap<Object, String> map =new HashMap<Object, String>();
		for (DictionaryVO dictionaryVO : values) {
			map.put(dictionaryVO.getCode(), dictionaryVO.getZhName());
		}
		return map;
    }

    /**
     * 原油产地
     * @return
     */
    public static Map<Object, String> getCrudeOilOrigin(){
    	// 引入值集接口
		DictionaryService dictionaryService = ContextLoaderListener.getCurrentWebApplicationContext().getBean(DictionaryService.class);
		HashMap<Integer,DictionaryVO> getCrudeOilOriginMap = dictionaryService.getCrudeOilOriginMap();
		
		Collection<DictionaryVO> values = getCrudeOilOriginMap.values();
		
		HashMap<Object, String> map =new HashMap<Object, String>();
		for (DictionaryVO dictionaryVO : values) {
			map.put(dictionaryVO.getCode(), dictionaryVO.getZhName());
		}
		return map;
    }


    /**
     * 信用条款
     */
    public static Map<Object, String> getCreditItem(){
    	// 引入值集接口
		DictionaryService dictionaryService = ContextLoaderListener.getCurrentWebApplicationContext().getBean(DictionaryService.class);
		HashMap<Integer,DictionaryVO> getCreditItemMap = dictionaryService.getCreditItemMap();
		
		Collection<DictionaryVO> values = getCreditItemMap.values();
		
		HashMap<Object, String> map =new HashMap<Object, String>();
		for (DictionaryVO dictionaryVO : values) {
			map.put(dictionaryVO.getCode(), dictionaryVO.getZhName());
		}
		return map;
    }

    /**
     * 采购方式
     * @return
     */
    public static Map<Object, String> getPurchaseModes(){
    	// 引入值集接口
		DictionaryService dictionaryService = ContextLoaderListener.getCurrentWebApplicationContext().getBean(DictionaryService.class);
		HashMap<Integer,DictionaryVO> getPurchaseModesMap = dictionaryService.getPurchaseModesMap();
		
		Collection<DictionaryVO> values = getPurchaseModesMap.values();
		
		HashMap<Object, String> map =new HashMap<Object, String>();
		for (DictionaryVO dictionaryVO : values) {
			map.put(dictionaryVO.getCode(), dictionaryVO.getZhName());
		}
		return map;
    }

    /**
     * 原油装货港
     * @return
     */
    public static Map<Object, String>  shipmentPorts(){
    	// 引入值集接口
		DictionaryService dictionaryService = ContextLoaderListener.getCurrentWebApplicationContext().getBean(DictionaryService.class);
		HashMap<Integer,DictionaryVO> getShipmentPortMap = dictionaryService.getShipmentPortMap();
		
		Collection<DictionaryVO> values = getShipmentPortMap.values();
		
		HashMap<Object, String> map =new HashMap<Object, String>();
		for (DictionaryVO dictionaryVO : values) {
			map.put(dictionaryVO.getCode(), dictionaryVO.getZhName());
		}
		return map;
    }

    /**
     * 原油卸货港
     * @return
     */
    public static Map<Object, String> getCrudeOilDischargePorts(){
    	// 引入值集接口
		DictionaryService dictionaryService = ContextLoaderListener.getCurrentWebApplicationContext().getBean(DictionaryService.class);
		HashMap<Integer,DictionaryVO> getCrudeOilDischargePortMap = dictionaryService.getCrudeOilDischargePortMap();
		
		Collection<DictionaryVO> values = getCrudeOilDischargePortMap.values();
		
		HashMap<Object, String> map =new HashMap<Object, String>();
		for (DictionaryVO dictionaryVO : values) {
			map.put(dictionaryVO.getCode(), dictionaryVO.getZhName());
		}
		return map;
    }

    /**
     * 船型
     * @return
     */
    public static Map<Object, String> getShipTypes(){
    	// 引入值集接口
		DictionaryService dictionaryService = ContextLoaderListener.getCurrentWebApplicationContext().getBean(DictionaryService.class);
		HashMap<Integer,DictionaryVO> getShipTypeMap = dictionaryService.getShipTypeMap();
		
		Collection<DictionaryVO> values = getShipTypeMap.values();
		
		HashMap<Object, String> map =new HashMap<Object, String>();
		for (DictionaryVO dictionaryVO : values) {
			map.put(dictionaryVO.getCode(), dictionaryVO.getZhName());
		}
		return map;
    }
    
    /**
     * 商品分类：  汽油  柴油  煤油
     * @return
     */
    public static Map<Object,String> getProductOilClassifyMap(){
		// 引入值集接口
		DictionaryService dictionaryService = ContextLoaderListener.getCurrentWebApplicationContext().getBean(DictionaryService.class);
		HashMap<Integer,DictionaryVO> getProductOilClassifyMap = dictionaryService.getProductOilClassifyMap();
		
		Collection<DictionaryVO> values = getProductOilClassifyMap.values();
		
		HashMap<Object, String> map =new HashMap<Object, String>();
		for (DictionaryVO dictionaryVO : values) {
			map.put(dictionaryVO.getCode(), dictionaryVO.getZhName());
		}
		return map;
    }

    /**
     * 出口类型
     * @return
     */
    public static Map<Object, String> getExportType(){
    	// 引入值集接口
		DictionaryService dictionaryService = ContextLoaderListener.getCurrentWebApplicationContext().getBean(DictionaryService.class);
		HashMap<Integer,DictionaryVO> getExportTypeMap = dictionaryService.getExportTypeMap();
		
		Collection<DictionaryVO> values = getExportTypeMap.values();
		
		HashMap<Object, String> map =new HashMap<Object, String>();
		for (DictionaryVO dictionaryVO : values) {
			map.put(dictionaryVO.getCode(), dictionaryVO.getZhName());
		}
		return map;
    }

    /**
     * 成品油地区
     * @return
     */
    public static Map<Object, String> getRegionProductOil(){
    	// 引入值集接口
		DictionaryService dictionaryService = ContextLoaderListener.getCurrentWebApplicationContext().getBean(DictionaryService.class);
		HashMap<Integer,DictionaryVO> getRegionProductOilMap = dictionaryService.getRegionProductOilMap();
		
		Collection<DictionaryVO> values = getRegionProductOilMap.values();
		
		HashMap<Object, String> map =new HashMap<Object, String>();
		for (DictionaryVO dictionaryVO : values) {
			map.put(dictionaryVO.getCode(), dictionaryVO.getZhName());
		}
		return map;
    }

    /**
     * 商检机构
     * @return
     */
    public static Map<Object, String> getBusinessCheckOrg () {
    	// 引入值集接口
		DictionaryService dictionaryService = ContextLoaderListener.getCurrentWebApplicationContext().getBean(DictionaryService.class);
		HashMap<Integer,DictionaryVO> getBusinessCheckOrgMap = dictionaryService.getBusinessCheckOrgMap();
		
		Collection<DictionaryVO> values = getBusinessCheckOrgMap.values();
		
		HashMap<Object, String> map =new HashMap<Object, String>();
		for (DictionaryVO dictionaryVO : values) {
			map.put(dictionaryVO.getCode(), dictionaryVO.getZhName());
		}
		return map;
    }

    /**
     * 计价事件
     * @return
     */
    public static Map<Object, String> getEventPricingType(){
    	// 引入值集接口
		DictionaryService dictionaryService = ContextLoaderListener.getCurrentWebApplicationContext().getBean(DictionaryService.class);
		HashMap<Integer,DictionaryVO> EventPricingTypeMap = dictionaryService.EventPricingTypeMap();
		
		Collection<DictionaryVO> values = EventPricingTypeMap.values();
		
		HashMap<Object, String> map =new HashMap<Object, String>();
		for (DictionaryVO dictionaryVO : values) {
			map.put(dictionaryVO.getCode(), dictionaryVO.getZhName());
		}
		return map;
    }

    /**
     * H/L/C
     * @return
     */
    public static Map<Object, String> getHLCType(){
		// 引入值集接口
		DictionaryService dictionaryService = ContextLoaderListener.getCurrentWebApplicationContext().getBean(DictionaryService.class);
		HashMap<Integer,DictionaryVO> getHLCTypeMap = dictionaryService.getHLCTypeMap();
		
		Collection<DictionaryVO> values = getHLCTypeMap.values();
		
		HashMap<Object, String> map =new HashMap<Object, String>();
		for (DictionaryVO dictionaryVO : values) {
			map.put(dictionaryVO.getCode(), dictionaryVO.getZhName());
		}
		return map;
    }

    //品种
    public static Map<Integer,String> getProductOilKind(){
        Map<Integer,String> map = new HashMap<Integer, String>();
        map.put(1, "新加坡汽油");
        map.put(11, "菲律宾柴油");
        map.put(12, "新加坡柴油");
        return map;
    }
    //规格
    public static Map<Integer,String> getProductOilSpecs(){
        Map<Integer,String> map = new HashMap<Integer, String>();
        //汽油
        map.put(1, "92#");
        //柴油
        map.put(12, "0#");
        return map;
    }

    /**
     * 汽油
     * @return
     */
    public static Map<Integer,String> getGasolineProductOilKind(){
        Map<Integer,String> map = new HashMap<Integer, String>();
        map.put(1, "新加坡汽油");
        return map;
    }
    /**
     * 柴油
     * @return
     */
    public static Map<Integer,String> getDieselProductOilKind(){
        Map<Integer,String> map = new HashMap<Integer, String>();
        map.put(11, "菲律宾柴油");
        map.put(12, "新加坡柴油");
        return map;
    }
    /**
     * 煤油
     * @return
     */
    public static Map<Integer,String> getKeroseneProductOilKind(){
        Map<Integer,String> map = new HashMap<Integer, String>();
        map.put(21, "欧洲煤油");
        map.put(22, "德国煤油");
        map.put(23, "北京煤油");
        return map;
    }
    /**
     * 汽油
     * @return
     */
    public static Map<Integer,String> getGasolineProductOilSpecs(){
        Map<Integer,String> map = new HashMap<Integer, String>();
        map.put(1, "92#");
        return map;
    }
    /**
     * 柴油
     * @return
     */
    public static Map<Integer,String> getDieselProductOilSpecs(){
        Map<Integer,String> map = new HashMap<Integer, String>();
        map.put(12, "0#");
        return map;
    }
    /**
     * 煤油
     * @return
     */
    public static Map<Integer,String> getKeroseneProductOilSpecs(){
        Map<Integer,String> map = new HashMap<Integer, String>();

        map.put(21, "A#");
        map.put(22, "S#");
        map.put(23, "D#");
        map.put(25, "F#");
        map.put(27, "G#");

        return map;
    }
    /*
     * 成品油需求发布时油种和规格
     * @return
     */
    public static Map<String,Map<Integer,String>> getProductOilKindAndSpecs(){
    	Map<String,Map<Integer,String>> map = new HashMap();
    	map.put("gasolineKindMap", DictUtils.getGasolineProductOilKind());
    	map.put("dieselKindMap", DictUtils.getDieselProductOilKind());
    	map.put("keroseneKindMap", DictUtils.getKeroseneProductOilKind());
    	map.put("gasolineSpecsMap", DictUtils.getGasolineProductOilSpecs());
    	map.put("dieselSpecsMap", DictUtils.getDieselProductOilSpecs());
    	map.put("keroseneSpecsMap", DictUtils.getKeroseneProductOilSpecs());

    	return map;
    }

    static Map<String ,Map> dictCache = new HashMap<>();
    static {
        dictCache.put("payItemMap",getPayItemMap());
        dictCache.put("regionProductOil",getRegionProductOil());
        dictCache.put("shipTypes",getShipTypes());
        dictCache.put("creditItem",getCreditItem());
        dictCache.put("valuationProidTypeMap",getValuationProidTypeMap());
        dictCache.put("productOilValuationBaseMap", getProductOilValuationBaseMap());//成品油计价基准
    }
    public static Object getValue(String dictName, String key) {
        if (dictCache.get(dictName) != null && StringUtil.isNotBlank(key)) {
            Object value = dictCache.get(dictName).get(Integer.valueOf(key));
            if(value == null || "".equals(value)){
                value = dictCache.get(dictName).get(key);
            }
            if(value == null){
                return "";
            }
            return value;
        }
        return "";
    }

    public static Map<Object, String> getCacheMap(String dictName) {
        if (dictCache.containsKey(dictName)) return dictCache.get(dictName);
        return null;
    }
}
