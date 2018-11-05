package com.sinochem.crude.trade.info.dao;

import java.util.Map;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.sinochem.crude.trade.info.domain.Collection;
//import org.apache.ibatis.annotations.Mapper;

//@Mapper
public interface CollectionMapper {

	public int insertRecord(Collection entity);
	
	public int deleteById(@Param("id") Long id);
	
	public int deleteRecords(Collection entity);
	
	public int updateRecordById(Collection entity);
	
	public int updateRecords(Map<String, Object> map);
	
	public Collection findByPrimaryKey(Long id);
	
	public Collection findByUuid(String uuid);	
	
	//返回对象的List
	public List<Collection> queryByEntitys(Collection entity);
	
	//返回Map的List
	public List<Map<String,Object>> queryRecords(Map<String, Object> map);
	
	public int countRecords(Map<String, Object> map);
	
	//**************************以下方法为开发者补充*********************************/
	
}
