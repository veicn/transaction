package com.sinochem.crude.trade.orderexecute.dao;

import java.util.Map;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.sinochem.crude.trade.orderexecute.domain.OrderParty;
//import org.apache.ibatis.annotations.Mapper;

//@Mapper
public interface OrderPartyMapper {

	public int insertRecord(OrderParty entity);
	
	public int deleteById( @Param("partyId") Long partyId);
	
	public int deleteRecords(OrderParty entity);
	
	public int updateRecordById(OrderParty entity);
	
	public int updateRecords(Map<String,Object> map);
	
	public OrderParty findByPrimaryKey( /* @Param("partyId") */ Long partyId);	
	
	public OrderParty findByUuid(String uuid);	
	
	//返回对象的List
	public List<OrderParty> queryByEntitys(OrderParty entity);
	
	//返回Map的List
	public List<Map<String,Object>> queryRecords(Map<String,Object> map);
	
	public int countRecords(Map<String,Object> map);
	
	//**************************以下方法为开发者补充*********************************/
	
	
}
