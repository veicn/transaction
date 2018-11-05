package com.sinochem.crude.trade.datafeed;

public class SymbolContants {
	public enum SYMBOL_TYPE{
		TYPE_7188("SW-USECNY-JKX-JKX","7188","SW"),
		TYPE_7189("一个月-USECNY-JKX-JKX","7189","一个月"),
		TYPE_7190("两个月-USECNY-JKX-JKX","7190","两个月"),
		TYPE_7191("三个月-USECNY-JKX-JKX","7191","三个月"),
		TYPE_7192("六个月-USECNY-JKX-JKX","7192","六个月"),
		TYPE_7193("九个月-USECNY-JKX-JKX","7193","九个月"),
		TYPE_7194("一年-USECNY-JKX-JKX","7194","一年"),
		TYPE_7195("两年-USECNY-JKX-JKX","7195","两年"),
		TYPE_7196("三年-USECNY-JKX-JKX","7196","三年"),
		TYPE_7197("四年-USECNY-JKX-JKX","7197","四年"),
		TYPE_7198("五年-USECNY-JKX-JKX","7198","五年"),
		;
		SYMBOL_TYPE(String symbolCode,String id,String symbolName){
    		this.symbolCode = symbolCode;
    		this.id = id;
    		this.symbolName = symbolName;
        	
    	}
		private String symbolCode; //合约代码
		private String id; //抓取id
		private String symbolName; //合约名称
		
		public String getId() {
			return id;
		}
		public String getSymbolName() {
			return symbolName;
		}
		public String getSymbolCode() {
			return symbolCode;
		}
		
	}
}
