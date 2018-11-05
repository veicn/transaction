package com.sinochem.crude.trade.orderexecute.dao;

import java.util.Map;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.sinochem.crude.trade.orderexecute.domain.InterfaceSystem;
//import org.apache.ibatis.annotations.Mapper;

//@Mapper
public interface InterfaceSystemMapper {

	public int insertRecord(InterfaceSystem entity);
	
	public int deleteById( @Param("sysId") Long sysId);
	
	public int deleteRecords(InterfaceSystem entity);
	
	public int updateRecordById(InterfaceSystem entity);
	
	public int updateRecords(Map<String,Object> map);
	
	public InterfaceSystem findByPrimaryKey( /* @Param("sysId") */ Long sysId);	
	
	public InterfaceSystem findByUuid(String uuid);	
	
	//返回对象的List
	public List<InterfaceSystem> queryByEntitys(InterfaceSystem entity);
	
	//返回Map的List
	public List<Map<String,Object>> queryRecords(Map<String,Object> map);
	
	public int countRecords(Map<String,Object> map);
	
	//**************************以下方法为开发者补充*********************************/
	
	public InterfaceSystem findByMemberId(Long memberId);
	
}
