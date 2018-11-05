package com.sinochem.crude.trade.transport.query;

import java.util.List;

public class Trader {
	/**
	 * id
	 */
	private long traderId;
	/**
	 * 名称
	 */
	private String  traderName;
	
	/**
	 * 会员资质类型 1炼厂,2贸易商,3商检,4船东,5船经纪人,6船代,7转租船东
	 */
	private String type;
	
	/**
	 * 类型List
	 */
	private List<String> typeList;
	
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<String> getTypeList() {
		return typeList;
	}
	public void setTypeList(List<String> typeList) {
		this.typeList = typeList;
	}
	public long getTraderId() {
		return traderId;
	}
	public void setTraderId(long traderId) {
		this.traderId = traderId;
	}
	public String getTraderName() {
		return traderName;
	}
	public void setTraderName(String traderName) {
		this.traderName = traderName;
	}
	
}
