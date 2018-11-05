package com.sinochem.crude.trade.orderexecute.dao;

import java.util.List;
import java.util.Map;

public interface OrderMessagePushMapper {

	/**
	 * 取得订单信息和执行员
	 */
	public List<Map<String, Object>> getOrderAndPeison(Map<String, Object> map);
	
	public List<Map<String, Object>> checkT10(String documentType);
	
	public List<Map<String, Object>> checkT11(String documentType);
	
	public List<Map<String, Object>> checkT12(String documentType);
	
	public List<Map<String, Object>> checkT13(String documentType);
	
	public List<Map<String, Object>> checkT14(Map<String, Object> map);
	
	public List<Map<String, Object>> checkT20(String documentType);
	
	public List<Map<String, Object>> checkT21(String documentType);
	
	public List<Map<String, Object>> checkT22(String documentType);
	
	public List<Map<String, Object>> checkT24(Map<String, Object> map);
}
