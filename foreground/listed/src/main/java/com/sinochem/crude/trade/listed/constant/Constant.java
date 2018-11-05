package com.sinochem.crude.trade.listed.constant;

/**
 * 用于存放模块中出现的常量，避免程序中出现魔法值
 * @author Yichen Zhao
 * date: 20180107
 */
public class Constant {

    /** 需求类型（D - 需求 B - 报价）*/
    public static final String DEMAND_TYPE_D = "D";
    /** 需求类型（D - 需求 B - 报价）*/
    public static final String DEMAND_TYPE_B = "B";

    /** 业务类型(CrudeOil - 原油业务 ProductOil - 成品油业务) */
    public static final String BIZ_TYPE_CRUDEOIL = "CrudeOil";
    /** 业务类型(CrudeOil - 原油业务 ProductOil - 成品油业务) */
    public static final String BIZ_TYPE_PRODUCTOIL = "ProductOil";

    /** 交易类型(S - 卖 B - 买) */
    public static final String DEAL_TYPE_B = "B";
    /** 交易类型(S - 卖 B - 买) */
    public static final String DEAL_TYPE_S = "S";
    
    /** 默认的分页数据条数*/
    public static final int DEFAULT_PAGE_SIZE = 20;
    
    /** 前台用FLAG*/
    public static final String MALL = "mall";

    /** 数据库桶数放大比例*/
    public static final double DEMAND_NUM_AMPLIFY_SCALE = 1000.0;
    public static final double TEN_THOUSAND = 10000.0;

    /** 成品油商城列表显示条数*/
    public static final int TRADE_MALL_MAX_DATA_QUANTITY = 8;
    public static final int HOT_DEMAND_QUANTITY = 5;
    
    public static String[][] LISTED_0001 = {new String[]{"zh","数量（桶）"}, new String[]{"en","Qty(BBL)"} };
    public static String[][] LISTED_0002 = {new String[]{"zh","溢短装"}, new String[]{"en","Tolerance"} };
    public static String[][] LISTED_0003 = {new String[]{"zh","贸易条款"}, new String[]{"en","Incoterm"} };
    public static String[][] LISTED_0004 = {new String[]{"zh","升贴水"}, new String[]{"en","Premium/Discount"} };
    public static String[][] LISTED_0005 = {new String[]{"zh","计价基准"}, new String[]{"en","Pricing Basis"} };
    public static String[][] LISTED_0006 = {new String[]{"zh","计价期"}, new String[]{"en","Pricing Period"} };
    public static String[][] LISTED_0007 = {new String[]{"zh","计价公式"}, new String[]{"en","Price Fomula"} };
    public static String[][] LISTED_0008 = {new String[]{"zh","付款日期"}, new String[]{"en","Payment Date"} };
    public static String[][] LISTED_0009 = {new String[]{"zh","信用条款"}, new String[]{"en","Credit term"} };
    public static String[][] LISTED_0010 = {new String[]{"zh","结算量标准"}, new String[]{"en","Qty Determination"} };
    public static String[][] LISTED_0011 = {new String[]{"zh","报价有效期"}, new String[]{"en","Valid to"} };
    public static String[][] LISTED_0012 = {new String[]{"zh","装卸允许时间"}, new String[]{"en","Laytime"} };
    public static String[][] LISTED_0013 = {new String[]{"zh","商检费分摊"}, new String[]{"en","Inspection"} };
    public static String[][] LISTED_0014 = {new String[]{"zh","法律"}, new String[]{"en","Law"} };
    public static String[][] LISTED_0015 = {new String[]{"zh","GTC"}, new String[]{"en","GTC"} };
    public static String[][] LISTED_0016 = {new String[]{"zh","备注"}, new String[]{"en","Remark"} };
    public static String[][] LISTED_0017 = {new String[]{"zh","装货港"}, new String[]{"en","Load Port"} };
    public static String[][] LISTED_0018 = {new String[]{"zh","装货期开始"}, new String[]{"en","Laycan Start"} };
    public static String[][] LISTED_0019 = {new String[]{"zh","装货期结束"}, new String[]{"en","Laycan End"} };
    public static String[][] LISTED_0020 = {new String[]{"zh","卸货港"}, new String[]{"en","Discharge Port"} };
    public static String[][] LISTED_0021 = {new String[]{"zh","到货期开始"}, new String[]{"en","DDR Start"} };
    public static String[][] LISTED_0022 = {new String[]{"zh","到货期结束"}, new String[]{"en","DDR End"} };
    
    public static String[][] LISTED_0023 = {new String[]{"zh","采购需求定向发布"}, new String[]{"en","Specific Purchase Demand"} };
    public static String[][] LISTED_0024 = {new String[]{"zh","销售资源定向发布"}, new String[]{"en","Specific Sales Resource"} };
    public static String[][] LISTED_0025 = {new String[]{"zh","销售需求报价发布"}, new String[]{"en","New Quotation"} };
    public static String[][] LISTED_0026 = {new String[]{"zh","采购需求报价发布"}, new String[]{"en","New Quotation"} };
    public static String[][] LISTED_0027 = {new String[]{"zh","销售需求报价修改"}, new String[]{"en","Update Quotation"} };
    public static String[][] LISTED_0028 = {new String[]{"zh","采购需求报价修改"}, new String[]{"en","Update Quotation"} };
    public static String[][] LISTED_0029 = {new String[]{"zh","销售需求意向报价确认"}, new String[]{"en","Confirm Indication"} };
    public static String[][] LISTED_0030 = {new String[]{"zh","采购需求意向报价确认"}, new String[]{"en","Confirm Indication"} };
    public static String[][] LISTED_0031 = {new String[]{"zh","销售需求报价中标"}, new String[]{"en","Deal Done"} };
    public static String[][] LISTED_0032 = {new String[]{"zh","采购需求报价中标"}, new String[]{"en","Deal Done"} };
    public static String[][] LISTED_0033 = {new String[]{"zh","销售需求报价未中标"}, new String[]{"en","Quotation Failed"} };
    public static String[][] LISTED_0034 = {new String[]{"zh","采购需求报价未中标"}, new String[]{"en","Quotation Failed"} };
    public static String[][] LISTED_0035 = {new String[]{"zh","含硫量"}, new String[]{"en","Sulphur/ m%"} };
    public static String[][] LISTED_0038 = {new String[]{"zh","API度"}, new String[]{"en","API"} };
    public static String[][] LISTED_0036 = {new String[]{"zh","至"}, new String[]{"en","To"} };
    public static String[][] LISTED_0037 = {new String[]{"zh","需求发起者不能参与投标或报价!"}, new String[]{"en","The requirement originator cannot participate in the submission of bids or quotation!"} };
    
    public static String[][] LISTED_0134 = {new String[]{"zh","交易方式"}, new String[]{"en","Demand Type"} };
    public static String[][] LISTED_0135 = {new String[]{"zh","招标"}, new String[]{"en","Tender"} };
    public static String[][] LISTED_0136 = {new String[]{"zh","询价"}, new String[]{"en","Inquiry"} };
    public static String[][] LISTED_0137 = {new String[]{"zh","发布范围"}, new String[]{"en","Range"} };
    public static String[][] LISTED_0138 = {new String[]{"zh","公开发布"}, new String[]{"en","Public"} };
    public static String[][] LISTED_0139 = {new String[]{"zh","定向发布"}, new String[]{"en","Target"} };
    public static String[][] LISTED_0140 = {new String[]{"zh","数量"}, new String[]{"en","Qty"} };
    public static String[][] LISTED_0141 = {new String[]{"zh","50万桶以下"}, new String[]{"en","<500KB"} };
    public static String[][] LISTED_0142 = {new String[]{"zh","50万-100万桶"}, new String[]{"en","500-1000KB"} };
    public static String[][] LISTED_0143 = {new String[]{"zh","100万-200万桶"}, new String[]{"en","1000-2000KB"} };
    public static String[][] LISTED_0144 = {new String[]{"zh","200万桶以上"}, new String[]{"en",">2000KB"} };
    public static String[][] LISTED_0145 = {new String[]{"zh","发布企业"}, new String[]{"en","Company"} };
    public static String[][] LISTED_0146 = {new String[]{"zh","采购方式"}, new String[]{"en","Purchase Type"} };
    public static String[][] LISTED_0147 = {new String[]{"zh","按油种采购"}, new String[]{"en","By Grade"} };
    public static String[][] LISTED_0148 = {new String[]{"zh","按性质采购"}, new String[]{"en","By Specification"} };
    
    /** 卸货港*/
    public static String[][] UN_LOAD_PORT_01 = { new String[]{"zh","大连港"}, new String[]{"en","DALIAN"} };
    public static String[][] UN_LOAD_PORT_02 = { new String[]{"zh","天津港"}, new String[]{"en","TIANJIN"} };
    public static String[][] UN_LOAD_PORT_03 = { new String[]{"zh","青岛港"}, new String[]{"en","QINGDAO"} };
    public static String[][] UN_LOAD_PORT_04 = { new String[]{"zh","日照港"}, new String[]{"en","RIZHAO"} };
    public static String[][] UN_LOAD_PORT_05 = { new String[]{"zh","烟台港"}, new String[]{"en","YANTAI"} };
    public static String[][] UN_LOAD_PORT_06 = { new String[]{"zh","营口港"}, new String[]{"en","YINGKOU"} };
    public static String[][] UN_LOAD_PORT_07 = { new String[]{"zh","宁波港"}, new String[]{"en","NINGBO"} };
    public static String[][] UN_LOAD_PORT_08 = { new String[]{"zh","舟山港"}, new String[]{"en","ZHOUSHAN"} };
    public static String[][] UN_LOAD_PORT_09 = { new String[]{"zh","泉州港"}, new String[]{"en","QUANZHOU"} };
    public static String[][] UN_LOAD_PORT_10 = { new String[]{"zh","惠州港"}, new String[]{"en","HUIZHOU"} };
    public static String[][] UN_LOAD_PORT_11 = { new String[]{"zh","湛江港"}, new String[]{"en","ZHANJIANG"} };
    public static String[][] UN_LOAD_PORT_12 = { new String[]{"zh","洋浦港"}, new String[]{"en","YANGPU"} };
    public static String[][] UN_LOAD_PORT_13 = { new String[]{"zh","唐山港"}, new String[]{"en","TANGSHAN"} };
    public static String[][] UN_LOAD_PORT_14 = { new String[]{"zh","茂名港"}, new String[]{"en","MAOMING"} };
    public static String[][] UN_LOAD_PORT_15 = { new String[]{"zh","钦州港"}, new String[]{"en","QINZHOU"} };
    public static String[][] UN_LOAD_PORT_16 = { new String[]{"zh","锦州港"}, new String[]{"en","JINZHOU"} };
    public static String[][] UN_LOAD_PORT_17 = { new String[]{"zh","曹妃甸"}, new String[]{"en","CAOFEIDIAN"} };
    public static String[][] UN_LOAD_PORT_18 = { new String[]{"zh","新加坡"}, new String[]{"en","SINGAPORE"} };
    public static String[][] UN_LOAD_PORT_19 = { new String[]{"zh","镇江港"}, new String[]{"en","ZHENJIANG"} };
    public static String[][] UN_LOAD_PORT_20 = { new String[]{"zh","新港"}, new String[]{"en","XINGANG"} };
    public static String[][] UN_LOAD_PORT_21 = { new String[]{"zh","台湾沙仑港"}, new String[]{"en","SHALUN TAIWAN"} };
    public static String[][] UN_LOAD_PORT_22 = { new String[]{"zh","台湾高雄港"}, new String[]{"en","KAOHSIUNG TAIWAN"} };
    public static String[][] UN_LOAD_PORT_23 = { new String[]{"zh","潍坊滨海油库"}, new String[]{"en","WEIFANGBINHAIYOUKU"} };
}
