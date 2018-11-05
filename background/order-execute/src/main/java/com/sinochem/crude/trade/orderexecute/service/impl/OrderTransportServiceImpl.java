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
import com.sinochem.crude.trade.orderexecute.domain.OrderTransport;
import com.sinochem.crude.trade.orderexecute.exception.OrderExecException;
import com.sinochem.crude.trade.orderexecute.dao.OrderTransportMapper;
import com.sinochem.crude.trade.orderexecute.service.OrderTransportService;

@Service
public class OrderTransportServiceImpl implements OrderTransportService {
	@Autowired
	private OrderTransportMapper orderTransportMapper;
	
	public OrderTransportMapper getMapper(){
		return orderTransportMapper;
	} 
	
	/**
	 * 新增
	 */
	@Override
	public int insertRecord(OrderTransport ordertransport){
		 return orderTransportMapper.insertRecord(ordertransport);
	}
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	@Override
	public int deleteById(Long orderTransportId) throws BizException{
		if (orderTransportId == null) {
			throw new OrderExecException("orderexecute.code.00131","id 为空，不能修改","id");
		}
		return orderTransportMapper.deleteById(orderTransportId);
	}
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    @Override
    public int deleteRecords(OrderTransport  record){
    	return orderTransportMapper.deleteRecords(record);
    }
	
	/**
	 * 根据主键-修改对象
	 */
	@Override
	public int updateRecordById(OrderTransport orderTransport) throws BizException{
		if ( orderTransport.getOrderTransportId() == null  ) {
			throw new OrderExecException("orderexecute.code.00131","orderTransportId 为空，不能修改","orderTransportId");
		}
		return orderTransportMapper.updateRecordById(orderTransport);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(Map<String, Object> map) {
		return orderTransportMapper.updateRecords(map);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(OrderTransport orderTransport){
		return orderTransportMapper.updateRecords(orderTransport.toMap());
	}
	
	
	/**
	 * 根据主键-查询对象
	 */
	@Override
	public OrderTransport findByPrimaryKey(Long orderTransportId){
		return  orderTransportMapper.findByPrimaryKey(orderTransportId);	
	}
	
	/**
	 * 根据uuid查询对象
	 */
	@Override
	public OrderTransport findByUuid(String uuid){
		return  orderTransportMapper.findByUuid(uuid);	
	}
	
	/**
	 * 根据对象-查询对象列表
	 */
	@Override
	public List<OrderTransport> queryByEntitys(OrderTransport orderTransport){
		return  orderTransportMapper.queryByEntitys(orderTransport);
	}
	
	/**
	 * 根据条件-查询全部
	 */
	@Override
	public List<Map<String, Object>> queryRecords(Map<String, Object> map){
		return orderTransportMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-分页查询
	 */
	@Override
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) orderTransportMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-查询记录条数
	 */
	@Override
	public int countRecords(Map<String, Object> map){
		return orderTransportMapper.countRecords(map);
	}

	//**************************以下方法为开发者补充*********************************/
	
	@Override
	public OrderTransport findByOrderId(Long orderId) {
		if(orderId == null){
			return null;
		}
		
		OrderTransport query = new OrderTransport();
		query.setOrderId(orderId);
		List<OrderTransport> list = orderTransportMapper.queryByEntitys(query);
		
		if(list != null && !list.isEmpty()){
			return list.get(0);
		}
		
		return null;
	}
	
}
