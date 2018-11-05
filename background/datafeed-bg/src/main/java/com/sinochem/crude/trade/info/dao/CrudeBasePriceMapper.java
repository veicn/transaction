package com.sinochem.crude.trade.info.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sinochem.crude.trade.info.domain.CrudeBasePrice;

//@Mapper
public interface CrudeBasePriceMapper {

	public int insertRecord(CrudeBasePrice entity);
	
	public int deleteById( @Param("id") Long id);
	
	public int deleteRecords(CrudeBasePrice entity);
	
	public int updateRecordById(CrudeBasePrice entity);
	
	public int updateRecords(Map<String,Object> map);
	
	public CrudeBasePrice findByPrimaryKey( Long id);	
	
	public CrudeBasePrice findByUuid(String uuid);	
	
	//返回对象的List
	public List<CrudeBasePrice> queryByEntitys(CrudeBasePrice entity);
	
	//返回Map的List
	public List<Map<String,Object>> queryRecords(Map<String,Object> map);
	
	public int countRecords(Map<String,Object> map);

	
	//**************************以下方法为开发者补充*********************************/
	public List<String> getDateList();

	public List<Map<String, Object>> getFrontOfficialList(@Param("sb")String sb);
}
