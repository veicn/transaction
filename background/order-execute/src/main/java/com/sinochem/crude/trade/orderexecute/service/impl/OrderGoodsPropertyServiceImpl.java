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
import com.sinochem.crude.trade.orderexecute.domain.OrderGoodsProperty;
import com.sinochem.crude.trade.orderexecute.exception.OrderExecException;
import com.sinochem.crude.trade.orderexecute.dao.OrderGoodsPropertyMapper;
import com.sinochem.crude.trade.orderexecute.service.OrderGoodsPropertyService;

@Service
public class OrderGoodsPropertyServiceImpl implements OrderGoodsPropertyService {
	@Autowired
	private OrderGoodsPropertyMapper orderGoodsPropertyMapper;
	
	public OrderGoodsPropertyMapper getMapper(){
		return orderGoodsPropertyMapper;
	} 
	
	/**
	 * 新增
	 */
	@Override
	public int insertRecord(OrderGoodsProperty ordergoodsproperty){
		 return orderGoodsPropertyMapper.insertRecord(ordergoodsproperty);
	}
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	@Override
	public int deleteById(Long goodsPropertyId) throws BizException{
		if (goodsPropertyId == null) {
			throw new OrderExecException("orderexecute.code.00131","id 为空，不能修改","id");
		}
		return orderGoodsPropertyMapper.deleteById(goodsPropertyId);
	}
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    @Override
    public int deleteRecords(OrderGoodsProperty  record){
    	return orderGoodsPropertyMapper.deleteRecords(record);
    }
	
	/**
	 * 根据主键-修改对象
	 */
	@Override
	public int updateRecordById(OrderGoodsProperty orderGoodsProperty) throws BizException{
		if ( orderGoodsProperty.getGoodsPropertyId() == null  ) {
			throw new OrderExecException("orderexecute.code.00131","goodsPropertyId 为空，不能修改","goodsPropertyId");
		}
		return orderGoodsPropertyMapper.updateRecordById(orderGoodsProperty);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(Map<String, Object> map) {
		return orderGoodsPropertyMapper.updateRecords(map);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(OrderGoodsProperty orderGoodsProperty){
		return orderGoodsPropertyMapper.updateRecords(orderGoodsProperty.toMap());
	}
	
	
	/**
	 * 根据主键-查询对象
	 */
	@Override
	public OrderGoodsProperty findByPrimaryKey(Long goodsPropertyId){
		return  orderGoodsPropertyMapper.findByPrimaryKey(goodsPropertyId);	
	}
	
	/**
	 * 根据uuid查询对象
	 */
	@Override
	public OrderGoodsProperty findByUuid(String uuid){
		return  orderGoodsPropertyMapper.findByUuid(uuid);	
	}
	
	/**
	 * 根据对象-查询对象列表
	 */
	@Override
	public List<OrderGoodsProperty> queryByEntitys(OrderGoodsProperty orderGoodsProperty){
		return  orderGoodsPropertyMapper.queryByEntitys(orderGoodsProperty);
	}
	
	/**
	 * 根据条件-查询全部
	 */
	@Override
	public List<Map<String, Object>> queryRecords(Map<String, Object> map){
		return orderGoodsPropertyMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-分页查询
	 */
	@Override
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) orderGoodsPropertyMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-查询记录条数
	 */
	@Override
	public int countRecords(Map<String, Object> map){
		return orderGoodsPropertyMapper.countRecords(map);
	}
	
	//**************************以下方法为开发者补充*********************************/
	
}
