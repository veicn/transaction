package com.sinochem.crude.trade.orderexecute.dao;

import java.util.Map;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.sinochem.crude.trade.orderexecute.domain.OrderPrice;
//import org.apache.ibatis.annotations.Mapper;

//@Mapper
public interface OrderPriceMapper {

	public int insertRecord(OrderPrice entity);
	
	public int deleteById( @Param("orderPriceId") Long orderPriceId);
	
	public int deleteRecords(OrderPrice entity);
	
	public int updateRecordById(OrderPrice entity);
	
	public int updateRecords(Map<String,Object> map);
	
	public OrderPrice findByPrimaryKey( /* @Param("orderPriceId") */ Long orderPriceId);	
	
	public OrderPrice findByUuid(String uuid);	
	
	//返回对象的List
	public List<OrderPrice> queryByEntitys(OrderPrice entity);
	
	//返回Map的List
	public List<Map<String,Object>> queryRecords(Map<String,Object> map);
	
	public int countRecords(Map<String,Object> map);
	
	//**************************以下方法为开发者补充*********************************/
	
	
}
