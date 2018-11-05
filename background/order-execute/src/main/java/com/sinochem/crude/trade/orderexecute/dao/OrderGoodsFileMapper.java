package com.sinochem.crude.trade.orderexecute.dao;

import java.util.Map;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.sinochem.crude.trade.orderexecute.domain.OrderGoodsFile;
//import org.apache.ibatis.annotations.Mapper;

//@Mapper
public interface OrderGoodsFileMapper {

	public int insertRecord(OrderGoodsFile entity);
	
	public int deleteById( @Param("goodsFileId") Long goodsFileId);
	
	public int deleteRecords(OrderGoodsFile entity);
	
	public int updateRecordById(OrderGoodsFile entity);
	
	public int updateRecords(Map<String,Object> map);
	
	public OrderGoodsFile findByPrimaryKey( /* @Param("goodsFileId") */ Long goodsFileId);	
	
	public OrderGoodsFile findByUuid(String uuid);	
	
	//返回对象的List
	public List<OrderGoodsFile> queryByEntitys(OrderGoodsFile entity);
	
	//返回Map的List
	public List<Map<String,Object>> queryRecords(Map<String,Object> map);
	
	public int countRecords(Map<String,Object> map);
	
	//**************************以下方法为开发者补充*********************************/
	
	
}
