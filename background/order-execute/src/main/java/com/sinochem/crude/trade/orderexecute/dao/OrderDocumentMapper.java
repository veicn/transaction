package com.sinochem.crude.trade.orderexecute.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sinochem.crude.trade.orderexecute.domain.OrderDocument;
//import org.apache.ibatis.annotations.Mapper;
import com.sinochem.crude.trade.orderexecute.domain.OrderDocumentFile;
import com.sinochem.crude.trade.orderexecute.query.DocumentQuery;

//@Mapper
public interface OrderDocumentMapper {

	public int insertRecord(OrderDocument entity);
	
	public int deleteById( @Param("orderDocumentId") Long orderDocumentId);
	
	public int deleteRecords(OrderDocument entity);
	
	public int updateRecordById(OrderDocument entity);
	
	public int updateRecords(Map<String,Object> map);
	
	public OrderDocument findByPrimaryKey( /* @Param("orderDocumentId") */ Long orderDocumentId);	
	
	public OrderDocument findByUuid(String uuid);	
	
	//返回对象的List
	public List<OrderDocument> queryByEntitys(OrderDocument entity);
	
	//返回Map的List
	public List<Map<String,Object>> queryRecords(Map<String,Object> map);
	
	public int countRecords(Map<String,Object> map);
	
	//**************************以下方法为开发者补充*********************************/
	
	public List<Map<String,Object>> getDocumentFileList(DocumentQuery query);
	
	public List<Map<String,Object>> getDocumentType(DocumentQuery query);
	
	public List<String> getContractFileList(String uuid);
	
	public Map<String,Object> getOrderData(DocumentQuery query);
	
	public Map<String,Object> getDocumentData(DocumentQuery query);
	
	public Map<String,Object> getDocumentDetail(DocumentQuery query);
	
	public int insertFileRecord(OrderDocumentFile entity);

	public int updateFileRecordById(OrderDocumentFile entity);

	public int updateFileAliveByCode(OrderDocumentFile entity);
	
	public int updateDocumentInfo(OrderDocument entity);
	
	public Map<String,Object> getDocumentInfo(OrderDocument entity);

	public List<Map<String, Object>> queryDocumentFileList(DocumentQuery query);

	public List<Map<String, Object>> queryCustomFileList(DocumentQuery query);

	public List<Map<String, Object>> queryDocFileCheckList(DocumentQuery query);
}
