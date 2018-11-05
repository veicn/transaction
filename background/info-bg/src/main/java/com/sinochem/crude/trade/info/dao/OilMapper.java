package com.sinochem.crude.trade.info.dao;

import java.util.Map;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.sinochem.crude.trade.info.domain.Oil;
//import org.apache.ibatis.annotations.Mapper;

//@Mapper
public interface OilMapper {

	public int insertRecord(Oil entity);
	
	public int deleteById( @Param("id") Long id);
	
	public int deleteRecords(Oil entity);
	
	public int updateRecordById(Oil entity);
	
	public int updateRecords(Map<String,Object> map);
	
	public Oil findByPrimaryKey( Long id);	
	
	public Oil findByUuid(String uuid);	
	
	//返回对象的List
	public List<Oil> queryByEntitys(Oil entity);
	
	//返回Map的List
	public List<Map<String,Object>> queryRecords(Map<String,Object> map);
	
	public int countRecords(Map<String,Object> map);
	
	//**************************以下方法为开发者补充*********************************/
	
	/**
	 * 新增查询
	 */
	public List<Map<String,Object>> addCrudeRecords(Map<String,Object> map);
}
