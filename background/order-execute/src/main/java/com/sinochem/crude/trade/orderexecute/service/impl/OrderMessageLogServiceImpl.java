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
import com.sinochem.crude.trade.orderexecute.domain.OrderMessageLog;
import com.sinochem.crude.trade.orderexecute.exception.OrderExecException;
import com.sinochem.crude.trade.orderexecute.dao.OrderMessageLogMapper;
import com.sinochem.crude.trade.orderexecute.service.OrderMessageLogService;

@Service
public class OrderMessageLogServiceImpl implements OrderMessageLogService {
	@Autowired
	private OrderMessageLogMapper orderMessageLogMapper;
	
	public OrderMessageLogMapper getMapper(){
		return orderMessageLogMapper;
	} 
	
	/**
	 * 新增
	 */
	@Override
	public int insertRecord(OrderMessageLog ordermessagelog){
		 return orderMessageLogMapper.insertRecord(ordermessagelog);
	}
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	@Override
	public int deleteById(Long messageId) throws BizException{
		if (messageId == null) {
			throw new OrderExecException("orderexecute.code.00131","id 为空，不能修改","id");
		}
		return orderMessageLogMapper.deleteById(messageId);
	}
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    @Override
    public int deleteRecords(OrderMessageLog  record){
    	return orderMessageLogMapper.deleteRecords(record);
    }
	
	/**
	 * 根据主键-修改对象
	 */
	@Override
	public int updateRecordById(OrderMessageLog orderMessageLog) throws BizException{
		if ( orderMessageLog.getMessageId() == null  ) {
			throw new OrderExecException("orderexecute.code.00131","messageId 为空，不能修改","messageId");
		}
		return orderMessageLogMapper.updateRecordById(orderMessageLog);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(Map<String, Object> map) {
		return orderMessageLogMapper.updateRecords(map);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(OrderMessageLog orderMessageLog){
		return orderMessageLogMapper.updateRecords(orderMessageLog.toMap());
	}
	
	
	/**
	 * 根据主键-查询对象
	 */
	@Override
	public OrderMessageLog findByPrimaryKey(Long messageId){
		return  orderMessageLogMapper.findByPrimaryKey(messageId);	
	}
	
	/**
	 * 根据uuid查询对象
	 */
	@Override
	public OrderMessageLog findByUuid(String uuid){
		return  orderMessageLogMapper.findByUuid(uuid);	
	}
	
	/**
	 * 根据对象-查询对象列表
	 */
	@Override
	public List<OrderMessageLog> queryByEntitys(OrderMessageLog orderMessageLog){
		return  orderMessageLogMapper.queryByEntitys(orderMessageLog);
	}
	
	/**
	 * 根据条件-查询全部
	 */
	@Override
	public List<Map<String, Object>> queryRecords(Map<String, Object> map){
		return orderMessageLogMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-分页查询
	 */
	@Override
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) orderMessageLogMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-查询记录条数
	 */
	@Override
	public int countRecords(Map<String, Object> map){
		return orderMessageLogMapper.countRecords(map);
	}
	
	//**************************以下方法为开发者补充*********************************/
	
}
