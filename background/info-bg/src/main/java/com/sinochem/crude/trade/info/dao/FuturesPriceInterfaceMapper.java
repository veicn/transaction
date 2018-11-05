package com.sinochem.crude.trade.info.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sinochem.crude.trade.info.domain.FuturesPriceInterface;

//@Mapper
public interface FuturesPriceInterfaceMapper {

	public int insertRecord(FuturesPriceInterface entity);
	
	public int deleteById( @Param("id") Long id);
	
	public int deleteRecords(FuturesPriceInterface entity);
	
	public int updateRecordById(FuturesPriceInterface entity);
	
	public int updateRecords(Map<String,Object> map);
	
	public FuturesPriceInterface findByPrimaryKey( Long id);	
	
	public FuturesPriceInterface findByUuid(String uuid);	
	
	//返回对象的List
	public List<FuturesPriceInterface> queryByEntitys(FuturesPriceInterface entity);
	
	//返回Map的List
	public List<Map<String,Object>> queryRecords(Map<String,Object> map);
	
	public int countRecords(Map<String,Object> map);
	
	//**************************以下方法为开发者补充*********************************/
	
	
}
