package com.sinochem.crude.trade.info.dao;

import java.util.Map;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.sinochem.crude.trade.info.domain.FeedClassify;
//import org.apache.ibatis.annotations.Mapper;

//@Mapper
public interface FeedClassifyMapper {

	public int insertRecord(FeedClassify entity);
	
	public int deleteById( @Param("id") String id);
	
	public int deleteRecords(FeedClassify entity);
	
	public int updateRecordById(FeedClassify entity);
	
	public int updateRecordByUuid(FeedClassify entity);
	
	public int updateRecords(Map<String,Object> map);
	
	public FeedClassify findByPrimaryKey( String id);	
	
	public FeedClassify findByUuid(String uuid);	
	
	//返回对象的List
	public List<FeedClassify> queryByEntitys(FeedClassify entity);
	
	//返回Map的List
	public List<Map<String,Object>> queryRecords(Map<String,Object> map);
	
	public int countRecords(Map<String,Object> map);

	public FeedClassify getQuotationClass(String id);
	
	//**************************以下方法为开发者补充*********************************/
	
	
}
