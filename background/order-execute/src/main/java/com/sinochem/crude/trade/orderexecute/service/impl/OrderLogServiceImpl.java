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
import com.sinochem.crude.trade.orderexecute.domain.OrderLog;
import com.sinochem.crude.trade.orderexecute.exception.OrderExecException;
import com.sinochem.crude.trade.orderexecute.dao.OrderLogMapper;
import com.sinochem.crude.trade.orderexecute.service.OrderLogService;

@Service
public class OrderLogServiceImpl implements OrderLogService {
	@Autowired
	private OrderLogMapper orderLogMapper;
	
	public OrderLogMapper getMapper(){
		return orderLogMapper;
	} 
	
	/**
	 * 新增
	 */
	@Override
	public int insertRecord(OrderLog orderlog){
		 return orderLogMapper.insertRecord(orderlog);
	}
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	@Override
	public int deleteById(Long logId) throws BizException{
		if (logId == null) {
			throw new OrderExecException("orderexecute.code.00131","id 为空，不能修改","id");
		}
		return orderLogMapper.deleteById(logId);
	}
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    @Override
    public int deleteRecords(OrderLog  record){
    	return orderLogMapper.deleteRecords(record);
    }
	
	/**
	 * 根据主键-修改对象
	 */
	@Override
	public int updateRecordById(OrderLog orderLog) throws BizException{
		if ( orderLog.getLogId() == null  ) {
			throw new OrderExecException("orderexecute.code.00131","logId 为空，不能修改","logId");
		}
		return orderLogMapper.updateRecordById(orderLog);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(Map<String, Object> map) {
		return orderLogMapper.updateRecords(map);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(OrderLog orderLog){
		return orderLogMapper.updateRecords(orderLog.toMap());
	}
	
	
	/**
	 * 根据主键-查询对象
	 */
	@Override
	public OrderLog findByPrimaryKey(Long logId){
		return  orderLogMapper.findByPrimaryKey(logId);	
	}
	
	/**
	 * 根据uuid查询对象
	 */
	@Override
	public OrderLog findByUuid(String uuid){
		return  orderLogMapper.findByUuid(uuid);	
	}
	
	/**
	 * 根据对象-查询对象列表
	 */
	@Override
	public List<OrderLog> queryByEntitys(OrderLog orderLog){
		return  orderLogMapper.queryByEntitys(orderLog);
	}
	
	/**
	 * 根据条件-查询全部
	 */
	@Override
	public List<Map<String, Object>> queryRecords(Map<String, Object> map){
		return orderLogMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-分页查询
	 */
	@Override
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) orderLogMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-查询记录条数
	 */
	@Override
	public int countRecords(Map<String, Object> map){
		return orderLogMapper.countRecords(map);
	}
	
	//**************************以下方法为开发者补充*********************************/
	
}
