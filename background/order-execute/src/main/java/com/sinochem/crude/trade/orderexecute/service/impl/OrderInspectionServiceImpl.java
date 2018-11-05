package com.sinochem.crude.trade.orderexecute.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
//import com.runyi.ryplat.api.commons.SimplePageInfo;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.common.utils.DateTimeUtils;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.orderexecute.dao.OrderInspectionMapper;
import com.sinochem.crude.trade.orderexecute.domain.OrderInspection;
import com.sinochem.crude.trade.orderexecute.exception.OrderExecException;
import com.sinochem.crude.trade.orderexecute.service.OrderInspectionService;

@Service
public class OrderInspectionServiceImpl implements OrderInspectionService {
	@Autowired
	private OrderInspectionMapper orderInspectionMapper;
	
	public OrderInspectionMapper getMapper(){
		return orderInspectionMapper;
	} 
	
	/**
	 * 新增
	 */
	@Override
	public int insertRecord(OrderInspection orderinspection){
		 return orderInspectionMapper.insertRecord(orderinspection);
	}
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	@Override
	public int deleteById(String id) throws BizException{
		if (id == null) {
			throw new BizException("id 为空，不能修改 ");
		}
		return orderInspectionMapper.deleteById(id);
	}
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    @Override
    public int deleteRecords(OrderInspection  record){
    	return orderInspectionMapper.deleteRecords(record);
    }
	
	/**
	 * 根据主键-修改对象
	 */
	@Override
	public int updateRecordById(OrderInspection orderInspection) throws BizException{
		if ( orderInspection.getId() == null  ) {
			throw new BizException("id 为空，不能修改 ");
		}
		return orderInspectionMapper.updateRecordById(orderInspection);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(Map<String, Object> map) {
		return orderInspectionMapper.updateRecords(map);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(OrderInspection orderInspection){
		return orderInspectionMapper.updateRecords(orderInspection.toMap());
	}
	
	
	/**
	 * 根据主键-查询对象
	 */
	@Override
	public OrderInspection findByPrimaryKey(String id){
		return  orderInspectionMapper.findByPrimaryKey(id);	
	}
	
	/**
	 * 根据uuid查询对象
	 */
	@Override
	public OrderInspection findByUuid(String uuid){
		return  orderInspectionMapper.findByUuid(uuid);	
	}
	
	/**
	 * 根据对象-查询对象列表
	 */
	@Override
	public List<OrderInspection> queryByEntitys(OrderInspection orderInspection){
		return  orderInspectionMapper.queryByEntitys(orderInspection);
	}
	
	/**
	 * 根据条件-查询全部
	 */
	@Override
	public List<Map<String, Object>> queryRecords(Map<String, Object> map){
		return orderInspectionMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-分页查询
	 */
	@Override
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) orderInspectionMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-查询记录条数
	 */
	@Override
	public int countRecords(Map<String, Object> map){
		return orderInspectionMapper.countRecords(map);
	}
	
	//**************************以下方法为开发者补充*********************************/
	
	@Override
	public OrderInspection findByOrderNo(String orderNo) {
		OrderInspection query = new OrderInspection();
		query.setOrderNo(orderNo);
		List<OrderInspection> list = queryByEntitys(query);
		
		if(list != null && !list.isEmpty()) {
			return list.get(0);
		}
		
		return null;
	}

	@Override
	public int insertOrUpdateRecord(OrderInspection orderInspection,CurrentUser user) {
		if(StringUtils.isBlank(orderInspection.getOrderNo()) ||
				orderInspection.getOrderId() == null){
			throw new OrderExecException("orderexecute.code.00027", "缺少参数");
		}
		int num = 0;
		OrderInspection existEntity = findByOrderNo(orderInspection.getOrderNo());
		if(existEntity != null) {
			orderInspection.setId(existEntity.getId());
			orderInspection.setModifyDate(DateTimeUtils.currentDate());
			orderInspection.setModifyPerson(String.valueOf(user.getMemberId()));
			
			num = updateRecordById(orderInspection);
		}else {
			orderInspection.setCreateDate(DateTimeUtils.currentDate());
			orderInspection.setCreatePerson(String.valueOf(user.getMemberId()));
			
			num = insertRecord(orderInspection);
		}
		
		return num;
	}
	
}
