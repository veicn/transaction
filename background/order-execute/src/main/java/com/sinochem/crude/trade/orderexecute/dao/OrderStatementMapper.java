package com.sinochem.crude.trade.orderexecute.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sinochem.crude.trade.common.result.Result;
import com.sinochem.crude.trade.orderexecute.controller.openapi.vo.StatementSheetVO;
import com.sinochem.crude.trade.orderexecute.domain.OrderStatement;
import com.sinochem.crude.trade.orderexecute.model.OrderStatementVO;
import com.sinochem.crude.trade.orderexecute.query.OrderStatementQuery;

//@Mapper
public interface OrderStatementMapper {

	public int insertRecord(OrderStatement entity);
	
	public int deleteById( @Param("orderStatementId") Long orderStatementId);
	
	public int deleteRecords(OrderStatement entity);
	
	public int updateRecordById(OrderStatement entity);
	
	public int updateRecords(Map<String,Object> map);
	
	public OrderStatement findByPrimaryKey( /* @Param("orderStatementId") */ Long orderStatementId);	
	
	public OrderStatement findByUuid(String uuid);	
	
	//返回对象的List
	public List<OrderStatement> queryByEntitys(OrderStatement entity);
	
	//返回Map的List
	public List<Map<String,Object>> queryRecords(Map<String,Object> map);
	
	public int countRecords(Map<String,Object> map);
	
	//**************************以下方法为开发者补充*********************************/
	
	/**
	 * 查询订单信息
	 */
	Map<String,Object> selectinformation(Long orderId);

	public Map<String, Object> selectData(Map<String, Object> map);	
	
	public List<Map<String,Object>> selectPage(OrderStatementQuery query);
	
	public double calcStatementTotal(Long orderId);
	/**
	 * 查询是否点价
	 * 
	 */
	public int queryIsTrigger(Long orderId);
	double findDifferAmount(Long orderId);
	public Map<String, Object> selectDataForTriggerResult(Long orderId);

	public int updateStatusById(OrderStatementVO vo);

	public int editStatusById(OrderStatementVO vo);	
}
