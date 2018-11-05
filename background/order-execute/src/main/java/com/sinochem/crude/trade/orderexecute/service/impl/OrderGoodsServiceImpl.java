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
import com.sinochem.crude.trade.orderexecute.domain.OrderGoods;
import com.sinochem.crude.trade.orderexecute.exception.OrderExecException;
import com.sinochem.crude.trade.orderexecute.dao.OrderGoodsMapper;
import com.sinochem.crude.trade.orderexecute.service.OrderGoodsService;

@Service
public class OrderGoodsServiceImpl implements OrderGoodsService {
	@Autowired
	private OrderGoodsMapper orderGoodsMapper;
	
	public OrderGoodsMapper getMapper(){
		return orderGoodsMapper;
	} 
	
	/**
	 * 新增
	 */
	@Override
	public int insertRecord(OrderGoods ordergoods){
		 return orderGoodsMapper.insertRecord(ordergoods);
	}
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	@Override
	public int deleteById(Long orderGoodsId) throws BizException{
		if (orderGoodsId == null) {
			throw new OrderExecException("orderexecute.code.00131","id 为空，不能修改","id");
		}
		return orderGoodsMapper.deleteById(orderGoodsId);
	}
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    @Override
    public int deleteRecords(OrderGoods  record){
    	return orderGoodsMapper.deleteRecords(record);
    }
	
	/**
	 * 根据主键-修改对象
	 */
	@Override
	public int updateRecordById(OrderGoods orderGoods) throws BizException{
		if ( orderGoods.getOrderGoodsId() == null  ) {
			throw new OrderExecException("orderexecute.code.00131","orderGoodsId 为空，不能修改","orderGoodsId");
		}
		return orderGoodsMapper.updateRecordById(orderGoods);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(Map<String, Object> map) {
		return orderGoodsMapper.updateRecords(map);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(OrderGoods orderGoods){
		return orderGoodsMapper.updateRecords(orderGoods.toMap());
	}
	
	
	/**
	 * 根据主键-查询对象
	 */
	@Override
	public OrderGoods findByPrimaryKey(Long orderGoodsId){
		return  orderGoodsMapper.findByPrimaryKey(orderGoodsId);	
	}
	
	/**
	 * 根据uuid查询对象
	 */
	@Override
	public OrderGoods findByUuid(String uuid){
		return  orderGoodsMapper.findByUuid(uuid);	
	}
	
	/**
	 * 根据对象-查询对象列表
	 */
	@Override
	public List<OrderGoods> queryByEntitys(OrderGoods orderGoods){
		return  orderGoodsMapper.queryByEntitys(orderGoods);
	}
	
	/**
	 * 根据条件-查询全部
	 */
	@Override
	public List<Map<String, Object>> queryRecords(Map<String, Object> map){
		return orderGoodsMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-分页查询
	 */
	@Override
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) orderGoodsMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-查询记录条数
	 */
	@Override
	public int countRecords(Map<String, Object> map){
		return orderGoodsMapper.countRecords(map);
	}

	//**************************以下方法为开发者补充*********************************/
	
	@Override
	public OrderGoods findByOrderId(Long orderId) {
		if(orderId == null){
			return null;
		}
		
		OrderGoods query = new OrderGoods();
		query.setOrderId(orderId);
		List<OrderGoods> list = orderGoodsMapper.queryByEntitys(query);
		if(list != null && !list.isEmpty()){
			return list.get(0);
		}
		
		return null;
	}
	
}
