package com.sinochem.crude.trade.info.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sinochem.crude.trade.info.domain.ExternalInteractive;

//@Mapper
public interface ExternalInteractiveMapper {

	public int insertRecord(ExternalInteractive entity);
	
	public int deleteById( @Param("id") Long id);
	
	public int deleteRecords(ExternalInteractive entity);
	
	public int updateRecordById(ExternalInteractive entity);
	
	public int updateRecords(Map<String,Object> map);
	
	public ExternalInteractive findByPrimaryKey( Long id);	
	
	public ExternalInteractive findByUuid(String uuid);	
	
	//返回对象的List
	public List<ExternalInteractive> queryByEntitys(ExternalInteractive entity);
	
	//返回Map的List
	public List<Map<String,Object>> queryRecords(Map<String,Object> map);
	
	public int countRecords(Map<String,Object> map);
	
	//**************************以下方法为开发者补充*********************************/
	
	
}
