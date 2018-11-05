package com.sinochem.crude.trade.shiprefueling.dao;

import com.sinochem.crude.trade.shiprefueling.domain.po.RIgnition;
import com.sinochem.crude.trade.shiprefueling.domain.po.RSupply;
import com.sinochem.crude.trade.shiprefueling.model.query.QueryRSupply;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface RSupplyMapper {

	public int insertRecord(RSupply entity);
	
	public int deleteById( @Param("orderId") Long orderId);
	
	public int deleteRecords(RSupply entity);
	
	public int updateRecordById(RSupply entity);
	
	public int updateRecordByUuid(RSupply entity);
	
	public int updateRecords(Map<String,Object> map);
	
	public RSupply findByPrimaryKey( Long orderId);	
	
	public RSupply findByUuid(String uuid);	
	
	//返回对象的List
	public List<RSupply> queryByEntitys(RSupply entity);
	
	//返回Map的List
	public List<Map<String,Object>> queryRecords(Map<String,Object> map);
	
	public int countRecords(Map<String,Object> map);



	//**************************以下方法为开发者补充*********************************/
	public List<Map<String,Object>> querySellCompanyOrder(Map<String,Object> map);
	public List<Map<String,Object>> queryBuyCompanyOrder(Map<String,Object> map);

	List<Map<String,Object>> querySupplyRecords(Map<String, Object> map);

    int updateRecordsAliveFlag(@Param("orderId") Long orderId, @Param("aliveFlag") String flag);
    //返回对象的List
	List<RSupply> queryByQueryRSupply(QueryRSupply queryRSupply);
}
