package com.sinochem.crude.trade.info.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sinochem.crude.trade.info.domain.FuturesPrice;

//@Mapper
public interface FuturesPriceMapper {

	public int insertRecord(FuturesPrice entity);
	
	public int deleteById( @Param("id") Long id);
	
	public int deleteRecords(FuturesPrice entity);
	
	public int updateRecordById(FuturesPrice entity);
	
	public int updateRecords(Map<String,Object> map);
	
	public FuturesPrice findByPrimaryKey( Long id);	
	
	public FuturesPrice findByUuid(String uuid);	
	
	//返回对象的List
	public List<FuturesPrice> queryByEntitys(FuturesPrice entity);
	
	//返回Map的List
	public List<Map<String,Object>> queryRecords(Map<String,Object> map);
	
	public int countRecords(Map<String,Object> map);

	
	//**************************以下方法为开发者补充*********************************/
}
