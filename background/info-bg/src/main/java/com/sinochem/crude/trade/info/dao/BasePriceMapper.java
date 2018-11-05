package com.sinochem.crude.trade.info.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sinochem.crude.trade.info.domain.BasePrice;
//import org.apache.ibatis.annotations.Mapper;

//@Mapper
public interface BasePriceMapper {

	public int insertRecord(BasePrice entity);
	
	public int deleteById( @Param("id") Long id);
	
	public int deleteRecords(BasePrice entity);
	
	public int updateRecordById(BasePrice entity);
	
	public int updateRecordByUuid(BasePrice entity);
	
	public int updateRecords(Map<String,Object> map);
	
	public BasePrice findByPrimaryKey( Long id);	
	
	public BasePrice findByUuid(String uuid);	
	
	//返回对象的List
	public List<BasePrice> queryByEntitys(BasePrice entity);
	
	//返回Map的List
	public List<Map<String,Object>> queryRecords(Map<String,Object> map);
	
	public int countRecords(Map<String,Object> map);

	
	
	//**************************以下方法为开发者补充*********************************/
	
	public BasePrice findLastRecordByTempId(Long tempId);

	public List<Map<String, Object>> queryLikeRecords(Map<String, Object> beanToMap);
}
