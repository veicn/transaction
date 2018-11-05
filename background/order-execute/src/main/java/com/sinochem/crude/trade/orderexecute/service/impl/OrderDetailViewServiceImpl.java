package com.sinochem.crude.trade.orderexecute.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
//import com.runyi.ryplat.api.commons.SimplePageInfo;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.orderexecute.dao.OrderDetailViewMapper;
import com.sinochem.crude.trade.orderexecute.domain.OrderDetailView;
import com.sinochem.crude.trade.orderexecute.service.OrderDetailViewService;

@Service
public class OrderDetailViewServiceImpl implements OrderDetailViewService {
	@Autowired
	private OrderDetailViewMapper orderDetailMapper;
	
	public OrderDetailViewMapper getMapper(){
		return orderDetailMapper;
	} 
	
	
	/**
	 * 根据主键-查询对象
	 */
	@Override
	public OrderDetailView findByPrimaryKey(Long id){
		return  orderDetailMapper.findByPrimaryKey(id);	
	}
	
	/**
	 * 根据uuid查询对象
	 */
	@Override
	public OrderDetailView findByUuid(String uuid){
		return  orderDetailMapper.findByUuid(uuid);	
	}
	
	/**
	 * 根据对象-查询对象列表
	 */
	@Override
	public List<OrderDetailView> queryByEntitys(OrderDetailView order){
		return  orderDetailMapper.queryByEntitys(order);
	}
	
	/**
	 * 根据条件-查询全部
	 */
	@Override
	public List<Map<String, Object>> queryRecords(Map<String, Object> map){
		return orderDetailMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-分页查询
	 */
	@Override
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) orderDetailMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-查询记录条数
	 */
	@Override
	public int countRecords(Map<String, Object> map){
		return orderDetailMapper.countRecords(map);
	}
	
	//**************************以下方法为开发者补充*********************************/
	
}
