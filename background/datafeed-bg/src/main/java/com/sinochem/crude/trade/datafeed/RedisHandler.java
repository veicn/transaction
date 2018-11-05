package com.sinochem.crude.trade.datafeed;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.redisson.api.RMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eyeieye.melody.util.StringUtil;
import com.google.common.base.Strings;
import com.sinochem.crude.trade.common.datafeed.DataFeedUtils;
import com.sinochem.crude.trade.common.utils.DateTimeUtils;
import com.sinochem.crude.trade.datafeed.SymbolContants.SYMBOL_TYPE;
import com.sinochem.crude.trade.info.dao.SysCodeSetMapper;
import com.sinochem.crude.trade.info.domain.FeedClassify;
import com.sinochem.crude.trade.info.domain.MesticProductPrice;
import com.sinochem.crude.trade.info.domain.PriceIndex;
import com.sinochem.crude.trade.info.domain.PriceIndexTemplate;
import com.sinochem.crude.trade.info.domain.SymbolPrice;
import com.sinochem.crude.trade.info.service.FeedClassifyService;
import com.sinochem.crude.trade.info.service.MesticProductPriceService;
import com.sinochem.crude.trade.info.service.PriceIndexService;
import com.sinochem.crude.trade.info.service.PriceIndexTemplateService;
import com.sinochem.crude.trade.info.service.SymbolPriceService;

@Component
public class RedisHandler implements Handler {
	
	@Autowired
	private FeedClassifyService feedClassifyService;
	@Autowired
	private PriceIndexTemplateService priceIndexTemplateService;
	@Autowired
	private PriceIndexService priceIndexService;
	@Autowired
	private MesticProductPriceService mesticProductPriceService;
	@Autowired
	private SysCodeSetMapper sysCodeSetMapper;
	@Autowired
	private SymbolPriceService symbolPriceService;
	
    private  RMap<String, Map<String, String>> map = DataFeedUtils.map;
    /*期货模板*/
    private  Map<String, String> forwardMapStatic = ModelMap.forwardMapStatic;
    /*航煤出厂价*/
    private  Map<String, String> jutFuelPriceMapStatic = ModelMap.jutFuelPriceMapStatic;
    /*美元汇率*/
    private  Map<String, String> dollarRateMapStatic = ModelMap.dollarRateMapStatic;
    /*汽油、苯*/
    private  Map<String, String> oilOrBenzolMapStatic = ModelMap.oilOrBenzolMapStatic;
    /*石脑油*/
    private  Map<String, String> naphthaMapStatic = ModelMap.naphthaMapStatic;
    /*船用燃料油、二乙二醇*/
    private  Map<String, String> shipFuelOilMapStatic = ModelMap.shipFuelOilMapStatic;
    /*液化气、石蜡、丙烯、丙烷*/
    private  Map<String, String> liquefiedGasMapStatic = ModelMap.liquefiedGasMapStatic;
    /*人民币远期汇率*/
    private  Map<String, String> RMBExchangeRateMapStatic = ModelMap.RMBExchangeRateMapStatic;
    /*92#汽油、95#分省价格  英文、0#柴油国内市场估价*/
    private  Map<String, String> oil92ENMapStatic = ModelMap.oil92ENMapStatic;
    /*95#国内市场批发报价、0#柴油东北、华北、西北报价、0#柴油华中华东、0#柴油华南、西南*/
    private  Map<String, String> oil95ENMapStatic = ModelMap.oil95ENMapStatic;
    /*山东地炼汽油市场报价、其他地炼汽油市场报价、山东地炼市场柴油1,2、其他地炼市场柴油*/
    private  Map<String, String> oilSDENMapStatic = ModelMap.oilSDENMapStatic;
    /*国际市场_柴油现货*/
    private  Map<String, String> dieselSpotENMapStatic = ModelMap.dieselSpotENMapStatic;
    /*国际市场_汽油现货*/
    private  Map<String, String> gasSpotENMapStatic = ModelMap.gasSpotENMapStatic;

    private static final Log log = LogFactory.getLog(RedisHandler.class);
    @Override
    public void handle(String data) {
    	if (Strings.isNullOrEmpty(data)) {
    		return;
    	}
        Feed feed = new Feed();
        feed.parseValue(data);
        
        
        String id = feed.getId();
        
        if(id!=null && id!=""){
	        //查询行情分类
	    	FeedClassify feedClassify = feedClassifyService.getQuotationClass(id);
	    	//模板转换
	    	Map<String,String> newMap = this.zhFeed(id,feed,feedClassify);
	        
	        //将转换过的map放入redis
	        if (!feed.isUpdate()) {
	            map.fastPut(feed.getId(), newMap);
	        } else {
	            if (map.containsKey(feed.getId())) {
	                Map<String, String> originMap = map.get(feed.getId());
	                originMap.putAll(feed.getValueMap());
	                map.fastPut(feed.getId(), originMap);
	            }
	        }
	        if(feedClassify == null){
	    		log.error(id+"行情分类不存在");
	    		return;
	    	}
	        //入库
	        if(StringUtils.equals(feedClassify.getLangVer(), "zh")){
	        	if(StringUtils.equals(feedClassify.getQuotationClassify(), "dollarRate")){
	        		//美元指数入库
	        		saveDollarRate(newMap,id);
	        	}else if(StringUtils.equals(feedClassify.getQuotationClassify(), "jutFuelPrice")){
	        		//航煤出厂价入库
	        		saveJutFuelPrice(newMap,feedClassify,feed,id);
	        	}else if(StringUtils.equals(feedClassify.getQuotationClassify(), "oilOrBenzol") 
	        			|| StringUtils.equals(feedClassify.getQuotationClassify(), "naphtha")
	        			|| StringUtils.equals(feedClassify.getQuotationClassify(), "shipFuelOil")
	        			|| StringUtils.equals(feedClassify.getQuotationClassify(), "liquefiedGas")){
	        		//汽油、石脑油、苯入库
	        		saveShipFuelOil(newMap,feedClassify,feed,id);
	        	}else if(StringUtils.equals(feedClassify.getQuotationClassify(), "forward")){
	        		//WTI、ICE Brent、DME指数入库
	        		savePriceIndex(newMap,id,feed);
	        	}else if(StringUtils.equals(feedClassify.getQuotationClassify(), "RMBExchangeRate")){
	        		//人民币远期汇率入库
	        		saveSymbolPrice(newMap,id,feed);
	        	}
	        }else{
	        	if(StringUtils.equals(feedClassify.getQuotationClassify(), "oil92EN")
	        			|| StringUtils.equals(feedClassify.getQuotationClassify(), "oil95EN")
	        			|| StringUtils.equals(feedClassify.getQuotationClassify(), "oilSDEN")
	        			|| StringUtils.equals(feedClassify.getQuotationClassify(), "dieselSpotEN")	
	        			|| StringUtils.equals(feedClassify.getQuotationClassify(), "gasSpotEN")	
	        			){
	        		saveDomesticEn(newMap,feedClassify,feed,id);
	        	}
	        }
        }
    }
   
	/*
     * 模板转换
     * */
    private Map<String,String> zhFeed(String id,Feed feed,FeedClassify feedClassify){
    	Map<String,String> newMap=new HashMap<>();
	
    	Map<String, String> valueMap = feed.getValueMap();
    	
    	if(feedClassify == null){
    		log.error(id+"行情分类不存在");
    		return valueMap;
    	}
        Set<String> keySet = valueMap.keySet();
        //模板map转换
        if(StringUtils.equals(feedClassify.getQuotationClassify(), "forward")){
        	for (String key : keySet) {
        		if(!com.sinochem.crude.trade.common.utils.StringUtils.isEmpty(forwardMapStatic.get(key))){
        			newMap.put(forwardMapStatic.get(key), valueMap.get(key));
        		}
        	}
        }else if(StringUtils.equals(feedClassify.getQuotationClassify(), "jutFuelPrice")){
        	for (String key : keySet) {
        		if(!com.sinochem.crude.trade.common.utils.StringUtils.isEmpty(jutFuelPriceMapStatic.get(key))){
        			newMap.put(jutFuelPriceMapStatic.get(key), valueMap.get(key));
        		}
        	}
        }else if(StringUtils.equals(feedClassify.getQuotationClassify(), "dollarRate")){
        	for (String key : keySet) {
        		if(!com.sinochem.crude.trade.common.utils.StringUtils.isEmpty(dollarRateMapStatic.get(key))){
        			newMap.put(dollarRateMapStatic.get(key), valueMap.get(key));
        		}
        	}
        }else if(StringUtils.equals(feedClassify.getQuotationClassify(), "oilOrBenzol")){
        	for (String key : keySet) {
        		if(!com.sinochem.crude.trade.common.utils.StringUtils.isEmpty(oilOrBenzolMapStatic.get(key))){
        			newMap.put(oilOrBenzolMapStatic.get(key), valueMap.get(key));
        		}
        	}
        }else if(StringUtils.equals(feedClassify.getQuotationClassify(), "naphtha")){
        	for (String key : keySet) {
        		if(!com.sinochem.crude.trade.common.utils.StringUtils.isEmpty(naphthaMapStatic.get(key))){
        			newMap.put(naphthaMapStatic.get(key), valueMap.get(key));
        		}
        	}
        }else if(StringUtils.equals(feedClassify.getQuotationClassify(), "shipFuelOil")){
        	for (String key : keySet) {
        		if(!com.sinochem.crude.trade.common.utils.StringUtils.isEmpty(shipFuelOilMapStatic.get(key))){
        			newMap.put(shipFuelOilMapStatic.get(key), valueMap.get(key));
        		}
        	}
        }else if(StringUtils.equals(feedClassify.getQuotationClassify(), "liquefiedGas")){
        	for (String key : keySet) {
        		if(!com.sinochem.crude.trade.common.utils.StringUtils.isEmpty(liquefiedGasMapStatic.get(key))){
        			newMap.put(liquefiedGasMapStatic.get(key), valueMap.get(key));
        		}
        	}
        }else if(StringUtils.equals(feedClassify.getQuotationClassify(), "RMBExchangeRate")){
        	for (String key : keySet) {
        		if(!com.sinochem.crude.trade.common.utils.StringUtils.isEmpty(RMBExchangeRateMapStatic.get(key))){
        			newMap.put(RMBExchangeRateMapStatic.get(key), valueMap.get(key));
        		}
        	}
        }else if(StringUtils.equals(feedClassify.getQuotationClassify(), "oil92EN")){
    		for (String key : keySet) {
    			if(!com.sinochem.crude.trade.common.utils.StringUtils.isEmpty(oil92ENMapStatic.get(key))){
    				newMap.put(oil92ENMapStatic.get(key), valueMap.get(key));
    			}
    		}
    	}else if(StringUtils.equals(feedClassify.getQuotationClassify(), "oil95EN")){
    		for (String key : keySet) {
    			if(!com.sinochem.crude.trade.common.utils.StringUtils.isEmpty(oil95ENMapStatic.get(key))){
    				newMap.put(oil95ENMapStatic.get(key), valueMap.get(key));
    			}
    		}
    	}else if(StringUtils.equals(feedClassify.getQuotationClassify(), "oilSDEN")){
    		for (String key : keySet) {
    			if(!com.sinochem.crude.trade.common.utils.StringUtils.isEmpty(oilSDENMapStatic.get(key))){
    				newMap.put(oilSDENMapStatic.get(key), valueMap.get(key));
    			}
    		}
    	}else if(StringUtils.equals(feedClassify.getQuotationClassify(), "dieselSpotEN")){
    		for (String key : keySet) {
    			if(!com.sinochem.crude.trade.common.utils.StringUtils.isEmpty(dieselSpotENMapStatic.get(key))){
    				newMap.put(dieselSpotENMapStatic.get(key), valueMap.get(key));
    			}
    		}
    	}else if(StringUtils.equals(feedClassify.getQuotationClassify(), "gasSpotEN")){
    		for (String key : keySet) {
    			if(!com.sinochem.crude.trade.common.utils.StringUtils.isEmpty(gasSpotENMapStatic.get(key))){
    				newMap.put(gasSpotENMapStatic.get(key), valueMap.get(key));
    			}
    		}
    	}
        return newMap;
    }
    /*
     * 人民币远期汇率入库
     * */
    private void saveSymbolPrice(Map<String, String> newMap, String id, Feed feed) {
    	String symbolCode = getSymbolCode(id);
    	String symbolName = getSymbolName(id);
		SymbolPrice symbolPrice=new SymbolPrice();
		SimpleDateFormat sdf= new SimpleDateFormat("ddMMMyy", Locale.ENGLISH);
		try {
			if(StringUtil.isNotBlank(newMap.get("date"))){
				symbolPrice.setTradeDate(sdf.parse(newMap.get("date")));
			}else{
				log.error(id+"远期汇率日期为空");
				return;
			}
		} catch (ParseException e) {
			log.error("远期汇率日期格式错误");
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		symbolPrice.setSymbol(symbolCode);
		symbolPrice.setSymbolName(symbolName);
		List<SymbolPrice> queryByEntitys = symbolPriceService.queryByEntitys(symbolPrice);
		if(StringUtil.isNotBlank(newMap.get("highPrice"))){
			symbolPrice.setHighPrice(new BigDecimal(newMap.get("highPrice")));
		}
		if(StringUtil.isNotBlank(newMap.get("lowPrice"))){
			symbolPrice.setLowPrice(new BigDecimal(newMap.get("lowPrice")));
		}
		if(StringUtil.isNotBlank(newMap.get("middlePrice"))){
			symbolPrice.setSettlementPrice(new BigDecimal(newMap.get("middlePrice")));
		}
		if(CollectionUtils.isEmpty(queryByEntitys)) {
			symbolPriceService.insertRecord(symbolPrice);
		}else if(feed.isUpdate()){
			symbolPrice.setId(queryByEntitys.get(0).getId());
			symbolPriceService.updateRecordById(symbolPrice);
		}
		
	}
	/*
     * 航煤出厂价入库
     * */
    public void saveJutFuelPrice(Map<String,String> newMap,FeedClassify feedClassify,Feed feed,String id){
    	//航煤出厂价
    	MesticProductPrice mesticproductprice=new MesticProductPrice();
    	mesticproductprice.setAreaName(newMap.get("area"));
    	mesticproductprice.setPriceSource(newMap.get("area"));
    	mesticproductprice.setProductName(feedClassify.getExt1());
    	//根据产品名称查询code
    	String productCode= sysCodeSetMapper.queryCodeByProductName(feedClassify.getExt1());
    	if(productCode==null || productCode==""){
    		log.info(feedClassify.getExt1()+" code查询失败");
    		return;
    	}
    	mesticproductprice.setProductCode(productCode);
    	mesticproductprice.setPriceUnit("元/吨");
    	mesticproductprice.setExtend2(feedClassify.getQuotationId());
    	if(newMap.get("date")!=null && newMap.get("date")!=""){
    		mesticproductprice.setPriceDate(DateTimeUtils.toDate(newMap.get("date"), "yyMMdd"));
    	}
    	if(newMap.get("todayPrice")!=null && newMap.get("todayPrice")!=""){
    		//判断是否todayPrice为纯数字
    		if(org.apache.commons.lang.StringUtils.isNumeric(newMap.get("todayPrice"))){
    			mesticproductprice.setHighPrice(new BigDecimal(newMap.get("todayPrice")));
    			mesticproductprice.setLowPrice(new BigDecimal(newMap.get("todayPrice")));
    		//判断是否todayPrice包含两个数字
    		}else if(newMap.get("todayPrice").indexOf("-")!=-1){
    			String[] todayPrices = newMap.get("todayPrice").split("-");
    			mesticproductprice.setHighPrice(new BigDecimal(todayPrices[0]));
    			mesticproductprice.setLowPrice(new BigDecimal(todayPrices[1]));
    		}else{
    			log.info(feedClassify.getQuotationId()+" 当日价为空");
        		return;
    		}         		
    	}else{
    		log.info(feedClassify.getQuotationId()+" 当日价为空");
    		return;
    	}
    	if(newMap.get("yesterdayPrice")!=null && newMap.get("yesterdayPrice")!=""){
    		//判断是否yesterdayPrice为纯数字
    		if(org.apache.commons.lang.StringUtils.isNumeric(newMap.get("yesterdayPrice"))){
    			mesticproductprice.setPreHighPrice(new BigDecimal(newMap.get("yesterdayPrice")));
    			mesticproductprice.setPreLowPrice(new BigDecimal(newMap.get("yesterdayPrice")));
    		//判断是否yesterdayPrice包含两个数字	
    		}else if(newMap.get("yesterdayPrice").indexOf("-")!=-1){
    			String[] yesterdayPrices = newMap.get("yesterdayPrice").split("-");
    			mesticproductprice.setPreHighPrice(new BigDecimal(yesterdayPrices[0]));
    			mesticproductprice.setPreLowPrice(new BigDecimal(yesterdayPrices[1]));
    		}           		
    	}
    	if(newMap.get("upDown")!=null && newMap.get("upDown")!=""){
    		//判断是否upDown为纯数字
    		if(org.apache.commons.lang.StringUtils.isNumeric(newMap.get("upDown"))){
    			mesticproductprice.setHighPriceChange(new BigDecimal(newMap.get("upDown")));
    			mesticproductprice.setLowPriceChange(new BigDecimal(newMap.get("upDown")));
    			if(Double.parseDouble(newMap.get("upDown"))>0){
        			mesticproductprice.setPriceTrend("1");
        		}else if(Double.parseDouble(newMap.get("upDown"))<0){
        			mesticproductprice.setPriceTrend("-1");
        		}else{
        			mesticproductprice.setPriceTrend("0");
        		}
    		//判断是否upDown包含两个数字
    		}else if(newMap.get("upDown").indexOf("/")!=-1){
    			String[] upDowns = newMap.get("upDown").split("/");
    			mesticproductprice.setHighPriceChange(new BigDecimal(upDowns[0]));
    			mesticproductprice.setLowPriceChange(new BigDecimal(upDowns[1]));
    			if(Double.parseDouble(upDowns[0])>0){
        			mesticproductprice.setPriceTrend("1");
        		}else if(Double.parseDouble(upDowns[0])<0){
        			mesticproductprice.setPriceTrend("-1");
        		}else{
        			mesticproductprice.setPriceTrend("0");
        		}
    		}else{
    			mesticproductprice.setPriceTrend("0");
    		}
    	}else{
    		mesticproductprice.setPriceTrend("0");
    	}
    	mesticproductprice.setProductName(feedClassify.getExt1());
    	mesticproductprice.setExtend1(newMap.get("shopSign"));
    	mesticproductprice.setExtend10(feedClassify.getLangVer());
    	List<MesticProductPrice> keyFlag = mesticProductPriceService.findTotal(mesticproductprice);
		if(keyFlag.size() == 0) {
			mesticProductPriceService.insertRecord(mesticproductprice);
			log.info(id+"新增厂家报价成功");
		}else if(feed.isUpdate()){
			mesticproductprice.setId(keyFlag.get(0).getId());
			mesticProductPriceService.updateRecordById(mesticproductprice);
			log.info(id+"修改厂家报价成功");
		}
    }
    /*
     * 汽油、石脑油、苯入库
     * */
    public void saveShipFuelOil(Map<String,String> newMap,FeedClassify feedClassify,Feed feed,String id){
    	// 汽油、石脑油、苯
    	MesticProductPrice mesticproductprice=new MesticProductPrice();
    	if(StringUtils.equals(feedClassify.getQuotationClassify(), "oilOrBenzol")){
    		mesticproductprice.setAreaName(newMap.get("area"));
        	mesticproductprice.setPriceSource(newMap.get("companyName"));
    	}else if(StringUtils.equals(feedClassify.getQuotationClassify(), "naphtha")){
    		mesticproductprice.setAreaName(newMap.get("area"));
        	mesticproductprice.setPriceSource(newMap.get("area"));
    	}else if(StringUtils.equals(feedClassify.getQuotationClassify(), "shipFuelOil")){
    		mesticproductprice.setExtend1(newMap.get("typeName"));
    		mesticproductprice.setExtend3(newMap.get("port"));
    		mesticproductprice.setExtend4(newMap.get("dealWay"));
    		mesticproductprice.setAreaName(newMap.get("port"));
    	}else if(StringUtils.equals(feedClassify.getQuotationClassify(), "liquefiedGas")){
    		mesticproductprice.setPriceSource(newMap.get("companyName"));
    		mesticproductprice.setExtend4(newMap.get("dealWay"));
    	}
    	mesticproductprice.setProductName(feedClassify.getExt1());
    	//根据产品名称查询code
    	String productCode= sysCodeSetMapper.queryCodeByProductName(feedClassify.getExt1());
    	if(productCode==null || productCode==""){
    		log.info(feedClassify.getExt1()+" code查询失败");
    		return;
    	}
    	mesticproductprice.setProductCode(productCode);
    	mesticproductprice.setPriceUnit("元/吨");
    	mesticproductprice.setExtend2(feedClassify.getQuotationId());
    	if(newMap.get("date")!=null && newMap.get("date")!=""){
    		mesticproductprice.setPriceDate(DateTimeUtils.toDate(newMap.get("date"), "yyMMdd"));
    	}
    	if(newMap.get("todayPrice")!=null && newMap.get("todayPrice")!=""){
    		//判断是否todayPrice为纯数字
    		if(org.apache.commons.lang.StringUtils.isNumeric(newMap.get("todayPrice"))){
    			mesticproductprice.setHighPrice(new BigDecimal(newMap.get("todayPrice")));
    			mesticproductprice.setLowPrice(new BigDecimal(newMap.get("todayPrice")));
    		//判断是否todayPrice包含两个数字
    		}else if(newMap.get("todayPrice").indexOf("-")!=-1){
    			String[] todayPrices = newMap.get("todayPrice").split("-");
    			mesticproductprice.setHighPrice(new BigDecimal(todayPrices[0]));
    			mesticproductprice.setLowPrice(new BigDecimal(todayPrices[1]));
    		}else{
    			log.info(feedClassify.getQuotationId()+" 当日价为空");
        		return;
    		}            		
    	}else{
    		log.info(feedClassify.getQuotationId()+" 当日价为空");
    		return;
    	}
    	if(newMap.get("yesterdayPrice")!=null && newMap.get("yesterdayPrice")!=""){
    		//判断是否yesterdayPrice为纯数字
    		if(org.apache.commons.lang.StringUtils.isNumeric(newMap.get("yesterdayPrice"))){
    			mesticproductprice.setPreHighPrice(new BigDecimal(newMap.get("yesterdayPrice")));
    			mesticproductprice.setPreLowPrice(new BigDecimal(newMap.get("yesterdayPrice")));
    		//判断是否yesterdayPrice包含两个数字	
    		}else if(newMap.get("yesterdayPrice").indexOf("-")!=-1){
    			String[] yesterdayPrices = newMap.get("yesterdayPrice").split("-");
    			mesticproductprice.setPreHighPrice(new BigDecimal(yesterdayPrices[0]));
    			mesticproductprice.setPreLowPrice(new BigDecimal(yesterdayPrices[1]));
    		}           		
    	}
    	if(newMap.get("upDown")!=null && newMap.get("upDown")!=""){
    		//判断是否upDown为纯数字
    		if(org.apache.commons.lang.StringUtils.isNumeric(newMap.get("upDown"))){
    			mesticproductprice.setHighPriceChange(new BigDecimal(newMap.get("upDown")));
    			mesticproductprice.setLowPriceChange(new BigDecimal(newMap.get("upDown")));
    			if(Double.parseDouble(newMap.get("upDown"))>0){
        			mesticproductprice.setPriceTrend("1");
        		}else if(Double.parseDouble(newMap.get("upDown"))<0){
        			mesticproductprice.setPriceTrend("-1");
        		}else{
        			mesticproductprice.setPriceTrend("0");
        		}
    		//判断是否upDown包含两个数字
    		}else if(newMap.get("upDown").indexOf("/")!=-1){
    			String[] upDowns = newMap.get("upDown").split("/");
    			mesticproductprice.setHighPriceChange(new BigDecimal(upDowns[0]));
    			mesticproductprice.setLowPriceChange(new BigDecimal(upDowns[1]));
    			if(Double.parseDouble(upDowns[0])>0){
        			mesticproductprice.setPriceTrend("1");
        		}else if(Double.parseDouble(upDowns[0])<0){
        			mesticproductprice.setPriceTrend("-1");
        		}else{
        			mesticproductprice.setPriceTrend("0");
        		}
    		}else{
    			mesticproductprice.setPriceTrend("0");
    		}
    	}else{
    		mesticproductprice.setPriceTrend("0");
    	}
    	mesticproductprice.setExtend1(newMap.get("shopSign"));
    	mesticproductprice.setExtend10(feedClassify.getLangVer());
    	List<MesticProductPrice> keyFlag = mesticProductPriceService.findTotal(mesticproductprice);
		if(keyFlag.size() == 0) {
			mesticProductPriceService.insertRecord(mesticproductprice);
			log.info(id+"新增厂家报价成功");
		}else if(feed.isUpdate()){
			mesticproductprice.setId(keyFlag.get(0).getId());
			mesticProductPriceService.updateRecordById(mesticproductprice);
			log.info(id+"修改厂家报价成功");
		}
    }
    /*
     * 美元指数入库
     * */
    public void saveDollarRate(Map<String,String> newMap,String id){
    	if(newMap.get("todayPrice") == null || newMap.get("todayPrice") == ""){
    		log.info(id+" 指数价格为空");
    		return;
    	}
    	PriceIndex priceIndex = new PriceIndex();
    	PriceIndexTemplate ptl =  priceIndexTemplateService.findBycrudeCode("Dollar");
		try {
			//设置模板id、指数值、指数日期
			priceIndex.setIndexTemplateId(ptl.getId());
			SimpleDateFormat sdf= new SimpleDateFormat("ddMMMyy", Locale.ENGLISH);
			if(StringUtil.isNotBlank(newMap.get("date"))){
				priceIndex.setIndexDate(sdf.parse(newMap.get("date")));
			}else{
				log.error(id+"指数日期为空");
				return;
			}
		} catch (ParseException e) {
			log.error("指数日期格式错误");
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		List<PriceIndex> queryRecords = priceIndexService.queryByEntitys(priceIndex);
		if(!CollectionUtils.isEmpty(queryRecords)){
			priceIndex.setUuid(queryRecords.get(0).getUuid());
		}
		priceIndex.setSmeiValue(new BigDecimal(newMap.get("todayPrice")));
		
		//找到上一天的记录,计算涨跌、涨跌幅
		Map<String, Object> piPreMap = new HashMap<>();
		piPreMap.put("indexTemplateId", ptl.getId());
		piPreMap.put("indexDate", priceIndex.getIndexDate());
		PriceIndex piPre = priceIndexService.findPreRecordByTempId(piPreMap);
		if (piPre!=null) {
			BigDecimal riseFall =  priceIndex.getSmeiValue().subtract(piPre.getSmeiValue());
			priceIndex.setRiseFall(riseFall);
			
			if(piPre.getSmeiValue().compareTo(BigDecimal.ZERO)!=0){
				BigDecimal riseFallRate =  riseFall.divide(piPre.getSmeiValue(), 4);
				priceIndex.setRiseFallRate(riseFallRate);
			}
		}
		priceIndexService.saveOrUpdatePriceIndex(priceIndex, null);
		log.info(id+"新增或更新指数成功");
    }
    /*
     * WTI、ICE Brent、DME指数入库
     * */
    public void savePriceIndex(Map<String,String> map,String id,Feed feed){
    	PriceIndex priceIndex = new PriceIndex();
    	PriceIndexTemplate ptl=new PriceIndexTemplate();
    	if(id.equals("2670")){
    		ptl =  priceIndexTemplateService.findBycrudeCode("WTI");
    	}else if(id.equals("2664")){
    		ptl =  priceIndexTemplateService.findBycrudeCode("ICE Brent");
    	}else if(id.equals("2566")){
    		ptl =  priceIndexTemplateService.findBycrudeCode("DME");
    	}else{
    		return;
    	}
    	if(StringUtil.isBlank(map.get("settlement"))){
    		log.info(id+"结算价为空");
    		return;
    	}
    	if(feed.isUpdate()){
    		priceIndex=priceIndexService.findNewestPrice(ptl.getId());
    	}else{
    		try {
    			//设置模板id、指数值、指数日期
    			priceIndex.setIndexTemplateId(ptl.getId());
    			SimpleDateFormat sdf= new SimpleDateFormat("ddMMMyy", Locale.ENGLISH);
    			if(StringUtil.isNotBlank(map.get("date"))){
    				priceIndex.setIndexDate(sdf.parse(map.get("date")));
    			}else{
    				log.error(id+"指数日期为空");
    				return;
    			}
    		} catch (ParseException e) {
    			log.error("指数日期格式错误");
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    			return;
    		}
    	}
		List<PriceIndex> queryRecords = priceIndexService.queryByEntitys(priceIndex);
		if(!CollectionUtils.isEmpty(queryRecords)){
			priceIndex.setUuid(queryRecords.get(0).getUuid());
		}
		priceIndex.setSmeiValue(new BigDecimal(map.get("settlement")));
		
		//找到上一天的记录,计算涨跌、涨跌幅
		Map<String, Object> piPreMap = new HashMap<>();
		piPreMap.put("indexTemplateId", ptl.getId());
		piPreMap.put("indexDate", priceIndex.getIndexDate());
		PriceIndex piPre = priceIndexService.findPreRecordByTempId(piPreMap);
		if (piPre!=null) {
			BigDecimal riseFall =  priceIndex.getSmeiValue().subtract(piPre.getSmeiValue());
			priceIndex.setRiseFall(riseFall);
			
			if(piPre.getSmeiValue().compareTo(BigDecimal.ZERO)!=0){
				BigDecimal riseFallRate =  riseFall.divide(piPre.getSmeiValue(), 4);
				priceIndex.setRiseFallRate(riseFallRate);
			}
		}
		priceIndexService.saveOrUpdatePriceIndex(priceIndex, null);
		log.info(id+"新增或更新指数成功");
    }
    /*
     * 英文报价入库
     * */
    private void saveDomesticEn(Map<String, String> newMap, FeedClassify feedClassify, Feed feed, String id) {
    	MesticProductPrice mesticproductprice=new MesticProductPrice();
    	SimpleDateFormat sdf= new SimpleDateFormat("ddMMMyy", Locale.ENGLISH);
    	if(StringUtils.equals(feedClassify.getQuotationClassify(), "oil92EN")){
    		mesticproductprice.setAreaName(newMap.get("province"));
    		mesticproductprice.setPriceSource(newMap.get("province"));
    		mesticproductprice.setPriceUnit("yuan/ton");
    	}else if(StringUtils.equals(feedClassify.getQuotationClassify(), "oil95EN")){
    		mesticproductprice.setExtend1(newMap.get("grade"));
    		mesticproductprice.setAreaName(newMap.get("region"));
        	mesticproductprice.setPriceSource(newMap.get("companyName"));
        	mesticproductprice.setPriceUnit("yuan/ton");
    	}else if(StringUtils.equals(feedClassify.getQuotationClassify(), "oilSDEN")){
    		mesticproductprice.setExtend1(newMap.get("grade"));
        	mesticproductprice.setPriceSource(newMap.get("companyName"));
        	mesticproductprice.setPriceUnit("yuan/ton");
    	}else if(StringUtils.equals(feedClassify.getQuotationClassify(), "dieselSpotEN")){
    		mesticproductprice.setExtend1(newMap.get("name"));
    		mesticproductprice.setPriceUnit(newMap.get("currency"));
    	}else if(StringUtils.equals(feedClassify.getQuotationClassify(), "gasSpotEN")){
    		mesticproductprice.setExtend3(newMap.get("lacation"));
    		mesticproductprice.setExtend1(newMap.get("name"));
    		mesticproductprice.setExtend4(newMap.get("transportation"));
    		mesticproductprice.setPriceUnit(newMap.get("currency"));
    	}
    	if(StringUtils.equals(feedClassify.getQuotationClassify(), "gasSpotEN")
    			||StringUtils.equals(feedClassify.getQuotationClassify(), "dieselSpotEN")){
    		try {
    			if(StringUtil.isNotBlank(newMap.get("date"))){
    				mesticproductprice.setPriceDate(sdf.parse(newMap.get("date")));
    			}else{
    				log.error(id+"英文报价日期为空");
    				return;
    			}
    		} catch (ParseException e) {
    			log.error("英文报价日期格式错误");
    			e.printStackTrace();
    			return;
    		}
    	}else{
    		if(newMap.get("date")!=null && newMap.get("date")!=""){
        		mesticproductprice.setPriceDate(DateTimeUtils.toDate(newMap.get("date"), "yyMMdd"));
        	}
    	}
    	mesticproductprice.setProductName(feedClassify.getExt1());
    	//根据产品名称查询code
    	String productCode= sysCodeSetMapper.queryCodeByProductName(feedClassify.getExt1());
    	if(productCode==null || productCode==""){
    		log.info(feedClassify.getExt1()+" code查询失败");
    		return;
    	}
    	mesticproductprice.setProductCode(productCode);
    	mesticproductprice.setExtend2(feedClassify.getQuotationId());
    	if(newMap.get("today")!=null && newMap.get("today")!=""){
    		//判断是否todayPrice为纯数字
    		if(isNumeric(newMap.get("today"))){
    			mesticproductprice.setHighPrice(new BigDecimal(newMap.get("today")));
    			mesticproductprice.setLowPrice(new BigDecimal(newMap.get("today")));
    		//判断是否todayPrice包含两个数字
    		}else if(newMap.get("today").indexOf("-")!=-1){
    			String[] todayPrices = newMap.get("today").split("-");
    			mesticproductprice.setHighPrice(new BigDecimal(todayPrices[0]));
    			mesticproductprice.setLowPrice(new BigDecimal(todayPrices[1]));
    		}else{
    			log.info(feedClassify.getQuotationId()+" 当日价为空");
        		return;
    		}            		
    	}else{
    		log.info(feedClassify.getQuotationId()+" 当日价为空");
    		return;
    	}
    	if(newMap.get("previous")!=null && newMap.get("previous")!=""){
    		//判断是否yesterdayPrice为纯数字
    		if(isNumeric(newMap.get("previous"))){
    			mesticproductprice.setPreHighPrice(new BigDecimal(newMap.get("previous")));
    			mesticproductprice.setPreLowPrice(new BigDecimal(newMap.get("previous")));
    		//判断是否yesterdayPrice包含两个数字	
    		}else if(newMap.get("previous").indexOf("-")!=-1){
    			String[] yesterdayPrices = newMap.get("previous").split("-");
    			mesticproductprice.setPreHighPrice(new BigDecimal(yesterdayPrices[0]));
    			mesticproductprice.setPreLowPrice(new BigDecimal(yesterdayPrices[1]));
    		}           		
    	}
    	if(newMap.get("change")!=null && newMap.get("change")!=""){
    		//判断是否upDown为纯数字
    		if(isNumeric(newMap.get("change"))){
    			mesticproductprice.setHighPriceChange(new BigDecimal(newMap.get("change")));
    			mesticproductprice.setLowPriceChange(new BigDecimal(newMap.get("change")));
    			if(Double.parseDouble(newMap.get("change"))>0){
        			mesticproductprice.setPriceTrend("1");
        		}else if(Double.parseDouble(newMap.get("change"))<0){
        			mesticproductprice.setPriceTrend("-1");
        		}else{
        			mesticproductprice.setPriceTrend("0");
        		}
    		//判断是否upDown包含两个数字
    		}else if(newMap.get("change").indexOf("/")!=-1){
    			String[] upDowns = newMap.get("change").split("/");
    			mesticproductprice.setHighPriceChange(new BigDecimal(upDowns[0]));
    			mesticproductprice.setLowPriceChange(new BigDecimal(upDowns[1]));
    			if(Double.parseDouble(upDowns[0])>0){
        			mesticproductprice.setPriceTrend("1");
        		}else if(Double.parseDouble(upDowns[0])<0){
        			mesticproductprice.setPriceTrend("-1");
        		}else{
        			mesticproductprice.setPriceTrend("0");
        		}
    		}else{
    			mesticproductprice.setPriceTrend("0");
    		}
    	}else{
    		mesticproductprice.setPriceTrend("0");
    	}
    	mesticproductprice.setExtend10(feedClassify.getLangVer());
    	List<MesticProductPrice> keyFlag = mesticProductPriceService.findTotal(mesticproductprice);
		if(keyFlag.size() == 0) {
			mesticProductPriceService.insertRecord(mesticproductprice);
			log.info(id+"新增厂家报价成功");
		}else if(feed.isUpdate()){
			mesticproductprice.setId(keyFlag.get(0).getId());
			mesticProductPriceService.updateRecordById(mesticproductprice);
			log.info(id+"修改厂家报价成功");
		}
	}
    @Override
    public Set<String> listId() {
        return map.keySet();
    }

    @Override
    public Map<String, String> getItem(String id) {
        return map.get(id);
    }
    private String getSymbolCode(String id) {
		for(SYMBOL_TYPE item : SymbolContants.SYMBOL_TYPE.values()) {
			if(item.getId().equals(id)) {
				return item.getSymbolCode();
			}
		}
		return null;
	}
    private String getSymbolName(String id) {
    	for(SYMBOL_TYPE item : SymbolContants.SYMBOL_TYPE.values()) {
    		if(item.getId().equals(id)) {
    			return item.getSymbolName();
    		}
    	}
    	return null;
    }
    public static boolean isNumeric(String str){
    	try {
			Double.parseDouble(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
    }
}
