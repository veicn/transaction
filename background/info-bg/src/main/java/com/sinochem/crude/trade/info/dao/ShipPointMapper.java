package com.sinochem.crude.trade.info.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sinochem.crude.trade.info.domain.ShipPoint;

//@Mapper
public interface ShipPointMapper {

	public int insertRecord(ShipPoint entity);
	
	public int deleteById( @Param("id") Long id);
	
	public int deleteRecords(ShipPoint entity);
	
	public int updateRecordById(ShipPoint entity);
	
	public int updateRecords(Map<String,Object> map);
	
	public ShipPoint findByPrimaryKey( Long id);	
	
	public ShipPoint findByUuid(String uuid);	
	
	//返回对象的List
	public List<ShipPoint> queryByEntitys(ShipPoint entity);
	
	//返回Map的List
	public List<Map<String,Object>> queryRecords(Map<String,Object> map);
	
	public int countRecords(Map<String,Object> map);
	
	//**************************以下方法为开发者补充*********************************/
	
	public List<Map<String,Object>> queryDilveryRegion(Date endDate);
	
	public List<Map<String,Object>> queryPointYear(Map<String,Object> map);
	
	public List<Map<String,Object>> queryEndDate(String paramDateStr);
	
}
