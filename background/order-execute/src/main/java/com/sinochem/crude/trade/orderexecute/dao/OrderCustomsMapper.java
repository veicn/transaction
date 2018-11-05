package com.sinochem.crude.trade.orderexecute.dao;

import java.util.Map;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.sinochem.crude.trade.orderexecute.domain.OrderCustoms;
//import org.apache.ibatis.annotations.Mapper;

//@Mapper
public interface OrderCustomsMapper {

	public int insertRecord(OrderCustoms entity);
	
	public int deleteById( @Param("id") String id);
	
	public int deleteRecords(OrderCustoms entity);
	
	public int updateRecordById(OrderCustoms entity);
	
	public int updateRecords(Map<String,Object> map);
	
	public OrderCustoms findByPrimaryKey( /* @Param("id") */ String id);	
	
	public OrderCustoms findByUuid(String uuid);	
	
	//返回对象的List
	public List<OrderCustoms> queryByEntitys(OrderCustoms entity);
	
	//返回Map的List
	public List<Map<String,Object>> queryRecords(Map<String,Object> map);
	
	public int countRecords(Map<String,Object> map);
	
	//**************************以下方法为开发者补充*********************************/
	
	
}
