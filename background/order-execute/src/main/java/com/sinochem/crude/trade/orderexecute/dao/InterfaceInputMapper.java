package com.sinochem.crude.trade.orderexecute.dao;

import java.util.Map;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.sinochem.crude.trade.orderexecute.domain.InterfaceInput;
//import org.apache.ibatis.annotations.Mapper;

//@Mapper
public interface InterfaceInputMapper {

	public int insertRecord(InterfaceInput entity);
	
	public int deleteById( @Param("inputId") Long inputId);
	
	public int deleteRecords(InterfaceInput entity);
	
	public int updateRecordById(InterfaceInput entity);
	
	public int updateRecords(Map<String,Object> map);
	
	public InterfaceInput findByPrimaryKey( /* @Param("inputId") */ Long inputId);	
	
	public InterfaceInput findByUuid(String uuid);	
	
	//返回对象的List
	public List<InterfaceInput> queryByEntitys(InterfaceInput entity);
	
	//返回Map的List
	public List<Map<String,Object>> queryRecords(Map<String,Object> map);
	
	public int countRecords(Map<String,Object> map);
	
	//**************************以下方法为开发者补充*********************************/
	
	public List<InterfaceInput> queryLogging(Map<String,Object> map);

}
