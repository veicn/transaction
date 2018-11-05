package com.sinochem.crude.trade.orderexecute.service.impl;

import java.util.Map;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
//import com.runyi.ryplat.api.commons.SimplePageInfo;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.orderexecute.domain.OrderExecutePerson;
import com.sinochem.crude.trade.orderexecute.exception.OrderExecException;
import com.sinochem.crude.trade.orderexecute.dao.OrderExecutePersonMapper;
import com.sinochem.crude.trade.orderexecute.service.OrderExecutePersonService;

@Service
public class OrderExecutePersonServiceImpl implements OrderExecutePersonService {
	@Autowired
	private OrderExecutePersonMapper orderExecutePersonMapper;
	
	public OrderExecutePersonMapper getMapper(){
		return orderExecutePersonMapper;
	} 
	
	/**
	 * 新增
	 */
	@Override
	public int insertRecord(OrderExecutePerson orderexecuteperson){
		 return orderExecutePersonMapper.insertRecord(orderexecuteperson);
	}
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	@Override
	public int deleteById(Long executePersonId) throws BizException{
		if (executePersonId == null) {
			throw new OrderExecException("orderexecute.code.00131","id 为空，不能修改","id");
		}
		return orderExecutePersonMapper.deleteById(executePersonId);
	}
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    @Override
    public int deleteRecords(OrderExecutePerson  record){
    	return orderExecutePersonMapper.deleteRecords(record);
    }
	
	/**
	 * 根据主键-修改对象
	 */
	@Override
	public int updateRecordById(OrderExecutePerson orderExecutePerson) throws BizException{
		if ( orderExecutePerson.getExecutePersonId() == null  ) {
			throw new OrderExecException("orderexecute.code.00131","executePersonId 为空，不能修改","executePersonId");
		}
		return orderExecutePersonMapper.updateRecordById(orderExecutePerson);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(Map<String, Object> map) {
		return orderExecutePersonMapper.updateRecords(map);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(OrderExecutePerson orderExecutePerson){
		return orderExecutePersonMapper.updateRecords(orderExecutePerson.toMap());
	}
	
	
	/**
	 * 根据主键-查询对象
	 */
	@Override
	public OrderExecutePerson findByPrimaryKey(Long executePersonId){
		return  orderExecutePersonMapper.findByPrimaryKey(executePersonId);	
	}
	
	/**
	 * 根据uuid查询对象
	 */
	@Override
	public OrderExecutePerson findByUuid(String uuid){
		return  orderExecutePersonMapper.findByUuid(uuid);	
	}
	
	/**
	 * 根据对象-查询对象列表
	 */
	@Override
	public List<OrderExecutePerson> queryByEntitys(OrderExecutePerson orderExecutePerson){
		return  orderExecutePersonMapper.queryByEntitys(orderExecutePerson);
	}
	
	/**
	 * 根据条件-查询全部
	 */
	@Override
	public List<Map<String, Object>> queryRecords(Map<String, Object> map){
		return orderExecutePersonMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-分页查询
	 */
	@Override
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) orderExecutePersonMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-查询记录条数
	 */
	@Override
	public int countRecords(Map<String, Object> map){
		return orderExecutePersonMapper.countRecords(map);
	}
	
	//**************************以下方法为开发者补充*********************************/
	
}
