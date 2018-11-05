package com.sinochem.crude.trade.orderexecute.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
//import com.runyi.ryplat.api.commons.SimplePageInfo;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.orderexecute.dao.OrderFeeItemMapper;
import com.sinochem.crude.trade.orderexecute.domain.OrderFeeItem;
import com.sinochem.crude.trade.orderexecute.exception.OrderExecException;
import com.sinochem.crude.trade.orderexecute.service.OrderFeeItemService;

@Service
public class OrderFeeItemServiceImpl implements OrderFeeItemService {
	@Autowired
	private OrderFeeItemMapper orderFeeItemMapper;
	
	public OrderFeeItemMapper getMapper(){
		return orderFeeItemMapper;
	} 
	
	/**
	 * 新增
	 */
	@Override
	public int insertRecord(OrderFeeItem orderfeeitem){
		 return orderFeeItemMapper.insertRecord(orderfeeitem);
	}
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	@Override
	public int deleteById(Long feeItemId) throws BizException{
		if (feeItemId == null) {
			throw new OrderExecException("orderexecute.code.00131","id 为空，不能修改","id");
		}
		return orderFeeItemMapper.deleteById(feeItemId);
	}
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    @Override
    public int deleteRecords(OrderFeeItem  record){
    	return orderFeeItemMapper.deleteRecords(record);
    }
	
	/**
	 * 根据主键-修改对象
	 */
	@Override
	public int updateRecordById(OrderFeeItem orderFeeItem) throws BizException{
		if ( orderFeeItem.getFeeItemId() == null  ) {
			throw new OrderExecException("orderexecute.code.00131","feeItemId 为空，不能修改","feeItemId");
		}
		return orderFeeItemMapper.updateRecordById(orderFeeItem);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(Map<String, Object> map) {
		return orderFeeItemMapper.updateRecords(map);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(OrderFeeItem orderFeeItem){
		return orderFeeItemMapper.updateRecords(orderFeeItem.toMap());
	}
	
	
	/**
	 * 根据主键-查询对象
	 */
	@Override
	public OrderFeeItem findByPrimaryKey(Long feeItemId){
		return  orderFeeItemMapper.findByPrimaryKey(feeItemId);	
	}
	
	/**
	 * 根据uuid查询对象
	 */
	@Override
	public OrderFeeItem findByUuid(String uuid){
		return  orderFeeItemMapper.findByUuid(uuid);	
	}
	
	/**
	 * 根据对象-查询对象列表
	 */
	@Override
	public List<OrderFeeItem> queryByEntitys(OrderFeeItem orderFeeItem){
		return  orderFeeItemMapper.queryByEntitys(orderFeeItem);
	}
	
	/**
	 * 根据条件-查询全部
	 */
	@Override
	public List<OrderFeeItem> queryRecords(Map<String, Object> map){
		return orderFeeItemMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-分页查询
	 */
	@Override
	public Page<OrderFeeItem> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<OrderFeeItem>) orderFeeItemMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-查询记录条数
	 */
	@Override
	public int countRecords(Map<String, Object> map){
		return orderFeeItemMapper.countRecords(map);
	}
	
	//**************************以下方法为开发者补充*********************************/
	
	
	/**
	 * 查询费用信息
	 */
	@Override
	public List<OrderFeeItem> selectOrderFeeItem(Long orderId) {
		// 查询费用信息
		return orderFeeItemMapper.selectOrderFeeItem(orderId);
	}
	
}
