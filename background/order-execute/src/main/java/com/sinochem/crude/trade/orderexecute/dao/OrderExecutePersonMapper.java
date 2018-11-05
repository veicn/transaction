package com.sinochem.crude.trade.orderexecute.dao;

import java.util.Map;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.sinochem.crude.trade.orderexecute.domain.OrderExecutePerson;
//import org.apache.ibatis.annotations.Mapper;

//@Mapper
public interface OrderExecutePersonMapper {

	public int insertRecord(OrderExecutePerson entity);
	
	public int deleteById( @Param("executePersonId") Long executePersonId);
	
	public int deleteRecords(OrderExecutePerson entity);
	
	public int updateRecordById(OrderExecutePerson entity);
	
	public int updateRecords(Map<String,Object> map);
	
	public OrderExecutePerson findByPrimaryKey( /* @Param("executePersonId") */ Long executePersonId);	
	
	public OrderExecutePerson findByUuid(String uuid);	
	
	//返回对象的List
	public List<OrderExecutePerson> queryByEntitys(OrderExecutePerson entity);
	
	//返回Map的List
	public List<Map<String,Object>> queryRecords(Map<String,Object> map);
	
	public int countRecords(Map<String,Object> map);
	
	//**************************以下方法为开发者补充*********************************/
	
	
}
