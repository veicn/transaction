package com.sinochem.crude.trade.orderexecute.dao;

import java.util.Map;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.sinochem.crude.trade.orderexecute.domain.TriggerContract;
//import org.apache.ibatis.annotations.Mapper;

//@Mapper
public interface TriggerContractMapper {

	public int insertRecord(TriggerContract entity);
	
	public int deleteById( @Param("id") Long id);
	
	public int deleteRecords(TriggerContract entity);
	
	public int updateRecordById(TriggerContract entity);
	
	public int updateRecords(Map<String,Object> map);
	
	public TriggerContract findByPrimaryKey( /* @Param("id") */ Long id);	
	
	public TriggerContract findByUuid(String uuid);	
	
	//返回对象的List
	public List<TriggerContract> queryByEntitys(TriggerContract entity);
	
	//返回Map的List
	public List<Map<String,Object>> queryRecords(Map<String,Object> map);
	
	public int countRecords(Map<String,Object> map);
	
	//**************************以下方法为开发者补充*********************************/
	
	
}
