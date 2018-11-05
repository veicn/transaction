package com.sinochem.crude.trade.orderexecute.service.impl;

import java.util.Map;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
//import com.runyi.ryplat.api.commons.SimplePageInfo;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.orderexecute.domain.OrderParty;
import com.sinochem.crude.trade.orderexecute.exception.OrderExecException;
import com.sinochem.crude.trade.orderexecute.dao.OrderPartyMapper;
import com.sinochem.crude.trade.orderexecute.service.OrderPartyService;

@Service
public class OrderPartyServiceImpl implements OrderPartyService {
	@Autowired
	private OrderPartyMapper orderPartyMapper;
	
	public OrderPartyMapper getMapper(){
		return orderPartyMapper;
	} 
	
	/**
	 * 新增
	 */
	@Override
	public int insertRecord(OrderParty orderparty){
		 return orderPartyMapper.insertRecord(orderparty);
	}
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	@Override
	public int deleteById(Long partyId) throws BizException{
		if (partyId == null) {
			throw new OrderExecException("orderexecute.code.00131","id 为空，不能修改","id");
		}
		return orderPartyMapper.deleteById(partyId);
	}
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    @Override
    public int deleteRecords(OrderParty  record){
    	return orderPartyMapper.deleteRecords(record);
    }
	
	/**
	 * 根据主键-修改对象
	 */
	@Override
	public int updateRecordById(OrderParty orderParty) throws BizException{
		if ( orderParty.getPartyId() == null  ) {
			throw new OrderExecException("orderexecute.code.00131","partyId 为空，不能修改","partyId");
		}
		return orderPartyMapper.updateRecordById(orderParty);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(Map<String, Object> map) {
		return orderPartyMapper.updateRecords(map);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(OrderParty orderParty){
		return orderPartyMapper.updateRecords(orderParty.toMap());
	}
	
	
	/**
	 * 根据主键-查询对象
	 */
	@Override
	public OrderParty findByPrimaryKey(Long partyId){
		return  orderPartyMapper.findByPrimaryKey(partyId);	
	}
	
	/**
	 * 根据uuid查询对象
	 */
	@Override
	public OrderParty findByUuid(String uuid){
		return  orderPartyMapper.findByUuid(uuid);	
	}
	
	/**
	 * 根据对象-查询对象列表
	 */
	@Override
	public List<OrderParty> queryByEntitys(OrderParty orderParty){
		return  orderPartyMapper.queryByEntitys(orderParty);
	}
	
	/**
	 * 根据条件-查询全部
	 */
	@Override
	public List<Map<String, Object>> queryRecords(Map<String, Object> map){
		return orderPartyMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-分页查询
	 */
	@Override
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) orderPartyMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-查询记录条数
	 */
	@Override
	public int countRecords(Map<String, Object> map){
		return orderPartyMapper.countRecords(map);
	}
	
	//**************************以下方法为开发者补充*********************************/
	
	@Override
	public OrderParty findByOrderIdAndPartyType(Long orderId, String partyType){
		
		if(orderId == null || StringUtils.isEmpty(partyType)){
			return null;
		}
		
		OrderParty query = new OrderParty();
		query.setOrderId(orderId);
		query.setPartyType(partyType);
		
		List<OrderParty> list = orderPartyMapper.queryByEntitys(query);
		
		if(list != null && !list.isEmpty()){
			return list.get(0);
		}
		
		return null;
	}

	@Override
	public OrderParty findByOrderIdAndCustomerId(Long orderId, Long customerId) {
		OrderParty query = new OrderParty();
		query.setOrderId(orderId);
		query.setCustomerId(customerId);
		
		List<OrderParty> list = orderPartyMapper.queryByEntitys(query);
		if(list != null && !list.isEmpty()){
			return list.get(0);
		}
		
		return null;
	}
	
}
