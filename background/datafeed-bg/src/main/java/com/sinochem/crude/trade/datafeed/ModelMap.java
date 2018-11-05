package com.sinochem.crude.trade.datafeed;

import java.util.Map;

import com.google.common.collect.Maps;


public class ModelMap {
	
	/*期货模板*/
    public static Map<String, String> forwardMapStatic = Maps.newHashMap();
    
    /*航煤出厂价*/
    public static Map<String, String> jutFuelPriceMapStatic = Maps.newHashMap();
    
    /*美元汇率*/
    public static Map<String, String> dollarRateMapStatic = Maps.newHashMap();
    
    /*汽油、苯、石油焦、白油、对二甲苯、乙二醇、甲苯、C5、硫磺、丁二烯、MTBE、二甲苯、聚丙烯粒料/粉料*/
    public static Map<String, String> oilOrBenzolMapStatic = Maps.newHashMap();
    
    /*石脑油*/
    public static Map<String, String> naphthaMapStatic = Maps.newHashMap();
    
    /*船用燃料油、二乙二醇*/
    public static Map<String, String> shipFuelOilMapStatic = Maps.newHashMap();
    
    /*液化气、石蜡、丙烯、丙烷*/
    public static Map<String, String> liquefiedGasMapStatic = Maps.newHashMap();
    
    /*人民币远期汇率*/
    public static Map<String, String> RMBExchangeRateMapStatic = Maps.newHashMap();
    
    
    static {
    	
    	forwardMapStatic.put("1", "code"); // 代码
    	forwardMapStatic.put("2", "typeName");// 品种名
    	forwardMapStatic.put("3", "lastPrice"); // 最近价
    	forwardMapStatic.put("5", "upDown"); // 升跌
    	forwardMapStatic.put("6", "highPrice"); // 最高价
    	forwardMapStatic.put("7", "lowPrice"); // 最低价
    	forwardMapStatic.put("9", "time"); // 时间
    	forwardMapStatic.put("12", "buyPrice"); // 叫买
    	forwardMapStatic.put("13", "monthError"); // 月差
    	forwardMapStatic.put("15", "sellPrice"); // 叫卖
    	forwardMapStatic.put("16", "endDate"); // 到期日
    	forwardMapStatic.put("18", "buyQuantity"); // 买量
    	forwardMapStatic.put("19", "sellQuantity"); // 卖量
    	forwardMapStatic.put("20", "delivery"); // 成交量
    	forwardMapStatic.put("21", "date"); // 日期
    	forwardMapStatic.put("22", "openInterest"); // 持仓量
    	forwardMapStatic.put("23", "settlement"); // 结算价
    	forwardMapStatic.put("24", "closing"); // 昨收盘
    	forwardMapStatic.put("32", "openQuotation"); //今开盘
    	
    }
    
    
    static {
    	
    	jutFuelPriceMapStatic.put("1", "code"); // 代码
    	jutFuelPriceMapStatic.put("2", "area"); // 地区
    	jutFuelPriceMapStatic.put("3", "todayPrice"); // 当日价
    	jutFuelPriceMapStatic.put("5", "upDown"); // 升跌
    	jutFuelPriceMapStatic.put("11", "yesterdayPrice"); // 昨日价
    	jutFuelPriceMapStatic.put("17", "shopSign"); // 牌号
    	jutFuelPriceMapStatic.put("21", "date"); // 日期
    	jutFuelPriceMapStatic.put("32", "remark"); // 备注
    	
    }
    
    
    static {
    	
    	dollarRateMapStatic.put("1", "code"); // 代码
    	dollarRateMapStatic.put("2", "typeName"); // 品种名
    	dollarRateMapStatic.put("12", "todayPrice"); // 中间价
    	dollarRateMapStatic.put("24", "date"); // 日期
    	
    }
    
    
    static {
    	
    	oilOrBenzolMapStatic.put("1", "code"); // 代码
    	oilOrBenzolMapStatic.put("2", "companyName"); // 企业名称、企业单位
    	oilOrBenzolMapStatic.put("3", "todayPrice"); // 当日价
    	oilOrBenzolMapStatic.put("5", "upDown"); // 升跌
    	oilOrBenzolMapStatic.put("11", "yesterdayPrice"); // 昨日价
    	oilOrBenzolMapStatic.put("16", "area"); // 地区
    	oilOrBenzolMapStatic.put("17", "shopSign"); // 牌号
    	oilOrBenzolMapStatic.put("21", "date"); // 日期
    	oilOrBenzolMapStatic.put("23", "remark2"); // 备注2
    	oilOrBenzolMapStatic.put("32", "remark"); // 备注 
    	
    }
    
    static {
    	
    	naphthaMapStatic.put("1", "code"); // 代码
    	naphthaMapStatic.put("2", "area"); // 地区
    	naphthaMapStatic.put("3", "todayPrice"); // 当日价
    	naphthaMapStatic.put("5", "upDown"); // 升跌
    	naphthaMapStatic.put("11", "yesterdayPrice"); // 昨日价
    	naphthaMapStatic.put("17", "shopSign"); // 牌号
    	naphthaMapStatic.put("21", "date"); // 日期
    	naphthaMapStatic.put("23", "remark2"); // 备注2
    	naphthaMapStatic.put("32", "remark"); // 备注 
    	
    }
    
    static {
    	
    	shipFuelOilMapStatic.put("1", "code"); // 代码
    	shipFuelOilMapStatic.put("2", "typeName"); // 品种名
    	shipFuelOilMapStatic.put("5", "upDown"); // 升跌
    	shipFuelOilMapStatic.put("13", "todayPrice"); // 今日价、当日价格
    	shipFuelOilMapStatic.put("14", "yesterdayPrice"); // 昨日价
    	shipFuelOilMapStatic.put("16", "port"); // 港口
    	shipFuelOilMapStatic.put("21", "date"); // 日期
    	shipFuelOilMapStatic.put("24", "dealWay"); // 交易方式
    	shipFuelOilMapStatic.put("32", "remark"); // 备注
    	
    }
    
    static {
    	
    	liquefiedGasMapStatic.put("1", "code"); // 代码
    	liquefiedGasMapStatic.put("2", "companyName"); // 企业名称、企业单位
    	liquefiedGasMapStatic.put("3", "todayPrice"); // 当日价
    	liquefiedGasMapStatic.put("5", "upDown"); // 升跌
    	liquefiedGasMapStatic.put("11", "yesterdayPrice"); // 昨日价
    	liquefiedGasMapStatic.put("16", "area"); // 地区
    	liquefiedGasMapStatic.put("17", "shopSign"); // 牌号
    	liquefiedGasMapStatic.put("21", "date"); // 日期
    	liquefiedGasMapStatic.put("23", "dealWay"); // 交易方式
    	liquefiedGasMapStatic.put("32", "remark"); // 备注 
    	
    }
    
    static {
    	
    	RMBExchangeRateMapStatic.put("1", "code"); // 代码
    	RMBExchangeRateMapStatic.put("2", "typeName"); // 品种名
    	RMBExchangeRateMapStatic.put("3", "middlePrice"); // 中间价
    	RMBExchangeRateMapStatic.put("5", "upDown"); // 升跌
    	RMBExchangeRateMapStatic.put("6", "highPrice"); // 最高价
    	RMBExchangeRateMapStatic.put("7", "lowPrice"); // 最低价
    	RMBExchangeRateMapStatic.put("9", "time"); // 时间
    	RMBExchangeRateMapStatic.put("12", "buyPrice"); // 叫买
    	RMBExchangeRateMapStatic.put("15", "sellPrice"); // 叫卖
    	RMBExchangeRateMapStatic.put("21", "date"); // 日期
    	RMBExchangeRateMapStatic.put("25", "upDownPercentage"); // 升跌%
    	
    }
    /*92#汽油、95#分省价格  英文、0#柴油国内市场估价*/
    public static Map<String, String> oil92ENMapStatic = Maps.newHashMap();
   
	static {
    	oil92ENMapStatic.put("1", "code"); // 代码
    	oil92ENMapStatic.put("3", "today"); // 当日价
    	oil92ENMapStatic.put("5", "change"); // 升跌
    	oil92ENMapStatic.put("11", "previous"); // 昨日价
    	oil92ENMapStatic.put("16", "province"); // 省
    	oil92ENMapStatic.put("17", "comment"); // 评论
    	oil92ENMapStatic.put("21", "date"); // 日期
	}
    /*95#国内市场批发报价、0#柴油东北、华北、西北报价、0#柴油华中华东、0#柴油华南、西南*/
    public static Map<String, String> oil95ENMapStatic = Maps.newHashMap();
	static {
		oil95ENMapStatic.put("1", "code"); // 代码
		oil95ENMapStatic.put("3", "today"); // 当日价
		oil95ENMapStatic.put("5", "change"); // 升跌
		oil95ENMapStatic.put("11", "previous"); // 昨日价
		oil95ENMapStatic.put("21", "date"); // 日期
		oil95ENMapStatic.put("22", "region"); // 地区
		oil95ENMapStatic.put("26", "companyName"); // 公司名
		oil95ENMapStatic.put("28", "comment"); // 评论
		oil95ENMapStatic.put("30", "grade"); // 牌号
	}
	 
    /*山东地炼汽油市场报价、其他地炼汽油市场报价、山东地炼市场柴油1,2、其他地炼市场柴油*/
    public static Map<String, String> oilSDENMapStatic = Maps.newHashMap();
	static {
		oilSDENMapStatic.put("1", "code"); // 代码
		oilSDENMapStatic.put("3", "today"); // 当日价
		oilSDENMapStatic.put("5", "change"); // 升跌
		oilSDENMapStatic.put("11", "previous"); // 昨日价
		oilSDENMapStatic.put("16", "grade"); // 牌号
		oilSDENMapStatic.put("21", "date"); // 日期
		oilSDENMapStatic.put("22", "comment1"); // 评论
		oilSDENMapStatic.put("26", "companyName"); // 公司名
		oilSDENMapStatic.put("30", "comment2"); // 评论
	}

    /*国际市场_柴油现货*/
    public static Map<String, String> dieselSpotENMapStatic = Maps.newHashMap();
    
	static {
		dieselSpotENMapStatic.put("1", "code"); // 代码
		dieselSpotENMapStatic.put("3", "today"); // 当日价
		dieselSpotENMapStatic.put("16", "term"); // 术语
		dieselSpotENMapStatic.put("20", "deliver"); // 交付
		dieselSpotENMapStatic.put("23", "currency"); // 货币单位
		dieselSpotENMapStatic.put("24", "date"); // 日期
		dieselSpotENMapStatic.put("30", "term2"); // 术语
		dieselSpotENMapStatic.put("34", "name"); //
	}
	 /*国际市场_汽油现货*/
    public static Map<String, String> gasSpotENMapStatic = Maps.newHashMap();
	static {
		gasSpotENMapStatic.put("1", "code"); // 代码
		gasSpotENMapStatic.put("3", "today"); // 当日价
		gasSpotENMapStatic.put("13", "term"); // 术语
		gasSpotENMapStatic.put("20", "deliver"); // 交付
		gasSpotENMapStatic.put("22", "lacation"); // 位置
		gasSpotENMapStatic.put("23", "currency"); // 货币单位
		gasSpotENMapStatic.put("24", "date"); // 日期
		gasSpotENMapStatic.put("30", "transportation"); // 运输方式
		gasSpotENMapStatic.put("34", "name"); // 
	}
	
}
