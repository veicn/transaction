package com.sinochem.crude.trade.orderexecute.service;

import java.util.Map;
import java.util.List;

import com.github.pagehelper.Page;
//import com.runyi.ryplat.api.commons.SimplePageInfo;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.orderexecute.domain.OrderExecutePerson;
import com.sinochem.crude.trade.orderexecute.dao.OrderExecutePersonMapper; 

public interface OrderExecutePersonService {
	
	public abstract OrderExecutePersonMapper getMapper(); 
	
	/**
	 * 新增
	 */
	int insertRecord(OrderExecutePerson orderExecutePerson);
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	public int deleteById(Long executePersonId) throws BizException;
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    public int deleteRecords(OrderExecutePerson  record);
	
	/**
	 * 根据主键-修改对象
	 */
	int updateRecordById(OrderExecutePerson orderExecutePerson) throws BizException;
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(Map<String, Object> map);
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(OrderExecutePerson orderExecutePerson);
	
	
	/**
	 * 根据主键-查询对象
	 */
	OrderExecutePerson findByPrimaryKey(Long executePersonId);

	/**
	 * 根据uuid查询对象
	 */
	OrderExecutePerson findByUuid(String uuid);
 
	
	/**
	 * 根据对象-查询对象列表
	 */
	List<OrderExecutePerson> queryByEntitys(OrderExecutePerson orderExecutePerson);
		
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
