package com.sinochem.crude.trade.orderexecute.dao;

import java.util.Map;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.sinochem.crude.trade.orderexecute.domain.InterfaceList;
//import org.apache.ibatis.annotations.Mapper;

//@Mapper
public interface InterfaceListMapper {

	public int insertRecord(InterfaceList entity);
	
	public int deleteById( @Param("interfaceId") Long interfaceId);
	
	public int deleteRecords(InterfaceList entity);
	
	public int updateRecordById(InterfaceList entity);
	
	public int updateRecords(Map<String,Object> map);
	
	public InterfaceList findByPrimaryKey( /* @Param("interfaceId") */ Long interfaceId);	
	
	public InterfaceList findByUuid(String uuid);	
	
	//返回对象的List
	public List<InterfaceList> queryByEntitys(InterfaceList entity);
	
	//返回Map的List
	public List<Map<String,Object>> queryRecords(Map<String,Object> map);
	
	public int countRecords(Map<String,Object> map);
	
	//**************************以下方法为开发者补充*********************************/
	
	
}
