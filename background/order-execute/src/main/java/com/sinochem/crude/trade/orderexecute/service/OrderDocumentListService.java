package com.sinochem.crude.trade.orderexecute.service;

import java.util.Map;
import java.util.List;

import com.github.pagehelper.Page;
//import com.runyi.ryplat.api.commons.SimplePageInfo;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.orderexecute.domain.OrderDocumentList;
import com.sinochem.crude.trade.orderexecute.dao.OrderDocumentListMapper; 

public interface OrderDocumentListService {
	
	public abstract OrderDocumentListMapper getMapper(); 
	
	/**
	 * 新增
	 */
	int insertRecord(OrderDocumentList orderDocumentList);
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	public int deleteById(Long documentId) throws BizException;
	
	/* *//**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！ 将flag改为0
     *//*
    public int deleteRecords(Map<String, Object>  map);*/
   
	/**
	 * 根据主键-修改对象
	 */
	int updateRecordById(OrderDocumentList orderDocumentList) throws BizException;
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(Map<String, Object> map);
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(OrderDocumentList orderDocumentList);
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！ 将flag改为0
     */
	int updateDeleteRecords(Map<String, Object> map);
	/**
	 * 根据主键-查询对象
	 */
	OrderDocumentList findByPrimaryKey(Long documentId);

	/**
	 * 根据uuid查询对象
	 */
	OrderDocumentList findByUuid(String uuid);
 
	
	/**
	 * 根据对象-查询对象列表
	 */
	List<OrderDocumentList> queryByEntitys(OrderDocumentList orderDocumentList);
		
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
