package com.sinochem.crude.trade.info.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sinochem.crude.trade.info.domain.AgioInterface;

//@Mapper
public interface AgioInterfaceMapper {

	public int insertRecord(AgioInterface entity);
	
	public int deleteById( @Param("id") Long id);
	
	public int deleteRecords(AgioInterface entity);
	
	public int updateRecordById(AgioInterface entity);
	
	public int updateRecords(Map<String,Object> map);
	
	public AgioInterface findByPrimaryKey( Long id);	
	
	public AgioInterface findByUuid(String uuid);	
	
	//返回对象的List
	public List<AgioInterface> queryByEntitys(AgioInterface entity);
	
	//返回Map的List
	public List<Map<String,Object>> queryRecords(Map<String,Object> map);
	
	public int countRecords(Map<String,Object> map);
	
	//**************************以下方法为开发者补充*********************************/
	
	
}
