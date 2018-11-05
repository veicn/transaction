package com.sinochem.crude.trade.info.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sinochem.crude.trade.info.domain.Quality;
//import org.apache.ibatis.annotations.Mapper;

//@Mapper
public interface QualityMapper {

	public int insertRecord(Quality entity);
	
	public int deleteById( @Param("id") Long id);
	
	public int deleteRecords(Quality entity);
	
	public int updateRecordById(Quality entity);
	
	public int updateRecords(Map<String,Object> map);
	
	public Quality findByPrimaryKey( Long id);	
	
	public Quality findByUuid(String uuid);	
	
	//返回对象的List
	public List<Quality> queryByEntitys(Quality entity);
	
	//返回Map的List
	public List<Map<String,Object>> queryRecords(Map<String,Object> map);
	
	public int countRecords(Map<String,Object> map);
	
	//**************************以下方法为开发者补充*********************************/
	
	/**
	 * 查询列表主频道
	 */
	public List<Map<String,Object>> crudeRecords(Map<String,Object> map);
	
	/**
	 * 原油删除
	 */	
	public int crudeDeleteById(String uuid);
	
}
