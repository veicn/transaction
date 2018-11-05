package com.sinochem.crude.trade.operation.dao;

import java.util.Map;
import java.util.List;

import com.sinochem.crude.trade.operation.domain.Enu;
//import org.apache.ibatis.annotations.Mapper;

//@Mapper
public interface EnuMapper {

	public int insertRecord(Enu entity);
	
	public int deleteById();
	
	public int deleteRecords(Enu entity);
	
	public int updateRecordById(Enu entity);
	
	public int updateRecordByMenuId(Enu entity);
	
	public int updateRecords(Map<String,Object> map);
	
	public Enu findByPrimaryKey();	
	
	public Enu findByUuid(String uuid);	
	
	//返回对象的List
	public List<Enu> queryByEntitys(Enu entity);
	
	//返回Map的List
	public List<Map<String,Object>> queryRecords(Map<String,Object> map);
	
	public int countRecords(Map<String,Object> map);

	public int tpMenuDelete(String menuId);
	
	//**************************以下方法为开发者补充*********************************/
	
	
}
