package com.sinochem.crude.trade.orderexecute.dao;

import java.util.Map;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.sinochem.crude.trade.orderexecute.domain.OrderInspection;
//import org.apache.ibatis.annotations.Mapper;

//@Mapper
public interface OrderInspectionMapper {

	public int insertRecord(OrderInspection entity);
	
	public int deleteById( @Param("id") String id);
	
	public int deleteRecords(OrderInspection entity);
	
	public int updateRecordById(OrderInspection entity);
	
	public int updateRecords(Map<String,Object> map);
	
	public OrderInspection findByPrimaryKey( /* @Param("id") */ String id);	
	
	public OrderInspection findByUuid(String uuid);	
	
	//返回对象的List
	public List<OrderInspection> queryByEntitys(OrderInspection entity);
	
	//返回Map的List
	public List<Map<String,Object>> queryRecords(Map<String,Object> map);
	
	public int countRecords(Map<String,Object> map);
	
	//**************************以下方法为开发者补充*********************************/
	
	
}
