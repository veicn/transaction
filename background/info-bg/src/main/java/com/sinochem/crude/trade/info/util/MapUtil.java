package com.sinochem.crude.trade.info.util;

import java.util.HashMap;



public class MapUtil extends HashMap<String,Object>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//返回结果
	public MapUtil() {
		this.put("status", "0");
		this.put("message", "");
		this.put("dataList", null);
	}
	public MapUtil setNo(String message) {
		this.put("status", "0");
		this.put("message", message);
		this.put("dataList", null);
		return this;
	}
	public MapUtil setOk(String message,Object dataList) {
		this.put("status", "1");
		this.put("message", message);
		this.put("dataList", dataList);
		return this;
	}
	public MapUtil appSetOk(String message,Object dataList) {
		this.put("status", "1");
		this.put("message", message);
		this.put("datas", dataList);
		return this;
	}
	public MapUtil appSetNo(String message) {
		this.put("status", "0");
		this.put("message", message);
		this.put("datas", null);
		return this;
	}
}
