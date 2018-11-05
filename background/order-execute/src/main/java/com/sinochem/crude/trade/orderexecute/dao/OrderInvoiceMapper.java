package com.sinochem.crude.trade.orderexecute.dao;

import java.util.Map;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.sinochem.crude.trade.orderexecute.domain.OrderInvoice;
//import org.apache.ibatis.annotations.Mapper;

//@Mapper
public interface OrderInvoiceMapper {

	public int insertRecord(OrderInvoice entity);
	
	public int deleteById( @Param("id") Long id);
	
	public int deleteRecords(OrderInvoice entity);
	
	public int updateRecordById(OrderInvoice entity);
	
	public int updateRecords(Map<String,Object> map);
	
	public OrderInvoice findByPrimaryKey( /* @Param("id") */ Long id);	
	
	public OrderInvoice findByUuid(String uuid);	
	
	//返回对象的List
	public List<OrderInvoice> queryByEntitys(OrderInvoice entity);
	
	//返回Map的List
	public List<Map<String,Object>> queryRecords(Map<String,Object> map);
	
	public int countRecords(Map<String,Object> map);
	
	//**************************以下方法为开发者补充*********************************/
	
	
}
