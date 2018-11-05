package com.sinochem.crude.trade.orderexecute.dao;

import java.util.Map;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.sinochem.crude.trade.orderexecute.domain.OrderSettlement;
//import org.apache.ibatis.annotations.Mapper;
import com.sinochem.crude.trade.orderexecute.query.OrderStatementQuery;

//@Mapper
public interface OrderSettlementMapper {

	public int insertRecord(OrderSettlement entity);
	
	public int deleteById( @Param("orderSettlementId") Long orderSettlementId);
	
	public int deleteRecords(OrderSettlement entity);
	
	public int updateRecordById(OrderSettlement entity);
	
	public int updateRecords(Map<String,Object> map);
	
	public OrderSettlement findByPrimaryKey( /* @Param("orderSettlementId") */ Long orderSettlementId);	
	
	public OrderSettlement findByUuid(String uuid);	
	
	//返回对象的List
	public List<OrderSettlement> queryByEntitys(OrderSettlement entity);
	
	//返回Map的List
	public List<Map<String,Object>> queryRecords(Map<String,Object> map);
	
	public int countRecords(Map<String,Object> map);
	
	//**************************以下方法为开发者补充*********************************/
	
	public List<Map<String,Object>> selectAccountsPage(OrderStatementQuery query);
	public Map<String, Object> selectinformation(Long orderId);
	public double calcSettlementTotal(Long orderId);
	
}
