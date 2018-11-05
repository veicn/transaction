package com.sinochem.crude.trade.orderexecute.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
//import com.runyi.ryplat.api.commons.SimplePageInfo;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.orderexecute.dao.OrderPriceMapper;
import com.sinochem.crude.trade.orderexecute.domain.OrderPrice;
import com.sinochem.crude.trade.orderexecute.exception.OrderExecException;
import com.sinochem.crude.trade.orderexecute.service.OrderPriceService;

@Service
public class OrderPriceServiceImpl implements OrderPriceService {
	@Autowired
	private OrderPriceMapper orderPriceMapper;
	
	public OrderPriceMapper getMapper(){
		return orderPriceMapper;
	} 
	
	/**
	 * 新增
	 */
	@Override
	public long insertRecord(OrderPrice orderprice){
		 return orderPriceMapper.insertRecord(orderprice);
	}
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	@Override
	public int deleteById(Long orderPriceId) throws BizException{
		if (orderPriceId == null) {
			throw new OrderExecException("orderexecute.code.00131","id 为空，不能修改","id");
		}
		return orderPriceMapper.deleteById(orderPriceId);
	}
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    @Override
    public int deleteRecords(OrderPrice  record){
    	return orderPriceMapper.deleteRecords(record);
    }
	
	/**
	 * 根据主键-修改对象
	 */
	@Override
	public int updateRecordById(OrderPrice orderPrice) throws BizException{
		if ( orderPrice.getOrderPriceId() == null  ) {
			throw new OrderExecException("orderexecute.code.00131","orderPriceId 为空，不能修改","orderPriceId");
		}
		return orderPriceMapper.updateRecordById(orderPrice);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(Map<String, Object> map) {
		return orderPriceMapper.updateRecords(map);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(OrderPrice orderPrice){
		return orderPriceMapper.updateRecords(orderPrice.toMap());
	}
	
	
	/**
	 * 根据主键-查询对象
	 */
	@Override
	public OrderPrice findByPrimaryKey(Long orderPriceId){
		return  orderPriceMapper.findByPrimaryKey(orderPriceId);	
	}
	
	/**
	 * 根据uuid查询对象
	 */
	@Override
	public OrderPrice findByUuid(String uuid){
		return  orderPriceMapper.findByUuid(uuid);	
	}
	
	/**
	 * 根据对象-查询对象列表
	 */
	@Override
	public List<OrderPrice> queryByEntitys(OrderPrice orderPrice){
		return  orderPriceMapper.queryByEntitys(orderPrice);
	}
	
	/**
	 * 根据条件-查询全部
	 */
	@Override
	public List<Map<String, Object>> queryRecords(Map<String, Object> map){
		return orderPriceMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-分页查询
	 */
	@Override
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) orderPriceMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-查询记录条数
	 */
	@Override
	public int countRecords(Map<String, Object> map){
		return orderPriceMapper.countRecords(map);
	}
	
	//**************************以下方法为开发者补充*********************************/
	
	@Override
	public OrderPrice findByOrderId(Long orderId) {
		if(orderId == null){
			return null;
		}
		
		OrderPrice query = new OrderPrice();
		query.setOrderId(orderId);
		
		List<OrderPrice> list = orderPriceMapper.queryByEntitys(query);
		if(list != null && !list.isEmpty()){
			if(list.size() > 1) {
				throw new OrderExecException("orderexecute.code.00083","预期只有一条，实际返回多条");
			}
			return list.get(0);
		}
		
		return null;
	}

	public void updatePayDate(Long orderPriceId, Date payDate) {
		
		if(payDate != null) {
			OrderPrice orderPriceUpdate = new OrderPrice();
			orderPriceUpdate.setPayDate(payDate);
			orderPriceUpdate.setOrderPriceId(orderPriceId);
			
			orderPriceMapper.updateRecordById(orderPriceUpdate);
		}
	}
}
