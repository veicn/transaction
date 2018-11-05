package com.sinochem.crude.trade.orderexecute.dao;

import java.util.Map;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.sinochem.crude.trade.orderexecute.domain.OrderLog;
//import org.apache.ibatis.annotations.Mapper;

//@Mapper
public interface OrderLogMapper {

	public int insertRecord(OrderLog entity);
	
	public int deleteById( @Param("logId") Long logId);
	
	public int deleteRecords(OrderLog entity);
	
	public int updateRecordById(OrderLog entity);
	
	public int updateRecords(Map<String,Object> map);
	
	public OrderLog findByPrimaryKey( /* @Param("logId") */ Long logId);	
	
	public OrderLog findByUuid(String uuid);	
	
	//返回对象的List
	public List<OrderLog> queryByEntitys(OrderLog entity);
	
	//返回Map的List
	public List<Map<String,Object>> queryRecords(Map<String,Object> map);
	
	public int countRecords(Map<String,Object> map);
	
	//**************************以下方法为开发者补充*********************************/
	
	
}
