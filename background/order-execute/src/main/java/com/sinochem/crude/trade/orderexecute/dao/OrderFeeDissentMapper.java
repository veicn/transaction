package com.sinochem.crude.trade.orderexecute.dao;

import java.util.Map;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sinochem.crude.trade.orderexecute.domain.OrderFeeDissent;
//import org.apache.ibatis.annotations.Mapper;

//@Mapper
public interface OrderFeeDissentMapper {

	public int insertRecord(OrderFeeDissent entity);
	
	public int deleteById( @Param("feeDissentId") Long feeDissentId);
	
	public int deleteRecords(OrderFeeDissent entity);
	
	public int updateRecordById(OrderFeeDissent entity);
	
	public int updateRecords(Map<String,Object> map);
	
	public OrderFeeDissent findByPrimaryKey( /* @Param("feeDissentId") */ Long feeDissentId);	
	
	public OrderFeeDissent findByUuid(String uuid);	
	
	//返回对象的List
	public List<OrderFeeDissent> queryByEntitys(OrderFeeDissent entity);
	
	//返回Map的List
	public List<Map<String,Object>> queryRecords(Map<String,Object> map);
	
	public int countRecords(Map<String,Object> map);
	
	//**************************以下方法为开发者补充*********************************/
	
	public int updateOrderStatementStatus(Map<String,Object> map);
	
	public Map<String,Object> getStatementInfo(Long orderStatementId);
}
