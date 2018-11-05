package com.sinochem.crude.trade.productOil.constant;

import java.util.HashMap;
import java.util.Map;

public class InfoConstant {

	/**
	 * 英文版
	 */
	public static String langVel_EN = "en";
	
	/**
	 * 中文版
	 */
	public static String langVel_ZH = "zh";
	
    /**
     * html文件前半部分
     * 汽油：92#（国四/国五/调和/95#分省价格/柴油国内市场估价，三者表格样式相同
     * 表格样式一
     */
    public static String EN_first1 = "<table>\r\n" + 
			"    <tr>\r\n" + 
			"        <th>Provincee</th>\r\n" + 
			"        <th>Today</th>\r\n" + 
			"        <th>Previous</th>\r\n" + 
			"        <th>Change</th>\r\n" + 
			"        <th>Date</th>\r\n" + 
			"    </tr>";
    

    /**
     * html文件前半部分
     * 汽油：92#（国四/国五/调和/95#分省价格/柴油国内市场估价，三者表格样式相同
     * 表格样式二
     */
    public static String EN_first2 = "<table>\r\n" + 
			"    <tr>\r\n" + 
			"        <th>Company Name</th>\r\n" + 
			"        <th>Region</th>\r\n" + 
			"        <th>Grade</th>\r\n" + 
			"        <th>Today</th>\r\n" + 
			"        <th>Previous</th>\r\n" + 
			"        <th>Change</th>\r\n" + 
			"        <th>Date</th>\r\n" + 
			"    </tr>";
    
    /**
     * html文件前半部分
     * 汽油：92#（国四/国五/调和/95#分省价格/柴油国内市场估价，三者表格样式相同
     * 表格样式三
     */
    public static String EN_first3 = "<table>\r\n" + 
    		"    <tr>\r\n" + 
    		"        <th>Company Name</th>\r\n" + 
    		"        <th>Grade</th>\r\n" + 
    		"        <th>Today</th>\r\n" + 
    		"        <th>Previous</th>\r\n" + 
    		"        <th>Change</th>\r\n" + 
    		"        <th>Date</th>\r\n" + 
    		"    </tr>";
    
    /**
     * html文件前半部分
     * 国际市场_汽油现货
     * 表格样式四
     */
    public static String EN_first4 = "<table>\r\n" + 
    		"    <tr>\r\n" + 
    		"        <th>Name</th>\r\n" + 
//    		"        <th>term</th>\r\n" + 
//    		"        <th>deliver</th>\r\n" + 
    		"        <th>price</th>\r\n" + 
    		"        <th>currency</th>\r\n" + 
    		"        <th>transportation</th>\r\n" + 
    		"        <th>data</th>\r\n" + 
    		"    </tr>";
    
    /**
     * html文件前半部分
     * 国际市场_汽油现货
     * 表格样式五
     */
    public static String EN_first5 = "<table>\r\n" + 
    		"    <tr>\r\n" + 
    		"        <th>Name</th>\r\n" + 
//    		"        <th>deliver</th>\r\n" + 
    		"        <th>price</th>\r\n" + 
    		"        <th>currency</th>\r\n" + 
//    		"        <th>term</th>\r\n" + 
    		"        <th>lacation</th>\r\n" + 
    		"        <th>transportation</th>\r\n" + 
    		"        <th>data</th>\r\n" + 
    		"    </tr>";
    
    
    
    
    
    
    /**
     * html文件后半部分-单表格
     */
    public static String later = "<style>\n" +
			"\t.news-content div{\n" +
			"\t\ttext-align:center;\n" +
			"\t\tmargin-bottom: 20px;\n" +
			"\t\tfont-size:25px;\n" +
			"\t}\n" +
			"    .news-content table {\n" +
			"        width: 810px;\n" +
			"        border: 1px solid #EBEBEB;\n" +
			"        border-collapse: collapse;\n" +
			"        border-spacing: 0;\n" +
			"        margin: auto;\n" +
			"    }\n" +
			"    .news-content table>tbody>tr>td,.news-content table>tbody>tr>th{\n" +
			"        height: 30px;\n" +
			"        text-align: center;\n" +
			"        border: 1px solid #EBEBEB;\n" +
			"    }\n" +
			"    .news-content table>tbody>tr>th{\n" +
			"        background: #f5f5f5;\n" +
			"    }\n" +
			"    .news-content table>tbody>tr:nth-of-type(2n+1){\n" +
			"        background: #f7f7f7;\n" +
			"    }\n" +
			"\n" +
			"\n" +
			"    .news-content table>tbody>tr>td,table>tbody>tr>th{\n" +
			"        height: 30px;\n" +
			"        text-align: center;\n" +
			"        border: 1px solid #EBEBEB;\n" +
			"    }\n" +
			"    .news-content table>tbody>tr>th{\n" +
			"        background: #f5f5f5;\n" +
			"    }\n" +
			"</style>";
    
    
    /**
     * html文件后半部分-多表格
     */
    public static String later2 = "<style>\n" +
			"\t.news-content div{\n" +
			"\t\ttext-align:center;\n" +
			"\t\tmargin-bottom: 20px;\n" +
			"\t\tfont-size:25px;\n" +
			"\t}\n" +
			"    .news-content table {\n" +
			"        width: 810px;\n" +
			"        border: 1px solid #EBEBEB;\n" +
			"        border-collapse: collapse;\n" +
			"        border-spacing: 0;\n" +
			"        margin: auto;\n" +
			"    }\n" +
			"    .news-content table>tbody>tr>td,.news-content table>tbody>tr>th{\n" +
			"        height: 30px;\n" +
			"        text-align: center;\n" +
			"        border: 1px solid #EBEBEB;\n" +
			"    }\n" +
			"    .news-content table>tbody>tr>th{\n" +
			"        background: #f5f5f5;\n" +
			"    }\n" +
			"    .news-content table>tbody>tr:nth-of-type(2n+1){\n" +
			"        background: #f7f7f7;\n" +
			"    }\n" +
			"\n" +
			"\n" +
			"    .news-content table>tbody>tr>td,table>tbody>tr>th{\n" +
			"        height: 30px;\n" +
			"        text-align: center;\n" +
			"        border: 1px solid #EBEBEB;\n" +
			"    }\n" +
			"    .news-content table>tbody>tr>th{\n" +
			"        background: #f5f5f5;\n" +
			"    }\n" +
			"</style>";
    
    
    

	/**
	 * 国际市场汽油_现货报价
	 * PRICES1
	 * 样式五
	 */
	public static String gasoline_international = "International Gasoline Prices";

	/**
	 * 国际市场柴油_现货报价
	 * PRICES2
	 * 样式四
	 */
	public static String diesel_international = "International Gasoil Prices";
	
	
	/**
	 * 汽油：92#（国四/国五/调和
	 * PRICES3
	 * 样式一
	 */
	public static String gasoline92_provinces = "92# Gasoline Demestic Assessments(provinces)";
	
	/**
	 * 95#分省价格  
	 * PRICES3
	 * 样式一
	 */
	public static String gasoline95_provinces = "95# Gasoline Demestic Assessments(provinces)";
	
	/**
	 * 汽油95#国内市场批发报价	
	 * PRICES4
	 * 样式二  
	 */
	public static String gasoline95 = "95# Gasoline Demestic Wholesale Prices";
	
	/**
	 * 山东地炼汽油市场报价	
	 * PRICES5
	 * 样式三  
	 */
	public static String shandong_refine_gasoline = "Gasoline Wholesale Prices of Shandong Independent Refineries";
	
	
	/**
	 * 山东地炼市场柴油1,2
	 * PRICES6	  
	 * 样式三
	 */
	public static String shandong_refine_diesel_1_2 = "Gasoil Wholesale Prices of Shandong Independent Refineries";
	
	/**
	 * 0#柴油东北、华北、西北报价
	 * PRICES7
	 * 样式二
	 */
	public static String diesel_northEast_northChina_northWest = "0# Gasoil Wholesale Prices of Northeast/North China/Northwest";
	
	/**
	 * 0#柴油华中、华东
	 * PRICES7
	 * 样式二
	 */
	public static String diesel_southChina_southWest = "0# Gasoil Wholesale Prices of South China/South West";
	
	/**
	 * 0#柴油华南、西南
	 * PRICES7
	 * 样式二
	 */
	public static String diesle_southChina_southWest = "0# Gasoil Wholesale Prices of South China/South West";
	
	/**
	 * 柴油国内市场估价
	 * PRICES8
	 * 样式一
	 */
	public static String diesel_domestic = "0# Gasoil Demestic Assessments";
	

	/**
	 * 其他地炼汽油市场报价	
	 * 样式三  
	 */
	public static String other_refine_gasoline = "Gasoline Wholesale Prices of Other Independent Refineries";

	/**
	 * 其它地炼市场柴油	
	 * 样式三  
	 */
	public static String other_refine_diesel = "Gasoil Wholesale Prices of Others Independent Refineries";
	
	
	public static Map<String,String> channelType = new HashMap<>();
	static {
		
		/**
		 * 国际市场汽油_现货报价,对应CHANNEL_CODE
		 * PRICES1
		 */
		channelType.put(gasoline_international, "PRICES1");
		
		/**
		 * 国际市场柴油_现货报价
		 * PRICES2
		 */
		channelType.put(diesel_international, "PRICES2");
		
		/**
		 * 汽油：92#（国四/国五/调和，对应CHANNEL_CODE
		 */
		channelType.put(gasoline92_provinces, "PRICES3");
		
		/**
		 * 95#分省价格，对应CHANNEL_CODE
		 */
		channelType.put(gasoline95_provinces, "PRICES3");
		
		/**
		 * 汽油95#国内市场批发报价，对应CHANNEL_CODE
		 */
		channelType.put(gasoline95, "PRICES4");
		
		/**
		 * 山东地炼汽油市场报价，对应CHANNEL_CODE
		 */
		channelType.put(shandong_refine_gasoline, "PRICES5");

		/**
		 * 山东地炼市场柴油1,2，对应CHANNEL_CODE
		 */
		channelType.put(shandong_refine_diesel_1_2, "PRICES6");

		/**
		 * 0#柴油东北、华北、西北报价
		 * PRICES7
		 */
		channelType.put(diesel_northEast_northChina_northWest, "PRICES7");
	
		/**
		 * 0#柴油华中、华东
		 * PRICES7
		 */
		channelType.put(diesel_southChina_southWest, "PRICES7");
		
		/**
		 * 0#柴油华南、西南
		 * PRICES7
		 */
		channelType.put(diesle_southChina_southWest, "PRICES7");
		
		/**
		 * 柴油国内市场估价，对应CHANNEL_CODE
		 */
		channelType.put(diesel_domestic, "PRICES8");
		
		/**
		 * 其他地炼汽油市场报价，对应CHANNEL_CODE
		 */
		channelType.put(other_refine_gasoline, "");
		
		
		/**
		 * 其他地炼市场柴油，对应CHANNEL_CODE
		 */
		channelType.put(other_refine_diesel, "");
	}
	
}
