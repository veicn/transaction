package com.sinochem.crude.trade.orderexecute.dao;

import java.util.Map;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.sinochem.crude.trade.orderexecute.domain.InterfaceMember;
//import org.apache.ibatis.annotations.Mapper;

//@Mapper
public interface InterfaceMemberMapper {

	public int insertRecord(InterfaceMember entity);
	
	public int deleteById( @Param("interfaceMemberId") Long interfaceMemberId);
	
	public int deleteRecords(InterfaceMember entity);
	
	public int updateRecordById(InterfaceMember entity);
	
	public int updateRecords(Map<String,Object> map);
	
	public InterfaceMember findByPrimaryKey( /* @Param("interfaceMemberId") */ Long interfaceMemberId);	
	
	public InterfaceMember findByUuid(String uuid);	
	
	//返回对象的List
	public List<InterfaceMember> queryByEntitys(InterfaceMember entity);
	
	//返回Map的List
	public List<Map<String,Object>> queryRecords(Map<String,Object> map);
	
	public int countRecords(Map<String,Object> map);
	
	//**************************以下方法为开发者补充*********************************/
	
	
}
