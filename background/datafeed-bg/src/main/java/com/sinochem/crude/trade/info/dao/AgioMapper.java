package com.sinochem.crude.trade.info.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sinochem.crude.trade.info.domain.Agio;

//@Mapper
public interface AgioMapper {

	public int insertRecord(Agio entity);
	
	public int deleteById( @Param("id") Long id);
	
	public int deleteRecords(Agio entity);
	
	public int updateRecordById(Agio entity);
	
	public int updateRecords(Map<String,Object> map);
	
	public Agio findByPrimaryKey( Long id);	
	
	public Agio findByUuid(String uuid);	
	
	//返回对象的List
	public List<Agio> queryByEntitys(Agio entity);
	
	//返回Map的List
	public List<Map<String,Object>> queryRecords(Map<String,Object> map);
	
	public int countRecords(Map<String,Object> map);

	
	//**************************以下方法为开发者补充*********************************/
}
