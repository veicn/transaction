package com.sinochem.crude.trade.orderexecute.dao;

import java.util.Map;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sinochem.crude.trade.orderexecute.domain.OrderFee;
//import org.apache.ibatis.annotations.Mapper;

//@Mapper
public interface OrderFeeMapper {

	public int insertRecord(OrderFee entity);
	
	public int deleteById( @Param("orderFeeId") Long orderFeeId);
	
	public int deleteRecords(OrderFee entity);
	
	public int updateRecordById(OrderFee entity);
	
	public int updateRecords(Map<String,Object> map);
	
	public OrderFee findByPrimaryKey( /* @Param("orderFeeId") */ Long orderFeeId);	
	
	public OrderFee findByUuid(String uuid);	
	
	//返回对象的List
	public List<OrderFee> queryByEntitys(OrderFee entity);
	
	//返回Map的List
	public List<Map<String,Object>> queryRecords(Map<String,Object> map);
	
	public int countRecords(Map<String,Object> map);
	
	//**************************以下方法为开发者补充*********************************/
	
	
	public List<Map<String,Object>> selectInformation(Map<String,Object> map);

	/**
	 * 更新
	 * @param entity
	 */
//	public void updateFee(OrderFee entity);

	public Map<String,Object> selectId(Map<String,Object> map);
	
	public List<Map<String,Object>> selectClassified(Map<String,Object> map);
}
