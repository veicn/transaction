package com.sinochem.crude.trade.orderexecute.dao;

import java.util.Map;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.sinochem.crude.trade.orderexecute.domain.OrderShipUnloadinginfo;
//import org.apache.ibatis.annotations.Mapper;

//@Mapper
public interface OrderShipUnloadinginfoMapper {

	public int insertRecord(OrderShipUnloadinginfo entity);
	
	public int deleteById( @Param("id") Long id);
	
	public int deleteRecords(OrderShipUnloadinginfo entity);
	
	public int updateRecordById(OrderShipUnloadinginfo entity);
	
	public int updateRecords(Map<String,Object> map);
	
	public OrderShipUnloadinginfo findByPrimaryKey( /* @Param("id") */ Long id);	
	
	public OrderShipUnloadinginfo findByUuid(String uuid);	
	
	//返回对象的List
	public List<OrderShipUnloadinginfo> queryByEntitys(OrderShipUnloadinginfo entity);
	
	//返回Map的List
	public List<Map<String,Object>> queryRecords(Map<String,Object> map);
	
	public int countRecords(Map<String,Object> map);
	
	//**************************以下方法为开发者补充*********************************/
	
	public int updateRecordFromShip(OrderShipUnloadinginfo entity);
	
	public OrderShipUnloadinginfo findbyOrderNo(String orderNo);
	
	public List<OrderShipUnloadinginfo> findByOrderId(Long orderId);
}
