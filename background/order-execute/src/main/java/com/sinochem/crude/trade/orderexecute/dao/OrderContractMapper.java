package com.sinochem.crude.trade.orderexecute.dao;

import java.util.Map;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.sinochem.crude.trade.orderexecute.domain.OrderContract;
//import org.apache.ibatis.annotations.Mapper;

//@Mapper
public interface OrderContractMapper {

	public int insertRecord(OrderContract entity);
	
	public int deleteById( @Param("contractId") Long contractId);
	
	public int deleteRecords(OrderContract entity);
	
	public int updateRecordById(OrderContract entity);
	
	public int updateRecords(Map<String,Object> map);
	
	public OrderContract findByPrimaryKey( /* @Param("contractId") */ Long contractId);	
	
	public OrderContract findByUuid(String uuid);	
	
	//返回对象的List
	public List<OrderContract> queryByEntitys(OrderContract entity);
	
	//返回Map的List
	public List<Map<String,Object>> queryRecords(Map<String,Object> map);
	
	public int countRecords(Map<String,Object> map);
	
	//**************************以下方法为开发者补充*********************************/
	
	public List<OrderContract> findByOrderUuid(String orderUuid);
	public int removeRecordByIdOrUuid(@Param("id")Long id, @Param("uuid")String uuid, @Param("customerId")Long customerId);
	
}
