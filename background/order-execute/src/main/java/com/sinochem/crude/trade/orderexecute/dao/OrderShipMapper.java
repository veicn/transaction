package com.sinochem.crude.trade.orderexecute.dao;

import java.util.Map;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.sinochem.crude.trade.orderexecute.domain.OrderShip;
//import org.apache.ibatis.annotations.Mapper;

//@Mapper
public interface OrderShipMapper {

	public int insertRecord(OrderShip entity);
	
	public int deleteById( @Param("orderShipId") Long orderShipId);
	
	public int deleteRecords(OrderShip entity);
	
	public int updateRecordById(OrderShip entity);
	
	public int updateRecords(Map<String,Object> map);
	
	public OrderShip findByPrimaryKey( /* @Param("orderShipId") */ Long orderShipId);	
	
	public OrderShip findByUuid(String uuid);	
	
	//返回对象的List
	public List<OrderShip> queryByEntitys(OrderShip entity);
	
	//返回Map的List
	public List<Map<String,Object>> queryRecords(Map<String,Object> map);
	
	public int countRecords(Map<String,Object> map);
	
	//**************************以下方法为开发者补充*********************************/
	public int updateRecordsByOrderNo(OrderShip entity);

	public OrderShip findByOrderId(Long orderId);
	
}
