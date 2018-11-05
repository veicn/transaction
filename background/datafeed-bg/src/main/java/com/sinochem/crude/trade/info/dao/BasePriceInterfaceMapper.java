package com.sinochem.crude.trade.info.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sinochem.crude.trade.info.domain.BasePriceInterface;

//@Mapper
public interface BasePriceInterfaceMapper {

	public int insertRecord(BasePriceInterface entity);
	
	public int deleteById( @Param("id") Long id);
	
	public int deleteRecords(BasePriceInterface entity);
	
	public int updateRecordById(BasePriceInterface entity);
	
	public int updateRecords(Map<String,Object> map);
	
	public BasePriceInterface findByPrimaryKey( Long id);	
	
	public BasePriceInterface findByUuid(String uuid);	
	
	//返回对象的List
	public List<BasePriceInterface> queryByEntitys(BasePriceInterface entity);
	
	//返回Map的List
	public List<Map<String,Object>> queryRecords(Map<String,Object> map);
	
	public int countRecords(Map<String,Object> map);
	
	//**************************以下方法为开发者补充*********************************/
	
	
}
