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
import com.sinochem.crude.trade.orderexecute.domain.OrderDocumentList;
import com.sinochem.crude.trade.orderexecute.exception.OrderExecException;
import com.sinochem.crude.trade.orderexecute.dao.OrderDocumentListMapper;
import com.sinochem.crude.trade.orderexecute.service.OrderDocumentListService;

@Service
public class OrderDocumentListServiceImpl implements OrderDocumentListService {
	@Autowired
	private OrderDocumentListMapper orderDocumentListMapper;
	
	public OrderDocumentListMapper getMapper(){
		return orderDocumentListMapper;
	} 
	
	/**
	 * 新增
	 */
	@Override
	public int insertRecord(OrderDocumentList orderdocumentlist){
		 return orderDocumentListMapper.insertRecord(orderdocumentlist);
	}
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	@Override
	public int deleteById(Long documentId) throws BizException{
		if (documentId == null) {
			throw new OrderExecException("orderexecute.code.00131","id 为空，不能修改","id");
		}
		return orderDocumentListMapper.deleteById(documentId);
	}
	
	
	
	/**
	 * 根据主键-修改对象
	 */
	@Override
	public int updateRecordById(OrderDocumentList orderDocumentList) throws BizException{
		if ( orderDocumentList.getDocumentId() == null  ) {
			throw new OrderExecException("orderexecute.code.00131","documentId 为空，不能修改","documentId");
		}
		return orderDocumentListMapper.updateRecordById(orderDocumentList);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(Map<String, Object> map) {
		return orderDocumentListMapper.updateRecords(map);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(OrderDocumentList orderDocumentList){
		return orderDocumentListMapper.updateRecords(orderDocumentList.toMap());
	}
	
	
	/**
	 * 根据主键-查询对象
	 */
	@Override
	public OrderDocumentList findByPrimaryKey(Long documentId){
		return  orderDocumentListMapper.findByPrimaryKey(documentId);	
	}
	
	/**
	 * 根据uuid查询对象
	 */
	@Override
	public OrderDocumentList findByUuid(String uuid){
		return  orderDocumentListMapper.findByUuid(uuid);	
	}
	
	/**
	 * 根据对象-查询对象列表
	 */
	@Override
	public List<OrderDocumentList> queryByEntitys(OrderDocumentList orderDocumentList){
		return  orderDocumentListMapper.queryByEntitys(orderDocumentList);
	}
	
	/**
	 * 根据条件-查询全部
	 */
	@Override
	public List<Map<String, Object>> queryRecords(Map<String, Object> map){
		return orderDocumentListMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-分页查询
	 */
	@Override
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) orderDocumentListMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-查询记录条数
	 */
	@Override
	public int countRecords(Map<String, Object> map){
		return orderDocumentListMapper.countRecords(map);
	}

	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！ 将flag改为0
     */
	@Override
	public int updateDeleteRecords(Map<String, Object> map) {
		return orderDocumentListMapper.updateDeleteRecords(map);
	}
	//**************************以下方法为开发者补充*********************************/

	
	
}
