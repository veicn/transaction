package com.sinochem.crude.trade.commons;

public class JKXContants {

	public enum JKX_NEW_TYPE{
		TYPE_9GJRB("成品油日报","9GJRB","HYKU","day","zh")
		,TYPE_PRODW("成品油周报","PRODW","CPYYB","week","zh")
		,TYPE_PRODM("成品油月报","PRODM","CPYYB","month","zh")
		,TYPE_9SC("成品油价格快讯","9SC","HYKU","day","zh")
		,TYPE_9CPYTJ3("成品油表观消费量","9CPYTJ3","QLFY","month","zh")
		,TYPE_9ZGSYKC("中国石油库存","9ZGSYKC","YYKC","month","zh")
		,TYPE_9CPYTJ4("主营开工率","9CPYTJ4","ZYKGL","halfMonth","zh")
		,TYPE_17PSFX1("炼厂开工率","17PSFX1","LCKGL","week","zh")
		,TYPE_9CPYTJ5("成品油社会商业库存-成品油山东地炼库存-成品油国内库存", "9CPYTJ5", "CPYYB","month","zh")
		,TYPE_9HMKX1("航煤快讯","9HMKX1","HYKU","day","zh")
		,TYPE_28033("原油现货报告","28033|亚洲原油收市报告","XHBG","day","zh")
		,TYPE_YYXHZB("原油现货招标信息","YYXHZB","ZBXX","untime","zh")
		,TYPE_8841("原油及成品油库存报告","8841","YYKC","untime","zh")
		,TYPE_CRU1("原油日报","CRU1","YY","day","zh")
		,TYPE_CRUWW("原油周报","CRUWW","YY","week","zh")
		,TYPE_CRUM("原油月报","CRUM","YY","month","zh")
		,TYPE_CFTC28063("原油持仓报告","CFTC|28063","YY","untime","zh")
		,TYPE_9YJYCXW("原油专题文章","9YJYCXW","TTJJ","untime","zh")
		,TYPE_9BHYSGC("原油盘中简讯","9BHYSGC","HYKU","untime","zh")
		,TYPE_17CQ("船期报告","17CQ+原油船期","HYXW","untime","zh")
		,TYPE_HYSC("航运信息","HYSC","YLSC","untime","zh")
		,TYPE_ZHCPYTLFX("金凯迅分析：国内汽柴油进出口套利分析","ZHCPYTLFX","ANALYSIS1","halfMonth","en")
		,TYPE_ZHCPYSFTL("金凯迅分析：汽柴油山东套利分析","ZHCPYSFTL","ANALYSIS2","halfMonth","en")
		,TYPE_ZHCPYXW("成品油实时评论","ZHCPYXW","NEWS3","multipleDaily","en")
		,TYPE_ZHCPYKX("成品油快讯","ZHCPYKX","NEWS1","multipleDaily","en")
		,TYPE_ZHCPYZC("新闻政策","ZHCPYZC","NEWS2","multipleDaily","en")
		,TYPE_ZHCPYSCRB("成品油国际市场日报","ZHCPYSCRB","REPORTS","day","en")
		,TYPE_9TJZBEN("最新国际原油移动加权变化率","9TJZBEN","DATA4","month","en")
		,TYPE_ZH9SSPL("成品油调价预测","ZH9SSPL","DATA4","month","en")
		,TYPE_9TJQKYLEN("成品油历次调价一览表","9TJQKYLEN","DATA5","month","en")
		,TYPE_ZHCPYKGL("成品油开工率","ZHCPYKGL","DATA3","month","en")
		,TYPE_ZHCPYKGLKC("原油库存","ZHCPYKGLKC","ABOUT CRUDEOIL2","month","en")
		,TYPE_ZHCPYJCKCLBG("国内成品油产量/进出口量/表观消费量","ZHCPYJCKCLBG","DATA1","month","en")
		,TYPE_81918("NEMEX RBOB/取暖油每日持仓报告","81918","REPORTS","untime","en")
		,TYPE_OIL("原油新闻","OIL","YYXW","multipleDaily","en")
		,TYPE_E8841("美国库存","E8841","MGKC","untime","en")
		;
		
		JKX_NEW_TYPE(String jkxType,String jkxCode,String channelCode,String typeTime,String langVer){
    		this.jkxType = jkxType;
    		this.jkxCode = jkxCode;
    		this.channelCode = channelCode;
    		this.typeTime = typeTime;
    		this.langVer = langVer;
    	}
    	private String jkxType; //金凯讯 资讯类型
    	private String jkxCode; //金凯讯类型编码
    	private String channelCode; //对应的频道名称
    	private String typeTime;//对应的日报、周报、月报
    	private String langVer;//语言类型
    	
		public String getJkxType() {
			return jkxType;
		}

		public String getJkxCode() {
			return jkxCode;
		}

		public String getChannelCode() {
			return channelCode;
		}

		public String getTypeTime() {
			return typeTime;
		}

		public String getLangVer() {
			return langVer;
		}

		
	}
}
