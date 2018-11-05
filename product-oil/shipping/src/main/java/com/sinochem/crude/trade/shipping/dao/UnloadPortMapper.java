package com.sinochem.crude.trade.shipping.dao;

import java.util.Map;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.sinochem.crude.trade.shipping.domain.UnloadPort;
//import org.apache.ibatis.annotations.Mapper;

//@Mapper
public interface UnloadPortMapper {

	public int insertRecord(UnloadPort entity);
	
	public int deleteById( @Param("unloadPortId") Long unloadPortId);
	
	public int deleteRecords(UnloadPort entity);
	
	public int updateRecordById(UnloadPort entity);
	
	public int updateRecordByUuid(UnloadPort entity);
	
	public int updateRecords(Map<String,Object> map);
	
	public UnloadPort findByPrimaryKey( Long unloadPortId);	
	
	public UnloadPort findByUuid(String uuid);	
	
	//返回对象的List
	public List<UnloadPort> queryByEntitys(UnloadPort entity);
	
	//返回Map的List
	public List<Map<String,Object>> queryRecords(Map<String,Object> map);
	
	public int countRecords(Map<String,Object> map);

	
	//**************************以下方法为开发者补充*********************************/
	//查询船舶卸港信息
	public UnloadPort findByShipConfirmationSheetId(Long confirmationSheetId);
	
}
