package com.sinochem.crude.trade.info.dao;

import java.util.Map;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.sinochem.crude.trade.info.domain.BrowsingRecord;
//import org.apache.ibatis.annotations.Mapper;

//@Mapper
public interface BrowsingRecordMapper {

	public int insertRecord(BrowsingRecord entity);
	
	public int deleteById( @Param("id") Long id);
	
	public int deleteRecords(BrowsingRecord entity);
	
	public int updateRecordById(BrowsingRecord entity);
	
	public int updateRecordByUuid(BrowsingRecord entity);
	
	public int updateRecords(Map<String,Object> map);
	
	public BrowsingRecord findByPrimaryKey( Long id);	
	
	public BrowsingRecord findByUuid(String uuid);	
	
	//返回对象的List
	public List<BrowsingRecord> queryByEntitys(BrowsingRecord entity);
	
	//返回Map的List
	public List<Map<String,Object>> queryRecords(Map<String,Object> map);
	
	public int countRecords(Map<String,Object> map);
	
	//**************************以下方法为开发者补充*********************************/
	
	
}
