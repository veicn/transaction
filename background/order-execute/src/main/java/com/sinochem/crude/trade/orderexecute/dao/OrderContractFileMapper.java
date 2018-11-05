package com.sinochem.crude.trade.orderexecute.dao;

import java.util.Map;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.sinochem.crude.trade.orderexecute.domain.OrderContractFile;
//import org.apache.ibatis.annotations.Mapper;

//@Mapper
public interface OrderContractFileMapper {

	public int insertRecord(OrderContractFile entity);
	
	public int deleteById( @Param("contractFileId") Long contractFileId);
	
	public int deleteRecords(OrderContractFile entity);
	
	public int updateRecordById(OrderContractFile entity);
	
	public int updateRecords(Map<String,Object> map);
	
	public OrderContractFile findByPrimaryKey( /* @Param("contractFileId") */ Long contractFileId);	
	
	public OrderContractFile findByUuid(String uuid);	
	
	//返回对象的List
	public List<OrderContractFile> queryByEntitys(OrderContractFile entity);
	
	//返回Map的List
	public List<Map<String,Object>> queryRecords(Map<String,Object> map);
	
	public int countRecords(Map<String,Object> map);
	
	//**************************以下方法为开发者补充*********************************/
	
	public OrderContractFile findByPrimaryKeyAndCreater(@Param("contractFileId")Long contractFileId, @Param("userId")Long userId);
	
}
