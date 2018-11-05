package com.sinochem.crude.trade.orderexecute.dao;

import java.util.Map;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.sinochem.crude.trade.orderexecute.domain.TriggerApply;
//import org.apache.ibatis.annotations.Mapper;

//@Mapper
public interface TriggerApplyMapper {

	public int insertRecord(TriggerApply entity);
	
	public int deleteById( @Param("id") Long id);
	
	public int deleteRecords(TriggerApply entity);
	
	public int updateRecordById(TriggerApply entity);
	
	public int updateRecords(Map<String,Object> map);
	
	public TriggerApply findByPrimaryKey( /* @Param("id") */ Long id);	
	
	public TriggerApply findByUuid(String uuid);	
	
	//返回对象的List
	public List<TriggerApply> queryByEntitys(TriggerApply entity);
	
	//返回Map的List
	public List<Map<String,Object>> queryRecords(Map<String,Object> map);
	
	public int countRecords(Map<String,Object> map);
	
	//**************************以下方法为开发者补充*********************************/
	
	
}
