package com.sinochem.crude.trade.orderexecute.dao;

import java.util.Map;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.sinochem.crude.trade.orderexecute.domain.OrderTransport;
//import org.apache.ibatis.annotations.Mapper;

//@Mapper
public interface OrderTransportMapper {

	public int insertRecord(OrderTransport entity);
	
	public int deleteById( @Param("orderTransportId") Long orderTransportId);
	
	public int deleteRecords(OrderTransport entity);
	
	public int updateRecordById(OrderTransport entity);
	
	public int updateRecords(Map<String,Object> map);
	
	public OrderTransport findByPrimaryKey( /* @Param("orderTransportId") */ Long orderTransportId);	
	
	public OrderTransport findByUuid(String uuid);	
	
	//返回对象的List
	public List<OrderTransport> queryByEntitys(OrderTransport entity);
	
	//返回Map的List
	public List<Map<String,Object>> queryRecords(Map<String,Object> map);
	
	public int countRecords(Map<String,Object> map);
	
	//**************************以下方法为开发者补充*********************************/
	
	
}
