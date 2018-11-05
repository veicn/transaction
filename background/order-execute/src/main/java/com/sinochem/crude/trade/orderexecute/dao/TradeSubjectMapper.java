package com.sinochem.crude.trade.orderexecute.dao;

import java.util.Map;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.sinochem.crude.trade.orderexecute.domain.TradeSubject;
//import org.apache.ibatis.annotations.Mapper;

//@Mapper
public interface TradeSubjectMapper {

	public int insertRecord(TradeSubject entity);
	
	public int deleteById( @Param("tradeSubjectId") Long tradeSubjectId);
	
	public int deleteRecords(TradeSubject entity);
	
	public int updateRecordById(TradeSubject entity);
	
	public int updateRecords(Map<String,Object> map);
	
	public TradeSubject findByPrimaryKey( /* @Param("tradeSubjectId") */ Long tradeSubjectId);	
	
	public TradeSubject findByUuid(String uuid);	
	
	//返回对象的List
	public List<TradeSubject> queryByEntitys(TradeSubject entity);
	
	//返回Map的List
	public List<Map<String,Object>> queryRecords(Map<String,Object> map);
	
	public int countRecords(Map<String,Object> map);
	
	//**************************以下方法为开发者补充*********************************/
	
	
}
