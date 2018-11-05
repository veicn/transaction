package com.sinochem.crude.trade.orderexecute.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sinochem.crude.trade.orderexecute.domain.OrderShipLoadinginfo;
//import org.apache.ibatis.annotations.Mapper;

//@Mapper
public interface OrderShipLoadinginfoMapper {

	public int insertRecord(OrderShipLoadinginfo entity);
	
	public int deleteById( @Param("id") Long id);
	
	public int deleteRecords(OrderShipLoadinginfo entity);
	
	public int updateRecordById(OrderShipLoadinginfo entity);
	
	public int updateRecords(Map<String,Object> map);
	
	public OrderShipLoadinginfo findByPrimaryKey( /* @Param("id") */ Long id);	
	
	public OrderShipLoadinginfo findByUuid(String uuid);	
	
	//返回对象的List
	public List<OrderShipLoadinginfo> queryByEntitys(OrderShipLoadinginfo entity);
	
	//返回Map的List
	public List<Map<String,Object>> queryRecords(Map<String,Object> map);
	
	public int countRecords(Map<String,Object> map);
	
	//**************************以下方法为开发者补充*********************************/
	
	public int updateRecordFromShip(OrderShipLoadinginfo entity);
	
	public List<OrderShipLoadinginfo> findbyOrderNo(String orderNo);
	
	public List<OrderShipLoadinginfo> findByOrderId(Long orderId);

}
