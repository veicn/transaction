package com.sinochem.crude.trade.orderexecute.dao;

import java.util.Map;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sinochem.crude.trade.orderexecute.domain.OrderFeeItem;
//import org.apache.ibatis.annotations.Mapper;

//@Mapper
public interface OrderFeeItemMapper {

	public int insertRecord(OrderFeeItem entity);
	
	public int deleteById( @Param("feeItemId") Long feeItemId);
	
	public int deleteRecords(OrderFeeItem entity);
	
	public int updateRecordById(OrderFeeItem entity);
	
	public int updateRecords(Map<String,Object> map);
	
	public OrderFeeItem findByPrimaryKey( /* @Param("feeItemId") */ Long feeItemId);	
	
	public OrderFeeItem findByUuid(String uuid);	
	
	//返回对象的List
	public List<OrderFeeItem> queryByEntitys(OrderFeeItem entity);
	
	//返回Map的List
	public List<OrderFeeItem> queryRecords(Map<String,Object> map);
	
	public int countRecords(Map<String,Object> map);

	
	
	//**************************以下方法为开发者补充*********************************/
	
	public void updateFileAlive(Long relatId);

	public Long selectOrder(Long orderId);

	public Long selectOrderFee(Long id);


	public Long selectOrderFeeItemRelat(Long feeItemId);
	
	public List<OrderFeeItem> selectOrderFeeItem(@Param("orderId") Long orderId);
}
