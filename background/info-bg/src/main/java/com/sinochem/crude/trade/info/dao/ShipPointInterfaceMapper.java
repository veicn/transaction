package com.sinochem.crude.trade.info.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sinochem.crude.trade.info.domain.ShipPointInterface;

//@Mapper
public interface ShipPointInterfaceMapper {

	public int insertRecord(ShipPointInterface entity);
	
	public int deleteById( @Param("id") Long id);
	
	public int deleteRecords(ShipPointInterface entity);
	
	public int updateRecordById(ShipPointInterface entity);
	
	public int updateRecords(Map<String,Object> map);
	
	public ShipPointInterface findByPrimaryKey( Long id);	
	
	public ShipPointInterface findByUuid(String uuid);	
	
	//返回对象的List
	public List<ShipPointInterface> queryByEntitys(ShipPointInterface entity);
	
	//返回Map的List
	public List<Map<String,Object>> queryRecords(Map<String,Object> map);
	
	public int countRecords(Map<String,Object> map);
	
	//**************************以下方法为开发者补充*********************************/
	
	
}
