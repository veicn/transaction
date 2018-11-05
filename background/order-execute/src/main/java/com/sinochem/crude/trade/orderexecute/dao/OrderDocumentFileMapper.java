package com.sinochem.crude.trade.orderexecute.dao;

import java.util.List;
import java.util.Map;

import com.sinochem.crude.trade.orderexecute.domain.OrderDocumentFile;

//@Mapper
public interface OrderDocumentFileMapper {

	public int insertRecord(OrderDocumentFile entity);
	
	public int deleteById(OrderDocumentFile entity);
	
	public int deleteRecords(OrderDocumentFile entity);
	
	public int updateRecordById(OrderDocumentFile entity);
	
	public int updateRecords(Map<String,Object> map);
	
	public OrderDocumentFile findByPrimaryKey( /* @Param("documentFileId") */ Long documentFileId);	
	
	public OrderDocumentFile findByUuid(String uuid);	
	
	//返回对象的List
	public List<OrderDocumentFile> queryByEntitys(OrderDocumentFile entity);
	
	//返回Map的List
	public List<Map<String,Object>> queryRecords(Map<String,Object> map);
	
	public int countRecords(Map<String,Object> map);
	
	//**************************以下方法为开发者补充*********************************/W
	
	public List<Map<String,Object>> queryDocumentFile(Map<String, Object> map);
	
	public List<Map<String,Object>> getDocumentType();
}
