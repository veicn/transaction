package com.sinochem.crude.trade.shipping.dao;

import java.util.Map;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.sinochem.crude.trade.shipping.domain.Accessory;
//import org.apache.ibatis.annotations.Mapper;

//@Mapper
public interface AccessoryMapper {

	public int insertRecord(Accessory entity);
	
	public int deleteById( @Param("accessoryId") Long accessoryId);
	
	public int deleteRecords(Accessory entity);
	
	public int updateRecordById(Accessory entity);
	
	public int updateRecordByUuid(Accessory entity);
	
	public int updateRecords(Map<String,Object> map);
	
	public Accessory findByPrimaryKey( Long accessoryId);	
	
	public Accessory findByUuid(String uuid);	
	
	//返回对象的List
	public List<Accessory> queryByEntitys(Accessory entity);
	
	//返回Map的List
	public List<Map<String,Object>> queryRecords(Map<String,Object> map);
	
	public int countRecords(Map<String,Object> map);
	
	//**************************以下方法为开发者补充*********************************/
	
}
