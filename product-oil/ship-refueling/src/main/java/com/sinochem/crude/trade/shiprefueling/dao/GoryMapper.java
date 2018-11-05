package com.sinochem.crude.trade.shiprefueling.dao;

import com.sinochem.crude.trade.shiprefueling.domain.po.Gory;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
//import org.apache.ibatis.annotations.Mapper;

public interface GoryMapper {

	public int insertRecord(Gory entity);
	
	public int deleteById( @Param("palletId") Long palletId);
	
	public int deleteRecords(Gory entity);
	
	public int updateRecordById(Gory entity);
	
	public int updateRecordByUuid(Gory entity);
	
	public int updateRecords(Map<String,Object> map);
	
	public Gory findByPrimaryKey( Long palletId);	
	
	public Gory findByUuid(String uuid);	
	
	//返回对象的List
	public List<Gory> queryByEntitys(Gory entity);
	
	//返回Map的List
	public List<Map<String,Object>> queryRecords(Map<String,Object> map);
	
	public int countRecords(Map<String,Object> map);


    //**************************以下方法为开发者补充*********************************/


	List<Gory> findByOrder(Map<String, Object> map);
	List<Gory> findByOrderId(Long orderId);

	public int updateAliveFlagByOrderId(@Param("orderId") Long orderId, @Param("aliveFlag") String flag);

}
