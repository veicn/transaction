package com.sinochem.crude.trade.orderexecute.dao;

import java.util.Map;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.sinochem.crude.trade.orderexecute.domain.TriggerResult;
//import org.apache.ibatis.annotations.Mapper;

//@Mapper
public interface TriggerResultMapper {

	public int insertRecord(TriggerResult entity);
	
	public int deleteById( @Param("id") Long id);
	
	public int deleteRecords(TriggerResult entity);
	
	public int updateRecordById(TriggerResult entity);
	
	public int updateRecords(Map<String,Object> map);
	
	public TriggerResult findByPrimaryKey( /* @Param("id") */ Long id);	
	
	public TriggerResult findByUuid(String uuid);	
	
	//返回对象的List
	public List<TriggerResult> queryByEntitys(TriggerResult entity);
	
	//返回Map的List
	public List<Map<String,Object>> queryRecords(Map<String,Object> map);
	
	public int countRecords(Map<String,Object> map);
	
	//**************************以下方法为开发者补充*********************************/
	
	
}
