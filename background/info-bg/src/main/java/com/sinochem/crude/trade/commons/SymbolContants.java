package com.sinochem.crude.trade.commons;

public class SymbolContants {
	public enum SYMBOL_TYPE{
		TYPE_2664("1st Month-Brent-EXCHANGE-ICE","2664","布伦特首行"),
		TYPE_2665("2nd Month-Brent-EXCHANGE-ICE","2665","布伦特次行"),
		TYPE_2666("3rd Month-Brent-EXCHANGE-ICE","2666","布伦特三行"),
		TYPE_2213("Jan-Brent-EXCHANGE-ICE","2213","布伦特1月"),
		TYPE_2214("Feb-Brent-EXCHANGE-ICE","2214","布伦特2月"),
		TYPE_2215("Mar-Brent-EXCHANGE-ICE","2215","布伦特3月"),
		TYPE_2216("Apr-Brent-EXCHANGE-ICE","2216","布伦特4月"),
		TYPE_2217("May-Brent-EXCHANGE-ICE","2217","布伦特5月"),
		TYPE_2218("Jun-Brent-EXCHANGE-ICE","2218","布伦特6月"),
		TYPE_2219("Jul-Brent-EXCHANGE-ICE","2219","布伦特7月"),
		TYPE_2220("Aug-Brent-EXCHANGE-ICE","2220","布伦特8月"),
		TYPE_2221("Sep-Brent-EXCHANGE-ICE","2221","布伦特9月"),
		TYPE_2222("Oct-Brent-EXCHANGE-ICE","2222","布伦特10月"),
		TYPE_2223("Nov-Brent-EXCHANGE-ICE","2223","布伦特11月"),
		TYPE_2224("Dec-Brent-EXCHANGE-ICE","2224","布伦特12月"),
		TYPE_2670("1st Month-WTI-EXCHANGE-NYMEX","2670","WTIC1"),
		TYPE_2671("2nd Month-WTI-EXCHANGE-NYMEX","2671","WTIC2"),
		TYPE_2672("3rd Month-WTI-EXCHANGE-NYMEX","2672","WTIC3"),
		TYPE_2237("Jan-WTI-EXCHANGE-NYMEX","2237","WTI1901"),
		TYPE_2238("Feb-WTI-EXCHANGE-NYMEX","2238","WTI1802"),
		TYPE_2239("Mar-WTI-EXCHANGE-NYMEX","2239","WTI1803"),
		TYPE_2240("Apr-WTI-EXCHANGE-NYMEX","2240","WTI1804"),
		TYPE_2241("May-WTI-EXCHANGE-NYMEX","2241","WTI1805"),
		TYPE_2242("Jun-WTI-EXCHANGE-NYMEX","2242","WTI1806"),
		TYPE_2243("Jul-WTI-EXCHANGE-NYMEX","2243","WTI1807"),
		TYPE_2244("Aug-WTI-EXCHANGE-NYMEX","2244","WTI1808"),
		TYPE_2245("Sep-WTI-EXCHANGE-NYMEX","2245","WTI1809"),
		TYPE_2246("Oct-WTI-EXCHANGE-NYMEX","2246","WTI1810"),
		TYPE_2247("Nov-WTI-EXCHANGE-NYMEX","2247","WTI1811"),
		TYPE_2248("Dec-WTI-EXCHANGE-NYMEX","2248","WTI1812"),
		TYPE_2566("1st Month-Oman-EXCHANGE-DME","2566","阿曼首行"),
		TYPE_2567("2nd Month-Oman-EXCHANGE-DME","2567","阿曼次行"),
		TYPE_2568("3rd Month-Oman-EXCHANGE-DME","2568","阿曼三行"),
		TYPE_2554("Jan-Oman-EXCHANGE-DME","2554","阿曼1月"),
		TYPE_2555("Feb-Oman-EXCHANGE-DME","2555","阿曼2月"),
		TYPE_2556("Mar-Oman-EXCHANGE-DME","2556","阿曼3月"),
		TYPE_2557("Apr-Oman-EXCHANGE-DME","2557","阿曼4月"),
		TYPE_2558("May-Oman-EXCHANGE-DME","2558","阿曼5月"),
		TYPE_2559("Jun-Oman-EXCHANGE-DME","2559","阿曼6月"),
		TYPE_2560("Jul-Oman-EXCHANGE-DME","2560","阿曼7月"),
		TYPE_2561("Aug-Oman-EXCHANGE-DME","2561","阿曼8月"),
		TYPE_2562("Sep-Oman-EXCHANGE-DME","2562","阿曼9月"),
		TYPE_2563("Oct-Oman-EXCHANGE-DME","2563","阿曼10月"),
		TYPE_2564("Nov-Oman-EXCHANGE-DME","2564","阿曼11月"),
		TYPE_2565("Dec-Oman-EXCHANGE-DME","2565","阿曼12月"),
		;
		SYMBOL_TYPE(String symbolCode,String id,String quotesName){
    		this.symbolCode = symbolCode;
    		this.id = id;
    		this.quotesName = quotesName;
        	
    	}
		private String symbolCode; //合约代码
		private String id; //抓取id
		private String quotesName; //品种名
		
		public String getId() {
			return id;
		}
		public String getQuotesName() {
			return quotesName;
		}
		public String getSymbolCode() {
			return symbolCode;
		}
		
	}
	
	public enum SYMBOL_NAME_CHANGE {
		SYMBOL_NAME_BRENT("布伦特", "Brent"),
		SYMBOL_NAME_OMAN("阿曼", "Oman"),
		SYMBOL_NAME_SC("沪原油", "SC");
		SYMBOL_NAME_CHANGE(String sourceString, String toString) {
			this.sourceString = sourceString;
			this.toString = toString;
		}
		private String sourceString;
		private String toString;
		
		public String getSourceString() {
			return sourceString;
		}
		public String getToString() {
			return toString;
		}
	}
}
