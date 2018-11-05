package com.sinochem.crude.trade.orderexecute.dao;

import java.util.Map;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.sinochem.crude.trade.orderexecute.domain.OrderGoods;
//import org.apache.ibatis.annotations.Mapper;

//@Mapper
public interface OrderGoodsMapper {

	public int insertRecord(OrderGoods entity);
	
	public int deleteById( @Param("orderGoodsId") Long orderGoodsId);
	
	public int deleteRecords(OrderGoods entity);
	
	public int updateRecordById(OrderGoods entity);
	
	public int updateRecords(Map<String,Object> map);
	
	public OrderGoods findByPrimaryKey( /* @Param("orderGoodsId") */ Long orderGoodsId);	
	
	public OrderGoods findByUuid(String uuid);	
	
	//返回对象的List
	public List<OrderGoods> queryByEntitys(OrderGoods entity);
	
	//返回Map的List
	public List<Map<String,Object>> queryRecords(Map<String,Object> map);
	
	public int countRecords(Map<String,Object> map);
	
	//**************************以下方法为开发者补充*********************************/
	
	
}
