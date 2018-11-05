package com.sinochem.crude.trade.info.dao;

import java.util.Map;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.sinochem.crude.trade.info.domain.OilToHarbor;
//import org.apache.ibatis.annotations.Mapper;

//@Mapper
public interface OilToHarborMapper {

	public int insertRecord(OilToHarbor entity);
	
	public int deleteById( @Param("id") Long id);
	
	public int deleteRecords(OilToHarbor entity);
	
	public int updateRecordById(OilToHarbor entity);
	
	public int updateRecordByUuid(OilToHarbor entity);
	
	public int updateRecords(Map<String,Object> map);
	
	public OilToHarbor findByPrimaryKey( Long id);	
	
	public OilToHarbor findByUuid(String uuid);	
	
	//返回对象的List
	public List<OilToHarbor> queryByEntitys(OilToHarbor entity);
	
	//返回Map的List
	public List<Map<String,Object>> queryRecords(Map<String,Object> map);
	
	public int countRecords(Map<String,Object> map);
	
	//**************************以下方法为开发者补充*********************************/
	
	
}
