package com.sinochem.crude.trade.orderexecute.dao;

import java.util.Map;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.sinochem.crude.trade.orderexecute.domain.InterfaceContrast;
//import org.apache.ibatis.annotations.Mapper;

//@Mapper
public interface InterfaceContrastMapper {

	public int insertRecord(InterfaceContrast entity);
	
	public int deleteById( @Param("contrastId") Long contrastId);
	
	public int deleteRecords(InterfaceContrast entity);
	
	public int updateRecordById(InterfaceContrast entity);
	
	public int updateRecords(InterfaceContrast entity);
	
	public InterfaceContrast findByPrimaryKey( /* @Param("contrastId") */ Long contrastId);	
	
	public InterfaceContrast findByUuid(String uuid);	
	
	//返回对象的List
	public List<InterfaceContrast> queryByEntitys(InterfaceContrast entity);
	
	//返回Map的List
	public List<Map<String,Object>> queryRecords(Map<String,Object> map);
	
	public int countRecords(Map<String,Object> map);
	
	//**************************以下方法为开发者补充*********************************/
	public int deleteRecordsByid(Long id);
	
}
