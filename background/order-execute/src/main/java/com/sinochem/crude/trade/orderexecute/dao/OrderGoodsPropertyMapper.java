package com.sinochem.crude.trade.orderexecute.dao;

import java.util.Map;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.sinochem.crude.trade.orderexecute.domain.OrderGoodsProperty;
//import org.apache.ibatis.annotations.Mapper;

//@Mapper
public interface OrderGoodsPropertyMapper {

	public int insertRecord(OrderGoodsProperty entity);
	
	public int deleteById( @Param("goodsPropertyId") Long goodsPropertyId);
	
	public int deleteRecords(OrderGoodsProperty entity);
	
	public int updateRecordById(OrderGoodsProperty entity);
	
	public int updateRecords(Map<String,Object> map);
	
	public OrderGoodsProperty findByPrimaryKey( /* @Param("goodsPropertyId") */ Long goodsPropertyId);	
	
	public OrderGoodsProperty findByUuid(String uuid);	
	
	//返回对象的List
	public List<OrderGoodsProperty> queryByEntitys(OrderGoodsProperty entity);
	
	//返回Map的List
	public List<Map<String,Object>> queryRecords(Map<String,Object> map);
	
	public int countRecords(Map<String,Object> map);
	
	//**************************以下方法为开发者补充*********************************/
	
	
}
