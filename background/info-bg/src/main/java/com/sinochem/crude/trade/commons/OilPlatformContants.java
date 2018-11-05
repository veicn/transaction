package com.sinochem.crude.trade.commons;

public class OilPlatformContants {
	public enum OIL_TYPE{
		TYPE_Brent("Spot-DTD Brent-Platts-DATED NORTH SEA","DTD Brent","1"),
		TYPE_Dubai("1st Month-Dubai-Platts-FOB ARAB GULF","Platts Dubai","1"),
		TYPE_Oman("1st Month-Oman-Platts-FOB ARAB GULF","Platts Oman","1"),
		TYPE_MURBAN("Outright-OSP-MURBAN-FOB ARAB GULF","MURBAN","2"),
		TYPE_ZAKUM("Outright-OSP-UPPER ZAKUM-FOB ARAB GULF","UPPER ZAKUM","2"),
		TYPE_BLEND("Outright-OSP-DAS BLEND-FOB ARAB GULF","DAS BLEND","2"),
		TYPE_AXL("Premium/Discount-OSP-AXL-FAR EAST","AXL","2"),
		TYPE_AL("Premium/Discount-OSP-AL-FAR EAST","AL","2"),
		TYPE_AM("Premium/Discount-OSP-AM-FAR EAST","AM","2"),
		TYPE_AH("Premium/Discount-OSP-AH-FAR EAST","AH","2"),
		TYPE_IL("Premium/Discount-OSP-IL-FAR EAST","IL","2"),
		TYPE_IH("Premium/Discount-OSP-IH-FAR EAST","IH","2"),
		TYPE_KUWAIT("Premium/Discount-OSP-KUWAIT-FAR EAST","KUWAIT","2"),
		TYPE_BASRAH_LT("Premium/Discount-OSP-BASRAH LT-FAR EAST","BASRAH LT","2"),
		TYPE_BASRAH_H("Premium/Discount-OSP-BASRAH H-FAR EAST","BASRAH H","2"),
		TYPE_LAND("Outright-OSP-QATAR LAND-FOB ARAB GULF","LAND","2"),
		TYPE_MARINE("Outright-OSP-QATAR MARINE-FOB ARAB GULF","MARINE","2"),
		TYPE_BT_SPREAD1("DTD Brent spread #1-DTD Brent spread-Platts-DATED NORTH SEA","DTD BRENT月差 #1","1"),
		TYPE_BT_SPREAD2("DTD Brent spread #2-DTD Brent spread-Platts-DATED NORTH SEA","DTD BRENT月差 #2","1"),
		TYPE_BT_SPREAD3("DTD Brent spread #3-DTD Brent spread-Platts-DATED NORTH SEA","DTD BRENT月差 #3","1"),
		TYPE_DI_SPREAD1("Dubai spread #1-Dubai spread-Platts-FOB ARAB GULF","DUBAI月差 #1","1"),
		TYPE_DI_SPREAD2("Dubai spread #2-Dubai spread-Platts-FOB ARAB GULF","DUBAI月差 #2","1"),
		TYPE_DI_SPREAD3("Dubai spread #3-Dubai spread-Platts-FOB ARAB GULF","DUBAI月差 #3","1"),
		TYPE_WTI("WTI spread-WTI spread-EXCHANGE-NYMEX","WTI","1"),
		TYPE_ICE_BRENT("ICE Brent spread-ICE Brent spread-EXCHANGE-ICE","ICE Brent","1"),
		TYPE_EFS1("EFS #1-EFS-Platts-ICE-DUBAI SWAP","EFS #1","1"),
		TYPE_EFS2("EFS #2-EFS-Platts-ICE-DUBAI SWAP","EFS #2","1"),
		TYPE_EFS3("EFS #3-EFS-Platts-ICE-DUBAI SWAP","EFS #3","1"),
		TYPE_DB_DUBAI1("DTD Brent/Dubai #1-DTD Brent/Dubai-Platts-DTD Brent-Dubai","DTD Brent/Dubai #1","1"),
		TYPE_DB_DUBAI2("DTD Brent/Dubai #2-DTD Brent/Dubai-Platts-DTD Brent-Dubai","DTD Brent/Dubai #2","1"),
		TYPE_DB_DUBAI3("DTD Brent/Dubai #3-DTD Brent/Dubai-Platts-DTD Brent-Dubai","DTD Brent/Dubai #3","1"),
		TYPE_WTI_BRENT1("WTI/Brent #1-WTI/Brent-EXCHANGE-WTI-BRENT","WTI/Brent #1","1"),
		TYPE_WTI_BRENT2("WTI/Brent #2-WTI/Brent-EXCHANGE-WTI-BRENT","WTI/Brent #2","1"),
		TYPE_WTI_BRENT3("WTI/Brent #3-WTI/Brent-EXCHANGE-WTI-BRENT","WTI/Brent #3","1"),
		;
		OIL_TYPE(String symbolCode,String crudeNameE,String type){
    		this.symbolCode = symbolCode;
    		this.crudeNameE = crudeNameE;
    		this.type = type;
        	
    	}
		private String symbolCode; //合约代码
		private String crudeNameE; //抓取id
		private String type; //1原油发布平台-期纸货  2原油发布平台-OSP
		
		
		public String getCrudeNameE() {
			return crudeNameE;
		}
		public String getSymbolCode() {
			return symbolCode;
		}
		public String getType() {
			return type;
		}
		
	}
}
