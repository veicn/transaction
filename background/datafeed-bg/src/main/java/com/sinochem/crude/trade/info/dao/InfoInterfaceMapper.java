package com.sinochem.crude.trade.info.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sinochem.crude.trade.info.domain.InfoInterface;

//@Mapper
public interface InfoInterfaceMapper {

	public int insertRecord(InfoInterface entity);
	
	public int deleteById( @Param("id") Long id);
	
	public int deleteRecords(InfoInterface entity);
	
	public int updateRecordById(InfoInterface entity);
	
	public int updateRecords(Map<String,Object> map);
	
	public InfoInterface findByPrimaryKey( Long id);	
	
	public InfoInterface findByUuid(String uuid);	
	
	//返回对象的List
	public List<InfoInterface> queryByEntitys(InfoInterface entity);
	
	//返回Map的List
	public List<Map<String,Object>> queryRecords(Map<String,Object> map);
	
	public int countRecords(Map<String,Object> map);
	
	//**************************以下方法为开发者补充*********************************/
	
	
}
