package com.sinochem.crude.trade.orderexecute.dao;

import java.util.Map;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.sinochem.crude.trade.orderexecute.domain.FeeSubject;
//import org.apache.ibatis.annotations.Mapper;

//@Mapper
public interface FeeSubjectMapper {

	public int insertRecord(FeeSubject entity);
	
	public int deleteById( @Param("feeSubjectId") Long feeSubjectId);
	
	public int deleteRecords(FeeSubject entity);
	
	public int updateRecordById(FeeSubject entity);
	
	public int updateRecords(Map<String,Object> map);
	
	public FeeSubject findByPrimaryKey( /* @Param("feeSubjectId") */ Long feeSubjectId);	
	
	public FeeSubject findByUuid(String uuid);	
	
	//返回对象的List
	public List<FeeSubject> queryByEntitys(FeeSubject entity);
	
	//返回Map的List
	public List<Map<String,Object>> queryRecords(Map<String,Object> map);
	
	public int countRecords(Map<String,Object> map);
	
	//**************************以下方法为开发者补充*********************************/
	
	
}
