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
import com.sinochem.crude.trade.orderexecute.domain.OrderInvoice;
import com.sinochem.crude.trade.orderexecute.exception.OrderExecException;
import com.sinochem.crude.trade.orderexecute.dao.OrderInvoiceMapper;
import com.sinochem.crude.trade.orderexecute.service.OrderInvoiceService;

@Service
public class OrderInvoiceServiceImpl implements OrderInvoiceService {
	@Autowired
	private OrderInvoiceMapper orderInvoiceMapper;
	
	public OrderInvoiceMapper getMapper(){
		return orderInvoiceMapper;
	} 
	
	/**
	 * 新增
	 */
	@Override
	public int insertRecord(OrderInvoice orderinvoice){
		 return orderInvoiceMapper.insertRecord(orderinvoice);
	}
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	@Override
	public int deleteById(Long id) throws BizException{
		if (id == null) {
			throw new OrderExecException("orderexecute.code.00131","id 为空，不能修改","id");
		}
		return orderInvoiceMapper.deleteById(id);
	}
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    @Override
    public int deleteRecords(OrderInvoice  record){
    	return orderInvoiceMapper.deleteRecords(record);
    }
	
	/**
	 * 根据主键-修改对象
	 */
	@Override
	public int updateRecordById(OrderInvoice orderInvoice) throws BizException{
		if ( orderInvoice.getId() == null  ) {
			throw new OrderExecException("orderexecute.code.00131","id 为空，不能修改","id");
		}
		return orderInvoiceMapper.updateRecordById(orderInvoice);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(Map<String, Object> map) {
		return orderInvoiceMapper.updateRecords(map);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(OrderInvoice orderInvoice){
		return orderInvoiceMapper.updateRecords(orderInvoice.toMap());
	}
	
	
	/**
	 * 根据主键-查询对象
	 */
	@Override
	public OrderInvoice findByPrimaryKey(Long id){
		return  orderInvoiceMapper.findByPrimaryKey(id);	
	}
	
	/**
	 * 根据uuid查询对象
	 */
	@Override
	public OrderInvoice findByUuid(String uuid){
		return  orderInvoiceMapper.findByUuid(uuid);	
	}
	
	/**
	 * 根据对象-查询对象列表
	 */
	@Override
	public List<OrderInvoice> queryByEntitys(OrderInvoice orderInvoice){
		return  orderInvoiceMapper.queryByEntitys(orderInvoice);
	}
	
	/**
	 * 根据条件-查询全部
	 */
	@Override
	public List<Map<String, Object>> queryRecords(Map<String, Object> map){
		return orderInvoiceMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-分页查询
	 */
	@Override
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) orderInvoiceMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-查询记录条数
	 */
	@Override
	public int countRecords(Map<String, Object> map){
		return orderInvoiceMapper.countRecords(map);
	}
	
	//**************************以下方法为开发者补充*********************************/
	
}
