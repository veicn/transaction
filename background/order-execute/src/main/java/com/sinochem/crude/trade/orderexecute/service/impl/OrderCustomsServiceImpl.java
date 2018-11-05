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
import com.sinochem.crude.trade.common.utils.DateTimeUtils;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.orderexecute.domain.OrderCustoms;
import com.sinochem.crude.trade.orderexecute.exception.OrderExecException;
import com.sinochem.crude.trade.orderexecute.dao.OrderCustomsMapper;
import com.sinochem.crude.trade.orderexecute.service.OrderCustomsService;

@Service
public class OrderCustomsServiceImpl implements OrderCustomsService {
	@Autowired
	private OrderCustomsMapper orderCustomsMapper;
	
	public OrderCustomsMapper getMapper(){
		return orderCustomsMapper;
	} 
	
	/**
	 * 新增
	 */
	@Override
	public int insertRecord(OrderCustoms ordercustoms){
		 return orderCustomsMapper.insertRecord(ordercustoms);
	}
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	@Override
	public int deleteById(String id) throws BizException{
		if (id == null) {
			throw new BizException("id 为空，不能修改 ");
		}
		return orderCustomsMapper.deleteById(id);
	}
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    @Override
    public int deleteRecords(OrderCustoms  record){
    	return orderCustomsMapper.deleteRecords(record);
    }
	
	/**
	 * 根据主键-修改对象
	 */
	@Override
	public int updateRecordById(OrderCustoms orderCustoms) throws BizException{
		if ( orderCustoms.getId() == null  ) {
			throw new BizException("id 为空，不能修改 ");
		}
		return orderCustomsMapper.updateRecordById(orderCustoms);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(Map<String, Object> map) {
		return orderCustomsMapper.updateRecords(map);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(OrderCustoms orderCustoms){
		return orderCustomsMapper.updateRecords(orderCustoms.toMap());
	}
	
	
	/**
	 * 根据主键-查询对象
	 */
	@Override
	public OrderCustoms findByPrimaryKey(String id){
		return  orderCustomsMapper.findByPrimaryKey(id);	
	}
	
	/**
	 * 根据uuid查询对象
	 */
	@Override
	public OrderCustoms findByUuid(String uuid){
		return  orderCustomsMapper.findByUuid(uuid);	
	}
	
	/**
	 * 根据对象-查询对象列表
	 */
	@Override
	public List<OrderCustoms> queryByEntitys(OrderCustoms orderCustoms){
		return  orderCustomsMapper.queryByEntitys(orderCustoms);
	}
	
	/**
	 * 根据条件-查询全部
	 */
	@Override
	public List<Map<String, Object>> queryRecords(Map<String, Object> map){
		return orderCustomsMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-分页查询
	 */
	@Override
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) orderCustomsMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-查询记录条数
	 */
	@Override
	public int countRecords(Map<String, Object> map){
		return orderCustomsMapper.countRecords(map);
	}

	//**************************以下方法为开发者补充*********************************/
	@Override
	public OrderCustoms findByOrderNo(String orderNo) {
		OrderCustoms query = new OrderCustoms();
		query.setOrderNo(orderNo);
		List<OrderCustoms> list = orderCustomsMapper.queryByEntitys(query);
		
		if(list != null && !list.isEmpty()) {
			return list.get(0);
		}
		
		return null;
	}

	@Override
	public int insertOrUpdateRecord(OrderCustoms orderCustoms, CurrentUser user) {
		if(StringUtils.isBlank(orderCustoms.getOrderNo()) ||
				orderCustoms.getOrderId() == null){
			throw new OrderExecException("orderexecute.code.00027", "缺少参数");
		}
		int num = 0;
		OrderCustoms existEntity = findByOrderNo(orderCustoms.getOrderNo());
		if(existEntity != null) {
			orderCustoms.setId(existEntity.getId());
			orderCustoms.setModifyDate(DateTimeUtils.currentDate());
			orderCustoms.setModifyPerson(String.valueOf(user.getMemberId()));
			
			num = updateRecordById(orderCustoms);
		}else {
			orderCustoms.setCreateDate(DateTimeUtils.currentDate());
			orderCustoms.setCreatePerson(String.valueOf(user.getMemberId()));
			
			num = insertRecord(orderCustoms);
		}
		
		return num;
	}
}
