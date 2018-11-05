package com.sinochem.crude.trade.orderexecute.dao;

import java.util.Map;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.sinochem.crude.trade.orderexecute.domain.OrderDocumentList;
//import org.apache.ibatis.annotations.Mapper;

//@Mapper
public interface OrderDocumentListMapper {

	public int insertRecord(OrderDocumentList entity);
	
	public int deleteById( @Param("documentId") Long documentId);
	
	public int deleteRecords(OrderDocumentList entity);
	
	public int updateRecordById(OrderDocumentList entity);
	
	public int updateRecords(Map<String,Object> map);
	
	public OrderDocumentList findByPrimaryKey( /* @Param("documentId") */ Long documentId);	
	
	public OrderDocumentList findByUuid(String uuid);	
	
	//返回对象的List
	public List<OrderDocumentList> queryByEntitys(OrderDocumentList entity);
	
	//返回Map的List
	public List<Map<String,Object>> queryRecords(Map<String,Object> map);
	
	public int countRecords(Map<String,Object> map);

	//根据条件逻辑删除   将flag改为0
	public int updateDeleteRecords(Map<String, Object> map);
	
	//**************************以下方法为开发者补充*********************************/
	
	
}
