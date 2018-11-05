package com.sinochem.crude.trade.orderexecute.dao;

import java.util.Map;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.sinochem.crude.trade.orderexecute.domain.OrderMessageLog;
//import org.apache.ibatis.annotations.Mapper;

//@Mapper
public interface OrderMessageLogMapper {

	public int insertRecord(OrderMessageLog entity);
	
	public int deleteById( @Param("messageId") Long messageId);
	
	public int deleteRecords(OrderMessageLog entity);
	
	public int updateRecordById(OrderMessageLog entity);
	
	public int updateRecords(Map<String,Object> map);
	
	public OrderMessageLog findByPrimaryKey( /* @Param("messageId") */ Long messageId);	
	
	public OrderMessageLog findByUuid(String uuid);	
	
	//返回对象的List
	public List<OrderMessageLog> queryByEntitys(OrderMessageLog entity);
	
	//返回Map的List
	public List<Map<String,Object>> queryRecords(Map<String,Object> map);
	
	public int countRecords(Map<String,Object> map);
	
	//**************************以下方法为开发者补充*********************************/
	
	
}
