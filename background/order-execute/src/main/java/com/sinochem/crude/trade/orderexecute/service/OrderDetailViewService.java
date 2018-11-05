package com.sinochem.crude.trade.orderexecute.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
//import com.runyi.ryplat.api.commons.SimplePageInfo;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.orderexecute.dao.OrderDetailViewMapper;
import com.sinochem.crude.trade.orderexecute.domain.OrderDetailView;

public interface OrderDetailViewService {
	
	public abstract OrderDetailViewMapper getMapper(); 
	
	/**
	 * 根据主键-查询对象
	 */
	OrderDetailView findByPrimaryKey(Long id);

	/**
	 * 根据uuid查询对象
	 */
	OrderDetailView findByUuid(String uuid);
 
	
	/**
	 * 根据对象-查询对象列表
	 */
	List<OrderDetailView> queryByEntitys(OrderDetailView order);
		
	/**
	 * 根据条件-查询全部
	 */
	List<Map<String, Object>> queryRecords(Map<String, Object> map);
	
	/**
	 * 根据条件-分页查询
	 */
	Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo);
	
	/**
	 * 根据条件-查询记录条数
	 */
	int countRecords(Map<String, Object> map); 
	
	

	//**************************以下方法为开发者补充*********************************/
	
}
