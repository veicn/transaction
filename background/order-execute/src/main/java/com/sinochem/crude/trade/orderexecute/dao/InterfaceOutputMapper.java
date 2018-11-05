package com.sinochem.crude.trade.orderexecute.dao;

import java.util.Map;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.sinochem.crude.trade.orderexecute.domain.InterfaceOutput;
//import org.apache.ibatis.annotations.Mapper;

//@Mapper
public interface InterfaceOutputMapper {

	public int insertRecord(InterfaceOutput entity);
	
	public int deleteById( @Param("outputId") Long outputId);
	
	public int deleteRecords(InterfaceOutput entity);
	
	public int updateRecordById(InterfaceOutput entity);
	
	public int updateRecords(Map<String,Object> map);
	
	public InterfaceOutput findByPrimaryKey( /* @Param("outputId") */ Long outputId);	
	
	public InterfaceOutput findByUuid(String uuid);	
	
	//返回对象的List
	public List<InterfaceOutput> queryByEntitys(InterfaceOutput entity);
	
	//返回Map的List
	public List<Map<String,Object>> queryRecords(Map<String,Object> map);
	
	public int countRecords(Map<String,Object> map);
	
	//**************************以下方法为开发者补充*********************************/
	
	
}
